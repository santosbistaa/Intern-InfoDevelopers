package com.santos.springcore;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class RunningCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "I am Running Coach.";
    }
}
