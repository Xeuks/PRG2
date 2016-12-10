/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Opponents;

import Models.GameModel;
import Models.TurnDecision;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author K.Schumi
 */
public class NetworkOpponent extends Observable implements IOpponent, Observer {
    
    public enum Commands {Hallo, GameRequest, AcceptRequest, RefuseRequest, TurnDecision}
    
    public class Packet {
        Commands cmd;
        int[] parameters;
        
        public Packet(Commands c, int[] params)
        {
            cmd = c;
            parameters = params;
        }
        
        public Commands getCommand()
        {
            return cmd;
        }
        
        public final int[] getParams()
        {
            return parameters;
        }
    }
    
    public NetworkOpponent()
    {
       
    }
    
    public void searchForOpponents()
    {
        //thread to listen for gamerequest commands
        // on random port
        
        
        //send hallo command
        // with port of gamerequest listener port as a parameter
        //thread to listen for hallo commands
        // add all host which respond to local list
    }
    
    public void broadcastHallo()
    {
        //send hallo command
    }
    
    public boolean sendGameRequest(String ip, int port)
    {
        //send request to ip on port 4600
        //wait for response
        
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
}
