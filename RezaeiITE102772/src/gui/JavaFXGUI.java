/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static java.awt.Color.red;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.*;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import logic.Board;
import logic.Cards;
import logic.Domino;
import logic.GUIConnector;
import logic.Pos;
import logic.Result;

/**
 * This class in for updating any changes of the game in the logic package. an instance of JavaFXGUI is
 * created in FXMLDocumentController and all necessary components are passed to JavaFXGUI constructor. 
 * When a changes happened in game of logic package, the updates are being sent to here via GUIConnector interface as
 * this class implements abstract method of GUIConnector interface. 
 * @author Alireza
 */
public class JavaFXGUI implements GUIConnector {

    Random r = new Random();

    private Label lbl0;
    private Label lbl1;
    private Label lbl2;
    private Label lbl3;

    private Pane pnCurrent;
    private ImageView[] imgCurrent;

    private ImageView[][] imgVwsNext;
    private ImageView[][] imgVwsCurrent;
    private ImageView[][] imgVewsGame; // for humman
    private ImageView[][] imgVwsAI1;
    private ImageView[][] imgVwsAI2;
    private ImageView[][] imgVwsAI3;

    private static int IMG_COUNT = 25;
    private Image[] imgs;

    private Domino currentDomino = null;

    protected static final Image EMPTY_IMG = new Image("gui/img/Empty.png");
    protected static final Image CITYHALL_IMG = new Image("gui/img/CityHall_Empty.png");

    public JavaFXGUI(Pane pnCurrent, ImageView[][] imgVwsGame, ImageView[][] imgVwsAI1, ImageView[][] imgVwsAI2,
            ImageView[][] imgVwsAI3, ImageView[][] imgVwsNext, ImageView[][] imgVwsCurrent,
            Label lbl0, Label lbl1, Label lbl2, Label lbl3) {

        this.lbl0 = lbl0;
        this.lbl1 = lbl1;
        this.lbl2 = lbl2;
        this.lbl3 = lbl3;

        this.imgVewsGame = imgVwsGame; // for Human field
        this.imgVwsNext = imgVwsNext;
        this.imgVwsCurrent = imgVwsCurrent;

        this.imgVwsAI1 = imgVwsAI1;
        this.imgVwsAI2 = imgVwsAI2;
        this.imgVwsAI3 = imgVwsAI3;

        this.pnCurrent = pnCurrent;
        

        imgs = new Image[IMG_COUNT];
        
        for (Cards card : Cards.values()) {
            if (card.ordinal() <= 23) {
                this.imgs[card.ordinal()] = this.loadImage(card);
            }
        }

    }

    private void emptyGridGame() {
        for (int i = 0; i < imgVewsGame.length; i++) {
            for (int j = 0; j < imgVewsGame[i].length; j++) {
                this.imgVewsGame[i][j].setImage(EMPTY_IMG);
            }
        }
    }

    private Image loadImage(Cards card) {
        Image img = null;
        switch (card) {
            case A0:
                img = new Image("gui/img/Amusement_0.png");
                break;
            case A1:
                img = new Image("gui/img/Amusement_1.png");
                break;
            case A2:
                img = new Image("gui/img/Amusement_2.png");
                break;
            case A3:
                img = new Image("gui/img/Amusement_3.png");
                break;
            case H0:
                img = new Image("gui/img/Home_0.png");
                break;
            case H1:
                img = new Image("gui/img/Home_1.png");
                break;
            case H2:
                img = new Image("gui/img/Home_2.png");
                break;
            case H3:
                img = new Image("gui/img/Home_3.png");
                break;
            case I0:
                img = new Image("gui/img/Industry_0.png");
                break;
            case I1:
                img = new Image("gui/img/Industry_1.png");
                break;
            case I2:
                img = new Image("gui/img/Industry_2.png");
                break;
            case I3:
                img = new Image("gui/img/Industry_3.png");
                break;
            case O0:
                img = new Image("gui/img/Office_0.png");
                break;
            case O1:
                img = new Image("gui/img/Office_1.png");
                break;
            case O2:
                img = new Image("gui/img/Office_2.png");
                break;
            case O3:
                img = new Image("gui/img/Office_3.png");
                break;
            case P0:
                img = new Image("gui/img/Park_0.png");
                break;
            case P1:
                img = new Image("gui/img/Park_1.png");
                break;
            case P2:
                img = new Image("gui/img/Park_2.png");
                break;
            case P3:
                img = new Image("gui/img/Park_3.png");
                break;
            case S0:
                img = new Image("gui/img/Shopping_0.png");
                break;
            case S1:
                img = new Image("gui/img/Shopping_1.png");
                break;
            case S2:
                img = new Image("gui/img/Shopping_2.png");
                break;
            case S3:
                img = new Image("gui/img/Shopping_3.png");
                break;
            default:
                img = EMPTY_IMG;
        }
        return img;
    }

    
 

