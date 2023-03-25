package model.world;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import model.abilities.Ability;
import model.effects.Effect;
import java.awt.*;

public abstract class Champion implements Damageable, Comparable {
	private String name;
	private int maxHP;
	private int currentHP;
	private int mana;
	private int maxActionPointsPerTurn;
	private int currentActionPoints;
	private int attackRange;
	private int attackDamage;
	private int speed;
	private ArrayList<Ability> abilities;
	private ArrayList<Effect> appliedEffects;
	private Condition condition;
	private Point location;
	private ImageIcon img;
	private ImageIcon logo;

	public Champion(String name, int maxHP, int mana, int maxActions, int speed, int attackRange, int attackDamage) {
		this.name = name;
		this.maxHP = maxHP;
		this.mana = mana;
		maxActionPointsPerTurn = maxActions;
		this.speed = speed;
		this.attackRange = attackRange;
		this.attackDamage = attackDamage;
		condition = Condition.ACTIVE;
		abilities = new ArrayList<Ability>();
		appliedEffects = new ArrayList<Effect>();
		currentActionPoints = maxActions;
		currentHP = this.maxHP;
		setImg();
	}

	public ImageIcon getLogo() {
		return logo;
	}

	public void setLogo(ImageIcon logo) {
		this.logo = logo;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		if (currentHP > maxHP)
			this.currentHP = maxHP;
		else if (currentHP > 0)
			this.currentHP = currentHP;
		else
			this.currentHP = 0;
	}

	public int getMaxActionPointsPerTurn() {
		return maxActionPointsPerTurn;
	}

