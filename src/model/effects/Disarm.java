package model.effects;

import java.util.ArrayList;

import model.abilities.*;
import model.world.Champion;

public class Disarm extends Effect {

	public Disarm(int duration) {
		super("Disarm", duration, EffectType.DEBUFF);
	}

	public void apply(Champion c){
		//Disarm d = (Disarm)clone();
		DamagingAbility a = new DamagingAbility("Punch", 0, 1, 1, AreaOfEffect.SINGLETARGET, 1, 50);
		c.getAbilities().add(a);

	}

	public void remove(Champion c) {
		ArrayList<Ability> ability = c.getAbilities();
		for (int i = 0; i < ability.size(); i++) {
			if (ability.get(i).getName().equals("Punch"))
				ability.remove(i);
		}
	}

}
