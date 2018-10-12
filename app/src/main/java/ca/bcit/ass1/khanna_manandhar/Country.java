package ca.bcit.ass1.khanna_manandhar;

import java.io.Serializable;
import java.util.List;

public class Country implements Serializable {
    private String name;
    private String capital;
    private String region;
    private String flag;
    private long population;
    private double area;
    private List<String> borders;

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
    public void setBorders(List<String> borders){
        this.borders = borders;
    }
    public List<String> getBorders(){
        return this.borders;
    }
    public String toString() {
        return getName();
    }
}
