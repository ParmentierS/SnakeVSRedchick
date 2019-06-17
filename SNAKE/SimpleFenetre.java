import javax.swing.JFrame;

public class SimpleFenetre extends JFrame{
	
	public SimpleFenetre(){
		super();
		
		build();//On initialise notre fenêtre
	}
	
	private void build(){
		setTitle("--- TOP 10 DE SNAKE ---"); //On donne un titre à l'application
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		setResizable(false); //On interdit la redimensionnement de la fenêtre
	}
}
