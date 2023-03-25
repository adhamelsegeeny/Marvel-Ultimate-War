package views;

import model.world.Champion;
import model.world.Direction;

public interface GameListener {
	public void onAttack(Direction d);
	public void onCast();
	public void onLeaderAbility();
	public void onMove(Direction d);
	public void onEndTurn();
	public void prepareChampions();
	public void onSetTeam(Champion c);
	
	

}
