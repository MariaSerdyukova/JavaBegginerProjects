package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class TicTacToeApplication extends Application {

    @Override
    public void start(Stage window) {
        BorderPane layout = new BorderPane();
        GridPane buttons = new GridPane();

        Label turn = new Label("X");
        Label topText = new Label("Turn: " + turn.getText());

        Button button11 = new Button();
        Button button12 = new Button();
        Button button13 = new Button();
        Button button21 = new Button();
        Button button22 = new Button();
        Button button23 = new Button();
        Button button31 = new Button();
        Button button32 = new Button();
        Button button33 = new Button();

        setOnButtonAction(button11, topText, turn, buttons, topText);
        setOnButtonAction(button12, topText, turn, buttons, topText);
        setOnButtonAction(button13, topText, turn, buttons, topText);
        setOnButtonAction(button21, topText, turn, buttons, topText);
        setOnButtonAction(button22, topText, turn, buttons, topText);
        setOnButtonAction(button23, topText, turn, buttons, topText);
        setOnButtonAction(button31, topText, turn, buttons, topText);
        setOnButtonAction(button32, topText, turn, buttons, topText);
        setOnButtonAction(button33, topText, turn, buttons, topText);

        buttons.add(button11, 0, 0);
        buttons.add(button12, 0, 1);
        buttons.add(button13, 0, 2);
        buttons.add(button21, 1, 0);
        buttons.add(button22, 1, 1);
        buttons.add(button23, 1, 2);
        buttons.add(button31, 2, 0);
        buttons.add(button32, 2, 1);
        buttons.add(button33, 2, 2);

        layout.setTop(topText);
        layout.setCenter(buttons);

        Scene scene = new Scene(layout);

        window.setScene(scene);
        window.show();
    }

    public static void setOnButtonAction(Button button, Label topLabel, Label label, GridPane buttons, Label topText) {
        button.setOnAction((event) -> {
            if (button.getText().equals("")) {
                button.setText(label.getText());
                if (label.getText().equals("X")) {
                    label.setText("O");
                } else {
                    label.setText("X");
                }

                topLabel.setText("Turn: " + label.getText());
            }
            
            if (setOnFinalTextIfSomebodyWin(buttons)) {
                topText.setText("The end!");
            }

        }
        );
    }

    public static Boolean setOnFinalTextIfSomebodyWin(GridPane buttons) {
        return (checkWin(buttons, "X") || checkWin(buttons, "O"));
    }
    
    
    public static Boolean checkWin(GridPane buttons, String text)
    {
        ArrayList<Pair<Integer, Integer>> list = new ArrayList<>();
        ObservableList<Node> childrens = buttons.getChildren();
        for (Node node : childrens) {
            if (node instanceof Button){
                Button cNode = (Button)node;
                if(cNode.getText() == text)
                {
                    list.add(new Pair<>(buttons.getRowIndex(node), buttons.getColumnIndex(node)));
                }
            }
        }
        
        if((list.stream().filter(pair -> pair.getKey() == 0).count() == 3) 
                || (list.stream().filter(pair -> pair.getKey() == 1).count() == 3)
                || (list.stream().filter(pair -> pair.getKey() == 2).count() == 3)
                || (list.stream().filter(pair -> pair.getValue() == 0).count() == 3)
                || (list.stream().filter(pair -> pair.getValue() == 1).count() == 3)
                || (list.stream().filter(pair -> pair.getValue() == 2).count() == 3)
                || (list.stream().filter(pair -> pair.getKey() == pair.getValue()).count() == 3)
                || (list.stream().filter(pair -> pair.getKey() == 2 - pair.getValue()).count() == 3)) {
            
            return true;
        } 
        
        return false;
        
    }
    
    
    public static Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        launch(TicTacToeApplication.class);
        System.out.println("Hello world!");
    }

}
