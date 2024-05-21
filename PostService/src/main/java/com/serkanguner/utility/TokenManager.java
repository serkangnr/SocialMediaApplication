/*
package com.serkanguner.utility;

import org.springframework.stereotype.Component;

@Component
public class TokenManager {
    //1. Token uretmeli
    public String createToken(Long id){
        return "authtoken:"+id;
    }

    //2. Urettigi tokenden bilgi cikarimi yapmali
    public Long getIdFromToken(String token){
        String[] split = token.split("authtoken:");
        return Long.parseLong(split[1]);
    }
}
*/
