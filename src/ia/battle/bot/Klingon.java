package ia.battle.bot;

import java.util.ArrayList;

import ia.battle.core.BattleField;
import ia.battle.core.ConfigurationManager;
import ia.battle.core.FieldCell;
import ia.battle.core.WarriorData;
import ia.battle.core.actions.Action;
import ia.battle.misc.AStar;
import ia.battle.misc.Node;
import ia.exceptions.RuleException;

public class Klingon extends ia.battle.core.Warrior {

	public Klingon(String name, int health, int defense, int strength, int speed, int range) throws RuleException {
		super(name, health, defense, strength, speed, range);
		// TODO Auto-generated constructor stub
		maxMoveRange = (int) (speed / 5);
	}

	private int maxMoveRange;
	private boolean movedOnTurn = false;
	private static final int ASSUMED_HUNTER_RANGE = 5;


	@Override
	public Action playTurn(long tick, int actionNumber) {

		WarriorData ed = BattleField.getInstance().getEnemyData();
		if (ed.getInRange()) {
			return new ia.battle.core.actions.Attack(ed.getFieldCell());
		}

		ArrayList<FieldCell> path = getPath(getPosition(), ed.getFieldCell());
		// return new Move(path);

		int closerDistance = Integer.MAX_VALUE;
		FieldCell target = null;
		if (actionNumber == 0) {
			ArrayList<FieldCell> specialItems = new ArrayList<>();
			BattleField.getInstance().getSpecialItems().forEach(fieldCell -> specialItems.add(fieldCell));
			for (FieldCell specialItem : specialItems) {
				int distance = computeDistance(this.getPosition(), specialItem);
				if (closerDistance > distance && distance <= maxMoveRange) {
					closerDistance = distance;
					target = specialItem;
				}
			}
		}
		if (target != null) {
			movedOnTurn = true;
			return new Move(getPath(getPosition(), target));
		}

		WarriorData hd = BattleField.getInstance().getHunterData();
		if (hd.getInRange()) {
			// return new ia.battle.core.actions.Attack(hd.getFieldCell());
		}

		if (!movedOnTurn) {
			if (path.isEmpty()) {
				target = BattleField.getInstance().getFieldCell(getPosition().getX() - hd.getFieldCell().getX(),
						getPosition().getY() - hd.getFieldCell().getX());
				path = getPath(getPosition(), target);
			}

		}

		if (actionNumber == 2) {
			movedOnTurn = false;
		}

		System.out.println("------------------------------Skip------------------------------");
		// return new ia.battle.core.actions.Skip();
		return new Move(path);
		// return new ia.battle.core.actions.BuildWall(getPosition());

	}

	@Override
	public void wasAttacked(int damage, FieldCell source) {
		((Trainer) getWarriorManager()).remember(damage, source);
	}

	@Override
	public void enemyKilled() {

	}

	@Override
	public void worldChanged(FieldCell oldCell, FieldCell newCell) {

	}

	private int computeDistance(FieldCell source, FieldCell target) {
		int distance = 0;

		distance = Math.abs(target.getX() - source.getX());
		distance += Math.abs(target.getY() - source.getY());

		return distance;
	}

	private ArrayList<FieldCell> getPath(FieldCell source, FieldCell target) {
		ArrayList<FieldCell> path = new ArrayList<FieldCell>();
		if (!(source == null || target == null)) {
			AStar a = new AStar(ConfigurationManager.getInstance().getMapWidth(),
					ConfigurationManager.getInstance().getMapHeight());
			a.addClosedNodes(a.getNodesInRangeOfNode(new Node(BattleField.getInstance().getHunterData().getFieldCell()),
					ASSUMED_HUNTER_RANGE));

			a.findPath(source, target).forEach(node -> {
				path.add(BattleField.getInstance().getFieldCell(node.getX(), node.getY()));
			});
		}
		return path;
	}

}
