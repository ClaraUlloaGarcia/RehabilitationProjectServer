
import Keep.FileManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ClaraU
 */
class main {
    
public static void main(String[] args){
    FileManager fm = new FileManager();
    fm.UserPassword("user1", "Password1");
    fm.UserPassword("user2", "Password2");
    
    fm.FixedVariables(0, "Mery", 21, false, 55.4, 1.67);
    fm.FixedVariables(1, "Manola", 27, false, 75.4, 1.8);
    
    fm.ChangingVariables(27, 50, );
}
}