package utils;

import java.util.regex.Pattern;

public class validatorUtils {

    // funciones estaticas de validacion de parametros

    public static boolean validateNumber(String id) {
        return id.matches("[0-9]+");
    }

    public static boolean validateString(String name) {
        if (name.length() == 0)
            return false;
        return true;
    }

    public static boolean validateEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        System.out.println(email);
        System.out.println(Pattern.matches(regex, email));
        return Pattern.matches(regex, email);
    }

}
