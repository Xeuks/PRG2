/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.ColumnChoiceListener;
import Models.GameModel;
import Models.TurnDecision;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;


/**
 *
 * @author K.Schumi
 */
public class GameView extends javax.swing.JFrame implements Observer, MouseListener {

    ArrayList<ColumnChoiceListener> subscribers = new ArrayList();
    /**
     * Creates new form GameView
     */
    public GameView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("You");

        jLabel2.setText("Opponent");

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 0));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 0, 0));

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(186, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
    
    @Override
    public void update(Observable obs, Object obj)
    {
        GameModel gameModel = (GameModel)obs;
        int rows = gameModel.getNumberOfHoles();
        int cols = gameModel.getNumberOfColumns();
        switch(gameModel.getGameState())
        {
            case PreparingGame:
            {               
                java.awt.GridLayout layout = new java.awt.GridLayout(rows, cols);
                jPanel1.setLayout(layout);
               
                for(int i = 0; i < rows; i++)
                {
                    for(int j = 0; j < cols; j++)
                    {
                        javax.swing.JTextField hole = new javax.swing.JTextField();
                          
                        hole.setText(Integer.toString(j));
                        hole.setEditable(false);
                        hole.setMinimumSize(new Dimension(50,50));
                        hole.setForeground(jPanel1.getBackground());
                        hole.setBorder(BorderFactory.createLineBorder(Color.black));
                        hole.addMouseListener(this);
                        jPanel1.add(hole);
                    }
                }
                pack();
                break;
            }
            case MyTurn:
            {
                TurnDecision turnDecision = (TurnDecision)obj;
                int r = turnDecision.getRow() + 1;
                    javax.swing.JTextField hole = (javax.swing.JTextField) jPanel1.getComponent(
                            (rows - r) * rows + turnDecision.getColumn());
                    
                       
                        hole.setForeground(Color.yellow);
                        hole.setBackground(Color.yellow);
                   
                break;
            }
            case OpponentTurn:
            {
                TurnDecision turnDecision = (TurnDecision)obj;
                int r = turnDecision.getRow() + 1;
                    javax.swing.JTextField hole = (javax.swing.JTextField) jPanel1.getComponent(
                            (rows - r) * rows + turnDecision.getColumn());
                    
                       
                        hole.setForeground(Color.red);
                        hole.setBackground(Color.red);
                break;
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent me)
    {
        javax.swing.JTextField label = (javax.swing.JTextField)me.getComponent();

        int column = Integer.parseInt(label.getText());

        for(int i = 0; i < subscribers.size(); i++)
            subscribers.get(i).onColumnChoosen(column);
    }
    
    @Override
    public void mousePressed(MouseEvent me)
            {}
    @Override
    public void mouseReleased(MouseEvent me)
            {}
    @Override
    public void mouseEntered(MouseEvent me){}
    @Override
    public void mouseExited(MouseEvent me){}
    
    
    public void SubscribeForBoardClicks(ColumnChoiceListener listener)
    {
        subscribers.add(listener);
    }
}