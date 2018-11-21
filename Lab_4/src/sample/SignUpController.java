package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController  {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private TextField signUp_Surname;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField signUp_Name;

    @FXML
    private TextField login_field;

    @FXML
    private CheckBox signUp_CheckBoxMale;

    @FXML
    private CheckBox signUp_CheckBoxFemale;

    @FXML
    void initialize() {

        loginSignUpButton.setOnAction(new EventHandler<ActionEvent>() {
            DataBaseHandler dbHandler = new DataBaseHandler();
            @Override
            public void handle(ActionEvent event) {
                dbHandler.signUpUser(signUp_Name.getText(), signUp_Surname.getText(),
                    login_field.getText(), password_field.getText(),
                        "Male");
                loginSignUpButton.getScene().getWindow().hide(); // ЗАКРЫВАЕТСЯ ОКНО С РЕГИСТРАЦИЕЙ
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/sample.fxml"));  // УКАЗЫВАЕМ ПУТЬ К НОВОМУ ОКНУ ИЗ ФАЙЛА SIGNUP.FXML

                try{
                    loader.load();
                }catch(IOException e){
                    System.out.println("Error: " + e);
                }
                Parent root= loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Client-Server App");
                stage.showAndWait();

            }

        });


    }
}
