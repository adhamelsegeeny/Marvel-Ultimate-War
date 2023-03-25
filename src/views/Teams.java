package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Game;
import model.world.Champion;

public class Teams extends JPanel {

	//private JButton[][] deck = new JButton[3][5];
	private ArrayList<JButton> roster;
	//private JLabel label;

	public Teams() throws Exception {
		super();
		this.setLayout(new GridLayout(3, 5));
		roster = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			//for (int j = 0; j < 5; j++) {
				JButton b = new JButton();
				b.setBackground(Color.black);
				b.setFocusable(false);
//				deck[i][j].setBorder(BorderFactory.createEtchedBorder());
//				deck[i][j].setBorderPainted(false);
				this.add(b);
				roster.add(b);
				Game.loadAbilities("Abilities.csv");
				Game.loadChampions("Champions.csv");//arraylist is full
				ImageIcon icon = Game.getAvailableChampions().get(i).getImg();
				//String s= Game.getAvailableChampions().get(i).getName();
				if (icon != null) {
					Image img = icon.getImage();
					Image newimg = img.getScaledInstance(200, 230, java.awt.Image.SCALE_SMOOTH);
					icon = new ImageIcon(newimg);
					//b.setText(s);
					//b.setFont(new Font("Arial", Font.BOLD, 12));
					
					b.setIcon(icon);
				}

			//}
		}

	}
	
	
//	public void setDeck(Choose[][] deck) {
//		this.deck = deck;
//	}
	

	public ArrayList<JButton> getRoster() {
		return roster;
	}


	public void setRoster(ArrayList<JButton> roster) {
		this.roster = roster;
	}

}
