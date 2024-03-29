package com.codesqaude.cocomarco.domain.oauth;

import com.codesqaude.cocomarco.domain.oauth.dto.AccessToken;
import com.codesqaude.cocomarco.domain.oauth.dto.AccessTokenRequest;
import com.codesqaude.cocomarco.service.GitOAuthType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static com.codesqaude.cocomarco.domain.oauth.GitOauthUri.ACCESS_TOKEN_URI;
import static com.codesqaude.cocomarco.domain.oauth.GitOauthUri.USER_INFO_URI;

public class GitOAuth implements OAuth {

    private final String clientId;
    private final String clientSecret;
    private final RestTemplate restTemplate = new RestTemplate();

    public GitOAuth(GitOAuthType type) {
        this.clientId = type.getClientId();
        this.clientSecret = type.getClientSecret();
    }

    @Override
    public AccessToken accessToken(String code) {
        HttpHeaders httpHeaders = httpHeadersTypeJson();
        HttpEntity<AccessTokenRequest> httpEntity =
                new HttpEntity<>(new AccessTokenRequest(clientId, clientSecret, code), httpHeaders);
        return restTemplate.exchange(ACCESS_TOKEN_URI.getUri(), HttpMethod.POST, httpEntity, AccessToken.class).getBody();
    }

    @Override
    public GitUserInfo userInfo(AccessToken accessToken) {
        HttpHeaders httpHeaders = httpHeadersTypeJson();
        httpHeaders.setBearerAuth(accessToken.getAccessToken());
        HttpEntity<?> userInfo = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(USER_INFO_URI.getUri(), HttpMethod.GET, userInfo, GitUserInfo.class).getBody();
    }

    public HttpHeaders httpHeadersTypeJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
