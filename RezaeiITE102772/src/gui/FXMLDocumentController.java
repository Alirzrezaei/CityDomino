/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.GUItoGame;
import logic.Pos;

/**
 * in this class all the components of the gui were created and game will be initialized here
 * then it pass them to two objects of game in logic and javafxgui. It also get users events and
 * pass them to game in logic to update the logic and gui respectively. 
 * @author Alireza
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private BorderPane bdrPnMain;
    @FXML
    private GridPane grdPnNextCards;
    @FXML
    private Pane pnCurrentCard;
    @FXML
    private Button btnNewGame;
    @FXML
    private Label lblPlayerField;
    @FXML
    private GridPane grdPnField;
    @FXML
    private GridPane grdPnAI3;
    @FXML
    private GridPane grdPnAI2;
    @FXML
    private GridPane grdPnAI1;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnLoad;
    
    private GUItoGame game;
    private JavaFXGUI gui;
    
    @FXML
    private GridPane grdPnCurrents;
   
    @FXML
    private Button discardBtn;
    @FXML
    private Label lbl0;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;
    @FXML
    private Button btnRight;
    @FXML
    private Button btnUp;
    @FXML
    private Button btnLeft;
    @FXML
    private Button btnDown;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ImageView[][] imgVwsGame = addImageViewsToGrid(grdPnField);
        this.addDragAndDropHandlers(imgVwsGame);
        //this.addDragAndDropHandlers(pnDiscard);
            
        ImageView[][] imgVwsAI1 = addImageViewsToGrid(grdPnAI1);
        ImageView[][] imgVwsAI2 = addImageViewsToGrid(grdPnAI2);
        ImageView[][] imgVwsAI3 = addImageViewsToGrid(grdPnAI3);
        
        ImageView[][] imgVwsNext = addImageViewsToGrid(grdPnNextCards);
        ImageView[][] imgVwscurrent = addImageViewsToGrid(grdPnCurrents);
        
        btnImg();
        //ImageView[][] imgPlayerColor = addImageViewsToGrid(grdPnPlayerColor);
 
        //initialize GUI with selected pane, boards, next box and current box 
        gui = new JavaFXGUI(pnCurrentCard, imgVwsGame, imgVwsAI1, imgVwsAI2, imgVwsAI3, imgVwsNext, 
                imgVwscurrent, lbl0, lbl1, lbl2, lbl3);
        
        //initializ the game with passing gui, size of X axis of board, size of Y axis of board
        // number of total player and number of human player
        this.game = new logic.Game(this.gui, grdPnField.getColumnConstraints().size(), 
                grdPnField.getRowConstraints().size(), 4, 1);
        
    }

    private ImageView[][] addImageViewsToGrid(GridPane grdPn) {
        int colcount = grdPn.getColumnConstraints().size();
        int rowcount = grdPn.getRowConstraints().size();

        ImageView[][] imgVwsGame = new ImageView[colcount][rowcount];

        for (int x = 0; x < colcount; x++) {
            for (int y = 0; y < rowcount; y++) {
                //creates an imageview with the empty image (for some reasons there needs to an image there for the drag and drop to work :()
                imgVwsGame[x][y] = new ImageView(JavaFXGUI.EMPTY_IMG);

                //add the imageview to the cell and
                //assign the correct indicees for this imageview, so you later can use GridPane.getColumnIndex(Node)
                grdPn.add(imgVwsGame[x][y], x, y);

                //the image shall resize when the cell resizes
                imgVwsGame[x][y].fitWidthProperty().bind(grdPn.widthProperty().divide(colcount).subtract(grdPn.getHgap()));
                imgVwsGame[x][y].fitHeightProperty().bind(grdPn.heightProperty().divide(rowcount).subtract(grdPn.getVgap()));
            }
        }
        return imgVwsGame;
    }

    private void addDragAndDropHandlers(ImageView[][] imgVwsGame) {
        for (int x = 0; x < imgVwsGame.length; x++) {
            for (int y = 0; y < imgVwsGame[x].length; y++) {
                final int fx = x; 
                final int fy = y; 
                imgVwsGame[x][y].setOnDragOver((EventHandler<DragEvent>) (DragEvent event) -> {
                    if (event.getGestureSource() != imgVwsGame) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
                    event.consume();
                });

                imgVwsGame[x][y].setOnDragDropped((EventHandler<DragEvent>) (DragEvent event) -> {
                    boolean success = false;
                    
                    Pos pos = new Pos(fx, fy);
                    if(this.game.fits(pos)){
                        success = true;
                        this.game.setOnTheBoard(pos);
                        
                    }
                    event.setDropCompleted(success);
                    event.consume();
                });
            }
        }
    }

    @FXML
    private void onClickStartGame(ActionEvent event) throws IOException {
       
        this.game.start();
        
    }
   
  

    @FXML
    private void onDragDetectedPnSelected(MouseEvent event) {
         /* drag was detected, start a drag-and-drop gesture*/
        /* allow any transfer mode */
        Dragboard dbDrag = this.pnCurrentCard.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();

        content.putString("");
        dbDrag.setContent(content);

        dbDrag.setDragView(this.pnCurrentCard.snapshot(new SnapshotParameters(), null), 10, 10);

        event.consume();
    }

    @FXML
    private void onClickLoadGame(ActionEvent event) {
        
        this.game = game.load(this.game);
    }

    @FXML
    private void onClickPnCurrent(MouseEvent event) {
        game.currentBoxClick();
    }

    @FXML
    private void grdPnNextBoxMouseClick(MouseEvent event) {
        int x = -1;
        int y = -1; 
        
        boolean leftClicked = event.getButton() == MouseButton.PRIMARY;
        boolean rightClicked = event.getButton() == MouseButton.SECONDARY;
        
        for (Iterator<Node> it = grdPnNextCards.getChildren().iterator(); it.hasNext();) {
            Node node = it.next();
            if (node instanceof ImageView) {
                if (node.getBoundsInParent().contains(event.getX(), event.getY())) {
                    //to use following methods the columnIndex and rowIndex
                    //must have been set when adding the imageview to the grid
                    x = GridPane.getColumnIndex(node);
                    y = GridPane.getRowIndex(node);
                }
            }
        }

        if (x >= 0 && y >= 0 && leftClicked) {
            this.game.clickOnNext(y);
        }
        
        ImageView imgVwsSelectBox = new ImageView();
    }

    @FXML
    private void onClickSaveGame(ActionEvent event) {
        System.out.println("I am clicked");
        game.save();
    }

    @FXML
    private void onClickDiscardBtn(MouseEvent event) {
        this.game.discardDomino();
    }

    @FXML
    private void onClickBtnRight(MouseEvent event) {
        game.arrowKeysClick('r');
    }

    @FXML
    private void onClickBtnUp(MouseEvent event) {
        game.arrowKeysClick('u');
    }

    @FXML
    private void onClickBtnLeft(MouseEvent event) {
        game.arrowKeysClick('l');
    }

    @FXML
    private void OnClickBtnDown(MouseEvent event) {
        game.arrowKeysClick('d');
    }

    private void btnImg(){
       ImageView imgUp = new ImageView();
       imgUp.setImage(new Image("gui/img/up.png"));
       btnUp.setGraphic(imgUp);
       imgUp.fitWidthProperty().bind(btnUp.widthProperty());
       imgUp.fitHeightProperty().bind(btnUp.heightProperty());
       
       ImageView imgDown = new ImageView();
       imgDown.setImage(new Image("gui/img/down.png"));
       btnDown.setGraphic(imgDown);
       imgDown.fitWidthProperty().bind(btnDown.widthProperty());
       imgDown.fitHeightProperty().bind(btnDown.heightProperty());
       
        ImageView imgRight = new ImageView();
       imgRight.setImage(new Image("gui/img/right.png"));
       btnRight.setGraphic(imgRight);
       imgRight.fitWidthProperty().bind(btnRight.widthProperty());
       imgRight.fitHeightProperty().bind(btnRight.heightProperty());
       
        ImageView imgLeft = new ImageView();
       imgLeft.setImage(new Image("gui/img/left.png"));
       btnLeft.setGraphic(imgLeft);
       imgLeft.fitWidthProperty().bind(btnLeft.widthProperty());
       imgLeft.fitHeightProperty().bind(btnLeft.heightProperty());
       
        ImageView imgSave = new ImageView();
       imgSave.setImage(new Image("gui/img/save.png"));
       btnSave.setGraphic(imgSave);
       imgSave.fitWidthProperty().bind(btnSave.widthProperty());
       imgSave.fitHeightProperty().bind(btnSave.heightProperty());
       
        ImageView imgLoad = new ImageView();
       imgLoad.setImage(new Image("gui/img/load.png"));
       btnLoad.setGraphic(imgLoad);
       imgLoad.fitWidthProperty().bind(btnLoad.widthProperty());
       imgLoad.fitHeightProperty().bind(btnLoad.heightProperty());
       
        ImageView imgDel = new ImageView();
       imgDel.setImage(new Image("gui/img/delete.png"));
       discardBtn.setGraphic(imgDel);
       imgDel.fitWidthProperty().bind(discardBtn.widthProperty());
       imgDel.fitHeightProperty().bind(discardBtn.heightProperty());
    }

}
