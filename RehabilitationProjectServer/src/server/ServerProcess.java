package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerProcess extends Thread {

    private final Socket socket;
    public InputStream inputStream;
    public OutputStream outputStream;

    private final int CODE_ERROR = 1;
    private final int CODE_OK = 0;

    public String user = null;

    public ServerProcess(Socket socket) {
        this.socket = socket;
    }
    
    public void run() {
        SequenceManager sequenceManager = new SequenceManager();

        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            while (true) {
                int s;
                s = inputStream.read(); //Reads one character
                if (sequenceManager.addChar((char) s)) { //If it is true (received sequence) a command is expected
                    s = inputStream.read();
                    if (s == 'L') {
                        processLogin();
                    } else if (s == 'R') {
                        processRegister();
                    } else if (s == 'D') {
                        processData();
                    } else {
                        sendError();
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        releaseResources();
        System.out.println("Connection finished :)");

    }

    // Auxiliary methods
    private String readLine() {
        int s;
        String line = "";
        try {
            s = inputStream.read();
            while (s != '\n') {
                if (s == '\r') {
                    s = inputStream.read();
                    continue;
                }
                line += new Character((char) s);
                //It goes letter by letter
                s = inputStream.read();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return line;
    }

    private void releaseResources() {
        try {
            inputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerProcess.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerProcess.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendError() {
        try {
            outputStream.write(CODE_ERROR);// Error
        } catch (IOException ex) {
            Logger.getLogger(ServerProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendOk() {
        try {
            outputStream.write(CODE_OK); //All went well
        } catch (IOException ex) {
            Logger.getLogger(ServerProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Login Methods
    private void processLogin() {

        String userName = readLine();
        userName = userName.toLowerCase(); //all userName letters are small ones
        if (!Validator.validateSingleWord(userName)) {
            sendError();
            return;
        }
        String password = readLine();
        boolean loggedIn = login(userName, password);
        if (loggedIn) {
            user = userName;
            sendOk();
        } else {
            sendError();
        }
    }

    private boolean login(String userName, String password) {
        FileManager fileManager = new FileManager();
        List[] credentials = fileManager.getUserPassword(); //get users and passwords already saved from the file
        if (credentials == null) {
            return false;
        }
        if (credentials.length != 2) {
            return false;
        }
        List<String> users = credentials[0];
        List<String> passwords = credentials[1];

        if (users.size() != passwords.size()) {
            System.out.println("Number of users does not match with number of passwords");
            return false;
        }
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(userName) && passwords.get(i).equals(password)) {
                return true;
            }
        }
        return false;
    }

    //Registration Methods
    private void processRegister() {
        String userName = readLine();
        userName = userName.toLowerCase();
        if (!Validator.validateNewUser(userName)) {
            sendError();
            return;
        }
        System.out.println("User read");

        String password = readLine();
        //The password will differenciate lower than upper letters
        if (!Validator.validateSingleWord(password)) {
            sendError();
            return;
        }
        System.out.println("Password read");

        String gender = readLine();
        if (!Validator.validateGender(gender)) {
            sendError();
            return;
        }
        System.out.println("Gender read");

        String ageLine = readLine();
        int age;
        try {
            age = Integer.parseInt(ageLine);
            if (!Validator.validateAge(age)) {
                sendError();
            }
        } catch (NumberFormatException e) {
            sendError();
            return;
        }
        System.out.println("Age read");
        
        String weightLine = readLine();
        double weight;
        try {
            weight = Double.parseDouble(weightLine);
        } catch (NumberFormatException e) {
            sendError();
            return;
        }
        System.out.println("Weight read");
        
        String heightLine = readLine();
        double height;
        try {
            height = Double.parseDouble(heightLine);
        } catch (NumberFormatException e) {
            sendError();
            return;
        }
        System.out.println("Height read");

        boolean register = register(userName, password, gender, age, weight, height);
        if (register) {
            user = userName;
            sendOk();
        } else {
            sendError();
            System.out.println("Error sent");
        }
    }

    private boolean register(String userName, String password, String gender, int age, double weight, double height) {
        FileManager fileManager = new FileManager();

        return fileManager.saveFixedVariables(userName, gender, age,
                weight, height) && fileManager.saveUserPassword(userName, password); //Save the registration in the file
    }

    // Variable data methods
    private void processData() {
        // Already logged in?
        if (user == null) {
            sendError();
            return;
        }
        String sizeString = readLine(); //How many values come from the Bitalino
        int size = Integer.parseInt(sizeString);
        System.out.println("Recibo una linea con " + size + " elementos");
        List<String> bitalinoData = new ArrayList(); //String list as data is sent by text
        for (int i = 0; i < size; i++) {
            String line = readLine(); //Read one line
            System.out.println("Data received from client " + line);
            bitalinoData.add(line);//Addition to the list
        }
        String flex_ang = readLine();
        String turn_ang = readLine();
                
        List<Integer> bitalino = new ArrayList();
        for (String data : bitalinoData) {
            try {
                int newData = Integer.parseInt(data);
                bitalino.add(newData);
            }catch (NumberFormatException ex) {
                sendError();
                return;
            }
        }
        
        double validateFlex;
        double validateTurn;
        try {
            validateFlex = Double.parseDouble(flex_ang);
            validateTurn = Double.parseDouble(turn_ang);
        } catch (NumberFormatException ex) {
            sendError();
            return;
        }

        FileManager fileManager = new FileManager();
        boolean saved = fileManager.saveChangingVariables(user, validateFlex, validateTurn, bitalino); //Save in each patient file, its variable data
        if (saved) {
            //Exercises server sends to client
            WorkoutManager workoutManager = new WorkoutManager();
            String workoutList [] = workoutManager.nextWorkout(); 
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.append(workoutList.length+ "\n");
            for (String workout : workoutList){
                printWriter.append(workout + "\n");
            }
            printWriter.flush();
            sendOk();
        }else {
            sendError();
        }
    }
}
