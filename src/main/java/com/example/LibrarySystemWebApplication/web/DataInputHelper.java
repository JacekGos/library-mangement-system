package com.example.LibrarySystemWebApplication.web;

import java.util.List;

public interface DataInputHelper {

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

    static boolean isConvertableToInt(String stringData) {

        try {
            Integer.parseInt(stringData);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    static boolean isConvertableToDouble(String stringData) {

        try {
            Double.parseDouble(stringData);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}
