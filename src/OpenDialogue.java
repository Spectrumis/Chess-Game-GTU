import javafx.scene.Scene;
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
public class OpenDialogue {
    public static void OpenMenu()
    {
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Welcome To GTUChess!");
        window.setMinWidth(400);
        window.setMinHeight(250);
        window.setMaxWidth(400);
        window.setMaxHeight(250);
        Text Space=new Text();
        Text Space2=new Text();
        Space.setText("   ");
        Space2.setText("   ");
        Text Space3=new Text();
        Space3.setText("");
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
        Layout7.getChildren().addAll(Space2,iv1,iv2);
        Layout8.getChildren().addAll(Space3,p1,p2);
        Layout4.getChildren().addAll(Layout2,Layout3,Layout5,Layout6);
        Layout.getChildren().addAll(l1,Layout4,l2,Layout7,Layout8);

        vs.setOnAction(e-> {
                    if (vs.isSelected()) {
                        easy.setDisable(true);
                        Medium.setDisable(true);
                        Hard.setDisable(true);
                    }
                    else
                    if (!vs.isSelected()) {
                        easy.setDisable(false);
                        Medium.setDisable(false);
                        Hard.setDisable(false);
                    }

        });
        easy.setOnAction(e-> {
            if (easy.isSelected()) {
                vs.setDisable(true);
                Medium.setDisable(true);
                Hard.setDisable(true);
            }
            else
            if (!easy.isSelected()) {
                vs.setDisable(false);
                Medium.setDisable(false);
                Hard.setDisable(false);
            }
                });
        Medium.setOnAction(e-> {
            if (Medium.isSelected()) {
                vs.setDisable(true);
                easy.setDisable(true);
                Hard.setDisable(true);
            }
            else
            if (!Medium.isSelected()) {
                vs.setDisable(false);
                easy.setDisable(false);
                Hard.setDisable(false);
            }
        });
        Hard.setOnAction(e-> {
            if (Hard.isSelected()) {
                vs.setDisable(true);
                easy.setDisable(true);
                Medium.setDisable(true);
            }
            else
            if (!Hard.isSelected()) {
                vs.setDisable(false);
                easy.setDisable(false);
                Medium.setDisable(false);
            }
        });
        Scene s1=new Scene(Layout);
        window.setScene(s1);
        window.show();
    }
}
