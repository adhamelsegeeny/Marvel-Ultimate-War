package views;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.util.ArrayList;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import javax.swing.JTextField;
import javax.swing.JRadioButton;

import engine.Game;
import engine.Player;
import engine.PriorityQueue;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.world.Champion;
import model.world.Direction;

public class GUI implements ActionListener, KeyListener {
	private Game game;
	private LoadingScreen ls;
	private Player p1;
	private Player p2;
	private int count = 0;
	private Overview overview;
	private Champion champion = null;
	private CurrentFrame current = CurrentFrame.LOADING_SCRREN;
	private JRadioButton up;
	private JRadioButton down;
	private JRadioButton right;
	private JRadioButton left;
	private ButtonGroup group;
	private Ability ability;
	private JFrame radio;
	private static Clip clip;
	private Stats stats;

	public GUI() throws Exception {
		ls = new LoadingScreen();
		ls.addKeyListener(this);
		ls.getStart().addActionListener(this);
		ls.getPlayer1().addActionListener(this);
		ls.getPlayer2().addActionListener(this);
		ls.getSubmit1().addActionListener(this);
		ls.getSubmit2().addActionListener(this);
		ls.getNext().addActionListener(this);
		ls.getPlay().addActionListener(this);
		for (JButton b : ls.getTeams().getRoster()) {
			b.addActionListener(this);
		}
		for (JButton b : ls.getBoard().getRoster()) {
			b.addActionListener(this);
		}
		up = new JRadioButton("Up");
		down = new JRadioButton("Down");
		right = new JRadioButton("Right");
		left = new JRadioButton("Left");
		group = new ButtonGroup();
		group.add(down);
		group.add(left);
		group.add(right);
		group.add(up);
		up.addActionListener(this);
		right.addActionListener(this);
		down.addActionListener(this);
		left.addActionListener(this);
		ls.getLeaderAbility().addActionListener(this);
		for (Choose b : ls.getB()) {
			b.addActionListener(this);
		}
		ls.getAbility1().addActionListener(this);
		ls.getAbility2().addActionListener(this);
		ls.getAbility3().addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ls.getStart()) {
			ls.getLabel().remove(ls.getStart());
			ls.getLabel2().add(ls.getPlayer1());
			ls.getLabel2().add(ls.getPlayer2());
			current = CurrentFrame.NAMES;
			ls.remove(ls.getLabel());
			ls.add(ls.getLabel2());
			ls.revalidate();
			ls.repaint();
		}

		else if (e.getSource() == ls.getPlayer1()) {
			// a1 = new JTextArea();
			ls.getA1().setBounds(50, 200, 150, 55);
			ls.getA1().setFont(new Font("Consolas", Font.PLAIN, 30));
			// ls.getA1().setForeground(new Color(0x00FF00));
			ls.getA1().setForeground(Color.blue);
			ls.getA1().setBackground(Color.black);
			ls.getA1().setCaretColor(Color.white);
			ls.getSubmit1().setBounds(220, 205, 100, 25);
			ls.getSubmit1().setBackground(Color.LIGHT_GRAY);
			ls.getSubmit1().setFocusable(false);
			ls.getLabel2().add(ls.getA1());
			ls.getLabel2().add(ls.getSubmit1());
			ls.revalidate();
			ls.repaint();

		} else if (e.getSource() == ls.getPlayer2()) {
			// ls.getA2() = new JTextArea();
			ls.getA2().setBounds(1050, 200, 150, 55);
			ls.getA2().setFont(new Font("Consolas", Font.PLAIN, 30));
			ls.getA2().setForeground(Color.red);
			ls.getA2().setBackground(Color.black);
			ls.getA2().setCaretColor(Color.white);

			ls.getSubmit2().setBounds(1220, 205, 100, 25);
			ls.getSubmit2().setBackground(Color.LIGHT_GRAY);
			ls.getSubmit2().setFocusable(false);
			ls.getLabel2().add(ls.getA2());
			ls.getLabel2().add(ls.getSubmit2());
			ls.revalidate();
			ls.repaint();

		}

