import java.awt.Color;
import java.awt.Graphics;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * @author Liza BOUMALI
 * @version 1.2
 * Cette classe g�re les formes � afficher dans la fenetre cr�e dans Frame.
 * @see Frame
 * 
 */

public class Panel extends JPanel {
	/**
	 * Les deux listes vont servir � stocker les valeurs des angles correspondant � chaque type de completion_code.
	 * @see Stats_Tickets
	 * et la deuxi�me servira � stocker les couleurs reference de chaque type de completion_code.
	 * @see Stats_Tickets.paint
	 */
public ArrayList<Double> list= new ArrayList<>();
public ArrayList<Color> list_colors= new ArrayList<>();
/**
 * @author Liza BOUMALI
 * @see Stats_Tickets
 * @param g qui represente un graphique 2D
 * paintComponent parcours les deux listes cr�es precedemment et dessine le graphe sous phorme de pieChart en 2 dimensions pour les statistiques des tickets dans la base de donn�es.
 */
	public void paintComponent(Graphics g ) {
		
		Stats_Tickets.paint(g);
	
		list_colors.add(Color.cyan);
		list_colors.add(Color.green);
		list_colors.add(Color.magenta);
		list_colors.add(Color.red);
		list_colors.add(Color.pink);
		
		double current = 0;
		
		for(int i = 0 ; i<list.size(); i++) {
			System.out.println("Couleur : "+list_colors.get(i)+", mot : "+list.get(i));
			g.setColor(list_colors.get(i));
			double dd = list.get(i);
			int d = (int)dd;
			g.fillArc(200, 80, 300, 300, (int) current,d);
			System.out.println(current);
			current += dd;
		}
		
	}
	

}
