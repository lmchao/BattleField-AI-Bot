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
				health   = maxStats * 70 / 100;
				defense  = maxStats * 05 / 100; 
				strength = maxStats * 05 / 100;
				speed    = maxStats * 15 / 100;
				range    = maxStats * 05 / 100;
				
			};
			
			if (this.getCount()-1 > BattleField.getInstance().getEnemyData().getWarriorNumber()){
				health   = maxStats * 15 / 100;
				defense  = maxStats * 10 / 100; 
				strength = maxStats * 35 / 100;
				speed    = maxStats * 05 / 100;
				range    = maxStats * 35 / 100;
				
			};
			
		}
		//ConfigurationManager.getInstance()

		return new Klingon("Worf", health, defense, strength, speed, range);
	}

	public void remember(int damage, FieldCell source) {
		System.out.println(source);
	}
}