		else if (e.getSource() == ls.getSubmit1()) {
			if (!(ls.getA1().getText().isEmpty())) {
				p1 = new Player(ls.getA1().getText());
				ls.getSubmit1().setEnabled(false);
				ls.getA1().setEditable(false);
				ls.revalidate();
				ls.repaint();
			} else
				JOptionPane.showMessageDialog(null, "Players should enter and submit their names", "Error",
						JOptionPane.ERROR_MESSAGE);
		}

		else if (e.getSource() == ls.getSubmit2()) {
			if (!(ls.getA1().getText().isEmpty()) && !(ls.getA2().getText().isEmpty())) {
				p2 = new Player(ls.getA2().getText());
				ls.getSubmit2().setEnabled(false);
				ls.getA2().setEditable(false);
				ls.getLabel2().add(ls.getNext());
				ls.revalidate();
				ls.repaint();
			} else
				JOptionPane.showMessageDialog(null, "Players should enter and submit their names", "Error",
						JOptionPane.ERROR_MESSAGE);
		}

		else if (e.getSource() == ls.getNext()) {
			ls.remove(ls.getLabel2());
			ls.add(ls.getTips());
		}

		else if (e.getSource() == ls.getPlay()) {
			ls.remove(ls.getTips());
			ls.add(ls.getTeams());

			JButton b1 = new JButton();
			b1.setText(p1.getName());
			b1.setBackground(new Color(0x123456));
			b1.setFont(new Font("Arial", Font.BOLD, 20));
			b1.setFocusable(false);
			b1.setForeground(Color.white);

			JButton b2 = new JButton();
			b2.setText(p2.getName());
			// b2.setSize(100, 70);
			b2.setBackground(Color.red);
			b2.setForeground(Color.white);
			b2.setFocusable(false);
			b2.setFont(new Font("Arial", Font.BOLD, 20));

//			ls.add(ls.getPanel1(), BorderLayout.WEST);
//			ls.add(ls.getPanel2(), BorderLayout.EAST);
			// current = CurrentFrame.CHOOSE;
			ls.revalidate();
			ls.repaint();
			JOptionPane.showMessageDialog(null, p1.getName() + " Choose your 3 Champions", "Teams",
					JOptionPane.INFORMATION_MESSAGE);
			return;

		}

		else if (ls.getTeams().getRoster().contains(e.getSource())) {

			int i = ls.getTeams().getRoster().indexOf(e.getSource());
			// JButton b = (JButton) (e.getSource());
			Champion c = Game.getAvailableChampions().get(i);
			champion = c;
			// this.overview=new Overview(c.getImg(),c.toString());
			ls.onSetTeam(c);
			overview = ls.getView();
			overview.getConfirm().addActionListener(this);
			overview.getLeader().addActionListener(this);

			return;
		}
		// try {

