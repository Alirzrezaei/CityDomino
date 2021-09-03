/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import gui.FXMLDocumentController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

/**
 * this class is handling writing to a text file a saved game and/or read from a
 * text file to load a saved game. it changes all strings from the file to
 * instances of game classes and them make new game to show it into gui.
 *
 * @author Alireza
 */
public class IOCityDomino {

    GUItoGame game;
    GUIConnector gui;
    FileOutputStream f;
    OutputStreamWriter o;

    public IOCityDomino(String s) {
        try {
            this.f = new FileOutputStream(s);
            this.o = new OutputStreamWriter(f);
        } catch (IOException ex) {

        }
    }

    IOCityDomino() {

    }

    public void logRecord(NextPlayer player, Domino domino, Pos pos) {
        String s = "";
        s = s + player.getNextPlayer().getName() + " chose " + player.getNextPlayer().getNextDomino()
                + " at index " + player.getNextPlayer().nextBoxIndex() + " " + player.getNextPlayer().getName();
        if (pos != null) {

            if (domino.getRotation() == 0 || domino.getRotation() == 2) {
                s = s + " put " + domino.toString() + " horizontally to " + pos.toString() + "\n";
            } else {
                s = s + " put " + domino.toString() + " vertically to " + pos.toString() + "\n";
            }
        } else {
            s = s + " did not use " + domino.toString() + "\n";
        }
        System.out.println(s);
        try {
            o.write(s);
            o.flush();
        } catch (IOException ex) {

        }

    }

    protected void close() throws IOException {
        try {
            if (this.o != null) {
                o.close();
            }
            if (this.f != null) {
                f.close();
            }
        } catch (IOException ex) {

        }
    }

    public void save(int currentPl, NextPlayer[] currentPlayer, NextPlayer[] nextPlayer, Domino[] nextBox, Domino[] currentBox, List<Domino> stack)
            throws IOException {


        FileChooser choose = new FileChooser();
        choose.setTitle("Select File Name");
        File newFile = null;

        if (newFile == null) {
            newFile = choose.showSaveDialog(null);
        }

        if (newFile != null) {
            FileOutputStream fop = new FileOutputStream(new File(newFile.getPath()));
            OutputStreamWriter out = new OutputStreamWriter(fop);

            try {

                for (int i = 0; i < currentPlayer.length; i++) {
                    out.write("<field>");
                    out.write("<" + currentPlayer[i].getNextPlayer().getID() + ">\n");
                    out.write(currentPlayer[i].getNextPlayer().getBoard().toString() + "\n");
                }

                out.write("<banks>");
                out.write("\n");
                for (int i = currentPl; i < currentPlayer.length; i++) {
                    if (currentPlayer[i].getNextPlayer() != null) {
                        if (i == currentPlayer.length - 1) {
                            if (currentPlayer[i].getNextPlayer().getNextDomino() != null) {
                                out.write(currentPlayer[i].getNextPlayer().getID() + " "
                                        + currentPlayer[i].getNextPlayer().getNextDomino().getTiles().toString());
                            }
                        } else {
                            if (currentPlayer[i].getNextPlayer().getNextDomino() != null) {
                                out.write(currentPlayer[i].getNextPlayer().getID() + " "
                                        + currentPlayer[i].getNextPlayer().getNextDomino().getTiles().toString() + ", ");
                            }
                        }
                    }
                }

                out.write("\n");
                for (int i = 0; i < nextPlayer.length; i++) {

                    if (nextPlayer[i] != null) {
                        if (i == nextPlayer.length - 1) {
                            out.write(nextPlayer[i].getNextPlayer().getID() + " "
                                    + nextPlayer[i].getNextPlayer().getNextDomino().getTiles().toString());
                        } else {
                            out.write(nextPlayer[i].getNextPlayer().getID() + " "
                                    + nextPlayer[i].getNextPlayer().getNextDomino().getTiles().toString() + ", ");
                        }
                    } else {
                        if (i == nextPlayer.length - 1) {
                            out.write("- "
                                    + nextBox[i].getTiles().toString());
                        } else {
                            out.write("- "
                                    + nextBox[i].getTiles().toString() + ", ");
                        }
                    }
                }
                out.write("\n");
                out.write("<bag>");
                out.write("\n");
                for (int i = 0; i < stack.size(); i++) {
                    if (i == stack.size() - 1) {
                        out.write("" + stack.get(i).getFst().toString() + "" + stack.get(i).getSnd().toString());
                    } else {
                        out.write("" + stack.get(i).getFst().toString() + "" + stack.get(i).getSnd().toString() + ",");
                    }
                }
                out.flush();
            } finally {

                if (out != null) {
                    out.close();
                }
                if (fop != null) {
                    fop.close();
                }

            }
        }

    }

