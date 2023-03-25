package model.abilities;

import java.util.ArrayList;

import model.effects.*;
import model.world.Champion;

import model.world.Damageable;

public class CrowdControlAbility extends Ability {
	private Effect effect;

	public CrowdControlAbility(String name, int cost, int baseCoolDown, int castRange, AreaOfEffect area, int required,
			Effect effect) {
		super(name, cost, baseCoolDown, castRange, area, required);
		this.effect = effect;
	}

	public Effect getEffect() {
		return effect;
	}

	public void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException {
		for (int i = 0; i < targets.size(); i++) {
			Effect e = (Effect) effect.clone();
			Champion d = (Champion) targets.get(i);
			d.getAppliedEffects().add(e);
			e.apply(d);
		}
	}
}
