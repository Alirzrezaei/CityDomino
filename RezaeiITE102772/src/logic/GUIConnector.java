/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;



/**
 * This class is a interface and it is used to connect the logic with gui. 
 * classes in game send their updates to abstracted method to show them in the gui. 
 * @author Alireza
 */
public interface GUIConnector {
 
    public void setToNext(int index, Domino domino);
    
    
    public void showInChooseBox(Domino domino);

   
    public void updateGrid(Board board, int id);

    
    public void showOnGrid(Pos fstPos, Cards fstValue, Pos sndPos, Cards sndValue, int id);

    
    public void showResult(Result result);


    
    public void setToCurrent(int index, Domino domino);
    
    public void placeCityHall();
    
    public void colorLabels(int index, int playerID);
}
