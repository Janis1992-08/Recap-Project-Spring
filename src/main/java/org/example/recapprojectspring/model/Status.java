package org.example.recapprojectspring.model;


public enum Status {
    OPEN("Open"),
    IN_PROGRESS("In Progress"),
    DONE("Done");

    private final String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    public String getStatus() {
        return this.statusName;
    }

}
