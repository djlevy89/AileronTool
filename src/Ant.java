import java.util.Random;

import javafx.animation.KeyFrame;

import javafx.animation.Timeline;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;

import javafx.scene.Group;

import javafx.scene.Scene;

import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;

import javafx.scene.shape.Circle;

import javafx.stage.Stage;

import javafx.util.Duration;

public class Ant extends Application {

    

    double currentX, currentY;

    double width = 500, height=500;

    Random random;

    //method to make the ant walk in random directions

    public void initialize(Group g) {

        //clearing all current elements in group, if exists

        g.getChildren().clear();

        //setting initial position at center

        currentX = width / 2;

        currentY = height / 2;

        //random direction generator

        random = new Random();

        //a red Circle to denote the ant

        Circle ant = new Circle(currentX, currentY, 5, Color.RED);

        g.getChildren().add(ant);

        /**

         * A TimeLine is needed to run the animation (moving ant in every 2

         * milli seconds to make it fast)

         */

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2), new EventHandler<ActionEvent>() {

            @Override

            public void handle(ActionEvent event) {

                //generating a random number between 0 and 3

                int dir = random.nextInt(4);

                if (dir == 0) {

                    //moving right 2 spaces

                    currentX += 2;

                } else if (dir == 1) {

                    //left

                    currentX -= 2;

                } else if (dir == 2) {

                    //down

                    currentY += 2;

                } else {

                    //up

                    currentY -= 2;

                }

                //creating a circle to represent the current position in the path

                Circle dot = new Circle(currentX, currentY, 2, Color.BLACK);

                //adding to the group

                g.getChildren().add(dot);

                //moving the ant too

                ant.setCenterX(currentX);

                ant.setCenterY(currentY);

                //checking for out of bounds

                if (currentX < 0 || currentX > width || currentY < 0 || currentY > height) {

                    //re positioning at center

                    currentX = width / 2;

                    currentY = height / 2;

                    ant.setCenterX(currentX);

                    ant.setCenterY(currentY);

                }

            }

        }));

        timeline.setCycleCount(Timeline.INDEFINITE); //will loop infinitely

        timeline.play(); //starting the animation

    }

    @Override

    public void start(Stage primaryStage) {

        //a pane to include the group

        Pane pane = new Pane();

        //a group to display the path of ant as a series of circles

        Group g = new Group();

        pane.getChildren().add(g);

        //initializing the animation

        initialize(g);

        //creating a scene and adding the pane

        Scene scene = new Scene(pane, width, height);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Ant:Random Walk");

        primaryStage.show();

        //adjusting the values of width and height as per window resizing

        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {

            width=newVal.doubleValue();

        });

        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {

            height=newVal.doubleValue();

        });

    }

    public static void main(String[] args) {

        launch(args);

    }

}
