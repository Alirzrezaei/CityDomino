/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * This class is creating board for player and implement all things that is necessary to work with 
 * board. It finds position for given domino, lay the given domino in given position, find best position for 
 * bots' domino, check if the row are free to move the cards and calculate the point. 
 * @author Alireza
 */
public class Board {

    private Cards[][] cells;
    private Pos cityCenterPos;

    public final Cards E = Cards.E;
    public final Cards CC = Cards.CC;

    private GUIConnector gui;

    /*
    constructor to make a board with given dimensions
   
     */
    public Board(GUIConnector gui, int x, int y) {
        this.gui = gui;

        this.cells = new Cards[x][y];
        this.clear();

        setStarter();
    }

    /**
     * constructs the board given by string. The rows are seperated by "\n".
     * All columns have the same length, all rows have the same length. Used for testing and 
     * for loading a boards from a text file. It changes string values to card and store it 
     * into a array with type of Cards
     *
     * @param s
     * @param openEnds
     */
    Board(String s) {
        String[] lines = s.split("\n");
        int sizeX = lines[0].length();
        int j = (sizeX+1)/ 3;
        int counter = 0;
        int sizeY = lines.length;
        this.cells = new Cards[j][sizeY];
        
        char[][] testCells = new char[sizeX][sizeY];
        
        for (int y = 0; y < lines.length; y++) {
            testCells[y] = lines[y].toCharArray();
        }
        
        for (int y = 0; y < sizeY; y++) {
            
            for (int x = 0; x < sizeX; x = x+3) {
                
              
               String temp = ""+ testCells[y][x]+""+testCells[y][x+1];
               Cards value = Cards.stringToCard(temp);
               
               this.cells[counter][y] = value;
                counter++;
            }
            counter = 0;
        }

    }
    
    /**
     * this constructor is for testing.
     *
     * @param hCells
     * @param gui
     */
    Board(GUIConnector gui, Cards[][] cells) {
        this.cells = cells;
        this.gui = gui;
        //setStarter();
    }

    Board(Cards[][] tempCells) {
        this.cells = tempCells;
    }

    /**
     * return the board
     */
    Cards[][] getCells() {
        return this.cells;
    }

    /**
     * return the length of the first dimension of the board
     */
    public int getRows() {
        return cells.length;
    }

    /**
     * return the length of the second dimension of the board
     */
    public int getColumns() {
        return cells[0].length;
    }

    /**
     * this method clear all the cells to empty
     */
    public void clear() {
        for (int c = 0; c < getColumns(); c++) {
            for (int r = 0; r < getRows(); r++) {
                cells[c][r] = E;
            }
        }
    }

    /**
     * this method is checking if the given integer number is in the range of
     * first dimension of the board
     *
     * @param x
     * @return true if the given number is greater equal to 0 and less than
     * length of first dimension of the board
     */
    public boolean isValidX(int x) {
        return x >= 0 && x < cells.length;
    }

    /**
     * this method is checking if the given integer number is in the range of
     * second dimension of the board
     *
     * @param y
     * @return true if the given number is greater equal to 0 and less than
     * length of second dimension of the board
     */
    public boolean isValidY(int y) {
        return y >= 0 && y < cells[0].length;
    }

    /**
     * checks if the given position if valid position in the board
     *
     * @param p position
     * @return true if the position is in the board
     */
    public boolean isValidPosition(Pos p) {
        return isValidX(p.x()) && isValidY(p.y());
    }

    /**
     * check if the given position in the board is empty
     *
     * @param p position in the board
     * @return true if the given position if empty
     */
    public boolean isEmptyPos(Pos p) {
        return cells[p.x()][p.y()] == E;
    }

    /**
     * return the value of the cell at the point of x and y
     *
     * @param x X dimention
     * @param y Y dimention
     * @return the value of the cell as Card value
     */
    public Cards getCells(int x, int y) {
        assert isValidX(x) : "x is not valid";
        assert isValidY(y) : "y is not valid";

        return cells[x][y];
    }

    /**
     * this method returns the value of the cell at given position
     *
     * @param p position
     * @return value of the cell as Card value
     */
    public Cards getCells(Pos p) {
        return getCells(p.x(), p.y());
    }

    /**
     * this method is putting the city center in the middle of the board and
     * update the graphic with picture.
     */
    public void setStarter() {

        int x = (int) (cells.length / 2);
        int y = (int) (cells[0].length / 2);

        cityCenterPos = new Pos(x, y);
        cells[cityCenterPos.x()][cityCenterPos.y()] = CC;
    }

