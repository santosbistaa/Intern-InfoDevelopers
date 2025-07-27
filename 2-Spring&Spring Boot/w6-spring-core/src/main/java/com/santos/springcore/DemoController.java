package com.santos.springcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // define a private field for the dependency
    private Coach myCoach;


    // define a constructor for dependency injection
//    @Autowired
//    public DemoController(Coach theCoach){
//        myCoach = theCoach;
//    }


    // Setter Injection
//    @Autowired
//    public void setMyCoach(Coach myCoach) {
//        this.myCoach = myCoach;
//    }


    // Field Injection
//    @Autowired
//    private Coach myCoach;


    // Use of Qualifier to be specific for coach implementation using Constructor injection
//    @Autowired
//    public DemoController( @Qualifier("footballCoach") Coach myCoach) {
//        this.myCoach = myCoach;
//    }


    // Use of Qualifier to be specific for the Basketball Coach using Setter injection
//    @Autowired
//    public void setMyCoach(@Qualifier("basketballCoach") Coach myCoach) {
//        this.myCoach = myCoach;
//    }

    /*  Use of @Primary to implement the specific coach.
        We cannot assign multiple coach as @Primary.
        Here we are using @Primary for Running Coach.
        @Qualifier is recommended rather than @Primary because of its use on multiple implementation
    */
    @Autowired
    public DemoController(Coach myCoach) {
        this.myCoach = myCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
