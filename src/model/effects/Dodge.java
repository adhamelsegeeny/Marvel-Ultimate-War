package model.effects;


import model.world.Champion;

public class Dodge extends Effect {

	public Dodge(int duration) {
		super("Dodge", duration, EffectType.BUFF);
	}

	public void apply(Champion c){
		//Dodge a= (Dodge)clone();
		int speed = (int) (c.getSpeed() * 1.05);
		c.setSpeed(speed);
	}

	public void remove(Champion c) {
		int speed = (int) (c.getSpeed() / 1.05);
		c.setSpeed(speed);
		/*ArrayList<Effect> effect = c.getAppliedEffects();
		for (int i = 0; i < effect.size(); i++) {
			if (effect.get(i).getName().equals("Dodge"))
				effect.remove(i);
		}*/
	}

}
