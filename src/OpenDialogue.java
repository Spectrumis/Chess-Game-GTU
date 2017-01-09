import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * Created by Recep Sivri on 27.11.2016.
 */
public class OpenDialogue {//oyun açılış ekranı için implement edilmiş bir classtır.
    int LevelOfGame=-1;
    int Color=-1;
    Stage window=new Stage();
    public void initialWindow()
    {
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Welcome To GTUChess!");
        window.setMinWidth(400);
        window.setMinHeight(350);
        window.setMaxWidth(400);
        window.setMaxHeight(250);
    }
    public  void OpenMenu(ExtendedButton Start,ExtendedButton [][]board)
    {

        Text Space=new Text();
        Space.setText("   ");
        Text Space2=new Text();
        Space2.setText("\t\t  ");
        Text Space3=new Text();
        Space3.setText("\t");
        Text Space6=new Text();
        Space6.setText("\t");
        Text Space5=new Text();
        Space5.setText("\t  ");
        Text Space4=new Text();
        Space4.setText("         ");
        Text l0=new Text();
        Text l1=new Text();
        Text l2=new Text();
        Text TextVS=new Text();
        Text TextComp=new Text();
        Text TextEasy=new Text();
        Text TextMedium=new Text();
        Text TextHard=new Text();
        TextComp.setText("vs. Computer\t");
        TextComp.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));
        TextVS.setText("vs. Player");
        TextVS.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));

        TextEasy.setText("Easy");
        TextEasy.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));

        TextMedium.setText("Medium");
        TextMedium.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));

        TextHard.setText("Hard");
        TextHard.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));

        l0.setText("    Choose type of the game");
        l0.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));


        l1.setText("    Choose difficulty level of the game");
        l1.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));

        l2.setText("    Select color of piece:");
        l2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));

        Image image = new Image("img/wpawnShow.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        RadioButton p1=new RadioButton();
        RadioButton p2=new RadioButton();
        p1.setText("White");
        p2.setText("Black");
        p1.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));
        p2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));
        Image image2 = new Image("img/pawnShow.png");
        ImageView iv2 = new ImageView();
        iv2.setImage(image2);

        CheckBox vs=new CheckBox();
        CheckBox comp=new CheckBox();
        CheckBox easy=new CheckBox();
        CheckBox Medium=new CheckBox();
        CheckBox Hard=new CheckBox();
        VBox Layout=new VBox(10);
        HBox Layout2=new HBox(1);
        HBox Layout3=new HBox(1);
        HBox Layout4=new HBox(20);
        HBox Layout5=new HBox(1);
        HBox Layout6=new HBox(1);
        HBox Layout7=new HBox(15);
        HBox Layout8=new HBox(28);

        Layout2.getChildren().addAll(Space6,comp,TextComp,vs,TextVS);
        Layout3.getChildren().addAll(easy,TextEasy);
        Layout5.getChildren().addAll(Medium,TextMedium);
        Layout6.getChildren().addAll(Hard,TextHard);
        Layout7.getChildren().addAll(Space2,iv1,Space5,iv2,Space4,Start);
        Layout8.getChildren().addAll(Space3, p1,p2);
        Layout4.getChildren().addAll(Space,Layout3,Layout5,Layout6);
        Layout.getChildren().addAll(l0,Layout2,l1,Layout4,l2,Layout7,Layout8);

        Start.setMinSize(80,80);
        Start.setStyle("-fx-border-color: gray; -fx-background-image: url('img/start.png')");
        Start.setDisable(true);
        vs.setOnAction(e-> {
            if (vs.isSelected()) {
                comp.setSelected(false);
                easy.setDisable(true);
                Medium.setDisable(true);
                Hard.setDisable(true);
                easy.setSelected(false);
                Medium.setSelected(false);
                Hard.setSelected(false);
                LevelOfGame=0;
            }
            else
            if (!vs.isSelected()) {
                comp.setSelected(false);
                easy.setSelected(false);
                Medium.setSelected(false);
                Hard.setSelected(false);
                LevelOfGame=-1;
            }

        });
        comp.setOnAction(e-> {
            if (comp.isSelected()) {
                vs.setSelected(false);
                easy.setDisable(false);
                Medium.setDisable(false);
                Hard.setDisable(false);
                Start.setDisable(true);
                LevelOfGame=0;
            }
            else
            if (!comp.isSelected()) {
                easy.setSelected(false);
                Medium.setSelected(false);
                Hard.setSelected(false);
                vs.setSelected(false);
                Start.setDisable(true);
                LevelOfGame=-1;
            }
        });
        easy.setOnAction(e-> {
            if (easy.isSelected()) {
                Medium.setSelected(false);
                Hard.setSelected(false);
                LevelOfGame=1;
            }
            else if (!easy.isSelected()) {
                Medium.setSelected(false);
                Hard.setSelected(false);
                LevelOfGame=-1;
            }
            if ((comp.isSelected() && (easy.isSelected() || Medium.isSelected() || Hard.isSelected())) || vs.isSelected() && (p1.isSelected()||p2.isSelected())){
                Start.setDisable(false);
            }
            else
                Start.setDisable(true);
        });
        Medium.setOnAction(e-> {
            if (Medium.isSelected()) {
                easy.setSelected(false);
                Hard.setSelected(false);
                LevelOfGame=2;
            }
            else
            if (!Medium.isSelected()) {
                easy.setSelected(false);
                Hard.setSelected(false);
                LevelOfGame=-1;
            }
            if ((comp.isSelected() && (easy.isSelected()||Medium.isSelected()||Hard.isSelected())) || vs.isSelected() && (p1.isSelected()||p2.isSelected())){
                Start.setDisable(false);
            }
            else
                Start.setDisable(true);
        });
        Hard.setOnAction(e-> {
            if (Hard.isSelected()) {
                easy.setSelected(false);
                Medium.setSelected(false);
                LevelOfGame=3;
            }
            else
            if (!Hard.isSelected()) {
                easy.setSelected(false);
                Medium.setSelected(false);
                LevelOfGame=-1;
            }
            if ((comp.isSelected() && (easy.isSelected()||Medium.isSelected()||Hard.isSelected())) || vs.isSelected() && (p1.isSelected()||p2.isSelected())){
                Start.setDisable(false);
            }
            else
                Start.setDisable(true);
        });
        p1.setOnAction(e-> {
            if(p1.isSelected()) {
                p2.setSelected(false);
                Color=0;
            }
            else
            if(!p1.isSelected()) {
                p2.setSelected(false);
                Color=-1;
            }
            if ((comp.isSelected() && (easy.isSelected()||Medium.isSelected()||Hard.isSelected())) || vs.isSelected() && (p1.isSelected()||p2.isSelected())){
                Start.setDisable(false);
            }
            else
                Start.setDisable(true);

        });
        p2.setOnAction(e-> {
            if(p2.isSelected()) {
                p1.setSelected(false);
                Color=1;
            }
            else
            if(!p2.isSelected()) {
                Color=-1;
            }
            if ((comp.isSelected() && (easy.isSelected()||Medium.isSelected()||Hard.isSelected())) || vs.isSelected() && (p1.isSelected()||p2.isSelected())){
                Start.setDisable(false);
            }
            else
                Start.setDisable(true);
        });

        Scene s1=new Scene(Layout);
        window.setScene(s1);
        window.show();
    }
    public int getLevelofGame()
    {
        return LevelOfGame;
    }
}
