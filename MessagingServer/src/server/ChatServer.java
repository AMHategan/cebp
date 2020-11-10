package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer{
	
	List<ClientThread> clientThreads;
	private static final int portNumber = 4444;
	private int serverPort;
	
	public ChatServer(int portNumber){
        this.serverPort = portNumber;
    }
	
	public void process() throws Exception {
		clientThreads = new ArrayList<ClientThread>();
		ServerSocket serverSocket = null;
		try {
            serverSocket = new ServerSocket(serverPort); 
            acceptClients(serverSocket);
        } catch (IOException e){
            System.err.println("Port not available for listening: "+serverPort);
            System.exit(1);
        }
	}
	
	private void acceptClients(ServerSocket serverSocket){

        System.out.println("server starts port = " + serverSocket.getLocalSocketAddress());
        while(true){
            try{
                Socket socket = serverSocket.accept();
                System.out.println("accepts: " + socket.getRemoteSocketAddress());
                ClientThread client = new ClientThread(this, socket);
                Thread thread = new Thread(client);
                thread.start();
                clientThreads.add(client);
            } catch (IOException ex){
                System.out.println("Accept failed on: "+serverPort);
            }
        }
    }
	
	public List<ClientThread> getClients(){
        return clientThreads;
    }
	
	public static void main(String[] args) throws Exception {
		ChatServer server = new ChatServer(portNumber);
		server.process();
	}
}