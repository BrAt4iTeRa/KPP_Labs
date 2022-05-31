package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.counter.RequestCounterThread;
import com.example.demo.exception.DataRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class TimeController {
    private TimeCounting states;
    private RequestCounterThread counterThread;
    private Logger logger = LoggerFactory.getLogger(TimeController.class);

    @Autowired
    public void setter(TimeCounting newService){
        this.states = newService;
    }

    private static final String template = "Distance \" %s \" divide on speed \"%s\" is %s";
    private static final String template1 = "Distance %s divide on speed %s time is %s\n";


    @GetMapping("/time")
    public String get(@RequestParam(value = "distance") String distance,
                      @RequestParam(value = "speed") String speed) throws DataRequestException{
        counterThread = new RequestCounterThread();
        DataClass gr = new DataClass(distance, speed);
        return String.format(template,gr.getDistance(),gr.getSpeed(),states.calc(gr));
    }

    @PostMapping("/time")
    public ResponseEntity<?> post1(@RequestBody List<DataClass> list){
        List<String> res = new ArrayList<>();
        List<Integer> resInt = new ArrayList<>();
        List<String> res_output = new ArrayList<>();
        list.forEach((element)->{
            try {
            res_output.add(String.format(template1,element.getDistance(),element.getSpeed(),states.calc(element)));
            resInt.add(states.calcInt(element));
            res.add(states.calc(element));
            } catch (Throwable e) {
                logger.info("Argument is null (not fact)");
            }
        });

        long sizeOfRequest = states.calcSize(res);
        String offten = states.mostRecurring(res);
        int MAX = states.findMax(resInt);
        int MIN = states.findMin(resInt);
        return new ResponseEntity<>(res_output + "\nSize = " + sizeOfRequest + "\nMax = "+MAX + "\nMin = "+ MIN+"\nOfften = "+ offten, HttpStatus.OK);
    }
}

