package model.abilities;

import java.util.ArrayList;

import model.world.Champion;
import model.world.Damageable;

public class HealingAbility extends Ability {
	private int healAmount;

	public HealingAbility(String name, int cost, int baseCoolDown, int castRange, AreaOfEffect area, int required,
			int healAmount) {
		super(name, cost, baseCoolDown, castRange, area, required);
		this.healAmount = healAmount;
	}

	public int getHealAmount() {
		return healAmount;
	}

	public void setHealAmount(int healAmount) {
		this.healAmount = healAmount;
	}

	public void execute(ArrayList<Damageable> targets) {
		for (int i = 0; i < targets.size(); i++) {
			Champion d = (Champion)targets.get(i);
			int hp = d.getCurrentHP() + getHealAmount();
			d.setCurrentHP(hp);
		}

	}

}
