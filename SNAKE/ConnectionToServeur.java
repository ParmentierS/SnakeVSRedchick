
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*; // for input output
import java.lang.*; // for Threads
import java.net.*; // sockets

/**
 * @version
 * @author 
 */

public class ConnectionToServeur extends Thread
{
    public static final int port = 3000;
    public String IPServeur = "localhost";
    public Socket clientSocket = null; 
    public BufferedReader isClient = null;
    public PrintWriter osClient = null;
    public BufferedReader readfromline = null;
    public int i = 0; 
    public int Clientconnected = 0; 
    private boolean IsRun = false;
    
    // donnée propre au transfert d'information :
    public static int ID;
    public String ServerComunication = null;
    private boolean GameIsRunning = false;
    
    /**
     * 
     *
     * @pre :
     * @post:
     */
    public ConnectionToServeur()
    {
    }
    
    /**
     * 
     *
     * @pre :
     * @post:
     */
    public void CloseConnection()
    {
        try {
            IsRun = false;
            osClient.println("End");
            osClient.flush();
            clientSocket.close();
        } 
        catch (Exception e) {
            System.out.println("Problème de fermeture de la connection" + e);
        } 
    }
    
    /**
     * 
     *
     * @pre :
     * @post:
     */
    public boolean Connection(String IP)
    {
        try {
            IPServeur = IP;
            clientSocket = new Socket(IP, port);
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }
    
    /**
     * 
     *
     * @pre :
     * @post:
     */
    public boolean ConnectCompte(String name,String password)
    {
        Send(""+name + " "+ password);
        try {
            isClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // InputStream from client
            readfromline = new BufferedReader(new InputStreamReader(System.in));
        }
        catch (IOException e) {
            System.out.println("Can't deal with the streams... : " + e);
        } 
        String Return = null;
        try {
            while (Return==null) {
                try{Thread.sleep(100);}catch(Exception e){}
                Return = isClient.readLine(); // réception des message IL DOIVENT ETRE TRAITER
            } 
        } 
        catch (Exception e) {
            System.out.println("Exception while sending data" + e);
            return false;
        } 
        String [] ELEMENT = Return.split (" ");
        if (Integer.parseInt(ELEMENT[1]) != -1)
        {ID = Integer.parseInt(ELEMENT[1]); return true;}
        else
        return false;
    }
    
    /**
     * 
     *
     * @pre :
     * @post:
     */
    public boolean Send(String Information)
    {
        try {
            osClient = new PrintWriter(clientSocket.getOutputStream()); // prepare an OutputStream for the Client
            osClient.println(Information); // Write it
            osClient.flush(); // put it onto the network
            return true;
        }
        catch (Exception e) {
            System.out.print("problème dans l'envoi d'un message : " + e);
            return false;
        }
    }
    
    /**
     * 
     *
     * @pre :
     * @post:
     */
    public String Recive()
    {
        String temp;
        if (ServerComunication == null)
        {
            while(ServerComunication==null){try{Thread.sleep(10);}catch(Exception e){}}
            temp = ServerComunication; ServerComunication = null;
            //System.out.println("mess recu : " + temp);
            return temp;
        }
        else
        {
            temp = ServerComunication; ServerComunication = null;
            //System.out.println("mess recu : " + temp);
            return temp;
        }
    }
    
    /**
     * 
     *
     * @pre :
     * @post:
     */
    public void IsRun(boolean is)
    {
        IsRun = is;
    }
    /**
     * 
     *
     * @pre :
     * @post:
     */
    public void run()
    {
        try {
            while (true) {
                ServerComunication = isClient.readLine(); 
                
                if(IsRun)
                System.out.println("mess recu : " + ServerComunication);
            } 
        } 
        catch (Exception e) {
            System.out.println("Exception while sending data" + e);
        } 
        System.out.println("Closing connection to client");
    }
} 








































