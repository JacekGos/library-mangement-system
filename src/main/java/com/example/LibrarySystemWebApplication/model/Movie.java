package com.example.LibrarySystemWebApplication.model;

public class Movie extends LibraryElement {

    private int durationTime;

    public Movie(int libraryElementId, byte typeId, String title, int sortId, int statusId, int durationTime) {
        super(libraryElementId, typeId, title, sortId, statusId);
        this.durationTime = durationTime;
    }
    
    public int getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }

}
