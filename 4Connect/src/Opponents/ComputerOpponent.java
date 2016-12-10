/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Opponents;

import Models.GameModel;
import Models.TurnDecision;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author K.Schumi
 */
public class ComputerOpponent implements IOpponent, Observer {

    ArrayList<ArrayList<Character>> board;
    int nrOfHoles;
    
    
    @Override
    public int getChoosenColumn()
    {
        for(int i = 0; i < board.size(); i++)
        {
            if(board.get(i).size() < nrOfHoles)
                return i;
        }
        return 0;
    }
    
    @Override
    public void update(Observable obs, Object obj)
    {
        GameModel gameModel = (GameModel)obs;
        
         switch(gameModel.getGameState())
        {
            case PreparingGame:
                if(board == null)
                {
                    board = new ArrayList();
                    int nrOfCols = gameModel.getNumberOfColumns();
                    for(int i = 0; i < nrOfCols; i++)
                    {
                         board.add(new ArrayList());
                    }
                    nrOfHoles = gameModel.getNumberOfHoles();
                }
                break;
            case MyTurn:
                
            case OpponentTurn:
                TurnDecision turnDecision = (TurnDecision)obj;
                board.get(turnDecision.getColumn()).add('x');
                break;
         }
    }
}