    private ColumnConstraints getColConstraints() {
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(100);
        column.setHgrow(Priority.ALWAYS);
        column.setMinWidth(10.0);
        return column;
    }

    /**
     * Creates a new row constraints for a grid pane. The row has always the
     * height of 100%.
     *
     * @return new row constraints for grid pane
     */
    private RowConstraints getRowConstraints() {
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(100);
        row.setVgrow(Priority.ALWAYS);
        row.setMinHeight(10.0);
        return row;
    }

    public void setImageOnBoard(int y, int x) {
        imgVewsGame[y][x].setImage(imgCurrent[0].getImage());
        
    }

    public ImageView[] getCurrent() {
        return imgCurrent;
    }

    public void placeCityHall(GridPane gridPanes) {
        int colcount = gridPanes.getColumnConstraints().size();
        int rowcount = gridPanes.getRowConstraints().size();

        ImageView imgVwsCityHall = new ImageView(JavaFXGUI.CITYHALL_IMG);
        gridPanes.add(imgVwsCityHall, 2, 2);

        imgVwsCityHall.fitWidthProperty().bind(gridPanes.widthProperty().divide(colcount).subtract(gridPanes.getHgap()));
        imgVwsCityHall.fitHeightProperty().bind(gridPanes.heightProperty().divide(rowcount).subtract(gridPanes.getVgap()));

    }

    @Override
    public void setToNext(int index, Domino domino) {

        
        if (domino != null) {
            if (index >= 0 && index < this.imgVwsNext[0].length) {
                this.imgVwsNext[0][index].setImage(this.imgs[domino.getFst().ordinal()]);

                this.imgVwsNext[1][index].setImage(this.imgs[domino.getSnd().ordinal()]);
            }
        } else {
            this.imgVwsNext[0][index].setImage(EMPTY_IMG);

            this.imgVwsNext[1][index].setImage(EMPTY_IMG);
        }

    }
    /**
     * this method is receiving an index and a domino and set the domino in given index
     * @param index index of image view current
     * @param domino domino to put 
     */
    @Override
    public void setToCurrent(int index, Domino domino) {

        
        if (domino != null) {
            if (index >= 0 && index < this.imgVwsCurrent[0].length) {

                this.imgVwsCurrent[0][index].setImage(
                        this.imgs[domino.getFst().ordinal()]);

                this.imgVwsCurrent[1][index].setImage(
                        this.imgs[domino.getSnd().ordinal()]);
            }
        } else {
            if (index >= 0 && index < this.imgVwsCurrent[0].length) {

                this.imgVwsCurrent[0][index].setImage(EMPTY_IMG);

                this.imgVwsCurrent[1][index].setImage(EMPTY_IMG);
            }
        }

    }

    @Override
    public void showInChooseBox(Domino domino) {
        this.currentDomino = domino;
        this.pnCurrent.getChildren().clear();

        if (domino != null) {
            int idxFst = 0;
            int idxSnd = 1;

            ImageView[] imgVwsSelected = new ImageView[]{new ImageView(), new ImageView()};

            GridPane grdPn = new GridPane();
            grdPn.getColumnConstraints().add(this.getColConstraints());
            grdPn.getRowConstraints().add(this.getRowConstraints());

            if (domino.getRotation() % 2 == 0) {
                grdPn.getColumnConstraints().add(getColConstraints());

                for (int i = 0; i <= 1; ++i) {
                    grdPn.add(imgVwsSelected[i], i, 0);
                    imgVwsSelected[i].fitWidthProperty().bind(grdPn.widthProperty().divide(2));
                    imgVwsSelected[i].fitHeightProperty().bind(grdPn.heightProperty());
                }
                setPnSelectedHorizontal();
            } else {
                grdPn.getRowConstraints().add(this.getRowConstraints());

                for (int i = 0; i <= 1; ++i) {
                    grdPn.add(imgVwsSelected[i], 0, i);
                    imgVwsSelected[i].fitWidthProperty().bind(grdPn.widthProperty());
                    imgVwsSelected[i].fitHeightProperty().bind(grdPn.heightProperty().divide(2));
                }
                setPnSelectedVertical();
            }

            this.pnCurrent.getChildren().add(grdPn);
            grdPn.prefWidthProperty().bind(pnCurrent.widthProperty());
            grdPn.prefHeightProperty().bind(pnCurrent.heightProperty());

            imgVwsSelected[idxFst].setImage(imgs[domino.getFst().ordinal()]);
            imgVwsSelected[idxSnd].setImage(imgs[domino.getSnd().ordinal()]);
        }
       
    }

