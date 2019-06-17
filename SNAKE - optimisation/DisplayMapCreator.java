import java.io.*;
import java.util.*;

 /**
 * 
 * @author :
 * @version :
 */

import java.awt.*;
import javax.swing.*;

public class DisplayMapCreator extends JPanel {

    private int X = 20;
    private int Y = 20;
    
    private int[][] map = new int[40][60];
    
    public static final int FLOOR = 0;
    public static final int WALL = 1;
    public static final int CHICK = 2;
    public static final int REDCHICK = 3;
    public static final int REDCHICK_ON_CHICK = 31;
    public static final int REDCHICK_BEHIND_CHICK = 32;
    public static final int HEAD_UP = 41;
    public static final int HEAD_LEFT = 42;
    public static final int HEAD_RIGHT = 44;
    public static final int HEAD_DOWN = 43;
    public static final int CORPS = 5;
    public static final int TAIL = 6;
    
    
    private WriteReadDATA IO = new WriteReadDATA();
    
    private Image image;
    
    /**
     * Constructeur d'un espace graphique pour le jeux.
     * 
     * @pre: 
     * @post: Création d'un espace graphique sur lequel sera afficher la partie graphique du jeux.
     */
    public DisplayMapCreator() {
        super();
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension (400,400));
        
        for (int h = 1; h<19; h++)
        for (int l = 1; l<19; l++)
        {
            map[h][l] = 0;
        }
        for (int a = 0; a<20; a++)
        map[0][a] = 1;
        for (int a = 0; a<20; a++)
        map[19][a] = 1;
        for (int a = 0; a<20; a++)
        map[a][0] = 1;
        for (int a = 0; a<20; a++)
        map[a][19] = 1;
        repaint();
    }
    
    public void NewMap()
    {
        for (int h = 1; h<19; h++)
        for (int l = 1; l<19; l++)
        {
            map[h][l] = 0;
        }
        for (int a = 0; a<20; a++)
        map[0][a] = 1;
        for (int a = 0; a<20; a++)
        map[19][a] = 1;
        for (int a = 0; a<20; a++)
        map[a][0] = 1;
        for (int a = 0; a<20; a++)
        map[a][19] = 1;
        repaint();
    }
    
    public void LoadMap(String name)
    {
        try 
        {               
            BufferedReader inFile = new BufferedReader ( new FileReader (""+name+".SNAKEMAP")); 
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
        repaint();
    }
    
    public void SaveMap(String name,boolean MultiJ)
    {
        IO.Save(map,name,MultiJ);
    }
    
    public void ChangeElementMap(int x, int y, int ID)
    {
        map[y][x] = ID;
        repaint();
    }
    
    /**
     *Création de l'espace graphique.
     *
     * @pre: -
     * @post: Création de l'espace graphique.
     */
    public void paintComponent(Graphics g) 
    {
     
     super.paintComponent(g);
     java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
     
     for (int h = 0; h<40; h++)
         for (int l = 0; l<60; l++)
         {
             switch (map[h][l]) 
                 { 
                     case FLOOR      : image = toolkit.getImage("Floor.JPG"); break; 
                     case WALL       : image = toolkit.getImage("BleuWall.JPG"); break; 
                     case CHICK      : image = toolkit.getImage("Chick.JPG"); break; 
                     case REDCHICK   : image = toolkit.getImage("Redchick.JPG"); break; 
                     case HEAD_UP    : image = toolkit.getImage("Head_Up.JPG"); break; 
                     case HEAD_LEFT  : image = toolkit.getImage("Head_Left.JPG"); break; 
                     case HEAD_RIGHT : image = toolkit.getImage("Head_Right.JPG"); break; 
                     case HEAD_DOWN  : image = toolkit.getImage("Head_Down.JPG"); break; 
                     case CORPS      : image = toolkit.getImage("Corps.JPG"); break; 
                     case TAIL       : image = toolkit.getImage("Corps.JPG"); break; 
                     case REDCHICK_ON_CHICK      : image = toolkit.getImage("Redchick.JPG"); break; 
                     case REDCHICK_BEHIND_CHICK  : image = toolkit.getImage("Chick.JPG"); break; 
                     default: 
                     if (image == null) System.out.println("bug");
                 }
                 g.drawImage(image,20*l,20*h,this);
         }
     
                   
    }
}


