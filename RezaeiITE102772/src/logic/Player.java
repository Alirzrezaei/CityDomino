/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * this class a obstract class and classes of bot and human inherited this class to make 
 * human and bot players. 
 * @author Alireza
 */
public abstract class Player {
    
    private final int ID;
   
    private final String name;
    
    private Board board;
    
    private Domino crntDomino;
    
    private Domino nextDomino; 
    
    private Pos foundPos;
    
    private int nextBoxIndex;
    
    Player player;
    
    private GUIConnector gui;
    
    /**
     * contractor to receive name of the player with board and current domino;
     * @param name
     * @param board
     * @param domino 
     */
    Player(GUIConnector gui, String name, int id, Board board, Domino domino, Domino next){
        this.gui = gui;
        this.ID = id;
        this.name = name;
        this.board = board;
        this.crntDomino = domino; 
        this.nextDomino = next;
    }
    /**
     * a constructor that is receiving name and the dimensions of the board
     * and current domino of player. 
     * @param name
     * @param x
     * @param y
     * @param domino 
     */
    Player(GUIConnector gui,String name,int id, int x, int y){
        this.gui = gui;
        this.ID = id;
        this.name = name;
        this.board = new Board(gui, x, y); 
    }
    Player(Player player){
       // this.player = player;
        this.ID = player.getID();
        this.name = player.getName();
        this.board = player.getBoard();
        this.crntDomino = player.getCrntDomino();
        this.nextDomino = player.getNextDomino();
        this.nextBoxIndex = player.nextBoxIndex();
        
        
    }
    /**
     * this mehtod return the name of the player
     * @return String name of the player
     */
    public String getName(){
        return this.name;
    }
    public int getID(){
       return this.ID; 
    }
    /**
     * this method is a getter to return the board of the player
     * @return Board the board of player
     */
    public Board getBoard(){
        return this.board;
    }
    /**
     * this method is returning the current domino of the player
     * @return Domino current domino for the player
     */
    public Domino getCrntDomino(){
        return this.crntDomino;
    }
    
    public Domino getNextDomino(){
        return this.nextDomino;
    }
    public void setNextDomino(Domino domino){
        this.nextDomino = domino; 
    }
    /**
     * this method is returning the index of nextBox that bot player chose
     * @return int index of next domino
     */
    public int nextBoxIndex(){
        return this.nextBoxIndex;
    }/**
     * this method sets the given index as index of the next box for next round
     * @param idx 
     */
    public void setNextBoxIndex(int idx){
        this.nextBoxIndex = idx;
    }
 
    /**
     * set the given domino as current domino of the player. 
     * @param domino 
     */
    public void setCrntDomino(Domino domino){
        this.crntDomino = domino;
    }
    /**
     * 
     * @return true if the player is bot and false anyway
     */
    boolean isBot(){
       return false;
    }
    /**
     * this method is implemented in Bot class and do whatever is needed for the bot to do. 
     * @param nextBox
     * @param nextRoundPlayer 
     */
    public void botTurn(Domino[] nextBox, NextPlayer [] nextRoundPlayer){
        
    }
    @Override
    public String toString(){
        return "";
    }
    
}
    
