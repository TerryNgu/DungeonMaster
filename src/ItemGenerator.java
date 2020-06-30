import java.io.*;
import java.util.*;

/**
 * ItemGenerator Class - this class generate an ArrayList of items which is read in from a 
 * text file.
 * 
 * @author Terry Nguyen
 */

public class ItemGenerator{
	/**
	 * Instance of ItemGenerator object
	 */
	private static ItemGenerator instance = null;
	/**
	 * ArrayList of item objects
	 */
	private ArrayList<Item> itemList;
	
	/**
	 * ItemGenerator constructor that loads a list of items into an Array List
	 */
	private ItemGenerator(){
		itemList = new ArrayList();
		/**Scanner File object to scan through input file*/
		Scanner inFile = null;
		/**File Object to store file read-in*/
		File file = new File ("ItemList.txt");
		//Track item ArrayList elements
	
		
		
		//Try to scan input file, if not successful then exception is thrown
		try{
			inFile = new Scanner(file);
			
		}
		//catching exception
		catch(Exception e){
			System.out.println("Could not find ItemList file");
		}
		while (inFile.hasNext()){
			//variable to store the entire line
			String curLine = inFile.nextLine();
			//Stores the name of the item temp.
			String newWord = "";
			//last position of comma found
			int commaPos = 0;
			//Stores the value of the item temp.
			String newVal = "";
			
			
			//looping through the current line to separate the item name from the line
			for(int i = 0; i < curLine.length(); i++){
				if(curLine.substring(i,i+1).equals(",")){
					commaPos = i;
					break;
				}
				newWord += curLine.substring(i, i+1);	
			}
			for (int i = (commaPos + 1); i < curLine.length(); i++){
				newVal += curLine.substring(i, i + 1);
			}
			Item currItem = new Item (newWord, Integer.parseInt(newVal));
			
			itemList.add(currItem);
		
			
		}
		
	}
	
	/**
	 * Method that randomly generate a item from Array List
	 * @return returns a random item
	 */
	
	public static ItemGenerator getInstance(){
		if (instance == null){
			instance = new ItemGenerator();
		}
		return instance;
	}
	/**
	 * Generate random item and returns it
	 * @return random item generated
	 */
	Item generateItem(){
		int arrSize = itemList.size();
		Random rand = new Random();
		int randomNum = rand.nextInt(arrSize);
		
		Item clone = new Item(itemList.get(randomNum));
		
		return  clone;
				
		
	}
	/**
	 * Method to get potion
	 * 
	 * @return returns potion 
	 */
	Item getPotion(){
		int potPosition = 0;

		for (int i = 0; i < itemList.size(); i++){
			Item currItem = itemList.get(i);
			String name = currItem.getName();
			
			if(name.equals("Health Potion")){
				potPosition = i;
			}
		}
		return itemList.get(potPosition);
		
	}

}