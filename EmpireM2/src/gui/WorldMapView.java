package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WorldMapView extends JPanel implements ActionListener {
	JTextField textField;
	String playerName = "";
	String playerCity = "";
	JFrame frame;
	JFrame frame2;
	ImageIcon background;
	ImageIcon background2;
	ImageIcon cairo;
	ImageIcon rome;
	ImageIcon sparta;
	ImageIcon startButton;
	ImageIcon submit;
	JLabel myLabel;
	JLabel myLabel2;
	JLabel player;
	JLabel turncountP;
	JLabel label;
	JLabel goldP;
	JLabel foodP;
	JPanel panel2;

	public WorldMapView() {
		setPreferredSize(new Dimension(1000, 800));
		background2 = new ImageIcon(this.getClass().getResource("/warMap.jpg"));
		myLabel2 = new JLabel(background2);
		myLabel2.setSize(1000, 800);
//		

		add(myLabel2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
