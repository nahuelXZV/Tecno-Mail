package utils;

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
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

}
