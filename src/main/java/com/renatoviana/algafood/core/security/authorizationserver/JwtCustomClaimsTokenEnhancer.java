package com.renatoviana.algafood.core.security.authorizationserver;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;

public class JwtCustomClaimsTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication oAuth2Authentication) {
        if (oAuth2Authentication.getPrincipal() instanceof AuthUser) {
            var authUser = (AuthUser) oAuth2Authentication.getPrincipal();

            var info = new HashMap<String, Object>();
            info.put("nome_completo", authUser.getFullName());
            info.put("usuario_id", authUser.getUserId());

            var oAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
            oAuth2AccessToken.setAdditionalInformation(info);
        }

        return accessToken;
    }

}
