/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patient;

/**
 *
 * @author Maria Burbano
 */
public class Patient {
    public int id; 
    public String name;
    public int age;
    public boolean gender;
    public double weight;
    public double height;
    public int flex_ang;
    public int turn_ang;
    
    
    
    public Patient(int id,String name, int age, boolean gender, double weight, double height, int flex_ang, 
                                int turn_ang){
        this.id=id;
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.weight=weight;
        this.height=height;
        this.flex_ang= flex_ang;
        this.turn_ang= turn_ang;
    }
    public int getId(){
        return id;
    }
    public void setId(){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }
    public boolean getGender(){
        return gender;
    }
    public void setGender(boolean gender){
        this.gender=gender;
    }
    public double getWeight(){
        return weight;
    }
    public void setWeight(double weight){
        this.weight=weight;
    }
    public double getHeight(){
        return height;
    }
    public void setHeight(double height){
        this.height=height;
    }
    public int getFlexang(){
        return flex_ang;
    }
    public void setFlexang(){
        this.flex_ang= flex_ang;
    }
    public int getTurnang(){
        return turn_ang;
    }
    public void setTurnang(){
        this.turn_ang= turn_ang;
    }
    public String toString() {
		return "Patient id="+ id+ ", name=" + name + ", age="+ age + ", weight=" +
	            weight + ", height=" + height + ", gender="+
				gender + ", flexing angle=" + flex_ang+ ", turning angle= "+ turn_ang;
}
}