	public void setMaxActionPointsPerTurn(int maxActionPointsPerTurn) {
		this.maxActionPointsPerTurn = maxActionPointsPerTurn;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public int getMana() {
		return mana;
	}

	public int getCurrentActionPoints() {
		return currentActionPoints;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public ArrayList<Ability> getAbilities() {
		return abilities;
	}

	public ArrayList<Effect> getAppliedEffects() {
		return appliedEffects;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon icon) {
		this.img = icon;
	}

	private void setImg() {
		String name = this.getName();
		switch (name) {
		case "Ironman":
			this.img = new ImageIcon(this.getClass().getResource("/ironman.png"));
			this.logo = new ImageIcon(this.getClass().getResource("/iron.png"));
			break;

		case "Deadpool":
			this.img = new ImageIcon(this.getClass().getResource("/deadpool.png"));
			this.logo = new ImageIcon(this.getClass().getResource("/pool.png"));
			break;

		case "Captain America":
			this.img = new ImageIcon(this.getClass().getResource("/cap america.png"));
			this.logo = new ImageIcon(this.getClass().getResource("/cap1.png"));

			break;

		case "Thor":
			this.img = new ImageIcon(this.getClass().getResource("/thorr.png"));
			this.logo = new ImageIcon(this.getClass().getResource("/thor1.png"));
			break;

		case "Spiderman":
			this.img = new ImageIcon(this.getClass().getResource("/spiderman.png"));
			this.logo = new ImageIcon(this.getClass().getResource("/spider1.png"));
			break;

		case "Hela":
			this.img = new ImageIcon(this.getClass().getResource("/hela.png"));
			this.logo = new ImageIcon(this.getClass().getResource("/hela1.jpg"));
			break;

		case "Venom":
			this.img = new ImageIcon(this.getClass().getResource("/venom.png"));
			this.logo = new ImageIcon(this.getClass().getResource("/venom2.jpg"));
			break;

		case "Ghost Rider":
			this.img = new ImageIcon(this.getClass().getResource("/ghostrider.png"));
			this.logo = new ImageIcon(this.getClass().getResource("/ghost1.png"));
			break;

		case "Hulk":
			this.img = new ImageIcon(this.getClass().getResource("/hulk.png"));
			this.logo = new ImageIcon(this.getClass().getResource("/hulk1.png"));
			break;

		case "Dr Strange":
			this.img = new ImageIcon(this.getClass().getResource("/strange.png"));
			this.logo = new ImageIcon(this.getClass().getResource("/dr.png"));
			break;

		case "Quicksilver":
			this.img = new ImageIcon(this.getClass().getResource("/quicksilver.png"));
			this.logo = new ImageIcon(this.getClass().getResource("/quick1.jpg"));
			break;

		case "Yellow Jacket":
			this.img = new ImageIcon(this.getClass().getResource("/yellow.png"));
			this.logo = new ImageIcon(this.getClass().getResource("/yellow1.png"));
			break;

		case "Loki":
			this.img = new ImageIcon(this.getClass().getResource("/loki1.png"));
			this.logo = new ImageIcon(this.getClass().getResource("/loki.png"));
			break;

		case "Electro":
			this.img = new ImageIcon(this.getClass().getResource("/electro.png"));
			this.logo = new ImageIcon(this.getClass().getResource("/electro1.png"));
			break;

		case "Iceman":
			this.img = new ImageIcon(this.getClass().getResource("/iceman.png"));
			this.logo = new ImageIcon(this.getClass().getResource("/icem.png"));
			break;

		default:
			break;
		}

	}

	public void setCurrentActionPoints(int currentActionPoints) {
		if (currentActionPoints > maxActionPointsPerTurn)
			this.currentActionPoints = maxActionPointsPerTurn;
		else if (currentActionPoints < 0)
			this.currentActionPoints = 0;
		else
			this.currentActionPoints = currentActionPoints;
	}

	public int compareTo(Object o) {
		Champion c = (Champion) o;
		if (c.speed > speed)
			return 1;
		else if (c.speed == speed) {
			return (name.compareTo(c.name));
		}
		return -1;
	}

	public String toString() {
		String type = null;
		if (this instanceof Hero)
			type = "Hero";
		else if (this instanceof Villain)
			type = "Villain";
		else
			type = "AntiHero";

		String ability = "";
		int c = 1;
		for (Ability a : this.getAbilities()) {
			ability += '\n' + "Ability " + c + ": " + a.getName() + '\n' + "Mana cost: " + a.getManaCost() + '\n'
					+ "Required point: " + a.getRequiredActionPoints() + '\n' + "Basecooldown: " + a.getBaseCooldown()
					+ '\n';
			c++;
		}
		return "" + name + '\n' + "Type: " + type + '\n' + "Mana: " + mana + '\n' + "Speed: " + speed + '\n'
				+ "AttackDamage: " + attackDamage + '\n' + "AttackRange: " + attackRange + '\n' + "MaxHp: " + maxHP
				+ '\n' + "MaxPoints: " + maxActionPointsPerTurn + '\n' + ability;
	}

	public String toString1() {
		String type = null;
		if (this instanceof Hero)
			type = "Hero";
		else if (this instanceof Villain)
			type = "Villain";
		else
			type = "AntiHero";
		return "" + name + '\n' + "Type: " + type + '\n' + "Mana: " + mana + '\n' + "AttackDamage: " + attackDamage
				+ '\n' + "Attack Range: " + attackRange + '\n' + "Current HP: " + currentHP;
	}
	
	public String toString2() {
		String type = null;
		if (this instanceof Hero)
			type = "Hero";
		else if (this instanceof Villain)
			type = "Villain";
		else
			type = "AntiHero";
		return "" + name + "  " + "Type: " + type + "  " + "Mana: " + mana + '\n' + "AttackDamage: " + attackDamage
				+ "  " + "Attack Range: " + attackRange;
	}

	public String abilityString() {
		String s = "";
		for (Ability a : getAbilities()) {
			s = s + a.toString() + '\n' + '\n';
		}
		return s;
	}

	public String attributes() {
		String type = null;
		if (this instanceof Hero)
			type = "Hero";
		else if (this instanceof Villain)
			type = "Villain";
		else
			type = "AntiHero";
		return "" + name + '\n' + "Type: " + type + '\n' + "Mana: " + mana + '\n' + "Speed: " + speed + '\n'
				+ "AttackDamage: " + attackDamage + '\n' + "AttackRange: " + attackRange + '\n' + "Current HP: "
				+ currentHP + '\n' + "MaxPoints: " + maxActionPointsPerTurn + '\n';
	}

	public String effects() {
		String s = "Applied Effects:" + '\n';
		for (Effect e : getAppliedEffects()) {
			s = s + "Effect Name :" + e.getName() + '\n' + "Duration: " + e.getDuration() + '\n';
		}
		return s;
	}

	public abstract void useLeaderAbility(ArrayList<Champion> targets);

}
