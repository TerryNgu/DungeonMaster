import java.util.*;
import java.io.*;
import java.util.Random;

/**
 * Class to help generator and load enemy from a list
 * 
 * @author TerryNguyen 
 */

public class EnemyGenerator {
	/**
	 * Instance of EnemyGenerator, singleton design
	 */
	private static EnemyGenerator instance = null;
	/**
	 * ItemGenerator Object which will generator an item for enemy
	 */
	private ItemGenerator ig;
	
	/**
	 * EnemyGenerator constructor that loads a list of enemy into a Array List
	 * 
	 * @param i An ItemGenerator object to help allocate an item to a generated enemy
	 */
	private EnemyGenerator (){
		ig  = ItemGenerator.getInstance();
	}
	
	/**
	 * Method that make new instance or return the existence instance if one has
	 * already been created
	 * @return
	 */
	public static EnemyGenerator getInstance(){
		if (instance == null){
			instance = new EnemyGenerator();
		}
		return instance;
	}
	
	/**
	 * gtneratEnemy class to random generator and enemy from Array List
	 * 
	 * @param level takes in hero's level so produce the right level enemy
	 * @return returns a random enemy
	 */
	
	public Enemy generateEnemy(int heroLevel){
		Random rand = new Random();
		int randomNum = rand.nextInt(4) + 1;
		int randomNum2 = rand.nextInt(2)+ 1;
		
		EnemyDecorator decorator = null;
		Enemy baseEnemy = null;
		
		switch(randomNum){
			case 1:
				baseEnemy = new Troll(heroLevel, ig.generateItem());
				break;
			case 2:
				baseEnemy = new Orc(heroLevel, ig.generateItem());
				break;
			case 3:
				baseEnemy = new Goblin(heroLevel, ig.generateItem());
			case 4:
				baseEnemy = new Froglok(heroLevel, ig.generateItem());
		}
		
		
		if(heroLevel == 1){
			return baseEnemy;
		}
		
		for(int i = 0; i < heroLevel; i++){
			if(randomNum2 == 1){
				decorator = new WarriorDecorator(baseEnemy);
			}
			if(randomNum2 == 2){
				decorator = new WarlockDecorator(baseEnemy);
			}
		}
		return baseEnemy;
			
	}
		
}


