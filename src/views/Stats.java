package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Stats extends JFrame {
	private JLabel label;
	

	public Stats(String s ,String effects, String abilities) {
		super();
		this.setLayout(new GridLayout(1,3));
		this.getContentPane().setBackground(Color.black);
		this.setBounds(300, 40, 750, 700);
		this.setResizable(false);
		// this.setBackground(Color.black);
		this.setVisible(true);
		this.setTitle("Champion Stats");
		//ImageIcon image1= new ImageIcon(this.getClass().getResource("/hulk.png"));
//		Image img = image.getImage();
//		Image newimg = img.getScaledInstance(400, 500, java.awt.Image.SCALE_SMOOTH);
//		image = new ImageIcon(newimg);
		label = new JLabel();
		label.setBackground(Color.black);
		label.setOpaque(true);
		//label.setBounds(0, 0, this.getWidth() - 250, this.getHeight());
		//label.setIcon(image);
		JTextArea text = new JTextArea(s);
		text.setFont(new Font("Arial", Font.BOLD, 16));
		text.setBackground(Color.black);
		text.setBounds(400, 15, 150, 600);
		text.setForeground(Color.green);
		text.setEditable(false);
		JTextArea text1 = new JTextArea(effects);
		text1.setFont(new Font("Arial", Font.BOLD, 16));
		text1.setBackground(Color.black);
		text1.setBounds(600, 15, 150, 600);
		text1.setForeground(Color.green);
		text1.setEditable(false);
		JTextArea text2 = new JTextArea("Abilities" +'\n'+ abilities);
		text2.setFont(new Font("Arial", Font.BOLD, 14));
		text2.setBackground(Color.black);
		text2.setBounds(600, 15, 150, 600);
		text2.setForeground(Color.green);
		text2.setEditable(false);
		this.add(text);
		this.add(text2);
		this.add(text1);
		this.revalidate();
		this.repaint();
	}
	
}
