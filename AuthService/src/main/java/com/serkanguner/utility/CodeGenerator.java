package com.serkanguner.utility;

import java.util.UUID;

public class CodeGenerator {
    //Activation code : 5 haneli bir kod olusturup onu kullaniciya donmek istiyoruz. Rastgele bir UUID uretelim ve onun bas harflarinden 5 haneli bor kod cikaran bir metod yazalim
    //Ornegin: 23c8b54e-28d7-a5c8-b6f3-4b3870ed12b9 == 22ab4


    public static String generateActivationCode() {
        String uuid = UUID.randomUUID().toString();

        String[] split = uuid.split("-");

        StringBuilder activationCodeBuilder = new StringBuilder();

        for (String s : split) {
            activationCodeBuilder.append(s.charAt(0));
        }
        return activationCodeBuilder.toString();

    }

    public static String generateNewPasswordCode(){
        String uuid = UUID.randomUUID().toString();

        String[] split = uuid.split("-");

        StringBuilder activationCodeBuilder = new StringBuilder();

        for (String s : split) {
            activationCodeBuilder.append(s.charAt(0));
        }
        return activationCodeBuilder.toString();
    }





}
