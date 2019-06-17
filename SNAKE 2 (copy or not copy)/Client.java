import java.io.*; // for input output
import java.lang.*; // for Threads
import java.net.*; // sockets
import java.util.ArrayList ;

/**
 * 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Client implements Runnable
{

    public static final int port = 3000;
    private Socket clientSocket = null; 
    private BufferedReader isClient = null;
    private PrintWriter osClient = null;
    private boolean IsRun = true;
    private Thread thd ;
    private GameReseau Parent;
 
    // variable propre au communication :
    public String StringClient = ""; 
    
    /**
     * constructeur, initialise la classe et démarre la boucle infini de traitement
     */
    public Client(Socket clientSocket,GameReseau Parent)
    {
        
        this.Parent = Parent;
        this.clientSocket = clientSocket;
        try 
        {
            isClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // InputStream from Client
            osClient = new PrintWriter(clientSocket.getOutputStream()); // prepare an OutputStream for the Client
        }
        catch (IOException e) 
        {
            System.out.println("Cant't deal with the streams : " + e);
        }
        thd = new Thread (this) ;
        thd.start();
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
     *  permet la création d'une boucle infini qui ne s'occupe que du lien, de la communication entre le client et la base de donnée.
     * 
     * @param  
     * @return      
     */
    public void run()
    {
        while (IsRun) {
            try {
                StringClient = isClient.readLine();
                //System.out.println("String from the client : " + StringClient);
                String [] tab = StringClient.split (" "); 
                
                if(StringClient.compareTo("U")==0){Parent.NewMotionDirection2 = Parent.UP;}
                if(StringClient.compareTo("L")==0){Parent.NewMotionDirection2 = Parent.LEFT;}
                if(StringClient.compareTo("D")==0){Parent.NewMotionDirection2 = Parent.DOWN;}
                if(StringClient.compareTo("R")==0){Parent.NewMotionDirection2 = Parent.RIGHT;}
                if(StringClient.compareTo("close")==0) {Parent.exit();IsRun=false;}
                if(StringClient.compareTo("P")==0){Parent.pause = !Parent.pause;}
                Parent.gameBegin = true;
            }
            
            // gestion des multiple erreure pouvant ce produire
            catch (IOException e) {
                System.out.println("Couldn't get I/O for the connection to: ");
                IsRun = false; 
                Parent.exit();
            }
            catch (Exception e) {
                 System.out.print("cest ici que cq bug2");System.out.println(e);
                 IsRun = false; 
                 Parent.exit();
            }
        }
        
        try{Thread.sleep(100);}catch(Exception t){}
        System.out.println("fin d'un thread.......................................................");
    }
}

