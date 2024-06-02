package com.serkanguner.utility.lowercase;

import com.serkanguner.dto.request.RegisterRequestDto;
import com.serkanguner.entity.Auth;

import java.lang.reflect.Field;

public class LowercaseProcessor {

    public static void process(Auth auth) {
        Field[] fields = auth.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Lowercase.class)) {
                if (field.getType().equals(String.class)) {
                    field.setAccessible(true);
                    try {
                        String value = (String) field.get(auth.getUsername());
                        if (value != null) {
                            field.set(auth, value.toLowerCase());
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
