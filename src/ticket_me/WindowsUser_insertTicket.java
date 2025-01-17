package ticket_me;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;

import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import ticket_me.TicketsJdbcs;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
/**
 * @author MARTIN Thomas
 * Class which permit to created a new ticket.
 */
public class WindowsUser_insertTicket extends JFrame implements ActionListener {
	private JPanel panel_South = new JPanel();
	private JButton Button_Reset = new JButton("Reset");
	private JButton Button_Done = new JButton("Done");
	private JButton Button_Cancel = new JButton("Cancel");
	private JPanel panel_Center = new JPanel();
	private JLabel Label_Titre = new JLabel("Title");
	private JTextField Field_Titre = new JTextField();
	private JLabel Label_Description = new JLabel("Description");
	private TextArea Area_Description = new TextArea();
	private JPanel panel_Box = new JPanel();
	private JLabel Label_Categorie = new JLabel("Category");
	private JComboBox<String> Box_Categorie = new JComboBox<String>();
	private JLabel Label_Priorite = new JLabel("Priority");
	private JButton screenshotB = new JButton("Add Screenshot");
	private JComboBox<String> Box_Priorite = new JComboBox<String>();
	public boolean screenshotVerif = false;
	private ActionListener screenshotListener = new ActionListener() { // implementation du addImage
		
		public void actionPerformed(ActionEvent e) {
			screenshot s = new screenshot();
			s.setVisible(true);
			screenshotVerif = true;
			
		}
	};
	
	private ActionListener resetListener = new ActionListener() { // implementation du reset
	
		public void actionPerformed(ActionEvent e) {
			Field_Titre.setText("");
			Area_Description.setText(" ");
			Area_Description.setText("");
			Box_Categorie.setSelectedIndex(0);
			Box_Priorite.setSelectedIndex(0);

			
		}
	};
	private ActionListener doneListener = new ActionListener() { // validation du ticket

		public void actionPerformed(ActionEvent e) {
			insererT();
			dispose();

		}
	};

