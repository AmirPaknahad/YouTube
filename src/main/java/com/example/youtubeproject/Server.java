package com.example.youtubeproject;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int port = 5000;
    private static ArrayList<Socket> clients = new ArrayList<>();
    private ServerSocket server = null;
    private static ExecutorService pool = Executors.newFixedThreadPool(10);
}
