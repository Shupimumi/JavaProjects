
package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;    //Автоматически
                                         //Созданные
    @FXML
    private URL location;                //Методы

    @FXML
    private Button loginSignUpButton;   // Кнопка РЕГИСТРАЦИЯ

    @FXML
    private TextField login_field;      // Поле логина

    @FXML
    private Button loginSignInButton;   //Кнопка ВОЙТИ

    @FXML
    private PasswordField password_field;  //Поле пароля

    @FXML
    void initialize() {              // Поле для создания событий


       // Нажатие кнопки ВОЙТИ
        loginSignInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String login_Text = login_field.getText().trim();
                String login_Pass = password_field.getText().trim();
                if (!login_Text.equals("")&&!login_Pass.equals("")){
                    loginUser(login_Text, login_Pass);                  // ПРОВЕРКА ПУСТЫЕ ЛИ ПОЛЯ ВВОДА
                }else{
                    System.out.println("Error: strings are empty");
                }

                //ПРОВЕРКА ВВЕДЁННЫХ ПАРОЛЯ И ЛОГИНА С БАЗОЙ ДАННЫХ
                DataBaseHandler db = new DataBaseHandler();
                try {
                   if(db.getUser(login_Text, login_Pass)==true){
                       loginSignInButton.getScene().getWindow().hide();
                       FXMLLoader loader = new FXMLLoader();
                       loader.setLocation(getClass().getResource("/sample/app.fxml"));  // УКАЗЫВАЕМ ПУТЬ К НОВОМУ ОКНУ ИЗ ФАЙЛА SIGNUP.FXML

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
                } catch (ClassNotFoundException e) {
                   // e.printStackTrace();
                    System.out.println("Error: " + e);
                } catch (SQLException e) {
                   // e.printStackTrace();
                    System.out.println("Error: " + e);

                } catch (InstantiationException e) {
                    //e.printStackTrace();
                    System.out.println("Error: " + e);

                } catch (IllegalAccessException e) {
                   // e.printStackTrace();
                    System.out.println("Error: " + e);

                }

            }
        });

          // НАЖАТИЕ КНОПКИ РЕГИСТРАЦИИ
        loginSignUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginSignUpButton.getScene().getWindow().hide(); // ЗАКРЫВАЕТСЯ ОСНОВНОЕ ОКНО SAMPLE.FXML
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/signUp.fxml"));  // УКАЗЫВАЕМ ПУТЬ К НОВОМУ ОКНУ ИЗ ФАЙЛА SIGNUP.FXML

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

    private void loginUser(String login_text, String login_pass) {
    }
}
