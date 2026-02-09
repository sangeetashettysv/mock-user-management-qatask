package com.cgm.frontend.stepDefinitions;

import com.cgm.frontend.utils.DriverManager;
import io.cucumber.java.After;

public class Hooks {

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}