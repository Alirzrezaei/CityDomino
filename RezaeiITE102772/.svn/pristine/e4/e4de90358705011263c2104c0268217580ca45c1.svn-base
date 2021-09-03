/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;


import org.junit.Test;
import static org.junit.Assert.*;
import logic.Tiles;

/**
 *
 * @author Alireza
 */
public class DominoTest {
    
    public DominoTest() {
    }

    @Test
    public void testDominoConstuctor_withRotatio() {
        Domino domino = new Domino(Tiles.H0_H0_3, 0);
        
        assertEquals(0, domino.getRotation());
        
        assertEquals(Cards.H0, domino.getFst());
        assertEquals(Cards.H0, domino.getSnd());        
    }
    @Test
    public void testDominoConstuctor_withRotatio1() {
        Domino domino = new Domino(Tiles.S0_S0_10, 2);
        
        assertEquals(2, domino.getRotation());
        
        assertEquals(Cards.S0, domino.getFst());
        assertEquals(Cards.S0, domino.getSnd());        
    }
    @Test
    public void testDominoConstuctor_withoutRotation(){
        Domino domino = new Domino(Tiles.H0_H0_5);
        
        assertEquals(0, domino.getRotation());
        
        assertEquals(Cards.H0, domino.getFst());
        assertEquals(Cards.H0, domino.getSnd());       
    }
    @Test
    public void testIncrementRotation_1(){
        Domino domino = new Domino(Tiles.H0_S0_18);
        
        assertEquals(0, domino.getRotation());
       
        assertEquals(Cards.H0, domino.getFst());
        assertEquals(Cards.S0, domino.getSnd()); 
        
        domino.rotIncrement();
        
        assertEquals(1, domino.getRotation());
       
        assertEquals(Cards.H0, domino.getFst());
        assertEquals(Cards.S0, domino.getSnd()); 
    }
     @Test
    public void testIncrementRotation_2(){
        Domino domino = new Domino(Tiles.H0_S0_18);
        
        assertEquals(0, domino.getRotation());
       
        assertEquals(Cards.H0, domino.getFst());
        assertEquals(Cards.S0, domino.getSnd()); 
        
        domino.rotIncrement();
        domino.rotIncrement();
        
        assertEquals(2, domino.getRotation());
       
        assertEquals(Cards.S0, domino.getFst());
        assertEquals(Cards.H0, domino.getSnd()); 
    }
    
     @Test
    public void testPositions_rot0() {
        Domino dom = new Domino(Tiles.A1_H0_32, 0);
        assertEquals(0, dom.getRotation());
        assertEquals(Cards.A1, dom.getFst());
        assertEquals(Cards.H0, dom.getSnd());
        assertEquals(new Pos(2,1), dom.getSnd(new Pos(1,1)));
    }
    
    @Test
    public void testPositions_rot1() {
        Domino dom = new Domino(Tiles.P0_O0_16, 1);
        assertEquals(1, dom.getRotation());
        assertEquals(Cards.P0, dom.getFst());
        assertEquals(Cards.O0, dom.getSnd());
        assertEquals(new Pos(1,2), dom.getSnd(new Pos(1,1)));
    }
    
    @Test
    public void testPositions_rot2() {
        Domino dom = new Domino(Tiles.S0_O2_44, 2);
        assertEquals(2, dom.getRotation());
        assertEquals(Cards.O2, dom.getFst());
        assertEquals(Cards.S0, dom.getSnd());
        assertEquals(new Pos(1,1), dom.getSnd(new Pos(0,1)));
    }

    @Test
    public void testPositions_rot3() {
        Domino dom = new Domino(Tiles.H1_P0_24, 3);
        assertEquals(3, dom.getRotation());
        assertEquals(Cards.P0, dom.getFst());
        assertEquals(Cards.H1, dom.getSnd());
        assertEquals(new Pos(1,1), dom.getSnd(new Pos(1,0)));
    }
    @Test
    public void toStirng(){
        Domino domino = new Domino(Tiles.P0_P0_1);
        
        System.out.print(domino.toString());
        assertEquals("Tile: P0_P0_1 First: P0 Second: P0 Rotation: 0", domino.toString());
    }
    
    
    
    
}
