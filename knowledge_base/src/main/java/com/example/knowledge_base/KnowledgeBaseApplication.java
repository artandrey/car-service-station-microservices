package com.example.knowledge_base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class KnowledgeBaseApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(KnowledgeBaseApplication.class).web(WebApplicationType.NONE).run(args);
	}

}
