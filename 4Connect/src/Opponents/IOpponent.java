/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Opponents;

import java.util.Observer;

/**
 *
 * @author K.Schumi
 */
public interface IOpponent extends Observer {
    int getChoosenColumn();
}
