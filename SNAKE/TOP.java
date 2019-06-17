

 /**
 * 
 * @author :
 * @version :
 */

import java.awt.*;
import javax.swing.*;

public class TOP extends JPanel {

    public lectureFichier WR;
    /**
     * Constructeur d'un espace graphique pour le jeux.
     * 
     * @pre: 
     * @post: Création d'un espace graphique sur lequel sera afficher la partie graphique du jeux.
     */
    
    public TOP() {
        super();
        WR = new lectureFichier();
        WR.readScore();
        this.setBackground(new Color(171,171,171));
        this.setPreferredSize(new Dimension (200,250));
        
    }
	/**
     *  permet l'acctualisation des scores
     * 
     * @pre: 
     * @post: recharge le fichier des scors.
     */
    public void refrech() {
        WR.readScore();
    }
    /**
     *Création de l'espace graphique.
     *
     * @pre: -
     * @post: Création de l'espace graphique.
     */
    public void paintComponent(Graphics g) 
    {
     Font fonte = new Font("BalloonEFExtraBold", Font.BOLD ,14);
     g.setFont(fonte);
     g.setColor(new Color(0,0,0));
     
     super.paintComponent(g);  
     for (int i = 0; i < WR.LONGUEURTOP ; i++)
     {
        g.drawString(WR.noms[i],20,30+20*i);
        g.drawString(""+WR.scores[i],140,30+20*i);
    }
                   
    }
}


