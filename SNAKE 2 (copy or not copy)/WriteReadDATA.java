
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
    public static final int TAIL = 6;
    
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
     *  permet la sauvegarde de map sous forme de fichier .SNAKEMAP 
     *
     *  @pre: int a correspond au nom de la map, lvl a sont niveau de difficulter, et m la map a enregister.
     *  @post: grave un fichier contenent toutes les information.
     */
    public static void Save(int[][] m , int[] redchik,Snake snake, int lvl,String name)
     {
    int[][] map = m;
    try {                                                                                           
      PrintWriter outFile =new PrintWriter( new BufferedWriter ( new FileWriter (""+name+".SNAKEMAP")));   
      outFile.println ("LVL " + lvl);                                           
      
        for (int l = 0; l < 20 ; l++ )
        {
             for (int h = 0; h < 20 ; h++ )
             {
                 outFile.print ( ""+ map[l][h] + " ");
             }
             outFile.println("");
        }
        //écrire le poussin rouge
        outFile.println("redchik "+redchik[0]+" "+redchik[1]);
        //écrire le serpent
        if(snake.Head>snake.Tail)
        {
            for(int i = snake.Tail ; i < snake.Head ; i++)
            outFile.println(""+snake.snake[0][i]+" "+snake.snake[1][i]);
            outFile.println(""+snake.snake[0][snake.Head]+" "+snake.snake[1][snake.Head]+" "+m[snake.snake[1][snake.Head]][snake.snake[0][snake.Head]]);
        }
        else
        {
            for(int i = snake.Tail ; i < 400 ; i++)
            outFile.println(""+snake.snake[0][i]+" "+snake.snake[1][i]);
            for(int i = 0 ; i < snake.Head ; i++)
            outFile.println(""+snake.snake[0][i]+" "+snake.snake[1][i]);
            outFile.println(""+snake.snake[0][snake.Head]+" "+snake.snake[1][snake.Head]+" "+m[snake.snake[1][snake.Head]][snake.snake[0][snake.Head]]);
            
        }
        
        outFile.close();                           
    }
     catch (IOException e){                        
        System.out.println("Erreur d'écriture");  
    }
    
      
    }
     /**
     *  permet la sauvegarde de map sous forme de fichier .SNAKEMAP 
     *
     *  @pre: int a correspond au nom de la map, lvl a sont niveau de difficulter, et m la map a enregister.
     *  @post: grave un fichier contenent toutes les information.
     */
    public static void Save(int[][] m , int[] redchik,Snake snake1,Snake snake2, int lvl,String name)
     {
    int[][] map = m;
    try {                                                                                           
      PrintWriter outFile =new PrintWriter( new BufferedWriter ( new FileWriter (""+name+"-M.SNAKEMAP")));   
      outFile.println ("LVL " + lvl);                                           
      
        for (int l = 0; l < 20 ; l++ )
        {
             for (int h = 0; h < 20 ; h++ )
             {
                 outFile.print ( ""+ map[l][h] + " ");
             }
             outFile.println("");
        }
        //écrire le poussin rouge
        outFile.println("redchik "+redchik[0]+" "+redchik[1]);
        //écrire le serpent
        if(snake1.Head>snake1.Tail)
        {
            for(int i = snake1.Tail ; i < snake1.Head ; i++)
            outFile.println(""+snake1.snake[0][i]+" "+snake1.snake[1][i]);
            outFile.println(""+snake1.snake[0][snake1.Head]+" "+snake1.snake[1][snake1.Head]+" "+m[snake1.snake[1][snake1.Head]][snake1.snake[0][snake1.Head]]);
        }
        else
        {
            for(int i = snake1.Tail ; i < 400 ; i++)
            outFile.println(""+snake1.snake[0][i]+" "+snake1.snake[1][i]);
            for(int i = 0 ; i < snake1.Head ; i++)
            outFile.println(""+snake1.snake[0][i]+" "+snake1.snake[1][i]);
            outFile.println(""+snake1.snake[0][snake1.Head]+" "+snake1.snake[1][snake1.Head]+" "+m[snake1.snake[1][snake1.Head]][snake1.snake[0][snake1.Head]]);
        }
        outFile.println("");
        if(snake2.Head>snake2.Tail)
        {
            for(int i = snake2.Tail ; i < snake2.Head ; i++)
            outFile.println(""+snake2.snake[0][i]+" "+snake2.snake[1][i]);
            outFile.println(""+snake2.snake[0][snake2.Head]+" "+snake2.snake[1][snake2.Head]+" "+m[snake2.snake[1][snake2.Head]][snake2.snake[0][snake2.Head]]);
        }
        else
        {
            for(int i = snake2.Tail ; i < 400 ; i++)
            outFile.println(""+snake2.snake[0][i]+" "+snake2.snake[1][i]);
            for(int i = 0 ; i < snake2.Head ; i++)
            outFile.println(""+snake2.snake[0][i]+" "+snake2.snake[1][i]);
            outFile.println(""+snake2.snake[0][snake2.Head]+" "+snake2.snake[1][snake2.Head]+" "+m[snake2.snake[1][snake2.Head]][snake2.snake[0][snake2.Head]]);
        }
        
        outFile.close();                           
    }
     catch (IOException e){                        
        System.out.println("Erreur d'écriture");  
    }
    
      
    }
    /**
     *  permet la sauvegarde de map sous forme de fichier .SNAKEMAP 
     *
     *  @pre: int a correspond au nom de la map, lvl a sont niveau de difficulter, et m la map a enregister.
     *  @post: grave un fichier contenent toutes les information.
     */
    public static void Save(int[][] m ,String name,boolean MultiJ)
     {
    int[][] map = m;
    PrintWriter outFile;
    try {
        if( MultiJ)
        outFile =new PrintWriter( new BufferedWriter ( new FileWriter (""+name+"-M.SNAKEMAP"))); 
        else
        outFile =new PrintWriter( new BufferedWriter ( new FileWriter (""+name+".SNAKEMAP"))); 
        
        outFile.println ("LVL " + 0);                                           
      
        for (int l = 0; l < 20 ; l++ )
        {
             for (int h = 0; h < 20 ; h++ )
             {
                 outFile.print ( ""+ map[l][h] + " ");
             }
             outFile.println("");
        }
        //écrire le poussin rouge
        outFile.println("redchik 5 5");
        outFile.println("10 18");
        outFile.println("10 17");
        outFile.println("10 16");
        outFile.println("10 15 41");
        if( MultiJ)
        {
            outFile.println("");
            outFile.println("14 18");
            outFile.println("14 17");
            outFile.println("14 16");
            outFile.println("14 15 41");
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
     public static monde Read(String filename) {
        monde terrain = new monde();
        terrain.terrain = new int[20][20];  

        try 
        {               
            BufferedReader inFile = new BufferedReader ( new FileReader (filename)); 
            String size = inFile.readLine();                                           
            String [] tab = size.split (" ");                                          
           
            terrain.lvl = Integer.parseInt(tab[1]); 
            for (int l = 0; l < 20 ; l ++){
                String ligne = inFile.readLine();
                String [] ELEMENT = ligne.split (" ");
                for (int h = 0; h < 20 ; h ++)
                terrain.terrain[l][h] = Integer.parseInt(ELEMENT[h]);
                }
              
            String ligne = inFile.readLine();
            String [] ELEMENT = ligne.split (" ");
            
            terrain.RedChuckX = Integer.parseInt(ELEMENT[1]);
            terrain.RedChuckY = Integer.parseInt(ELEMENT[2]);
            terrain.terrain[terrain.RedChuckY][terrain.RedChuckX] = REDCHICK;
            
            terrain.SNAKE = new Snake();
            ligne = inFile.readLine(); 
            boolean in = true;
            while(ligne != null)
            {
                ELEMENT = ligne.split (" ");
                terrain.SNAKE.add(Integer.parseInt(ELEMENT[0]),Integer.parseInt(ELEMENT[1]));
                
                ligne = inFile.readLine(); 
                if(in){in = false; terrain.terrain[Integer.parseInt(ELEMENT[1])][Integer.parseInt(ELEMENT[0])] = TAIL;}
                else if(ligne == null)
                terrain.terrain[Integer.parseInt(ELEMENT[1])][Integer.parseInt(ELEMENT[0])] = Integer.parseInt(ELEMENT[2]);
                else terrain.terrain[Integer.parseInt(ELEMENT[1])][Integer.parseInt(ELEMENT[0])] = CORPS;
                
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
     
        return terrain;     
    }
     /**
     *  permet la lecture d'une map .SNAKEMAP 
     *
     *  @pre: filename corespond au nom de la map.
     *  @post: renvoi une mapcorrespondant au nom de fichier donner en argument.
     */
     public static monde ReadMultiple(String filename) {
        monde terrain = new monde();
        terrain.terrain = new int[20][20];  

        try 
        {               
            BufferedReader inFile = new BufferedReader ( new FileReader (filename+"-M.SNAKEMAP")); 
            String size = inFile.readLine();                                           
            String [] tab = size.split (" ");                                          
           
            terrain.lvl = Integer.parseInt(tab[1]); 
            for (int l = 0; l < 20 ; l ++){
                String ligne = inFile.readLine();
                String [] ELEMENT = ligne.split (" ");
                for (int h = 0; h < 20 ; h ++)
                terrain.terrain[l][h] = Integer.parseInt(ELEMENT[h]);
                }
              
            String ligne = inFile.readLine();
            String [] ELEMENT = ligne.split (" ");
            
            terrain.RedChuckX = Integer.parseInt(ELEMENT[1]);
            terrain.RedChuckY = Integer.parseInt(ELEMENT[2]);
            terrain.terrain[terrain.RedChuckY][terrain.RedChuckX] = REDCHICK;
            
            terrain.SNAKE = new Snake();
            ligne = inFile.readLine(); 
            boolean in = true;
            while(ligne.compareTo("")!=0)
            {
                ELEMENT = ligne.split (" ");
                terrain.SNAKE.add(Integer.parseInt(ELEMENT[0]),Integer.parseInt(ELEMENT[1]));
                
                ligne = inFile.readLine(); 
                if(in){in = false; terrain.terrain[Integer.parseInt(ELEMENT[1])][Integer.parseInt(ELEMENT[0])] = TAIL;}
                else if(ligne.compareTo("")==0)
                terrain.terrain[Integer.parseInt(ELEMENT[1])][Integer.parseInt(ELEMENT[0])] = Integer.parseInt(ELEMENT[2]);
                else terrain.terrain[Integer.parseInt(ELEMENT[1])][Integer.parseInt(ELEMENT[0])] = CORPS;
                
            }
            terrain.SNAKE2 = new Snake();
            ligne = inFile.readLine(); 
            in = true;
            while(ligne != null)
            {
                ELEMENT = ligne.split (" ");
                terrain.SNAKE2.add(Integer.parseInt(ELEMENT[0]),Integer.parseInt(ELEMENT[1]));
                
                ligne = inFile.readLine(); 
                if(in){in = false; terrain.terrain[Integer.parseInt(ELEMENT[1])][Integer.parseInt(ELEMENT[0])] = TAIL;}
                else if(ligne == null)
                terrain.terrain[Integer.parseInt(ELEMENT[1])][Integer.parseInt(ELEMENT[0])] = Integer.parseInt(ELEMENT[2]);
                else terrain.terrain[Integer.parseInt(ELEMENT[1])][Integer.parseInt(ELEMENT[0])] = CORPS;
                
            }
            
    
           inFile.close();                                                     
        
        }
        catch (FileNotFoundException e) {                                             
            
            System.err.println("Le fichier n existe pas 2.");
            System.exit (0);
        }
        catch ( IOException e){                                                       
            System.out.println ( "Erreur de lecture 2.");
        }
        catch (NullPointerException e) {                                              
            System.err.println ( "Le fichier contient moins de valeurs que ce qui est annoncé 2.") ;
        }
     
        return terrain;     
    }

    
}

