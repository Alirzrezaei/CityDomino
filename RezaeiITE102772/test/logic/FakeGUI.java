/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import logic.Board;
import logic.Domino;
import logic.GUIConnector;
import logic.Pos;
import logic.Result;

/**
 *
 * @author klk
 */
public class FakeGUI implements GUIConnector {

    @Override
    public void setToNext(int index, Domino domino) {}

    @Override
    public void showInChooseBox(Domino domino) {}

    @Override
    public void updateGrid(Board board, int id) {}

    @Override
    public void showResult(Result result) {}


    @Override
    public void showOnGrid(Pos fstPos, Cards fstValue, Pos sndPos, Cards sndValue, int id) {}

    @Override
    public void setToCurrent(int index, Domino domino) {}

    @Override
    public void placeCityHall() {}

    @Override
    public void colorLabels(int index, int playerID) {}

    
}
