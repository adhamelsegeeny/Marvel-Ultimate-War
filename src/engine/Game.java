package engine;

import java.awt.*;
import model.world.*;
import java.io.*;
import java.util.*;

import javax.swing.ImageIcon;

import exceptions.*;
import model.abilities.*;
import model.effects.*;

public class Game {
	private Player firstPlayer;
	private Player secondPlayer;
	private boolean firstLeaderAbilityUsed;
	private boolean secondLeaderAbilityUsed;
	private Object[][] board;
	private static ArrayList<Champion> availableChampions;
	private static ArrayList<Ability> availableAbilities;
	private PriorityQueue turnOrder;
	private static final int BOARDHEIGHT = 5;
	private static final int BOARDWIDTH = 5;

	public Game(Player first, Player second){
		firstPlayer = first;
		secondPlayer = second;
		board = new Object[BOARDHEIGHT][BOARDWIDTH];
//		availableChampions = new ArrayList<Champion>();
//		availableAbilities = new ArrayList<Ability>();
		turnOrder = new PriorityQueue(6);
		this.placeChampions();
		this.placeCovers();
		this.prepareChampionTurns();
//		loadAbilities("Abilities.csv");
//		loadChampions("Champions.csv");

	}

	private void placeChampions() {
		if (firstPlayer.getTeam().size() < 3 || secondPlayer.getTeam().size() < 3)
			return;
		for (int i = 0; i < 3; i++) {
			board[0][i + 1] = firstPlayer.getTeam().get(i); // firstPlayer team on the lower edge at height=0 and
															// adjacent cells
			firstPlayer.getTeam().get(i).setLocation(new Point(0, i + 1));

			board[BOARDHEIGHT - 1][i + 1] = secondPlayer.getTeam().get(i); // secondPlayer team on the upper edge at
																			// height=4
			secondPlayer.getTeam().get(i).setLocation(new Point(BOARDHEIGHT - 1, i + 1));
		}
	}

	private void placeCovers() {
		for (int i = 0; i < 5; i++) {
			int height = (int) (Math.random() * (BOARDHEIGHT - 2)) + 1; // empty cells that exclude the champions' cells
																		// at height 0 & 4
			int width = (int) (Math.random() * BOARDWIDTH);
			while (board[height][width] != null) {
				height = (int) (Math.random() * (BOARDHEIGHT - 2)) + 1;
				width = (int) (Math.random() * (BOARDWIDTH));
			}
			board[height][width] = new Cover(height, width);
		}
	}

