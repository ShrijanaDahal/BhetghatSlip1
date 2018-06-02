package com.samansar.bhetghatslip.Model;

public class Model_After_Approve {
    String name,subject,description,contact,address
            ,email,starting_time, ending_time, meeting_date;

    public Model_After_Approve(String name, String subject, String description, String contact, String address, String email, String starting_time, String ending_time, String meeting_date) {
        this.name = name;
        this.subject = subject;
        this.description = description;
        this.contact = contact;
        this.address = address;
        this.email = email;
        this.starting_time = starting_time;
        this.ending_time = ending_time;
        this.meeting_date = meeting_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStarting_time() {
        return starting_time;
    }

    public void setStarting_time(String starting_time) {
        this.starting_time = starting_time;
    }

    public String getEnding_time() {
        return ending_time;
    }

    public void setEnding_time(String ending_time) {
        this.ending_time = ending_time;
    }

    public String getMeeting_date() {
        return meeting_date;
    }

    public void setMeeting_date(String meeting_date) {
        this.meeting_date = meeting_date;
    }
}
