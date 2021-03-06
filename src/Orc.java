import java.util.Random;

/**
 * Orc class type of enemy
 * @author TerryNguyen
 *
 */
public class Orc extends Enemy{

	/**
	 * Orc class construct 
	 * @param name name of monster
	 * @param quip quip of monster
	 * @param level level of hero
	 * @param hp hp of monster
	 * @param i item for monster
	 */
	public Orc(int level, Item i){
		super("Orc","Blarghh",level, 4, i);
	}
	/**
	 * Attack Method that apply damage to entity
	 * @parameter Objec that attack applies to
	 */
	@Override
	public void attack(Entity e) {
		
		Random rand = new Random();
		int randomNum = rand.nextInt(e.getLevel()) + 4;
		
		int attackPoint = randomNum * this.getLevel();
		
		e.takeDamage(attackPoint);
		
	}
}
