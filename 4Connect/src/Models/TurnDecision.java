/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author K.Schumi
 */
public class TurnDecision {
    int Column;
    int Row;
    
    public TurnDecision(int col, int row)
    {
        Row = row;
        Column = col;
    }
    
    public int getColumn()
    {
        return Column;
    }
    
    public int getRow()
    {
        return Row;
    }
}
