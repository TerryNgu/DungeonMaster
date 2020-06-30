import java.util.ArrayList;
import java.util.Random;
/**
 * Class Of the Hero
 * @author TerryNguyen
 *
 */

public class Hero extends Entity implements Magical {

	private ArrayList<Item> items;
	private Map map = null;
	private Point location = null;
	private int gold;
	
	/**
	 * Hero Constructor method 
	 * @param n name of hero
	 * @param q quip of hero
	 * @param m Map that hero is in
	 */
	public Hero(String n, String q, Map m ){
		super(n,q,1,15);
		map = m;
		location = m.findStart();
		gold = 100;
		items = new ArrayList();
	}
	/**
	 * Method that displays the Hero Name, Level, HP, Gold, and inventory
	 */
	public void display(){
		super.display();
		System.out.println("$: " + gold);
		this.displayItems();
	}
	/**
	 * Display Hero Items
	 */
	public void displayItems(){
		int itemsNum = items.size();
		int itemsCount = 1;
		
		System.out.println("Inventory: ");
		
		while (itemsNum != 0){
			System.out.println(itemsCount + ". " + items.get(itemsCount - 1).getName());
			itemsCount ++;
			itemsNum --;
		}
	}
	/**
	 * Get amount of number of items in inventory
	 * @return return count of items
	 */
	public int getNumItems(){
		return items.size();
	}
	/**
	 * Method to pick up item
	 * @param i item to pick up
	 * @return return item picked up
	 */
	public boolean pickUpItem(Item i){
		if(i.getName().equals("Bag o' Gold")){
				gold += i.getValue();
		}
		else{
			items.add(i);
		}
		
		return true;
	}
	/**
	 * Method to remove an item by index for inventory
	 * @param i index of item to remove
	 * @return return removed item
	 */
	public Item removeItem(int i){
		
		Item curr = items.get(i);
		items.remove(i);
	
		return curr;
	}
	/**
	 * Method to remove item by string name
	 * @param n name of item to remove
	 * @return return item remove
	 */
	public Item removeItem(String n){
		
		Item currItem = null;
		boolean itemRemoved = false;
		
		for (int i = 0; i < items.size(); i++){
			if(items.get(i).getName().equals(n)){
				currItem = items.get(i);
				items.remove(i);
				itemRemoved = true;
				}
		}
		if(!itemRemoved){
			System.out.println("Item trying to remove was not found");
		}
		return currItem;
	}
	
	
	/**
	 * Let players know if there is potion available 
	 * @return return true if there's potion
	 */
	public boolean hasPotion(){
		int itemNum = this.getNumItems();
		
		for (int i = 0; i < itemNum; i++){
			
			String currItem = items.get(i).getName();
			
			if(currItem.equals("Health Potion")){
				return true;
			}
		}
		
		return false;
	}
	/**
	 * Method get location of hero
	 * @return return location of hero
	 */
	public Point getLocation(){
		return location;
	}
	/**
	 * go north
	 * @return return point
	 */
	public char goNorth(){
	
			Point newLocation = new Point(location.xCoor - 1, location.yCoor);
			location = newLocation;
			map.reveal(location);
			return map.getCharAtLoc(location);
	}
	/**
	 * go north
	 * @return return point
	 */
	public char goSouth(){
		
			Point newLocation = new Point(location.xCoor + 1, location.yCoor);
			location = newLocation;
			map.reveal(location);
			return map.getCharAtLoc(location);
	}
	/**
	 * go east
	 * @return return east
	 */
	public char goEast(){
		
			Point newLocation = new Point(location.xCoor, location.yCoor + 1);
			location = newLocation;
			map.reveal(location);
			return map.getCharAtLoc(location);
	}
	/**
	 * go west
	 * @return return west
	 */
	public char goWest(){
		
			Point newLocation = new Point(location.xCoor , location.yCoor - 1);
			location = newLocation;
			map.reveal(location);
			return map.getCharAtLoc(location);
	}
	/**
	 * Method get gold
	 * @return return amount
	 */
	
	public int getGold(){
		return gold;
	}
	/**
	 * Put gold in inventory
	 * @param g amount of gold
	 */
	public void collectGold(int g){
		gold += g;
	}
	/**
	 * Spend gold when buying potion
	 * @param g amount spent
	 */
	public void spendGold(int g){
		gold -= g;
	}
	
	
	@Override
	void attack(Entity e) {
		Random rand = new Random();
		int randomNum = (rand.nextInt(2) + 1);
		
		int damageDealt = randomNum*this.getLevel();
		
		e.takeDamage(damageDealt);
	}
		
	@Override
	public int magicMissile() {
		Random rand = new Random();
		int randomNum = rand.nextInt(3) + 1;
		
		int damageDealt = randomNum*this.getLevel();
		
		return damageDealt;
	}

	@Override
	public int fireball() {
		Random rand = new Random();
		int randomNum = rand.nextInt(4) + 1;
		
		int damageDealt = randomNum*this.getLevel();
		
		return damageDealt;
	}

	@Override
	public int thunderclap() {
		Random rand = new Random();
		int randomNum = rand.nextInt(5) + 1;
	
		int damageDealt = randomNum*this.getLevel();
		
		return damageDealt;
	}

}
