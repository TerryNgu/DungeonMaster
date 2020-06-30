import java.util.Random;
/**
 * Goblin class which provides goblin type enemy
 * @author TerryNguyen
 *
 */
public class Goblin extends Enemy {
	/**
	 * Goblin class construct 
	 * @param name name of monster
	 * @param quip quip of monster
	 * @param level level of hero
	 * @param hp hp of monster
	 * @param i item for monster
	 */
	public Goblin(int level, Item i){
		super("Goblin","Blarghh",level, 4, i);
	}
	/**
	 * Attack Method that apply damage to entity
	 * @parameter Objec that attack applies to
	 */
	@Override
	public void attack(Entity e) {
		
		Random rand = new Random();
		int randomNum = rand.nextInt(e.getLevel()) + 2;
		
		int attackPoint = randomNum * this.getLevel();
		
		e.takeDamage(attackPoint);
		
	}
}
