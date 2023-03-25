package model.effects;



import model.world.Champion;

public class Silence extends Effect {

	public Silence(int duration) {
		super("Silence", duration, EffectType.DEBUFF);
	}

	public void apply(Champion c) {
		// Silence s = (Silence)clone();
		int current = c.getCurrentActionPoints();
		c.setCurrentActionPoints(current + 2);
		int max = c.getMaxActionPointsPerTurn();
		c.setMaxActionPointsPerTurn(max + 2);

	}

	public void remove(Champion c) {
		int current = c.getCurrentActionPoints();
		c.setCurrentActionPoints(current - 2);
		int max = c.getMaxActionPointsPerTurn();
		c.setMaxActionPointsPerTurn(max - 2);
		/*ArrayList<Effect> effect = c.getAppliedEffects();
		for (int i = 0; i < effect.size(); i++) {
			if (effect.get(i).getName().equals("Silence"))
				effect.remove(i);

		}*/

	}

}
