 /**
 * 
 * @author :
 * @version : 1.0
 */

import java.io.*;
public class lectureFichier 
{
	public static final int LONGUEURTOP = 10;
	public static int[] scores = new int[LONGUEURTOP];
	public static String[] noms = new String [LONGUEURTOP];
	
	/**
     * 
     * @pre: 
     * @post:
     */
	public static void createtop(int longueurtop)
	{
		try 
		{
			BufferedWriter createtop = new BufferedWriter(new FileWriter("top" + longueurtop + ".txt"));
			for(int j=1; j <= longueurtop; j++)
			{
					createtop.write("PLAYER" + j + " 0");
					createtop.newLine();
			}
			createtop.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Le fichier texte top" + longueurtop + " manque a l'appel");
		} 
		catch (IOException e) 
		{
			System.out.println("Le fichier texte top" + longueurtop + " ne fonctionne pas correctement");
		}
	}
	
	/**
     * 
     * @pre: 
     * @post:
     */
	public static void readScore()
	{
		try
		{
			BufferedReader lecturetop = new BufferedReader(new FileReader("top" + LONGUEURTOP + ".txt"));
			for(int i = 0; i < LONGUEURTOP; i++)
			{
				String line = lecturetop.readLine();
				String [] ELEMENT = line.split (" ");
				scores[i] = Integer.parseInt(ELEMENT[ELEMENT.length-1]);
	            noms[i] = ELEMENT[0];
			}
			lecturetop.close();
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("Le fichier texte top"+ LONGUEURTOP + " manque a l'appel");
		} 
		catch (IOException e) 
		{
			System.out.println("Le fichier texte top" + LONGUEURTOP + " ne fonctionne pas correctement");
		}
	}
	public static void writeScore(String nom, int score)
	{
		try 
		{
			BufferedWriter ecrituretop = new BufferedWriter(new FileWriter("top" + LONGUEURTOP + ".txt"));
			int i;
			for(i=0; i!= LONGUEURTOP && score<scores[i]; i++)
			{
			}
			for(int j=0; j<i; j++)
			{
					ecrituretop.write(noms[j] + " " + scores[j]);
					ecrituretop.newLine();
			}
			if (i==LONGUEURTOP)
			{
				System.out.println("Vous n'avez pas le niveau pour etre dans le top , Desole!");
			}
			else
			{
				ecrituretop.write(nom + " " + score);
				ecrituretop.newLine();
				for(int j=i; j < LONGUEURTOP - 1; j++)
				{
					ecrituretop.write(noms[j] + " " + scores[j]);
					ecrituretop.newLine();
				}
				
			}
			ecrituretop.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Le fichier texte top" + LONGUEURTOP + " manque a l'appel");
		} 
		catch (IOException e) 
		{
			System.out.println("Le fichier texte top" + LONGUEURTOP + " ne fonctionne pas correctement");
		}
	}
}
