package model.effects;



import model.world.Champion;

public class Embrace extends Effect {

	public Embrace(int duration) {
		super("Embrace", duration, EffectType.BUFF);
	}

	public void apply(Champion c) {
		// Embrace e= (Embrace)this.clone();
		int health = (int) ((0.2 * c.getMaxHP()) + c.getCurrentHP());
		c.setCurrentHP(health);
		int mana = (int) (1.2 * c.getMana());
		c.setMana(mana);
		int speed = (int) (c.getSpeed() * 1.2);
		int damage = (int) (c.getAttackDamage() * 1.2);
		c.setSpeed(speed);
		c.setAttackDamage(damage);
	}

	public void remove(Champion c) {
		int speed = (int) (c.getSpeed() / 1.2);
		int damage = (int) (c.getAttackDamage() / 1.2);
		c.setSpeed(speed);
		c.setAttackDamage(damage);
		/*ArrayList<Effect> effect = c.getAppliedEffects();
		for (int i = 0; i < effect.size(); i++) {
			if (effect.get(i).getName().equals("Embrace"))
				effect.remove(i);
		}*/

	}
}
