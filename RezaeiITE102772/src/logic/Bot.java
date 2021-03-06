/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;
import java.util.Random;

/**
 * This class inherited the Player class and make a bot player to play all bots methods 
 * and store the necessary things like board, current domino, next domino, name and id of the 
 * bot player. 
 * @author Alireza
 */
public class Bot extends Player{
    
    private final boolean isBot = true;
    /**
     * a constructor to receive board and domino for the user
     * @param name
     * @param board
     * @param domino 
     */
    public Bot(GUIConnector gui, String name, int id, Board board, Domino domino, Domino next) {
        super(gui, "Bot"+name, id,board, domino, next);
  
    }
    /**
     * the constructor to receive dimensions to create a board for player
     * @param gui
     * @param name
     * @param x
     * @param y 
     */
    public Bot(GUIConnector gui, String name, int id, int x, int y){
        super(gui, "Bot"+name, id, x, y);
 
    }
    public Bot(Player player){
        super(player);
    }
    /**
     * this method returns true on call to tell that this player is a bot player 
     * @return true
     */
    @Override
    public boolean isBot(){
        return this.isBot;
    }
    /**
     * when the player is bot in the game, this method will be called and do all necessary things for 
     * bot to choose a card from next box and then put its current card into field 
     */
    public void botTurn(Domino[] nextBox, NextPlayer[] nextRoundPlayer){
        int idx = -1;
        boolean choose = false;
        Random rand  = new Random();
        if(nextBox != null){
            
        while(!choose){
            int i = rand.nextInt(4);
            
            if(nextRoundPlayer[i] == null){
                idx = i;
                this.setCrntDomino(this.getNextDomino());
                this.setNextDomino(nextBox[i]);
                choose = true;
            }
        }
        
        this.setNextBoxIndex(idx);
        
        }else{
                System.out.println("next box is null!");
        }
    }
    
}
