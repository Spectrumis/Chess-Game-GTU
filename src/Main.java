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
        List<Cell> tempMovesList = new LinkedList<>();
        int x=0, y=0, currentStatus = 0;
        boolean startingStatusHandler = false;

        Game game = new Game();
        game.printBoard();

        if(startingStatusHandler || game.getIsComputerOn() == 0) {
            currentStatus = game.playGame(1, 1, tempMovesList);
        }
        startingStatusHandler = true;
        if(game.getIsComputerOn() != 0){
            switch (game.getIsComputerOn()){
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
        System.out.println(tempMovesList.size()+"  "+currentStatus);
    }

    public static void main(String[] args) {
        launch(args);

    }
}
