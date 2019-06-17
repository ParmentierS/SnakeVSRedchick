import javax.swing.JFrame;

public class SimpleFenetre extends JFrame{
	
	public SimpleFenetre(){
		super();
		
		build();//On initialise notre fen�tre
	}
	
	private void build(){
		setTitle("--- TOP 10 DE SNAKE ---"); //On donne un titre � l'application
		setLocationRelativeTo(null); //On centre la fen�tre sur l'�cran
		setResizable(false); //On interdit la redimensionnement de la fen�tre
	}
}
