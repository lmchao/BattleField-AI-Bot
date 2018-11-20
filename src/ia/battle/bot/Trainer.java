package ia.battle.bot;

import ia.battle.core.BattleField;
import ia.battle.core.ConfigurationManager;
import ia.battle.core.FieldCell;
import ia.battle.core.WarriorManager;
import ia.exceptions.RuleException;

public class Trainer extends WarriorManager {
	public Trainer() {
	}

	public String getName() {
		return "Picard";
	}

	public ia.battle.core.Warrior getNextWarrior() throws RuleException {
		
		int maxStats = ConfigurationManager.getInstance().getMaxPointsPerWarrior();
		int health = maxStats / 5; 
		int defense = maxStats / 5;
		int strength = maxStats / 5;
		int speed = maxStats / 5;
		int range = maxStats / 5;
		
		if (BattleField.getInstance().getTick() != 0 ){ 
			if (this.getCount()-1 < BattleField.getInstance().getEnemyData().getWarriorNumber()){
				health   = maxStats * 20 / 100;
				defense  = maxStats * 10 / 100; 
				strength = maxStats * 30 / 100;
				speed    = maxStats * 20 / 100;
				range    = maxStats * 20 / 100;
				
			};
			
			if (this.getCount()-1 > BattleField.getInstance().getEnemyData().getWarriorNumber()){
				health   = maxStats * 35 / 100;
				defense  = maxStats * 10 / 100; 
				strength = maxStats * 25 / 100;
				speed    = maxStats * 10 / 100;
				range    = maxStats * 20 / 100;
				
			};
			
		}
		//ConfigurationManager.getInstance()

		return new Klingon("Worf", health, defense, strength, speed, range);
	}

	public void remember(int damage, FieldCell source) {
		System.out.println(source);
	}
}