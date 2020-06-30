import java.util.Random;
/**
 * Warlock Decorator Class that provides decorator for enemy
 * @author TerryNguyen
 *
 */
public class WarlockDecorator extends EnemyDecorator implements Magical {
/**
 * WarlockDecorator Constructor
 * @param c enemy object
 */
	public WarlockDecorator(Enemy c) {
		super(c, 1, "Warlock");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void attack(Entity e){
		super.attack(e);
		
		Random rand = new Random();
		int randomNum = rand.nextInt(3) + 1;
		
		switch(randomNum){
		case 1:
			int attackP1 = this.magicMissile();
			e.takeDamage(attackP1);
			break;
		case 2:
			int attackP2 = this.fireball();
			e.takeDamage(attackP2);
			break;
		
		case 3:
			int attackP3 = this.thunderclap();
			e.takeDamage(attackP3);
			break;
		}
	}
	/**
	 * Method that randomly return attack
	 * @return attack point
	 */
	@Override
	public int magicMissile() {
		Random rand = new Random();
		int randomNum = rand.nextInt(5) + 1;
		
		int attackPoint = this.getLevel()* randomNum;
		return attackPoint;
	}
	/**
	 * Method that randomly return attack
	 * @return attack point
	 */
	@Override
	public int fireball() {
		Random rand = new Random();
		int randomNum = rand.nextInt(6) + 1;
		
		int attackPoint = this.getLevel()* randomNum;
		return attackPoint;
	}
	/**
	 * Method that randomly return attack
	 * @return attack point
	 */
	@Override
	public int thunderclap() {
		Random rand = new Random();
		int randomNum = rand.nextInt(7) + 1;
		
		int attackPoint = this.getLevel()* randomNum;
		return attackPoint;
	}

}
