/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.List;

/**
 * Class tiles has all 38 tiles and each tiles has two cards. Class tiles can be used to make Dominos.
 * 
 * @author Alireza
 */
public enum Tiles {
    P0_P0_1(Cards.P0, Cards.P0), P0_P0_2(Cards.P0, Cards.P0), H0_H0_3(Cards.H0, Cards.H0), H0_H0_4(Cards.H0, Cards.H0),
    H0_H0_5(Cards.H0, Cards.H0), H0_H0_6(Cards.H0, Cards.H0), A0_A0_7(Cards.A0, Cards.A0), A0_A0_8(Cards.A0, Cards.A0),
    A0_A0_9(Cards.A0, Cards.A0), S0_S0_10(Cards.S0, Cards.S0), S0_S0_11(Cards.S0, Cards.S0), O0_O0_12(Cards.O0, Cards.O0),
    P0_H0_13(Cards.P0, Cards.H0), P0_A0_14(Cards.P0, Cards.A0), P0_S0_15(Cards.P0, Cards.S0), P0_O0_16(Cards.P0, Cards.O0),
    H0_A0_17(Cards.H0, Cards.A0), H0_S0_18(Cards.H0, Cards.S0), P1_H0_19(Cards.P1, Cards.H0), P1_A0_20(Cards.P1, Cards.A0),
    P1_S0_21(Cards.P1, Cards.S0), P1_O0_22(Cards.P1, Cards.O0), P1_I0_23(Cards.P1, Cards.I0), H1_P0_24(Cards.H1, Cards.P0),
    H1_P0_25(Cards.H1, Cards.P0), H1_P0_26(Cards.H1, Cards.P0), H1_P0_27(Cards.H1, Cards.P0), H1_A0_28(Cards.H1, Cards.A0),
    H1_S0_29(Cards.H1, Cards.S0), A1_P0_30(Cards.A1, Cards.P0), A1_P0_31(Cards.A1, Cards.P0), A1_H0_32(Cards.A1, Cards.H0),
    A1_H0_33(Cards.A1, Cards.H0), A1_H0_34(Cards.A1, Cards.H0), A1_H0_35(Cards.A1, Cards.H0), P0_S1_36(Cards.P0, Cards.S1),
    A0_S1_37(Cards.A0, Cards.S1), P0_O1_38(Cards.P0, Cards.O1), S0_O1_39(Cards.S0, Cards.O1), I1_P0_40(Cards.I1, Cards.P0),
    P0_S2_41(Cards.P0, Cards.S2), A0_S2_42(Cards.A0, Cards.S2), P0_O2_43(Cards.P0, Cards.O2), S0_O2_44(Cards.S0, Cards.O2),
    I2_P0_45(Cards.I2, Cards.P0), O0_I2_46(Cards.O0, Cards.I2), O0_I2_47(Cards.O0, Cards.I2), P0_I3_48(Cards.P0, Cards.I3);

    private final Cards fst;
    private final Cards snd;

    Tiles(Cards fst, Cards snd) {
        this.fst = fst;
        this.snd = snd;
    }

    protected static Tiles stringToTiles(List<Tiles> tiles, String s) {
        Tiles tile = null;
        if (s.equals("P0P0")) {
            if (!tiles.contains(P0_P0_1)) {
                tile = P0_P0_1;
            } else {
                tile = P0_P0_2;
            }
        } else if (s.equals("H0H0")) {
            if (!tiles.contains(H0_H0_3)) {
                tile = H0_H0_3;
            } else if (!tiles.contains(H0_H0_4)) {
                tile = H0_H0_4;
            } else if (!tiles.contains(H0_H0_5)) {
                tile = H0_H0_5;
            } else {
                tile = H0_H0_6;
            }
        } else if (s.equals("A0A0")) {
            if (!tiles.contains(A0_A0_7)) {
                tile = A0_A0_7;
            } else if (!tiles.contains(A0_A0_8)) {
                tile = A0_A0_8;
            } else {
                tile = A0_A0_9;
            }
        } else if (s.equals("S0S0")) {
            if (!tiles.contains(S0_S0_10)) {
                tile = S0_S0_10;
            } else {
                tile = S0_S0_11;
            }
        } else if (s.equals("O0O0")) {
            tile = O0_O0_12;
        } else if (s.equals("P0H0")) {
            tile = P0_H0_13;
        } else if (s.equals("P0A0")) {
            tile = P0_A0_14;
        } else if (s.equals("P0S0")) {
            tile = P0_S0_15;
        } else if (s.equals("P0O0")) {
            tile = P0_O0_16;
        } else if (s.equals("H0A0")) {
            tile = H0_A0_17;
        } else if (s.equals("H0S0")) {
            tile = H0_S0_18;
        } else if (s.equals("P1H0")) {
            tile = P1_H0_19;
        } else if (s.equals("P1A0")) {
            tile = P1_A0_20;
        } else if (s.equals("P1S0")) {
            tile = P1_S0_21;
        } else if (s.equals("P1O0")) {
            tile = P1_O0_22;
        } else if (s.equals("P1I0")) {
            tile = P1_I0_23;
        } else if (s.equals("H1P0")) {
            if (!tiles.contains(H1_P0_24)) {
                tile = H1_P0_24;
            } else if (!tiles.contains(H1_P0_25)) {
                tile = H1_P0_25;
            } else if (!tiles.contains(H1_P0_26)) {
                tile = H1_P0_26;
            } else {
                tile = H1_P0_27;
            }
        } else if (s.equals("H1A0")) {
            tile = H1_A0_28;
        } else if (s.equals("H1S0")) {
            tile = H1_S0_29;
        } else if (s.equals("A1P0")) {
            if (!tiles.contains(A1_P0_30)) {
                tile = A1_P0_30;
            } else {
                tile = A1_P0_31;
            }
        }else if(s.equals("A1H0")){
            if(!tiles.contains(A1_H0_32)){
            tile = A1_H0_32;
            }else if(!tiles.contains(A1_H0_33)){
                tile = A1_H0_33;
            }else if(!tiles.contains(A1_H0_34)){
                tile = A1_H0_34;
            }else{
                tile = A1_H0_35;
            }
        }else if(s.equals("P0S1")){
            tile = P0_S1_36;
        }else if(s.equals("A0S1")){
            tile = A0_S1_37;
        }else if(s.equals("P0O1")){
            tile = P0_O1_38;
        }else if(s.equals("S0O1")){
            tile = S0_O1_39;
        }else if(s.equals("I1P0")){
            tile = I1_P0_40;
        }else if(s.equals("P0S2")){
            tile = P0_S2_41;
        }else if(s.equals("A0S2")){
            tile = A0_S2_42;
        }else if(s.equals("P0O2")){
            tile = P0_O2_43;
        }else if(s.equals("S0O2")){
            tile = S0_O2_44;
        }else if(s.equals("I2P0")){
            tile = I2_P0_45;
        }else if(s.equals("O0I2")){
            if(!tiles.contains(O0_I2_46)){
                tile = O0_I2_46;
            }else{
                tile = O0_I2_47;
            }
        }else if(s.equals("P0I3")){
            tile = P0_I3_48;
        }else{
            System.out.println("Wrong String is provided.");
        }

        return tile;
    }

    public Cards getFirst() {
        return fst;
    }

    public Cards getSecond() {
        return snd;
    }

    public String toString() {
        return this.getFirst() + "" + this.getSecond();
    }
}
