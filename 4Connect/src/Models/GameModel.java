/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Opponents.IOpponent;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author K.Schumi
 */
public class GameModel extends Observable {
    public enum GameStates {
        PreparingGame,
        MyTurn,
        OpponentTurn,
        GameOver,
    }
    
    
    final char  USER_MARK = 'I';
    final char  OPPONENT_MARK = 'O';
    
    GameStates currentState;
    ArrayList<ArrayList<Character>> board;
    IOpponent opponent;
    int numberOfHoles;
    int numberOfColumns;
    boolean shouldOpponentStart;
    boolean didUserWin = false;
    
    public GameModel(int x, int y, IOpponent opp, boolean shouldOppStart)
    {
        currentState = GameStates.PreparingGame;
        shouldOpponentStart = shouldOppStart;
        numberOfHoles = x;
        numberOfColumns = y;
        opponent = opp;
        board = new ArrayList(numberOfHoles);
        
        for(int i = 0; i < numberOfHoles; i++)
            board.add(new ArrayList());
        
        
        addObserver(opponent);
    }
    
    
    
    public void Start()
    {
        sendNotification();
        
        if(shouldOpponentStart)
            currentState = GameStates.OpponentTurn;
        else
            currentState = GameStates.MyTurn;
    }
    
    public boolean isGameRunning()
    {
        return currentState == GameStates.MyTurn || currentState == GameStates.OpponentTurn;
    }
    
    public void checkIfWon()
    {
        boolean hasSomeoneWon = false;
        //TODO check if someone won
        
        
        if(hasSomeoneWon)
        {
            didUserWin = ( currentState == GameStates.MyTurn);
            currentState = GameStates.GameOver; 
        }
        else
        {
             currentState = ( currentState == GameStates.MyTurn)
                                ? GameStates.OpponentTurn
                                : GameStates.MyTurn;
        }

    }
    
    public boolean isValidChoice(int column)
    {
        boolean valid = (column >= 0 && column < numberOfColumns) && board.get(column).size() < numberOfHoles;
        return valid;
    }
    
    public void insertStone(int column)
    {
        if(isValidChoice(column))
        {
            board.get(column).add(currentState == GameStates.MyTurn ? USER_MARK : OPPONENT_MARK);
            sendNotification(new TurnDecision(column, board.get(column).size()-1));
            
            checkIfWon();
        }
    }
    
    public IOpponent getOpponent()
    {
        return opponent;
    }
    
    public int getNumberOfColumns()
    {
        return numberOfColumns;
    }
    
    public int getNumberOfHoles()
    {
        return numberOfHoles;
    }
    
    public boolean hasUserWon()
    {
        return didUserWin;
    }
    
    public void setGameState(GameStates state)
    {
        currentState = state;
    }
    
    public GameStates getGameState()
    {
        return currentState;
    }
    
    void sendNotification()
    {
        setChanged(); 
        notifyObservers();    
    }
    
    void sendNotification(Object param)
    {
        setChanged(); 
        notifyObservers(param);    
    }
}
