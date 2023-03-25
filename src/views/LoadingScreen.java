package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.*;

import engine.Game;
import engine.Player;
import engine.PriorityQueue;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;

@SuppressWarnings("serial")
public class LoadingScreen extends JFrame implements ActionListener {
	private JLabel label;// label that holds background image and buttons
	private ImageIcon img;// background image
	private JButton start;
	private JLabel label2;
	private JButton player1;// button to open textarea
	private JButton player2;
	private JTextField a1;// player1 name
	private JTextField a2;// player2 name
	private JButton next = new JButton();// next to choosing teams
	private Teams teams;// jpanel for choosing teams
	private JPanel panel1;// choosing team1
	private JPanel panel2;// choosing team2
	private JButton submit1;// submit name
	private JButton submit2;// submit name
	private Overview view; // player stats
	private Board board; // game board
	private JPanel northPanel;
	private JPanel southPanel;
	private JPanel eastPanel;
	private JPanel westPanel;
	private JTextField pq;
	private JLabel tips;
	private JButton play;
	private JTextArea tip;
	private JTextArea tip2;
	private JTextField points;
	private JTextArea p1;
	private JTextArea p2;
	private JButton leaderAbility;
	private Choose champ1;
	private Choose champ2;
	private Choose champ3;
	private Choose champ4;
	private Choose champ5;
	private Choose champ6;
	private ArrayList<Choose> b;
	// private JTextArea war;
	private JButton ability1;
	private JButton ability2;
	private JButton ability3;
	private JTextArea attributes;
	private JTextArea effects;
	JScrollPane scroll1;
	JScrollPane scroll2;
	JProgressBar c1 = new JProgressBar();
	JProgressBar c2 = new JProgressBar();
	JProgressBar c3 = new JProgressBar();
	JProgressBar c4 = new JProgressBar();
	JProgressBar c5 = new JProgressBar();
	JProgressBar c6 = new JProgressBar();
	JButton end;

