package com.example.LibrarySystemWebApplication.web;

public interface dataInputHelper {

    static boolean checkEmpty(String inputString) {

        if (inputString.isBlank()) {
            return true;
        }
        return false;
    }

    static boolean checkLength(String inputString) {

        if (inputString.length() > 30) {
            return true;
        }
        return false;
    }

    static boolean isFirstCharEmpty(String inputString) {

        char[] stringArray = inputString.toCharArray();

        if (stringArray[0] == ' ') {
            return true;
        }
        return false;
    }

}
