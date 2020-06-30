import java.util.Random;

/**
 * Troll class type enemy
 * @author TerryNguyen
 *
 */
public class Troll extends Enemy{
	/**
	 * Troll class construct 
	 * @param name name of monster
	 * @param quip quip of monster
	 * @param level level of hero
	 * @param hp hp of monster
	 * @param i item for monster
	 */
	public Troll(int level,Item i){
		super("Troll","Ugh uh",level, 5, i);
	
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
