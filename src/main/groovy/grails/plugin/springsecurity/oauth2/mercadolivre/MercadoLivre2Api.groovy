package grails.plugin.springsecurity.oauth2.mercadolivre

import com.github.scribejava.core.builder.api.DefaultApi20

class MercadoLivre2Api extends DefaultApi20 {

    private static final String AUTHORIZATION_BASE_URL = "https://auth.mercadolibre.com.ar/authorization"

    protected MercadoLivre2Api() {
    }

    private static class InstanceHolder {

        private static final MercadoLivre2Api INSTANCE = new MercadoLivre2Api();
    }

    public static MercadoLivre2Api instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    String getAccessTokenEndpoint() {
        return "https://api.mercadolibre.com/oauth/token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return AUTHORIZATION_BASE_URL
    }
}
