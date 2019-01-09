package com.bassamworks.wearematchplay.constatnts

object Constants {

    object API {
        const val API_ROOT = "https://api.wearematchplay.com/v2/"
        const val PATH_URL_LOGIN = "auth/login/"
        const val PATH_URL_MATCHES = "matches/"

        object Matches {
            const val DEFAULT_PAGE_SIZE = 15
        }
    }

    object Preferences {
        const val KEY_CURRENT_USER_TOKEN: String = "key_current_user_token"
        const val DEFAULT_KEY_CURRENT_USER_TOKEN: String = ""
    }

}