    /**
     * Check if the given domino and position is fitted in the board.
     *
     * @param domino
     * @param pFst
     * @return
     */
    public boolean fits(Domino domino, Pos pFst) {
        assert (isValidPosition(pFst)) : "the position is not valid";
        assert (domino != null) : "domino is null";

        Pos pSnd = domino.getSnd(pFst);
        Pos[] posNeighbors = touchCell(pFst);
        Pos[] posSndNeighbors = touchCell(pSnd);

        boolean isFit = false;

        if (isValidPosition(pSnd) && isValidPosition(pFst)) {
            if (isAtBank(pFst) && occupiedSide(pFst) == 3) {
                isFit = false;
            } else if (isCorner(pFst) && occupiedSide(pFst) == 2) {
                isFit = false;
            } else if (occupiedSide(pFst) == 4) {
                isFit = false;
            } else {

                if (isEmptyPos(pSnd) && isEmptyPos(pFst)) {

                    for (int i = 0; posNeighbors != null && i < posNeighbors.length && !isFit; i++) {
                        if (cells[posNeighbors[i].x()][posNeighbors[i].y()].getType() == CC.getType()
                                || domino.getFst().getType() == cells[posNeighbors[i].x()][posNeighbors[i].y()].getType()) {
                            isFit = true;
                        }
                    }
                    for (int j = 0; posSndNeighbors != null && j < posSndNeighbors.length && !isFit; j++) {
                        if (cells[posSndNeighbors[j].x()][posSndNeighbors[j].y()].getType() == CC.getType()
                                || domino.getSnd().getType() == cells[posSndNeighbors[j].x()][posSndNeighbors[j].y()].getType()) {
                            isFit = true;
                        }
                    }
                }

            }
        }
        return isFit;
    }

    /**
     * return number of occupied sides of the given position
     *
     * @param p
     * @return
     */
    private int occupiedSide(Pos p) {
        assert (p != null) : "position in occupied sides is null";
//  

        Pos[] neighbors = p.getNeigbors();
        int sumOfOccupied = 0;

        for (Pos neighbor : neighbors) {
            if (isValidPosition(neighbor) && !isEmptyPos(neighbor)) {
                sumOfOccupied++;
            }
        }
        return sumOfOccupied;
    }

    /**
     * return the positions of the touched cells for given position
     *
     * @param p
     * @return
     */
    private Pos[] touchCell(Pos p) {
        assert (p != null) : "position in touchcell is null";

        //   assert(isValidPosition(p));
        Pos[] positions = new Pos[occupiedSide(p)];
        Pos[] neighbors = p.getNeigbors();
        int j = 0;
        for (int i = 0; i < neighbors.length; i++) {
            if (isValidPosition(neighbors[i]) && !isEmptyPos(neighbors[i])) {
                positions[j] = neighbors[i];
                j++;
            }

        }

        if (positions.length > 0) {
            return positions;
        } else {
            return null;
        }
    }

    /**
     * this method is laying the given domino in the given position and update
     * graphic with this Domino.
     *
     * @param domino
     * @param pFst
     */
    public void lay(Domino domino, Pos pFst) {
        assert (domino != null) : "domino in lay is null";

        Pos pSnd = domino.getSnd(pFst);

        this.cells[pFst.x()][pFst.y()] = domino.getFst();
        this.cells[pSnd.x()][pSnd.y()] = domino.getSnd();

        // update GUI
    }

    /**
     * this method is receives a domino and try to find a position for that.
     *
     * @param domino
     * @return
     */
    public Pos findPos(Domino domino) {
        boolean found = false;
        Pos pos = null;
        Pos tempPos = null;
        int rotation = 0;

        int point = 0;
        int calcPoint = 0;
        if (domino != null) {
            for (int i = 0; i < getRows(); i++) {
                for (int j = 0; j < getColumns(); j++) {
                    tempPos = new Pos(j, i);
                    for (int k = 0; k < 4; k++) {
                        found = fits(domino, tempPos);
                        if (found) {
                            calcPoint = CalcPointForPos(domino, tempPos);
                            
                            if (calcPoint > point) {
                                if(pos == null){
                                point = calcPoint;
                                pos = new Pos(tempPos.x(), tempPos.y());
                                rotation = domino.getRotation();
                                }else{
                                    if(!oneCellFree(domino, tempPos)){
                                        point = calcPoint;
                                        pos = new Pos(tempPos.x(), tempPos.y());
                                        rotation = domino.getRotation();
                                    }
                                }
                            }
                            else{
                                if(pos == null && calcPoint == point){
                                    pos = new Pos(tempPos.x(), tempPos.y());
                                    rotation = domino.getRotation();
                                }
                            }

                        }                 
                        domino.rotIncrement();             
                    }
                }
            }
        }
        int test = 0;
        while (domino != null && rotation != domino.getRotation()) {
            domino.rotIncrement();
            test++;
        }

        return pos != null ? pos : null;
    }

