/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Opponents;

import Models.GameModel;
import Models.TurnDecision;
import java.io.*;
import java.net.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Enumeration;

import Models.Packet;

/**
 *
 * @author K. Schumi
 * @author D. Hodzic
 */
public class NetworkOpponent extends Observable implements IOpponent, Observer, Runnable {
    public final static int PORT = 4601;
    private static boolean running = false;
    private DatagramSocket socket;
    public InetAddress broadcast;
    
    public enum Commands {Hallo, GameRequest, AcceptRequest, RefuseRequest, TurnDecision}
    
    
    
    public NetworkOpponent()
    {
        try {
            socket = new DatagramSocket(PORT);
            socket.setBroadcast(true);
        } catch (SocketException ex) {
            System.out.println("Fehler: " + ex.getMessage());
        }
    }
    
    public void searchForOpponents() throws IOException
    {
        //thread to listen for gamerequest commands
        new Thread(this).start();
      
        //send hallo command
        // with port of gamerequest listener port as a parameter
        broadcastHallo();
        
        //thread to listen for hallo commands
        // add all host which respond to local list
       new Thread(this).start();
        
        
    }
    
    private byte[] serialize(Object object) throws IOException {
        try(ByteArrayOutputStream b = new ByteArrayOutputStream()){
            try(ObjectOutputStream o = new ObjectOutputStream(b)){
                o.writeObject(object);
            }
            catch(IOException e)
            {
                System.out.println(e.getMessage());
                
            }
            return b.toByteArray();
        }
    }
    
    public static Packet deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try(ByteArrayInputStream b = new ByteArrayInputStream(bytes)){
            try(ObjectInputStream o = new ObjectInputStream(b)){
                return ((Packet)o.readObject());
            }
        }
    }

    public void broadcastHallo() throws IOException
    {
        /*
        System.setProperty("java.net.preferIPv4Stack" , "true");
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            if (networkInterface.isLoopback())
                continue;    // Don't want to broadcast to the loopback interface
            networkInterface.getInterfaceAddresses().stream().forEach((interfaceAddress) -> {
                broadcast = interfaceAddress.getBroadcast();
            });
        }       */ 
        
        int[] hParam = new int []{1,PORT};
        Packet helloPacket = new Packet(Commands.Hallo,hParam);
        
        byte[] helloByte = serialize(helloPacket);
        
        
        DatagramPacket packet = new DatagramPacket(helloByte, helloByte.length, InetAddress.getByName("192.168.0.255"), PORT);
        socket.send(packet);
        
    }
    
    public void broadcastListener() throws IOException, ClassNotFoundException {
        


        
        byte[] buffer = new byte[1024];
        byte[] data;
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        
        data = new byte[packet.getLength()];
        System.arraycopy(packet.getData(), packet.getOffset(), data, 0, packet.getLength());
        
        Packet hPacket = deserialize(data);
        hPacket.setIp(packet.getAddress());
        setChanged();
        notifyObservers(hPacket);
    }
    
    public boolean sendGameRequest(String ip, int port) throws IOException
    {
        //send request to ip on port 4600
        //wait for response
        //Woher X, Y kriegen und in Packet übergeben?
        int[] grParam = new int []{2};
        Packet grPacket = new Packet(Commands.GameRequest,grParam);
        
        byte[] grByte = serialize(grPacket);
        
        DatagramPacket packet = new DatagramPacket(grByte, grByte.length,InetAddress.getByName(ip), PORT);
        socket.send(packet);
        //if accept ret true
        //  stop hallo and gamerequest listener threads
        //  connect to tcp server socket
         
        //if deny ret false
        // 
        
        return false;
    }
    
    public void acceptGameRequest()
    {
        
        //  if accept open tcp server 
        //  send back accept packet with port number
        //  stop hallo and gamerequest listener threads notify searchopponentdialog
    }
    
    public void terminateThread(){
        running = false;
    }
    
    
    
    @Override
    public int getChoosenColumn()
    {
        //TODO wait for turndecision packet
        return 0;
    }
    
    @Override
    public void update(Observable obs, Object obj)
    {
        //TODO send notification of choosen column when local user
        GameModel gameModel = (GameModel)obs;
        
         switch(gameModel.getGameState())
        {
            case MyTurn:
                TurnDecision turnDecision = (TurnDecision)obj;
                // send turndecison 
         }
    }
    
    @Override
    public void run(){
        running = true;
        while(running)
        {
            try {
                broadcastListener();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        
    }
    
    
}
