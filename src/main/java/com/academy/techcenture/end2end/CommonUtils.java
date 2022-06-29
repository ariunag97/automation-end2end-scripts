package com.academy.techcenture.end2end;

import com.github.javafaker.Faker;

public class CommonUtils {

    public static String randomEmail(){

        Faker faker = new Faker();
        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();
        String[] domain = {"gmail", "yahoo","icloud","hotmail"};

        String email = lastName+"."+firstName+"@"+ domain[(int)(Math.random()*(4))]+".com";

        return email.toLowerCase();
    }

    public static void main(String[] args) {


    }
}
