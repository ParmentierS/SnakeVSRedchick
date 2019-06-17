
/**
 * http://cermics.enpc.fr/cours/java/notes/notes4.html
 * @author :
 * @version :
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MapCreator extends Thread 
{

    private JButton Exit;
    private JButton LoadMap2;
    private JButton LoadMap1;
    private JButton NewMap;
    private JButton SaveMap2;
    private JButton SaveMap1;
    private JButton ID;
    
    private JButton FLOOR = new JButton(new ImageIcon("Floor.JPG"));
    private JButton WALL = new JButton(new ImageIcon("BleuWall.JPG")); 
    private JButton CHICK = new JButton(new ImageIcon("Chick.JPG")); 
    
    private DisplayMapCreator DISPLAY; 
    private JFrame window;
    
    private boolean run = true;
    
    private int IDImageSelectionnee = 0; 
    private boolean IsSelec = true;      // pour tester: initialiser a false
    
    /**
     * @pre :
     * @post:
     *        
     */
    private JFrame parent;
    public MapCreator(JFrame menu)
    {
        parent = menu;
    }
    /**
     * @pre :
     * @post:
     *        
     */
    public void setVisible(boolean set)
    {
        window.setVisible(set);
    }
    /**
     * @pre :
     * @post:
     *        
     */
    public void run()
    {   
    
    // affichage de la fenetre du jeux
    window = new JFrame ("MMORPG - Createur de carte -"); 
    DISPLAY = new DisplayMapCreator() ;
    // creation des contenaires
    Box MAP = Box.createVerticalBox();
    Box Decort = Box.createVerticalBox();
    Panel Button = new Panel(new GridLayout(4,2));
    Box All = Box.createHorizontalBox();
    
    // initialisation des boutons
    Exit = new JButton("Exit");                 
    Exit.addActionListener(new listenerTop());
    Button.add(Exit);
    
    NewMap = new JButton("New map");                 
    NewMap.addActionListener(new listenerTop());
    Button.add(NewMap);
    
    SaveMap1 = new JButton("Save map 1J");                 
    SaveMap1.addActionListener(new listenerTop());
    Button.add(SaveMap1);
    
    SaveMap2 = new JButton("Save map 2J");                 
    SaveMap2.addActionListener(new listenerTop());
    Button.add(SaveMap2);
    
    
    FLOOR.addActionListener(new listenerTop());
    WALL.addActionListener(new listenerTop());
    CHICK.addActionListener(new listenerTop());
    
    Button.add(FLOOR);
    Button.add(WALL); 
    Button.add(CHICK); 
    
    // embriquement des contenaire
    MAP.add(DISPLAY);
    All.add(MAP);
    All.add(Button);
    
    // affichage
    Container pane = window.getContentPane();
    pane.add(All);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.pack();
    window.setResizable(false);
    window.setVisible(true);
    
    window.repaint();
    window.addMouseListener(new MouseEventListener());
    
    }
    
    /**
     * @pre :
     * @post:
     */
    class MouseEventListener extends MouseAdapter { 
        
        public void mousePressed(MouseEvent evt) 
        { 
            if (evt.getX()<1200)      
            {
                DISPLAY.ChangeElementMap(evt.getX()/20, evt.getY()/20-1, IDImageSelectionnee);
            }
        } 
        public void mouseReleased(MouseEvent evt) 
        {
        }
        public void mouseEntered(MouseEvent evt) {}

        public void mouseExited(MouseEvent evt) {}
  
        public void mouseClicked(MouseEvent evt) {}
    } 
    
    /**
     * @pre :
     * @post:
     */
    private class listenerTop implements ActionListener
    {
    /**
     * @pre :
     * @post:
     *        
     */
           public void actionPerformed(ActionEvent top)
           {
                if (top.getSource() == Exit) 
                {
                    window.setVisible(false);
                    parent.setVisible(true);
                }
                if (top.getSource() == ID) 
                {
                    String name = JOptionPane.showInputDialog(null,"Entrez l'ID de l'image",JOptionPane.PLAIN_MESSAGE);
                    if(name != null )
                    IDImageSelectionnee = Integer.parseInt(name);
                }
                if (top.getSource() == NewMap) 
                {
                    DISPLAY.NewMap();
                }
                if (top.getSource() == SaveMap2) 
                {
                    String name = JOptionPane.showInputDialog(null,"Entrez le nom de la map a enregister",JOptionPane.PLAIN_MESSAGE);
                    if(name != null )
                    DISPLAY.SaveMap(name,true);
                }
                if (top.getSource() == SaveMap1) 
                {
                    String name = JOptionPane.showInputDialog(null,"Entrez le nom de la map a enregister",JOptionPane.PLAIN_MESSAGE);
                    if(name != null )
                    DISPLAY.SaveMap(name,false);
                }
                if (top.getSource() == FLOOR) 
                IDImageSelectionnee = 0;
                if (top.getSource() == WALL) 
                IDImageSelectionnee = 1;
                if (top.getSource() == CHICK) 
                IDImageSelectionnee = 2;
            }  
    }

    
}
