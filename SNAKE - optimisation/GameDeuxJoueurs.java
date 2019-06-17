


/**
 * 
 * @author :
 * @version :
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameDeuxJoueurs extends Thread {

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
    private JButton newGame; 
    private JButton exitButton; 
    private JButton top10; 
    private JButton Music; 
    private JButton Save; 
    private JButton Load; 
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
    
    private final int SpeedInit = 2500/2 ; // en milliseconde 250
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
    
    private JFrame parent;
    public GameDeuxJoueurs(JFrame menu)
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
        // création de la fenetre d'affichage du top 10.
        windowTop = new SimpleFenetre();
        score = new TOP() ;
        Box Top = Box.createHorizontalBox();
        Panel Button = new Panel(new GridLayout(2,1));
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
        window = new JFrame ("----SNAKES--VS--REDCHICK----"); 
        
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
        top10 = new JButton("Top 10"); 
        top10.addActionListener(new listener());
        Menu.add(top10);
        newGame = new JButton("New Game");                 
        newGame.addActionListener(new listener());
        Menu.add(newGame);
        Pause = new JButton("pause");   
        Pause.addActionListener(new listener());
        Menu.add(Pause);
        Save = new JButton("save");   
        Save.addActionListener(new listener());
        Menu.add(Save);
        Load = new JButton("Load");   
        Load.addActionListener(new listener());
        Menu.add(Load);
        Music = new JButton("music");   
        Music.addActionListener(new listener());
        Menu.add(Music);
        
        // remplissage des STATS
        STAT.add(Box.createHorizontalStrut(10));
        
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
                            case KeyEvent.VK_UP     : if(!gameBegin){gameBegin = true; MotionDirection = map[YHead][XHead];}    NewMotionDirection = UP;   break; 
                            case KeyEvent.VK_LEFT   : if(!gameBegin){gameBegin = true; MotionDirection = map[YHead][XHead];}  NewMotionDirection = LEFT; break; 
                            case KeyEvent.VK_DOWN   : if(!gameBegin){gameBegin = true; MotionDirection = map[YHead][XHead];}  NewMotionDirection = DOWN; break; 
                            case KeyEvent.VK_RIGHT  : if(!gameBegin){gameBegin = true; MotionDirection = map[YHead][XHead];} NewMotionDirection = RIGHT;break;
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
                            case KeyEvent.VK_W     : if(!gameBegin){gameBegin = true; MotionDirection2 = map[YHead2][XHead2];}    NewMotionDirection2 = UP;   break; 
                            case KeyEvent.VK_A     : if(!gameBegin){gameBegin = true; MotionDirection2 = map[YHead2][XHead2];}    NewMotionDirection2 = LEFT; break; 
                            case KeyEvent.VK_S     : if(!gameBegin){gameBegin = true; MotionDirection2 = map[YHead2][XHead2];}    NewMotionDirection2 = DOWN; break; 
                            case KeyEvent.VK_D     : if(!gameBegin){gameBegin = true; MotionDirection2 = map[YHead2][XHead2];}    NewMotionDirection2 = RIGHT;break;
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
                Speed = (int)SpeedInit/(LVL+5);
                
                
                XRedChick = T.RedChuckX;
                YRedChick = T.RedChuckY;
                redChick = new RedChick(LVL,XRedChick,YRedChick);
                snake1 = T.SNAKE;
                snake2 = T.SNAKE2;
                YHead = snake1.getHeadY();
                XHead = snake1.getHeadX();
                YHead2 = snake2.getHeadY();
                XHead2 = snake2.getHeadX();
                
                
                // démérage de la boucle de jeux.
                long START = System.currentTimeMillis();
                long END;
                
                while(run)
                {
                    START = System.currentTimeMillis();
                    
                    
                    if (gameBegin)
                    {
                        if (!pause)
                        {
                            map = graph.GetMap();
                            snake = snake1;
                            SnakeMotion();
                            
                            snake = snake2;
                            SnakeMotion2();
                            int [] tabX = {XHead, XHead2}, tabY = {YHead, YHead2};
                            switch (redChick.position(map,XRedChick,YRedChick,tabX,tabY,LVL)) 
                            { 
                                case UP    : if (map[YRedChick-1][XRedChick] == CHICK) 
                                             {
                                                 if(map[YRedChick][XRedChick] == REDCHICK_BEHIND_CHICK)  {graph.Setmap(YRedChick,XRedChick,CHICK);graph.Setmap(YRedChick-1,XRedChick,REDCHICK_BEHIND_CHICK);YRedChick--;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK)         {graph.Setmap(YRedChick,XRedChick,FLOOR);graph.Setmap(YRedChick-1,XRedChick,REDCHICK_ON_CHICK);YRedChick--;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK_ON_CHICK){graph.Setmap(YRedChick,XRedChick,CHICK);graph.Setmap(YRedChick-1,XRedChick,REDCHICK_ON_CHICK);YRedChick--;}
                                             } 
                                             else 
                                             {
                                                 if(map[YRedChick][XRedChick] == REDCHICK_BEHIND_CHICK)  {graph.Setmap(YRedChick,XRedChick,CHICK);graph.Setmap(YRedChick-1,XRedChick,REDCHICK);YRedChick--;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK)         {graph.Setmap(YRedChick,XRedChick,FLOOR);graph.Setmap(YRedChick-1,XRedChick,REDCHICK);YRedChick--;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK_ON_CHICK){graph.Setmap(YRedChick,XRedChick,CHICK);graph.Setmap(YRedChick-1,XRedChick,REDCHICK);YRedChick--;}
                                             }  
                                             break; 
                                case DOWN  : if (map[YRedChick+1][XRedChick] == CHICK)
                                             {
                                                 if(map[YRedChick][XRedChick] == REDCHICK_BEHIND_CHICK)  {graph.Setmap(YRedChick,XRedChick,CHICK);graph.Setmap(YRedChick+1,XRedChick,REDCHICK_BEHIND_CHICK);YRedChick++;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK)         {graph.Setmap(YRedChick,XRedChick,FLOOR);graph.Setmap(YRedChick+1,XRedChick,REDCHICK_ON_CHICK);YRedChick++;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK_ON_CHICK){graph.Setmap(YRedChick,XRedChick,CHICK);graph.Setmap(YRedChick+1,XRedChick,REDCHICK_ON_CHICK);YRedChick++;}
                                             } 
                                             else 
                                             {
                                                 if(map[YRedChick][XRedChick] == REDCHICK_BEHIND_CHICK)  {graph.Setmap(YRedChick,XRedChick,CHICK);graph.Setmap(YRedChick+1,XRedChick,REDCHICK);YRedChick++;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK)         {graph.Setmap(YRedChick,XRedChick,FLOOR);graph.Setmap(YRedChick+1,XRedChick,REDCHICK);YRedChick++;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK_ON_CHICK){graph.Setmap(YRedChick,XRedChick,CHICK);graph.Setmap(YRedChick+1,XRedChick,REDCHICK);YRedChick++;}
                                             }  
                                             break; 
                                case RIGHT : if (map[YRedChick][XRedChick+1] == CHICK)
                                             {
                                                 if(map[YRedChick][XRedChick] == REDCHICK_BEHIND_CHICK)  {graph.Setmap(YRedChick,XRedChick,CHICK);graph.Setmap(YRedChick,XRedChick+1,REDCHICK_BEHIND_CHICK);XRedChick++;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK)         {graph.Setmap(YRedChick,XRedChick,FLOOR);graph.Setmap(YRedChick,XRedChick+1,REDCHICK_ON_CHICK);XRedChick++;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK_ON_CHICK){graph.Setmap(YRedChick,XRedChick,CHICK);graph.Setmap(YRedChick,XRedChick+1,REDCHICK_ON_CHICK);XRedChick++;}
                                             } 
                                             else 
                                             {
                                                 if(map[YRedChick][XRedChick] == REDCHICK_BEHIND_CHICK)  {graph.Setmap(YRedChick,XRedChick,CHICK);graph.Setmap(YRedChick,XRedChick+1,REDCHICK);XRedChick++;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK)         {graph.Setmap(YRedChick,XRedChick,FLOOR);graph.Setmap(YRedChick,XRedChick+1,REDCHICK);XRedChick++;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK_ON_CHICK){graph.Setmap(YRedChick,XRedChick,CHICK);graph.Setmap(YRedChick,XRedChick+1,REDCHICK);XRedChick++;}
                                             }   
                                             break; 
                                case LEFT  : if (map[YRedChick][XRedChick-1] == CHICK)
                                             {
                                                 if(map[YRedChick][XRedChick] == REDCHICK_BEHIND_CHICK)  {graph.Setmap(YRedChick,XRedChick,CHICK);graph.Setmap(YRedChick,XRedChick-1,REDCHICK_BEHIND_CHICK);XRedChick--;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK)         {graph.Setmap(YRedChick,XRedChick,FLOOR);graph.Setmap(YRedChick,XRedChick-1,REDCHICK_ON_CHICK);XRedChick--;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK_ON_CHICK){graph.Setmap(YRedChick,XRedChick,CHICK);graph.Setmap(YRedChick,XRedChick-1,REDCHICK_ON_CHICK);XRedChick--;}
                                             } 
                                             else 
                                             {
                                                 if(map[YRedChick][XRedChick] == REDCHICK_BEHIND_CHICK)  {graph.Setmap(YRedChick,XRedChick,CHICK);graph.Setmap(YRedChick,XRedChick-1,REDCHICK);XRedChick--;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK)         {graph.Setmap(YRedChick,XRedChick,FLOOR);graph.Setmap(YRedChick,XRedChick-1,REDCHICK);XRedChick--;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK_ON_CHICK){graph.Setmap(YRedChick,XRedChick,CHICK);graph.Setmap(YRedChick,XRedChick-1,REDCHICK);XRedChick--;}
                                             }  
                                             break; 
                                case HIDDENTRUE   : if (map[YRedChick][XRedChick] == CHICK)
                                             {
                                                 graph.Setmap(YRedChick,XRedChick,REDCHICK_BEHIND_CHICK);
                                             } 
                                             break; 
                                case HIDDENFALSE  : if (map[YRedChick][XRedChick] == CHICK)
                                             {
                                                 graph.Setmap(YRedChick,XRedChick,REDCHICK_ON_CHICK);
                                             } 
                                             break; 
                            }
                            
                        }
                    }
                    
                    window.setTitle("----SNAKE--VS--REDCHICK----Score:"+Point+"   Niveau:"+LVL);
                    if(pause)
                    Pause.setText("pause(ON)");
                    else
                    Pause.setText("pause(OFF)");
                    
                    END = System.currentTimeMillis();
                    
                    window.repaint();
                    try{Thread.sleep(Speed - END + START);}catch(Exception e){}
                }
                // si le joueur resort vainceur : la boucle lvl reste opérationnel et lvl est incrémenter de 1.
                if (win)
                {
                    LVL++;
                    T = IO.ReadMultiple(""+LVL);
                    map = T.terrain;
                    graph.Setmap(map);
                    window.repaint();
                    level = true;run = true;gameBegin = false;
                }
                // si le joueur resort perdant ou a apuiller sur le bouton NewGame : la boucle lvl est intéronpu.
                else
                {
                    if (!newgame)
                    {
                        // réinitialise toute les variables
                        graph.GameOver(true);
                        window.repaint();
                        try{Thread.sleep(2000);}catch(Exception e){}
                        level = false;run = false;
                    }
                    if(score.WR.scores[score.WR.LONGUEURTOP-1]<Point)
                    {
                        //crée une fenètre pour inscrire ces point dans le top 10.
                        String name = JOptionPane.showInputDialog(null,"entre votre nom",JOptionPane.PLAIN_MESSAGE);
                        if(name != null )
                        {
                            if(name.length() >13) name = name.substring(0, 13);
                            if(score.WR.scores[score.WR.LONGUEURTOP-1]<Point){score.WR.writeScore(name,Point);score.refrech();}
                        }
                    }
                }
                window.setTitle("----SNAKE--VS--REDCHICK----Score:"+Point+"   Niveau:"+LVL);
                try{Thread.sleep(100);}catch(Exception e){}
            }
            window.setTitle("----SNAKE--VS--REDCHICK----Score:"+Point+"   Niveau:"+LVL);
            try{Thread.sleep(100);}catch(Exception e){}
        }    
    }
    /**
     * 
     *  méthode coordonnant le mouvement du serpent au clavier.
     *  
     * @pre :
     * @post: modifie le carte
     */
    public void SnakeMotion() {
        
        // vérifie si la nouvelle direction choisi par le joueur n'est pas l'oposer de la direction dans laquel le serpent de dirige.
        
        boolean OK = true;
        if (MotionDirection == UP    && NewMotionDirection == DOWN ){OK = false;}
        if (MotionDirection == DOWN  && NewMotionDirection == UP   ){OK = false;}
        if (MotionDirection == RIGHT && NewMotionDirection == LEFT ){OK = false;}
        if (MotionDirection == LEFT  && NewMotionDirection == RIGHT){OK = false;}
        
        // si le direction est possible, acctualisation de la direction
        if (OK)
        {
            switch (NewMotionDirection) 
                 { 
                     case UP    : if (map[YHead-1][XHead] != WALL) MotionDirection = UP   ; break; 
                     case DOWN  : if (map[YHead+1][XHead] != WALL) MotionDirection = DOWN ; break; 
                     case RIGHT : if (map[YHead][XHead+1] != WALL) MotionDirection = RIGHT; break;  
                     case LEFT  : if (map[YHead][XHead-1] != WALL) MotionDirection = LEFT ; break; 
                 }
        }
        
        //suivant la direction du serpent, modification de la carte
        switch (MotionDirection) 
                 { 
                     case UP    :    if (map[YHead-1][XHead] == FLOOR)         {graph.Setmap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.Setmap(YHead,XHead,CORPS); graph.Setmap(YHead-1,XHead,HEAD_UP); YHead--;snake.add(XHead,YHead);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.Setmap(YTail,XTail,TAIL);}
                                     else if (map[YHead-1][XHead] == CHICK)    {graph.Setmap(YHead,XHead,CORPS); graph.Setmap(YHead-1,XHead,HEAD_UP); Point = Point + 10; YHead--;snake.add(XHead,YHead);  if(IsMusic){Thread T1 = new Sound("snakehit.wav");T1.start();}  }
                                     else if (map[YHead-1][XHead] == REDCHICK || map[YHead-1][XHead] == REDCHICK_ON_CHICK || map[YHead-1][XHead] == REDCHICK_BEHIND_CHICK) {graph.Setmap(YHead,XHead,CORPS); graph.Setmap(YHead-1,XHead,HEAD_UP); Point = Point + 100;win = true; run = false; level = false; }
                                     else if (map[YHead-1][XHead] == CORPS || map[YHead-1][XHead]>40)    {win = false; run = false; level = false; }
                                     else if (map[YHead-1][XHead] == TAIL)     if(snake2.getTailX()==XHead || snake2.getTailY()==YHead-1){win = false; run = false; level = false;}else{graph.Setmap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.Setmap(YHead,XHead,CORPS); graph.Setmap(YHead-1,XHead,HEAD_UP); YHead--;snake.add(XHead,YHead);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.Setmap(YTail,XTail,TAIL);}
                                     break; 
                     case DOWN  :    if (map[YHead+1][XHead] == FLOOR)         {graph.Setmap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.Setmap(YHead,XHead,CORPS); graph.Setmap(YHead+1,XHead,HEAD_DOWN); YHead++;snake.add(XHead,YHead);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.Setmap(YTail,XTail,TAIL);}
                                     else if (map[YHead+1][XHead] == CHICK)    {graph.Setmap(YHead,XHead,CORPS); graph.Setmap(YHead+1,XHead,HEAD_DOWN); Point = Point + 10; YHead++;snake.add(XHead,YHead);if(IsMusic){Thread T1 = new Sound("snakehit.wav");T1.start();} }
                                     else if (map[YHead+1][XHead] == REDCHICK || map[YHead+1][XHead] == REDCHICK_ON_CHICK || map[YHead+1][XHead] == REDCHICK_BEHIND_CHICK) {graph.Setmap(YHead,XHead,CORPS); graph.Setmap(YHead+1,XHead,HEAD_DOWN); Point = Point + 100;win = true; run = false; level = false; }
                                     else if (map[YHead+1][XHead] == CORPS || map[YHead+1][XHead]>40)    {win = false; run = false; level = false; }
                                     else if (map[YHead+1][XHead] == TAIL)     if(snake2.getTailX()==XHead || snake2.getTailY()==YHead+1){win = false; run = false; level = false;}else{graph.Setmap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.Setmap(YHead,XHead,CORPS); graph.Setmap(YHead+1,XHead,HEAD_DOWN); YHead++;snake.add(XHead,YHead);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.Setmap(YTail,XTail,TAIL);}
                                     break; 
                     case RIGHT :    if (map[YHead][XHead+1] == FLOOR)         {graph.Setmap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.Setmap(YHead,XHead,CORPS); graph.Setmap(YHead,XHead+1,HEAD_RIGHT); XHead++;snake.add(XHead,YHead);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.Setmap(YTail,XTail,TAIL);}
                                     else if (map[YHead][XHead+1] == CHICK)    {graph.Setmap(YHead,XHead,CORPS); graph.Setmap(YHead,XHead+1,HEAD_RIGHT); Point = Point + 10; XHead++;snake.add(XHead,YHead);if(IsMusic){Thread T1 = new Sound("snakehit.wav");T1.start();} }
                                     else if (map[YHead][XHead+1] == REDCHICK || map[YHead][XHead+1] == REDCHICK_ON_CHICK || map[YHead][XHead+1] == REDCHICK_BEHIND_CHICK) {graph.Setmap(YHead,XHead,CORPS); graph.Setmap(YHead,XHead+1,HEAD_RIGHT); Point = Point + 100;win = true; run = false; level = false; }
                                     else if (map[YHead][XHead+1] == CORPS || map[YHead][XHead+1]>40)    {win = false; run = false; level = false; }
                                     else if (map[YHead][XHead+1] == TAIL)     if(snake2.getTailX()==XHead+1 || snake2.getTailY()==YHead){win = false; run = false; level = false;}else{graph.Setmap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.Setmap(YHead,XHead,CORPS); graph.Setmap(YHead,XHead+1,HEAD_RIGHT); XHead++;snake.add(XHead,YHead);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.Setmap(YTail,XTail,TAIL);}
                                     break; 
                     case LEFT  :    if (map[YHead][XHead-1] == FLOOR)         {graph.Setmap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.Setmap(YHead,XHead,CORPS); graph.Setmap(YHead,XHead-1,HEAD_LEFT); XHead--;snake.add(XHead,YHead);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.Setmap(YTail,XTail,TAIL);}
                                     else if (map[YHead][XHead-1] == CHICK)    {graph.Setmap(YHead,XHead,CORPS); graph.Setmap(YHead,XHead-1,HEAD_LEFT); Point = Point + 10; XHead--;snake.add(XHead,YHead);if(IsMusic){Thread T1 = new Sound("snakehit.wav");T1.start();} }
                                     else if (map[YHead][XHead-1] == REDCHICK || map[YHead][XHead-1] == REDCHICK_ON_CHICK || map[YHead][XHead-1] == REDCHICK_BEHIND_CHICK) {graph.Setmap(YHead,XHead,CORPS); graph.Setmap(YHead,XHead-1,HEAD_LEFT); Point = Point + 100;win = true; run = false; level = false; }
                                     else if (map[YHead][XHead-1] == CORPS || map[YHead][XHead-1]>40)    {win = false; run = false; level = false; }
                                     else if (map[YHead][XHead-1] == TAIL)     if(snake2.getTailX()==XHead-1 || snake2.getTailY()==YHead){win = false; run = false; level = false;}else{graph.Setmap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.Setmap(YHead,XHead,CORPS); graph.Setmap(YHead,XHead-1,HEAD_LEFT); XHead--;snake.add(XHead,YHead);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.Setmap(YTail,XTail,TAIL);}
                                     break; 
                 }
                 
             
    }
    public void SnakeMotion2() {
        
        // vérifie si la nouvelle direction choisi par le joueur n'est pas l'oposer de la direction dans laquel le serpent de dirige.
        boolean OK = true;
        if (MotionDirection2 == UP    && NewMotionDirection2 == DOWN ){OK = false;}
        if (MotionDirection2 == DOWN  && NewMotionDirection2 == UP   ){OK = false;}
        if (MotionDirection2 == RIGHT && NewMotionDirection2 == LEFT ){OK = false;}
        if (MotionDirection2 == LEFT  && NewMotionDirection2 == RIGHT){OK = false;}
        // si le direction est possible, acctualisation de la direction
        if (OK)
        {
            switch (NewMotionDirection2) 
                 { 
                     case UP    : if (map[YHead2-1][XHead2] != WALL) MotionDirection2 = UP   ; break; 
                     case DOWN  : if (map[YHead2+1][XHead2] != WALL) MotionDirection2 = DOWN ; break; 
                     case RIGHT : if (map[YHead2][XHead2+1] != WALL) MotionDirection2 = RIGHT; break;  
                     case LEFT  : if (map[YHead2][XHead2-1] != WALL) MotionDirection2 = LEFT ; break; 
                 }
        }
        //suivant la direction du serpent, modification de la carte
        switch (MotionDirection2) 
                 { 
                     case UP    :    if (map[YHead2-1][XHead2] == FLOOR)         {graph.Setmap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.Setmap(YHead2,XHead2,CORPS); graph.Setmap(YHead2-1,XHead2,HEAD_UP); YHead2--;snake.add(XHead2,YHead2);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.Setmap(YTail,XTail,TAIL);}
                                     else if (map[YHead2-1][XHead2] == CHICK)    {graph.Setmap(YHead2,XHead2,CORPS); graph.Setmap(YHead2-1,XHead2,HEAD_UP); Point = Point + 10; YHead2--;snake.add(XHead2,YHead2);  if(IsMusic){Thread T1 = new Sound("snakehit.wav");T1.start();}  }
                                     else if (map[YHead2-1][XHead2] == REDCHICK || map[YHead2-1][XHead2] == REDCHICK_ON_CHICK || map[YHead2-1][XHead2] == REDCHICK_BEHIND_CHICK) {graph.Setmap(YHead2,XHead2,CORPS); graph.Setmap(YHead2-1,XHead2,HEAD_UP); Point = Point + 100;win = true; run = false; level = false; }
                                     else if (map[YHead2-1][XHead2] == CORPS || map[YHead2-1][XHead2]>40)    {win = false; run = false; level = false; }
                                     else if (map[YHead2-1][XHead2] == TAIL)     if(snake1.getTailX()==XHead2 || snake1.getTailY()==YHead2-1){win = false; run = false; level = false;}else{graph.Setmap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.Setmap(YHead2,XHead2,CORPS); graph.Setmap(YHead2-1,XHead2,HEAD_UP); YHead2--;snake.add(XHead2,YHead2);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.Setmap(YTail,XTail,TAIL);}
                                     break; 
                     case DOWN  :    if (map[YHead2+1][XHead2] == FLOOR)         {graph.Setmap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.Setmap(YHead2,XHead2,CORPS); graph.Setmap(YHead2+1,XHead2,HEAD_DOWN); YHead2++;snake.add(XHead2,YHead2);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.Setmap(YTail,XTail,TAIL);}
                                     else if (map[YHead2+1][XHead2] == CHICK)    {graph.Setmap(YHead2,XHead2,CORPS); graph.Setmap(YHead2+1,XHead2,HEAD_DOWN); Point = Point + 10; YHead2++;snake.add(XHead2,YHead2);if(IsMusic){Thread T1 = new Sound("snakehit.wav");T1.start();} }
                                     else if (map[YHead2+1][XHead2] == REDCHICK || map[YHead2+1][XHead2] == REDCHICK_ON_CHICK || map[YHead2+1][XHead2] == REDCHICK_BEHIND_CHICK) {graph.Setmap(YHead2,XHead2,CORPS); graph.Setmap(YHead2+1,XHead2,HEAD_DOWN); Point = Point + 100;win = true; run = false; level = false; }
                                     else if (map[YHead2+1][XHead2] == CORPS || map[YHead2+1][XHead2]>40)    {win = false; run = false; level = false; }
                                     else if (map[YHead2+1][XHead2] == TAIL)     if(snake1.getTailX()==XHead2 || snake1.getTailY()==YHead2+1){win = false; run = false; level = false;}else{graph.Setmap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.Setmap(YHead2,XHead2,CORPS); graph.Setmap(YHead2+1,XHead2,HEAD_DOWN); YHead2++;snake.add(XHead2,YHead2);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.Setmap(YTail,XTail,TAIL);}
                                     break; 
                     case RIGHT :    if (map[YHead2][XHead2+1] == FLOOR)         {graph.Setmap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.Setmap(YHead2,XHead2,CORPS); graph.Setmap(YHead2,XHead2+1,HEAD_RIGHT); XHead2++;snake.add(XHead2,YHead2);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.Setmap(YTail,XTail,TAIL);}
                                     else if (map[YHead2][XHead2+1] == CHICK)    {graph.Setmap(YHead2,XHead2,CORPS); graph.Setmap(YHead2,XHead2+1,HEAD_RIGHT); Point = Point + 10; XHead2++;snake.add(XHead2,YHead2);if(IsMusic){Thread T1 = new Sound("snakehit.wav");T1.start();} }
                                     else if (map[YHead2][XHead2+1] == REDCHICK || map[YHead2][XHead2+1] == REDCHICK_ON_CHICK || map[YHead2][XHead2+1] == REDCHICK_BEHIND_CHICK) {graph.Setmap(YHead2,XHead2,CORPS); graph.Setmap(YHead2,XHead2+1,HEAD_RIGHT); Point = Point + 100;win = true; run = false; level = false; }
                                     else if (map[YHead2][XHead2+1] == CORPS || map[YHead2][XHead2+1]>40)    {win = false; run = false; level = false; }
                                     else if (map[YHead2][XHead2+1] == TAIL)     if(snake1.getTailX()==XHead2+1 || snake1.getTailY()==YHead2){win = false; run = false; level = false;}else{graph.Setmap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.Setmap(YHead2,XHead2,CORPS); graph.Setmap(YHead2,XHead2+1,HEAD_RIGHT); XHead2++;snake.add(XHead2,YHead2);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.Setmap(YTail,XTail,TAIL);}
                                     break; 
                     case LEFT  :    if (map[YHead2][XHead2-1] == FLOOR)         {graph.Setmap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.Setmap(YHead2,XHead2,CORPS); graph.Setmap(YHead2,XHead2-1,HEAD_LEFT); XHead2--;snake.add(XHead2,YHead2);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.Setmap(YTail,XTail,TAIL);}
                                     else if (map[YHead2][XHead2-1] == CHICK)    {graph.Setmap(YHead2,XHead2,CORPS); graph.Setmap(YHead2,XHead2-1,HEAD_LEFT); Point = Point + 10; XHead2--;snake.add(XHead2,YHead2);if(IsMusic){Thread T1 = new Sound("snakehit.wav");T1.start();} }
                                     else if (map[YHead2][XHead2-1] == REDCHICK || map[YHead2][XHead2-1] == REDCHICK_ON_CHICK || map[YHead2][XHead2-1] == REDCHICK_BEHIND_CHICK) {graph.Setmap(YHead2,XHead2,CORPS); graph.Setmap(YHead2,XHead2-1,HEAD_LEFT); Point = Point + 100;win = true; run = false; level = false; }
                                     else if (map[YHead2][XHead2-1] == CORPS || map[YHead2][XHead2-1]>40)    {win = false; run = false; level = false; }
                                     else if (map[YHead2][XHead2-1] == TAIL)     if(snake1.getTailX()==XHead2-1 || snake1.getTailY()==YHead2){win = false; run = false; level = false;}else{graph.Setmap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.Setmap(YHead2,XHead2,CORPS); graph.Setmap(YHead2,XHead2-1,HEAD_LEFT); XHead2--;snake.add(XHead2,YHead2);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.Setmap(YTail,XTail,TAIL);}
                                     break; 
                 }
             
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
                }
                if (e.getSource() == newGame) 
                {
                    LVL = 1;
                    newgame = true; 
                    win = false;
                    run = false;
                    level = false;
                    T = IO.ReadMultiple(""+LVL);
                    map = T.terrain;
                    graph.NewGame(true); window.repaint();
                    try{Thread.sleep(Speed);}catch(Exception S){}
                    graph.NewGame(false); graph.Setmap(map);window.repaint();
                    gameBegin = false;
                    run = true;
                    level = true;
                    newgame = false;
                    Point = 0;
                    Speed = SpeedInit;
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
                    pause = !pause;
                }
                if (e.getSource() == Save) 
                {
                    pause = true;
                    String name = JOptionPane.showInputDialog(null,"Nom de la sauvegarde (sans extention)",JOptionPane.PLAIN_MESSAGE);
                    if(name != null )
                    {
                        int[][] temp = graph.GetMap();
                        int[] coord = {XRedChick,YRedChick};
                        IO.Save(temp,coord,snake1,snake2,LVL,name);
                    }
                }
                if (e.getSource() == Load) 
                {
                    newgame = true; 
                    win = false;
                    run = false;
                    level = false;
                    gameBegin = false;
                    pause = true;
                    String name = JOptionPane.showInputDialog(null,"entre votre nom",JOptionPane.PLAIN_MESSAGE);
                    if(name != null )
                    {
                        T = IO.ReadMultiple(name);
                        map = T.terrain;
                        graph.Setmap(map);
                        LVL = T.lvl;
                        Point = 0;
                        Speed = (int)SpeedInit/(LVL+5);
                    }
                    run = true;
                    level = true;
                    newgame = false;
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
