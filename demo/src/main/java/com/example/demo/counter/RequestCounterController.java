package com.example.demo.counter;

import com.example.demo.counter.RequestCounter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestCounterController {
    @GetMapping("/counter")
    public String getCounter(){
        RequestCounter counter = new RequestCounter();
        return "counter = " + counter.getCount();
    }
}
