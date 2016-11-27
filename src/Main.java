import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main extends Application  {

    Stage window;
    Scene scene1;
    boolean answer;
    String[][] ButtonBorders=new String[8][8];
    ArrayList<Coordinate> currentPoint=new ArrayList<Coordinate>();
    ExtendedButton CurrentButton;
    ExtendedButton[][] button=new ExtendedButton[8][];
    List<Cell> tempMovesList = new ArrayList<Cell>();
    int x=0, y=0, currentStatus = 0, a=0;
    boolean startingStatusHandler = false;

    Game game = new Game();
    int b;
    @Override
    public void start(Stage primaryStage) throws Exception{
        window=primaryStage;
        window.setTitle("Chess");
        GridPane grid=new GridPane();

        int i,j;

        for(i=0;i<8;++i)
        {
            button[i]=new ExtendedButton[8];
            for(j=0;j<8;++j)
            {
                button[i][j]=new ExtendedButton();
                button[i][j].setMinSize(80,80);
                button[i][j].setStyle(" -fx-border-color: gray;  -fx-background-radius:0");
                button[i][j].setCoor(new Coordinate(i,j));
                a=i;
                b=j;
                button[i][j].setOnAction(e->{


                    refreshTable();
                    CurrentButton=(ExtendedButton)e.getSource();
                    if (startingStatusHandler || game.getIsComputerOn() == 0) {

                        currentStatus = game.playGame(CurrentButton.getCoorX(), CurrentButton.getCoorY(), tempMovesList);

                        System.out.print("Status:" + currentStatus + "\n");


                        System.out.print("TempListCounter:" + a + "\n");
                    }
                    startingStatusHandler = true;
                    if (game.getIsComputerOn() != 0) {
                        tempMovesList.clear();
                        switch (game.getIsComputerOn()) {
                            case 1:
                                tempMovesList.addAll(game.playComputerEasy());
                                break;
                            case 2:
                                tempMovesList.addAll(game.playComputerMedium());
                                break;
                            case 3:
                                tempMovesList.addAll(game.playComputerHard());
                                break;
                            default:
                                System.out.println("ComputerOn degeri yanlis\n");
                                break;
                        }
                    }
                    if(currentStatus==1)
                    {
                        for (a = 0; a < tempMovesList.size(); ++a)
                            markButton(button[tempMovesList.get(a).getX()][tempMovesList.get(a).getY()]);
                            markButton(CurrentButton);
                    }
                    if(currentStatus==2)
                    {
                        button[CurrentButton.getCoorX()][CurrentButton.getCoorY()].setStyle(button[currentPoint.get(currentPoint.size()-1).getX()][currentPoint.get(currentPoint.size()-1).getY()].getStyle());
                        button[currentPoint.get(currentPoint.size()-1).getX()][currentPoint.get(currentPoint.size()-1).getY()].setStyle("-fx-border-color: gray; )");
                    }
                    currentPoint.add(new Coordinate(CurrentButton.getCoorX(),CurrentButton.getCoorY()));


                });

            }
        }
        for(i=0;i<8;++i)
        {
            for(j=0;j<8;++j)
            {
                GridPane.setConstraints(button[i][j],i,j);
            }
        }
        for(i=0;i<8;++i)
        {
            for(j=0;j<8;++j)
            {
                grid.getChildren().addAll(button[i][j]);
            }
        }
        for(i=0;i<8;++i)
        {
            button[i][6].setStyle("-fx-border-color: gray; -fx-background-image: url('img/pawn.png')");

        }
        button[0][7].setStyle("-fx-border-color: gray; -fx-background-image: url('img/rook.png')");
        button[7][7].setStyle("-fx-border-color: gray; -fx-background-image: url('img/rook.png')");
        button[1][7].setStyle("-fx-border-color: gray; -fx-background-image: url('img/knight.png')");
        button[6][7].setStyle("-fx-border-color: gray; -fx-background-image: url('img/knight.png')");
        button[2][7].setStyle("-fx-border-color: gray; -fx-background-image: url('img/bishop.png')");
        button[5][7].setStyle("-fx-border-color: gray; -fx-background-image: url('img/bishop.png')");
        button[3][7].setStyle("-fx-border-color: gray; -fx-background-image: url('img/queen.png')");
        button[4][7].setStyle("-fx-border-color: gray; -fx-background-image: url('img/king.png')");
        button[0][0].setStyle("-fx-border-color: gray; -fx-background-image: url('img/wrook.png')");
        button[7][0].setStyle("-fx-border-color: gray; -fx-background-image: url('img/wrook.png')");
        button[1][0].setStyle("-fx-border-color: gray; -fx-background-image: url('img/wknight.png')");
        button[6][0].setStyle("-fx-border-color: gray; -fx-background-image: url('img/wknight.png')");
        button[2][0].setStyle("-fx-border-color: gray; -fx-background-image: url('img/wbishop.png')");
        button[5][0].setStyle("-fx-border-color: gray; -fx-background-image: url('img/wbishop.png')");
        button[3][0].setStyle("-fx-border-color: gray; -fx-background-image: url('img/wqueen.png')");
        button[4][0].setStyle("-fx-border-color: gray; -fx-background-image: url('img/wking.png')");
        for(i=0;i<8;++i)
        {
            button[i][1].setStyle("-fx-border-color: gray; -fx-background-image: url('img/wpawn.png')");

        }
        BorderPane pane=new BorderPane();
        pane.setLeft(grid);
        Scene scene=new Scene(pane,750,660);
        MenuBar menuBar = new MenuBar();

        // --- Menu File
        Menu menuFile = new Menu("Game");
        MenuItem Level=new MenuItem("Levels");
        MenuItem Options=new MenuItem("Options");
        MenuItem Load=new MenuItem("Load");
        MenuItem Save=new MenuItem("Save");
        MenuItem Exit=new MenuItem("Exit");
        menuFile.getItems().addAll(Level,Options,Load,Save,Exit);
        // --- Menu Edit
        Menu About = new Menu("About");


        // --- Menu View
        Menu menuView = new Menu("Help");
        menuBar.getMenus().addAll(menuFile, About, menuView);
        pane.setTop(menuBar);
        window.setScene(scene);
        window.show();
        Exit.setOnAction(e->{
            Platform.exit();
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
    private String getPieces(ExtendedButton CurrentButton)
    {
        if(!CurrentButton.getStyle().contains("wpawn")&&CurrentButton.getStyle().contains("pawn"))
        {
            return "pawn";
        }
        else
        if(!CurrentButton.getStyle().contains("wrook")&&CurrentButton.getStyle().contains("rook"))
        {
            return "rook";
        }
        else
        if(!CurrentButton.getStyle().contains("wknight")&&CurrentButton.getStyle().contains("knight"))
        {
            return "knight";
        }
        else
        if(!CurrentButton.getStyle().contains("wbishop")&&CurrentButton.getStyle().contains("bishop"))
        {
            return "bishop";
        }
        else
        if(!CurrentButton.getStyle().contains("wqueen")&&CurrentButton.getStyle().contains("queen"))
        {
            return "queen";
        }
        else
        if(!CurrentButton.getStyle().contains("wking")&&CurrentButton.getStyle().contains("king"))
        {
            return "king" ;
        }
        if(CurrentButton.getStyle().contains("wpawn"))
        {
            return "wpawn";
        }
        else
        if(CurrentButton.getStyle().contains("wrook"))
        {
            return "wrook";
        }
        else
        if(CurrentButton.getStyle().contains("wknight"))
        {
            return "wknight";
        }
        else
        if(CurrentButton.getStyle().contains("wbishop"))
        {
            return "wbishop";
        }
        else
        if(CurrentButton.getStyle().contains("wqueen"))
        {
            return "wqueen";
        }
        else
        if(CurrentButton.getStyle().contains("wking"))
        {
            return "wking" ;
        }
        else
            return "null";
    }
    private void refreshTable()
    {
        int a,b;
        for(a=0;a<8;++a) {
            for(b=0;b<8;++b) {
                ButtonBorders[a][b]=new String(button[a][b].getStyle().toString().split(" ")[1]);
                button[a][b].setStyle("-fx-border-color: gray; -fx-background-image: url('img/" + "" +
                        getPieces(button[a][b]) + ".png')");
            }
        }

    }
    private void markButton(ExtendedButton button)
    {
        button.setStyle("-fx-border-color: lawngreen; -fx-border-width: 3; -fx-background-image: url('img/"+"" +
                getPieces(button)+".png')");
    }

}
