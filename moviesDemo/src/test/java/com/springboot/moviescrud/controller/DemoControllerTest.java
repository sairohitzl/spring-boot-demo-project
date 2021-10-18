package com.springboot.moviescrud.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoControllerTest {

    @Test
    void showHome() {
        DemoController demoController = new DemoController(); //Arrange
        String response = demoController.showHome();          //Act
        assertEquals("redirect:/login-page",response); //Assert
    }
}