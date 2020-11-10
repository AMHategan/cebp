package client;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client 
{
    private String serverHost;
    private int serverPort;
    private static String userName;
    private static final String host = "localhost";
    private static final int portNumber = 4444;
    private static Autentificate a = new Autentificate();
    Scanner scan;
    	
	private Client(String host, int portNumber){
        this.serverHost = host;
        this.serverPort = portNumber;
    }
	
	private void startClient(Scanner scan){
        try{
            Socket socket = new Socket(serverHost, serverPort);
            Thread.sleep(200);
            ServerThread serverThread = new ServerThread(socket,userName);
            Thread serverAccessThread = new Thread(serverThread);
            serverAccessThread.start();
            while(serverAccessThread.isAlive()){
                if(scan.hasNextLine()){
                    serverThread.addNextMessage(scan.nextLine());
                }
            }
        }catch(IOException ex){
            System.err.println("Fatal Connection error!");
            ex.printStackTrace();
        }catch(InterruptedException ex){
            System.out.println("Interrupted");
        }
    }
	
	public static void main(String[] args){
		//a.createNewTable();
		String readName = null;
        Scanner scan = new Scanner(System.in);
        System.out.println("Please input username:");
        while(readName == null || readName.trim().equals("")){
            readName = scan.nextLine();
            if(readName.trim().equals("")){
                System.out.println("Invalid. Please enter again:");
            }
        }
        userName=readName;
        Client client = new Client(host, portNumber);
        client.startClient(scan);
        
	}
}