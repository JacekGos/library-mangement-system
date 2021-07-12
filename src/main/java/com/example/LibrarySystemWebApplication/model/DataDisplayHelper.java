package com.example.LibrarySystemWebApplication.model;

public interface DataDisplayHelper {

    static String convertStatusToString(int statusId) {

        String statusString = null;

        switch (statusId) {
            case 1:
                statusString = "Dostępna";
                break;
            case 2:
                statusString = "Oczekuje";
                break;
            case 3:
                statusString = "Wypożyczona";
                break;
            case 4:
                statusString = "Zatwierdzony";
                break;
            case 5:
                statusString = "Odrzucony";
                break;
            case 6:
                statusString = "Zakończony";
                break;
            default:
                statusString = "Błąd";
                break;
        }

        return statusString;
    }

    static String convertsSortToString(int sortId) {

        String sortString = null;

        switch (sortId) {
            case 1:
                sortString = "Historyczne";
                break;
            case 2:
                sortString = "Fantastyka";
                break;
            case 3:
                sortString = "Kryminał";
                break;
            case 4:
                sortString = "Edukacja";
                break;
            case 5:
                sortString = "Technologie";
                break;
            default:
                sortString = "Błąd";
                break;
        }

        return sortString;
    }

    static String convertTypeToString(int typeId) {

        String typeString = null;

        switch (typeId) {
            case 1:
                typeString = "Książka";
                break;
            case 2:
                typeString = "Film";
                break;
            case 3:
                typeString = "Czasopismo";
                break;
            default:
                typeString = "Błąd";
                break;
        }

        return typeString;
    }


}
