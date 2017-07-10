package grails.plugin.springsecurity.oauth2.mercadolivre

import com.github.scribejava.core.builder.api.DefaultApi20
import com.github.scribejava.core.model.OAuth2AccessToken
import com.github.scribejava.core.model.OAuthConstants
import com.github.scribejava.core.model.OAuthRequest
import com.github.scribejava.core.model.Verb
import grails.converters.JSON
import grails.plugin.springsecurity.oauth2.exception.OAuth2Exception
import grails.plugin.springsecurity.oauth2.service.OAuth2AbstractProviderService
import grails.plugin.springsecurity.oauth2.token.OAuth2SpringToken
import grails.transaction.Transactional

@Transactional
class MercadoLivreOAuth2ProviderService extends OAuth2AbstractProviderService {

    @Override
    String getProviderID() {
        return 'mercadolivre'
    }

    @Override
    Class<? extends DefaultApi20> getApiClass() {
        MercadoLivre2Api.class
    }

    @Override
    String getProfileScope() {
        return "https://api.mercadolibre.com/users/me"
    }

    @Override
    String getScopes() {
        return "read"
    }

    @Override
    String getScopeSeparator() {
        return " "
    }

    @Override
    OAuth2SpringToken createSpringAuthToken(OAuth2AccessToken accessToken) {
        def user
        def response = getResponse(accessToken)
        try {
            println "JSON response body: " + accessToken.rawResponse
            user = JSON.parse(response.body)
            println "Usuário: $user"
            println "Response: $response.body"
        } catch (Exception exception) {
            log.error("Error parsing response from " + getProviderID() + ". Response:\n" + response.body)
            throw new OAuth2Exception("Error parsing response from " + getProviderID(), exception)
        }
        if (!user?.email) {
            log.error("No user email from " + getProviderID() + ". Response was:\n" + response.body)
            throw new OAuth2Exception("No user email from " + getProviderID())
        }
        new MercadoLivreOauth2SpringToken(accessToken, user?.email, providerID)
    }
}