	public LoadingScreen() throws Exception {
		super();
		this.setUndecorated(true);
		this.setLayout(new BorderLayout());
		this.getContentPane().setBackground(Color.black);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // terminate program when click x on window
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // set size of frame to full screen
		this.setTitle("Marvel Ultimate War");
		ImageIcon image = new ImageIcon(this.getClass().getResource("/wallpaper.jpg"));
		this.setIconImage(image.getImage());

		img = new ImageIcon(this.getClass().getResource("/mar.jpg"));
		label = new JLabel(img);
		
//		java.net.URL url = this.getClass().getResource("/res/images/animated.gif");
//		ImageIcon imageIcon = new ImageIcon(url);
//		JLabel labelt = new JLabel(imageIcon);
		
		// label.setBackground(Color.white);
		this.add(label);

		start = new JButton();
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/play.jpg"));
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(180, 60, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		start.setIcon(icon);
		// start.setText("Play");

		start.setBounds(620, 300, 150, 50);
		// start.setBackground(Color.LIGHT_GRAY);
		start.setFocusable(false);
		// start.setBorder(BorderFactory.createEtchedBorder());
		label.add(start);
//		label.add(war);
		player1 = new JButton();
		player1.setText("Enter Your Name as The First Player");
		player1.setFont(new Font("Arial", Font.ITALIC, 12));
		player1.setFocusable(false);
		player1.setBounds(40, 120, 280, 50);
		player1.setBackground(new Color(0x123456));
		player1.setForeground(Color.white);
		player1.addActionListener(this);

		ImageIcon img2 = new ImageIcon(this.getClass().getResource("/civil.jpg"));
		label2 = new JLabel(img2);
		// label2.setBackground(Color.white);

		a1 = new JTextField();

		player2 = new JButton();
		player2.setText("Enter Your Name as The Second Player");
		player2.setFont(new Font("Arial", Font.ITALIC, 12));
		player2.setFocusable(false);
		player2.setBounds(1050, 120, 280, 50);
		player2.setBackground(new Color(0xD22B2B));
		player2.setForeground(Color.white);
		player2.addActionListener(this);

		a2 = new JTextField();

		teams = new Teams();

		submit1 = new JButton();
		submit1.setText("Submit");

		submit2 = new JButton();
		submit2.setText("Submit");

		next.setText("Read some tips");
		next.setFocusable(false);
		next.setBounds(620, 600, 120, 50);
		next.setFont(new Font("Arial", Font.ITALIC, 12));
		next.setBackground(Color.BLACK);
		next.setForeground(Color.white);
		next.addActionListener(this);

		board = new Board();

		northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(1, 3));
		northPanel.setBackground(Color.darkGray);
		northPanel.setPreferredSize(new Dimension(this.getWidth(), 45));

		southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1, 3));
		southPanel.setBackground(Color.darkGray);
		southPanel.setPreferredSize(new Dimension(this.getWidth(), 45));

		eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(4, 1));
		eastPanel.setBackground(Color.black);
		eastPanel.setPreferredSize(new Dimension(200, this.getHeight()));
		p1 = new JTextArea();
		p2 = new JTextArea();
		p1.setOpaque(false);
		p2.setOpaque(false);
		p1.setEditable(false);
		p2.setEditable(false);
		p1.setFont(new Font("Consolas", Font.BOLD, 16));
		p2.setFont(new Font("Consolas", Font.BOLD, 16));
		p1.setForeground(Color.blue);
		p2.setForeground(Color.red);

		westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(4, 1));
		westPanel.setBackground(Color.black);
		westPanel.setPreferredSize(new Dimension(200, this.getHeight()));

		pq = new JTextField();
		pq.setPreferredSize(new Dimension(600, 50));

		pq.setFont(new Font("Consolas", Font.BOLD, 16));
		pq.setForeground(Color.red);
		pq.setBackground(Color.black);
		pq.setCaretColor(Color.white);
		pq.setEditable(false);

		ImageIcon img3 = new ImageIcon(this.getClass().getResource("/cap.jpg"));
		tips = new JLabel(img3);
		// tips.setBackground(Color.white);
		tip = new JTextArea();
		tip.setBackground(new Color(0xD22B2B));
		tip.setFocusable(false);
		tip.setForeground(Color.black);
		tip.setFont(new Font("Arial", Font.ITALIC, 20));
		tip.setBounds(100, 20, 400, 350);
		tip.setOpaque(false);
		tip.setText("Arrows: Move in the desired direction" + '\n' + "W: Attack up" + '\n' + "A: Attack left" + '\n'
				+ "S: Attack down" + '\n' + "D: Attack right" + '\n' + " 1: Ability 1" + '\n' + " 2: Ability 2" + '\n'
				+ " 3: Ability 3" + '\n' + "4: Punch in case of Disarm" + '\n' + "Enter: End turn" + '\n'
				+ "Esc: EndGame");
		tip2 = new JTextArea();
		tip2.setText("To get the Champion's stats:" + '\n' + "Press on their name at the left or right sides");
		tip2.setBackground(new Color(0xD22B2B));
		tip2.setFocusable(false);
		tip.setEditable(false);
		tip2.setForeground(Color.black);
		tip2.setFont(new Font("Arial", Font.ITALIC, 20));
		tip2.setBounds(900, 20, 500, 250);
		tip2.setOpaque(false);

		play = new JButton("Game");
		play.setFocusable(false);
		play.setIcon(icon);
		play.setBounds(1070, 700, 150, 50);

		tips.add(tip);
		tips.add(play);
		tips.add(tip2);

		points = new JTextField();

		points.setFont(new Font("Consolas", Font.BOLD, 20));
		points.setForeground(Color.blue);
		points.setBackground(Color.black);
		points.setCaretColor(Color.white);

		leaderAbility = new JButton("Use Leader Ability");
		leaderAbility.setFont(new Font("Consolas", Font.BOLD, 22));
		leaderAbility.setForeground(Color.red);
		leaderAbility.setBackground(Color.black);
		leaderAbility.setFocusable(false);

		champ1 = new Choose();
		champ2 = new Choose();
		champ3 = new Choose();
		champ4 = new Choose();
		champ5 = new Choose();
		champ6 = new Choose();

		champ1.setFont(new Font("Consolas", Font.PLAIN, 2));
		champ2.setFont(new Font("Consolas", Font.PLAIN, 2));
		champ3.setFont(new Font("Consolas", Font.PLAIN, 2));
		champ4.setFont(new Font("Consolas", Font.PLAIN, 2));
		champ5.setFont(new Font("Consolas", Font.PLAIN, 2));
		champ6.setFont(new Font("Consolas", Font.PLAIN, 2));

		b = new ArrayList<>();
		b.add(champ1);
		b.add(champ2);
		b.add(champ3);
		b.add(champ4);
		b.add(champ5);
		b.add(champ6);
		ability1 = new JButton();
		ability2 = new JButton();
		ability3 = new JButton();

		ability1.setFont(new Font("Consolas", Font.BOLD, 24));
		ability1.setForeground(Color.blue);
		ability1.setBackground(Color.black);
		ability1.setFocusable(false);

		ability2.setFont(new Font("Consolas", Font.BOLD, 24));
		ability2.setForeground(Color.red);
		ability2.setBackground(Color.black);
		ability2.setFocusable(false);

		ability3.setFont(new Font("Consolas", Font.BOLD, 24));
		ability3.setForeground(Color.blue);
		ability3.setBackground(Color.black);
		ability3.setFocusable(false);

		attributes = new JTextArea();
		attributes.setFont(new Font("Consolas", Font.BOLD, 14));
		attributes.setForeground(Color.red);
		attributes.setBackground(Color.black);
		attributes.setEditable(false);
		scroll1 = new JScrollPane(attributes);
		scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

		effects = new JTextArea();
		effects.setFont(new Font("Consolas", Font.BOLD, 16));
		effects.setForeground(Color.red);
		effects.setBackground(Color.black);
		effects.setEditable(false);
		scroll2 = new JScrollPane(effects);
		scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		southPanel.add(ability1);
		southPanel.add(ability2);
		southPanel.add(ability3);

		c1.setStringPainted(true);
		c2.setStringPainted(true);
		c3.setStringPainted(true);
		c4.setStringPainted(true);
		c5.setStringPainted(true);
		c6.setStringPainted(true);

		this.setVisible(true); // make frame visible
		this.validate(); // refresh frame
		this.repaint();

	}

	public JTextArea getAttributes() {
		return attributes;
	}

	public JTextArea getEffects() {
		return effects;
	}

	public JTextArea getTip2() {
		return tip2;
	}

	public JButton getAbility1() {
		return ability1;
	}

	public JButton getAbility2() {
		return ability2;
	}

	public JButton getAbility3() {
		return ability3;
	}

	public ArrayList<Choose> getB() {
		return b;
	}

	public Choose getChamp1() {
		return champ1;
	}

	public Choose getChamp2() {
		return champ2;
	}

	public Choose getChamp3() {
		return champ3;
	}

	public Choose getChamp4() {
		return champ4;
	}

	public Choose getChamp5() {
		return champ5;
	}

	public Choose getChamp6() {
		return champ6;
	}

	public JTextField getPoints() {
		return points;
	}

	public JLabel getTips() {
		return tips;
	}

	public JButton getPlay() {
		return play;
	}

	public JTextArea getTip() {
		return tip;
	}

	public JTextField getPq() {
		return pq;
	}

	public JLabel getLabel2() {
		return label2;
	}

	public Board getBoard() {
		return board;
	}

	public JPanel getNorthPanel() {
		return northPanel;
	}

	public JPanel getSouthPanel() {
		return southPanel;
	}

	public JPanel getEastPanel() {
		return eastPanel;
	}

	public JPanel getWestPanel() {
		return westPanel;
	}

	public JButton getSubmit1() {
		return submit1;
	}

	public void setSubmit1(JButton submit1) {
		this.submit1 = submit1;
	}

	public JButton getSubmit2() {
		return submit2;
	}

	public void setSubmit2(JButton submit2) {
		this.submit2 = submit2;
	}

	public JButton getNext() {
		return next;
	}

	public void setNext(JButton next) {
		this.next = next;
	}

	public void setStart(JButton start) {
		this.start = start;
	}

	public JLabel getLabel() {
		return label;
	}

	public ImageIcon getImg() {
		return img;
	}

	public JButton getStart() {
		return start;
	}

	public Overview getView() {
		return view;
	}

	public void setView(Overview view) {
		this.view = view;
	}

	public JButton getPlayer1() {
		return player1;
	}

	public JButton getPlayer2() {
		return player2;
	}

	public JTextField getA1() {
		return a1;
	}

	public JTextField getA2() {
		return a2;
	}

	public Teams getTeams() {
		return teams;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public void setPanel1(JPanel panel1) {
		this.panel1 = panel1;
	}

	public JPanel getPanel2() {
		return panel2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public JTextArea getP1() {
		return p1;
	}

	public JTextArea getP2() {
		return p2;
	}

	public JButton getLeaderAbility() {
		return leaderAbility;
	}

	public void onSetTeam(Champion c) {
		view = new Overview(c.getImg(), c.toString());
		this.revalidate();
		this.repaint();
	}

	public void setInitial(ArrayList<Champion> team1, ArrayList<Champion> team2, Game game) {
		for (int i = 0; i < team1.size(); i++) {
			Champion c = team1.get(i);
			ImageIcon icon = team1.get(i).getImg();
			Image img = icon.getImage();
			Image newimg = img.getScaledInstance(200, 110, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);
			c.setImg(icon);
			ImageIcon icon1 = team2.get(i).getImg();
			Image img1 = icon1.getImage();
			Image newimg1 = img1.getScaledInstance(200, 110, java.awt.Image.SCALE_SMOOTH);
			icon1 = new ImageIcon(newimg1);
			team2.get(i).setImg(icon1);
		}
//		int k = 1;
//		for (int i = 0; i < 15; i++) {
//			for (int j = 0; j < 25; j++) {
//				if(k==7)
//					break;
//				JButton s = board.getRoster().get(j);
//				if (Game.getAvailableChampions().get(i).getName() == s.getText()) {
//					switch (k) {
//					case 1:
//						s.add(c1);
//						c1.setValue(Game.getAvailableChampions().get(i).getCurrentHP());
//						k++;
//						break;
//					case 2:
//						s.add(c2);
//						c1.setValue(Game.getAvailableChampions().get(i).getCurrentHP());
//						k++;
//						break;
//					case 3:
//						s.add(c3);
//						c1.setValue(Game.getAvailableChampions().get(i).getCurrentHP());
//						k++;
//						break;
//					case 4:
//						s.add(c4);
//						c1.setValue(Game.getAvailableChampions().get(i).getCurrentHP());
//						k++;
//						break;
//					case 5:
//						s.add(c5);
//						c1.setValue(Game.getAvailableChampions().get(i).getCurrentHP());
//						k++;
//						break;
//					case 6:
//						s.add(c6);
//						c1.setValue(Game.getAvailableChampions().get(i).getCurrentHP());
//						k++;
//						break;
//					}
//				}
//			}
//			if(k==7)
//				break;
//		}

	}

	public void place(ArrayList<Champion> team1, ArrayList<Champion> team2, Game game) {
		for(JButton b:board.getRoster()) {
			b.setBorder(null);
		}
		for (int i = 0; i < team1.size(); i++) {
			Champion c = team1.get(i);
			Point loc = c.getLocation();
			ImageIcon icon = c.getImg();
			board.setImg(loc.x, loc.y, icon);

			this.board.getDeck(loc.x, loc.y)
					.setText("Current HP: " + ((Champion) game.getBoard()[loc.x][loc.y]).getCurrentHP());
			this.board.getDeck(loc.x, loc.y).setHorizontalTextPosition(JButton.CENTER);
			this.board.getDeck(loc.x, loc.y).setVerticalTextPosition(JButton.BOTTOM);
			this.board.getDeck(loc.x, loc.y).setForeground(Color.blue);
			this.board.getDeck(loc.x, loc.y).setBorder(BorderFactory.createLineBorder(Color.BLUE));
		}
		for (int i = 0; i < team2.size(); i++) {
			Champion c = team2.get(i);
			Point loc = c.getLocation();
			ImageIcon icon = c.getImg();
			board.setImg(loc.x, loc.y, icon);

			this.board.getDeck(loc.x, loc.y)
					.setText("Current HP: " + ((Champion) game.getBoard()[loc.x][loc.y]).getCurrentHP());
			this.board.getDeck(loc.x, loc.y).setHorizontalTextPosition(JButton.CENTER);
			this.board.getDeck(loc.x, loc.y).setVerticalTextPosition(JButton.BOTTOM);
			this.board.getDeck(loc.x, loc.y).setForeground(Color.red);
			this.board.getDeck(loc.x, loc.y).setBorder(BorderFactory.createLineBorder(Color.red));
		}
		this.add(board);
	}

	public void placeCovers(Object[][] board) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (board[i][j] instanceof Cover) {
					ImageIcon icon = ((Cover) (board[i][j])).getIcon();
					this.board.setImg(i, j, icon);
					this.board.getDeck(i, j).setText("Current HP: " + ((Cover) board[i][j]).getCurrentHP());
					this.board.getDeck(i, j).setHorizontalTextPosition(JButton.CENTER);
					this.board.getDeck(i, j).setVerticalTextPosition(JButton.BOTTOM);
					this.board.getDeck(i, j).setForeground(Color.white);

				}
			}
		}

	}

	public void silverCell(Champion c) {// set background color
		Point loc = c.getLocation();
		board.getDeck(loc.x, loc.y).setBackground(new Color(0xffd700));
	}

	public void refresh(Game game) {
		for (int i = 4; i > -1; i--) {
			for (int j = 0; j < 5; j++) {
				this.board.getDeck(i, j).setIcon(null);
				this.board.getDeck(i, j).setBackground(Color.white);
				this.board.getDeck(i, j).setText(null);
				if (game.getCurrentChampion().getLocation().x == i && game.getCurrentChampion().getLocation().y == j) {
					silverCell(game.getCurrentChampion());
				}
			}
		}

		place(game.getFirstPlayer().getTeam(), game.getSecondPlayer().getTeam(), game);
		this.add(board.layer);
		placeCovers(game.getBoard());
		pq.setText(game.getTurnOrder().toString());
		points.setText("       CurrentActionPoints: " + game.getCurrentChampion().getCurrentActionPoints());

		ability1.setText(game.getCurrentChampion().getAbilities().get(0).getName());
		ability2.setText(game.getCurrentChampion().getAbilities().get(1).getName());
		ability3.setText(game.getCurrentChampion().getAbilities().get(2).getName());

		ability1.setToolTipText(game.getCurrentChampion().getAbilities().get(0).toString1());
		ability2.setToolTipText(game.getCurrentChampion().getAbilities().get(1).toString1());
		ability3.setToolTipText(game.getCurrentChampion().getAbilities().get(2).toString1());

//		attributes.setText('\n' + "  " + game.getCurrentChampion().toString2());
//		effects.setText(" " + game.getCurrentChampion().effects());
//
//		northPanel.add(scroll1);
//		northPanel.add(scroll2);

		p1.setText('\n' + " First Player:" + '\n' + " " + game.getFirstPlayer().getName() + '\n' + " leader: "
				+ game.getFirstPlayer().getLeader().getName());
		p2.setText('\n' + " Second Player:" + '\n' + " " + game.getSecondPlayer().getName() + '\n' + " leader: "
				+ game.getSecondPlayer().getLeader().getName());

		if (game.getCurrentChampion() == game.getFirstPlayer().getLeader()
				|| game.getCurrentChampion() == game.getSecondPlayer().getLeader()) {
			northPanel.add(leaderAbility);
		} else {
			try {
				northPanel.remove(leaderAbility);
			} catch (Exception e) {

			}
		}
		if (game.getCurrentChampion() == game.getFirstPlayer().getLeader() && game.isFirstLeaderAbilityUsed())
			leaderAbility.setEnabled(false);

		else if (game.getCurrentChampion() == game.getSecondPlayer().getLeader() && game.isSecondLeaderAbilityUsed())
			leaderAbility.setEnabled(false);
		else
			leaderAbility.setEnabled(true);

		if (game.checkGameOver() != null) {
			JOptionPane.showMessageDialog(null, game.checkGameOver().getName() + " WINS!!!", "GAMEOVER!!",
					JOptionPane.INFORMATION_MESSAGE);
			// this.dispose();
			System.exit(0);
		}
//		if (game.checkGameOver() != null) {
//			this.removeAll();
//			ImageIcon icon= new ImageIcon("win.png");
//			JLabel win= new JLabel(icon);
//			this.add(win);
//			end= new JButton();
//			end.setBounds(0,0,1500,900);
//			win.add(end);
//			end.setOpaque(false);
//			JTextField wins= new JTextField(game.checkGameOver().getName()+" Wins");
//			win.setFont(new Font("Arial",Font.ITALIC,32));;
//		}
		this.revalidate();
		this.repaint();
	}

}
