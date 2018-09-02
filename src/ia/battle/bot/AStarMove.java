package ia.battle.bot;

import java.util.ArrayList;

import ia.battle.core.BattleField;
import ia.battle.core.ConfigurationManager;
import ia.battle.core.FieldCell;
import ia.battle.misc.AStar;
import ia.battle.misc.Node;

class AStarMove extends ia.battle.core.actions.Move{
	private FieldCell cellFrom;
	private FieldCell cellTo;
	int maxMoves = Integer.MAX_VALUE;
	public AStarMove(FieldCell cellFrom, FieldCell cellTo) {
		super();
		this.cellFrom = cellFrom;
		this.cellTo = cellTo;
	}
	public AStarMove(FieldCell cellFrom, FieldCell cellTo, int maxMoves) {
		this.cellFrom = cellFrom;
		this.cellTo = cellTo;
		this.maxMoves = maxMoves;
	}
	@Override
	public ArrayList<FieldCell> move() {
		cellTo.getCost();
		AStar a = new AStar(ConfigurationManager.getInstance().getMapWidth(), ConfigurationManager.getInstance().getMapHeight());
		ArrayList<Node> bestPath = a.findPath(cellFrom, cellTo);
		a.mergePath(bestPath);
		//a.printMap();
		ArrayList<FieldCell> cellList = new ArrayList<FieldCell>();
		for(Node node : bestPath){
			cellList.add(BattleField.getInstance().getFieldCell(node.getX(), node.getY()));
			if (--maxMoves == 0) break;
		}
			
		return cellList;
	}
}
