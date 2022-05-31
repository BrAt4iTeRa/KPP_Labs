package com.example.demo;

public class DataClass {
    private final String distance;
    private final String speed;

    public DataClass(String distance, String speed){
        this.distance = distance;
        this.speed = speed;
    }

    public String getSpeed() {
        return speed;
    }

    public String getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null) return false;
        if(getClass()!=o.getClass()) return false;
        DataClass dataClass = (DataClass) o;
        return ((this.distance.equals(((DataClass) o).distance) && this.speed == ((DataClass) o).speed));
    }
    @Override
    public int hashCode(){
        int res = 1;
        res = 31*res + distance.hashCode();
        res = 31*res + speed.hashCode();
        return res;
    }
}
