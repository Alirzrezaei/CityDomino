/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * This class is a complex bot class that can play more complex and consider more complex criteria
 * to play
 * @author Alireza
 */
public class ComplexBot extends Player {
    private final boolean isBot = true;
    
    public ComplexBot(GUIConnector gui, String name, int id, int x, int y) {
        super(gui,"ComplexBot" + name, id, x, y);
    }
    
    public boolean isBot(){
        return isBot;
    }
}
