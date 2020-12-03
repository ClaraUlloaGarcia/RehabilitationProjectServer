
import BITalino.BITalinoManager;
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
    
    //GUARDAR
    //Fichero 1 guardar usuario y contrasnia
    FileManager fm = new FileManager();
    fm.SaveUserPassword("user1", "Password1");
    fm.SaveUserPassword("user2", "Password2");
    //Fichero 2 guarda las variables fijas del cliente al registrarse
    fm.SaveFixedVariables(0, "Mery", 21, false, 55.4, 1.67);
    fm.SaveFixedVariables(1, "Manola", 27, false, 75.4, 1.8);
    //Fichero 3 almacena las variabless que cambian cada semana
    //BITalinoManager b = new BITalinoManager();
    //fm.SaveChangingVariables(27, 50, b.connectReadBitalino("98:D3:31:FD:3B:92"));
    
    //CARGAR
    fm.getUserPassword();
    fm.getFixedVariables();
    fm.getChangingVariables();
}
}