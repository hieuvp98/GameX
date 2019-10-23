package entities;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class MyCharacter extends Sprite {
    private ImageView imageView;
    private static final int speed = 30;
    private static final int distance = 5;
    private boolean moving = false;
    private boolean isJumping = false;
    private ArrayList<Image> walking;
    private ArrayList<Image> jumping;
    private int count = 0;
    private String beginImg;
    private Pane parent;

    public MyCharacter(int x, int y, int width, int height,Pane parent) {
        super(x, y, width, height);
        this.parent = parent;
        this.beginImg = "image/ogre_idle_000.png";
        imageView = new ImageView(beginImg);
        imageView.setX(this.x);
        imageView.setY(this.y);
        imageView.setFitWidth(this.width);
        imageView.setFitHeight(this.height);
        loadImage();
        parent.getChildren().add(imageView);
    }

    private void loadImage() {
        walking = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            String img = "0_Ogre_Running_0";
            if (i < 10) {
                img = img + "0" + i;
            } else img = img + i;
            walking.add(new Image("image/run/" + img + ".png"));
        }
        jumping = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            jumping.add(new Image("image/jump/0_Ogre_Jump_Start_00" + i + ".png"));
        }
    }

    public void keyEvent(KeyEvent event) {
        if (moving) return;
        KeyCode code = event.getCode();
        if (code == KeyCode.RIGHT && x < 530)
            move(distance);
        else if (code == KeyCode.LEFT && x > -10)
            move(-distance);
        else if (code == KeyCode.UP)
            jump();
    }

    private void move(int dis) {
        if (dis < 0)
            imageView.setScaleX(-1);
        else imageView.setScaleX(1);
        if (!isJumping) {
            imageView.setImage(walking.get(count));
            count++;
            if (count == walking.size()) count = 0;
        }
        moving = true;
        TranslateTransition tt = new TranslateTransition(Duration.millis(speed), this.imageView);
        tt.setFromX(this.x);
        this.x += dis;
//        if (x > 500){
//            x = 0;
//            imageView.setX(x);
//            moving = false;
//            return;
//        }
//        if (x < -10){
//            x = 500;
//            imageView.setX(700);
//            moving = false;
//            return;
//        }
        tt.setToX(this.x);
        tt.setOnFinished(event -> {
            moving = false;
            imageView.setX(this.x);
        });
        tt.playFromStart();
    }

    private void jump() {
        if (isJumping) return;
        else isJumping = true;
        imageView.setImage(jumping.get(5));
        TranslateTransition ttUp = new TranslateTransition(Duration.millis(500), this.imageView);
        TranslateTransition ttDown = new TranslateTransition(Duration.millis(500), this.imageView);
        ttUp.setToY(this.y - 530);
        ttDown.setToY(0);
        ttUp.setOnFinished(event -> ttDown.play());
        ttDown.setOnFinished(event -> {
            imageView.setImage(new Image(beginImg));
            isJumping = false;
        });
        ttUp.playFromStart();

    }

    public void stop() {
        //imageView.setImage(new Image(beginImg));
    }

    public ImageView getImageView() {
        return imageView;
    }
}
