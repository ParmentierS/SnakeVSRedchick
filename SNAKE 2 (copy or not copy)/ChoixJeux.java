import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ChoixJeux extends JFrame{

	private JButton Exit; 
    private JButton Reseau; 
    private JButton ReseauClient; 
    private JButton Solo; 
    private JButton DeuxJoueur; 
    private JButton CreatMap; 
    
    private JFrame parent;
    
                    
	public ChoixJeux(){
		super();
		
		build();//On initialise notre fenêtre
	}
	
	private void build(){
		setTitle("--- TOP 10 DE SNAKE ---"); //On donne un titre à l'application
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		
		// déclaration des boxs
        Panel Menu = new Panel(new GridLayout(6,1));
        
        //Menu.setLayout(new GridLayout(5,1));
        
        // remplissage du menu
        Exit = new JButton("End Game");                 
        Exit.addActionListener(new listener());
        Menu.add(Exit);
        Reseau = new JButton("Partie en réseau (Serveur)"); 
        Reseau.addActionListener(new listener());
        Menu.add(Reseau);
        ReseauClient = new JButton("Partie en réseau (Client)"); 
        ReseauClient.addActionListener(new listener());
        Menu.add(ReseauClient);
        DeuxJoueur = new JButton("Partie 2 Joueur");   
        DeuxJoueur.addActionListener(new listener());
        Menu.add(DeuxJoueur);
        Solo = new JButton("Partie en solo");                 
        Solo.addActionListener(new listener());
        Menu.add(Solo);
        CreatMap = new JButton("Createur de map");   
        CreatMap.addActionListener(new listener());
        Menu.add(CreatMap);
        
        
        // incorporation des box les une dans les autres
        
        Container pane = getContentPane();
        pane.add(Menu);
        
        // affichage
        Image icone = Toolkit.getDefaultToolkit().getImage("Head_Right.JPG");
        setIconImage(icone);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);
        parent = this;
	}
	 private class listener implements ActionListener
    {
    /**
     *  effecture une action particulière pour chaque bouton de la fenetre de jeux.
     *
     * @pre :
     * @post: 
     */
           public void actionPerformed(ActionEvent e)
           {
                if (e.getSource() == Exit) 
                {
                    System.exit(0); 
                }
                if (e.getSource() == Reseau) 
                {
                    
                    setVisible(false);
                    Thread T1 = new GameReseau(parent);T1.start();
                }
                if (e.getSource() == ReseauClient) 
                {
                    
                    setVisible(false);
                    Thread T1 = new GameReseauClient(parent);T1.start();
                }
                if (e.getSource() == Solo) 
                {
                    setVisible(false);
                    Thread T1 = new GameSolo(parent);T1.start();
                }      
                if (e.getSource() == DeuxJoueur) 
                {
                    setVisible(false);
                    Thread T1 = new GameDeuxJoueur(parent);T1.start();
                } 
                if (e.getSource() == CreatMap) 
                {
                    setVisible(false);
                    Thread T1 = new MapCreator(parent);T1.start();
                }
            }        
    }
    
     /**
     * @pre :
     * @post:
     */
    public static void main(String[] args) {
     //Game game;
     //game=new Game();
     JFrame windowTop = new ChoixJeux();
    }
}

