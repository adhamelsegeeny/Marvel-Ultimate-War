package model.effects;

import java.util.ArrayList;

import model.world.Champion;
import model.world.Condition;

public class Stun extends Effect {

	public Stun(int duration) {
		super("Stun", duration, EffectType.DEBUFF);
	}

	public void apply(Champion c) {
		// Stun s= (Stun)clone();
		c.setCondition(Condition.INACTIVE);
	}

	public void remove(Champion c) {
		ArrayList<Effect> effect = c.getAppliedEffects();
		for (int i = 0; i < effect.size(); i++) {
			if (effect.get(i).getName().equals("Root"))
				c.setCondition(Condition.ROOTED);
		}
		if (c.getCondition() != Condition.ROOTED)
			c.setCondition(Condition.ACTIVE);
	}
}
