/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.Arrays;

/**
 * This class receives array of players and work on it to calculate to find who is the 
 * winner of the game and its point. 
 * @author Alireza
 */
    
    
public class Result {
    private String winner;
    private int winnerPoint = 0;
    private Player [] players;
    
    /**
     * a constructor receives players and works on it to display result. 
     * @param players 
     */
    Result(Player[] players){
        this.players = players;
        getWinner(players);
    }
    /**
     * this method is finding player with higher score and set winner name and its point 
     * into global variables.
     * @param players 
     */
    private void getWinner(Player[] players){
        String winner = "";
        int temp;
        int point = 0;
        
        for(int i = 0; i < players.length; i++){
            temp = players[i].getBoard().CalcPoints();
            if(temp > point){
                point = temp;
                winner =  players[i].getName();
            }
        }
        this.winnerPoint = point;
        this.winner = winner;    
    }
    /**
     * returns highest point of the given player array
     * @return highest point
     */
    public int getWinnerPoint(){
        return this.winnerPoint;
    }
    /**
     * returns name of the winner player
     * @return String name of player
     */
    public String getWinner(){
        return this.winner;
    }
    /**
     * return array of player
     * @return array of players
     */
    public Player[] getPlayer(){
        return this.players;
    }
    /**
     * this method is getting the array of player and calculate the points and return 
     * result for all players in string format
     * @param player
     * @return string details of points
     */
    public String baords(Player[] player){
        String boards = "";
        
        for(int i = 0; i < player.length; i++){
            //boards = boards + player[i].getName() + "\n";
            //boards = boards + player[i].getBoard().toString()+ "\n";
            boards = boards + player[i].getBoard().CalcPointsFinal(player[i].getName());
            boards = boards + "point of board: "+player[i].getBoard().CalcPoints() +"\n";
            boards = boards + "-----------------------\n";
        }
                
        return boards;
    }
    
    
    
}
