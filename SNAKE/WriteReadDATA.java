
import java.io.*;
import java.util.*;

/**
 * classe permettant d'enregister ou de lire des fichier destiner a contenir des map de jeux
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WriteReadDATA
{
    public static final int FLOOR = 0;
    public static final int WALL = 1;
    public static final int CHICK = 2;
    public static final int REDCHICK = 3;
    public static final int HEAD_UP = 41;
    public static final int HEAD_LEFT = 42;
    public static final int HEAD_RIGHT = 43;
    public static final int HEAD_DOWN = 44;
    public static final int CORPS = 5;
    
    /**
     *  permet la sauvegarde de map sous forme de fichier .SNAKEMAP 
     *
     *  @pre: int a correspond au nom de la map, lvl a sont niveau de difficulter, et m la map a enregister.
     *  @post: grave un fichier contenent toutes les information.
     */
    public static void Write(int[][] m , int a, int lvl)
     {
    int[][] map = m;
    try {                                                                                           
      PrintWriter outFile =new PrintWriter( new BufferedWriter ( new FileWriter (""+a+".SNAKEMAP")));   
      outFile.println ("LVL " + lvl);                                           
      
        for (int l = 0; l < 20 ; l++ )
        {
             for (int h = 0; h < 20 ; h++ )
             {
                 outFile.print ( ""+ map[h][l] + " ");
             }
             outFile.println("");
        }
        outFile.close();                           
    }
     catch (IOException e){                        
        System.out.println("Erreur d'écriture");  
    }
    
      
    }
    /**
     *  permet la lecture d'une map .SNAKEMAP 
     *
     *  @pre: filename corespond au nom de la map.
     *  @post: renvoi une mapcorrespondant au nom de fichier donner en argument.
     */
     public static int[][] Read(String filename) {
        int[][] map = new int[20][20];  

        try 
        {               
            BufferedReader inFile = new BufferedReader ( new FileReader (filename)); 
            String size = inFile.readLine();                                           
            String [] tab = size.split (" ");                                          
           
            int LVL = Integer.parseInt(tab[1]); 
            for (int l = 0; l < 20 ; l ++){
                String ligne = inFile.readLine();
                String [] ELEMENT = ligne.split (" ");
                for (int h = 0; h < 20 ; h ++)
                map[l][h] = Integer.parseInt(ELEMENT[h]);
                }
                
            
           inFile.close();                                                     
        
        }
        catch (FileNotFoundException e) {                                             
            
            System.err.println("Le fichier n existe pas.");
            System.exit (0);
        }
        catch ( IOException e){                                                       
            System.out.println ( "Erreur de lecture.");
        }
        catch (NullPointerException e) {                                              
            System.err.println ( "Le fichier contient moins de valeurs que ce qui est annoncé.") ;
        }
     
        return map;     
    }

    
}
