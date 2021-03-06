/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * This class is enum class contains all the cards with their type and prestige.
 * @author Alireza
 */
public enum Cards {
    A0(0, CardType.AMUSEMENT),  A1(1, CardType.AMUSEMENT),  A2(2, CardType.AMUSEMENT),  A3(3, CardType.AMUSEMENT),
    H0(0, CardType.HOME),       H1(1, CardType.HOME),       H2(2, CardType.HOME),       H3(3, CardType.HOME),
    I0(0, CardType.INDUSTRY),    I1(1, CardType.INDUSTRY),    I2(2, CardType.INDUSTRY),    I3(3, CardType.INDUSTRY),
    O0(0, CardType.OFFICE),     O1(1, CardType.OFFICE),     O2(2, CardType.OFFICE),     O3(3, CardType.OFFICE),
    P0(0, CardType.PARK),       P1(1, CardType.PARK),       P2(2, CardType.PARK),       P3(3, CardType.PARK),    
    S0(0, CardType.SHOPPING),   S1(1, CardType.SHOPPING),   S2(2, CardType.SHOPPING),   S3(3, CardType.SHOPPING), 
      
    CC(0, CardType.CITYCENTER), E(0, CardType.EMPTY);
    
    /**
     * each card has a prestige as a point that can be calculated at the end of 
     * game for score of player
     */
    private int point;
    
    /**
     * each card had a type and card with same type can be laid beside in the field
     */
    private CardType type;
    
    /**
     * constructor to make a card with prestige and type
     * @param point
     * @param type 
     */
    Cards(int point, CardType type){
        this.point = point;
        this.type = type;
    }
    /**
     * this method is returning prestige of each card
     * @return Prestige of each card
     */
    int getPoint(){
        return point;
    }
    /**
     * this method is returning the type of each card
     * @return type of the card
     */
    CardType getType(){
        return type;
    }
    protected static Cards stringToCard(String s){
        Cards card = null;
        if(s.equals("A0")){
            card = Cards.A0;
        }else if(s.equals("A1")){
            card = Cards.A1;
        }else if(s.equals("A2")){
            card = Cards.A2;
        }else if(s.equals("A3")){
            card = Cards.A3;
        }else if(s.equals("H0")){
            card = Cards.H0;
        }else if(s.equals("H1")){
            card = Cards.H1;
        }else if(s.equals("H2")){
            card = Cards.H2;
        }else if(s.equals("H3")){
            card = Cards.H3;
        }else if(s.equals("I0")){
            card = Cards.I0;
        }else if(s.equals("I1")){
            card = Cards.I1;
        }else if(s.equals("I2")){
            card = Cards.I2;
        }else if(s.equals("I3")){
            card = Cards.I3;
        }else if(s.equals("O0")){
            card = Cards.O0;
        }else if(s.equals("O1")){
            card = Cards.O1;
        }else if(s.equals("O2")){
            card = Cards.O2;
        }else if(s.equals("O3")){
            card = Cards.O3;
        }else if(s.equals("P0")){
            card = Cards.P0;
        }else if(s.equals("P1")){
            card = Cards.P1;
        }else if(s.equals("P2")){
            card = Cards.P2;
        }else if(s.equals("P3")){
            card = Cards.P3;
        }else if(s.equals("S0")){
            card = Cards.S0;
        }else if(s.equals("S1")){
            card = Cards.S1;
        }else if(s.equals("S2")){
            card = Cards.S2;
        }else if(s.equals("S3")){
            card = Cards.S3;
        }else if(s.equals("CC")){
            card = Cards.CC;
        }else if(s.equals("--")){
            card = Cards.E;
        }else{
            System.out.println("Wrond string format");
        }
        return card;
    }
}
