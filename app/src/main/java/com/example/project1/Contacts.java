package com.example.project1;

public class Contacts {

    private String name,lastName, urlImage,mail;
    private int age,id;

    public Contacts(String name, String lastName, String urlImage, int age,int id,String mail) {
        this.name = name;
        this.lastName = lastName;
        this.urlImage = urlImage;
        this.age = age;
        this.id = id;
        this.mail = mail;
    }



    public Contacts(String name, String lastName, int age, String mail) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public String getIdAsString(){
        return String.valueOf(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
