package model.effects;



import model.world.Champion;

public class SpeedUp extends Effect {

	public SpeedUp(int duration) {
		super("SpeedUp", duration, EffectType.BUFF);
	}

	public void apply(Champion c) {
		// SpeedUp s = (SpeedUp)clone();
		int speed = (int) (c.getSpeed() * 1.15);
		c.setSpeed(speed);
		int current = c.getCurrentActionPoints();
		c.setCurrentActionPoints(current + 1);
		int max = c.getMaxActionPointsPerTurn();
		c.setMaxActionPointsPerTurn(max + 1);
	}

	public void remove(Champion c) {
		int speed = (int) (c.getSpeed() / 1.15);
		c.setSpeed(speed);
		int current = c.getCurrentActionPoints();
		c.setCurrentActionPoints(current - 1);
		int max = c.getMaxActionPointsPerTurn();
		c.setMaxActionPointsPerTurn(max - 1);
		/*ArrayList<Effect> effect = c.getAppliedEffects();
		for (int i = 0; i < effect.size(); i++) {
			if (effect.get(i).getName().equals("SpeedUp"))
				effect.remove(i);

		}*/

	}

}
