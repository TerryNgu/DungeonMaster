import java.util.Random;
/**
 * Froglok Type of Enemy
 * @author TerryNguyen
 *
 */
public class Froglok extends Enemy {
	/**
	 * Froglok class construct 
	 * @param name name of monster
	 * @param quip quip of monster
	 * @param level level of hero
	 * @param hp hp of monster
	 * @param i item for monster
	 */
	public Froglok(int level, Item i){
		super("Froglok","Croooak",level, 6, i);

	}
	/**
	 * Attack Method that apply damage to entity
	 * @parameter Objec that attack applies to
	 */
	@Override
	public void attack(Entity e) {
		
		Random rand = new Random();
		int randomNum = rand.nextInt(e.getLevel()) + 3;
		
		int attackPoint = randomNum * this.getLevel();
		
		e.takeDamage(attackPoint);
		
	}
	
}
