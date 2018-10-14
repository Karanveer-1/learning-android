package ca.bcit.ass1.khanna_manandhar;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Country implements Serializable {
    private String TAG = MainActivity.class.getSimpleName();
    private String name;
    private String capital;
    private String region;
    private String flag;
    private long population;
    private double area;
    private ArrayList<String> borders;

    public void setFlag(String flag){
        this.flag = flag;
    }
    public String getFlag(){
        return this.flag;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setCapital(String capital){
        this.capital = capital;
    }
    public String getCapital(){
        return this.capital;
    }
    public void setRegion(String region){
        this.region = region;
    }
    public String getRegion(){
        return this.region;
    }
    public void setPopulation(long population){
        this.population = population;
    }
    public long getPopulation(){
        return this.population;
    }
    public void setArea(double area){
        this.area = area;
    }
    public double getArea(){
        return this.area;
    }
    public void setBorders(ArrayList<String> borders){
        this.borders = borders;
    }
    public String getBorders(){
        String str = "";
        for (int i = 0; i < borders.size(); i++) {
            str += borders.get(i) + ", ";
        }
        return str;
    }
    public String toString() {
        return getName();
    }
}
