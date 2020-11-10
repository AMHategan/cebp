package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientThread implements Runnable{

	private ChatServer server;
	private Socket socket;
	private PrintWriter clientOut;
	private Scanner in;
	
	public ClientThread(ChatServer server,Socket socket){
		this.server=server;
		this.socket=socket;
	}
	
	private PrintWriter getWriter(){
        return clientOut;
    }
	
	public void run() {
        try{
            this.clientOut = new PrintWriter(socket.getOutputStream(), false);
            in = new Scanner(socket.getInputStream());
            while(!socket.isClosed()){
                if(in.hasNextLine()){
                    String input = in.nextLine();
                    for(ClientThread thatClient : server.getClients()){
                        PrintWriter thatClientOut = thatClient.getWriter();
                        if(thatClientOut != null){
                            thatClientOut.write(input + "\r\n");
                            thatClientOut.flush();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
