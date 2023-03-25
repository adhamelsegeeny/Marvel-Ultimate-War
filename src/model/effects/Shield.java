package model.effects;



import model.world.Champion;

public class Shield extends Effect {
	
	public Shield(int duration) {
		super("Shield",duration,EffectType.BUFF);
	}
	
	public void apply(Champion c){
		//Shield s= (Shield)clone();
		int speed = (int) (c.getSpeed() * 1.02);
		c.setSpeed(speed);
	}
	
	public void remove(Champion c) {
		
		int speed = (int) (c.getSpeed() / 1.02);
		c.setSpeed(speed);
		/*ArrayList<Effect> effect = c.getAppliedEffects();
		for (int i = 0; i < effect.size(); i++) {
			if (effect.get(i).getName().equals("Shield"))
				effect.remove(i);
		}*/
	}

}
