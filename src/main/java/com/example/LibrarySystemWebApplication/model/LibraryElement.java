package com.example.LibrarySystemWebApplication.model;

public abstract class LibraryElement {

    private int libraryElementId;
    private byte typeId;
    private String title;
    private int sortId;
    private int statusId;

    public LibraryElement(int libraryElementId, byte typeId, String title, int sortId, int statusId) {

        this.libraryElementId = libraryElementId;
        this.typeId = typeId;
        this.title = title;
        this.sortId = sortId;
        this.statusId = statusId;

    }

    public int getLibraryElementId() {
        return libraryElementId;
    }

    public void setLibraryElementId(int libraryElementId) {
        this.libraryElementId = libraryElementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public byte getTypeId() {
        return typeId;
    }

    public void setTypeId(byte typeId) {
        this.typeId = typeId;
    }


}
