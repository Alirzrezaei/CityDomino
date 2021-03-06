/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * this class is the main class in logic and an instance of this class in
 * created in gui and all parameters to make a game is passed here. Players are
 * being make in this class and their turn is being handled here. It receives
 * users actions from gui and with coordination of other logic classes is
 * controlling of the logics and pass the updates to gui to show it to user.
 *
 * @author Alireza
 */
public class Game implements GUItoGame {

    private Player[] player;
    private NextPlayer[] currentRoundPlayer;
    private NextPlayer[] nextRoundPlayer;
    private int currentPl;

    private Domino[] nextBox;
    private Domino[] currentBox;

    private Domino CurrentDomino;

    private GUIConnector gui;

    private List<Domino> stack;

    private IOCityDomino io;

    /**
     * the main constructor to initiate the game from scratch.
     *
     * @param gui
     * @param x dimension for board
     * @param y dimension for board
     * @param totalPlayer human and bot player
     * @param humanPlayer human player
     */
    public Game(GUIConnector gui, int x, int y, int totalPlayer, int humanPlayer) {

        this.gui = gui;
        player = new Player[totalPlayer];

        currentRoundPlayer = new NextPlayer[totalPlayer];
        nextRoundPlayer = new NextPlayer[totalPlayer];

        for (int i = 0; i < totalPlayer; i++) {
            if (i < humanPlayer) {
                this.player[i] = new Human(gui, "" + i, i, x, y);
            } else {
                this.player[i] = new Bot(gui, "" + i, i, x, y);
            }
        }

        for (int i = 0; i < currentRoundPlayer.length; i++) {
            currentRoundPlayer[i] = new NextPlayer(player[i]);
        }

        this.nextBox = new Domino[totalPlayer];
        this.currentBox = new Domino[totalPlayer];
        this.CurrentDomino = null;
        this.currentPl = 0;

        this.stack = new LinkedList<>();
        /*
        this.currentRoundPlayers = new int[totalPlayer];
        this.nextRoundPlayers = new int[totalPlayer];
        for (int i = 0; i < totalPlayer; i++) {
            this.currentRoundPlayers[i] = i;
            this.nextRoundPlayers[i] = -1;
        }*/
        deal(stack);
        this.gui.placeCityHall();
        this.io = new IOCityDomino("LogRecords.txt");
    }

    /**
     * constructor to test the game.
     *
     * @param gui
     * @param board
     */
//    public Game(GUIConnector gui, Board board) {
//        Player p1 = new Human("TestPayer", 0, board, CurrentDomino);
//        this.gui = gui;
//        this.stack = new LinkedList<>();
//    }
    Game(GUIConnector gui, List<Domino> stack, Domino[] currentBox, Domino[] nextBox, Board[] board, int[] playerID, int[] nextChose) {

        this.stack = stack;
        this.player = new Player[playerID.length];
        this.currentPl = 0;
        this.currentRoundPlayer = new NextPlayer[(playerID.length)];
        this.nextRoundPlayer = new NextPlayer[playerID.length];
        this.currentBox = currentBox;
        this.gui = gui;
        this.nextBox = nextBox;
        this.CurrentDomino = null;
        setToCurrentBox(CurrentDomino);
        
        System.out.print("here callsed");

        for (int i = 0; i < playerID.length; i++) {
            if (playerID[i] == 0) {
                Player pl = new Human(this.gui, "" + playerID[i], playerID[i], board[i], currentBox[i], nextBox[i]);
                this.currentRoundPlayer[i] = new NextPlayer(pl);
                
            } else {
                Player pl = new Bot(this.gui, "" + playerID[i], playerID[i], board[i], currentBox[i], nextBox[i]);
                this.currentRoundPlayer[i] = new NextPlayer(pl);
            }

            if (currentBox[i] == null) {
                currentPl++;
            }

            this.gui.updateGrid(currentRoundPlayer[i].getNextPlayer().getBoard(), currentRoundPlayer[i].getNextPlayer().getID());
            this.gui.setToNext(i, this.nextBox[i]);
            this.gui.setToCurrent(i, this.currentBox[i]);

        }
        for (int i = 0; i < player.length; i++) {
            for (int j = 0; j < currentRoundPlayer.length; j++) {
                if (currentRoundPlayer[j].getNextPlayer().getID() == i) {
                    player[i] = currentRoundPlayer[j].getNextPlayer();
                }
            }
        }
        for (int i = 0; i < nextChose.length; i++) {
            if (nextChose[i] != -1) {
                for (int j = 0; j < nextRoundPlayer.length; j++) {
                    if (currentRoundPlayer[j].getNextPlayer().getID() == nextChose[i]) {
                        nextRoundPlayer[i] = new NextPlayer(currentRoundPlayer[j].getNextPlayer());
                    }
                }
            } else {
                nextRoundPlayer[i] = null;
            }
        }
        this.io = new IOCityDomino("Log Records.txt");
    }

