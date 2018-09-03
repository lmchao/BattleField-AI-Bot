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
	private int maxMoves = Integer.MAX_VALUE;
	private AStar a;
	private static final int ASSUMED_HUNTER_RANGE = 5;
	public AStarMove(FieldCell cellFrom, FieldCell cellTo) {
		super();
		this.cellFrom = cellFrom;
		this.cellTo = cellTo;
		setAStar();
	}
	public AStarMove(FieldCell cellFrom, FieldCell cellTo, int maxMoves) {
		this.cellFrom = cellFrom;
		this.cellTo = cellTo;
		this.maxMoves = maxMoves;
		setAStar();
	}
	
	private void setAStar(){
		this.a = new AStar(ConfigurationManager.getInstance().getMapWidth(), ConfigurationManager.getInstance().getMapHeight());
	}
	
	private void blockHunterCells(){
		a.addClosedNodes(a.getNodesInRangeOfNode(new Node(BattleField.getInstance().getHunterData().getFieldCell()), ASSUMED_HUNTER_RANGE));
	}
	
	private void prioritizeSpecialItemCells(){
		ArrayList<Node> specialItems = new ArrayList<>();
		BattleField.getInstance().getSpecialItems().forEach(fieldCell -> specialItems.add(new Node(fieldCell)));
		a.prioritizeNodes(specialItems);
	}
	
	@Override
	public ArrayList<FieldCell> move() {
		return moveAvoidingHunter();
	}
	
	private ArrayList<FieldCell> moveDefault(){
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
	
	public ArrayList<FieldCell> moveAvoidingHunter(){
		blockHunterCells();
		prioritizeSpecialItemCells();
		return moveDefault();
	}
}
