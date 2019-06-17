

public class RedChick
{
	//constantes
	public static final int FLOOR = 0;
    public static final int WALL = 1;
    public static final int CHICK = 2;
    public static final int REDCHICK = 3;
    public static final int HEAD_UP = 41;
    public static final int HEAD_LEFT = 42;
    public static final int HEAD_RIGHT = 44;
    public static final int HEAD_DOWN = 43;
    public static final int CORPS = 5;
    public static final int TAIL = 6;
    
    public static final int LEFT = HEAD_LEFT;
    public static final int RIGHT = HEAD_RIGHT;
    public static final int DOWN = HEAD_DOWN;
    public static final int UP = HEAD_UP;
    public static final int HIDDENTRUE = 50;
    public static final int HIDDENFALSE = 51;
    public static final int CENTER = 52;
    
	private int LVL; 
	@SuppressWarnings("unused")
	private static int X; 
	@SuppressWarnings("unused")
	private static int Y; 
	private static int[][] map;
	
	public RedChick(int lvl, int x, int y)
	{
	    X = x;
	    Y = y;
		LVL = lvl;
	}
	
	/*private int trouverLaBonnePosition(int XREDCHUCK, int YREDCHUCK , int XSnake, int YSnake)
	{
	    int[][] TERRAIN = createWarningMap(XSnake,YSnake);
	    
	    
		if (TERRAIN[YREDCHUCK][XREDCHUCK+1]==2147483647)
	    {TERRAIN[YREDCHUCK][XREDCHUCK+1] = 0;}
	    if (TERRAIN[YREDCHUCK][XREDCHUCK-1]==2147483647)
	    {TERRAIN[YREDCHUCK][XREDCHUCK-1] = 0;}
	    if (TERRAIN[YREDCHUCK+1][XREDCHUCK]==2147483647)
	    {TERRAIN[YREDCHUCK+1][XREDCHUCK] = 0;}
	    if (TERRAIN[YREDCHUCK-1][XREDCHUCK]==2147483647)
	    {TERRAIN[YREDCHUCK-1][XREDCHUCK] = 0;}
	    
	    for(int i = 0; i < map[0].length;i++)
		{
			for(int j = 0; j < map.length ; j++)
			{
				if (TERRAIN[i][j] == 2147483647)
					System.out.print("§  ");
				else if(TERRAIN[i][j] < 10)
					System.out.print(TERRAIN[i][j] + "  ");
				else
					System.out.print(TERRAIN[i][j] + " ");
			}
			System.out.print("\n");
		}
	    
	    if (TERRAIN[YREDCHUCK][XREDCHUCK+1]>TERRAIN[YREDCHUCK][XREDCHUCK-1] && TERRAIN[YREDCHUCK][XREDCHUCK+1]>=TERRAIN[YREDCHUCK+1][XREDCHUCK] && TERRAIN[YREDCHUCK][XREDCHUCK+1]>=TERRAIN[YREDCHUCK+1][XREDCHUCK] && TERRAIN[YREDCHUCK][XREDCHUCK+1] != 0)
	    {return RIGHT;}
	    if (TERRAIN[YREDCHUCK][XREDCHUCK-1]>TERRAIN[YREDCHUCK][XREDCHUCK+1] && TERRAIN[YREDCHUCK][XREDCHUCK-1]>=TERRAIN[YREDCHUCK-1][XREDCHUCK] && TERRAIN[YREDCHUCK][XREDCHUCK-1]>=TERRAIN[YREDCHUCK-1][XREDCHUCK] && TERRAIN[YREDCHUCK][XREDCHUCK-1] != 0)
	    {return LEFT;}
	    if (TERRAIN[YREDCHUCK+1][XREDCHUCK]>TERRAIN[YREDCHUCK-1][XREDCHUCK] && TERRAIN[YREDCHUCK+1][XREDCHUCK]>=TERRAIN[YREDCHUCK][XREDCHUCK-1] && TERRAIN[YREDCHUCK+1][XREDCHUCK]>=TERRAIN[YREDCHUCK][XREDCHUCK+1] && TERRAIN[YREDCHUCK+1][XREDCHUCK] != 0)
	    {return DOWN;}
	    if (TERRAIN[YREDCHUCK-1][XREDCHUCK]>TERRAIN[YREDCHUCK+1][XREDCHUCK] && TERRAIN[YREDCHUCK-1][XREDCHUCK]>=TERRAIN[YREDCHUCK][XREDCHUCK-1] && TERRAIN[YREDCHUCK-1][XREDCHUCK]>=TERRAIN[YREDCHUCK][XREDCHUCK+1] && TERRAIN[YREDCHUCK-1][XREDCHUCK] != 0)
	    {return UP;}
		return 0;
	}*/
	
