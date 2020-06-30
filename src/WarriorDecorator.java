/**
 * Warrior Decorator class that provides decorator for enemy
 * @author TerryNguyen
 *
 */
public class WarriorDecorator extends EnemyDecorator{
	/**
	 * Warrior Decorator Constructor
	 * @param c object of enemy
	 */
	public WarriorDecorator(Enemy c) {
		super(c, 2, "Warrior");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void attack(Entity e){
		super.attack(e);
		e.takeDamage(5);
	}

}
