package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TimeCache {
    private final Map<DataClass,String> hashMap = new HashMap<>();
    public boolean isContain(DataClass key) {
        return hashMap.containsKey(key);
    }
    public void addToMap(DataClass key, String value){
        hashMap.put(key,value);
    }
    public String getParam(DataClass key){
        return hashMap.get(key);
    }

}
