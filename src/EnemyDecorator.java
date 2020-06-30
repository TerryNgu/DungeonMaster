/**
 * Abstract Class Enemy Decorator, this provides a blueprint for all enemies decorators
 * @author TerryNguyen
 *
 */
public abstract class EnemyDecorator extends Enemy {
	/**
	 * Enemy object to used for initializing super class of this
	 */
	private Enemy comp;
	/**
	 * EnemyDecorator Constructor, initialzing Enemy Decorator
	 * @param c an enemy object
	 * @param hp amout of HP to be added to enemy
	 * @param type type of enemy decorator 
	 */
	public EnemyDecorator(Enemy c, int hp, String type){
		super(c.getName() + " " + type,c.getQuip(),c.getLevel(),c.getHp(),c.getItem());
		comp = c;
		comp.increaseMaxHP(hp);
	}
	
	
	@Override
	public void attack(Entity e) {
		comp.attack(e);
	}

}