		else if (e.getSource() == overview.getConfirm() && count < 3) {
			p1.getTeam().add(champion);
			int i = Game.getAvailableChampions().indexOf(champion);
			ls.getTeams().getRoster().get(i).setEnabled(false);
			ls.getTeams().getRoster().get(i).setBackground(new Color(0x123456));
			overview.dispose();
			ls.revalidate();
			ls.repaint();
			count++;
			if (count == 3 && p1.getLeader() == null) {
				p1.setLeader(champion);
				ls.getTeams().getRoster().get(i).setBackground(new Color(0xffd700));
			}
			if (count == 3)
				JOptionPane.showMessageDialog(null, p2.getName() + " Choose your 3 Champions", "Teams",
						JOptionPane.INFORMATION_MESSAGE);
			return;

		} else if (e.getSource() == overview.getConfirm() && count >= 3) {
			p2.getTeam().add(champion);
			int i = Game.getAvailableChampions().indexOf(champion);
			ls.getTeams().getRoster().get(i).setEnabled(false);
			if (p2.getLeader() == null && count == 5) {
				p2.setLeader(champion);
				ls.getTeams().getRoster().get(i).setBackground(new Color(0xffd700));
			} else
				ls.getTeams().getRoster().get(i).setBackground(Color.red);
			count++;
			overview.dispose();
			if (count == 6) {
				for (JButton b : ls.getTeams().getRoster()) {
					b.setEnabled(false);
				}
				current = CurrentFrame.GAME;

				while (JOptionPane.showConfirmDialog(null, "Start the game?", "Marvel Ultimate War",
						JOptionPane.YES_NO_OPTION) == 1) {
				}
				addBoard();

				ls.revalidate();
				ls.repaint();
			}
			return;
			/*
			 * if(p2.getTeam().size()==3) { new JFrame; }
			 */
		} else if (e.getSource() == overview.getLeader() && count < 3) {
			p1.getTeam().add(champion);
			p1.setLeader(champion);
			int i = Game.getAvailableChampions().indexOf(champion);
			ls.getTeams().getRoster().get(i).setEnabled(false);
			ls.getTeams().getRoster().get(i).setBackground(new Color(0xffd700));
			ls.revalidate();
			ls.repaint();
			overview.dispose();
			count++;
			if (count == 3)
				JOptionPane.showMessageDialog(null, p2.getName() + " Choose your 3 Champions", "Teams",
						JOptionPane.INFORMATION_MESSAGE);
			return;

		}

		else if (e.getSource() == overview.getLeader() && count >= 3) {
			p2.getTeam().add(champion);
			p2.setLeader(champion);
			int i = Game.getAvailableChampions().indexOf(champion);
			ls.getTeams().getRoster().get(i).setEnabled(false);
			ls.getTeams().getRoster().get(i).setBackground(new Color(0xffd700));
			count++;
			overview.dispose();
			if (count == 6) {
				for (JButton b : ls.getTeams().getRoster()) {
					b.setEnabled(false);
				}
				current = CurrentFrame.GAME;
				while (JOptionPane.showConfirmDialog(null, "Start the game?", "Marvel Ultimate War",
						JOptionPane.YES_NO_OPTION) == 1) {
				}
				addBoard();

			}
			return;

		} else if (e.getSource() == up) {
			try {
				game.castAbility(ability, Direction.UP);
			} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			radio.dispose();
			ls.refresh(game);
			ability = null;

		} else if (e.getSource() == right) {
			try {
				game.castAbility(ability, Direction.RIGHT);
			} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			radio.dispose();
			ls.refresh(game);
			ability = null;

		} else if (e.getSource() == down) {
			try {
				game.castAbility(ability, Direction.DOWN);
			} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			radio.dispose();
			ls.refresh(game);
			ability = null;
		} else if (e.getSource() == left) {
			try {
				game.castAbility(ability, Direction.LEFT);
			} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			radio.dispose();
			ls.refresh(game);
			ability = null;
		}

