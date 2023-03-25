package model.effects;



import model.world.Champion;

public class Shock extends Effect {

	public Shock(int duration) {
		super("Shock", duration, EffectType.DEBUFF);
	}

	public void apply(Champion c) {
		
		int speed = (int) (c.getSpeed() * 0.9);
		c.setSpeed(speed);
		int damage = (int) (c.getAttackDamage() * 0.9);
		c.setAttackDamage(damage);
		int current = c.getCurrentActionPoints() - 1;
		int max = c.getMaxActionPointsPerTurn() - 1;
		c.setCurrentActionPoints(current);
		c.setMaxActionPointsPerTurn(max);
		
		
		
	}

	public void remove(Champion c) {

		int speed = (int) (c.getSpeed() / 0.9);
		c.setSpeed(speed);
		int damage = (int) (c.getAttackDamage() / 0.9);
		c.setAttackDamage(damage);
		int current = c.getCurrentActionPoints() + 1;
		int max = c.getMaxActionPointsPerTurn() + 1;
		c.setCurrentActionPoints(current);
		c.setMaxActionPointsPerTurn(max);
		
		

	}

}
