/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * the class Pos is making positions with given x and y coordinates of the board. 
 * It also calculated neighbors of given position and also checks if two positions are 
 * next to each other. 
 * @author Alireza
 */
public class Pos {
    
    private final int x;
    private final int y;
    
    /**
     * constructor to make a position at the given x and y
     * @param x first value of the position on x axis
     * @param y second value of the position on y axis
     */
    public Pos(int x, int y){
        this.x = x;
        this.y = y; 
    }
    /**
     * 
     * @return integer the X value of the position
     */
    public int x(){
        return this.x;
    }
    /**
     * 
     * @return integer the Y value of the position
     */
    public int y(){
        return this.y;
    }
    /**
     * checks if the given position is next to this position 
     * @param pos Position
     * @return true if the given position is next to this position
     */
    public boolean isNextPos(Pos pos){
        int xDiffer = Math.abs(x - pos.x());
        int yDiffer = Math.abs(y - pos.y());
        
        return ((xDiffer == 1 && yDiffer == 0) || (xDiffer == 0 && yDiffer == 1));
    }
    /**
     * calculate neighbors of this position.
     * @return array of neighbor for this position
     */
    public Pos[] getNeigbors(){
         
        Pos[] neighbours = new Pos[4];
        neighbours[0] = new Pos(this.x-1, this.y  );
        neighbours[1] = new Pos(this.x  , this.y-1); 
        neighbours[2] = new Pos(this.x+1, this.y)  ; 
        neighbours[3] = new Pos(this.x  , this.y+1); 
        return neighbours;
    }
    /**
     * two positions are equal if the x-values and y-values are equal 
     * @param obj
     * @return true, if the x-values and y-values are equal 
     */
    @Override
    public boolean equals(Object obj) {
        return obj != null &&
                obj.getClass() == this.getClass() &&
                ((Pos)obj).x == this.x &&
                ((Pos)obj).y == this.y;
    }
    @Override
    public String toString(){
        return "( "+this.x() + ", " + this.y() +" )";
    }
}
