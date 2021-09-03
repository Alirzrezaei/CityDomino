/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * this next player is storing players into instance of player and is being used in 
 * game in logic to keep track of current round players and storing next round players
 * 
 * @author Alireza
 */
public class NextPlayer {
    private Player player;
    
    NextPlayer(Player player){
        this.player = player;
    }
    NextPlayer(Bot player){
        this.player = player;
    }
    NextPlayer(Human player){
        this.player = player;
    }
    public Player getNextPlayer(){
        return this.player;
    }
}
