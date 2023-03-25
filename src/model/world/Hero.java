package model.world;

import java.util.ArrayList;


import model.effects.*;

public class Hero extends Champion {

	public Hero(String name, int maxHP, int mana, int maxActions, int speed, int attackRange, int attackDamage) {
		super(name, maxHP, mana, maxActions, speed, attackRange, attackDamage);
	}

	public void useLeaderAbility(ArrayList<Champion> targets) {
		for (Champion tmp : targets) {
			ArrayList<Effect> effect = tmp.getAppliedEffects();
			for (int i = 0; i < effect.size(); i++) {
				Effect e = effect.get(i);
				if (effect.get(i).getType() == EffectType.DEBUFF) {
					effect.remove(e);
					e.remove(tmp);
					i--;
				}
			}
			Embrace embrace = new Embrace(2);
			tmp.getAppliedEffects().add(embrace);
			embrace.apply(tmp);
		}

	}
}
