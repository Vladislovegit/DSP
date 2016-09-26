package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;

public class Main extends Application {

    @FXML
    private BarChart bc;
    @FXML
    private BarChart bc_dissection;
    @FXML
    private BarChart bc_sobel;
    @FXML
    private TextField mnTextField;
    @FXML
    private TextField mxTextField;
    @FXML
    private TextField fileNameTextField;
    @FXML
    private javafx.scene.control.Button okButton ;
    @FXML
    private Text statusText;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main_layout.fxml"));
        fxmlLoader.setController(this);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene((Parent) fxmlLoader.load(), 1500, 500));
        primaryStage.show();

        bc.setBarGap(0);
        bc.setCategoryGap(0);
        bc_dissection.setBarGap(0);
        bc_dissection.setCategoryGap(0);
        bc_sobel.setBarGap(0);
        bc_sobel.setCategoryGap(0);

        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                onOkClick();
            }
        });
    }

    private void onOkClick() {
        String fileName = fileNameTextField.getText();
        BufferedImage image = ImageLoader.load("images/src/" + fileName);
        Integer[][] src = ImageLoader.getGrayValues(image);
        bc.setTitle("Source");
        bc.getData().addAll(ImageProcessing.getHistogramSeries(src));

        Integer[][] res = ImageProcessing.dissection(src,
                Integer.valueOf(mnTextField.getText()),
                Integer.valueOf(mxTextField.getText()));
        ImageLoader.save(res, "images/res/" + fileName + "_dissection", image);
        bc_dissection.setTitle("Dissection");
        bc_dissection.getData().addAll(ImageProcessing.getHistogramSeries(res));

        Integer[][] red_filt = ImageProcessing.SobelFilter(ImageLoader.getRedValues(image));
        Integer[][] green_filt = ImageProcessing.SobelFilter(ImageLoader.getGreenValues(image));
        Integer[][] blue_filt = ImageProcessing.SobelFilter(ImageLoader.getBlueValues(image));
        ImageLoader.save(red_filt, green_filt, blue_filt, "images/res/" + fileName + "_sobel", image);

        bc_sobel.setTitle("Sobel Operator");
        bc_sobel.getData().addAll(ImageProcessing.getHistogramSeries(ImageLoader.getGrayValues(ImageLoader.load("images/res/" + fileName + "_sobel"))));

        red_filt = ImageProcessing.degradation(ImageLoader.getRedValues(image));
        green_filt = ImageProcessing.degradation(ImageLoader.getGreenValues(image));
        blue_filt = ImageProcessing.degradation(ImageLoader.getBlueValues(image));
        ImageLoader.save(red_filt, green_filt, blue_filt, "images/res/" + fileName + "_degradation", image);

        statusText.setText("Success");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
