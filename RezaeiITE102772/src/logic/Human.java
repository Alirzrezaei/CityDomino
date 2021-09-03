/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;


/**
 * this inherited class from Player class is making an instance of human player with given 
 * parameters, like name, id, board, current domino, next domino, ....
 * 
 * @author Alireza
 */
public class Human extends Player{
    
    private final boolean isBot = false;
    /**
     * a constructor to receive board and domino for the user
     * @param name
     * @param id
     * @param board
     * @param domino 
     */
    public Human(GUIConnector gui, String name, int id, Board board, Domino domino, Domino next) {
        super(gui, "Human"+name, id, board, domino, next);
       
    }
    public Human(Player player){
        super(player);
    }
    /**
     * the constructor to receive dimensions to create a board for player
     * @param gui
     * @param name
     * @param id
     * @param x
     * @param y 
     */
    public Human(GUIConnector gui, String name, int id, int x, int y){
        super(gui, "Human" + name, id, x, y);
    }
    public boolean isHuman(){
        return this.isBot;
    }
    
    
}
