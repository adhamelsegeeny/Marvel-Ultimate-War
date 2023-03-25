package model.world;

import java.util.ArrayList;

import model.effects.Stun;

public class AntiHero extends Champion{
	
	public AntiHero(String name, int maxHP, int mana, int maxActions, int speed, int attackRange, int attackDamage) {
		super(name,maxHP,mana,maxActions,speed,attackRange,attackDamage);
		
	}
	
	public void useLeaderAbility(ArrayList<Champion> targets){
		for (int i = 0; i < targets.size(); i++) {
			Champion tmp=targets.get(i);
			Stun stun= new Stun(2);
			stun.apply(tmp);
			tmp.getAppliedEffects().add(stun);

			
		}
	}
}
