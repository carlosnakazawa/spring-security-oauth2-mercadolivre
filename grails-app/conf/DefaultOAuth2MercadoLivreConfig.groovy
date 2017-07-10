/**
 * Created by carlo on 03/07/2017.
 */
security {
    oauth2 {
        providers {
            mercadolivre {
                successUri = "/oauth2/mercadolivre/success"
                failureUri = "/oauth2/mercadolivre/failure"
                callback = "/oauth2/mercadolivre/callback"
                api_key = "changeme_apikey"
                api_secret = "changeme_apisecret"
            }
        }
    }
}
