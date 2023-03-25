package views;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Board extends JPanel {

	private Choose[][] deck = new Choose[5][5];
	private ArrayList<JButton> roster;
	private JLabel label;
	JLayeredPane layer;
	public Board() {
		super();
		this.setOpaque(false);
		layer= new JLayeredPane();
		ImageIcon img4 = new ImageIcon(this.getClass().getResource("/dark.png"));
		JLabel portal= new JLabel(img4);
		portal.setBounds(0,0, 970, 690);
		this.setBounds(200, 45, 966, 678);
		this.setLayout(new GridLayout(5, 5,5,5));
		roster = new ArrayList<>();
		for (int i = 4; i >-1; i--) {
			for (int j = 0; j < 5; j++) {
				deck[i][j] = new Choose();
				deck[i][j].setOpaque(false);
				this.add(deck[i][j]);
				roster.add(deck[i][j]);
			}

		}
		layer.add(portal,0);
		layer.add(this,1);
	}
	
	

	public Choose[][] getDeck() {
		return deck;
	}



	public ArrayList<JButton> getRoster() {
		return roster;
	}



	public void setDeck(Choose[][] deck) {
		this.deck = deck;
	}

	public Choose getDeck(int x, int y) {
		return deck[x][y];
	}
	
	public void setImg(int x, int y, ImageIcon c) {
		deck[x][y].setIcon(c);
		
	}

	
	
}
