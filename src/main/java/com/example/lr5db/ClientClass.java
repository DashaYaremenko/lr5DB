package com.example.lr5db;

public class ClientClass {
    private String Id;
    private String LastName;
    private String FirstName;
    private String TypeDoc;
    public ClientClass(String id, String lastName, String firstName, String typeDoc) {
        this.Id = id;
        this.LastName = lastName;
        this.FirstName = firstName;
        this.TypeDoc = typeDoc;
    }
    public String getId() {
        return Id;
    }
    public void setId(String id) {
        this.Id = id;
    }
    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) {
        this.LastName = lastName;
    }
    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }
    public String getTypeDoc() {
        return TypeDoc;
    }
    public void setTypeDoc(String typeDoc) {
        this.TypeDoc = typeDoc;
    }
}