    /**
     * this method receives a domino and a position as parameters and calculate
     * the points of the copied board with given parameters.
     *
     * @param domino
     * @param posFst
     * @return integer in calculated point of a given domino and given position
     */
    private int CalcPointForPos(Domino domino, Pos posFst) {

        Cards[][] temp = new Cards[this.cells.length][];

        for (int i = 0; i < this.cells.length; i++) {
            temp[i] = this.cells[i].clone();
        }
        Board tempBoard = new Board(temp);
        int points = 0;

        tempBoard.lay(domino, posFst);

        points = tempBoard.CalcPoints();

        return points;
    }

    private boolean oneCellFree(Domino domino, Pos posFst) {

        Cards[][] temp = new Cards[this.cells.length][];

        for (int i = 0; i < this.cells.length; i++) {
            temp[i] = this.cells[i].clone();
        }
        boolean isLoneCell = false;
        Board tempBoard = new Board(temp);
        tempBoard.lay(domino, posFst);

        Pos posSnd = domino.getSnd(posFst);

        Pos[] posFstNeighbors = posFst.getNeigbors();
        Pos[] posSndNeighbors = posSnd.getNeigbors();
        
        for (int i = 0; i < posFstNeighbors.length && !isLoneCell; i++) {
            if (tempBoard.isValidPosition(posFstNeighbors[i])) {
                if (tempBoard.isCorner(posFstNeighbors[i]) && tempBoard.isEmptyPos(posFstNeighbors[i]) && tempBoard.occupiedSide(posFstNeighbors[i]) == 2) {
                    isLoneCell = true;
                } else if (tempBoard.isAtBank(posFstNeighbors[i]) && tempBoard.isEmptyPos(posFstNeighbors[i]) && tempBoard.occupiedSide(posFstNeighbors[i]) == 3) {
                    isLoneCell = true;
                } else {
                    int occupied = tempBoard.occupiedSide(posFstNeighbors[i]);
                    if (tempBoard.isEmptyPos(posFstNeighbors[i]) && occupied == 4) {
                        isLoneCell = true;
                    }
                }
            }
        }
        for (int i = 0; i < posSndNeighbors.length && !isLoneCell; i++) {
            if (tempBoard.isValidPosition(posSndNeighbors[i])) {
                if (tempBoard.isCorner(posSndNeighbors[i]) && tempBoard.isEmptyPos(posSndNeighbors[i]) && tempBoard.occupiedSide(posSndNeighbors[i]) == 2) {
                    isLoneCell = true;
                } else if (tempBoard.isAtBank(posSndNeighbors[i]) && tempBoard.isEmptyPos(posSndNeighbors[i]) && tempBoard.occupiedSide(posSndNeighbors[i]) == 3) {
                    isLoneCell = true;
                } else {
                    if (tempBoard.isEmptyPos(posSndNeighbors[i]) && tempBoard.occupiedSide(posSndNeighbors[i]) == 4) {
                        isLoneCell = true;
                    }
                }
            }
        }

        return isLoneCell;
    }

