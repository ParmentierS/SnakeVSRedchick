
/**
 * http://cermics.enpc.fr/cours/java/notes/notes4.html
 * @author :
 * @version :
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MapCreator
{

    private JButton Exit;
    private JButton LoadMap;
    private JButton NewMap;
    private JButton SaveMap;
    private JButton ID;
    
    private JButton FLOOR = new JButton(new ImageIcon("Floor.JPG"));
    private JButton WALL = new JButton(new ImageIcon("BleuWall.JPG")); 
    private JButton CHICK = new JButton(new ImageIcon("Chick.JPG")); 
    private JButton REDCHICK = new JButton(new ImageIcon("Redchick.JPG")); 
    private JButton REDCHICK_ON_CHICK = new JButton(new ImageIcon("Redchick.JPG")); 
    private JButton REDCHICK_BEHIND_CHICK = new JButton(new ImageIcon("Chick.JPG")); 
    private JButton HEAD_UP = new JButton(new ImageIcon("Head_Up.JPG")); 
    private JButton HEAD_LEFT = new JButton(new ImageIcon("Head_Left.JPG")); 
    private JButton HEAD_RIGHT = new JButton(new ImageIcon("Head_Right.JPG")); 
    private JButton HEAD_DOWN = new JButton(new ImageIcon("Head_Down.JPG")); 
    private JButton CORPS = new JButton(new ImageIcon("Corps.JPG")); 
    private JButton TAIL = new JButton(new ImageIcon("Corps.JPG")); 
    
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
    public MapCreator()
    {  
    super(); 
    
    // affichage de la fenetre du jeux
    window = new JFrame ("MMORPG - Createur de carte -"); 
    DISPLAY = new DisplayMapCreator() ;
    // creation des contenaires
    Box MAP = Box.createVerticalBox();
    Box Decort = Box.createVerticalBox();
    Box Button = Box.createVerticalBox();
    Box All = Box.createHorizontalBox();
    
    // initialisation des boutons
    Exit = new JButton("Exit");                 
    Exit.addActionListener(new listenerTop());
    Button.add(Exit);
    
    LoadMap = new JButton("Load map");                 
    LoadMap.addActionListener(new listenerTop());
    Button.add(LoadMap);
    
    NewMap = new JButton("New map");                 
    NewMap.addActionListener(new listenerTop());
    Button.add(NewMap);
    
    SaveMap = new JButton("Save map");                 
    SaveMap.addActionListener(new listenerTop());
    Button.add(SaveMap);
    
    ID = new JButton("ID");                 
    ID.addActionListener(new listenerTop());
    Button.add(ID);
    
    FLOOR.addActionListener(new listenerTop());
    WALL.addActionListener(new listenerTop());
    CHICK.addActionListener(new listenerTop());
    REDCHICK.addActionListener(new listenerTop());
    REDCHICK_ON_CHICK.addActionListener(new listenerTop());
    REDCHICK_BEHIND_CHICK.addActionListener(new listenerTop());
    HEAD_UP.addActionListener(new listenerTop());
    HEAD_LEFT.addActionListener(new listenerTop());
    HEAD_RIGHT.addActionListener(new listenerTop());
    HEAD_DOWN.addActionListener(new listenerTop());
    CORPS.addActionListener(new listenerTop());
    TAIL.addActionListener(new listenerTop());
    
    Button.add(FLOOR);
    Button.add(WALL); 
    Button.add(CHICK); 
    Button.add(REDCHICK); 
    Button.add(REDCHICK_ON_CHICK); 
    Button.add(REDCHICK_BEHIND_CHICK); 
    Button.add(HEAD_UP); 
    Button.add(HEAD_LEFT); 
    Button.add(HEAD_RIGHT); 
    Button.add(HEAD_DOWN); 
    Button.add(CORPS); 
    Button.add(TAIL); 
    
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
                    System.exit(0); 
                }
                if (top.getSource() == ID) 
                {
                    //String name = JOptionPane.showInputDialog(null,"Entrez l'ID de l'image",JOptionPane.PLAIN_MESSAGE);
                    //if(name != null )
                    //IDImageSelectionnee = Integer.parseInt(name);
                }
                if (top.getSource() == NewMap) 
                {
                    DISPLAY.NewMap();
                }
                if (top.getSource() == LoadMap) 
                {
                    //String name = JOptionPane.showInputDialog(null,"Entrez le nom de la map a charger",JOptionPane.PLAIN_MESSAGE);
                    //if(name != null )
                    //DISPLAY.LoadMap(name);
                }
                if (top.getSource() == SaveMap) 
                {
                    //String name = JOptionPane.showInputDialog(null,"Entrez le nom de la map a enregister",JOptionPane.PLAIN_MESSAGE);
                    //if(name != null )
                    //DISPLAY.SaveMap(name);
                }
                if (top.getSource() == FLOOR) 
                IDImageSelectionnee = 0;
                if (top.getSource() == WALL) 
                IDImageSelectionnee = 1;
                if (top.getSource() == CHICK) 
                IDImageSelectionnee = 2;
                if (top.getSource() == REDCHICK) 
                IDImageSelectionnee = 3;
                if (top.getSource() == REDCHICK_ON_CHICK) 
                IDImageSelectionnee = 31;
                if (top.getSource() == REDCHICK_BEHIND_CHICK) 
                IDImageSelectionnee = 32;
                if (top.getSource() == HEAD_UP) 
                IDImageSelectionnee = 41;
                if (top.getSource() == HEAD_LEFT) 
                IDImageSelectionnee = 42;
                if (top.getSource() == HEAD_RIGHT) 
                IDImageSelectionnee = 43;
                if (top.getSource() == HEAD_DOWN) 
                IDImageSelectionnee = 44;
                if (top.getSource() == CORPS) 
                IDImageSelectionnee = 5;
                if (top.getSource() == TAIL) 
                IDImageSelectionnee = 6;
            }  
    }

    /**
     * @pre :
     * @post:
     */
    public static void main(String[] args) {
     MapCreator Creator;
     Creator = new MapCreator();
    }
    
}
