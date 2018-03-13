/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javafx.application.Application;
import javafx.stage.Stage;
import login.LoginForm;

/**
 *
 * @author vomin
 */
public class MainClass extends Application {

    /**
     * @param args the command line arguments
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //LoginForm a=new LoginForm();
        LoginForm.main(null);
        //a.setVisible(true);
    }
    public static void main(String[] args) {
        Application.launch(args);
    }

    
}