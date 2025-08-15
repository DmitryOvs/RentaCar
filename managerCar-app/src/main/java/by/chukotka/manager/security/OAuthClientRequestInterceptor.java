package by.chukotka.manager.security;


import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import java.io.IOException;

@RequiredArgsConstructor
public class OAuthClientRequestInterceptor implements ClientHttpRequestInterceptor {

    private final OAuth2AuthorizedClientManager authorizedClientManager; //из его получаем информацию об аутентифицированном пользователе

    private final String registrationId; //регистратор индификатор регистрации OAuth относительно которой получают ключи доступа

    @Setter
    private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy(); //получаем информацию о пользователе

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            OAuth2AuthorizedClient oAuth2AuthorizedClient = this.authorizedClientManager.authorize(
                    OAuth2AuthorizeRequest.withClientRegistrationId(this.registrationId)
                            .principal(this.securityContextHolderStrategy.getContext().getAuthentication())
                            .build());
            request.getHeaders().setBearerAuth(oAuth2AuthorizedClient.getAccessToken().getTokenValue());
        }
        return execution.execute(request, body);
    }
}
