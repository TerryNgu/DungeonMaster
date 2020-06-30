/**
 * Item Class - Template for Items
 * @author Terry Nguyen
 */

/**
 * Template of items
 * @author TerryNguyen
 *
 */
public class Item implements Cloneable{
	/**
	 * Name of item
	 */
	private String name;
	/**
	 * Value of item
	 */
	private int value;
	/**
	 * Item Constructor
	 * @param n takes in item name
	 * @param v takes in item value
	 */
	public Item(String n, int v){
		name = n;
		value =v;
	}
	/**
	 * Item Constructor that takes in Item Object for cloning
	 * @param p and Item object
	 */
	public Item( Item p ) {
		if( p != null ) {
			value = p.value;
			name = p.name;
		}
	}
	@Override
	public Item clone(){
		return new Item(this);
	}
	/**
	 * Get name of Item
	 * @return item name
	 */
	String getName(){
		return name;
	}
	/**
	 * Get value item
	 * @return return value of item
	 */
	int getValue(){
		return value;
	}
}