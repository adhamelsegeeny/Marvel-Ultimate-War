package model.effects;

import model.world.Champion;

public abstract class Effect implements Cloneable {
	private String name;
	private int duration;
	private EffectType type;

	public Effect(String name, int duration, EffectType type) {
		this.name = name;
		this.duration = duration;
		this.type = type;

	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		if (duration < 0)
			this.duration = 0;
		else
			this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public EffectType getType() {
		return type;
	}
	
	public String toString() {
		return "Name: "+ name + '\n'+ "Duration: "+ duration + '\n' + "EffectType: "+ type;
	}

	public abstract void apply(Champion c);

	public abstract void remove(Champion c);

	public Object clone() throws CloneNotSupportedException {
		return (Effect) super.clone();

	}
}
