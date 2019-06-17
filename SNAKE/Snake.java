
/**
 * classe permettant la gestion du serpent. elle permet de conserver l'odre dans 
 * lequel le serpent c'est déplacer.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake
{

    private int[][] snake = new int[2][381];
    private int Head;
    private int Tail;
    private int INIT = 0;
    /**
     * initialise les variable d'instance
     */
    public Snake()
    {
        Tail = 0;
        Head = 0;
    }
    
    /**
     * rajoute un élément corporel au serpent.
     * 
     * @pre: X et Y corresponde au coordoner nouvelle de la tete du serpent.
     * @post: ajoute au tableau un élément au serpent.
     */
    public void add(int X, int Y)
    {
        if (INIT == 0)
        {
            snake[0][Head] = X;
            snake[1][Head] = Y;
            INIT = 1;
        }
        else
        {
            if(Head == 380)
            Head = 0;
            else
            Head++;
            
            snake[0][Head] = X;
            snake[1][Head] = Y;
        }
    }
    /**
     * racourci le serpent d'un élément corporel, sa queu.
     * 
     * @pre: -
     * @post: racourci la queu du serpent.
     */
    public void del()
    {
        if(Tail == 380)
        Tail = 0;
        else
        Tail++;
    }
    /**
     * @pre: -
     * @post: retourne les coordoner X de la queu du serpent
     */
    public int getTailX()
    {
        return snake[0][Tail];
    }
    /**
     * @pre: -
     * @post: retourne les coordoner Y de la queu du serpent
     */
    public int getTailY()
    {
        return snake[1][Tail];
    }
    
}
