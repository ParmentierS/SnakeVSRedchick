

/**
 * 
 * @author :
 * @version :
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Game{

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
    
    // variable influancent directement le jeux
    private boolean run = true;
    private boolean level = true;
    private boolean game = true;
    private boolean newgame = false;
    private boolean gameBegin = false;
    
    private boolean win = false;
    private boolean pause = false;
    
    private boolean IsMusic = true;
    
    private final int SpeedInit = 2500 ; // en milliseconde 250
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
    
    private int XHead;
    private int YHead;
    
    private int XTail;
    private int YTail;
    
    private Snake snake;
    
    // variable conserant le poussin rouge
    private int XRedChick;
    private int YRedChick;
    
    private RedChick redChick;
    
    // map du jeux
    private int[][] map;
    private WriteReadDATA IO = new WriteReadDATA();
    
    /**
     * @pre :
     * @post:
     *        
     */
    public Game()
    {  
        // création de la fenetre d'affichage du top 10.
        windowTop = new SimpleFenetre();
        score = new TOP() ;
        Box Top = Box.createHorizontalBox();
        Box Button = Box.createVerticalBox();
        Box All = Box.createHorizontalBox();
        
        // ajout des boutons.
        Button.add(Box.createHorizontalStrut(50));
        exittop = new JButton("exit");                 
        exittop.addActionListener(new listenerTop());
        Button.add(exittop);
        Button.add(Box.createVerticalStrut(50));
        refrech = new JButton("refrech"); 
        refrech.addActionListener(new listenerTop());
        Button.add(refrech);
        Button.add(Box.createVerticalStrut(50));
        
        // incorporation des boxs et affichage.
        Top.add(score);
        All.add(Top);
        All.add(Button);
        Container paneTop = windowTop.getContentPane();
        paneTop.add(All);
        windowTop.pack();
        windowTop.setVisible(VisibleTop);
        
        //création de la fenetre de jeux.
        window = new JFrame ("----SNAKE--VS--REDCHICK----"); 
        
        graph = new JPlotPanel () ;
        
        // déclaration des boxs
        Box TOTALBOX = Box.createVerticalBox();
        Box STAT = Box.createVerticalBox();
        Box Graf_Menu = Box.createHorizontalBox();
        Box Menu = Box.createVerticalBox();
        
        // remplissage du menu
        Menu.add(Box.createHorizontalStrut(50));
        exitButton = new JButton(new ImageIcon("Head_Right.JPG"));                 
        exitButton.addActionListener(new listener());
        Menu.add(exitButton);
        Menu.add(Box.createVerticalStrut(50));
        top10 = new JButton("Top 10"); 
        top10.addActionListener(new listener());
        Menu.add(top10);
        Menu.add(Box.createVerticalStrut(50));
        newGame = new JButton("New Game");                 
        newGame.addActionListener(new listener());
        Menu.add(newGame);
        Menu.add(Box.createVerticalStrut(50));
        Music = new JButton("music");   
        Music.addActionListener(new listener());
        Menu.add(Music);
        Menu.add(Box.createVerticalStrut(50));
        
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
                            case KeyEvent.VK_UP     : if(!gameBegin){gameBegin = true; MotionDirection = LEFT;}    NewMotionDirection = UP;   break; 
                            case KeyEvent.VK_LEFT   : if(!gameBegin){gameBegin = true; MotionDirection = LEFT;}  NewMotionDirection = LEFT; break; 
                            case KeyEvent.VK_DOWN   : if(!gameBegin){gameBegin = true; MotionDirection = LEFT;}  NewMotionDirection = DOWN; break; 
                            case KeyEvent.VK_RIGHT  : if(!gameBegin){gameBegin = true; MotionDirection = LEFT;} NewMotionDirection = RIGHT;break;
                        }
                    }
                }
            }, AWTEvent.KEY_EVENT_MASK);
        
        
        // lecture et initialisation de la map.
        map = IO.Read(""+LVL+".SNAKEMAP");
        graph.SetMap(map);
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
                
                // recherche des et actualisation des coordonnées du serpent et du poussin rouge
                for (int l = 0; l < 20 ; l++ )
                {
                    for (int h = 0; h < 20 ; h++ )
                    {
                        if( map[h][l] == TAIL){XTail = l; YTail = h;}
                        if( map[h][l] == REDCHICK ){XRedChick = l; YRedChick = h;}
                        else if( map[h][l] == REDCHICK_ON_CHICK ){XRedChick = l; YRedChick = h;}
                        else if( map[h][l] == REDCHICK_BEHIND_CHICK ){XRedChick = l; YRedChick = h;}
                        if( map[h][l] < 45 && map[h][l] > 40 ){XHead = l; YHead = h;}
                    }
                }
                snake = new Snake();
                if ( XTail == XHead ) 
                    if (YTail<YHead) 
                        {for(int i=YTail; i<=YHead ; i++){snake.add(XHead,i);System.out.println("level" +XHead+"  "+i );}}
                    else
                        {for(int i=YTail; i>=YHead ; i--){snake.add(XHead,i);System.out.println("level" +XHead+"  "+i );}}
                if ( YTail == YHead ) 
                    if (XTail<XHead) 
                        {for(int i=XTail; i<=XHead ; i++){snake.add(i,YHead);System.out.println("level" +i+"  "+YHead );}}
                    else
                        {for(int i=XTail; i>=XHead ; i--){snake.add(i,YHead);System.out.println("level" +i+"  "+YHead );}}
                
                
                redChick = new RedChick(LVL,XRedChick,YRedChick);
                
                
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
                            SnakeMotion();
                            switch (redChick.position(map,XRedChick,YRedChick,XHead,YHead,LVL)) 
                            { 
                                case UP    : if (map[YRedChick-1][XRedChick] == CHICK) 
                                             {
                                                 if(map[YRedChick][XRedChick] == REDCHICK_BEHIND_CHICK)  {graph.SetMap(YRedChick,XRedChick,CHICK);graph.SetMap(YRedChick-1,XRedChick,REDCHICK_BEHIND_CHICK);YRedChick--;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK)         {graph.SetMap(YRedChick,XRedChick,FLOOR);graph.SetMap(YRedChick-1,XRedChick,REDCHICK_ON_CHICK);YRedChick--;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK_ON_CHICK){graph.SetMap(YRedChick,XRedChick,CHICK);graph.SetMap(YRedChick-1,XRedChick,REDCHICK_ON_CHICK);YRedChick--;}
                                             } 
                                             else 
                                             {
                                                 if(map[YRedChick][XRedChick] == REDCHICK_BEHIND_CHICK)  {graph.SetMap(YRedChick,XRedChick,CHICK);graph.SetMap(YRedChick-1,XRedChick,REDCHICK);YRedChick--;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK)         {graph.SetMap(YRedChick,XRedChick,FLOOR);graph.SetMap(YRedChick-1,XRedChick,REDCHICK);YRedChick--;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK_ON_CHICK){graph.SetMap(YRedChick,XRedChick,CHICK);graph.SetMap(YRedChick-1,XRedChick,REDCHICK);YRedChick--;}
                                             }  
                                             break; 
                                case DOWN  : if (map[YRedChick+1][XRedChick] == CHICK)
                                             {
                                                 if(map[YRedChick][XRedChick] == REDCHICK_BEHIND_CHICK)  {graph.SetMap(YRedChick,XRedChick,CHICK);graph.SetMap(YRedChick+1,XRedChick,REDCHICK_BEHIND_CHICK);YRedChick++;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK)         {graph.SetMap(YRedChick,XRedChick,FLOOR);graph.SetMap(YRedChick+1,XRedChick,REDCHICK_ON_CHICK);YRedChick++;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK_ON_CHICK){graph.SetMap(YRedChick,XRedChick,CHICK);graph.SetMap(YRedChick+1,XRedChick,REDCHICK_ON_CHICK);YRedChick++;}
                                             } 
                                             else 
                                             {
                                                 if(map[YRedChick][XRedChick] == REDCHICK_BEHIND_CHICK)  {graph.SetMap(YRedChick,XRedChick,CHICK);graph.SetMap(YRedChick+1,XRedChick,REDCHICK);YRedChick++;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK)         {graph.SetMap(YRedChick,XRedChick,FLOOR);graph.SetMap(YRedChick+1,XRedChick,REDCHICK);YRedChick++;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK_ON_CHICK){graph.SetMap(YRedChick,XRedChick,CHICK);graph.SetMap(YRedChick+1,XRedChick,REDCHICK);YRedChick++;}
                                             }  
                                             break; 
                                case RIGHT : if (map[YRedChick][XRedChick+1] == CHICK)
                                             {
                                                 if(map[YRedChick][XRedChick] == REDCHICK_BEHIND_CHICK)  {graph.SetMap(YRedChick,XRedChick,CHICK);graph.SetMap(YRedChick,XRedChick+1,REDCHICK_BEHIND_CHICK);XRedChick++;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK)         {graph.SetMap(YRedChick,XRedChick,FLOOR);graph.SetMap(YRedChick,XRedChick+1,REDCHICK_ON_CHICK);XRedChick++;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK_ON_CHICK){graph.SetMap(YRedChick,XRedChick,CHICK);graph.SetMap(YRedChick,XRedChick+1,REDCHICK_ON_CHICK);XRedChick++;}
                                             } 
                                             else 
                                             {
                                                 if(map[YRedChick][XRedChick] == REDCHICK_BEHIND_CHICK)  {graph.SetMap(YRedChick,XRedChick,CHICK);graph.SetMap(YRedChick,XRedChick+1,REDCHICK);XRedChick++;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK)         {graph.SetMap(YRedChick,XRedChick,FLOOR);graph.SetMap(YRedChick,XRedChick+1,REDCHICK);XRedChick++;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK_ON_CHICK){graph.SetMap(YRedChick,XRedChick,CHICK);graph.SetMap(YRedChick,XRedChick+1,REDCHICK);XRedChick++;}
                                             }   
                                             break; 
                                case LEFT  : if (map[YRedChick][XRedChick-1] == CHICK)
                                             {
                                                 if(map[YRedChick][XRedChick] == REDCHICK_BEHIND_CHICK)  {graph.SetMap(YRedChick,XRedChick,CHICK);graph.SetMap(YRedChick,XRedChick-1,REDCHICK_BEHIND_CHICK);XRedChick--;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK)         {graph.SetMap(YRedChick,XRedChick,FLOOR);graph.SetMap(YRedChick,XRedChick-1,REDCHICK_ON_CHICK);XRedChick--;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK_ON_CHICK){graph.SetMap(YRedChick,XRedChick,CHICK);graph.SetMap(YRedChick,XRedChick-1,REDCHICK_ON_CHICK);XRedChick--;}
                                             } 
                                             else 
                                             {
                                                 if(map[YRedChick][XRedChick] == REDCHICK_BEHIND_CHICK)  {graph.SetMap(YRedChick,XRedChick,CHICK);graph.SetMap(YRedChick,XRedChick-1,REDCHICK);XRedChick--;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK)         {graph.SetMap(YRedChick,XRedChick,FLOOR);graph.SetMap(YRedChick,XRedChick-1,REDCHICK);XRedChick--;}
                                                 else if (map[YRedChick][XRedChick] == REDCHICK_ON_CHICK){graph.SetMap(YRedChick,XRedChick,CHICK);graph.SetMap(YRedChick,XRedChick-1,REDCHICK);XRedChick--;}
                                             }  
                                             break; 
                                case HIDDENTRUE   : if (map[YRedChick][XRedChick] == CHICK)
                                             {
                                                 graph.SetMap(YRedChick,XRedChick,REDCHICK_BEHIND_CHICK);
                                             } 
                                             break; 
                                case HIDDENFALSE  : if (map[YRedChick][XRedChick] == CHICK)
                                             {
                                                 graph.SetMap(YRedChick,XRedChick,REDCHICK_ON_CHICK);
                                             } 
                                             break; 
                            }
                        }
                    }
                    
                    END = System.currentTimeMillis();
                    
                    window.repaint();
                    try{Thread.sleep(Speed - END + START);}catch(Exception e){}
                }
                // si le joueur resort vainceur : la boucle lvl reste opérationnel et lvl est incrémenter de 1.
                if (win)
                {
                    LVL++;
                    map = IO.Read(""+LVL+".SNAKEMAP");
                    graph.SetMap(map);
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
                        //String name = JOptionPane.showInputDialog(null,"entre votre nom",JOptionPane.PLAIN_MESSAGE);
                        //if(name != null )
                        //{
                            //if(name.length() >13) name = name.substring(0, 13);
                            //if(score.WR.scores[score.WR.LONGUEURTOP-1]<Point){score.WR.writeScore(name,Point);score.refrech();}
                        //}
                    }
                }
                try{Thread.sleep(10);}catch(Exception e){}
            }
            try{Thread.sleep(10);}catch(Exception e){}
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
                     case UP    :    if (map[YHead-1][XHead] == FLOOR)         {graph.SetMap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.SetMap(YHead,XHead,CORPS); graph.SetMap(YHead-1,XHead,HEAD_UP); YHead--;snake.add(XHead,YHead);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.SetMap(YTail,XTail,TAIL);}
                                     else if (map[YHead-1][XHead] == CHICK)    {graph.SetMap(YHead,XHead,CORPS); graph.SetMap(YHead-1,XHead,HEAD_UP); Point = Point + 10; YHead--;snake.add(XHead,YHead);  if(IsMusic){Thread T1 = new Sound("snakehit.wav");T1.start();}  }
                                     else if (map[YHead-1][XHead] == REDCHICK || map[YHead-1][XHead] == REDCHICK_ON_CHICK || map[YHead-1][XHead] == REDCHICK_BEHIND_CHICK) {graph.SetMap(YHead,XHead,CORPS); graph.SetMap(YHead-1,XHead,HEAD_UP); Point = Point + 100;win = true; run = false; level = false; }
                                     else if (map[YHead-1][XHead] == CORPS)    {win = false; run = false; level = false; }
                                     else if (map[YHead-1][XHead] == TAIL)     {graph.SetMap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.SetMap(YHead,XHead,CORPS); graph.SetMap(YHead-1,XHead,HEAD_UP); YHead--;snake.add(XHead,YHead);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.SetMap(YTail,XTail,TAIL);}
                                     break; 
                     case DOWN  :    if (map[YHead+1][XHead] == FLOOR)         {graph.SetMap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.SetMap(YHead,XHead,CORPS); graph.SetMap(YHead+1,XHead,HEAD_DOWN); YHead++;snake.add(XHead,YHead);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.SetMap(YTail,XTail,TAIL);}
                                     else if (map[YHead+1][XHead] == CHICK)    {graph.SetMap(YHead,XHead,CORPS); graph.SetMap(YHead+1,XHead,HEAD_DOWN); Point = Point + 10; YHead++;snake.add(XHead,YHead);if(IsMusic){Thread T1 = new Sound("snakehit.wav");T1.start();} }
                                     else if (map[YHead+1][XHead] == REDCHICK || map[YHead+1][XHead] == REDCHICK_ON_CHICK || map[YHead+1][XHead] == REDCHICK_BEHIND_CHICK) {graph.SetMap(YHead,XHead,CORPS); graph.SetMap(YHead+1,XHead,HEAD_DOWN); Point = Point + 100;win = true; run = false; level = false; }
                                     else if (map[YHead+1][XHead] == CORPS)    {win = false; run = false; level = false; }
                                     else if (map[YHead+1][XHead] == TAIL)     {graph.SetMap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.SetMap(YHead,XHead,CORPS); graph.SetMap(YHead+1,XHead,HEAD_DOWN); YHead++;snake.add(XHead,YHead);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.SetMap(YTail,XTail,TAIL);}
                                     break; 
                     case RIGHT :    if (map[YHead][XHead+1] == FLOOR)         {graph.SetMap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.SetMap(YHead,XHead,CORPS); graph.SetMap(YHead,XHead+1,HEAD_RIGHT); XHead++;snake.add(XHead,YHead);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.SetMap(YTail,XTail,TAIL);}
                                     else if (map[YHead][XHead+1] == CHICK)    {graph.SetMap(YHead,XHead,CORPS); graph.SetMap(YHead,XHead+1,HEAD_RIGHT); Point = Point + 10; XHead++;snake.add(XHead,YHead);if(IsMusic){Thread T1 = new Sound("snakehit.wav");T1.start();} }
                                     else if (map[YHead][XHead+1] == REDCHICK || map[YHead][XHead+1] == REDCHICK_ON_CHICK || map[YHead][XHead+1] == REDCHICK_BEHIND_CHICK) {graph.SetMap(YHead,XHead,CORPS); graph.SetMap(YHead,XHead+1,HEAD_RIGHT); Point = Point + 100;win = true; run = false; level = false; }
                                     else if (map[YHead][XHead+1] == CORPS)    {win = false; run = false; level = false; }
                                     else if (map[YHead][XHead+1] == TAIL)     {graph.SetMap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.SetMap(YHead,XHead,CORPS); graph.SetMap(YHead,XHead+1,HEAD_RIGHT); XHead++;snake.add(XHead,YHead);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.SetMap(YTail,XTail,TAIL);}
                                     break; 
                     case LEFT  :    if (map[YHead][XHead-1] == FLOOR)         {graph.SetMap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.SetMap(YHead,XHead,CORPS); graph.SetMap(YHead,XHead-1,HEAD_LEFT); XHead--;snake.add(XHead,YHead);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.SetMap(YTail,XTail,TAIL);}
                                     else if (map[YHead][XHead-1] == CHICK)    {graph.SetMap(YHead,XHead,CORPS); graph.SetMap(YHead,XHead-1,HEAD_LEFT); Point = Point + 10; XHead--;snake.add(XHead,YHead);if(IsMusic){Thread T1 = new Sound("snakehit.wav");T1.start();} }
                                     else if (map[YHead][XHead-1] == REDCHICK || map[YHead][XHead-1] == REDCHICK_ON_CHICK || map[YHead][XHead-1] == REDCHICK_BEHIND_CHICK) {graph.SetMap(YHead,XHead,CORPS); graph.SetMap(YHead,XHead-1,HEAD_LEFT); Point = Point + 100;win = true; run = false; level = false; }
                                     else if (map[YHead][XHead-1] == CORPS)    {win = false; run = false; level = false; }
                                     else if (map[YHead][XHead-1] == TAIL)     {graph.SetMap(snake.getTailY(),snake.getTailX(),FLOOR);snake.del();graph.SetMap(YHead,XHead,CORPS); graph.SetMap(YHead,XHead-1,HEAD_LEFT); XHead--;snake.add(XHead,YHead);
                                                                                XTail = snake.getTailX();YTail = snake.getTailY();graph.SetMap(YTail,XTail,TAIL);}
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
                    System.exit(0); 
                }
                if (e.getSource() == newGame) 
                {
                    LVL = 1;
                    newgame = true; 
                    win = false;
                    run = false;
                    level = false;
                    gameBegin = false;
                    map = IO.Read(""+LVL+".SNAKEMAP");
                    graph.NewGame(true); window.repaint();
                    try{Thread.sleep(Speed);}catch(Exception S){}
                    graph.NewGame(false); graph.SetMap(map);window.repaint();
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
    
        
    /**
     * @pre :
     * @post:
     */
    public static void main(String[] args) {
     Game game;
     game=new Game();
    }
    
}