    /**
     * store the given domino in the given index and then update the GUI
     *
     * @param idx
     * @param domino
     */
    void setToNextBox(int idx, Domino domino) {
        this.nextBox[idx] = domino;

    }

    /**
     * the obtained domino from the giver stack is passed to set it to NextBox
     * array
     *
     * @param stack
     */
    void deal(List<Domino> stack) {
        stack = Domino.fill(stack);

        for (int i = 0; i < player.length; i++) {
            setToNextBox(i, getDomFromStack(stack));
        }
        sortNextBox();
    }

    void fillNextBox(List<Domino> stack) {

        for (int i = 0; i < player.length; i++) {
            setToNextBox(i, getDomFromStack(stack));       
        }
        sortNextBox();
    }

    /**
     * this method is sorting the next box based on the ordinal of the tile
     */
    private void sortNextBox() {
        Domino temp;

        for (int i = 0; i < nextBox.length; i++) {         
            for (int j = i; j < nextBox.length; j++) {
                if (nextBox[i].getTiles().ordinal() > nextBox[j].getTiles().ordinal()) {
                    temp = nextBox[j];
                    nextBox[j] = nextBox[i];
                    nextBox[i] = temp;
                }
            }
            gui.setToNext(i, nextBox[i]);       
        }
    }

    /**
     * get a domino from the stack randomly and delete it after return.
     *
     * @param stack
     * @return the random domino from stack and delete it from stack
     */
    Domino getDomFromStack(List<Domino> stack) {
        Random rand = new Random();
        return stack.size() > 0
                ? stack.remove(rand.nextInt(stack.size()))
                : null;
    }

    /**
     * this method is returning the array of cell from board class
     *
     * @param board
     * @return array of cells as card type
     */
    Cards[][] getBoard(Board board) {
        return board.getCells();
    }

    /**
     * this method is saving dominos of next box as tiles and return it
     *
     * @return next box as tiles
     */
    Tiles[] getNextBox() {
        Tiles[] result = new Tiles[player.length];

        for (int i = 0; i < player.length; i++) {
            result[i] = (nextBox[i].getTiles());
        }

        return result;
    }

    /**
     * this method is returning the array of player for next round.
     *
     * @return int array of next round player
     */
    // public int[] getNextRoundPlayers() {
    //  return this.nextRoundPlayers;
    //}
    /**
     * this method is setting the current domino and display it in GUI
     *
     * @param domino
     */
    private void setToCurrentBox(Domino domino) {

        this.gui.showInChooseBox(domino);

    }

    /**
     * this method is returning linked list of stack of value
     *
     * @return stack as list
     */
    List<Domino> getStack() {
        return stack;
    }

    /**
     * this method is starting the game by clicking on start game button in GUI
     */
    @Override
    public void start() {

        this.nextBox = new Domino[player.length];
        this.currentBox = new Domino[player.length];
        this.CurrentDomino = null;
        this.currentPl = 0;

        currentRoundPlayer = new NextPlayer[player.length];
        nextRoundPlayer = new NextPlayer[player.length];

        for (int i = 0; i < currentRoundPlayer.length; i++) {
            currentRoundPlayer[i] = new NextPlayer(player[i]);
            currentRoundPlayer[i].getNextPlayer().getBoard().clear();
            currentRoundPlayer[i].getNextPlayer().getBoard().setStarter();
            currentRoundPlayer[i].getNextPlayer().setNextDomino(null);
            currentRoundPlayer[i].getNextPlayer().setCrntDomino(null);
            this.gui.updateGrid(currentRoundPlayer[i].getNextPlayer().getBoard(), currentRoundPlayer[i].getNextPlayer().getID());
            this.gui.setToCurrent(i, null);
            this.gui.colorLabels(-1, i);
        }

        this.gui.showInChooseBox(CurrentDomino);
        deal(stack);
        //this.io = new IOCityDomino("Log Records.txt");
    }

