package ia.battle.bot;

import ia.battle.core.BattleField;
import ia.battle.core.ConfigurationManager;
import ia.battle.core.FieldCell;
import ia.battle.core.WarriorData;
import ia.battle.core.actions.Action;
import ia.exceptions.RuleException;

public class Klingon extends ia.battle.core.Warrior {

	public Klingon(String name, int health, int defense, int strength, int speed, int range) throws RuleException {
		super(name, health, defense, strength, speed, range);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Action playTurn(long tick, int actionNumber) {
		WarriorData ed = BattleField.getInstance().getEnemyData();
		if (ed.getInRange()) {
			return new ia.battle.core.actions.Attack(ed.getFieldCell());
		}
		
		;
		BattleField.getInstance().getAdjacentCells(getPosition());
		return new AStarMove(getPosition(), ed.getFieldCell(), (int)(this.getSpeed() / 5));
		//return new MovimientoDummy(getPosition(), 1, 2);
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

}
