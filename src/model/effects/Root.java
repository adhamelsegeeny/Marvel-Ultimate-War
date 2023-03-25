package model.effects;

import java.util.ArrayList;

import model.world.Champion;
import model.world.Condition;

public class Root extends Effect {

	public Root(int duration) {
		super("Root", duration, EffectType.DEBUFF);
	}

	public void apply(Champion c) {
		// Root r = (Root) clone();
		if (c.getCondition() != Condition.INACTIVE)
			c.setCondition(Condition.ROOTED);

	}

	public void remove(Champion c) {
		if (c.getCondition() != Condition.INACTIVE)
			c.setCondition(Condition.ACTIVE);
		ArrayList<Effect> effect = c.getAppliedEffects();
		for (int i = 0; i < effect.size(); i++) {
			if (effect.get(i).getName().equals("Root")) {
				c.setCondition(Condition.ROOTED);
			}
		}

	}
}
