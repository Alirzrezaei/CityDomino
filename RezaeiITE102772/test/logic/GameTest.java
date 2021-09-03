/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import static logic.Cards.*;
import logic.FakeGUI;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alireza
 */
public class GameTest {
    
    public GameTest() {
    }

    @Test
    public void calcPoints_oneRegion() {
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, E, E}, 
            {E, H0, S0, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        
        
        assertEquals(0+0+0+4+0, board.CalcPoints());
    }

   @Test
    public void calcPoints_seperate() {
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, H0, E}, 
            {E, P1, S0, H1, E},
            {E, E, E, E, E}
        });
        
      
        assertEquals(0+0+0+4+2+0, board.CalcPoints());
    }
    @Test
    public void calcPoints_seperate2_withoutScore() {
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, H0, H0, E, E}, 
            {E, H0, E, E, E}, 
            {E, P0, CC, H0, E}, 
            {E, E, E, H0, E},
            {E, E, E, E, E}
        });
        
        
        assertEquals(0+0+0, board.CalcPoints());
    }
    @Test
    public void calcPoints_seperate3_withScore() {
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, H1, H0, E, E}, 
            {E, H1, E, E, E}, 
            {E, P0, CC, H0, E}, 
            {E, E, E, H1, E},
            {E, E, E, E, E}
        });
        
       
        assertEquals(6+0+2, board.CalcPoints());
    }
    @Test
    public void calcPoints_connected() {
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, H0, H0, H1, H1}, 
            {E, H1, E, E, E}, 
            {E, P0, CC, E, E}, 
            {E, E, E, E, E},
            {E, E, E, E, E}
        });
        
        
        assertEquals(0+15, board.CalcPoints());
    }
}
