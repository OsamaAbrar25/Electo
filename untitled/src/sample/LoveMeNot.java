package sample;

import javafx.scene.Scene;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoveMeNot extends Application {

    public static void main(String[] args) throws Exception { launch(args); }

    @Override public void start(final Stage stage) throws Exception {
        Label label = new Label(WORDS);
        label.setWrapText(true);
        label.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 20; -fx-text-fill: darkred;");

        ImageView image = new ImageView(IMAGE);
        image.setOpacity(0.3);

        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: mistyrose; -fx-padding: 10;");
        layout.getChildren().setAll(image, label);

        stage.setTitle("Love Me Not");
        stage.setScene(new Scene(layout));
        stage.show();
    }

    // creates a triangle.
    private static final String WORDS =
            "Love not me for comely grace," +
                    "For my pleasing eye or face," +
                    "Nor for any outward part," +
                    "No, nor for my constant heartn" +
                    "For these may fail, or turn to ill." +
                    "So thou and I must sever." +
                    "Keep therefore a true woman’s eye," +
                    "And love me still, but know not why," +
                    "So hast thou the same reason still" +
                    "To doat upon me ever.";

    private static final Image IMAGE =
            new Image("http://icons.iconarchive.com/icons/artdesigner/gentle-romantic/256/rose-icon.png");
}