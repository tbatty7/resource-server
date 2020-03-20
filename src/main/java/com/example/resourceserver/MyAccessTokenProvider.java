package com.example.resourceserver;

import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class MyAccessTokenProvider extends ClientCredentialsAccessTokenProvider {
    private String applicationId;

    public MyAccessTokenProvider(String applicationId) {
        super();
        this.applicationId = applicationId;
    }

    @Override
    public OAuth2AccessToken obtainAccessToken(OAuth2ProtectedResourceDetails details, AccessTokenRequest request) throws UserRedirectRequiredException, AccessDeniedException, OAuth2AccessDeniedException {
        ClientCredentialsResourceDetails resourceDetails = (ClientCredentialsResourceDetails) details;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Application-Id", "23094590-application-id-29038");

        return retrieveToken(request, resourceDetails, formBodyParameters(resourceDetails), httpHeaders);
    }

    private MultiValueMap<String, String> formBodyParameters(ClientCredentialsResourceDetails resourceDetails) {
        LinkedMultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.set("grant_type", "client_credentials");

        form.set("scope", "expected scope");
        return form;
    }
}