    public GUItoGame load(GUIConnector gui, GUItoGame game) throws IOException {
        this.gui = gui;
        this.game = game;
        FileReader fr = null;
        BufferedReader br = null;
        InputStreamReader sr = null;
        Scanner sc = null;
        String line = "";
        String s = "";
        FileChooser choose = new FileChooser();
        choose.setTitle("Choose a File to Load");
        File newFile = null;

        if (newFile == null) {
            newFile = choose.showOpenDialog(null);
        }
        if (newFile != null) {
            try {
                fr = new FileReader(newFile.getPath());
                sr = new InputStreamReader(System.in);
                br = new BufferedReader(fr);
                sc = new Scanner(sr);

                while ((line = br.readLine()) != null) {
                    s = s + line + "\n";
                }
               
                return stringSeperator(s);

            } finally {
                if (fr != null) {
                    fr.close();
                }
                if (sr != null) {
                    sr.close();
                }
                if (br != null) {
                    br.close();
                }
                if (sc != null) {
                    sc.close();
                }
            }
        }
        return stringSeperator(s);
    }

    private GUItoGame stringSeperator(String s) {
        boolean emapyCurrent = false;
        if (!s.equals("")) {
            String[] lines = s.split("\n");
            int noOfPlayers = 0;
            int playerID[];
            int x = 0;
            int k = 0;
            String[] boards;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i].contains("<field>")) {
                    noOfPlayers++;
                }
            }
            if (noOfPlayers == 4) {

                List<Tiles> stacks = new LinkedList<>();
                List<Domino> dominoStacks = new LinkedList<>();
                Domino[] currentBox = new Domino[noOfPlayers];
                Domino[] nextBox = new Domino[noOfPlayers];
                NextPlayer[] currentRoundPlayer = new NextPlayer[noOfPlayers];
                NextPlayer[] nextRoundPlayer = new NextPlayer[noOfPlayers];
                Board[] boardPl = new Board[noOfPlayers];

                String[] current = new String[noOfPlayers];
                String[] next = new String[noOfPlayers];
                String[] stack = null;
                playerID = new int[noOfPlayers];
                boards = new String[noOfPlayers];
                boolean emptyStack = false;
                int[] nextChose = new int[noOfPlayers];

                for (int i = 0; i < lines.length; i++) {
                    if (lines[i].contains("<field>")) {

                        int id = charToInt(lines[i].charAt(8));

                        playerID[k] = id;

                        if (x == 0) {
                            x = (lines[i + 1].length() + 1) / 3;
       
                            //for(int m = 0; m < boards.length; m++){
                            String board = "";
                            for (int n = 0; n < x; n++) {
                                board = board + lines[i + 1 + n] + "\n";
                            }
                            boards[k] = board;
                            // }
                        }
                     
                        k++;
                    }

                    x = 0;
                }

                for (int i = 0; i < lines.length; i++) {
                    if (lines[i].contains("<bag>")) {

                        if (lines[i + 1].equals("")) {
 
                            emptyStack = true;
                        } else {
                            int length = (lines[i + 1].length() + 1) / 5;
                            stack = new String[length];
                            String value = "";
                            int n = 0;
                            for (int j = 0; j < lines[i + 1].length(); j = j + 5) {
                                for (int m = j; m < j + 4; m++) {
                                    value = value + lines[i + 1].charAt(m);
                                }
                                stack[n] = value;
                                value = "";
                                n++;
                            }

                            // System.out.println("Stack is not empty.");
                        }
                    }
                }
                for (int i = 0; i < lines.length; i++) {
                    if (lines[i].contains("<banks>")) {

                        if (i + 1 >= lines.length || lines[i + 1].equals("")) {
                            emapyCurrent = true;
                        } else {
                            if (emptyStack) {
                                int length = (lines[i + 1].length() + 2) / 8;
                                String value = "";
                                int n = 0;
                                for (int j = 0; j < lines[i + 1].length(); j = j + 8) {
                                    for (int m = j; m < j + 6; m++) {
                                        value = value + lines[i + 1].charAt(m);
                                    }
                                    current[length + n] = value;
                                    n++;
                                    value = "";
                                }

                            } else {
                                int lengthCrnt = (lines[i + 1].length() + 2) / 8;
                                int lengthNext = 0;

                                String value = "";
                                int n = 0;
                                for (int j = 0; j < lines[i + 1].length(); j = j + 8) {
                                    for (int m = j; m < j + 6; m++) {
                                        value = value + lines[i + 1].charAt(m);
                                    }
                                    current[(current.length - lengthCrnt) + n] = value;
                                    n++;
                                    value = "";
                                }

                                if (!lines[i + 2].equals("")) {
                                    lengthNext = (lines[i + 2].length() + 2) / 8;
                                    n = 0;
                                    for (int j = 0; j < lines[i + 2].length(); j = j + 8) {
                                        for (int m = j; m < j + 6; m++) {
                                            value = value + lines[i + 2].charAt(m);
                                        }
                                        next[n] = value;
                                        n++;
                                        value = "";
                                    }
                                }
                            }
                        }

                    }

                }

                for (int i = 0; i < boards.length; i++) {
                    boardPl[i] = new Board(boards[i]);
                }
                if (stack != null) {
                    for (int i = 0; i < stack.length; i++) {
                        stacks.add(Tiles.stringToTiles(stacks, stack[i]));
                        dominoStacks.add(new Domino(stacks.get(i)));
                     
                    }
                }
                stacks.clear();
                for (int i = 0; i < current.length; i++) {
                    if (current[i] == null) {
                        currentBox[i] = null;
                        stacks.add(null);
                    } else {
                        String value = "";
                        for (int j = 2; j < current[i].length(); j++) {
                            value = value + current[i].charAt(j);
                        }
                        stacks.add(Tiles.stringToTiles(stacks, value));
                        currentBox[i] = new Domino(stacks.get(i));
                        value = "";

                    }
                    if (currentBox[i] != null) {
                        // System.out.println(i +" current box: "+currentBox[i].toString());
                    }
                }
                stacks.clear();
                if (next != null) {
                    boolean chose = false;
                    for (int i = 0; i < next.length; i++) {
                        String value = "";
                        int plID = -1;
                        if (next[i] != null) {
                            for (int j = 2; j < next[i].length(); j++) {
                                value = value + next[i].charAt(j);
                                if (!chose) {
                                    plID = charToInt(next[i].charAt(j - 2));
                                    chose = true;
                                }
                            }
                        }
                        stacks.add(Tiles.stringToTiles(stacks, value));
                        nextBox[i] = new Domino(stacks.get(i));
                        nextChose[i] = plID;
                        value = "";
                        chose = false;
                        //System.out.println(i +" next box: "+nextBox[i].toString());
                    }

                }
                if (!emapyCurrent) {
                    GUItoGame newGame = new Game(this.gui, dominoStacks, currentBox, nextBox, boardPl, playerID, nextChose);
                    this.game = newGame;
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Empty Current");
                    alert.setHeaderText("The current box is empty");

                    alert.setContentText("The game was just initialezed before save.\n"
                            + "You can start the game by clicking on New Game button. ");

                    alert.showAndWait();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Wrong Format");
                alert.setHeaderText("File Format is Wrong");

                alert.setContentText("You passed wrong format of the file.\n"
                );

                alert.showAndWait();
                System.out.println("There should be only four boards.");
            }
        }
        return this.game;

    }

    private int charToInt(char c) {
        int id = -1;
        if (c != '-') {
            if (c == '0') {
                id = 0;
            }
            if (c == '1') {
                id = 1;
            }
            if (c == '2') {
                id = 2;
            }
            if (c == '3') {
                id = 3;
            }
        }
        return id;
    }

}
