package com.github.mpnsk.serverless_crud.funqy;

import io.quarkus.funqy.Funq;

public class GreetingFunction {

    @Funq
    public String myFunqyGreeting(String friend) {
        return "Hello " + friend;
    }
}
