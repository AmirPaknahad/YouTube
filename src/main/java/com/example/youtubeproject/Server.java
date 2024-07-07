package com.example.youtubeproject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int port = 5000;
    private static ArrayList<Socket> clients = new ArrayList<>();
    private static ServerSocket server = null;
    private static ExecutorService pool = Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        try {
            server = new ServerSocket(port);
            System.out.println("server started.");
            while (true) {
                Socket client = server.accept();
                clients.add(client);
                ClientHandler clientHandler = new ClientHandler(client);
                pool.execute(clientHandler);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
