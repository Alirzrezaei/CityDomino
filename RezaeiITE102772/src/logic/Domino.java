/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.List;

/**
 * This class is making domino based on Tiles class in logic and save also the rotation
 * of the domino. Based on the rotation, this class return first and second tile and also
 * calculates the second position for the given first position. It also fills a stack which
 * is a linked list with all tiles. 
 * @author Alireza
 */
public class Domino {
    
    private final Tiles tiles;
        /** the rotation of the tile
     *   rotation 0 - horizontally - not rotated         - snd is right of fst      
     *            1 - vertically   - rotated  90 degrees - snd is bottom of fst     
     *            2 - horizontally - rotated 180 degrees - snd is left of fst        
     *            3 - vertically   - rotated 270 degrees - snd is top of fst
        */
    private int rotation;
    
    /**
     * create a domino with given tiles and default rotation of 0
     * @param tiles 
     */
    public Domino(Tiles tiles){
      
        this(tiles, 0);
    }
    
    
    /**
     * create a domino with given tiles and given rotation
     * @param tiles
     * @param rotation 
     */
    public Domino(Tiles tiles, int rotation){
        this.tiles = tiles;
        this.rotation = rotation; 
    }
    /**
     * this method is returning stored tiles of domino
     * @return tiles of domino
     */
    public Tiles getTiles(){
        return this.tiles;
    }
    /**
     * this method is returning stored rotation of the domino
     * @return rotation of domino
     */
    public int getRotation(){
        return this.rotation; 
    }
    /**
     * this method is incrementing the rotation of the domino by each call
     */
    public void rotIncrement(){
        rotation = (rotation + 1) % 4;
    }
    /**
     * this method is returning first part of domino as first if the rotation is 0 or 1 and 
     * return second part as first if the rotation is 2 or 3
     * @return first part as Card 
     */
    public Cards getFst(){
        return rotation < 2 ? tiles.getFirst() : tiles.getSecond();
    }
    /**
     * this method is returning second part of domino as second if the rotation is 0 or 1 and 
     * return first part as second if the rotation is 2 or 3
     * @return second part as Card
     */
    public Cards getSnd(){
        return rotation < 2 ? tiles.getSecond() : tiles.getFirst();
    }
    /**
     * this method gets first position of the domino and return position of the 
     * second card respective to rotation of the domino. 
     * case 0 or 2 added 1 to X axis and case 1 or 3 added 1 to Y axis
     * 
     * @param fst
     * @return position of the second card
     */
    public Pos getSnd(Pos fst){
        int x = fst.x();
        int y = fst.y();
        
        switch(rotation){
            
            case 0: 
            case 2: x = fst.x() + 1;
                    y = fst.y();
                    break;
            case 1:
            case 3: x = fst.x();
                    y = fst.y() + 1;
                    break;
            default:
                assert false : "rotation has to be 0..3 but was " + rotation;
        
        }
        
        return new Pos(x, y);
    }
    /**
     * this method fill a list with tiles as Domino
     * @param list
     * @return list of dominos
     */
    public static List<Domino> fill(List<Domino> list){
        list.clear();
        
        for(Tiles tile : Tiles.values()){
            list.add(new Domino(tile));
        }
        return list;
    }
    /**
     * checks if the given domino is equal to this domino
     * @param obj domino
     * @return true if this domino is equal to given domino object
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        else if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        else{
            final Domino other = (Domino) obj;
            return this.tiles == other.tiles;
        }
    }
    /**
     * this method is returning the values of the tiles and rotation of the domino
     * for printing goal
     * @return String the tiles and rotation domino
     */
    @Override
    public String toString(){
        return "[ " + this.getFst()+ ", " + this.getSnd() + " ]";
    }
}
