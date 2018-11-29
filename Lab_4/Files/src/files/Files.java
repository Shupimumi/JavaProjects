package files;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.awt.event.ActionListener;

import java.io.*;
import java.net.*;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javafx.scene.text.Font;


public class Files extends Application {
    Socket fromserver = null;
    BufferedReader in = null;
    PrintWriter    out= null;
    BufferedReader inu = null;
    boolean autorize = false;
    boolean CreatedMode = false;
    @Override
    public void start(Stage primaryStage) {
        
        try
                    {
                    fromserver = new Socket("localhost",1941);
                    in  = new
                    BufferedReader(new 
                    InputStreamReader(fromserver.getInputStream(),"UTF-8"));
                    out = new 
                    PrintWriter(fromserver.getOutputStream(),true);
                    inu = new 
                    BufferedReader(new InputStreamReader(System.in));
                    }
                    catch (IOException e)
                    {
                        
                    }
        
        
        
        TextField login = new TextField();
        login.relocate(435,20);
        TextField pass = new TextField();
        pass.relocate(435,50);
        
        Label autorizationText = new Label("Registration");
        autorizationText.relocate(485,1);
        
        Button btn = new Button("Sign IN");
        btn.relocate(490, 85);
        
        Button getQuest = new Button("Get files");
        getQuest.relocate(250, 427);
        getQuest.setDisable(true);

        Button signUp = new Button("Sign UP");
        signUp.relocate(489,120);
        
        Label status = new Label("Status string");
        status.relocate(20,500);
        
        Label autorizeDone = new Label();
        autorizeDone.setText("Entered user:  ");
        autorizeDone.relocate(10,150);
        autorizeDone.setFont(Font.font("Cambria", 32));
        autorizeDone.setVisible(false);
        
       
        
        
        Label example = new Label("Example: FileName:UserID (Text.txt:2)");
        example.relocate(300,427);
        example.setVisible(false);
        
        
        TextArea textQuest = new TextArea();
        textQuest.relocate(20,200);
        
        TextField textAnswer = new TextField();
        textAnswer.relocate(20,427);
        
        Button sendAnswer = new Button("Send file");
        sendAnswer.relocate(20,457);
        sendAnswer.setDisable(true);
        
       
        
        
        signUp.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                
                try
                {
                    if (!login.getText().isEmpty() && !pass.getText().isEmpty())
                    {
                        if(login.getText().length()<20 && login.getText().length() < 20 && pass.getText().length() < 20)
                        {
                        out.println("signUp");
                        signUp.setDisable(true);
                        out.println(login.getText());
                        out.println(pass.getText());
                        status.setText(in.readLine());
                        signUp.setDisable(false);
                        }
                        else status.setText("Length login and password can't be more 20 char");
                    }
                    
                    
                    }
                    catch(IOException e)
                    {
                           out.close();
                    }
                
            }
        });

         getQuest.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if (!CreatedMode)
                {
              String fuser="getQuest",fserver;

                    try
                    {
                        getQuest.setDisable(true);
                        out.println(fuser);
                        fserver = in.readLine();
                        textQuest.setText(fserver);                      
                        getQuest.setDisable(false);
                        sendAnswer.setDisable(false);
                    }
                    catch (IOException e)
                    {
                        out.close();
                    }
                }
                else
                {
                    try
                    {
                        
                        if (!textQuest.getText().isEmpty() && !textAnswer.getText().isEmpty())
                        {
                        getQuest.setDisable(true);
                        out.println("create");
                        in.readLine();
                        out.println(textQuest.getText());
                        in.readLine();
                        out.println(textAnswer.getText());
                        status.setText(in.readLine());
                        getQuest.setDisable(false);
                        } else
                        {
                            status.setText("Error. Can't send empty string");
                        }
                        
                    }
                    catch (IOException e)
                    {
                        
                    }
                }
                
            }
        });
         sendAnswer.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                
                try
                {
                    if (!textAnswer.getText().isEmpty() && textAnswer.getText().length()<30)
                    {
                        sendAnswer.setDisable(true);
                        String fuser=textAnswer.getText(),fserver;
                        out.println("sendanswer");
                        out.println(fuser);
                        fserver = in.readLine();
                        status.setText(fserver);
                        sendAnswer.setDisable(false);
                        textAnswer.setText("");
                    
                    if (fserver.equalsIgnoreCase("Action was done!"))
                    {
                        sendAnswer.setDisable(true);
                        textQuest.setText("");
                    }
                    }
                    
                    
                    }
                    catch(IOException e)
                    {
                        out.close();
                    }
                
                
            }
        });
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                
                try
                {
                    if (!login.getText().isEmpty() && !pass.getText().isEmpty())
                    {
                        if(login.getText().length()<20 && login.getText().length() < 20 && pass.getText().length() < 20)
                        {
                        btn.setDisable(true); 
                        String fuser=login.getText()+":"+pass.getText(),fserver;
                        out.println(fuser);
                        fserver = in.readLine();
                        System.out.println(fserver);
                        btn.setDisable(false);
                    if (!fserver.equalsIgnoreCase("Вы успешно авторизованы!"))
                    {
                       status.setText("Wrong login or password");
                    } else 
                    {
                       getQuest.setDisable(false);
                        btn.setVisible(false);
                        login.setVisible(false);
                        pass.setVisible(false);
                        autorizeDone.setVisible(true);
                        autorizationText.setVisible(false);
                        example.setVisible(true);
                       
                        
                        signUp.setVisible(false);
                        autorizeDone.setText("Hello,"+login.getText());
                        status.setText("Registration success!");
                        autorize=true;
                    }
                    }else status.setText("Length login and password can't be more 20 char");
                        
                    }
                    
                    }
                    catch(IOException e)
                    {
                           out.close();
                    }
                
            }
        });
      
        
        
        Pane root = new Pane();
        root.getChildren().add(btn);
        root.getChildren().add(login);
        root.getChildren().add(pass);
        root.getChildren().add(textQuest);
        root.getChildren().add(status);
        root.getChildren().add(textAnswer);
        root.getChildren().add(sendAnswer);
        root.getChildren().add(autorizationText);
        root.getChildren().add(autorizeDone);
        root.getChildren().add(signUp);
        root.getChildren().add(getQuest);
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("Client-APP for Exchange");
        primaryStage.getIcons().add(new Image("file:if__81ui_2303186.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
    public static void main(String[] args) {
        launch(args);
    }
    
}