    /**
     * Changes the properties of the pane where the selected domino is presented
     * to present a horizontal domino.
     */
    private void setPnSelectedHorizontal() {
        this.pnCurrent.setLayoutX(17.0);
        this.pnCurrent.setLayoutY(44.0);
        this.pnCurrent.setPrefHeight(42.0);
        this.pnCurrent.setPrefWidth(72.0);

        AnchorPane.setTopAnchor(pnCurrent, 40.0);
        AnchorPane.setBottomAnchor(pnCurrent, 27.0);
        AnchorPane.setLeftAnchor(pnCurrent, 21.0);
        AnchorPane.setRightAnchor(pnCurrent, 26.0);
    }

    /**
     * Changes the properties of the pane where the selected domino is presented
     * to present a vertical domino.
     */
    private void setPnSelectedVertical() {
        this.pnCurrent.setLayoutX(45.0);
        this.pnCurrent.setLayoutY(21.0);
        this.pnCurrent.setPrefHeight(71.0);
        this.pnCurrent.setPrefWidth(42.0);

        AnchorPane.setTopAnchor(pnCurrent, 29.0);
        AnchorPane.setBottomAnchor(pnCurrent, 10.0);
        AnchorPane.setLeftAnchor(pnCurrent, 40.0);
        AnchorPane.setRightAnchor(pnCurrent, 45.0);

    }
    /**
     * this method receives the board and id of a player and update the respective image view
     * of the board and show it in gui
     * @param board of player
     * @param id of player
     */
    @Override
    public void updateGrid(Board board, int id) {
        if (id == 0) {
            for (int i = 0; i < board.getColumns(); i++) {
                for (int j = 0; j < board.getRows(); j++) {
                    if (board.getCells(i, j) == board.E) {
                        this.imgVewsGame[i][j].setImage(EMPTY_IMG);
                    } else if (board.getCells(i, j) == board.CC) {
                        this.imgVewsGame[i][j].setImage(CITYHALL_IMG);
                    } else {
                        imgVewsGame[i][j].setImage(imgs[board.getCells(i, j).ordinal()]);
                    }
                }
            }
        }else if(id == 1){
            for (int i = 0; i < board.getColumns(); i++) {
                for (int j = 0; j < board.getRows(); j++) {
                    if (board.getCells(i, j) == board.E) {
                        this.imgVwsAI1[i][j].setImage(EMPTY_IMG);
                    } else if (board.getCells(i, j) == board.CC) {
                        this.imgVwsAI1[i][j].setImage(CITYHALL_IMG);
                    } else {
                        imgVwsAI1[i][j].setImage(imgs[board.getCells(i, j).ordinal()]);
                    }
                }
            }
        }else if(id == 2){
            for (int i = 0; i < board.getColumns(); i++) {
                for (int j = 0; j < board.getRows(); j++) {
                    if (board.getCells(i, j) == board.E) {
                        this.imgVwsAI2[i][j].setImage(EMPTY_IMG);
                    } else if (board.getCells(i, j) == board.CC) {
                        this.imgVwsAI2[i][j].setImage(CITYHALL_IMG);
                    } else {
                        imgVwsAI2[i][j].setImage(imgs[board.getCells(i, j).ordinal()]);
                    }
                }
            }
        }else if(id == 3){
            for (int i = 0; i < board.getColumns(); i++) {
                for (int j = 0; j < board.getRows(); j++) {
                    if (board.getCells(i, j) == board.E) {
                        this.imgVwsAI3[i][j].setImage(EMPTY_IMG);
                    } else if (board.getCells(i, j) == board.CC) {
                        this.imgVwsAI3[i][j].setImage(CITYHALL_IMG);
                    } else {
                        imgVwsAI3[i][j].setImage(imgs[board.getCells(i, j).ordinal()]);
                    }
                }
            }
        }
        

    }

