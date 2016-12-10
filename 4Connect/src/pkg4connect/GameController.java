/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4connect;
import Views.*;
import Models.*;

/**
 *
 * @author K.Schumi
 */
public class GameController implements ColumnChoiceListener{
    GameView gameView;
    GameModel gameModel;
    
    int columnChoosenByUser;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameController ctrl = new GameController();
        ctrl.startGameLoop();
    }
    
    public GameController()
    {
        gameView = new GameView();
        
        columnChoosenByUser = -1;
        
        gameView.SubscribeForBoardClicks(this);     
    }
    
    @Override
    public void onColumnChoosen(int column)
    {
        if(gameModel.getGameState() == GameModel.GameStates.MyTurn)
            columnChoosenByUser = column;
    }
    
    public void startGameLoop()
    {
        if(gameModel == null)
            prepareGame(); 
        
        while(true)
        {
            switch(gameModel.getGameState())
            {
                case PreparingGame:
                    prepareGame();   
                    break;

                case OpponentTurn:
                    int col = gameModel.getOpponent().getChoosenColumn();
                    if(gameModel.isValidChoice(col))
                    {
                        gameModel.insertStone(col); 
                    }                  
                    break;
                   
                case MyTurn:                    
                    if(columnChoosenByUser != -1)
                    {
                        if(gameModel.isValidChoice(columnChoosenByUser))
                        {
                            gameModel.insertStone(columnChoosenByUser);
                        }
                        columnChoosenByUser = -1;

                    }
                    break;
                    
                case GameOver:
                    gameModel.setGameState(GameModel.GameStates.PreparingGame);

                     
                    gameView.setVisible(false);
                    break;
            }
        }
    }
    
    void prepareGame()
    {
        GameModelFactoryDialog gmSettingsDialog = new GameModelFactoryDialog(gameView, true);
        gmSettingsDialog.setVisible(true);
        
        gameModel = gmSettingsDialog.getGameModel();
        gameModel.addObserver(gameView);
        // prepare done notfiy all observer
        gameModel.Start();
        gameView.setVisible(true);
    }
    
    public void saveGame()
    {
        //TODO
    }
    
}
