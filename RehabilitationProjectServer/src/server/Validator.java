package server;

import java.util.List;

public class Validator {
    
    //Validate 
    public static boolean validateSingleWord(String word) { 
        if(word == null || word.length() == 0) {
            return false;
        }
        for(int i = 0; i < word.length(); i++) {
            char character = word.charAt(i); //Obtain the character from position i
            //It can only be a letter or number, no symbols or blank space
            if(!Character.isAlphabetic(character) && !Character.isDigit(character)){ 
                return false;
            }
        }
        
        return true;
    }
    
    public static boolean validateGender(String gender){
        return "male".equals(gender) || "female".equals(gender);
    }
    
    //Method to verify that the user does not exist in the users file when we have a registration
    public static boolean validateNewUser(String user) {
        //1. Verify correct pattern, without symbols or blank spaces
        if(user == null || user.length() == 0){
            return false;
        }
        
        if(!Validator.validateSingleWord(user)) {
            return false;
        }
        //2. Is there an existing user with the same name? 
        FileManager fileManager = new FileManager();
        List[] credentials = fileManager.getUserPassword(); //carga fichero con usuarios ya existentes
        if(credentials == null) { 
            return true;
        }
        if(credentials.length == 0) { //No hay ningún paciente registrado?
            return true;
        }
        //In credentials[0] we have a list with all userNames from the file with all names and passwords
        List<String> users = credentials[0];
        for(String currentUser: users) {
            if(user.equals(currentUser)) {
                return false;
            }
        }
        return true;
    }
    
    //Validate age in order to not accept negative values
    public static boolean validateAge(int age) {
        return age >= 0 && age < 150;
    }
    
}
