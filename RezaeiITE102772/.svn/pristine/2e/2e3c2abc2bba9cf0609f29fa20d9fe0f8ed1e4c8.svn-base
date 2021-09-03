/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * This class is an between gui and game. users actions and events are being sent 
 * through abstract methods of this class to game in logic package. 
 * 
 * @author Alireza
 */
public interface GUItoGame {
    
    
    void start();
    
    void save();
    
    GUItoGame load(GUItoGame game);
    
    void currentBoxClick();
    
    void clickOnNext(int idx);
    
    boolean fits(Pos pos);
    
    void setOnTheBoard(Pos pos);
    
    void discardDomino();
    
    void arrowKeysClick(char command);
}
