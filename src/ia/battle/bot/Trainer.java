package ia.battle.bot;

import ia.battle.core.ConfigurationManager;
import ia.battle.core.FieldCell;
import ia.battle.core.WarriorManager;
import ia.exceptions.RuleException;

public class Trainer extends WarriorManager {
	public Trainer() {
	}

	public String getName() {
		return "Dwayne";
	}

	public ia.battle.core.Warrior getNextWarrior() throws RuleException {
		int maxStats = ConfigurationManager.getInstance().getMaxPointsPerWarrior();

		int h = maxStats / 5;
		int d = maxStats / 5;
		int s = maxStats / 5;
		int a = maxStats / 5;
		int r = maxStats / 5;
		
		//ConfigurationManager.getInstance()

		return new Klingon("Darf", h, d, s, a, r);
	}

	public void remember(int damage, FieldCell source) {
		System.out.println(source);
	}
}