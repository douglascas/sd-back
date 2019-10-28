package com.car.rental.util;

public class Email {

    /**
     * Validates if the email you entered is valid.
     *
     * @param email
     * @return {@link boolean}
     */
    public static boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

}
