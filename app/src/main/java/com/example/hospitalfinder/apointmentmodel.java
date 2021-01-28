package com.example.hospitalfinder;

public class apointmentmodel {

    private String name;
    private String blood;
    private String age;

    private apointmentmodel() {}
    private apointmentmodel(String name, String blood, String age)
    {
        this.name = name;
        this.blood = blood;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
