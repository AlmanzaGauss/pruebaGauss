/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebadbdevel;
import Acceso.Consultas;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author falag
 */
public class PruebaDBDevel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                Consultas cons= new Consultas();
         
                try {
            // TODO code application logic here
            System.out.println("Servidor");
            DatagramSocket miSocket= new DatagramSocket(5005);
            byte[] buffer = new byte[1824];
            int addip=0;
            while(true)
            {
              DatagramPacket peticion= new DatagramPacket(buffer, buffer.length);
              miSocket.receive(peticion);
              
              String ip=peticion.getAddress().getHostAddress();
              int puerto=peticion.getPort();
              String addr=new String(peticion.getData(),0,peticion.getLength());
             
              String query="UPDATE BoxInventory set BoxInventory_IPAdd_Wan='"+ip+"',Port_IppAdd_Wan="+puerto+" WHERE BoxInventory_MacAdd='"+addr+"'";
              System.out.println("QUERY:"+query);
              
              addip=cons.UpdateRows(query);
              System.out.println(addip);
   
              segundointentoth.HilosServer hilo= new segundointentoth.HilosServer(miSocket, peticion);
              hilo.start();
            }
        } catch (SocketException ex) {
            Logger.getLogger(PruebaDBDevel.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(PruebaDBDevel.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO code application logic here
    }
    
}
