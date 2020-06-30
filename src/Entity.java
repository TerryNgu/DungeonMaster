/**
 * Super Class of Hero and Enemy
 * @author TerryNguyen
 *
 */
abstract public class Entity {
		private String name;
		private String quip;
		private int level;
		private int maxHP;
		private int hp;
		/**
		 * Entity Constructor that generate an enemy
		 * 
		 * @param n entity name
		 * @param q entity quip
		 * @param l entity level
		 * @param mP entity Max HP
		 */

		public Entity(String n, String q, int l, int mp){
			name = n;
			quip = q;
			level = l;
			maxHP = mp;
			hp = mp;
			
		}
		/**
		 * Abstract attack method that will be override by enemy and hero
		 * @param e
		 */
		abstract void attack(Entity e);
		/**
		 * Get name of entity
		 * @return return name
		 */
		public String getName(){
			return name;
		}
		/**
		 * Get entity quip
		 * @return return quip
		 */
		public String getQuip(){
			return quip;
		}
		/**
		 * Get level of entity
		 * @return return level
		 */
		public int getLevel(){
			return level;
		}
		/**
		 * Get entity HP
		 * @return return HP
		 */
		public int getHp(){
			return hp;
		}
		/**
		 * Get Max Hp 
		 * @return return HP
		 */
		public int getMaxHP(){
			return maxHP;
		}
		/**
		 * Increase level of entity
		 * 
		 */
		public void increaseLevel(){
			level++;
			increaseMaxHP(9);
			hp = maxHP;
			
		}
		/**
		 * Increase Health
		 * @param h - amount heal
		 */
		public void heal(int h){
			hp += h;
		}
		/**
		 * Damage entity
		 * @param amount of damage
		 */
		public void takeDamage(int h){
			hp-= h;
		}
		/**
		 * Increase Max HP
		 * @param amount of increase
		 */
		public void increaseMaxHP(int h){
			maxHP += h;
		}
		/**
		 * Decrease Max HP
		 * @param amount of decrease
		 */
		public void decreaseMaxHP(int h){
			int newMaxHP = maxHP - h;
			
			if(newMaxHP >= this.getHp()){
				maxHP -= h;
			}else{
				maxHP = newMaxHP;
				hp = newMaxHP;
			}
			
			
		}
	
		/**
		 * Display Name, Level, and HP
		 */
		public void display(){
			System.out.println(this.getName() + " Lvl:" + this.getLevel());
			System.out.println("HP: " + this.getHp() + "/" + this.getMaxHP()) ;
		}
}