		else if (ls.getBoard().getRoster().contains(e.getSource()) && current == CurrentFrame.CHOOSE) {
			boolean flag = false;
			for (int i = 4; i > -1 && flag == false; i--) {
				for (int j = 0; j < 5; j++) {
					if (e.getSource() == ls.getBoard().getDeck(i, j)) {
						if (JOptionPane.showConfirmDialog(null, "Cast SingleTarget Ability", null,
								JOptionPane.OK_OPTION) == 0) {
							try {
								game.castAbility(ability, i, j);
							} catch (AbilityUseException | NotEnoughResourcesException | InvalidTargetException
									| CloneNotSupportedException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
										JOptionPane.ERROR_MESSAGE);
							}
							ls.refresh(game);
							ability = null;
							flag = true;
							break;
						} else {
							flag = true;
							break;
						}
					}
				}
			}
			current = CurrentFrame.GAME;
		}

		else if (e.getSource() == ls.getLeaderAbility()) {
			try {
				if (JOptionPane.showConfirmDialog(null, "Use Leader Ability", "Leader", JOptionPane.OK_OPTION) == 0) {
					game.useLeaderAbility();
					ls.refresh(game);
					// ls.getLeaderAbility().setEnabled(false);
				}
			} catch (LeaderAbilityAlreadyUsedException | LeaderNotCurrentException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			return;
		}

		else if (ls.getB().contains(e.getSource())) {

			int i = ls.getB().indexOf(e.getSource());

			// JButton b = (JButton) (e.getSource());
			boolean flag = false;
			if (i < 3) {
				for (Champion c : p1.getTeam()) {
					if (ls.getB().get(i).getText() == c.getName()) {
						stats = new Stats(c.attributes(), c.effects(), c.abilityString());
						flag = true;
					}
				}
				ls.getB().get(i).setEnabled(flag);

			}

			else {
				flag = false;
				for (Champion c : p2.getTeam()) {
					if (ls.getB().get(i).getText() == c.getName()) {
						stats = new Stats(c.attributes(), c.effects(), c.abilityString());
						flag = true;
					}
				}
				ls.getB().get(i).setEnabled(flag);

			}
		}

		else if (e.getSource() == ls.getAbility1()) {
			if (current == CurrentFrame.GAME)
				whichAbility(game.getCurrentChampion().getAbilities().get(0));
		}

		else if (e.getSource() == ls.getAbility2()) {
			if (current == CurrentFrame.GAME)
				whichAbility(game.getCurrentChampion().getAbilities().get(1));
		} else if (e.getSource() == ls.getAbility3()) {
			if (current == CurrentFrame.GAME)
				whichAbility(game.getCurrentChampion().getAbilities().get(2));
		}

		else if (e.getSource() == ls.getAbility2()) {
			if (current == CurrentFrame.GAME) {
				if (game.getCurrentChampion().getAbilities().get(3) != null)
					whichAbility(game.getCurrentChampion().getAbilities().get(3));
			}
		} 
//		else if (e.getSource() == ls.end) {
//			JOptionPane.showMessageDialog(null, game.checkGameOver().getName() + " WINS!!!", "GAMEOVER!!",
//					JOptionPane.INFORMATION_MESSAGE);
//			ls.dispose();
//			System.exit(0);
//		}

		ls.revalidate();
		ls.repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {

		switch (e.getKeyChar()) {

		case 'a':
			if (current == CurrentFrame.GAME) {
				try {
					game.attack(Direction.LEFT);
				} catch (ChampionDisarmedException | NotEnoughResourcesException | AbilityUseException
						| InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				ls.refresh(game);
			}
			break;

		case 'w':
			if (current == CurrentFrame.GAME) {
				try {
					game.attack(Direction.UP);
				} catch (ChampionDisarmedException | NotEnoughResourcesException | AbilityUseException
						| InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				ls.refresh(game);
			}
			break;

		case 's':
			if (current == CurrentFrame.GAME) {
				try {
					game.attack(Direction.DOWN);
				} catch (ChampionDisarmedException | NotEnoughResourcesException | AbilityUseException
						| InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				ls.refresh(game);
			}
			break;

		case 'd':
			if (current == CurrentFrame.GAME) {
				try {
					game.attack(Direction.RIGHT);
				} catch (ChampionDisarmedException | NotEnoughResourcesException | AbilityUseException
						| InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				ls.refresh(game);
			}
			break;
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {

		case 10:
			if (current == CurrentFrame.LOADING_SCRREN) {
				ls.getLabel().remove(ls.getStart());
				ls.getLabel2().add(ls.getPlayer1());
				ls.getLabel2().add(ls.getPlayer2());
				current = CurrentFrame.NAMES;
				ls.remove(ls.getLabel());
				ls.add(ls.getLabel2());
				ls.revalidate();
				ls.repaint();
			} else if (current == CurrentFrame.GAME) {

				if (JOptionPane.showConfirmDialog(null, "End Your Turn?", "End Turn", JOptionPane.YES_NO_OPTION) == 0)
					game.endTurn();
				ls.refresh(game);
				break;

			}
			break;
		case 37:
			if (current == CurrentFrame.GAME) {
				try {
					game.move(Direction.LEFT);
				} catch (UnallowedMovementException | NotEnoughResourcesException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				ls.refresh(game);
			}
			break;

		case 38:
			if (current == CurrentFrame.GAME) {
				try {
					game.move(Direction.UP);
				} catch (UnallowedMovementException | NotEnoughResourcesException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				ls.refresh(game);
			}
			break;

		case 39:
			if (current == CurrentFrame.GAME) {
				try {
					game.move(Direction.RIGHT);
				} catch (UnallowedMovementException | NotEnoughResourcesException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				ls.refresh(game);
			}
			break;

		case 40:
			if (current == CurrentFrame.GAME) {
				try {
					game.move(Direction.DOWN);
				} catch (UnallowedMovementException | NotEnoughResourcesException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				ls.refresh(game);
			}
			break;

		case 27:

			if (JOptionPane.showConfirmDialog(null, "End Game?", "Close", JOptionPane.YES_NO_OPTION) == 0) {
				ls.dispose();
				clip.close();
				System.exit(0);
			}
			break;

		case 49:
			if (current == CurrentFrame.GAME) {
				whichAbility(game.getCurrentChampion().getAbilities().get(0));
			}
			break;

		case 50:
			if (current == CurrentFrame.GAME) {
				whichAbility(game.getCurrentChampion().getAbilities().get(1));
			}
			break;

		case 51:
			if (current == CurrentFrame.GAME) {
				whichAbility(game.getCurrentChampion().getAbilities().get(2));
			}
			break;

		case 52:
			if (current == CurrentFrame.GAME) {
				if (game.getCurrentChampion().getAbilities().get(3) != null)
					whichAbility(game.getCurrentChampion().getAbilities().get(3));
			}
			break;

		}

		ls.revalidate();
		ls.repaint();

	}

	public void whichAbility(Ability a) {

		if (a.getCastArea() == AreaOfEffect.DIRECTIONAL) {
			ability = a;
			radio = new JFrame();
			radio.setVisible(true);
			radio.setBounds(500, 300, 300, 100);
			radio.setLayout(new FlowLayout());
			radio.setTitle("Directional Ability");
			radio.add(up);
			up.setFocusable(false);
			radio.add(right);
			radio.add(left);
			radio.add(down);

		} else if (a.getCastArea() != AreaOfEffect.SINGLETARGET) {
			if (JOptionPane.showConfirmDialog(null, "Cast " + a.getCastArea() + " Ability", null,
					JOptionPane.OK_OPTION) == 0) {
				try {
					game.castAbility(a);
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				ls.refresh(game);
			}
		} else {
			ability = a;
			JOptionPane.showMessageDialog(null, "Choose a cell", "Cast ability", JOptionPane.INFORMATION_MESSAGE);
			current = CurrentFrame.CHOOSE;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public void addBoard() {
		ls.remove(ls.getTeams());
		game = new Game(p1, p2);
		ls.silverCell(game.getCurrentChampion());
		ls.setInitial(p1.getTeam(), p2.getTeam(), game);
		ls.place(p1.getTeam(), p2.getTeam(), game);
		ls.placeCovers(game.getBoard());
		ls.add(ls.getBoard().layer);
		ls.add(ls.getNorthPanel(), BorderLayout.NORTH);
		// ls.add(ls.getSouthPanel(),BorderLayout.SOUTH);
		ls.add(ls.getEastPanel(), BorderLayout.EAST);
		ls.add(ls.getWestPanel(), BorderLayout.WEST);
		ls.getPq().setText(game.getTurnOrder().toString());
		ls.getNorthPanel().add(ls.getPq());
		ls.getPoints().setText("       CurrentActionPoints: " + game.getCurrentChampion().getCurrentActionPoints());
		ls.getPoints().setEditable(false);
		ls.getNorthPanel().add(ls.getPoints());
		if (game.getCurrentChampion() == p1.getLeader() || game.getCurrentChampion() == p2.getLeader())
			ls.getNorthPanel().add(ls.getLeaderAbility());

		ls.getAttributes().setText('\n' + "  " + game.getCurrentChampion().toString2());
		ls.getEffects().setText(" " + game.getCurrentChampion().effects());
//		ls.getNorthPanel().add(ls.scroll1);
//		ls.getNorthPanel().add(ls.scroll2);

		ls.getP1().setText('\n' + " First Player:" + '\n' + " " + game.getFirstPlayer().getName() + '\n' + " leader: "
				+ game.getFirstPlayer().getLeader().getName());
		ls.getP2().setText('\n' + " Second Player:" + '\n' + " " + game.getSecondPlayer().getName() + '\n' + " leader: "
				+ game.getSecondPlayer().getLeader().getName());

		ls.getAbility1().setToolTipText(game.getCurrentChampion().getAbilities().get(0).toString1());
		ls.getAbility2().setToolTipText(game.getCurrentChampion().getAbilities().get(1).toString1());
		ls.getAbility3().setToolTipText(game.getCurrentChampion().getAbilities().get(2).toString1());
		ls.getEastPanel().add(ls.getP2());
		ls.getWestPanel().add(ls.getP1());
		ls.getAbility1().setText(game.getCurrentChampion().getAbilities().get(0).getName());
		ls.getAbility2().setText(game.getCurrentChampion().getAbilities().get(1).getName());
		ls.getAbility3().setText(game.getCurrentChampion().getAbilities().get(2).getName());
		ls.add(ls.getSouthPanel(), BorderLayout.SOUTH);
		ls.getChamp1().setText(p1.getTeam().get(0).getName());
		ls.getChamp2().setText(p1.getTeam().get(1).getName());
		ls.getChamp3().setText(p1.getTeam().get(2).getName());
		ls.getChamp4().setText(p2.getTeam().get(0).getName());
		ls.getChamp5().setText(p2.getTeam().get(1).getName());
		ls.getChamp6().setText(p2.getTeam().get(2).getName());

		ls.revalidate();
		ls.repaint();
		for (int k = 0; k < 6; k++) {
			if (k < 3) {
				ImageIcon icon = p1.getTeam().get(k).getLogo();
				Image img = icon.getImage();
				Image newimg = img.getScaledInstance(170, 120, java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(newimg);
				ls.getB().get(k).setBackground(Color.black);
				// ls.getB().get(k).setText( p1.getTeam().get(k).getName());
//				if (p1.getTeam().indexOf(p1.getLeader()) == k)
//					ls.getB().get(k).setBackground(new Color(0xffd700));
				ls.getB().get(k).setIcon(icon);
				ls.getB().get(k).setFocusable(false);
				ls.getB().get(k).setBorderPainted(false);
				ls.getWestPanel().add(ls.getB().get(k));
				ls.revalidate();
				ls.repaint();
			} else {
				ImageIcon icon = p2.getTeam().get(k - 3).getLogo();
				Image img = icon.getImage();
				Image newimg = img.getScaledInstance(170, 120, java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(newimg);
				ls.getB().get(k).setBackground(Color.black);

				ls.getB().get(k).setIcon(icon);
				ls.getB().get(k).setFocusable(false);
				ls.getB().get(k).setBorderPainted(false);
				ls.getEastPanel().add(ls.getB().get(k));
				ls.revalidate();
				ls.repaint();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new GUI();
		File file = new File("theme.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);

	}

}
