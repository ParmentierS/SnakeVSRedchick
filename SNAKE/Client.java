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

    private static PrintWriter[] ListPlayer = new PrintWriter[100];
    private static boolean isInit = false;
    public static final int port = 3000;
    private Socket clientSocket = null; 
    private BufferedReader isClient = null;
    private PrintWriter osClient = null;
    private BufferedReader readfromline = null;
    private boolean IsRun = true;
    private Thread thd ;
 
    // variable propre au communication :
    private boolean IsFirstContact = true;
    public String StringClient = ""; // sert a envoiyer les infos 
    public String Player;
    public int ID; 
    
    /**
     * constructeur, initialise la classe et démarre la boucle infini de traitement
     */
    public Client(Socket clientSocket)
    {
        if (!isInit)
        {
        for (int i = 0; i< ListPlayer.length; i++)
        ListPlayer[i] = null;
        }
        isInit = true;
        
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
                
                // première connection, vérifier le compte et initialise tout.
                if (IsFirstContact)
                {
                    //if (ID != -1)
                    //ListPlayer[ID] = osClient;
                    //osClient.println("ID "+ID);
                    //osClient.flush();
                    IsFirstContact = false;
                }
            }
            
            // gestion des multiple erreure pouvant ce produire
            catch (IOException e) {
                System.out.println("Couldn't get I/O for the connection to: ");
                IsRun = false; 
            }
            catch (Exception e) {
                 System.out.print("cest ici que cq bug2");System.out.println(e);
                 IsRun = false; 
            }
        }
        
        try{Thread.sleep(100);}catch(Exception t){}
        System.out.println("fin d'un thread.......................................................");
    }
}

