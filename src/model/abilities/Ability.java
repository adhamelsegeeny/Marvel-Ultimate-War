package model.abilities;

import java.util.ArrayList;

import model.effects.Effect;
import model.world.Damageable;

public abstract class Ability {
	private String name;
	private int manaCost;
	private int baseCooldown;
	private int currentCooldown;
	private int castRange;
	private int requiredActionPoints;
	private AreaOfEffect castArea;

	public Ability(String name, int cost, int baseCoolDown, int castRange, AreaOfEffect area, int required) {
		this.name = name;
		manaCost = cost;
		this.baseCooldown = baseCoolDown;
		this.castRange = castRange;
		this.castArea = area;
		requiredActionPoints = required;
		//currentCooldown=0;
	}

	public int getCurrentCooldown() {
		return currentCooldown;
	}

	public void setCurrentCooldown(int currentCooldown) {
		if (currentCooldown < 0)
			this.currentCooldown = 0;
		else if (currentCooldown > baseCooldown)
			this.currentCooldown = baseCooldown;
		else
			this.currentCooldown = currentCooldown;
	}

	public String getName() {
		return name;
	}

	public int getManaCost() {
		return manaCost;
	}

	public int getBaseCooldown() {
		return baseCooldown;
	}

	public int getCastRange() {
		return castRange;
	}

	public AreaOfEffect getCastArea() {
		return castArea;
	}

	public int getRequiredActionPoints() {
		return requiredActionPoints;
	}
	
	public String toString() {
		String s= "Ability: " + getName() + '\n' + "Mana cost: " + getManaCost() + '\n'
				+ "Required point: " + getRequiredActionPoints() + '\n' + "Basecooldown: " + getBaseCooldown()
				+'\n'+"CurrentCoolDown: "+currentCooldown +'\n' +"Area of Effect: " + castArea+ '\n'+ "Cast Range: "+ castRange +'\n';
		
		int x= 0;
		String effect = null;
		if(this instanceof DamagingAbility) {
			x= ((DamagingAbility)this).getDamageAmount();
			s= "Type: DamagingAbility" +'\n'+s  + "DamageAmount : "+ x;
		}
		
		else if(this instanceof HealingAbility) {
			x= ((HealingAbility)this).getHealAmount();
			s="Type: HealingAbility"+ '\n'+ s + "HealAmount: "+x;
		}
		
		else {
			effect= ((CrowdControlAbility)this).getEffect().toString();
			s= "Type: CrowdControlAbility"+'\n'+s+ effect;
		}
		return s;
		
		
	}
	
	public String toString1() {
		String s= "Ability: " + getName() + "--" + "Mana cost: " + getManaCost() + "--"
				+ "Required point: " + getRequiredActionPoints() + " " + "Basecooldown: " + getBaseCooldown()
				+"--"+"CurrentCoolDown: "+currentCooldown +" " +"Area of Effect: " + castArea+ "--"+ "Cast Range: "+ castRange +"--";
		
		int x= 0;
		String effect = null;
		if(this instanceof DamagingAbility) {
			x= ((DamagingAbility)this).getDamageAmount();
			s= "Type: DamagingAbility" +"--"+s  + "DamageAmount : "+ x;
		}
		
		else if(this instanceof HealingAbility) {
			x= ((HealingAbility)this).getHealAmount();
			s="Type: HealingAbility"+ "--"+ s + "HealAmount: "+x;
		}
		
		else {
			effect= ((CrowdControlAbility)this).getEffect().toString();
			s= "Type: CrowdControlAbility"+"--"+s+ effect;
		}
		return s;
		
		
	}
	
	
	public abstract void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException;

}
