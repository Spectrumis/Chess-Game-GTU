import com.sun.glass.ui.CommonDialogs;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main extends Application  {

    Stage window;
    Scene scene1;
    boolean answer;
    String[][] ButtonBorders=new String[8][8];
    ArrayList<Coordinate> currentPoint=new ArrayList<Coordinate>();//tıklanan buttonların oluşturduğu arraydir.
    ExtendedButton CurrentButton;//o anki tıklanan buttonu represent eder.
    ExtendedButton[][] button=new ExtendedButton[8][];//boarddaki satranç tahtasını represent eder.
    Stack<Table> ListGame=new Stack<Table>();//oyunu geri alabilmek için stack oluşturuldu.
    List<Cell> tempMovesList = new ArrayList<Cell>();//Algoritma tarafından gelen geçerli hareketlerin olduğu arraydir.
    int x=0, y=0, currentStatus = 0, a=0;
    boolean startingStatusHandler = false;
    int markButton=0;
    Game game = new Game();
    int b;
    @Override
    public void start(Stage primaryStage) throws Exception{

        window=primaryStage;
        Label Space=new Label();
        Space.setText("       ");
        window.setTitle("Chess");
        GridPane grid=new GridPane();
        OpenDialogue Open=new OpenDialogue();
        ExtendedButton Restart=new ExtendedButton();
        Restart.setMinSize(80,80);
        Restart.setStyle("-fx-border-color: gray; -fx-background-image: url('img/restart.png')");
        ExtendedButton Previous=new ExtendedButton();
        Previous.setMinSize(80,80);
        Previous.setStyle("-fx-border-color: gray; -fx-background-image: url('img/previous.png')");

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
                        for (a = markButton; a < tempMovesList.size(); ++a) {
                            markButton(button[tempMovesList.get(a).getX()][tempMovesList.get(a).getY()]);
                        }

                        markButton(CurrentButton);
                    }
                    if(currentStatus==2)
                    {
                        button[CurrentButton.getCoorX()][CurrentButton.getCoorY()].setStyle(button[currentPoint.get(currentPoint.size()-1).getX()][currentPoint.get(currentPoint.size()-1).getY()].getStyle());
                        button[currentPoint.get(currentPoint.size()-1).getX()][currentPoint.get(currentPoint.size()-1).getY()].setStyle("-fx-border-color: gray; )");
                    }
                    markButton=tempMovesList.size();
                    currentPoint.add(new Coordinate(CurrentButton.getCoorX(),CurrentButton.getCoorY()));
                    Table temp=new Table(button);//oyunun her bir anı kayıt edilmektedir geri alabilmek için.
                    ListGame.add(temp);//stacke kayıtlanmaktadır.

                });

            }
        }
        for(i=7;i>=0;--i)
        {
            for(j=7;j>=0;--j)
            {
                GridPane.setConstraints(button[i][j],7-i,7-j);
            }
        }
        setButoons(button);//taşları boarda yerleştiriyorum.
        //BU kısımdada boardı ekrana yerleştiriyorum.
        Label Space2=new Label();
        Space2.setText("       ");
        GridPane.setConstraints(Restart,10,2);
        GridPane.setConstraints(Space,9,2);
        GridPane.setConstraints(Previous,10,4);
        GridPane.setConstraints(Space2,9,4);
        Restart.setDisable(true);
        for(i=7;i>=0;--i)
        {
            for(j=7;j>=0;--j)
            {
                grid.getChildren().addAll(button[i][j]);
            }
        }
        grid.getChildren().addAll(Space,Restart);
        grid.getChildren().addAll(Space2,Previous);
        Previous.setDisable(true);
        for(i=0;i<8;++i)
        {
            for(j=0;j<8;++j)
            {
                button[i][j].setDisable(true);
            }
        }
        BorderPane pane=new BorderPane();
        pane.setLeft(grid);
        Scene scene=new Scene(pane,800,680);
        MenuBar menuBar = new MenuBar();

        // --- Menu File yani menu kısmını oluşturduğum bölüm
        Menu menuFile = new Menu("Game");
        MenuItem restart=new MenuItem("Restart");
        MenuItem Load=new MenuItem("Load");
        MenuItem Save=new MenuItem("Save");
        MenuItem Exit=new MenuItem("Exit");
        menuFile.getItems().addAll(restart,Load,Save,Exit);
        ExtendedButton Start=new ExtendedButton();

        Label[] col=new Label[8];
        Label[] row=new Label[8];
        String [] alphabet={"A","B","C","D","E","F","G","H"};
        for(i=1;i<9;++i)
        {
            col[i-1] = new Label(alphabet[i-1]);
            col[i-1].setStyle("-fx-border-color: gray;");
            col[i-1].setMinSize(80,20);
            GridPane.setConstraints(col[i-1],i-1,0);
        }
        for(i=1;i<9;++i)
        {
            row[i-1] = new Label(""+i);
            row[i-1].setStyle("-fx-border-color: gray;");
            row[i-1].setMinSize(20,80);
            GridPane.setConstraints(row[i-1],8,i);
        }
        for(i=0;i<8;++i)
            for(j=0;j<8;++j)
                GridPane.setConstraints(button[i][j],7-i,7-j+1);
        for(i=0;i<8;++i)
            grid.getChildren().addAll(col[i]);
        for(i=0;i<8;++i)
            grid.getChildren().addAll(row[i]);

        Save.setOnAction(e->{//eğer Save'e tıklanılırsa
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(primaryStage);

            if(file != null){
                //Burada Dosya kayıt işlemleri yaplacaktır.
            }
        });

        Load.setOnAction(e->{//eğer Load'a tıklanılırsa
            FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Load File");
                FileChooser.ExtensionFilter extFilter =new FileChooser.ExtensionFilter("*", "*");
                fileChooser.getExtensionFilters().add(extFilter);
                 File file = fileChooser.showOpenDialog(null);

            if(file!=null)
            {
                //Load işlemi burada yapılacaktır.
            }
        });

        restart.setOnAction(e->{//new game menu kısmındaki.
            Open.OpenMenu(Start,button);
            if(Open.Color==1)
            {
                startingStatusHandler=false;
            }
            else
            if(Open.Color==0)
            {
                startingStatusHandler=true;
            }
            for(a=0;a<8;++a)
            {
                for(b=0;b<8;++b)
                {
                    button[a][b].setDisable(false);
                }
            }
            setButoons(button);
            Restart.setDisable(true);


        });
        // --- Menu Edit
        Menu About = new Menu("About");
        MenuItem Project=new MenuItem("Project");
        About.getItems().addAll(Project);

        // --- Menu View
        Menu menuView = new Menu("Help");
        menuBar.getMenus().addAll(menuFile, About, menuView);
        pane.setTop(menuBar);
        window.setScene(scene);
        window.show();
        Open.initialWindow();
        Open.OpenMenu(Start,button);

        Restart.setOnAction(e->{//geçerli olan oyunun yeniden başlaması yani sağdaki button olan restart.
            setButoons(button);
            game.restartGame();
        });
        Previous.setOnAction(e->{//undo yani geri alma buttonunun event handleri.
            Table temp=  ListGame.peek();
            int m,n;

            if(temp!=null) {
                //game.recallMove(); //recall Methodunu çağırıdığım zaman geri alma yapmıyor.
                ListGame.pop();
                for (m = 0; m < 8; ++m) {
                    for (n = 0; n < 8; ++n) {
                        button[m][n].setStyle(temp.Table[m][n].getStyle());
                    }
                }
                for (m = 0; m < 8; ++m)
                    for (n = 0; n < 8; ++n)
                        GridPane.setConstraints(button[m][n],7- m, 7-n+1);
                for (m = 0; m < 8; ++m)
                    for (n = 0; n < 8; ++n)
                        grid.getChildren().addAll(button[m][n]);
            }
            else{
                for (m = 0; m < 8; ++m)
                    for (n = 0; n < 8; ++n)
                        button[m][n].setStyle(" -fx-border-color: gray;  -fx-background-radius:0");
                setButoons(button);

                for (m = 0; m < 8; ++m)
                    for (n = 0; n < 8; ++n)
                        GridPane.setConstraints(button[m][n], m, 7-n + 1);
                for (m = 0; m < 8; ++m)
                    for (n = 0; n < 8; ++n)
                        grid.getChildren().addAll(button[m][n]);
            }
                });
        Project.setOnAction(e->{ //About için event handler.
            System.out.println("asdsad");
            Stage window2=new Stage();
            window2.initModality(Modality.APPLICATION_MODAL);
            window2.setTitle("About GTUChess");
            window2.setMinWidth(300);
            window2.setMinHeight(600);
            window2.setMaxWidth(300);
            window2.setMaxHeight(600);
            Text p=new Text();
            p.setText(    "      ----Group8----");
            p.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));
            Text p1=new Text();
            p1.setText("        Recep Sivri");
            p1.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));
            Text p2=new Text();
            p2.setText("        Ali Emre Büyükersoy");
            p2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));
            Text p3=new Text();
            p3.setText("        Yasin Açıkgöz");
            p3.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));
            Text p4=new Text();
            p4.setText("        Selman Ahatlı");
            p4.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));
            Text p5=new Text();
            p5.setText("        Yasin Tulumen");
            p5.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));
            Text p6=new Text();
            p6.setText("        Gözde Doğan");
            p6.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));
            Text p7=new Text();
            p7.setText("        Halil Köse");
            p7.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));
            Text p8=new Text();
            p8.setText("        Mehmet Öztürk\n\n\n\n\n\n\n\n\n\n\n\n\n");
            p8.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));
            Text p9=new Text();
            p9.setText("    CSE343    Uraz Cengiz Türker\n" +
                    "     Gebze Technical University\n " +
                    "               Gebze-Kocaeli");
            p9.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));
            VBox Layout=new VBox(10);
            Layout.getChildren().addAll(p,p1,p2,p3,p4,p5,p6,p7,p8,p9);
            Scene s1=new Scene(Layout);
            window2.setScene(s1);
            window2.show();

        });
        Start.setOnAction(e->{
            int a;
            Open.window.close();
            System.out.println(Open.LevelOfGame);
            Game.setIsComputerOn(Open.LevelOfGame);
            if(Open.Color==1)
            {
                startingStatusHandler=false;
            }
            else
            if(Open.Color==0)
            {
                startingStatusHandler=true;
            }
            for(a=0;a<8;++a)
            {
                for(b=0;b<8;++b)
                {
                    button[a][b].setDisable(false);
                }
            }
            Restart.setDisable(false);
            game.restartGame();
            Previous.setDisable(false);
        });
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
    private void setButoons(ExtendedButton [][] button)
    {
        int i,a,b;
        for(a=0;a<8;++a)
        {
            for(b=2;b<6;++b)
            {
                button[a][b].setStyle("-fx-border-color: gray;");
            }
        }
        for(i=0;i<8;++i)
        {
            button[i][6].setStyle("-fx-border-color: gray; -fx-background-image: url('img/wpawn.png')");

        }
        button[0][7].setStyle("-fx-border-color: gray; -fx-background-image: url('img/wrook.png')");
        button[7][7].setStyle("-fx-border-color: gray; -fx-background-image: url('img/wrook.png')");
        button[1][7].setStyle("-fx-border-color: gray; -fx-background-image: url('img/wknight.png')");
        button[6][7].setStyle("-fx-border-color: gray; -fx-background-image: url('img/wknight.png')");
        button[2][7].setStyle("-fx-border-color: gray; -fx-background-image: url('img/wbishop.png')");
        button[5][7].setStyle("-fx-border-color: gray; -fx-background-image: url('img/wbishop.png')");
        button[4][7].setStyle("-fx-border-color: gray; -fx-background-image: url('img/wqueen.png')");
        button[3][7].setStyle("-fx-border-color: gray; -fx-background-image: url('img/wking.png')");
        button[0][0].setStyle("-fx-border-color: gray; -fx-background-image: url('img/rook.png')");
        button[7][0].setStyle("-fx-border-color: gray; -fx-background-image: url('img/rook.png')");
        button[1][0].setStyle("-fx-border-color: gray; -fx-background-image: url('img/knight.png')");
        button[6][0].setStyle("-fx-border-color: gray; -fx-background-image: url('img/knight.png')");
        button[2][0].setStyle("-fx-border-color: gray; -fx-background-image: url('img/bishop.png')");
        button[5][0].setStyle("-fx-border-color: gray; -fx-background-image: url('img/bishop.png')");
        button[4][0].setStyle("-fx-border-color: gray; -fx-background-image: url('img/queen.png')");
        button[3][0].setStyle("-fx-border-color: gray; -fx-background-image: url('img/king.png')");
        for(i=0;i<8;++i)
        {
            button[i][1].setStyle("-fx-border-color: gray; -fx-background-image: url('img/pawn.png')");

        }
    }

    /**
     * Created by Recep Sivri on 28.11.2016.
     * Bu class remove yani geri alma methodu implement edilebilmek için oluşturuldu
     */
    public static class Table {
        public ExtendedButton [][] Table;
        public Table(ExtendedButton[][] Game)
        {
            int i,j;
            Table= new ExtendedButton[8][];
            for(i=0;i<8;++i)
            {
                Table[i] = new ExtendedButton[8];
                for (j = 0; j < 8; ++j) {
                    Table[i][j] = new ExtendedButton();
                    Table[i][j].setMinSize(80, 80);

                    Table[i][j].setStyle(Game[i][j].getStyle());
                    Table[i][j].setCoor(new Coordinate(i, j));
                }
            }
        }
    }
}
