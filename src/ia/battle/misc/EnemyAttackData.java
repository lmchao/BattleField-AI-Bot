package ia.battle.misc;

import ia.battle.core.FieldCell;

public class EnemyAttackData {
	private int damage;
	private FieldCell source;
	public EnemyAttackData(int damage, FieldCell source) {
		this.setDamage(damage);
		this.setSource(source);
	}
	public int getDamage() {
		return damage;
	}
	private void setDamage(int damage) {
		this.damage = damage;
	}
	public FieldCell getSource() {
		return source;
	}
	public void setSource(FieldCell source) {
		this.source = source;
	}

}
