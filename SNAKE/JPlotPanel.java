

 /**
 *  classe complète gerant l'affichage du jeux, soit: la map, le serpent et le poussin rouge.
 *  elle gère aussi l'affichage d'une image de fin de jeux et de démarage de jeux.
 * 
 * @author :
 * @version : 1.0
 */

import java.awt.*;
import javax.swing.*;

public class JPlotPanel extends JPanel {
   
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
   
   private boolean IsOver = false;
   private boolean IsStart = false;
   private boolean init = true;
   private int hauteur = 20; 
   private int largeur = 20; 
   private int[][] Map;
   private Image image;
   
    /**
     * Constructeur d'un espace graphique pour le jeux.
     * 
     * @pre: 
     * @post: Création d'un espace graphique sur lequel sera afficher la partie graphique du jeux.
     */
    
    public JPlotPanel() {
        
        // initialisation des variable
        super();
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension (20*hauteur,20*largeur));
        Map = new int [hauteur][largeur];
        Map[0][0] = 1;
        for (int l = 0; l < largeur ; l++ )
        {
             for (int h = 0; h < hauteur ; h++ )
             {
                 Map[h][l] = FLOOR;
             }
        }
        
    }
    
    /**
     *Actualise l'élément se trouvant au coordonné H-1 et L-1 par "ELEMENT". 
     *
     * @pre: hauteur "H" et largeur "L" qui doivent etre compris entre 1 et 20, "ELEMENT" dois valoir un des élément de construction de la carte.
     * @post: -
     */
    public void SetMap(int H, int L, int ELEMENT)
    {
        boolean IsOk = false;
        switch (ELEMENT) 
        { 
        case FLOOR      : IsOk = true; break; 
        case WALL       : IsOk = true; break; 
        case CHICK      : IsOk = true; break; 
        case REDCHICK   : IsOk = true; break; 
        case HEAD_UP    : IsOk = true; break; 
        case HEAD_LEFT  : IsOk = true; break; 
        case HEAD_RIGHT : IsOk = true; break; 
        case HEAD_DOWN  : IsOk = true; break; 
        case CORPS      : IsOk = true; break;
        case TAIL       : IsOk = true; break;
        case REDCHICK_ON_CHICK     : IsOk = true; break;
        case REDCHICK_BEHIND_CHICK : IsOk = true; break;
        default: 
        if (image == null) System.out.println("!!!l'élément envoyé en argument est indéfinissable.");
        }
        if (H<1 || H>19 || L<0 || L>19){IsOk = false;}
        if (IsOk)Map[H][L] = ELEMENT;
    }
    
    /**
     *remplace la carte actuelle pr celle envoyer en argument.
     *
     * @pre: Tableau a deux dimensions d'entier "m" représentant la carte du jeux, doit etre de tail 20 20, le surplus ne sera pas pris en compte.
     * @post: -
     */
    public void SetMap(int[][] m)
    {
        Map = m;
    }
    
    /**
     * Permet de récupéré la carte du jeux.
     *
     * @pre: -
     * @post: Renvoit un tableau a deux dimensions d'entier représentant la carte du jeux.
     */
    public int[][] GetMap()
    {
        return Map;
    }
    
    /**
     * permet de géré la fin du jeux via un affichage.
     *
     * @pre: -
     * @post: affiche une image GameOver si isover = True sinon rien
     */
    public void GameOver(boolean isover)
    {
        IsOver = isover;
    }
    
    /**
     * permet de géré le début du jeux via un affichage.
     *
     * @pre: -
     * @post: affiche une image NewGame si isstart = True sinon rien
     */
    public void NewGame(boolean isstart)
    {
        IsStart = isstart;
    }
    
    /**
     *Création de l'espace graphique.
     *
     * @pre: -
     * @post: Création de l'espace graphique.
     */
    public void paintComponent(Graphics g) 
    {
     
      // effectue une double boucle for dan le but des parcourire la map et de transformer chaccun des entier en une image corespondante
     super.paintComponent(g);  
     java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
     
     if (init)
     {
         for (int l = 0; l < largeur ; l++ )
         {
             for (int h = 0; h < hauteur ; h++ )
             {
                 double rand = Math.random();
                 switch (Map[h][l]) 
                 { 
                     case FLOOR      : image = toolkit.getImage("Floor.JPG"); break; 
                     case WALL       : if(rand < 0.5) image = toolkit.getImage("BleuWall.JPG");else image = toolkit.getImage("YellowWall.JPG");  break; 
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
     if (IsOver){image = toolkit.getImage("GameOver.JPG");g.drawImage(image,44,0,this);}
     if (IsStart){image = toolkit.getImage("NewGame.JPG");g.drawImage(image,44,0,this);}
                   
    }
}

