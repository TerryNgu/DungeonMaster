/**
 * Abstract Enemy class, it provides a blueprint for all enemies
 * @author TerryNguyen
 *
 */
public abstract class Enemy extends Entity {
	/**
	 * Item that is assigned to enemy
	 */
	private Item item;
	/**
	 * Enemy Constructor
	 * @param n name of enemy
	 * @param q quip of enemy
	 * @param l level of enemy
	 * @param mp maxhp of enemy
	 * @param i item of enemy
	 */
	public Enemy(String n, String q, int l, int mp, Item i) {
		super(n, q, l, mp);
		item = i;
	}
	/**
	 * Method that returns the enemy item
	 * @return
	 */
	public Item getItem(){
		return item;
	}
	/**
	 * Abstract Method attack that has to be override by supclass of enemy
	 */
	public abstract void attack(Entity e);
}
