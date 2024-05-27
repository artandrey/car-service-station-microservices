package com.example.api_gateway.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import javax.ws.rs.core.Response;

import com.example.api_gateway.modules.auth.models.AuthResult;
import com.example.api_gateway.modules.auth.models.SignInCredentials;
import com.example.api_gateway.modules.auth.models.SignUpCredentials;
import com.example.api_gateway.modules.auth.services.auth.implementation.AuthService;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private Keycloak keycloak;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(authService, "realm", "test-realm");
        ReflectionTestUtils.setField(authService, "keycloakClientId", "test-client-id");
        ReflectionTestUtils.setField(authService, "keycloakClientSecret", "test-client-secret");
        ReflectionTestUtils.setField(authService, "keycloakGetTokenUrl",
                "http://localhost:8080/realms/test-realm/protocol/openid-connect/token");
    }

    @Test
    public void testSignIn() {
        SignInCredentials credentials = new SignInCredentials("testuser", "password");
        AuthResult expectedAuthResult = new AuthResult("accessToken", "refreshToken", 3600, 1000);

        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(AuthResult.class)))
                .thenReturn(new ResponseEntity<>(expectedAuthResult, HttpStatus.OK));

        AuthResult result = authService.signIn(credentials);

        assertNotNull(result);
        assertEquals("accessToken", result.getAccessToken());
        assertEquals("refreshToken", result.getRefreshToken());
        assertEquals(3600, result.getExpiresIn());

        verify(restTemplate, times(1)).postForEntity(anyString(), any(HttpEntity.class), eq(AuthResult.class));
    }

    @Test
    public void testSignUp() {
        SignUpCredentials credentials = new SignUpCredentials();
        credentials.setUsername("user");
        credentials.setPassword("password");
        credentials.setEmail("user@example.com");
        credentials.setFirstName("Test");
        credentials.setLastName("Test");

        UsersResource usersResource = mock(UsersResource.class);
        Response response = mock(Response.class);

        when(keycloak.realm(anyString())).thenReturn(mock(RealmResource.class));
        when(keycloak.realm(anyString()).users()).thenReturn(usersResource);
        when(usersResource.create(any(UserRepresentation.class))).thenReturn(response);
        when(response.getStatus()).thenReturn(Response.Status.CREATED.getStatusCode());

        AuthResult expectedAuthResult = new AuthResult("accessToken", "refreshToken", 3600, 1000);

        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(AuthResult.class)))
                .thenReturn(new ResponseEntity<>(expectedAuthResult, HttpStatus.OK));

        AuthResult result = authService.signUp(credentials);

        assertNotNull(result);
        assertEquals("accessToken", result.getAccessToken());
        assertEquals("refreshToken", result.getRefreshToken());
        assertEquals(3600, result.getExpiresIn());

        verify(usersResource, times(1)).create(any(UserRepresentation.class));
        verify(restTemplate, times(1)).postForEntity(anyString(), any(HttpEntity.class), eq(AuthResult.class));
    }

    @Test
    public void testRefreshToken() {
        String refreshToken = "test-refresh-token";
        AuthResult expectedAuthResult = new AuthResult("newAccessToken", "newRefreshToken", 3600, 10000);

        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(AuthResult.class)))
                .thenReturn(new ResponseEntity<>(expectedAuthResult, HttpStatus.OK));

        AuthResult result = authService.refresh(refreshToken);

        assertNotNull(result);
        assertEquals("newAccessToken", result.getAccessToken());
        assertEquals("newRefreshToken", result.getRefreshToken());
        assertEquals(3600, result.getExpiresIn());

        verify(restTemplate, times(1)).postForEntity(anyString(), any(HttpEntity.class), eq(AuthResult.class));
    }
}