    /**
     * this method is checking if the game is ended.
     *
     * @return true if the stack is empty
     */
    void end() {


        Result result = new Result(player);
        for(int i = 0; i < currentBox.length; i++){
            this.currentBox[i] = null;
            this.gui.setToCurrent(i, currentBox[i]);
            this.gui.colorLabels(-1, i);
        }
        this.gui.showResult(result);

        System.out.println("game is finished");

    }

    /**
     * this method is to increase the next player and do the necessary stop for
     * the next player to do
     */
    void nextPlayer() {

        if (stack.isEmpty() && currentPl == player.length - 1) {
            this.end();

        } else {
            currentPl++;

            if (currentPl == currentRoundPlayer.length) {
                currentPl = 0;

                currentRoundPlayer = nextRoundPlayer.clone();

                nextRoundPlayer = new NextPlayer[player.length];

                for (int i = 0; i < currentBox.length; i++) {
                    currentBox[i] = currentRoundPlayer[i].getNextPlayer().getNextDomino();

                }
                for (int i = 0; i < currentRoundPlayer.length; i++) {
                    if (currentRoundPlayer[i].getNextPlayer().getNextDomino() != null) {
                        //gui.setToCurrent(i, currentRoundPlayer[i].getNextPlayer().getNextDomino());
                        gui.setToCurrent(i, currentBox[i]);
                        gui.colorLabels(i, currentRoundPlayer[i].getNextPlayer().getID());
                    }
                }

                if (!this.stack.isEmpty()) {
                    fillNextBox(this.stack);
                } else {
                    nextBox = null;
                }

            }

            if (currentRoundPlayer[currentPl].getNextPlayer().isBot()) {
                botTurn();
            } 
        }

    }

    private void botTurn() {

        if (currentRoundPlayer[currentPl].getNextPlayer() == null) {

            nextPlayer();

        } else {
            currentRoundPlayer[currentPl].getNextPlayer().botTurn(nextBox, nextRoundPlayer);

            nextRoundPlayer[currentRoundPlayer[currentPl].getNextPlayer().nextBoxIndex()]
                    = new NextPlayer(currentRoundPlayer[currentPl].getNextPlayer());

            this.nextBox[currentRoundPlayer[currentPl].getNextPlayer().nextBoxIndex()] = null;
            gui.setToNext(currentRoundPlayer[currentPl].getNextPlayer().nextBoxIndex(), null);

            Pos fstPos = currentRoundPlayer[currentPl].getNextPlayer().getBoard().findPos(
                    currentRoundPlayer[currentPl].getNextPlayer().getCrntDomino());

            if (fstPos != null) {

                Pos sndPos = currentRoundPlayer[currentPl].getNextPlayer().getCrntDomino().getSnd(fstPos);

                currentRoundPlayer[currentPl].getNextPlayer().getBoard().lay(
                        currentRoundPlayer[currentPl].getNextPlayer().getCrntDomino(), fstPos);

                this.gui.showOnGrid(fstPos, currentRoundPlayer[currentPl].getNextPlayer().getCrntDomino().getFst(), sndPos,
                        currentRoundPlayer[currentPl].getNextPlayer().getCrntDomino().getSnd(),
                        currentRoundPlayer[currentPl].getNextPlayer().getID());
                this.io.logRecord(currentRoundPlayer[currentPl], 
                    currentRoundPlayer[currentPl].getNextPlayer().getCrntDomino(), fstPos);
            }
            
            nextPlayer();
        }
    }

