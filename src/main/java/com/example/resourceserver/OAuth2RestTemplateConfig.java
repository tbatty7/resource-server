package com.example.resourceserver;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OAuth2RestTemplateConfig {
    @Bean
    public OAuth2RestTemplate customRestTemplate() {

        OAuth2ClientContext clientContext = new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest());
        OAuth2RestTemplate template = new OAuth2RestTemplate(clmResourceDetails(), clientContext);
        template.setAccessTokenProvider(new MyAccessTokenProvider("myApplicationId"));
        template.setErrorHandler(new SuppressErrorsErrorHandler(clmResourceDetails()));

        return template;
    }

    @Bean
    public OAuth2ProtectedResourceDetails clmResourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setClientId("myClientId");
        details.setClientSecret("myClientSecret");
        details.setScope(Arrays.asList("scope1"));

        Map<String, Object> accessTokenRequestParams = new HashMap<>();
        accessTokenRequestParams.put("program", "What is a program?");
        accessTokenRequestParams.put("requestChannel", "What is a request Channel?");

        URI accessTokenUri = UriComponentsBuilder.fromHttpUrl("whatever crazy url they created for us")
                .buildAndExpand(accessTokenRequestParams)
                .toUri();

        details.setAccessTokenUri(accessTokenUri.toString());

        return details;
    }

}