    /**
     * show the give first and second cards into first and second positions
     *
     * @param fstPos
     * @param fstValue
     * @param sndPos
     * @param sndValue
     */
    @Override
    public void showOnGrid(Pos fstPos, Cards fstValue, Pos sndPos, Cards sndValue, int id) {
        if (id == 0) {
   
            imgVewsGame[fstPos.x()][fstPos.y()].setImage(imgs[fstValue.ordinal()]);
            imgVewsGame[sndPos.x()][sndPos.y()].setImage(imgs[sndValue.ordinal()]);
        }
        if (id == 1) {
           
            imgVwsAI1[fstPos.x()][fstPos.y()].setImage(imgs[fstValue.ordinal()]);
            imgVwsAI1[sndPos.x()][sndPos.y()].setImage(imgs[sndValue.ordinal()]);
        }
        if (id == 2) {
           
            imgVwsAI2[fstPos.x()][fstPos.y()].setImage(imgs[fstValue.ordinal()]);
            imgVwsAI2[sndPos.x()][sndPos.y()].setImage(imgs[sndValue.ordinal()]);
        }
        if (id == 3) {
          
            imgVwsAI3[fstPos.x()][fstPos.y()].setImage(imgs[fstValue.ordinal()]);
            imgVwsAI3[sndPos.x()][sndPos.y()].setImage(imgs[sndValue.ordinal()]);
        }
    }
    /**
     * this method gets an instance of the result class and show them on a pop windows
     * @param result 
     */
    @Override
    public void showResult(Result result) {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Results");
        alert.setHeaderText("Winner: "+ result.getWinner() + " point " +result.getWinnerPoint()  );
        TextArea area = new TextArea("baords:\n" +result.baords(result.getPlayer())+ "\n -----------------------\n");
        area.setWrapText(true);
        area.setEditable(false);
        alert.getDialogPane().setExpandableContent(area);
        alert.showAndWait();

    }
    /**
     * this method place city hall image at middle of the game 
     */
    @Override
    public void placeCityHall() {
      
        imgVewsGame[2][2].setImage(CITYHALL_IMG);
        imgVwsAI1[2][2].setImage(CITYHALL_IMG);
        imgVwsAI2[2][2].setImage(CITYHALL_IMG);
        imgVwsAI3[2][2].setImage(CITYHALL_IMG);
    }
    /**
     * 
     * @param index
     * @param playerID 
     */
    @Override
    public void colorLabels(int index, int playerID) {
        int color = 0;

        if (playerID == 0) {
            if (index == 0) {
                this.lbl0.setStyle("-fx-background-color: green;");
            } else if (index == 1) {
                this.lbl1.setStyle("-fx-background-color: green;");
            } else if (index == 2) {
                this.lbl2.setStyle("-fx-background-color: green;");
            } else if (index == 3) {
                this.lbl3.setStyle("-fx-background-color: green;");
            } else {
                this.lbl0.setStyle("-fx-background-color: gray;");
                System.out.println("Wrong index is passed for label coloring.");
            }
        } else if (playerID == 1) {
            if (index == 0) {
                this.lbl0.setStyle("-fx-background-color: red;");
            } else if (index == 1) {
                this.lbl1.setStyle("-fx-background-color: red;");
            } else if (index == 2) {
                this.lbl2.setStyle("-fx-background-color: red;");
            } else if (index == 3) {
                this.lbl3.setStyle("-fx-background-color: red;");
            } else {
                this.lbl1.setStyle("-fx-background-color: gray;");
                System.out.println("Wrong index is passed for label coloring.");
            }
        } else if (playerID == 2) {
            if (index == 0) {
                this.lbl0.setStyle("-fx-background-color: blue;");
            } else if (index == 1) {
                this.lbl1.setStyle("-fx-background-color: blue;");
            } else if (index == 2) {
                this.lbl2.setStyle("-fx-background-color: blue;");
            } else if (index == 3) {
                this.lbl3.setStyle("-fx-background-color: blue;");
            } else {
                this.lbl2.setStyle("-fx-background-color: gray;");
                System.out.println("Wrong index is passed for label coloring.");
            }
        } else if (playerID == 3) {
            if (index == 0) {
                this.lbl0.setStyle("-fx-background-color: yellow;");
            } else if (index == 1) {
                this.lbl1.setStyle("-fx-background-color: yellow;");
            } else if (index == 2) {
                this.lbl2.setStyle("-fx-background-color: yellow;");
            } else if (index == 3) {
                this.lbl3.setStyle("-fx-background-color: yellow;");
            } else {
                this.lbl3.setStyle("-fx-background-color: gray;");
                System.out.println("Wrong index is passed for label coloring.");
            }
        } else {
            System.out.println("Wrong player ID is passed for label coloring.");
        }
    }

}
