/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import static logic.Cards.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alireza
 */
public class BoardTest {
    
    public BoardTest() {
    }

    @Test
    public void testConstructor() {
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        assertEquals(H0, board.getCells()[0][2]);
        assertEquals(CC, board.getCells()[2][2]);
    }
     @Test
    public void testConstrucot_x_y_Empty(){
        Board board = new Board(new FakeGUI(), 5, 5);
        
        assertEquals(CC, board.getCells(2, 2));
        assertEquals(E, board.getCells(2, 4));
        
    }
    @Test
    public void testConstrucot_x_y_False(){
        Board board = new Board(new FakeGUI(), 5, 5);
        
        assertFalse(board.isValidX(-1));
        assertFalse(board.isValidX(6));
        assertFalse(board.isValidY(-1));
        assertFalse(board.isValidY(7));
    }
     @Test
    public void testConstrucot_x_y_True(){
        Board board = new Board(new FakeGUI(), 5, 5);
        
        assertTrue(board.isValidX(0));
        assertTrue(board.isValidX(4));
        assertTrue(board.isValidY(3));
        assertTrue(board.isValidY(2));
    }
      
    @Test
    public void testFits(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        assertTrue(board.fits(new Domino(Tiles.H0_H0_5, 0), new Pos(0, 0)));
        
    }
    @Test
    public void testFits_sndNotEmpty_valuesDiff(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, E, E, E}, 
            {E, E, O0, P1, E}, 
            {E, E, CC, E, E}, 
            {E, E, E, E, E},
            {E, E, E, E, E}
        });
        assertFalse(board.fits(new Domino(Tiles.H0_S0_18, 0), new Pos(1, 1))); 
    }
    @Test
    public void testFits_sndNotEmpty_FstSame(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, E, E, E}, 
            {E, E, O0, P1, E}, 
            {E, E, CC, E, E}, 
            {E, E, E, E, E},
            {E, E, E, E, E}
        });
        assertFalse(board.fits(new Domino(Tiles.O0_I2_47, 0), new Pos(1, 1)));
        
    }
    @Test
    public void testFits_sndTouchCityCenter(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, E, E, E}, 
            {E, H0, E, E, E}, 
            {E, H0, CC, E, E}, 
            {E, E, E, E, E},
            {E, E, E, E, E}
        });
      
        assertFalse(board.fits(new Domino(Tiles.H0_S0_18, 0), new Pos(1, 1)));
        
    }
    @Test
    public void testFits_sideBySide_matchAnotherSide(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, E, E, E}, 
            {E, E, E, E, E}, 
            {E, E, CC, P0, E}, 
            {E, E, E, H0, E},
            {E, E, E, E, E}
        });
        assertFalse(board.fits(new Domino(Tiles.A1_P0_31, 1), new Pos(2, 4)));
        
    }
    @Test
    public void testFits_sideBySide_DominoFstMatchNeighborPos(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, E, S0, E}, 
            {E, E, E, E, E}, 
            {E, E, CC, P0, E}, 
            {E, E, E, H0, E},
            {E, E, E, E, E}
        });
        assertFalse(board.fits(new Domino(Tiles.H1_P0_24, 1), new Pos(2, 4)));
        
    }
     @Test
    public void testFits_sideBySide(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        assertTrue(board.fits(new Domino(Tiles.H0_S0_18, 0), new Pos(1, 2)));    
    }
    @Test// (expected = AssertionError.class)
    public void testFits_false_NoTouch(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
         
         for(int i = 0; i < board.getCells().length; i++){
             for (int j = 0; j < board.getCells()[i].length; j++){
                 System.out.print(board.getCells()[i][j] + ", ");
             }
             System.out.println("");
         }
        assertFalse(board.fits(new Domino(Tiles.H0_S0_18, 0), new Pos(4, 3)));    
    }
    @Test
    public void testFits_Touch_NoFit(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        assertTrue(board.fits(new Domino(Tiles.P1_H0_19, 0), new Pos(1, 2)));    
    }
    @Test
    public void testOccupSides(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        assertEquals(3, board.testOccupiedSide(new Pos(1, 2)));    
    }
     @Test
    public void testOccupSides_zeroTouch(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        assertEquals(0, board.testOccupiedSide(new Pos(4, 4)));    
    }
     @Test
    public void testFindPos1(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
         Pos p = board.findPos(new Domino(Tiles.H0_H0_4));
         assertTrue(board.findPos(new Domino(Tiles.H0_H0_4)).equals(p));    
    }
    @Test //(expected = AssertionError.class)
    public void testFindPos2(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
         Pos p = board.findPos(new Domino(Tiles.S0_S0_11, 1));
        assertTrue(board.findPos(new Domino(Tiles.S0_S0_11, 1)).equals(p));    
    }
     @Test
    public void testOccupiedSide_middleNoTouch(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, E, E, E}, 
            {E, E, E, E, E}, 
            {E, E, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos p = new Pos(1, 1);
        assertEquals(0, board.testOccupiedSide(p));
    }
     @Test
    public void testOccupiedSide_middleOneTouch(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos p = new Pos(2, 3);
        assertEquals(1, board.testOccupiedSide(p));
    }
    @Test
    public void testOccupiedSide_middleMoreTouch(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos p = new Pos(1, 2);
        assertEquals(3, board.testOccupiedSide(p));
    }
     @Test
    public void testOccupiedSide_Corner(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos p = new Pos(0, 4);
        assertEquals(1, board.testOccupiedSide(p));
    }
     @Test
    public void testOccupiedSide_CornerNoTouch(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos p = new Pos(0, 0);
        assertEquals(0, board.testOccupiedSide(p));
    }
    @Test
    public void testTouchedCells_CornerNoTouch(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos p = new Pos(0, 0);
        assertNull(board.testTouchedCells(p));
    }
     @Test
    public void testTouchedCells_Corner(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos p = new Pos(0, 4);
        assertEquals(1, board.testTouchedCells(p).length);
         assertArrayEquals(new Pos[]{new Pos(0, 3)}, board.testTouchedCells(p));
    }
    @Test
    public void testTouchedCells_middleMoreTouch(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos p = new Pos(1, 2);
        //Pos [] pos = new Pos[]{new Pos(0, 2), new Pos(1, 1), new Pos(2, 2)};
        //assertArrayEquals(new Pos[]{new Pos(0, 2), new Pos(1, 1), new Pos(2, 2)}, board.testTouchedCells(p));
        assertEquals(3, board.testTouchedCells(p).length);
        Pos[] pp = board.testTouchedCells(p);
        System.out.println(pp[0].x() +"  "+pp[0].y());
        System.out.println(pp[1].x() +"  "+pp[1].y());
        System.out.println(pp[2].x() +"  "+pp[2].y());  
    }
    @Test
    public void testIsCorner_topLeftCorner(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos pos = new Pos(0, 0);
        assertTrue(board.testIsCorner(pos));
    }
    @Test
    public void testIsCorner_topRightCorner(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos pos = new Pos(0, 4);
        assertTrue(board.testIsCorner(pos));
    }
    @Test
    public void testIsCorner_bottomLeftCorner(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos pos = new Pos(4, 0);
        assertTrue(board.testIsCorner(pos));
    }
    @Test
    public void testIsCorner_bottomRightCorner(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos pos = new Pos(4, 4);
        assertTrue(board.testIsCorner(pos));
    }
    @Test
    public void testIsCorner_notCorner_middle(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos pos = new Pos(2, 4);
        assertFalse(board.testIsCorner(pos));
    }
    @Test
    public void testIsCorner_notCorner_bank(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos pos = new Pos(0, 3);
        assertFalse(board.testIsCorner(pos));
    }
     @Test
    public void testAtBank_TopBank(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos pos = new Pos(0, 3);
        assertTrue(board.testIsAtBank(pos));
    } 
    @Test
    public void testAtBank_leftBank(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos pos = new Pos(3, 0);
        assertTrue(board.testIsAtBank(pos));
    }
    @Test
    public void testAtBank_rightBank(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos pos = new Pos(2, 4);
        assertTrue(board.testIsAtBank(pos));
    }
    @Test
    public void testAtBank_bottomBank(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos pos = new Pos(4, 1);
        assertTrue(board.testIsAtBank(pos));
    }
    @Test
    public void testAtBank_notBank_corner(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos pos = new Pos(4, 4);
        assertFalse(board.testIsAtBank(pos));
    }
    @Test
    public void testAtBank_notBank_middle(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Pos pos = new Pos(3, 3);
        assertFalse(board.testIsAtBank(pos));
    }
    @Test// (expected = AssertionError.class)
    public void testFit_fitAtCorner(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Domino domino = new Domino(Tiles.A0_S1_37, 1);
        Pos pos = new Pos(0, 4);
        assertFalse(board.fits(domino, pos));
    }
     @Test //(expected = AssertionError.class)
    public void testFit_NotFitAtCorner(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, I0}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Domino domino = new Domino(Tiles.A0_S1_37, 1);
        Pos pos = new Pos(0, 4);
        assertFalse(board.fits(domino, pos));
    }
     @Test //(expected = AssertionError.class)
    public void testFit_fitAtBank(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, I0, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Domino domino = new Domino(Tiles.I1_P0_40, 3);
        Pos pos = new Pos(2, 4);
        assertTrue(board.fits(domino, pos));
    }
     @Test//(expected = AssertionError.class)
    public void testFit_notFitAtBank(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, O0}, 
            {E, P1, CC, I0, E}, 
            {E, P1, S0, E, S1},
            {E, E, E, E, E}
        });
        Domino domino = new Domino(Tiles.A0_S1_37);
        Pos pos = new Pos(2, 4);
        assertFalse(board.fits(domino, pos));
    } 
    @Test//(expected = AssertionError.class)
    public void testFit_FitAtBank_twoSides(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, I0, E}, 
            {E, P1, S0, E, S1},
            {E, E, E, E, E}
        });
        Domino domino = new Domino(Tiles.A0_S1_37);
        Pos pos = new Pos(2, 4);
        assertFalse(board.fits(domino, pos));
    }
     @Test
    public void testFit_CityCenter(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, I0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, I0, E}, 
            {E, P1, S0, E, S1},
            {E, E, E, E, E}
        });
        
        Domino domino = new Domino(Tiles.A0_S1_37);
        Pos pos = new Pos(1, 2);
        assertTrue(board.fits(domino, pos));
    }
     @Test
    public void testFit_NotFitMiddle(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, I1, E}, 
            {E, P1, CC, I0, E}, 
            {E, P1, S0, E, S1},
            {E, E, E, E, E}
        });
        Domino domino = new Domino(Tiles.A0_S1_37);
        Pos pos = new Pos(1, 2);
        assertFalse(board.fits(domino, pos));
    }
     @Test
    public void testFit_NotFitMiddle_NoTouch(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, I1, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, E, E, E},
            {E, E, E, E, E}
        });
        Domino domino = new Domino(Tiles.A0_S1_37);
        Pos pos = new Pos(3, 3);
        assertFalse(board.fits(domino, pos));
    }
    @Test //(expected = AssertionError.class)
    public void testFit_NotFitBank_NoTouch(){
         Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, I1, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, E, E, E},
            {E, E, E, E, E}
        });
        Domino domino = new Domino(Tiles.A0_S1_37);
        Pos pos = new Pos(4, 3);
        assertEquals(0, board.testOccupiedSide(pos));
        
       // assertNull(board.testTouchedCells(pos));
        assertFalse(board.fits(domino, pos));
    }
    @Test
    public void testOccupiedSide_Pos_4_3(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, I1, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, E, E, E},
            {E, E, E, E, E}
        });
        Pos pos = new Pos(4, 3);
      
        assertEquals(0, board.testOccupiedSide(pos));
        
    }
    
    
    @Test
    public void calcPoints_oneRegion() {
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E}, 
            {E, H0, E, E, E}, 
            {E, P1, CC, E, E}, 
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        
        assertEquals(4, board.CalcPoints());
    }
    @Test
    public void calcPoints_seperate_sixRegion() {
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, H0, E, E}, 
            {E, H0, S0, E, E}, 
            {E, P1, CC, H0, E}, 
            {E, P1, S0, H1, E},
            {E, E, E, E, E}
        });
        
        
        assertEquals(0+0+0+4+2+0, board.CalcPoints());
    }
    @Test
    public void calcPoints_seperate_withoutScore() {
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, H0, H0, E, E}, 
            {E, H0, E, E, E}, 
            {E, P0, CC, H0, E}, 
            {E, E, E, H0, E},
            {E, E, E, E, E}
        });
        
        assertEquals(0, board.CalcPoints());
    }
    @Test
    public void calcPoints_seperate_fourRegion() {
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, H0, H0, E, E}, 
            {E, H1, E, E, E}, 
            {E, P1, CC, H0, E}, 
            {E, E, S0, H1, E},
            {E, E, S1, E, E}
        });
        
        assertEquals(3+1+2+2, board.CalcPoints());
    }
    @Test
    public void calcPoints_noRegion() {
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, E, E, E, E}, 
            {E, E, E, E, E}, 
            {E, E, CC, E, E}, 
            {E, E, E, E, E},
            {E, E, E, E, E}
        });
        
        assertEquals(0, board.CalcPoints());
    }
    @Test
    public void calcPoints_RegioAtOnSide() {
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {H1, P1, E, E, E}, 
            {H0, P1, E, E, E}, 
            {I1, P0, CC, E, E}, 
            {O2, P0, E, E, E},
            {E, I3, E, E, E}
        });
        
        assertEquals(2+8+1+2+3, board.CalcPoints());
    }
    
    @Test
    public void testFits_inCorner_OccupiedCorner(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {H1, P1, P0, P0, A0}, 
            {H0, P1, P0, E, E}, 
            {I1, P0, CC, E, E}, 
            {O2, P0, E, E, E},
            {E, I3, E, E, E}
        });
        Domino domino = new Domino(Tiles.P0_P0_1);
        Pos fst = new Pos(4, 0);
        assertFalse(board.fits(domino, fst));
    }
     @Test
    public void testFits_inCorner_OccupiedCorner_bottom(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {H1, P1, P0, P0, E}, 
            {H0, P1, P0, P0, P0}, 
            {I1, P0, CC, E, E}, 
            {O2, P0, E, E, E},
            {E, I3, E, E, E}
        });
        Domino domino = new Domino(Tiles.P0_P0_1);
        Pos fst = new Pos(0, 4);
        assertFalse(board.fits(domino, fst));
    }
     @Test
    public void testCellsIsAlone_touchSnd_inCorner(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {H1, P1, E, E, E}, 
            {H0, P1, P0, P0, P0}, 
            {I1, P0, CC, E, E}, 
            {O2, P0, E, E, E},
            {E, I3, E, E, E}
        });
        Domino domino = new Domino(Tiles.P0_P0_1, 1);
        Pos fst = new Pos(0, 2);
        assertTrue(board.testOneCellFree(domino, fst));
    }
    @Test
    public void testCellsIsAlone_touchFst(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {H1, P1, E, E, E}, 
            {H0, P1, P0, P0, P0}, 
            {I1, P0, CC, E, E}, 
            {O2, P0, E, E, E},
            {E, I3, E, E, E}
        });
        Domino domino = new Domino(Tiles.P0_P0_1, 3);
        Pos fst = new Pos(0, 2);
        assertTrue(board.testOneCellFree(domino, fst));
    }
    @Test
    public void testCellsIsAlone_touchSnd_inBank(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {P0, P1, E, E, E}, 
            {P0, P1, P0, P0, P0}, 
            {I1, P0, CC, E, E}, 
            {O2, P0, P0, E, I1},
            {E, I3, P0, P0, P0}
        });
        Domino domino = new Domino(Tiles.A1_H0_35, 1);
        Pos fst = new Pos(2, 3);
        //System.out.println(board.toString());
       assertTrue(board.testOneCellFree(domino, fst));
        board.lay(domino, fst);
        //System.out.println(board.toString());
    }
     @Test
    public void testCalcPoints_fullBoard(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {H0, H0, A1, H0, H0}, 
            {H0, H0, H0, H0, H0}, 
            {H1, I2, CC, P0, O1}, 
            {P0, P0, S0, P0, O2},
            {E, E, S0, O0, O0}
        });
        
       assertEquals(10+1+2+12, board.CalcPoints());
        
    }
     @Test
    public void testCalcPoints_uShape(){
        Board board = new Board(new FakeGUI(), new Cards[][]{
            {E, P0, E, E, E}, 
            {E, P1, E, P0, E}, 
            {E, P1, CC, P0, E}, 
            {E, P0, P0, P0, O2},
            {E, E, E, O0, O0}
        });
        
       assertEquals(16+6, board.CalcPoints());
        
    }
    @Test
    public void testStringConstructor(){
        String s = "-- A0 H0 -- --\n"
                 + "P0 -- H0 H0 --\n"
                 + "P0 I3 CC -- --\n"
                 + "-- O0 -- -- --\n"
                 + "-- -- -- -- --";
        
        Board board = new Board(s);
        
        System.out.println(board.toString());
       
        
    }
}
