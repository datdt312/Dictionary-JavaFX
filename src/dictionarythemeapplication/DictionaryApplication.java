/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionarythemeapplication;

import com.jfoenix.controls.JFXListView;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Chương trình từ điển
 * @since 2018 - 09 - 25
 * @version 1.0
 * @author haidb va dathn
 */
public class DictionaryApplication extends Application {
    private double xOffset = 0;
    private double yOffset = 0;
    
    /**
     * Hàm start chạy chương trình từ điẻn
     * @param stage cửa sổ từ điển
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Dictionary");
        stage.getIcons().add(new Image("https://cdn1.iconfinder.com/data/icons/literary-genres/512/book_Navigation_literature_Genre_Literary-05-512.png"));
        //stage.getIcons().add(new Image("book_fire.png"));
        //stage.getIcons().add(b_f);
        //while (!convertEditableThemeToRunableTheme.convertEditableToRunable())
        //    convertEditableThemeToRunableTheme.convertEditableToRunable();
        Parent root = FXMLLoader.load(getClass().getResource("editable_theme.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("runable_theme.fxml"));
        stage.initStyle(StageStyle.TRANSPARENT);

        //set mouse pressed
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        //set mouse drag
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Đây là hàm main chạy chương trình từ điển
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        launch(args);
    }

}