    /**
     * checks if the given position is at the corner of the board
     *
     * @param pos
     * @return true if position is at the corner of board
     */
    private boolean isCorner(Pos pos) {
        if (pos.x() == 0 && pos.y() == 0) {
            return true;
        } else if (pos.x() == this.cells.length - 1 && pos.y() == this.cells.length - 1) {
            return true;
        } else if (pos.x() == 0 && pos.y() == this.cells.length - 1) {
            return true;
        } else if (pos.x() == this.cells.length - 1 && pos.y() == 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * this method is checking if the position is at the bank of the board
     *
     * @param pos
     * @return true if the position is at the bank
     */
    private boolean isAtBank(Pos pos) {
        return (pos.x() == 0 || pos.y() == 0 || pos.x() == this.cells.length - 1
                || pos.y() == this.cells.length - 1) && (!isCorner(pos));
    }

    /**
     * this method is calculating all the point and return it
     *
     * @return integer all the points
     */
    public int CalcPoints() {
        Pos[][] posRegions = this.PosOfRegions();

        int points = 0;
        int sum = 0;
        int prestige = 0;

       
        if (posRegions != null) {
            for (int i = 0; i < posRegions.length; i++) {
                for (int j = 0; j < posRegions[i].length; j++) {
                    sum++;
                    prestige = prestige + this.cells[posRegions[i][j].x()][posRegions[i][j].y()].getPoint();
                }
                points = points + (sum * prestige);
                sum = 0;
                prestige = 0;
            }
        }

        return points;
    }
    
    public String CalcPointsFinal(String name) {
        Pos[][] posRegions = this.PosOfRegions();

        int points = 0;
        int sum = 0;
        int prestige = 0;
        String s  = "";
        if (posRegions != null) {

            s = s + name +"\n";
            for (int i = 0; i < posRegions.length; i++) {
 
                s = s + "region: "+ i + " " +this.cells[posRegions[i][0].x()][posRegions[i][0].y()].getType()+ ":: ";
                for (int j = 0; j < posRegions[i].length; j++) {

               
                    if(j < posRegions[i].length -1){
                        s = s + this.cells[posRegions[i][j].x()][posRegions[i][j].y()]+", ";
                    }else{
                        s = s + this.cells[posRegions[i][j].x()][posRegions[i][j].y()];
                    }
                }
                s = s + "\n";
               
            }
        }
        
        if (posRegions != null) {
            for (int i = 0; i < posRegions.length; i++) {
                for (int j = 0; j < posRegions[i].length; j++) {
                    sum++;
                    prestige = prestige + this.cells[posRegions[i][j].x()][posRegions[i][j].y()].getPoint();
                }
                points = points + (sum * prestige);
                sum = 0;
                prestige = 0;
            }
        }
        System.out.println(s);
        return s;
    }
    /**
     * this method is storing of positions of the all regions
     *
     * @return Position of all regions
     */
    private Pos[][] PosOfRegions() {
        Pos[][] pos = null;
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells[i].length; j++) {
                Pos tempPos = new Pos(i, j);
                if (!isEmptyPos(tempPos) && this.cells[i][j] != CC) {
                    pos = addPosition(pos, tempPos);
                }
            }
        }
        
        if(pos != null){
            for(int i = 0; i < pos.length -1; i++){
                for(int j = i+1; j < pos.length; j++){
                    if(this.cells[pos[i][0].x()][pos[i][0].y()].getType() == 
                            this.cells[pos[j][0].x()][pos[j][0].y()].getType()){
                        pos = checkJointArrays(pos, pos[i], pos[j], i, j);
                    }
                }
            }
        }
       
        return pos;
    }
    
    private Pos[][] checkJointArrays(Pos[][] positions, Pos[] firstArray, Pos[] secondArray, int first, int second){
        Pos[][] tempPosition = new Pos[1][];
        
        boolean combined = false;
        for(int i = 0; i < firstArray.length && !combined; i++){
            for(int j = 0; j < secondArray.length && !combined; j++){
          
                if(firstArray[i].isNextPos(secondArray[j])){
                    
                    firstArray = combineArrays(firstArray, secondArray).clone();
                    combined = true;
                    positions[first] = firstArray.clone();
                    positions = deleteArray(positions, second);
                }
            }
        }
        
        return positions;
    }
    private Pos[] combineArrays(Pos[] firstArray, Pos[] secondArray){
        Pos[] tempArray = new Pos[firstArray.length + secondArray.length];
        
        int j = 0;
        int k = 0;
        for(int i = 0; i < tempArray.length; i++){
            if(i < firstArray.length){
                tempArray[i] = firstArray[j];
                j++;
            }else{
                tempArray[i] = secondArray[k];
                k++;
            }
        }
        return tempArray;
    }
    private Pos[][] deleteArray(Pos[][] position, int index){
        Pos[][] tempPositions = new Pos[position.length-1][];
        
        int k = 0;
        
        for(int i = 0; i < tempPositions.length; i++){
            if(k == index){
                k++;     
            }
            tempPositions[i] = position[k].clone();
            k++;
            
        }
        return tempPositions;
    }
    /**
     * this method gets a array of 2-dimension and a position as parameters and
     * added the given position into the array.
     *
     * @param positions
     * @param position
     * @return array of all position of regions
     */
    private Pos[][] addPosition(Pos[][] positions, Pos position) {
        Pos[][] pos = null;
        boolean isAdded = false;
        if (positions == null) {
            positions = new Pos[][]{{new Pos(position.x(), position.y())}};    
        } else {
            for (int i = 0; i < positions.length && !isAdded; i++) {
                for (int j = 0; j < positions[i].length && !isAdded; j++) {
                    if (this.cells[position.x()][position.y()].getType()
                            == this.cells[positions[i][j].x()][positions[i][j].y()].getType()) {
                        if (position.isNextPos(positions[i][j])) {
                            positions[i] = addPosition(positions[i], position).clone();
                            isAdded = true;
                        }
                    }
                }
            }
            if (!isAdded) {
                pos = new Pos[positions.length + 1][];
                for (int i = 0; i < positions.length; i++) {
                    pos[i] = positions[i].clone();
                }
                pos[positions.length] = addPosition(pos[positions.length], position).clone();
                return pos;
            }
        }

        return positions;
    }

