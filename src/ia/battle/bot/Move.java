package ia.battle.bot;

import java.util.ArrayList;

import ia.battle.core.FieldCell;

public class Move extends ia.battle.core.actions.Move {
	private ArrayList<FieldCell> path;
	public Move(ArrayList<FieldCell> path) {
		this.path = path;
	}
	@Override
	public ArrayList<FieldCell> move() {
		return path;
	}
}
