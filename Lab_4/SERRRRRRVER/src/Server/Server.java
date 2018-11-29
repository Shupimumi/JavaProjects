package Server;
import java.lang.Thread;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.sql.*;
import java.util.Random;
import java.util.logging.*;



class JoinedClient extends Thread
{
    private static final Logger log = Logger.getLogger(JoinedClient.class.getName() );
    
    private int id;
    private boolean questSended = false;
    private int questID = 0;
    private String username=null;
            
    BufferedReader in = null;
    PrintWriter    out= null;

    ServerSocket servers = null;
    Socket       fromclient = null;
    
    
    
    JoinedClient(Socket clientInfo,int count)
    {
        fromclient=clientInfo;
        id=count;
        try {
             FileInputStream fis =  new FileInputStream("logging.properties.txt");
    LogManager.getLogManager().readConfiguration(fis);
    log.setLevel(Level.ALL);
    log.addHandler(new java.util.logging.FileHandler(id+"client_log", 1000000, 2));
    //log.setUseParentHandlers(false);
    fis.close();

        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
    }
    
    private Connection getConnect() throws SQLException // создание подключения к БД
    {
        
        String url = "jdbc:mysql://127.0.0.1:3306/?user=root"+
                "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, username, password);
        log.info("Сервер подключился к БД");
        return conn;
    }
    
    
    private boolean autorize(String logPass)
    {
        StringTokenizer st = new StringTokenizer(logPass, ":");
        String login=st.nextToken();
        String pass=st.nextToken();
        
        Connection conn = null;
        Statement stmt = null;
        
        try
        {
            conn=getConnect();
            stmt=conn.createStatement();
            System.out.println(id + " делает запрос");
            log.finest("ID "+id+" делает запрос на авторизацию log="+login+", pass="+pass);
            ResultSet res = stmt.executeQuery("SELECT * FROM `client-server_app`.users");
            while (res.next())
            {
               String b =res.getString(2);
               if (pass.equals(b)) 
               {
                   username=login;
                   return true;
               }     
            }
            return false;
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return false;
        }
        
    }
    

    private boolean signUp(String login, String pass)
    {
        Connection conn = null;
        Statement stmt = null;
        try
        {
            conn=getConnect();
            stmt=conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM `client-server_app`.users WHERE login LIKE '"+login+"'");
            if (res.next())
            {
               log.finest("ID "+id+" Не удалось зарегистрироваться, пользователь с таким именем уже существует");
               return false;
            }
            log.finest("ID "+id+" делает запрос в БД для регистрации");
            stmt.executeUpdate("INSERT INTO `client-server_app`.`users` (`login`, `password`) VALUES ('"+login+"', '"+pass+"');");
            return true;
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return false;
        }
    }
    private String getQuest()
    {
         // определяем объект для каталога
        String path = "C://Games//users for lab//" +id ;
        File dir = new File(path);
        String textQuest = "Путь: "+path
                +"  У вас есть эти файлы/папки: ";
        log.finest("ID "+id+" делает запрос на получение файлов");
         
        
        
       
        // если объект представляет каталог
        if(dir.isDirectory())
        {
            // получаем все вложенные объекты в каталоге
            for(File item : dir.listFiles()){
              
                 if(item.isDirectory()){
                      
                     textQuest += item.getName() + "    folder;     "  ;
                     
                 }
                 else{
                      
                     textQuest += item.getName() + "     file;       "  ;
                    
                 }
             }
             return textQuest;   
        }
        return textQuest;
    }
private boolean checkAnswer(String answer)
    {     
        String textAnswer = null;
        log.finest("ID "+id+" делает запрос передачу файла");
        if(id == 0){
             StringTokenizer st = new StringTokenizer (answer, ":");
             String file=st.nextToken();
             String user=st.nextToken();                      
             try {
                final File myFile = new File("C:\\Games\\users for lab\\0\\"+file);
                    if(myFile.renameTo(new File("C:\\Games\\users for lab\\" + user + "\\" + file))) {
                        System.out.println("File is moved successful!");
                 } else {
                         System.out.println("File is failed to move!");
                     }
                }catch(Exception e){
                  e.printStackTrace();
                }
        }
        if(id==1){
            StringTokenizer st = new StringTokenizer (answer, ":");
             String file=st.nextToken();
             String user=st.nextToken();                      
             try {
                final File myFile = new File("C:\\Games\\users for lab\\1\\"+file);
                    if(myFile.renameTo(new File("C:\\Games\\users for lab\\" + user + "\\" + file))) {
                        System.out.println("File is moved successful!");
                 } else {
                         System.out.println("File is failed to move!");
                     }
                }catch(Exception e){
                  e.printStackTrace();
                }
        }
        if(id == 2){
            StringTokenizer st = new StringTokenizer (answer, ":");
             String file=st.nextToken();
             String user=st.nextToken();           
             try {
                final File myFile = new File("C:\\Games\\users for lab\\2\\"+file);
                    if(myFile.renameTo(new File("C:\\Games\\users for lab\\" + user + "\\" + file))) {
                        System.out.println("File is moved successful!");
                 } else {
                         System.out.println("File is failed to move!");
                     }
                }catch(Exception e){
                  e.printStackTrace();
                }
        }
        if(id == 3){
            StringTokenizer st = new StringTokenizer (answer, ":");
             String file=st.nextToken();
             String user=st.nextToken();           
              try {
                final File myFile = new File("C:\\Games\\users for lab\\3\\"+file);
                    if(myFile.renameTo(new File("C:\\Games\\users for lab\\" + user + "\\" + file))) {
                        System.out.println("File is moved successful!");
                 } else {
                         System.out.println("File is failed to move!");
                     }
                }catch(Exception e){
                  e.printStackTrace();
                }
        }
        if(id == 4){
            StringTokenizer st = new StringTokenizer (answer, ":");
             String file=st.nextToken();
             String user=st.nextToken();           
             try {
                final File myFile = new File("C:\\Games\\users for lab\\4\\"+file);
                    if(myFile.renameTo(new File("C:\\Games\\users for lab\\" + user + "\\" + file))) {
                        System.out.println("File is moved successful!");
                 } else {
                         System.out.println("File is failed to move!");
                     }
                }catch(Exception e){
                  e.printStackTrace();
                }
        }
        
        return true;
    }
    public void run()
    {
        System.out.println("Подключен и выполняется клиент с id "+id);
        log.fine("ID "+id+" подключен к серверу.");
        boolean autorize=false;
        try
        {
        in  = new BufferedReader(new 
        InputStreamReader(fromclient.getInputStream(),"UTF-8"));
        out = new PrintWriter(fromclient.getOutputStream(),true);
        String         input,output;
        log.fine("ID "+id+" ожидание запросов.");
        System.out.println("Ожидаем запросов от клиента "+id);   
        while ((input = in.readLine())!= null) {
            System.out.println(id + " прислал " + input);
        log.fine("ID "+id+" передал на сервер:" + input);
        
      if (input.equalsIgnoreCase("sendanswer"))
        {
            if(checkAnswer(in.readLine()))
            {
                out.println("Action was done!");
                log.finest("ID "+id+" правильно ответил на загадку");
                questSended=false;
                questID=0;
            }
        }
        if (input.equalsIgnoreCase("signUp"))
        {
            log.fine("ID "+id+" пытается зарегистрироваться");
            String login,pass;
            login = in.readLine();
            pass= in.readLine();
            if(signUp(login,pass)) 
            {
                log.fine("ID "+id+" зарегистрирован!");
                out.println("Account has been signed up!");
            }
            else
            {
                log.fine("ID "+id+" не удалось зарегистрировать аккаунт. Имя уже существует");
                out.println("Error, that login already used");
            }
            continue;
        }
        if (input.equalsIgnoreCase("getQuest"))
        {
            log.fine("ID "+id+" отправил запрос на получение текста загадки.");
           
            out.println(this.getQuest());
           
            continue;
        }

        if (autorize==false)
        {
        if (this.autorize(input)==false) 
        {
            System.out.println("Не найден в базе");
            log.fine("ID "+id+" ввел неверные данные. Пользователь не найден в БД");
            out.println("Неверный логин или пароль");
        }else
        {
            out.println("Вы успешно авторизованы!");
            System.out.println("id "+id+" авторизовался");
            log.fine("ID "+id+" авторизовался на сервере");
            autorize=true;
            
        }
            
        }
        in  = new BufferedReader(new 
        InputStreamReader(fromclient.getInputStream(),"UTF-8"));
        out = new PrintWriter(fromclient.getOutputStream(),true);
        }
        
        out.close();
        in.close();
        fromclient.close();
        System.out.println("Отключился клиент с Id "+id);
        log.fine("ID "+id+" отключился от сервера");
        }
        catch (IOException e)
        {
            log.fine("ID "+id+" произошла ошибка на стороне клиента");
            System.out.println("Ошибка у пользователя");
        } 
    
    
    }
}



public class Server {
private static final Logger log = Logger.getLogger( Server.class.getName() ); //// создаем логер
static int count=0;