	public static void loadAbilities(String filePath) throws Exception {
		String currLine = "";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		availableAbilities = new ArrayList<Ability>();
		while ((currLine = br.readLine()) != null) { // reached the end of csv file
			String[] temp = currLine.split(",");// putting the dataline in array of strings
			if (temp[0].equals("DMG")) {
				DamagingAbility a = new DamagingAbility(temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[4]),
						Integer.parseInt(temp[3]), AreaOfEffect.valueOf(temp[5]), Integer.parseInt(temp[6]),
						Integer.parseInt(temp[7]));// converting strings to appropriate data types
				availableAbilities.add(a);
			}

			else if (temp[0].equals("HEL")) {
				HealingAbility b = new HealingAbility(temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[4]),
						Integer.parseInt(temp[3]), AreaOfEffect.valueOf(temp[5]), Integer.parseInt(temp[6]),
						Integer.parseInt(temp[7]));// converting strings to appropriate data types
				availableAbilities.add(b);
			} else {
				Effect s = null;
				switch (temp[7]) {
				case "Silence":
					s = new Silence(Integer.parseInt(temp[8]));
					break;
				case "Root":
					s = new Root(Integer.parseInt(temp[8]));
					break;
				case "Shock":
					s = new Shock(Integer.parseInt(temp[8]));
					break;
				case "Disarm":
					s = new Disarm(Integer.parseInt(temp[8]));
					break;
				case "Stun":
					s = new Stun(Integer.parseInt(temp[8]));
					break;
				case "Embrace":
					s = new Embrace(Integer.parseInt(temp[8]));
					break;
				case "Shield":
					s = new Shield(Integer.parseInt(temp[8]));
					break;
				case "SpeedUp":
					s = new SpeedUp(Integer.parseInt(temp[8]));
					break;
				case "Dodge":
					s = new Dodge(Integer.parseInt(temp[8]));
					break;
				case "PowerUp":
					s = new PowerUp(Integer.parseInt(temp[8]));
					break;
				}
				CrowdControlAbility a = new CrowdControlAbility(temp[1], Integer.parseInt(temp[2]),
						Integer.parseInt(temp[4]), Integer.parseInt(temp[3]), AreaOfEffect.valueOf(temp[5]),
						Integer.parseInt(temp[6]), s);// converting strings to appropriate data types
				availableAbilities.add(a);
			}
		}
		br.close();

	}

	public static void loadChampions(String filePath) throws Exception {
		// loadAbilities("Abilities.csv");
		String currLine = "";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		availableChampions = new ArrayList<Champion>();
		Champion h = null;
		while ((currLine = br.readLine()) != null) {
			String[] temp = currLine.split(",");
			if (temp[0].equals("H")) {
				h = new Hero(temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), Integer.parseInt(temp[4]),
						Integer.parseInt(temp[5]), Integer.parseInt(temp[6]), Integer.parseInt(temp[7]));
				availableChampions.add(h);
			}

			else if (temp[0].equals("A")) {
				h = new AntiHero(temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]),
						Integer.parseInt(temp[4]), Integer.parseInt(temp[5]), Integer.parseInt(temp[6]),
						Integer.parseInt(temp[7]));
				availableChampions.add(h);

			}

			else {
				h = new Villain(temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]),
						Integer.parseInt(temp[4]), Integer.parseInt(temp[5]), Integer.parseInt(temp[6]),
						Integer.parseInt(temp[7]));
				availableChampions.add(h);

			}
			for (int i = 0; i < availableAbilities.size(); i++) {// adding the champions' abilities
				Ability p = availableAbilities.get(i);
				String s = p.getName();
				if (temp[8].equals(s))
					(h.getAbilities()).add(p);
				if (temp[9].equals(s))
					(h.getAbilities()).add(p);
				if (temp[10].equals(s))
					(h.getAbilities()).add(p);
				if (h.getAbilities().size() == 3)
					break;
			}

		}
		br.close();

	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}

	public boolean isFirstLeaderAbilityUsed() {
		return firstLeaderAbilityUsed;
	}

	public boolean isSecondLeaderAbilityUsed() {
		return secondLeaderAbilityUsed;
	}

	public Object[][] getBoard() {
		return board;
	}

	public static ArrayList<Champion> getAvailableChampions() {
		return availableChampions;
	}

	public static ArrayList<Ability> getAvailableAbilities() {
		return availableAbilities;
	}

	public PriorityQueue getTurnOrder() {
		return turnOrder;
	}

	public static int getBoardheight() {
		return BOARDHEIGHT;
	}

	public static int getBoardwidth() {
		return BOARDWIDTH;
	}

	public Champion getCurrentChampion() {
		return (Champion) turnOrder.peekMin();
	}

	public Player checkGameOver() {

		if (firstPlayer.getTeam().size() == 0)
			return secondPlayer;
		if (secondPlayer.getTeam().size() == 0)
			return firstPlayer;
		return null;
	}

	public void move(Direction d) throws UnallowedMovementException, NotEnoughResourcesException {
		Champion c = this.getCurrentChampion();
		Point current = c.getLocation();
		if (c.getCondition() == Condition.ROOTED)
			throw new UnallowedMovementException("Champion's condition is rooted");
		if (c.getCurrentActionPoints() == 0)
			throw new NotEnoughResourcesException("Champion doesn't have enough points");
		switch (d) {
		case UP:
			if (current.x + 1 == BOARDHEIGHT)
				throw new UnallowedMovementException("Out of board");
			if (board[current.x + 1][current.y] != null)
				throw new UnallowedMovementException("This cell isn't empty");
			Point up = new Point(current.x + 1, current.y);
			board[current.x + 1][current.y] = c;
			c.setLocation(up);
			break;

		case DOWN:
			if (current.x - 1 == -1)
				throw new UnallowedMovementException("Out of board");
			if (board[current.x - 1][current.y] != null)
				throw new UnallowedMovementException("This cell isn't empty");
			Point down = new Point(current.x - 1, current.y);
			board[current.x - 1][current.y] = c;
			c.setLocation(down);
			break;

		case LEFT:
			if (current.y - 1 == -1)
				throw new UnallowedMovementException("Out of board");
			if (board[current.x][current.y - 1] != null)
				throw new UnallowedMovementException("This cell isn't empty");
			Point left = new Point(current.x, current.y - 1);
			board[current.x][current.y - 1] = c;
			c.setLocation(left);
			break;

		case RIGHT:
			if (current.y + 1 == BOARDWIDTH)
				throw new UnallowedMovementException("Out of board");
			if (board[current.x][current.y + 1] != null)
				throw new UnallowedMovementException("This cell isn't empty");
			Point right = new Point(current.x, current.y + 1);
			board[current.x][current.y + 1] = c;
			c.setLocation(right);
			break;

		}
		board[current.x][current.y] = null;
		c.setCurrentActionPoints(c.getCurrentActionPoints() - 1);

	}

	public void execution(ArrayList<Damageable> target, Ability a)
			throws AbilityUseException, CloneNotSupportedException {
		// if (target.isEmpty())
		// throw new AbilityUseException();
		a.execute(target);
		for (Damageable d : target) {
			KnockOut(d);
		}
	}

	public ArrayList<Champion> getCurrentTeam() {
		ArrayList<Champion> team1 = firstPlayer.getTeam();
		ArrayList<Champion> team2 = secondPlayer.getTeam();
		if (team1.contains(getCurrentChampion()))
			return team1;
		else
			return team2;
	}

	public ArrayList<Champion> getOppositeTeam() {
		ArrayList<Champion> team1 = firstPlayer.getTeam();
		ArrayList<Champion> team2 = secondPlayer.getTeam();
		if (team1.contains(getCurrentChampion()))
			return team2;
		else
			return team1;
	}

	public boolean removeShield(Champion c, Ability a) {
		if (!(a instanceof DamagingAbility))
			return false;
		for (Effect effect : c.getAppliedEffects()) {
			if (effect instanceof Shield) {
				c.getAppliedEffects().remove(effect);
				effect.remove(c);
				return true;
			}
		}
		return false;
	}

	public boolean removeShield(Champion c) {
		for (Effect effect : c.getAppliedEffects()) {
			if (effect instanceof Shield) {
				c.getAppliedEffects().remove(effect);
				effect.remove(c);
				return true;
			}
		}
		return false;
	}

	public double removeDodge(Champion c) {
		for (Effect effect : c.getAppliedEffects()) {
			if (effect instanceof Dodge)
				return Math.random();
		}
		return 1;
	}

	public int manhattan(Point a, Point b) {
		int z = Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
		return z;
	}

	public void damageCover(Champion c, Ability ab, Direction d, ArrayList<Damageable> target) {
		if (!(ab instanceof DamagingAbility))
			return;
		Point loc = c.getLocation();
		int a = loc.x;
		int b = loc.y;
		int range = ab.getCastRange();
		switch (d) {
		case UP:

			for (int i = a + range; i > a && i < BOARDHEIGHT; i--) {
				if (board[i][b] instanceof Cover)
					target.add((Cover) board[i][b]);
			}
			break;

		case DOWN:

			for (int i = a - range; i >= 0 && i < a; i++) {
				if (board[i][b] instanceof Cover)
					target.add((Cover) board[i][b]);
			}
			break;
		case RIGHT:

			for (int i = b + range; i > b && i < BOARDWIDTH; i--) {
				if (board[a][i] instanceof Cover)
					target.add((Cover) board[a][i]);
			}
			break;
		case LEFT:

			for (int i = b - range; i >= 0 && i < b; i++) {
				if (board[a][i] instanceof Cover)
					target.add((Cover) board[a][i]);
			}
			break;
		}

	}

	public Player getCurrentPlayer() {
		ArrayList<Champion> team1 = firstPlayer.getTeam();
		if (team1.contains(getCurrentChampion()))
			return firstPlayer;
		else
			return secondPlayer;
	}

	public Player getOppositePlayer() {
		ArrayList<Champion> team1 = firstPlayer.getTeam();
		if (team1.contains(getCurrentChampion()))
			return secondPlayer;
		else
			return firstPlayer;
	}

	public void KnockOut(Damageable c) {
		if (c == null)
			return;
		if (c.getCurrentHP() == 0) {
			Point loc = c.getLocation();
			if (c instanceof Champion) {
				((Champion) c).setCondition(Condition.KNOCKEDOUT);
				((Champion) c).setLocation(null);
				if (firstPlayer.getTeam().contains(c))
					firstPlayer.getTeam().remove(c);
				if (secondPlayer.getTeam().contains(c))
					secondPlayer.getTeam().remove(c);
				turnOrder.remove((Champion) c);
			}
			board[loc.x][loc.y] = null;

		}
	}

	public void castAbility(Ability a)
			throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException {
		AreaOfEffect area = a.getCastArea();
		Champion c = getCurrentChampion();

		if (a.getCastArea() == AreaOfEffect.DIRECTIONAL || a.getCastArea() == AreaOfEffect.SINGLETARGET)
			throw new AbilityUseException();

		exceptions(a, c);

		ArrayList<Champion> team1 = getCurrentTeam();
		ArrayList<Champion> team2 = getOppositeTeam();
		ArrayList<Damageable> target = new ArrayList<Damageable>();
		Point loc = c.getLocation();

		switch (area) {

		case TEAMTARGET:
			if ((a instanceof CrowdControlAbility && ((CrowdControlAbility) a).getEffect().getType() == EffectType.BUFF)
					|| a instanceof HealingAbility) {
				for (Champion d : team1) {
					if (manhattan(loc, d.getLocation()) <= a.getCastRange())
						target.add(d);
				}
			} else {
				for (Champion d : team2) {
					if (manhattan(loc, d.getLocation()) <= a.getCastRange() && !removeShield(d, a)) {
						target.add(d);
					}
				}
			}

			break;

		case SURROUND:
			if (a instanceof DamagingAbility) {
				ArrayList<Point> x = surroundCover(c, a);
				for (Point p : x) {
					if (p.x < BOARDHEIGHT && p.y < BOARDWIDTH && p.x > -1 && p.y > -1 && board[p.x][p.y] != null
							&& board[p.x][p.y] instanceof Cover && manhattan(p, loc) <= a.getCastRange())
						target.add((Cover) board[p.x][p.y]);
				}
			}

			if ((a instanceof CrowdControlAbility
					&& ((CrowdControlAbility) a).getEffect().getType() == EffectType.DEBUFF)
					|| a instanceof DamagingAbility) {
				for (int i = 0; i < team2.size(); i++) {
					Champion d = team2.get(i);
					Point tmp = d.getLocation();
					if ((manhattan(loc, tmp) == 1 || (Math.abs(loc.x - tmp.x) == 1 && Math.abs(loc.y - tmp.y) == 1))) {
						if (!removeShield(d, a))
							target.add(d);
					}
				}

			} else {
				for (int i = 0; i < team1.size(); i++) {
					Champion d = team1.get(i);
					Point tmp = d.getLocation();
					if ((Math.abs(loc.x - tmp.x) == 1 && Math.abs(loc.y - tmp.y) == 1) || manhattan(loc, tmp) == 1)
						target.add(d);
				}
			}
			break;

		case SELFTARGET:
			if ((a instanceof CrowdControlAbility
					&& ((CrowdControlAbility) a).getEffect().getType() == EffectType.DEBUFF)
					|| a instanceof DamagingAbility)
				break;
			target.add(c);
			break;

		default:
			break;
		}

		execution(target, a);
		c.setMana(c.getMana() - a.getManaCost());
		a.setCurrentCooldown(a.getBaseCooldown());
		c.setCurrentActionPoints(c.getCurrentActionPoints() - a.getRequiredActionPoints());

	}

	public void useLeaderAbility() throws LeaderAbilityAlreadyUsedException, LeaderNotCurrentException {

		Champion c = getCurrentChampion();
		if (getCurrentPlayer().getLeader() != c)
			throw new LeaderNotCurrentException("The champion is not the leader");

		if (getCurrentPlayer() == firstPlayer && isFirstLeaderAbilityUsed())
			throw new LeaderAbilityAlreadyUsedException("The leader ability is used already");

		if (getCurrentPlayer() == secondPlayer && isSecondLeaderAbilityUsed())
			throw new LeaderAbilityAlreadyUsedException("The leader ability is used already");

		ArrayList<Champion> team1 = getCurrentTeam();
		ArrayList<Champion> team2 = getOppositeTeam();
		ArrayList<Champion> target = new ArrayList<Champion>();

		// 3 case: hero/villain/antihero

		if (c instanceof Hero) { // first case
			for (Champion champion : team1)
				target.add(champion);
		}

		else if (c instanceof AntiHero) { // second case
			for (Champion champion : team1) {
				if (champion != getCurrentPlayer().getLeader())
					target.add(champion);
			}
			for (Champion champion : team2) {
				if (champion != getOppositePlayer().getLeader())
					target.add(champion);
			}

		}

		else {
			for (Champion champion : team2) {
				if (champion.getCurrentHP() < (int) (0.3 * champion.getMaxHP()))
					target.add(champion);
			}
		}
		c.useLeaderAbility(target);
		for (Champion d : target)
			KnockOut(d);
		if (firstPlayer.getLeader() == c)
			firstLeaderAbilityUsed = true;
		if (secondPlayer.getLeader() == c)
			secondLeaderAbilityUsed = true;

	}

	public void reset(Champion c) {

		c.setCurrentActionPoints(c.getMaxActionPointsPerTurn());
		ArrayList<Effect> effect = c.getAppliedEffects();
		for (int i = 0; i < effect.size(); i++) {
			Effect e = effect.get(i);
			e.setDuration(e.getDuration() - 1);
			if (e.getDuration() == 0) {
				c.getAppliedEffects().remove(e);
				e.remove(c);
				i--;
			}
		}

		for (Ability a : c.getAbilities())
			a.setCurrentCooldown(a.getCurrentCooldown() - 1);
	}

	public void endTurn() {
		turnOrder.remove();

		if (turnOrder.isEmpty()) {
			prepareChampionTurns();
		}
		Champion champ = getCurrentChampion();

		if (champ.getCondition() != Condition.INACTIVE)
			reset(champ);

		if (champ.getCondition() == Condition.INACTIVE) {
			reset(champ);
			endTurn();

		}
	}

	private void prepareChampionTurns() {
		ArrayList<Champion> team1 = firstPlayer.getTeam();
		ArrayList<Champion> team2 = secondPlayer.getTeam();
		for (Champion champion1 : team1)
			turnOrder.insert(champion1);

		for (Champion champion2 : team2)
			turnOrder.insert(champion2);

	}

	public void castAbility(Ability a, Direction d)
			throws CloneNotSupportedException, NotEnoughResourcesException, AbilityUseException {

		if (a.getCastArea() != AreaOfEffect.DIRECTIONAL)
			throw new AbilityUseException();

		ArrayList<Champion> team1 = getCurrentTeam();
		ArrayList<Champion> team2 = getOppositeTeam();
		ArrayList<Damageable> target = new ArrayList<Damageable>();
		Champion c = getCurrentChampion();

		exceptions(a, c);

		damageCover(c, a, d, target);

		if ((a instanceof CrowdControlAbility && ((CrowdControlAbility) a).getEffect().getType() == EffectType.DEBUFF)
				|| a instanceof DamagingAbility) {
			Point loc = c.getLocation();
			for (int i = 0; i < team2.size(); i++) {
				Champion champion = team2.get(i);
				Point tmp = team2.get(i).getLocation();

				switch (d) {
				case UP:
					if (Math.abs(tmp.x - loc.x) <= a.getCastRange() && tmp.y == loc.y && !removeShield(team2.get(i), a))
						target.add(champion);
					break;
				case DOWN:
					if (Math.abs(loc.x - tmp.x) <= a.getCastRange() && tmp.y == loc.y && !removeShield(team2.get(i), a))
						target.add(champion);
					break;
				case RIGHT:
					if (Math.abs(tmp.y - loc.y) <= a.getCastRange() && tmp.x == loc.x && !removeShield(team2.get(i), a))
						target.add(champion);
					break;
				case LEFT:
					if (Math.abs(loc.y - tmp.y) <= a.getCastRange() && tmp.x == loc.x && !removeShield(team2.get(i), a))
						target.add(champion);
					break;
				}

			}

		} else {
			Point loc = c.getLocation();
			for (Champion champion : team1) {
				Point tmp = champion.getLocation();
				if (tmp != null) {
					switch (d) {
					case UP:
						if (Math.abs(tmp.x - loc.x) <= a.getCastRange() && tmp.y == loc.y)
							target.add(champion);
						break;
					case DOWN:
						if (Math.abs(loc.x - tmp.x) <= a.getCastRange() && tmp.y == loc.y)
							target.add(champion);
						break;
					case RIGHT:
						if (Math.abs(tmp.y - loc.y) <= a.getCastRange() && tmp.x == loc.x)
							target.add(champion);
						break;
					case LEFT:
						if (Math.abs(loc.y - tmp.y) <= a.getCastRange() && tmp.x == loc.x)
							target.add(champion);
						break;
					}

				}
			}
		}

		execution(target, a);
		c.setMana(c.getMana() - a.getManaCost());
		a.setCurrentCooldown(a.getBaseCooldown());
		c.setCurrentActionPoints(c.getCurrentActionPoints() - a.getRequiredActionPoints());

	}

	public void castAbility(Ability a, int x, int y) throws AbilityUseException, NotEnoughResourcesException,
			InvalidTargetException, CloneNotSupportedException {

		if (a.getCastArea() != AreaOfEffect.SINGLETARGET)
			throw new AbilityUseException();

		ArrayList<Champion> team1 = getCurrentTeam();
		ArrayList<Champion> team2 = getOppositeTeam();
		ArrayList<Damageable> target = new ArrayList<Damageable>();
		Champion c = getCurrentChampion();
		exceptions(a, c);

		if (x >= BOARDHEIGHT || y >= BOARDWIDTH)
			throw new InvalidTargetException();

		if (board[x][y] == null)
			throw new InvalidTargetException("Cell is empty");

		if (board[x][y] instanceof Cover && !(a instanceof DamagingAbility))
			throw new InvalidTargetException("Can't cast this ability on cover");

		Point p = new Point(x, y);
		if (manhattan(p, c.getLocation()) > a.getCastRange())
			throw new AbilityUseException("Target out of range");

		if (board[x][y] instanceof Cover && a instanceof DamagingAbility)
			target.add((Cover) board[x][y]);

		if (board[x][y] instanceof Champion) {
			Champion champion = (Champion) board[x][y];

			if (team1.contains(champion) && ((a instanceof CrowdControlAbility
					&& ((CrowdControlAbility) a).getEffect().getType() == EffectType.DEBUFF)
					|| a instanceof DamagingAbility))

				throw new InvalidTargetException("Can't cast this ability on friendly champion");

			if (team2.contains(champion) && ((a instanceof CrowdControlAbility
					&& ((CrowdControlAbility) a).getEffect().getType() == EffectType.BUFF)
					|| a instanceof HealingAbility))

				throw new InvalidTargetException("Can't cast this ability on enemy champion");

			if (!removeShield(champion, a)) {
				target.add(champion);
			}
		}
		execution(target, a);
		c.setMana(c.getMana() - a.getManaCost());
		a.setCurrentCooldown(a.getBaseCooldown());
		c.setCurrentActionPoints(c.getCurrentActionPoints() - a.getRequiredActionPoints());
	}

	public void exceptions(Ability a, Champion c) throws AbilityUseException, NotEnoughResourcesException {
		if (a.getCurrentCooldown() != 0)
			throw new AbilityUseException("Ability is still under cooldown");
		// if (!(c.getAbilities().contains(a)))
		// return;

		if (c.getCurrentActionPoints() < a.getRequiredActionPoints())
			throw new NotEnoughResourcesException("Champion doesn't have enough points");
		if (c.getMana() < a.getManaCost())
			throw new NotEnoughResourcesException("Champion doesn't have enough mana");
		for (Effect effect : c.getAppliedEffects()) {
			if (effect instanceof Silence)
				throw new AbilityUseException("Champion has silence effect applied");
		}
	}

	public void attack(Direction d)
			throws ChampionDisarmedException, NotEnoughResourcesException, AbilityUseException, InvalidTargetException {
		Champion c = getCurrentChampion();
		if (c.getCurrentActionPoints() < 2)
			throw new NotEnoughResourcesException("Champion doesn't have enough points");

		Damageable target = null;
		Point loc = c.getLocation();
		for (Effect e : c.getAppliedEffects()) {
			if (e instanceof Disarm)
				throw new ChampionDisarmedException("Champion is under disarm effect");
		}
		switch (d) {

		case UP:
			for (int i = 1; loc.x + i < BOARDHEIGHT && i <= c.getAttackRange(); i++) {
				if (board[loc.x + i][loc.y] != null) {
					if (board[loc.x + i][loc.y] instanceof Cover
							|| !getCurrentTeam().contains(board[loc.x + i][loc.y])) {
						target = (Damageable) board[loc.x + i][loc.y];
						break;
					}
				}
			}
			break;

		case DOWN:
			for (int i = 1; i <= loc.x && i <= c.getAttackRange(); i++) {
				if (board[loc.x - i][loc.y] != null) {
					if (board[loc.x - i][loc.y] instanceof Cover
							|| !getCurrentTeam().contains(board[loc.x - i][loc.y])) {
						target = (Damageable) board[loc.x - i][loc.y];
						break;
					}
				}
			}
			break;
		case RIGHT:
			for (int i = 1; loc.y + i < BOARDWIDTH && i <= c.getAttackRange(); i++) {
				if (board[loc.x][loc.y + i] != null) {
					if (board[loc.x][loc.y + i] instanceof Cover
							|| !getCurrentTeam().contains(board[loc.x][loc.y + i])) {
						target = (Damageable) board[loc.x][loc.y + i];
						break;
					}
				}
			}
			break;
		case LEFT:
			for (int i = 1; i <= loc.y && i <= c.getAttackRange(); i++) {
				if (board[loc.x][loc.y - i] != null) {
					if (board[loc.x][loc.y - i] instanceof Cover
							|| !getCurrentTeam().contains(board[loc.x][loc.y - i])) {
						target = (Damageable) board[loc.x][loc.y - i];
						break;
					}
				}
			}
			break;
		}
		if (target == null) {
			c.setCurrentActionPoints(c.getCurrentActionPoints() - 2);
			return;
		}
		if (target instanceof Cover)
			target.setCurrentHP(target.getCurrentHP() - c.getAttackDamage());
		else {
			if (!removeShield((Champion) target)) {
				if (removeDodge((Champion) target) == 1 || removeDodge((Champion) target) < 0.5) {
					if (sameDamage(c, target))
						target.setCurrentHP(target.getCurrentHP() - c.getAttackDamage());
					else
						target.setCurrentHP(target.getCurrentHP() - (int) (c.getAttackDamage() * 1.5));
				}
			}
		}
		KnockOut(target);
		c.setCurrentActionPoints(c.getCurrentActionPoints() - 2);
	}

	public boolean sameDamage(Champion c, Damageable target) {
		if ((c instanceof Hero && target instanceof Hero) || (c instanceof AntiHero && target instanceof AntiHero)
				|| (c instanceof Villain && target instanceof Villain)) {
			return true;
		}
		return false;
	}
	
//	public void test() {
//		sameDamage((Hero)(getCurrentChampion()), getCurrentChampion());
//	}

	public ArrayList<Point> surroundCover(Champion c, Ability a) {
		if (!(a instanceof DamagingAbility))
			return null;
		ArrayList<Point> point = new ArrayList<Point>();
		Point loc = c.getLocation();
		for (int i = 1; i > -2; i--) {
			for (int j = -1; j < 2; j++) {
				if (i != 0 || j != 0) {
					Point p = new Point(loc.x + i, loc.y + j);
					point.add(p);
				}

			}
		}
		return point;
	}
	
}
