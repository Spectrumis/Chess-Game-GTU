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
        int x=0, y=0, currentStatus = 0;
        boolean startingStatusHandler = false;

        Game game = new Game();
        game.printBoard();

        /*------------------------------------There will be button handler--------------------------------------------*/
        /*
            Interface Oyun oncesi
                Burada oyunun zorluk derecesi PvP veya PvC karari verilcek ve zorluk derecesi secilicek
                Zorluk derecesi icin game class indaki isComputerOn degiskenini kullandik. PvP modunu bu degiskenin
                degerinin sıfır olmasi durumu temsil ederken easy, normal ve hard zorlugunu da sirasiyla bir, iki ve uc
                olmasi gösteriyor. Yani kisacasi
                    isComputerOn degeri         Anlami
                        0                       Player vs Player
                        1                       Player vs Easy
                        2                       Player vs Normal
                        3                       Player vs Hard
                olarak gosterilcek. Bu atamayi siz yapicaksiniz.
                Eger Player vs Computer secildiyse oyuncuya tas rengi sectirilecek
                    Siyah secerse startingStatusHandler 'i false yapicaksiniz
                    Beyaz secerse startingStatusHandler 'i true yapicaksiniz
                Bu islem sadece her yeni oyuna baslarken yapilacak


        */
        /*

            Interface Part 1
                Burada sizden istedigim tiklanan yerin kordinatını x ve y ye atmanız




         */
        /*------------------------------------Algorithm Part Begins---------------------------------------------------*/
        if(startingStatusHandler || !Game.getIsComputerOn()) {
            currentStatus = game.playGame(x, y, tempMovesList);
        }
        startingStatusHandler = true;
        if(game.getIsComputerOn()){
            switch (game.getIsComputerOn()){
                case 1:
                    tempMovesList.addAll(currentStatus=game.playComputerEasy());
                    break;
                case 2:
                    tempMovesList.addAll(currentStatus=game.playComputerMedium());
                    break;
                case 3:
                    tempMovesList.addAll(currentStatus=game.playComputerHard());
                    break;
                default:
                    System.out.println("ComputerOn degeri yanlis\n");
                    break;
            }
        }
        /*--------------------------------------Algorithm Part Ends---------------------------------------------------*/

        /*
            Interface Part 2
                Burada ise currentStatus degerine gore islem yapicaksiniz
                Eger sifir ise hicbir deisiklik yapmiyacak ve tiklanan yer haric hicbir yer isaretlemeyeceksiniz
                Eger bir degeri var ise yapacaginiz is tempMoveList'teki degerlerin hepsini ve son tiklanan yeri
              isaretlemek olucak
                Eger iki degeri var ise iki secenek var
                    -tempMoveListte iki cell var ise birincideki tasi ikinci kordinata oynaticaksiniz
                    -tempMoveListte dort cell var ise birinciyi ikinci kordinata, ucuncuyu dorduncu kordinata oynatacaksiniz
            Sonra ise yine bi butona basilmasi beklenicek

            (save game, undo move gibi oyun arasi basilabilecek butonlari ilk asamada yapmiycaz. Daha sonra eklicez)
        */

    }
}
