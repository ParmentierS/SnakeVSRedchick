


/**
 * 
 * @author :
 * @version :
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameReseauClient extends Thread {

    //constantes permettant d'identiffier les élément de la map.
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
    
    // variable consernant les fetre d'affichage du top 10.
    private boolean VisibleTop = false;
    private JFrame windowTop;
    private TOP score;
    private JButton exittop; 
    private JButton refrech; 
    
    // variable consernant la fentre de jeux.
    private JFrame window;
    private JPlotPanel graph; 
    private JButton exitButton; 
    private JButton top10; 
    private JButton Music; 
    private JButton Pause; 
    
    // variable influancent directement le jeux
    private boolean run = true;
    private boolean level = true;
    private boolean game = true;
    private boolean newgame = false;
    private boolean gameBegin = false;
    
    private boolean win = false;
    private boolean pause = false;
    
    private boolean IsMusic = true;
    
    private final int SpeedInit = 250 ; // en milliseconde 250
    private int Speed = 500; // en milliseconde 250
    private int LVL = 1; 
    private int Point = 0; 
    
    // variable permettant d'identifier des direction.
    public static final int LEFT = HEAD_LEFT;
    public static final int RIGHT = HEAD_RIGHT;
    public static final int DOWN = HEAD_DOWN;
    public static final int UP = HEAD_UP;
    public static final int HIDDENTRUE  = 50;
    public static final int HIDDENFALSE = 51;
    
    // variable conserant le serpent
    private int MotionDirection = 0;
    private int NewMotionDirection = 0;
    
    private int MotionDirection2 = UP;
    private int NewMotionDirection2 = UP;
    
    private int XHead;
    private int YHead;
    
    private int XTail;
    private int YTail;
    
    private int XHead2;
    private int YHead2;
    
    private int XTail2;
    private int YTail2;
    
    private Snake snake; // utiliser temporairement
    private Snake snake1;
    private Snake snake2;
    
    // variable conserant le poussin rouge
    private int[][] RedMultipte;
    private int XRedChick; // utiliser temporairement
    private int YRedChick; // utiliser temporairement
    
    private RedChick redChick;
    
    // map du jeux
    private monde T;
    private int[][] map;
    private WriteReadDATA IO = new WriteReadDATA();
    
    // le reseau
    private ConnectionToServeur CONNECTION;
    private String IP;
    
    private JFrame parent;
    public GameReseauClient(JFrame menu)
    {
        parent = menu;
    }
    /**
     * @pre :
     * @post:
     *        
     */
    public void run()
    {  
        boolean is_OK = false;
        while(!is_OK)
        {
            IP = JOptionPane.showInputDialog(null,"entree l'address IP du serveur",JOptionPane.PLAIN_MESSAGE);
            if(IP!= null)
            {
                //String [] tab = IP.split ("."); 
                //if(tab.length == 4)
                is_OK = true;
            }
        }
        CONNECTION = new ConnectionToServeur(this);
        if(!CONNECTION.Connection(IP))
        {
               level = false; 
               run = false; 
               game = false;
               window.setVisible(false);
               parent.setVisible(true);
        }
        CONNECTION.start();
        
        
        // création de la fenetre d'affichage du top 10.
        windowTop = new SimpleFenetre();
        score = new TOP() ;
        Box Top = Box.createHorizontalBox();
        Panel Button = new Panel(new GridLayout(4,1));
        Box All = Box.createHorizontalBox();
        
        // ajout des boutons.
        exittop = new JButton("exit");                 
        exittop.addActionListener(new listenerTop());
        Button.add(exittop);
        refrech = new JButton("refrech"); 
        refrech.addActionListener(new listenerTop());
        Button.add(refrech);
        
        // incorporation des boxs et affichage.
        Top.add(score);
        All.add(Top);
        All.add(Button);
        Container paneTop = windowTop.getContentPane();
        paneTop.add(All);
        windowTop.pack();
        windowTop.setVisible(VisibleTop);
        
        //création de la fenetre de jeux.
        window = new JFrame ("----SNAKE--VS--REDCHICK--Client-"); 
        
        graph = new JPlotPanel () ;
        
        // déclaration des boxs
        Box TOTALBOX = Box.createVerticalBox();
        Box STAT = Box.createVerticalBox();
        Box Graf_Menu = Box.createHorizontalBox();
        Panel Menu = new Panel(new GridLayout(7,1));
        
        // remplissage du menu
        exitButton = new JButton(new ImageIcon("Head_Right.JPG"));                 
        exitButton.addActionListener(new listener());
        Menu.add(exitButton);
        Menu.add(Box.createVerticalStrut(10));
        top10 = new JButton("Top 10"); 
        top10.addActionListener(new listener());
        Menu.add(top10);
        Menu.add(Box.createVerticalStrut(10));
        Pause = new JButton("pause");   
        Pause.addActionListener(new listener());
        Menu.add(Pause);
        Menu.add(Box.createVerticalStrut(10));
        Music = new JButton("music");   
        Music.addActionListener(new listener());
        Menu.add(Music);
        
        // remplissage des STATS
        STAT.add(Box.createHorizontalStrut(50));
        
        // incorporation des box les une dans les autres
        Graf_Menu.add(graph);
        Graf_Menu.add(Menu);
        TOTALBOX.add(Graf_Menu);
        TOTALBOX.add(STAT);
        
        Container pane = window.getContentPane();
        pane.add(TOTALBOX);
        
        // affichage
        Image icone = Toolkit.getDefaultToolkit().getImage("Head_Right.JPG");
        window.setIconImage(icone);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setResizable(false);
        window.setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        window.setVisible(true);
        
        // permet de récupéré la totalité des évènement. trouver : http://www.developpez.net/forums/d266788/java/interfaces-graphiques-java/awt-swing/debutant-keylistener-jframe-ne-repond/
        Toolkit.getDefaultToolkit().addAWTEventListener(
           new AWTEventListener()
           {
               public void eventDispatched(AWTEvent event){
                    KeyEvent ke = (KeyEvent)event;
                    if(ke.getID() == KeyEvent.KEY_PRESSED){
                        switch (ke.getKeyCode()) 
                        { 
                            case KeyEvent.VK_UP     : CONNECTION.Send("U");   break; 
                            case KeyEvent.VK_LEFT   : CONNECTION.Send("L");   break; 
                            case KeyEvent.VK_DOWN   : CONNECTION.Send("D");   break; 
                            case KeyEvent.VK_RIGHT  : CONNECTION.Send("R");   break; 
                            case KeyEvent.VK_P  : if(gameBegin){pause = !pause;} break;
                        }
                    }
                }
            }, AWTEvent.KEY_EVENT_MASK);
            
        Toolkit.getDefaultToolkit().addAWTEventListener(
           new AWTEventListener()
           {
               public void eventDispatched(AWTEvent event){
                    KeyEvent ke = (KeyEvent)event;
                    if(ke.getID() == KeyEvent.KEY_PRESSED){
                        switch (ke.getKeyCode()) 
                        { 
                            case KeyEvent.VK_Z     : CONNECTION.Send("U");   break; 
                            case KeyEvent.VK_Q     : CONNECTION.Send("L");   break; 
                            case KeyEvent.VK_S     : CONNECTION.Send("D");   break; 
                            case KeyEvent.VK_D     : CONNECTION.Send("R");   break; 
                        }
                    }
                }
            }, AWTEvent.KEY_EVENT_MASK);
        
        
        // lecture et initialisation de la map.
        T = IO.ReadMultiple(""+LVL);
        map = T.terrain;
        
        graph.Setmap(map);
        // affichage de l'image NewGame pendant 2 seconde.
        graph.NewGame(true); window.repaint();
        try{Thread.sleep(2000);}catch(Exception S){}
        graph.NewGame(false); window.repaint();
           
        
        // démarage de la boucle principale de jeux
        while(game)
        {
            // retire l'image GameOver
            graph.GameOver(false);
            window.repaint();
            
            Speed = SpeedInit/5;
    
            // boucle consernent le niveau de jeux ou l'on se trouve.
            while(level)
            {
                
                // démérage de la boucle de jeux.
                long START = System.currentTimeMillis();
                long END;
                
                while(run)
                {
                    START = System.currentTimeMillis();
                    window.setTitle("----SNAKE--VS--REDCHICK----Score:"+Point+"   Niveau:"+LVL);
                    if(pause)
                    Pause.setText("pause(ON)");
                    else
                    Pause.setText("pause(OFF)");
                    
                    END = System.currentTimeMillis();
                    
                    window.repaint();
                    try{Thread.sleep(33);}catch(Exception e){}
                }
                try{Thread.sleep(100);}catch(Exception e){}
            }
            try{Thread.sleep(100);}catch(Exception e){}
        }    
    }
    public void NewGame()
    {   
        LVL = 1;
                    
        newgame = true; 
        win = false;
        run = false;
        level = false;
        gameBegin = false;
        T = IO.ReadMultiple(""+LVL);
        map = T.terrain;
        graph.NewGame(true); window.repaint();
        try{Thread.sleep(Speed);}catch(Exception S){}
        graph.NewGame(false); graph.Setmap(map);window.repaint();
        run = true;
        level = true;
        newgame = false;
        Point = 0;
        Speed = SpeedInit;
    }
    public void NextGame(int lvl)
    {   
        LVL = lvl;
        T = IO.ReadMultiple(""+lvl);
        map = T.terrain;
        
        graph.Setmap(map);
    }
    public void AcctualyseMap(int Y,int X,int ELEM)
    {
        graph.Setmap(Y,X,ELEM);
    }
    public void exit()
    {
        level = false; 
        run = false; 
        game = false;
        window.setVisible(false);
        parent.setVisible(true);
        CONNECTION.CloseConnection();
    }
    // responsable de l'action des boutons
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
                if (e.getSource() == exitButton) 
                {   level = false; 
                    run = false; 
                    game = false;
                    window.setVisible(false);
                    parent.setVisible(true);
                    CONNECTION.CloseConnection();
                }
                if (e.getSource() == top10) 
                {
                    VisibleTop = !VisibleTop;
                    windowTop.setVisible(VisibleTop);
                    windowTop.repaint();
                }      
                if (e.getSource() == Music) 
                {
                    IsMusic = !IsMusic;
                } 
                if (e.getSource() == Pause) 
                {
                    CONNECTION.Send("P");
                }
            }        
    }
    private class listenerTop implements ActionListener
    {
        
    /**
     * effecture une action particulière pour chaque bouton de la fenetre du top 10.
     *
     * @pre :
     * @post:   
     */
           public void actionPerformed(ActionEvent top)
           {
                if (top.getSource() == exittop) 
                {
                    VisibleTop = false;
                    windowTop.setVisible(VisibleTop);
                    windowTop.repaint();
                }
                if (top.getSource() == refrech) 
                {
                    score.refrech();
                    windowTop.repaint();
                }      
            }        
    }
    
}

