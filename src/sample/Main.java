package sample;

import imageProcessing.Functions;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Image;

public class Main extends Application {
    public ComboBox<String> comboBox;
    public Button btnOK;
    public TextField fileNameTxtField;
    public TextField fileNameTxtField2;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main_layout.fxml"));
        fxmlLoader.setController(this);
        primaryStage.setScene(new Scene((Parent) fxmlLoader.load(), 250, 200));
        primaryStage.show();

        comboBox.setItems(getOptions());
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setOnComboBoxSelected();
            }
        });
        btnOK.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setOnBtnOkClicked();
            }
        });
    }

    private void setOnComboBoxSelected() {
        fileNameTxtField.setVisible(true);
        fileNameTxtField2.setVisible(true);
        btnOK.setVisible(true);
        switch (comboBox.getSelectionModel().getSelectedIndex()) {
            case 3:
            case 11:
                fileNameTxtField2.setVisible(false);
                break;
        }
    }

    private void setOnBtnOkClicked() {
        Image image = new Image(fileNameTxtField.getText());
        switch (comboBox.getSelectionModel().getSelectedIndex()) {
            case 0:
                Functions.doAND(image, new Image(fileNameTxtField2.getText()));
                break;
            case 1:
                Functions.doOR(image, new Image(fileNameTxtField2.getText()));
                break;
            case 2:
                Functions.doXOR(image, new Image(fileNameTxtField2.getText()));
                break;
            case 3:
                Functions.doNOT(image);
                break;
            case 4:
                Functions.doConstAddition(image, Double.parseDouble(fileNameTxtField2.getText()));
                break;
            case 5:
                Functions.doConstMultiplication(image, Double.parseDouble(fileNameTxtField2.getText()));
                break;
            case 6:
                Functions.doConstSubtraction(image, Double.parseDouble(fileNameTxtField2.getText()));
                break;
            case 7:
                Functions.doImagesAddition(image, new Image(fileNameTxtField2.getText()));
                break;
            case 8:
                Functions.doImagesSubtraction(image, new Image(fileNameTxtField2.getText()));
                break;
            case 9:
                Functions.doMasking(image, new Image(fileNameTxtField2.getText()));
                break;
            case 10:
                Functions.doSawtoothCut(image, Integer.parseInt(fileNameTxtField2.getText()));
                break;
            case 11:
                Functions.doHistogramEqualization(image);
                break;
        }
    }

    private ObservableList<String> getOptions() {
        return FXCollections.observableArrayList(
                "Логическое И",                 //0
                "Логическое ИЛИ",               //1
                "Логическое ИСКЛЮЧАЮЩЕЕ ИЛИ",   //2
                "Логическое отрицание",         //3
                "Сложение с константой",        //4
                "Умножение на константу",       //5
                "Вычитание константы",          //6
                "Сложение изображений",         //7
                "Вычитание изображений",        //8
                "Маскирование",                 //9
                "Пилообразный яркостный срез",  //10
                "Эквализация гистограммы"//,      //11
//                "Оператор Собеля",              //12
//                "Размытие (слабое)",            //13
//                "Препарирование"                //14
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