    /**
     * this method is calling a save method in IOCityDomino class to save
     * parameters of the current game into a text file. This method itself is
     * called from GUI when user clicked on save button
     */
    @Override
    public void save() {

        io = new IOCityDomino();

        try {
            io.save(currentPl, currentRoundPlayer, nextRoundPlayer, nextBox, currentBox, stack);
        } catch (IOException ex) {
            System.out.println("Problem in save: " + ex);
        }

    }

    /**
     * This method is calling load method from IOCityDomino class and reads from
     * a text file. Then the parameters should be sent to a constructor with
     * parameters in String format
     */
    @Override
    public GUItoGame load(GUItoGame game) {
        io = new IOCityDomino();

        try {
            game = io.load(gui, game);
        } catch (IOException ex) {
            System.out.println("Problem in Load: " + ex);
        }
        return game;
    }

    /**
     * this method is called when the user clicked on the current box and
     * increase the rotation of the domino. then update the gui
     */
    @Override
    public void currentBoxClick() {
        
        if (CurrentDomino != null) {
            CurrentDomino.rotIncrement();
            this.setToCurrentBox(CurrentDomino);
        } 
    }

    /**
     * this method is called when the player clicked on the next box
     *
     * @param idx
     */
    @Override
    public void clickOnNext(int idx) {

        if ((this.nextBox[idx] != null && CurrentDomino == null)) {
            nextRoundPlayer[idx] = new NextPlayer(currentRoundPlayer[currentPl].getNextPlayer());

            gui.setToNext(idx, null);
            currentRoundPlayer[currentPl].getNextPlayer().setNextBoxIndex(idx);
            CurrentDomino = currentRoundPlayer[currentPl].getNextPlayer().getNextDomino();
            currentRoundPlayer[currentPl].getNextPlayer().setNextDomino(nextBox[idx]);
            nextBox[idx] = null;
            if (CurrentDomino == null) {
                this.nextPlayer();
            } else {

                currentRoundPlayer[currentPl].getNextPlayer().setCrntDomino(CurrentDomino);
                gui.showInChooseBox(CurrentDomino);
            }
        } else {
          
        }
    }
    
    /**
     * this method is for human and receive position from gui and check if the
     * human player domino fit in that positon
     *
     * @return true if the current domino is fit into the given position
     */
    @Override
    public boolean fits(Pos pos) {
        return currentRoundPlayer[currentPl].getNextPlayer().getBoard().fits(CurrentDomino, pos);
    }

    @Override
    public void setOnTheBoard(Pos posFst) {

        if (posFst != null) {
            Pos posSnd = currentRoundPlayer[currentPl].getNextPlayer().getCrntDomino().getSnd(posFst);

            this.currentRoundPlayer[currentPl].getNextPlayer().getBoard().lay(
                    currentRoundPlayer[currentPl].getNextPlayer().getCrntDomino(), posFst);

            this.gui.showOnGrid(posFst, currentRoundPlayer[currentPl].getNextPlayer().getCrntDomino().getFst(),
                    posSnd, currentRoundPlayer[currentPl].getNextPlayer().getCrntDomino().getSnd(),
                    currentRoundPlayer[currentPl].getNextPlayer().getID());
            
            this.io.logRecord(currentRoundPlayer[currentPl], CurrentDomino, posFst);
            
            this.CurrentDomino = null;
            this.setToCurrentBox(CurrentDomino);
        }

        nextPlayer();

    }

    /**
     * this method is discarding the current domino of the human player when he
     * presses the discard button.
     */
    @Override
    public void discardDomino() {
        if (CurrentDomino != null) {
            this.io.logRecord(currentRoundPlayer[currentPl], CurrentDomino, null);    
            this.CurrentDomino = null;
            this.gui.showInChooseBox(null);
            nextPlayer();
        }

    }

    @Override
    public void arrowKeysClick(char command) {
        
        currentRoundPlayer[currentPl].getNextPlayer().getBoard().moveCityCenter(command);
        gui.updateGrid(currentRoundPlayer[currentPl].getNextPlayer().getBoard(),
                currentRoundPlayer[currentPl].getNextPlayer().getID());
    }

}
