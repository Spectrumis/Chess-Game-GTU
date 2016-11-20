package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        /*launch(args);*/
        List<Cell> tempMovesList = new LinkedList<>();
        Cell targetCell;

        Game game = new Game();
        game.printBoard();

        /*------------------------------------There will be button handler--------------------------------------------*/
        game.playGame(targetCell, tempMovesList);

    }
}
