import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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
        window.setMinHeight(250);
        window.setMaxWidth(400);
        window.setMaxHeight(250);
    }
    public  void OpenMenu(ExtendedButton Start,ExtendedButton [][]board)
    {

        Text Space=new Text();
        Text Space2=new Text();
        Space.setText("   ");
        Space2.setText("   ");
        Text Space3=new Text();
        Space3.setText("");
        Text Space4=new Text();
        Space4.setText("                                                  ");
        Text l1=new Text();
        Text l2=new Text();
        Text TextVS=new Text();
        Text TextEasy=new Text();
        Text TextMedium=new Text();
        Text TextHard=new Text();
        TextVS.setText("VS");
        TextVS.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));

        TextEasy.setText("EASY");
        TextEasy.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));

        TextMedium.setText("MEDIUM");
        TextMedium.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));

        TextHard.setText("HARD");
        TextHard.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));

        l1.setText("    Please Choose Level Of Game:");
        l1.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));

        l2.setText("    Select Color Of Piece:");
        l2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,15));

        Image image = new Image("img/wpawnShow.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        RadioButton p1=new RadioButton();
        RadioButton p2=new RadioButton();
        Image image2 = new Image("img/pawnShow.png");
        ImageView iv2 = new ImageView();
        iv2.setImage(image2);

        CheckBox vs=new CheckBox();
        CheckBox easy=new CheckBox();
        CheckBox Medium=new CheckBox();
        CheckBox Hard=new CheckBox();
        VBox Layout=new VBox(10);
        HBox Layout2=new HBox(1);
        HBox Layout3=new HBox(1);
        HBox Layout4=new HBox(20);
        HBox Layout5=new HBox(1);
        HBox Layout6=new HBox(1);
        HBox Layout7=new HBox(10);
        HBox Layout8=new HBox(28);

        Layout2.getChildren().addAll(Space,vs,TextVS);
        Layout3.getChildren().addAll(easy,TextEasy);
        Layout5.getChildren().addAll(Medium,TextMedium);
        Layout6.getChildren().addAll(Hard,TextHard);
        Layout7.getChildren().addAll(Space2,iv1,iv2,Space4,Start);
        Layout8.getChildren().addAll(Space3,p1,p2);
        Layout4.getChildren().addAll(Layout2,Layout3,Layout5,Layout6);
        Layout.getChildren().addAll(l1,Layout4,l2,Layout7,Layout8);

        Start.setMinSize(80,80);
        Start.setStyle("-fx-border-color: gray; -fx-background-image: url('img/start.png')");
        Start.setDisable(true);
        vs.setOnAction(e-> {
                    if (vs.isSelected()) {
                        easy.setDisable(true);
                        Medium.setDisable(true);
                        Hard.setDisable(true);
                        p1.setDisable(true);
                        p2.setDisable(true);
                        LevelOfGame=0;
                        Start.setDisable(false);
                    }
                    else
                    if (!vs.isSelected()) {
                        easy.setDisable(false);
                        Medium.setDisable(false);
                        Hard.setDisable(false);
                        p1.setDisable(false);
                        p2.setDisable(false);
                        Start.setDisable(true);
                        LevelOfGame=-1;
                    }


        });
        easy.setOnAction(e-> {
            if (easy.isSelected()) {
                vs.setDisable(true);
                Medium.setDisable(true);
                Hard.setDisable(true);
                LevelOfGame=1;
            }
            else
            if (!easy.isSelected()) {
                vs.setDisable(false);
                Medium.setDisable(false);
                Hard.setDisable(false);
                LevelOfGame=-1;
            }
            if (vs.isSelected()||easy.isSelected()||Medium.isSelected()||Hard.isSelected()) {
                if(p1.isSelected()||p2.isSelected())
                    Start.setDisable(false);
                else
                    Start.setDisable(true);
            }
            else
                Start.setDisable(true);
                });
        Medium.setOnAction(e-> {
            if (Medium.isSelected()) {
                vs.setDisable(true);
                easy.setDisable(true);
                Hard.setDisable(true);
                LevelOfGame=2;
            }
            else
            if (!Medium.isSelected()) {
                vs.setDisable(false);
                easy.setDisable(false);
                Hard.setDisable(false);
                LevelOfGame=-1;
            }
            if (vs.isSelected()||easy.isSelected()||Medium.isSelected()||Hard.isSelected()) {
                if(p1.isSelected()||p2.isSelected())
                    Start.setDisable(false);
                else
                    Start.setDisable(true);
            }
            else
                Start.setDisable(true);
        });
        Hard.setOnAction(e-> {
            if (Hard.isSelected()) {
                vs.setDisable(true);
                easy.setDisable(true);
                Medium.setDisable(true);
                LevelOfGame=3;
            }
            else
            if (!Hard.isSelected()) {
                vs.setDisable(false);
                easy.setDisable(false);
                Medium.setDisable(false);
                LevelOfGame=-1;
            }
            if (vs.isSelected()||easy.isSelected()||Medium.isSelected()||Hard.isSelected()) {
                if(p1.isSelected()||p2.isSelected())
                    Start.setDisable(false);
                else
                    Start.setDisable(true);
            }
            else
                Start.setDisable(true);
        });
        p1.setOnAction(e-> {
            if(p1.isSelected()) {
                p2.setDisable(true);
                Color=0;
            }
            else
            if(!p1.isSelected()) {
                p2.setDisable(false);
                Color=-1;
            }
            if (vs.isSelected()||easy.isSelected()||Medium.isSelected()||Hard.isSelected()) {
                if(p1.isSelected()||p2.isSelected())
                    Start.setDisable(false);
                else
                    Start.setDisable(true);
            }
            else
                Start.setDisable(true);

        });
        p2.setOnAction(e-> {
            if(p2.isSelected()) {
                p1.setDisable(true);
                Color=1;
            }
            else
            if(!p2.isSelected()) {
                p1.setDisable(false);
                Color=-1;
            }
            if (vs.isSelected()||easy.isSelected()||Medium.isSelected()||Hard.isSelected()) {
                if(p1.isSelected()||p2.isSelected())
                    Start.setDisable(false);
                else
                    Start.setDisable(true);
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
