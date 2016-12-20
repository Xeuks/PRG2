/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Opponents.NetworkOpponent;
import java.io.Serializable;
import java.net.InetAddress;

/**
 *
 * @author Damir Hodzic
 */
public class Packet implements Serializable  {
        public  NetworkOpponent.Commands cmd;
        public  int[] parameters;
        public transient InetAddress ip; 
        
        public Packet()
        {
        
        }
        
        public Packet(NetworkOpponent.Commands c, int[] params)
        {
            cmd = c;
            parameters = params;
        }
        
        public NetworkOpponent.Commands getCommand()
        {
            return cmd;
        }
        
        public final int[] getParams()
        {
            return parameters;
        }
        
        public void setIp(InetAddress ip){
            this.ip = ip;
        }
        
        public InetAddress getIp(){
            return ip;
        }
        
    }