	public int position(int[][] map,int XREDCHUCK, int YREDCHUCK , int [] XSnake, int [] YSnake, int lvl)
	{
		RedChick.map=map;
	    switch(LVL)
	    {
	    	case 1  : return trouverLaBonnePosition( XREDCHUCK, YREDCHUCK , XSnake, YSnake); 
	    	case 2  : return trouverLaBonnePosition( XREDCHUCK, YREDCHUCK , XSnake, YSnake); 
	    	case 3  : return trouverLaBonnePosition( XREDCHUCK, YREDCHUCK , XSnake, YSnake); 
	    	case 4  : return trouverLaBonnePosition( XREDCHUCK, YREDCHUCK , XSnake, YSnake); 
	    	
	    }
		
		return trouverLaBonnePosition(XREDCHUCK, YREDCHUCK , XSnake, YSnake); 
	}
	/**
     * 
     * @pre: 
     * @post:
     */
	public int position(int[][] map,int XREDCHUCK, int YREDCHUCK , int XSnake, int YSnake, int lvl)
	{
		this.map=map;
		
		return trouverLaBonnePosition1( XREDCHUCK, YREDCHUCK , XSnake, YSnake); 
	}
	public boolean Hidden()
	{
		/*
		 * IA partie 2 : Le poussin décide lorsque il tombe sur une case poussin jaune si il apparaît ou non a l'ecran
		 */
		return true;
		//return false;
	}
	private static int[][] createWarningMap(int [] XSnake, int [] YSnake)
	{
		int[][] multiWarningMap = new int[map[0].length][map.length];
		int[][] baseWarningMap = new int[map[0].length][map.length];
		for(int i = 0; i < map[0].length;i++)
		{
			for(int j = 0; j < map.length ; j++)
			{
				baseWarningMap[i][j] = 2147483647;
			}
		}
		multiWarningMap = baseWarningMap;
		for (int h = 0; h < XSnake.length ; h++)
		{	
			int[][] warningMap = baseWarningMap;
			warningMap = createWarningMap(YSnake[h], XSnake[h] ,map[YSnake[h]][XSnake[h]],warningMap, 0);
			for(int i = 0; i < map[0].length;i++)
			{
				for(int j = 0; j < map.length ; j++)
				{
					if(warningMap[i][j] < multiWarningMap[i][j])
					{
						multiWarningMap[i][j] = warningMap[i][j];
					}
				}
			}
		}
		return multiWarningMap;
	}
	/**
     * 
     * @pre: 
     * @post:
     */
	private static int[][] createWarningMap1(int XSnake, int YSnake)
	{
		int[][] warningMap = new int[map[0].length][map.length];
		for(int i = 0; i < map[0].length;i++)
		{
			for(int j = 0; j < map.length ; j++)
			{
				warningMap[i][j] = 10000;
			}
		}
		warningMap = createWarningMap1(YSnake, XSnake,map[YSnake][XSnake],warningMap, 0);
		return warningMap;
	}
	private int trouverLaBonnePosition(int XREDCHUCK, int YREDCHUCK , int []XSnake, int []YSnake)
	{
		int down = 0, right = 0, up = 0, left = 0;
		int[][] TERRAIN = createWarningMap(XSnake,YSnake);
		if (XREDCHUCK < TERRAIN.length && TERRAIN[YREDCHUCK][XREDCHUCK+1]!=2147483647)
	    {right = TERRAIN[YREDCHUCK][XREDCHUCK+1];}
	    if (XREDCHUCK > 0 && TERRAIN[YREDCHUCK][XREDCHUCK-1]!=2147483647)
	    {left = TERRAIN[YREDCHUCK][XREDCHUCK-1];}
	    if (YREDCHUCK < TERRAIN[0].length && TERRAIN[YREDCHUCK+1][XREDCHUCK]!=2147483647)
	    {down = TERRAIN[YREDCHUCK+1][XREDCHUCK];}
	    if (YREDCHUCK > 0 && TERRAIN[YREDCHUCK-1][XREDCHUCK]!=2147483647)
	    {up = TERRAIN[YREDCHUCK-1][XREDCHUCK];}
	    
	    for(int i = 0; i < map[0].length;i++)
		{
			for(int j = 0; j < map.length ; j++)
			{
				if (TERRAIN[i][j] == 2147483647)
					TERRAIN[i][j] = 0;
			}
		}
		
		return calculatemax(down , up, right , left , 0, TERRAIN , XREDCHUCK, YREDCHUCK , "durl" , 10);
	}
	/**
     * 
     * @pre: 
     * @post:
     */
	private int trouverLaBonnePosition1(int XREDCHUCK, int YREDCHUCK , int XSnake, int YSnake)
	{
		int down = 0, right = 0, up = 0, left = 0;
		int[][] TERRAIN = createWarningMap1(XSnake,YSnake);
		if (XREDCHUCK < TERRAIN.length && TERRAIN[YREDCHUCK][XREDCHUCK+1]!=10000)
	    {right = TERRAIN[YREDCHUCK][XREDCHUCK+1];}
	    if (XREDCHUCK > 0 && TERRAIN[YREDCHUCK][XREDCHUCK-1]!=10000)
	    {left = TERRAIN[YREDCHUCK][XREDCHUCK-1];}
	    if (YREDCHUCK < TERRAIN[0].length && TERRAIN[YREDCHUCK+1][XREDCHUCK]!=10000)
	    {down = TERRAIN[YREDCHUCK+1][XREDCHUCK];}
	    if (YREDCHUCK > 0 && TERRAIN[YREDCHUCK-1][XREDCHUCK]!=10000)
	    {up = TERRAIN[YREDCHUCK-1][XREDCHUCK];}
	    
	    
	    for(int i = 0; i < map[0].length;i++)
		{
			for(int j = 0; j < map.length ; j++)
			{
				if (TERRAIN[i][j] == 2147483647)
					TERRAIN[i][j] = 0;
			}
		}
		
		return calculatemax(down , up, right , left , 0, TERRAIN , XREDCHUCK, YREDCHUCK , "durl" , 10);
	}
	private int calculatemax(int down , int up, int right , int left , int center, int[][] TERRAIN , int XREDCHUCK, int YREDCHUCK , String egality, int iterator)
	{
		int max = Math.max(down , right);
		max = Math.max(max , up);
		max = Math.max(max , left);
		///*if (LVL%2 == 0)
		//{
			//center = TERRAIN[YREDCHUCK][XREDCHUCK];
			//max = Math.max(max , center);
		//}*/
		//System.out.println(down + " " + right + " " + up + " " + left + " " + center + " " + XREDCHUCK + " " + YREDCHUCK);
		String s = "";
		if (max == down)	s = s + 'd';
		else 				down = 0;
		if (max == right)	s = s + 'r';
		else 				right = 0;
		if (max == up)		s = s + 'u';
		else 				up = 0;
		if (max == left)	s = s + 'l';
		else 				left =0;
		if (s.length() == 0)
		{
			//System.out.println("longueur de la chaine : " + s.length());
			return CENTER;
		}
		else if (down == 0 && right == 0 && up == 0 && left == 0)
		{
			return CENTER;
		}
		else if (iterator == 0 || s.length() == 1)
		{
			int random = ((int) (Math.random()*100))%s.length();
			switch(s.charAt(random))
			{
			case 'd' : return DOWN;
   			case 'r' : return RIGHT;
		   	case 'u' : return UP;
			case 'l' : return LEFT;
			default : return CENTER;
			}
		}
		else
		{
			TERRAIN = createDoubleWarningMap(TERRAIN);
			if (right !=0)
		    {right = TERRAIN[YREDCHUCK][XREDCHUCK+1];}
		    if (left != 0)
		    {left = TERRAIN[YREDCHUCK][XREDCHUCK-1];}
		    if (down != 0)
		    {down = TERRAIN[YREDCHUCK+1][XREDCHUCK];}
		    if (up != 0)
		    {up = TERRAIN[YREDCHUCK-1][XREDCHUCK];}
			return calculatemax(down , up, right , left , center, TERRAIN , XREDCHUCK, YREDCHUCK , s, iterator - 1);
		}
	}
	/*private static int[][] createDoubleWarningMap (int[][] TERRAIN)
	{
		int[][] TERRAIN2 = new int[TERRAIN[0].length][TERRAIN.length];
		for (int i = 0; i < TERRAIN[0].length ; i++)
		{
			for (int j = 0; j < TERRAIN.length ; j++)
			{
				if (map[i][j] == WALL || map[i][j] == HEAD_UP || map[i][j] == HEAD_LEFT || map[i][j] == HEAD_RIGHT || map[i][j] == HEAD_DOWN || map[i][j] == CORPS || map[i][j] == TAIL )
				{
					TERRAIN2[i][j] = 0;
				}
				else
				{	
					int somme = TERRAIN[i][j];
					if (i != 0)
					{
						somme += TERRAIN[i-1][j];
					}
					if (j != 0)
					{
						somme += TERRAIN[i][j-1];
					}
					if (i != TERRAIN[0].length - 1)
					{
						somme += TERRAIN[i+1][j];
					}
					if (j != TERRAIN.length - 1)
					{
						somme += TERRAIN[i][j+1];
					}
					TERRAIN2[i][j] = somme%2147483647;
				}
			}
		}
		return TERRAIN2;
	}
	*/
	private int[][] createDoubleWarningMap (int[][] TERRAIN)
	{
		int[][] TERRAIN2 = new int[TERRAIN[0].length][TERRAIN.length];
		for (int i = 0; i < TERRAIN[0].length ; i++)
		{
			for (int j = 0; j < TERRAIN.length ; j++)
			{
				if (!(map[i][j] == FLOOR || map[i][j] == CHICK || map[i][j] == REDCHICK))			
				{
					TERRAIN2[i][j] = 0;
				}
				else
				{	
					int somme = TERRAIN[i][j];
					if (i != 0 && map[i-1][j] == FLOOR || map[i-1][j] == CHICK || map[i-1][j] == REDCHICK)
					{
						somme += TERRAIN[i-1][j];
					}
					else 
					{
						somme += TERRAIN[i][j];
					}
					if (j != 0 && map[i][j-1] == FLOOR || map[i][j-1] == CHICK || map[i][j-1] == REDCHICK)
					{
						somme += TERRAIN[i][j-1];
					}
					else 
					{
						somme += TERRAIN[i][j];
					}
					if (i != TERRAIN[0].length - 1 && map[i+1][j] == FLOOR || map[i+1][j] == CHICK || map[i+1][j] == REDCHICK)
					{
						somme += TERRAIN[i+1][j];
					}
					else 
					{
						somme += TERRAIN[i][j];
					}
					if (j != TERRAIN.length - 1 && map[i][j+1] == FLOOR || map[i][j+1] == CHICK || map[i][j+1] == REDCHICK)
					{
						somme += TERRAIN[i][j+1];
					}
					else 
					{
						somme += TERRAIN[i][j];
					}
					TERRAIN2[i][j] = somme%2147483647;
				}
			}
		}
		return TERRAIN2;
	}
	private static int[][] createWarningMap(int Y, int X, int direction , int[][] warningMap, int iterator)
	{
		if (Y == map[0].length || X == map.length || Y<0 || X<0)
		{
		}
		else if (iterator != 0 && (map[Y][X] == WALL || map[Y][X] == HEAD_UP || map[Y][X] == HEAD_LEFT || map[Y][X] == HEAD_RIGHT || map[Y][X] == HEAD_DOWN || map[Y][X] == CORPS || map[Y][X] == TAIL))
		{	
		}
		else if (iterator == 0)
		{
			switch(direction)
			{
				case DOWN :  warningMap = createWarningMap(Y + 1, X,DOWN,warningMap, iterator + 1);
	   		   			     warningMap = createWarningMap(Y, X + 1,RIGHT,warningMap, iterator + 1);
	   		   			     warningMap = createWarningMap(Y, X - 1,LEFT,warningMap, iterator + 1);
	   		   			     break;
				case RIGHT : warningMap = createWarningMap(Y + 1, X,DOWN,warningMap, iterator + 1);
	   		   			     warningMap = createWarningMap(Y, X + 1,RIGHT,warningMap, iterator + 1);
	   		   			     warningMap = createWarningMap(Y - 1, X,UP,warningMap, iterator + 1);
	   		   			     break;
				case UP :    warningMap = createWarningMap(Y, X + 1,RIGHT,warningMap, iterator + 1);
						     warningMap = createWarningMap(Y - 1, X,UP,warningMap, iterator + 1);
						     warningMap = createWarningMap(Y, X - 1,LEFT,warningMap, iterator + 1);
				             break;
				case LEFT :  warningMap = createWarningMap(Y + 1, X,DOWN,warningMap, iterator + 1);
				   		     warningMap = createWarningMap(Y - 1, X,UP,warningMap, iterator + 1);
				   		     warningMap = createWarningMap(Y, X - 1,LEFT,warningMap, iterator + 1);
				             break;
			}
		}
		else
		{
			if (warningMap[Y][X] > iterator && iterator < 200)
			{
				warningMap[Y][X] = iterator;
				switch(direction)
				{
					case DOWN :  warningMap = createWarningMap(Y + 1, X,DOWN,warningMap, iterator + 1);
		   		   			     warningMap = createWarningMap(Y, X + 1,RIGHT,warningMap, iterator + 1);
		   		   			     warningMap = createWarningMap(Y, X - 1,LEFT,warningMap, iterator + 1);
		   		   			     break;
					case RIGHT : warningMap = createWarningMap(Y + 1, X,DOWN,warningMap, iterator + 1);
		   		   			     warningMap = createWarningMap(Y, X + 1,RIGHT,warningMap, iterator + 1);
		   		   			     warningMap = createWarningMap(Y - 1, X,UP,warningMap, iterator + 1);
		   		   			     break;
					case UP :    warningMap = createWarningMap(Y, X + 1,RIGHT,warningMap, iterator + 1);
							     warningMap = createWarningMap(Y - 1, X,UP,warningMap, iterator + 1);
							     warningMap = createWarningMap(Y, X - 1,LEFT,warningMap, iterator + 1);
					             break;
					case LEFT :  warningMap = createWarningMap(Y + 1, X,DOWN,warningMap, iterator + 1);
					   		     warningMap = createWarningMap(Y - 1, X,UP,warningMap, iterator + 1);
					   		     warningMap = createWarningMap(Y, X - 1,LEFT,warningMap, iterator + 1);
					break;
				}
			}
		}
		return warningMap;
	}
	/**
     * 
     * @pre: 
     * @post:
     */
	private static int[][] createWarningMap1(int Y, int X, int direction , int[][] warningMap, int iterator)
	{
		if (Y == map[0].length || X == map.length || Y<0 || X<0)
		{
		}
		else if (iterator != 0 && (map[Y][X] == WALL || map[Y][X] == HEAD_UP || map[Y][X] == HEAD_LEFT || map[Y][X] == HEAD_RIGHT || map[Y][X] == HEAD_DOWN || map[Y][X] == CORPS || map[Y][X] == TAIL))
		{	
		}
		else if (iterator == 0)
		{
			switch(direction)
			{
				case DOWN :  warningMap = createWarningMap(Y + 1, X,DOWN,warningMap, iterator + 1);
	   		   			     warningMap = createWarningMap(Y, X + 1,RIGHT,warningMap, iterator + 1);
	   		   			     warningMap = createWarningMap(Y, X - 1,LEFT,warningMap, iterator + 1);
	   		   			     break;
				case RIGHT : warningMap = createWarningMap(Y + 1, X,DOWN,warningMap, iterator + 1);
	   		   			     warningMap = createWarningMap(Y, X + 1,RIGHT,warningMap, iterator + 1);
	   		   			     warningMap = createWarningMap(Y - 1, X,UP,warningMap, iterator + 1);
	   		   			     break;
				case UP :    warningMap = createWarningMap(Y, X + 1,RIGHT,warningMap, iterator + 1);
						     warningMap = createWarningMap(Y - 1, X,UP,warningMap, iterator + 1);
						     warningMap = createWarningMap(Y, X - 1,LEFT,warningMap, iterator + 1);
				             break;
				case LEFT :  warningMap = createWarningMap(Y + 1, X,DOWN,warningMap, iterator + 1);
				   		     warningMap = createWarningMap(Y - 1, X,UP,warningMap, iterator + 1);
				   		     warningMap = createWarningMap(Y, X - 1,LEFT,warningMap, iterator + 1);
				             break;
			}
		}
		else
		{
			if (warningMap[Y][X] > iterator && iterator < 45)
			{
				warningMap[Y][X] = iterator;
				switch(direction)
				{
					case DOWN :  warningMap = createWarningMap(Y + 1, X,DOWN,warningMap, iterator + 1);
		   		   			     warningMap = createWarningMap(Y, X + 1,RIGHT,warningMap, iterator + 1);
		   		   			     warningMap = createWarningMap(Y, X - 1,LEFT,warningMap, iterator + 1);
		   		   			     break;
					case RIGHT : warningMap = createWarningMap(Y + 1, X,DOWN,warningMap, iterator + 1);
		   		   			     warningMap = createWarningMap(Y, X + 1,RIGHT,warningMap, iterator + 1);
		   		   			     warningMap = createWarningMap(Y - 1, X,UP,warningMap, iterator + 1);
		   		   			     break;
					case UP :    warningMap = createWarningMap(Y, X + 1,RIGHT,warningMap, iterator + 1);
							     warningMap = createWarningMap(Y - 1, X,UP,warningMap, iterator + 1);
							     warningMap = createWarningMap(Y, X - 1,LEFT,warningMap, iterator + 1);
					             break;
					case LEFT :  warningMap = createWarningMap(Y + 1, X,DOWN,warningMap, iterator + 1);
					   		     warningMap = createWarningMap(Y - 1, X,UP,warningMap, iterator + 1);
					   		     warningMap = createWarningMap(Y, X - 1,LEFT,warningMap, iterator + 1);
					break;
				}
			}
		}
		return warningMap;
	}	
	public static void fill(int[][] map, int x, int y, int depth){
	    
		   map[x][y]  = 0;
		   int val;
		   val = ((int)(Math.random()*100))%100;
		   
		   if ((val < 80 - depth*2) && map[x-1][y]   != 0)  fill(map, x-1, y, depth+1);
		   val = ((int)(Math.random()*100))%100;
		   
		   if ((val < 80 - depth*2) && map[x  ][y-1]  != 0)  fill(map, x, y-1, depth+1);
		   val = ((int)(Math.random()*100))%100;
		   
		   if ((val < 80 - depth*2) && map[x+1][y]   != 0)  fill(map, x+1, y, depth+1);
		   val = ((int)(Math.random()*100))%100;
		   
		   if ((val < 80 - depth*2) && map[x  ][y+1] != 0)  fill(map, x, y+1, depth+1);
		   

		}

		public static int[][] generate(){
		   int[][] newMap = new int[20][20];

            for (int i=1;i<19;i++)
            	for (int j=1;j<19;j++)
            		newMap[i][j] = 1;
            
            fill(newMap, 10,10, 0);
            
		 return newMap;
		}
		
    
    public static void goToFrom (int[]start,int[]end , int[][] warningMap)
    {
    	@SuppressWarnings("unused")
		int signeY = (int) end[0]-start[0] / Math.abs(end[0]-start[0]);
    	@SuppressWarnings("unused")
		int signeX = (int) end[1]-start[1] / Math.abs(end[1]-start[1]);
    	
    }
    
	
}
//A chaque tour on crée un nouveau poussin rouge en se basant sur la carte avec dessus sa position courante
//en gros dans la main on devra faire new poussinRouge en lui donnant la carte comme paramètre
//c'est après qu'il décidera ou non de bouger de place et de faire d'autres actions
