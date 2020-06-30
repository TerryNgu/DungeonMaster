/**
 * Magical interface that provides 3 attack
 * @author TerryNguyen
 *
 */
public interface Magical  {
	/**
	 * Magic menu
	 */
	static final String MAGIC_MENU = "1. Magic Missile\n 2.Fireball \n 3.Thunderclap";
	/**
	 * to be override magic missle
	 * @return damage
	 */
	int magicMissile();
	/**
	 * to be override fireball
	 * @return damage
	 */
	int fireball();
	/**
	 * to be override thunderclap
	 * @return damage
	 */
	int thunderclap();
}

