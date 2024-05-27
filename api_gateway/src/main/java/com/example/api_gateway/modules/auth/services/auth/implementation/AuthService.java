package com.example.api_gateway.modules.auth.services.auth.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.api_gateway.modules.auth.exceptions.SignUpFailedException;
import com.example.api_gateway.modules.auth.exceptions.UnexpectedSignUpException;
import com.example.api_gateway.modules.auth.models.AuthResult;
import com.example.api_gateway.modules.auth.models.SignInCredentials;
import com.example.api_gateway.modules.auth.models.SignUpCredentials;
import com.example.api_gateway.modules.auth.services.auth.IAuthService;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService implements IAuthService {

    private final Keycloak keycloak;
    private final RestTemplate restTemplate;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.client}")
    private String keycloakClientId;

    @Value("${keycloak.secret}")
    private String keycloakClientSecret;

    @Value("${keycloak.get-token-url}")
    private String keycloakGetTokenUrl;

    private static final String GRANT_TYPE_PASSWORD = "password";
    private static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";

    @Override
    public AuthResult signIn(SignInCredentials credentials) {
        return getAccessToken(credentials);
    }

    @Override
    public AuthResult signUp(SignUpCredentials credentials) {
        UserRepresentation userRepresentation = getUserRepresentation(credentials);
        UsersResource usersResource = getUsersResource();
        userRepresentation.setGroups(List.of("Clients"));

        try (Response response = usersResource.create(userRepresentation)) {

            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                SignInCredentials signInCredentials = new SignInCredentials();
                signInCredentials.setUsername(credentials.getUsername());
                signInCredentials.setPassword(credentials.getPassword());
                System.out.println(credentials.getUsername());
                System.out.println(credentials.getPassword());

                return signIn(signInCredentials);
            } else {
                Map<String, String> resultMap = response.readEntity(new GenericType<>() {
                });
                throw new SignUpFailedException(resultMap.get("errorMessage"));
            }
        } catch (Exception e) {
            if (e instanceof SignUpFailedException registrationException) {
                throw new SignUpFailedException(registrationException.getMessage());
            } else {
                System.out.println(e.getMessage());
                throw new UnexpectedSignUpException();
            }

        }
    }

    private UserRepresentation getUserRepresentation(SignUpCredentials signUpCredentials) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(signUpCredentials.getUsername());
        user.setEmail(signUpCredentials.getEmail());
        user.setFirstName(signUpCredentials.getFirstName());
        user.setLastName(signUpCredentials.getLastName());
        user.setEmailVerified(false);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(signUpCredentials.getPassword());
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        List<CredentialRepresentation> list = new ArrayList<>();
        list.add(credentialRepresentation);
        user.setCredentials(list);
        return user;
    }

    @Override
    public AuthResult refresh(String refreshToken) {
        return getRefreshToken(refreshToken);
    }

    private UsersResource getUsersResource() {
        RealmResource resource = keycloak.realm(realm);
        return resource.users();
    }

    private AuthResult getRefreshToken(String refreshToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", GRANT_TYPE_REFRESH_TOKEN);
        requestBody.add("refresh_token", refreshToken);
        requestBody.add("client_id", keycloakClientId);
        requestBody.add("client_secret", keycloakClientSecret);

        ResponseEntity<AuthResult> response = restTemplate.postForEntity(keycloakGetTokenUrl,
                new HttpEntity<>(requestBody, headers), AuthResult.class);

        return response.getBody();
    }

    private AuthResult getAccessToken(SignInCredentials request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", GRANT_TYPE_PASSWORD);
        requestBody.add("client_id", keycloakClientId);
        requestBody.add("client_secret", keycloakClientSecret);
        requestBody.add("username", request.getUsername());
        requestBody.add("password", request.getPassword());

        ResponseEntity<AuthResult> response = restTemplate.postForEntity(keycloakGetTokenUrl,
                new HttpEntity<>(requestBody, headers), AuthResult.class);

        return response.getBody();
    }
}
