package com.santos.springcore;

import org.springframework.stereotype.Component;

@Component
public class BasketballCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "I am Basketball Coach.";
    }
}
