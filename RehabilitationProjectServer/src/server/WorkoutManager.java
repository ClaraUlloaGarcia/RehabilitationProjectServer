package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class WorkoutManager {
    
    private final int TOTAL_WORKOUT = 3;
    private String workoutList [] = new String [] {
        "Hide your good arm and with the hemiplegic arm, try to bend it and stretch it progressively.",
        "Open and close you hemiplegic hand. If you feel confident try to tight as much as possible and grab an object.", 
        "Interlace your hands and do circular movements. The good arm is going to guide your injured one.",
        "Put up your elbow at the hight of your face and extend your hand as high as possible.", 
        "Situate your elbow on a table and flex your arm. Try to reach 90º.",
        "Straight your arm and try to turn in the same height backwards until it makes an horizontal line with your body.",
        "Stand up and strench your arms up. Mantain this position during 20 seconds.",
        "Try to grab different 'light' weights from the floor like a book, a bottle full of water, a ball...",
        "Practice fine motor skills (precise small movements with your arm).",
        "Finger walk",
        "Snap your fingers",
        "Clap your hands"
    };
    
    //Exercises are selected randomly, without repetitions
    public String[] nextWorkout () {
        Random random = new Random();
        List<Integer> positions = new ArrayList();
        while(positions.size() < TOTAL_WORKOUT){
            int position = random.nextInt(workoutList.length);
            if(positions.indexOf(position) < 0){ //Check repetitions
                positions.add(position);
            }
        }
        String output [] = new String[TOTAL_WORKOUT];
        for(int i = 0; i < TOTAL_WORKOUT; i++){
            int position = positions.get(i);
            output[i] = workoutList[position]; 
        }
        return output;
    }
    
}