  public static void main(String[] args) throws IOException {
      try {
            FileInputStream fis =  new FileInputStream("logging.properties.txt");
            LogManager.getLogManager().readConfiguration(fis);
            log.setLevel(Level.ALL);
            log.addHandler(new java.util.logging.FileHandler("server_log", 1000000, 5));
            fis.close();


        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }


      
    ArrayList<JoinedClient> clientList = new ArrayList<JoinedClient>(); /// создаем список для добавления подключенных пользователей
    System.out.println(" >> Добро пожаловать на SERRRRVER");
    log.fine("Сервер запущен");
    BufferedReader in = null;  /// создаем объект для чтение потока в буфер
    PrintWriter    out= null;  

    ServerSocket servers = null; // объявляем сокет сервера
    Socket       fromclient = null; // сокет для подключения клиентов
    
    try {
      servers = new ServerSocket(1941,4); // инициализируем сокет сервера с портом 1941 на 10 макс. подключений
    } catch (IOException e) {
      System.out.println("Невозможно прослушать порт 1941");
      log.log(Level.FINE ,"Невозможно прослушать порт 1941: ",e);
      System.exit(-1);
    }

    while(count < 4)
    {
      try {
      System.out.println(" >> Ожидание подключений...");
      clientList.add( new JoinedClient(servers.accept(),count)); /// метод, заставляющий сокет ждать клиента
      System.out.println(" >> Клиент подключен! Его id = "+count);
      clientList.get(count).start();
      count++;
    } catch (IOException e) {
      log.log(Level.FINE ,"Невозможно принять подключение: ",e);
      System.out.println("Невозможно принять");
      System.exit(-1);
    }  
    }
    

    servers.close();
  }
}
