package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TimeCounting
{
    private TimeCache hashMap;
    private Logger logger = LoggerFactory.getLogger(TimeCounting.class);
    @Autowired
    public void setHashMap(TimeCache hashMap){
        this.hashMap = hashMap;
    }

    public String calc(DataClass gr) {
        int rest;
        String time;
        if(hashMap.isContain(gr)){
            time = hashMap.getParam(gr);
            logger.info("Get hashMap");
        }
        else {

            Integer dist_int = Integer.valueOf(gr.getDistance());
            Integer speed_int = Integer.valueOf(gr.getSpeed());

            Integer result = dist_int/speed_int;
            time = result.toString();
            hashMap.addToMap(gr,time);
            logger.info("Add to hashMap");
        }
        return time;
    }

    public int calcInt(DataClass gr) {
        int rest = 0;
        String time;

        Integer dist_int = Integer.valueOf(gr.getDistance());
        Integer speed_int = Integer.valueOf(gr.getSpeed());

        Integer result = dist_int/speed_int;
        time = result.toString();
        hashMap.addToMap(gr,time);
        logger.info("Add to hashMap");
        return result;
    }

    public long calcSize(List<String> resList){
        long size = 0;
        if(!resList.isEmpty()){
            size = resList
                    .stream()
                    .count();
        }
        return size;
    }

    public String mostRecurring(List<String> resList){
        int size = 0;
        int max =0;
        String word ="Monday";
        HashSet<String> res = new HashSet<>();
        res.addAll(resList);
        for(String a :res) {
            size = Collections.frequency(resList, a);
            if(size > max){
                max = size;
                word = a;
            }
        }
        return word;
    }

    public int findMax(List<Integer> resList){
        int max = 0;
        if(!resList.isEmpty()){
            max = resList
                    .stream()
                    .mapToInt(Integer::intValue)
                    .max()
                    .getAsInt();
        }
        return max;
    }
    public int findMin(List<Integer> resList){
        int min = 0;
        if(!resList.isEmpty()){
            min = resList
                    .stream()
                    .mapToInt(Integer::intValue)
                    .min()
                    .getAsInt();
        }
        return min;
    }

}