    /**
     * add position to a array of positions.
     *
     * @param positions
     * @param position
     * @return array of positions
     */
    private Pos[] addPosition(Pos[] positions, Pos position) {
        Pos[] pos = null;
        if (positions != null) {
            pos = new Pos[positions.length + 1];
        } else {
            pos = new Pos[1];
        }

        if (pos.length == 1) {
            pos[0] = position;
        } else {
            for (int i = 0; i < positions.length; i++) {
                pos[i] = positions[i];
            }
            pos[positions.length] = position;
        }
        return pos;
    }

    /**
     *
     * @return String the board and it values
     */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells[i].length; j++) {
                if(cells[j][i] == E){
                    s = s + "-- ";
                }else{
                    s = s +  cells[j][i].toString() + " ";
                }
            }
            s = s + "\n";
        }
        return s;
    }
    
    public void moveCityCenter(char command){
        if(command == 'u'){
            if(canMove(command)){
                for(int i = 0; i < this.cells.length; i++){
                    for(int j = 0; j < this.cells[i].length-1; j++){
                        cells[i][j] = cells[i][j+1];
                        cells[i][j+1] = E;
                    }
                }
            }else System.out.println("cells can not move up.");
        }else if(command == 'r'){
            if(canMove(command)){
                for(int i = 4; i >= 1; i--){
                    for(int j = 0; j < this.cells[i].length; j++){
                        cells[i][j] = cells[i-1][j];
                        cells[i-1][j] = E;
                    }
                }
            }else System.out.println("cells can not move right.");
        }else if(command == 'd'){
            if(canMove(command)){
                for(int i = 0; i < this.cells.length; i++){
                    for(int j = 4; j >=  1; j--){
                        cells[i][j] = cells[i][j-1];
                        cells[i][j-1] = E;
                    }
                }
            }else System.out.println("cells can not move down.");
        }else if(command == 'l'){
            if(canMove(command)){
                for(int i = 0; i < this.cells.length-1; i++){
                    for(int j = 0; j < this.cells[i].length; j++){
                        cells[i][j] = cells[i+1][j];
                        cells[i+1][j] = E;
                    }
                }
            }else System.out.println("cells can not move left.");
        }else{System.out.println("Wrond command was sent to move.");}
    }
    public boolean canMove(char command){
        boolean move = false; 
        
        if(command == 'u'){

        if(this.cells[0][0] == E && this.cells[1][0] == E && this.cells[2][0] == E 
                && this.cells[3][0] == E && this.cells[4][0] == E){
            move = true;
        }
        }
        if(command == 'd'){
            if(this.cells[0][4] == E && this.cells[1][4] == E && this.cells[2][4] == E 
                && this.cells[3][4] == E && this.cells[4][4] == E){
            move = true;
        }
        }
        if(command == 'r'){
            if(this.cells[4][0] == E && this.cells[4][1] == E && this.cells[4][2] == E 
                && this.cells[4][3] == E && this.cells[4][4] == E){
            move = true;
        }
        }
        if(command == 'l'){
            if(this.cells[0][0] == E && this.cells[0][1] == E && this.cells[0][2] == E 
                && this.cells[0][3] == E && this.cells[0][4] == E){
            move = true;
        }
        }
        
        return move;
    }
    //for test
    public int testOccupiedSide(Pos pos) {
        return occupiedSide(pos);
    }

    //for test
    public Pos[] testTouchedCells(Pos pos) {
        return touchCell(pos);
    }

    //for test
    public boolean testIsCorner(Pos pos) {
        return isCorner(pos);
    }

    //for test
    public boolean testIsAtBank(Pos pos) {
        return isAtBank(pos);
    }

    //for test
    public boolean testOneCellFree(Domino domino, Pos posFst) {
        return oneCellFree(domino, posFst);
    }
    
}