	public WindowsUser_insertTicket() {
		getContentPane().add(panel_South, BorderLayout.SOUTH);
		setVisible(true); // page visible
		setTitle("Create a new ticket"); // title
		setSize(800, 400); // size
		setLocationRelativeTo(null); // fenetre centrée
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // arret du processus quand fermeture de la fenetre

		// -- Mise en place du panel south --//
		SwingUtilities.updateComponentTreeUI(this);
		panel_South.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel_South.add(Button_Reset);
		panel_South.add(Button_Done);
		panel_South.add(Button_Cancel);
		Button_Reset.addActionListener(resetListener);
		Button_Cancel.addActionListener(this);
		Button_Done.addActionListener(doneListener);

		// -- Mise en place du panel centrale --//
		getContentPane().add(panel_Center, BorderLayout.CENTER);
		GridBagLayout gbl_panel_Center = new GridBagLayout();
		gbl_panel_Center.columnWidths = new int[] { 0, 299, 0 };
		gbl_panel_Center.rowHeights = new int[] { 0, 131, 0, 0 };
		gbl_panel_Center.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_Center.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_Center.setLayout(gbl_panel_Center);

		// -- Positionnement du label titre --//
		GridBagConstraints gbc_Label_Titre = new GridBagConstraints();
		gbc_Label_Titre.insets = new Insets(15, 15, 5, 10);
		gbc_Label_Titre.gridx = 0;
		gbc_Label_Titre.gridy = 0;
		panel_Center.add(Label_Titre, gbc_Label_Titre);

		// -- Positionnement du field titre --//
		GridBagConstraints gbc_Field_Titre = new GridBagConstraints();
		gbc_Field_Titre.insets = new Insets(15, 0, 5, 15);
		gbc_Field_Titre.fill = GridBagConstraints.HORIZONTAL;
		gbc_Field_Titre.gridx = 1;
		gbc_Field_Titre.gridy = 0;
		panel_Center.add(Field_Titre, gbc_Field_Titre);
		Field_Titre.setColumns(10);

		// -- Positionnement du label description --//
		GridBagConstraints gbc_Label_Description = new GridBagConstraints();
		gbc_Label_Description.anchor = GridBagConstraints.NORTH;
		gbc_Label_Description.insets = new Insets(20, 15, 5, 20);
		gbc_Label_Description.gridx = 0;
		gbc_Label_Description.gridy = 1;
		panel_Center.add(Label_Description, gbc_Label_Description);

		// -- Positionnement de la zone de texte description --//
		GridBagConstraints gbc_Area_Description = new GridBagConstraints();
		gbc_Area_Description.fill = GridBagConstraints.HORIZONTAL;
		gbc_Area_Description.anchor = GridBagConstraints.NORTH;
		gbc_Area_Description.insets = new Insets(20, 0, 5, 15);
		gbc_Area_Description.gridx = 1;
		gbc_Area_Description.gridy = 1;
		panel_Center.add(Area_Description, gbc_Area_Description);

		// -- Positionnement du panel box --//
		GridBagConstraints gbc_panel_Box = new GridBagConstraints();
		gbc_panel_Box.fill = GridBagConstraints.BOTH;
		gbc_panel_Box.gridx = 1;
		gbc_panel_Box.gridy = 2;
		panel_Center.add(panel_Box, gbc_panel_Box);

		// -- Set layout du panel box --//
		GridBagLayout gbl_panel_Box = new GridBagLayout();
		gbl_panel_Box.columnWidths = new int[] { 0, 100, 0, 0, 0, 100, 0, 0 };
		gbl_panel_Box.rowHeights = new int[] { 0, 0 };
		gbl_panel_Box.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_Box.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_Box.setLayout(gbl_panel_Box);

		// -- Positionnement du label categorie--//
		GridBagConstraints gbc_Label_Categorie = new GridBagConstraints();
		gbc_Label_Categorie.insets = new Insets(0, 0, 0, 5);
		gbc_Label_Categorie.gridx = 0;
		gbc_Label_Categorie.gridy = 0;
		panel_Box.add(Label_Categorie, gbc_Label_Categorie);

		// -- Positionnement de la box categorie --//
		GridBagConstraints gbc_Box_Categorie = new GridBagConstraints();
		gbc_Box_Categorie.insets = new Insets(15, 10, 15, 10);
		gbc_Box_Categorie.fill = GridBagConstraints.HORIZONTAL;
		gbc_Box_Categorie.gridx = 1;
		gbc_Box_Categorie.gridy = 0;
		Box_Categorie.addItem("Display");
		Box_Categorie.addItem("Sound");
		Box_Categorie.addItem("pc");
		Box_Categorie.addItem("printer");
		Box_Categorie.addItem("software");
		Box_Categorie.addItem("wireless network");
		Box_Categorie.addItem("password");
		Box_Categorie.addItem("youtube");
		Box_Categorie.addItem("whatsapp");
		Box_Categorie.addItem("microsoft");
		Box_Categorie.addItem("gmail");
		Box_Categorie.addItem("internet");
		panel_Box.add(Box_Categorie, gbc_Box_Categorie);

		// -- Positionnement du label priorité --//
		GridBagConstraints gbc_Label_Priorite = new GridBagConstraints();
		gbc_Label_Priorite.insets = new Insets(0, 0, 0, 5);
		gbc_Label_Priorite.anchor = GridBagConstraints.EAST;
		gbc_Label_Priorite.gridx = 4;
		gbc_Label_Priorite.gridy = 0;
		panel_Box.add(Label_Priorite, gbc_Label_Priorite);

		// -- Positionnement de la box priorité --//
		GridBagConstraints gbc_Box_Priorite = new GridBagConstraints();
		gbc_Box_Priorite.insets = new Insets(0, 10, 0, 10);
		gbc_Box_Priorite.fill = GridBagConstraints.HORIZONTAL;
		gbc_Box_Priorite.gridx = 5;
		gbc_Box_Priorite.gridy = 0;
		Box_Priorite.addItem("High");
		Box_Priorite.addItem("Medium");
		Box_Priorite.addItem("Low");
		panel_Box.add(Box_Priorite, gbc_Box_Priorite);
		
		GridBagConstraints gbc_screenshot = new GridBagConstraints();
		gbc_Label_Categorie.insets = new Insets(0, 0, 0, 5);
		gbc_Label_Categorie.gridx = 0;
		gbc_Label_Categorie.gridy = 0;
		panel_Box.add(screenshotB, gbc_screenshot);
		screenshotB.addActionListener(screenshotListener);
	}

	public void actionPerformed(ActionEvent e) { // annulation création ticket
		this.dispose();
	}
	/**
	 * Insert the new ticket on the database.
	 */
private void insererT() {
		TicketsJdbcs d = new TicketsJdbcs();
		String titre =Field_Titre.getText().toString();
		String description = Area_Description.getText().toString();
		String screens = "";
		String categorie = Box_Categorie.getSelectedItem().toString();
		String priorite = Box_Priorite.getSelectedItem().toString();
		String isCreatedBy = Windows_Home.username.getText();
		if(screenshotVerif == true) {
			if(null == titre || titre.equals("")) {
				JOptionPane.showMessageDialog(null, "Sorry,titre can't be empty!");
		}else {
			screens = screenshot.txtPath.getText();
			d.insert(titre, description,screens,categorie,priorite,isCreatedBy);
			
		       }
     	}
		
		if(screenshotVerif == false){
			if(null == titre || titre.equals("")) {
				JOptionPane.showMessageDialog(null, "Sorry,titre can't be empty!");
			}else {
				d.insert(titre, description,categorie,priorite,isCreatedBy);
			
			}
		}
	
		DefaultTableModel tableModel = (DefaultTableModel) Window_Main_User.tableau.getModel();
		Vector<String> t = new Vector<String>();
		t.add("id");
		t.add(titre);
		t.add(description);
		t.add("1");
		tableModel.addRow(t);
	}
}	


