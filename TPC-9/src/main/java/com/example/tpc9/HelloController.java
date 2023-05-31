package com.example.tpc9;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {

    @FXML
    private Label loginMessageLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    /**
     * @param e
     */
    public  void loginButtonOnAction(ActionEvent e){
        if(usernameTextField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {
            validateLogin();
        }
        else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }


    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection conectDB = connectNow.getConection();

        try{
            String verifyLogin = "SELECT Username, Password from UserAcounts where Username = ? ' " + usernameTextField.getText();
            //SELECT count(1) from UserAcounts where Username = '" + usernameTextField.getText() + "' AND Password = ' " +passwordField.getText() + "'"

            Statement statement = conectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("Welcome ");
                }
                else {
                    loginMessageLabel.setText("Invalid username/password");
                }
            }



        }catch (Exception e){
            e.printStackTrace();
        }




    }

}