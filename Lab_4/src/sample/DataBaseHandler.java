package sample;
import java.sql.*;
import java.sql.ResultSet;

public class DataBaseHandler {
    Connection dbConnection;
//ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ
    public Connection getDbConnection() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        System.out.println("***** MySQL JDBC Connection Testing *****");
        String connectionString = "jdbc:mysql://127.0.0.1:3306/?user=root"+
                "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        dbConnection = DriverManager.getConnection(connectionString,
                "root", "root");
        return dbConnection;
    }
    //SQL ЗАПРОС
    public void signUpUser(String firstName, String secondName, String login, String pass, String gender){
        String insert ="INSERT INTO `client-server_app`.`users` (`First name`, `Second name`, `Login`, `Password`, `Gender`) " +
                "VALUES ('"+firstName+"', '"+secondName+"', " +
                "'"+login+"', '"+pass+"', '"+gender+"');";
        try{
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate(insert);
        }catch(SQLException e){
            System.out.println("Error: " + e);
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //System.out.println("Error: " + e);

             e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    public boolean  getUser(String login, String pass) throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException {               //МЕТОД ДЛЯ РЕАЛИЗАЦИИ АВТОРИЗАЦИИ




        // РЕАЛИЗАЦИЯ ЗАПРОСА К БАЗЕ ДАННЫХ

        Statement stmt = getDbConnection().createStatement();
        String select ="SELECT Login, Password FROM `client-server_app`.users;";
        ResultSet resultSet = stmt.executeQuery(select);

        //ПОИСК СОВПАДЕНИЙ С БАЗОЙ ДАННЫХ

        while(resultSet.next()){
            if(login.equals(resultSet.getString("Login"))&&pass.equals(resultSet.getString("Password"))){
                System.out.println("Successfully !!!");
                return true;
            }else{
                System.out.println("Incorrect...");

            }

        }
        return false;




    }


}
