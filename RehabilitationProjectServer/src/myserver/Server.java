/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myserver;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ifndz
 */
public class Server {

    public byte[] buffer;
    public byte[] data;
    
    public String s;
    public Socket socket;
    public ServerSocket serverSocket;
    public InputStream inputStream;
    
    public static void main(String args[]) {}
        
        

/*        
        DatagramSocket socket = null;

        try {

            socket = new DatagramSocket(9000);
            buffer = new byte[1024];

            while (true) {
                DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);
                socket.receive(datagram);
                InetAddress originHost = datagram.getAddress();
                int destinationPort = datagram.getPort();
                byte[] data = datagram.getData();
                String cadena = new String(data, 0, datagram.getLength());
                System.out.println("Welcome to the server. Sent from"
                        + originHost + " through the port "
                        + destinationPort + " the message: " + cadena);

                System.out.println("Socket Local IP: " + socket.getLocalAddress());
                System.out.println("Socket Local Port: " + socket.getLocalPort());
                System.out.println("Socket Remote IP: " + originHost);
                System.out.println("Socket Remote Port: " + destinationPort);
                
                //RESPUESTA SERVER -> CLIENT
                String string = "Recibido";
                byte[] message = string.getBytes();
                DatagramPacket response = new DatagramPacket(message, message.length, originHost, destinationPort);
                socket.send(response);

            }
        } catch (SocketException ex) {
            Logger.getLogger(Myserver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Myserver.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
*/
        
        public void loggingUser() throws IOException{
        
        
            
        }
        
        public void registerUser() throws IOException{ //nombre y contraseña
            
            serverSocket = new ServerSocket(9000);
            socket = serverSocket.accept();
            
            System.out.println("Print local port: "+socket.getLocalPort());
            System.out.println("Print remote port: "+socket.getPort());
            
            inputStream = socket.getInputStream();
            
            try{
                while(true){
                   s = Integer.toString(inputStream.read());
                   for(int i=0; i<s.length(); i++){
                    
                       buffer[i] = Byte.valueOf(s);
                    
                    if(buffer[i] == -1){
                       System.out.println("Goodbye");
                       releaseResourcesServer(inputStream, socket, serverSocket);
                       break;
                       }
                    }
                }
            }catch(IOException ex){
                    ex.printStackTrace();
                    }
            
            
            
        }
    
        public static void receiveParameter() throws IOException{
        }
        
        public static void storeData() throws IOException{
        }
        
        
        /*
        private static void releaseResourcesClient(InputStream inputStream, Socket socket) {

        try {
            inputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(Myserver.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Myserver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // try {
        //     socketServidor.close();
        // } catch (IOException ex) {
        //     Logger.getLogger(ServerReceiveCharactersViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
        // }
    }

    */private static void releaseResourcesServer(InputStream inputStream, Socket socket, ServerSocket socketServidor) {

        try {
            inputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            socketServidor.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
