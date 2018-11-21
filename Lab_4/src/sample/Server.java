package sample;

import sun.rmi.runtime.Log;

import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    private int port;
    private DatagramSocket socket;
    private Thread serverRun, manage, receive;
    private boolean running = false;



    public Server(int port ) throws SocketException {
        this.port = port;
        socket = new DatagramSocket(port);
        serverRun = new Thread(new Runnable() {
            @Override
            public void run() {

                running = true;
                System.out.println("Server started on port " + port);
                manage();
                receive();
            }
        }, "ServerRun)");
    }
        private void manage() {
            manage = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (running) {

                    }
                }

            });
            manage.start();
        }
        private void receive(){
            receive = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (running) {

                    }
                }

            });
            receive.start();
        }












}


