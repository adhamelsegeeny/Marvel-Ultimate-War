package model.effects;

import java.util.ArrayList;

import model.abilities.*;
import model.world.Champion;

public class PowerUp extends Effect {

	public PowerUp(int duration) {
		super("PowerUp", duration, EffectType.BUFF);
	}

	public void apply(Champion c){
		//PowerUp p = (PowerUp)clone();
		ArrayList<Ability> x = c.getAbilities();
		for (int i = 0; i < c.getAbilities().size(); i++) {
			if (x.get(i) instanceof DamagingAbility) {
				Ability a = (DamagingAbility) x.get(i);
				int damage = (int) (((DamagingAbility) a).getDamageAmount() * 1.2);
				((DamagingAbility) a).setDamageAmount(damage);
			} else if (x.get(i) instanceof HealingAbility) {
				Ability a = (HealingAbility) x.get(i);
				int heal = (int) (((HealingAbility) a).getHealAmount() * 1.2);
				((HealingAbility) a).setHealAmount(heal);
			}

		}
	}

	public void remove(Champion c) {
		ArrayList<Ability> x = c.getAbilities();
		for (int i = 0; i < c.getAbilities().size(); i++) {
			if (x.get(i) instanceof DamagingAbility) {
				Ability a = (DamagingAbility) x.get(i);
				int damage = (int) (((DamagingAbility) a).getDamageAmount() / 1.2);
				((DamagingAbility) a).setDamageAmount(damage);
			} else if (x.get(i) instanceof HealingAbility) {
				Ability a = (HealingAbility) x.get(i);
				int heal = (int) (((HealingAbility) a).getHealAmount() / 1.2);
				((HealingAbility) a).setHealAmount(heal);
			}

		}
		
		/*ArrayList<Effect> effect = c.getAppliedEffects();
		for (int i = 0; i < effect.size(); i++) {
			if (effect.get(i).getName().equals("PowerUp"))
				effect.remove(i);
		}*/
	}
}
