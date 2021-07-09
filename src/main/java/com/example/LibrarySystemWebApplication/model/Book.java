package com.example.LibrarySystemWebApplication.model;

public class Book extends LibraryElement {

    private int pagesNumber;

    public Book(int libraryElementId, byte typeId, String title, int sortId, int statusId, int pagesNumber) {
        super(libraryElementId, typeId, title, sortId, statusId);
        this.pagesNumber = pagesNumber;
    }

    public Book() {}

    public int getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }


}
