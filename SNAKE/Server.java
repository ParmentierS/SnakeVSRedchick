import java.net.ServerSocket ;
import java.net.SocketException ;
import java.io.IOException ;
 
public class Server {
 
	private boolean ok=true ;
	private ServerSocket server;
	private final int PORT= 3000;
 
	
    /**
     * 
     *
     * @pre :
     * @post:   
     */
	public static void main (String [] args ){
		new Server();
	}
 
    /**
     * 
     *
     * @pre :
     * @post:   
     */
	public Server (){
		try{
			server = new ServerSocket (PORT);
		}
		catch (SocketException e) {
			System.out.println(e.getMessage() + " 01");
		} 
		catch (IOException e) {
			System.out.println(e.getMessage() + " 02");
		}
		System.out.println("En écoute sur le port : " + PORT );
		while (ok) {
			try {
				new Client(server.accept());
			}
			catch (IOException e) {
				System.out.println(e.getMessage()+ " 03");
			}
 
		}
	}
 
}
