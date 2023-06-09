package model.world;

import java.util.ArrayList;

public class Villain extends Champion {

	public Villain(String name, int maxHP, int mana, int maxActions, int speed, int attackRange, int attackDamage) {
		super(name, maxHP, mana, maxActions, speed, attackRange, attackDamage);
	}

	public void useLeaderAbility(ArrayList<Champion> targets) {
		for (int i = 0; i < targets.size(); i++) {
			Champion tmp = targets.get(i);
			if(tmp==null)
				return;
			tmp.setCondition(Condition.KNOCKEDOUT);
			tmp.setCurrentHP(0);
		}
	}
}
