package sample;

import entities.MyCharacter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public AnchorPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MyCharacter myCharacter = new MyCharacter(-10,465,100,100,mainPane);
        mainPane.setBackground(new Background(new BackgroundImage(new Image("image/backgroundGame/background_3.png",1100,600,false,true),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        mainPane.setOnKeyPressed(myCharacter::keyEvent);
        mainPane.setOnKeyReleased(event -> myCharacter.stop());
    }
}
