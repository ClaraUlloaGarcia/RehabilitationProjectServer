package BITalino;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import javax.bluetooth.RemoteDevice;


import java.util.logging.Level;
import java.util.logging.Logger;

public class BitalinoDemo {

    public static Frame[] frame;

    public static void main(String[] args) {

        BITalino bitalino = null;
        List<Integer> coso = new ArrayList<Integer>();
        try {
            bitalino = new BITalino();
            // Code to find Devices
            //Only works on some OS
            //Vector<RemoteDevice> devices = bitalino.findDevices();
            //System.out.println(devices);

            //You need TO CHANGE THE MAC ADDRESS
            //You should have the MAC ADDRESS in a sticker in the Bitalino
            String macAddress = "98:D3:31:FD:3B:92";
            
            //Sampling rate, should be 10, 100 or 1000
            int SamplingRate = 100;
            bitalino.open(macAddress, SamplingRate);

            // Start acquisition on analog channels A2 and A6
            // For example, If you want A1, A3 and A4 you should use {0,2,3}
            int[] channelsToAcquire = {0}; //We only want Electromyogram channel, that is A1
            bitalino.start(channelsToAcquire);

            //Read in total 10000000 times
            for (int j = 0; j < 10000; j++) { //Record 10 seconds

                //Each time read a block of 10 samples 
                int block_size=10;
                frame = bitalino.read(block_size);

                System.out.println("size block: " + frame.length);

                //Print the samples
                for (int i = 0; i < frame.length; i++) {
                    //System.out.println((j * block_size + i) + " seq: " + frame[i].seq + " "
                            //+ frame[i].analog[0] + " "
                            //+ frame[i].analog[1] + " "
                    //  + frame[i].analog[2] + " "
                    //  + frame[i].analog[3] + " "
                    //  + frame[i].analog[4] + " "
                    //  + frame[i].analog[5]
                   // );
                coso.add(frame[i].analog[0]); //we are adding the EMG to the arrayList
                }
            }
            //stop acquisition
           //we create an iterator to print the EMG values each second 
            Iterator it= coso.iterator();
            while (it.hasNext()){
            System.out.println(it.next());
            }
            
            bitalino.stop();
        } catch (BITalinoException ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                //close bluetooth connection
                if (bitalino != null) {
                    bitalino.close();
                }
            } catch (BITalinoException ex) {
                Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
