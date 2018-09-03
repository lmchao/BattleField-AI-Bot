package ia.battle.bot;

import ia.battle.core.BattleField;
import ia.battle.core.ConfigurationManager;
import ia.battle.core.FieldCell;
import java.util.ArrayList;

public class MovimientoDummy extends ia.battle.core.actions.Move {
	private FieldCell from;
	private int stepX;
	private int stepY;

	public MovimientoDummy(FieldCell from, int stepX, int stepY) {
		this.from = from;
		this.stepX = stepX;
		this.stepY = stepY;
	}

	public ArrayList<FieldCell> move() {
		int x = from.getX();
		int y = from.getY();

		@SuppressWarnings({ "rawtypes", "unchecked" })
		ArrayList<FieldCell> path = new ArrayList();

		int to = x + stepX;
		for (; x < to; x++) {
			if (x < ConfigurationManager.getInstance().getMapWidth() - 1) {
				path.add(BattleField.getInstance().getFieldCell(x, y));
			} else {
				x--;
				break;
			}
		}
		to = y + stepY;
		for (; y < to; y++)
			if (y < ConfigurationManager.getInstance().getMapHeight() - 1) {
				System.out.println(x);
				System.out.println(y);
				path.add(BattleField.getInstance().getFieldCell(x, y));
			}
		return path;
	}
}