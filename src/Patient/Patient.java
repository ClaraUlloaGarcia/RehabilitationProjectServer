package Patient;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria Burbano
 */
public class Patient {

    public int ip;
    public int id;
    public int password;
    public String name;
    public int age;
    public String gender;
    public double weight;
    public double height;
    public int flex_ang;
    public int turn_ang;

    public PrintWriter pw;
    public BufferedReader br;

    public Patient(int id, String name, int age, String gender, double weight,
            double height, int flex_ang, int turn_ang) throws Exception {

        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.flex_ang = flex_ang;
        this.turn_ang = turn_ang;

        Socket socket = new Socket("localhost" /*ip*/, 9000);
        //OutputStream outputStream = socket.getOutputStream();
        pw = new PrintWriter(socket.getOutputStream(), true);
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String logPatient() throws IOException { //EXPLICAR LO QUE DEVUELVE

        System.out.println("Logging new Patient");
        System.out.println("Sending data");
        String connect = "I want to stablish connection";
        //Socket socket = new Socket("localhost" /*ip*/, 9000);
        //OutputStream outputStream = socket.getOutputStream();

        //pw = new PrintWriter(socket.getOutputStream(), true);
        //br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s;

        //connection with the server
        pw.println(connect);

        //id
        s = "" + id;
        pw.println(s);

        //password
        pw.println(password);

        String server_answer = br.readLine();
        if (server_answer.equals("OK")) {
            return "Everything went good";
        } else {
            return "Something went wrong";
        }

    }

    public void clientToServer() throws IOException {

        System.out.println("Creamos un paciente");
        System.out.println("Sending data");
        String connect = "I want to stablish connection";
        Socket socket = new Socket("localhost", 9000);
        OutputStream outputStream = socket.getOutputStream();

        pw = new PrintWriter(socket.getOutputStream(), true);
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        byte byteBuffer[];
        String s;

        //connection with the server
        byteBuffer = connect.getBytes();
        outputStream.write(byteBuffer);

        /*
            //ip
            s = "" + ip;
            byteBuffer = s.getBytes();
            outputStream.write(byteBuffer);*/
        //id
        s = "" + id;
        byteBuffer = s.getBytes();
        outputStream.write(byteBuffer);

        //name
        byteBuffer = name.getBytes();
        outputStream.write(byteBuffer);

        //age
        s = "" + age;
        byteBuffer = s.getBytes();
        outputStream.write(byteBuffer);

        //flex_ang
        s = "" + flex_ang;
        byteBuffer = s.getBytes();
        outputStream.write(byteBuffer);

        //turn_ang
        s = "" + turn_ang;
        byteBuffer = s.getBytes();
        outputStream.write(byteBuffer);

        //gender
        s = "" + gender;
        byteBuffer = s.getBytes();
        outputStream.write(byteBuffer);

        //weight
        s = "" + weight;
        byteBuffer = s.getBytes();
        outputStream.write(byteBuffer);

        //height
        s = "" + height;
        byteBuffer = s.getBytes();
        outputStream.write(byteBuffer);

        releaseResources(outputStream, br, socket);
        System.exit(0);

    }

    private static void releaseResources(OutputStream outputStream,
            BufferedReader br, Socket socket) {
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            outputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getFlexang() {
        return flex_ang;
    }

    public void setFlexang() {
        this.flex_ang = flex_ang;
    }

    public int getTurnang() {
        return turn_ang;
    }

    public void setTurnang() {
        this.turn_ang = turn_ang;
    }
    
    @Override
    public String toString() {
        return "Patient id=" + id + ", name=" + name + ", age=" + age + ", weight="
                + weight + ", height=" + height + ", gender="
                + gender + ", flexing angle=" + flex_ang + ", turning angle= " + turn_ang;
    }

}
