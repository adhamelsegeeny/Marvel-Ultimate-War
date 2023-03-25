package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Overview extends JFrame {
	private JLabel label;
	private JButton confirm;
	private JButton leader;
	

	public Overview(ImageIcon image, String s) {
		super();
		this.setLayout(null);
		this.getContentPane().setBackground(Color.black);
		this.setBounds(300, 100, 750, 550);
		this.setResizable(false);
		//this.setBackground(Color.black);
		this.setVisible(true);
		this.setTitle("Champion Stats");
		Image img = image.getImage();
		Image newimg = img.getScaledInstance(400, 500, java.awt.Image.SCALE_SMOOTH);
		image = new ImageIcon(newimg);
		label = new JLabel();
		label.setBackground(Color.black);
		label.setOpaque(true);
		label.setBounds(0, 0, this.getWidth()-250,this.getHeight());
		label.setIcon(image);
		JTextArea text= new JTextArea(s);
		text.setFont(new Font("MV Boli",Font.BOLD,12));
		text.setBackground(Color.black);
		text.setBounds(390,15,190,600);
		text.setForeground(Color.green);
		text.setEditable(false);
		this.add(text);
		confirm = new JButton("Confirm");
		confirm.setFont(new Font("Arial",Font.BOLD,13));
		confirm.setBounds(600, 200, 100, 50);
		confirm.setBackground(Color.green);
		confirm.setForeground(Color.black);
		confirm.setFocusable(false);
		//confirm.setForeground(Color.green);
		//confirm.setBorder(BorderFactory.createEtchedBorder());
		this.add(confirm);
		
		leader= new JButton();
		leader = new JButton("Leader");
		leader.setFont (new Font("Arial",Font.BOLD,13));
		leader.setForeground(Color.black);
		leader.setBounds(600, 300, 100, 50);
		leader.setBackground(Color.green);
		leader.setFocusable(false);
		//leader.setForeground(Color.green);
		//leader.setBorder(BorderFactory.createEtchedBorder());
		this.add(leader);
		this.add(label);
		this.revalidate();
		this.repaint();
	}
//	public Overview (String s) {
//		super();
//		this.setLayout(null);
//		this.getContentPane().setBackground(Color.black);
//		this.setBounds(300, 100, 750, 550);
//		this.setResizable(false);
//		//this.setBackground(Color.black);
//		this.setVisible(true);
//		this.setTitle("Champion Stats");
//		
//		label = new JLabel();
//		label.setBackground(Color.black);
//		label.setOpaque(true);
//		label.setBounds(0, 0, this.getWidth()-250,this.getHeight());
//		label.setIcon(image);
//		JTextArea text= new JTextArea(s);
//		text.setFont(new Font("MV Boli",Font.BOLD,12));
//		text.setBackground(Color.black);
//		text.setBounds(390,15,190,600);
//		text.setForeground(Color.green);
//		text.setEditable(false);
//		this.add(text);
//		confirm = new JButton("Confirm");
//		confirm.setFont(new Font("Arial",Font.BOLD,13));
//		confirm.setBounds(600, 200, 100, 50);
//		confirm.setBackground(Color.green);
//		confirm.setForeground(Color.black);
//		confirm.setFocusable(false);
//		//confirm.setForeground(Color.green);
//		//confirm.setBorder(BorderFactory.createEtchedBorder());
//		this.add(confirm);
//		
//		leader= new JButton();
//		leader = new JButton("Leader");
//		leader.setFont (new Font("Arial",Font.BOLD,13));
//		leader.setForeground(Color.black);
//		leader.setBounds(600, 300, 100, 50);
//		leader.setBackground(Color.green);
//		leader.setFocusable(false);
//		//leader.setForeground(Color.green);
//		//leader.setBorder(BorderFactory.createEtchedBorder());
//		this.add(leader);
//		this.add(label);
//		this.revalidate();
//		this.repaint();
//	}


	public JLabel getLabel() {
		return label;
	}


	public void setLabel(JLabel label) {
		this.label = label;
	}


	public JButton getConfirm() {
		return confirm;
	}


	public void setConfirm(JButton confirm) {
		this.confirm = confirm;
	}


	public JButton getLeader() {
		return leader;
	}


	public void setLeader(JButton leader) {
		this.leader = leader;
	}
	
	
	
	

}
