
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
    private boolean IsRun = false;
    
    // donnée propre au transfert d'information :
    public String ServerComunication = null;
    private GameReseauClient Parent;
    /**
     * 
     *
     * @pre :
     * @post:
     */
    public ConnectionToServeur(GameReseauClient Parent)
    {
        this.Parent = Parent;
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
            osClient.println("close");
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
            osClient = new PrintWriter(clientSocket.getOutputStream()); // prepare an OutputStream for the Client
            isClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // InputStream from Client
            IsRun = true;
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
    public boolean Send(String Information)
    {
        try {
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
            while (IsRun) {
                ServerComunication = isClient.readLine(); 
                //System.out.println("mess recu : " + ServerComunication);
                String [] ELEMENT = ServerComunication.split (" ");
                if(ELEMENT[0].compareTo("c")==0) Parent.AcctualyseMap(Integer.parseInt(ELEMENT[1]),Integer.parseInt(ELEMENT[2]),Integer.parseInt(ELEMENT[3]));
                if(ELEMENT[0].compareTo("n")==0) Parent.NewGame();
                if(ELEMENT[0].compareTo("W")==0) Parent.NextGame(Integer.parseInt(ELEMENT[1]));
                if(ServerComunication.compareTo("close")==0) {Parent.exit();IsRun=false;}
            } 
        } 
        catch (Exception e) {
            System.out.println("Exception while sending data" + e);
            IsRun=false;
            Parent.exit();
        } 
    }
} 








































