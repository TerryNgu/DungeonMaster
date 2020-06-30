import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
/**
 * Game: Hero must navigate through map and kill monster, collect gold, and items along the 
 * 		 until Hero reach finish point.
 * 
 * @author TerryNguyen 2018
 */



public class Main {
	/**
	 * Hero enters monsterRoom and get an option to fight the monster
	 * 
	 * @param h - Hero Object
	 * @param m - map Object
	 * @param eg - EnemyGenerator Object
	 * @return return true if enemy was killed in monsterRoom, otherwise return false
	 */
	
	static boolean monsterRoom(Hero h, Map m, EnemyGenerator eg){
		Enemy monster = eg.generateEnemy(h.getLevel());
		String monsterName = monster.getName();
		boolean monsterAlive = true;
		boolean runAway = false; 
		int userInput1;
		
		int heroHP = h.getHp();
		int monsterHP = monster.getHp();
		
		System.out.println("You've encounter a " + monsterName);
	
		
		while (monsterAlive && !runAway){
			
			if (monsterHP == 0){
				monsterAlive = false;
				break;
			}
			
			monster.display();
			
			System.out.println("1. Fight\n2.Run Away");
			userInput1 = CheckInput.getIntRange(1,2);
			
			if (userInput1 == 1){
				monsterAlive = fight(h,monster);
			}
			
			//hero died, leave loop
			if (h.getHp() <= 0){
				runAway = true;
				break;
			}
			
			if(userInput1 == 2){
				int xCoor = h.getLocation().xCoor;
				int yCoor = h.getLocation().yCoor;
				
				//checking if hero is in a corner or at a wall
				
				//top left corner 
				if(xCoor == 0 && yCoor == 0){
					h.goSouth();
				}
				//top right corner
				else if(xCoor == 0 && yCoor == 4){
					h.goSouth();
				}
				//bottom left corner
				else if(xCoor == 4 && yCoor == 0){
					h.goNorth();
				}
				//bottom right corner
				else if(xCoor == 4 && yCoor == 4){
					h.goWest();
				}
				
				//left wall
				else if(yCoor == 0){
					h.goEast();
				}
				
				//right wall
				else if(yCoor == 4){
					h.goWest();
				}
				//top wall
				else if(xCoor == 0){
					h.goSouth();
				}
				//bottom wall
				else if(xCoor == 4){
					h.goNorth();
				}
				//if none then go east
				else{
					h.goEast();
				}
				
				runAway = true;
				
			}
		}
		
		// return true if monster died and hero leaves monster room
		if (!monsterAlive && !runAway){
			System.out.println("You defeated the " + monster.getName() + "!");
			//received monster item
			if(h.getNumItems() < 5){
				//if item is not a gold then add it to hero's inventory
				if(!monster.getItem().getName().equals("Bag o' Gold")){
					System.out.println("You received a " + monster.getItem().getName() + 
							" from its corpse.");
					//adding Items picked up all more HP
					//if not then add more HP according to item
					if (monster.getItem().getName() != "Health Potion"){
						h.increaseMaxHP(monster.getItem().getValue()/2);
					}
					h.pickUpItem(monster.getItem());
					
				}
			}
			
				//if monster item is gold then add to inventory
			if(monster.getItem().getName().equals("Bag o' Gold")){
					h.pickUpItem(monster.getItem());
					System.out.println("You received $" + monster.getItem().getValue() + " gold from its corpse" );
			}
			
				//monster was killed, put "n" at location the fight occured
				m.removeCharAtLoc(h.getLocation());
				return true;
			}
		
		//return false if monster is still alive
		return false;
}
	/**
	 * Method used in monsterRoom to prompt a fight between Hero and Enemy
	 * 
	 * @param h Hero Object 
	 * @param e Enemy Object
	 * @return return false if monster did not die in fight, therefore requires hero to fight again
	 */
	static boolean fight(Hero h, Enemy e){

		boolean hasPotion = h.hasPotion();
		Random rand = new Random();
		int randomNum1 = rand.nextInt(3);
		
		String hero_Laugh = h.getName() + " laughed, and yelled " + h.getQuip() + "!!!!";
		String hero_FeelPain = h.getName() + " terrifyingly exclaims " + "\"" + h.getQuip() + "\"";
		String monster_Scream = e.getName() + " frightfully shreiks " + "\"" + e.getQuip() + "\"";
		String monster_Laugh = e.getName() + " laugh out loud and let out a " + "\""+ e.getQuip() + "\"";
		
		int heroHP_beforeAttack = h.getHp();
		int heroHP_afterAttack = 0;
		int damageOnHero = 0;
		
		int monsterHP_beforeAttack = e.getHp();
		int monsterHP_afterAttack = 0;
		int damageOnMonster = 0;
		
		int userInput3;
		
		System.out.println("1. Physical Attack\n2. Magic Attack");
		
		//setting userInput range based on if player have or does not have potion
		if(hasPotion){
			System.out.println("3. Use Potion");
			userInput3 = CheckInput.getIntRange(1, 3);
		}else{
			userInput3 = CheckInput.getIntRange(1, 2);
		}
		System.out.println("---------------------------------------");
		//physical attack
		if (userInput3 == 1){
			//hero attack monster
			h.attack(e);
			
			monsterHP_afterAttack = e.getHp();
			damageOnMonster = monsterHP_beforeAttack - monsterHP_afterAttack;
			
			
		
			System.out.println(h.getName() + " attacks " + e.getName() + " for " + damageOnMonster + " hitpoints");
			
			//monster attack hero if monster did not died
			if (e.getHp() > 0){
				e.attack(h);
				heroHP_afterAttack = h.getHp();
				damageOnHero = heroHP_beforeAttack - heroHP_afterAttack;
			}
		}
		//magical attack
		if(userInput3 == 2){
			System.out.println("1. Magic Missle\n2. Fireball\n3. Thunderclap");
			int typeOfAttack = CheckInput.getIntRange(1, 3);
		
			
			switch (typeOfAttack){
			case 1: 
				int damage = h.magicMissile();
				e.takeDamage(damage);
				
				monsterHP_afterAttack = e.getHp();
				damageOnMonster = monsterHP_beforeAttack - monsterHP_afterAttack;
				
				System.out.println(h.getName() + " used Magic Missle on  " + e.getName() + " for " + damageOnMonster);
				
				//monster attack hero
				if (e.getHp() > 0){
					e.attack(h);
					heroHP_afterAttack = h.getHp();
					damageOnHero = heroHP_beforeAttack - heroHP_afterAttack;
				
				}
				break;
			case 2:
				int damage2 = h.fireball();
				e.takeDamage(damage2);
				
				monsterHP_afterAttack = e.getHp();
				damageOnMonster = monsterHP_beforeAttack - monsterHP_afterAttack;
				
				System.out.println(h.getName() + " hit " + e.getName() + " with Fireball and dealt " + damageOnMonster
						+ " damage");
				
				//monster attack hero
				if (e.getHp() > 0){
					e.attack(h);
					heroHP_afterAttack = h.getHp();
					damageOnHero = heroHP_beforeAttack - heroHP_afterAttack;
				}
				break;
			case 3:
				int damage3 = h.thunderclap();
				e.takeDamage(damage3);
				
				monsterHP_afterAttack = e.getHp();
				damageOnMonster = monsterHP_beforeAttack - monsterHP_afterAttack;
				
				System.out.println(h.getName() + " uses Thunderclap on " + e.getName() + ", and did " + damageOnMonster
						+ " damage");
				//monster attack hero
				if (e.getHp() > 0){
					e.attack(h);
					heroHP_afterAttack = h.getHp();
					damageOnHero = heroHP_beforeAttack - heroHP_afterAttack;
				}
				break;
			}
		}
		
		if(userInput3 == 3){
				//add 25 HP to hero, only heals up to maxHP
				h.heal(25);
				if(h.getMaxHP() < h.getHp()){
					int hpDiff = h.getHp() - h.getMaxHP();
					h.takeDamage(hpDiff);
					System.out.println("You recovered " + hpDiff);
				}else{
					System.out.println("You recovered 25 HP");
				}
				h.removeItem("Health Potion");
			
		}
		
		
		//Line prompt how much damage player took if monster is not dead
		if(e.getHp() > 0){
			System.out.println(e.getName() + " hits " + h.getName() + " for " + damageOnHero + " damage");
		}
			
		//hero died
		if (h.getHp() <= 0){
			return true;
		}
	
		//Hero randomly let out a battle cry
		if(randomNum1 == 0){ 
			System.out.println(hero_Laugh);
			if(e.getHp() > 0){
					System.out.println(monster_Scream);
				}
		}
		if(randomNum1 == 1){
				System.out.println(hero_FeelPain);
		}
		if(randomNum1 == 2){
			if(e.getHp() > 0){
					System.out.println(monster_Laugh);
			}
		}
		System.out.println("---------------------------------------");
		System.out.println("\n" + h.getName() + " HP: " + h.getHp()+ "/" + h.getMaxHP());
		
		//if monster died return false, else return true. Logic is in battle function
		if(e.getHp() <= 0){
			return false;
		}
		return true;
	
	}
	
	
	static void itemRoom(Hero h, Map m, ItemGenerator ig){
		int numItems = h.getNumItems();
		Item roomItem = ig.generateItem();
		
		if(numItems < 5){
			
				h.pickUpItem(roomItem);
				m.removeCharAtLoc(h.getLocation());
				System.out.println("You picked up a " + roomItem.getName());
			
		}
		
	}
	static void store (Hero h, ItemGenerator ig){

		Item potion = ig.getPotion();
		boolean keepShopping = true;
		Item removedItem;
		
		while(keepShopping){
			System.out.println("Welcome to WalMart " + h.getName() +
					"\n$: " + h.getGold() + "\n\n1. Buy Potions\n2. Sell Items\n3. Exit");
		
			int userInput =  CheckInput.getIntRange(1, 3);
			//buying Potion
			if(userInput == 1){
				if(h.getNumItems() < 5 && h.getGold() >= 25){
					h.pickUpItem(potion);
					System.out.println("::You successfully bought a potion for $25\n");
					h.spendGold(25);
				}
				else if(h.getGold() < 25){
					System.out.println("::You don't have enough money for a potion\nPotion cost $25\n");
				}
				else if(h.getNumItems() == 5){
					System.out.println("::Your inventory is full\n");
				}
			}
				//selling items
			if(userInput == 2){
				if(h.getNumItems() == 0){
					System.out.println("::You have nothing to sell....\n");
				}else{
					boolean wantToKeepSelling = true;
					int itemSoldFor = 0;
					
					System.out.println("Choose an item to sell:");
					h.displayItems();
					System.out.println("6. Exit");
					int userIn2 = CheckInput.getIntRange(0, h.getNumItems());
					
				
					if (userIn2 == 6){
						wantToKeepSelling = false;
					}
					
					if (wantToKeepSelling){
						int itemIndex = userIn2 - 1;
				
						removedItem = h.removeItem(itemIndex);
						
						//if item want to sell is Health Potion then regular selling price
						//else item sell price is double 
						if(removedItem.getName().equals("Health Potion")){
							itemSoldFor = 25;
						}else{
							itemSoldFor = removedItem.getValue() * 2;
						}
						
						h.collectGold(itemSoldFor);
						h.decreaseMaxHP(removedItem.getValue()/2);
						
						System.out.println("::You successfully sold " + removedItem.getName() + " for $" + itemSoldFor + "\n");
				}
			}
		}
			
		if(userInput == 3){
				keepShopping = false;
			}
		}
	}
	
	/**
	 * main method : Where the game ties the all the classes and methods togther 
	 * @param args
	 */
	public static void main(String[]args){
		
		boolean wantToKeepPlaying = true;
		boolean mapNotBeat = true;
		boolean protectedObject = true;
		int userInput;
		
		//getting ItemGeration instance
		//Singleton design
		ItemGenerator itemGen = ItemGenerator.getInstance();
		Hero hero = null;
		
		Random rand = new Random();
		 
	
		System.out.print("What is your name, traveler? ");
		String name = CheckInput.getString();
		
		System.out.print("What is your battlecry, " + name + " ");
		String quip = CheckInput.getString();
		
		//loop will continue until hero chose to quit
		//if player does not selected quit 
		while (wantToKeepPlaying){
			 	//Singleton design
				Map gameMap = Map.getInstance();
				int randNum = rand.nextInt(3) + 1;
				gameMap.loadMap(randNum);
				
				//can only enter this if statement once, if statement retains hero's integrity throughout the game
				//as the level changes
				if(protectedObject){
					hero = new Hero(name, quip, gameMap);
					protectedObject = false;
				}
				//variable was set to true when player reached finish point, resetting the booleaning
				mapNotBeat = true;
			
				//while game is not beat	
				while (mapNotBeat && wantToKeepPlaying){
					EnemyGenerator enemyGen = EnemyGenerator.getInstance();
					boolean enteredBattleRoom = false;
					boolean reenteredStartP = false;
					boolean itemFound = false;
					boolean atFinishP = false;
					boolean wrongInput = true;
			
					
					hero.display();
					gameMap.displayMap(hero.getLocation());
					
					do{
						//get user input
						userInput = CheckInput.getIntRange(1, 5);
					
					
						switch(userInput){
							case 1:
								if (hero.getLocation().xCoor != 0){
									hero.goNorth();
									wrongInput = false;
									break;
								}
								
							case 2:
								if(hero.getLocation().xCoor != 4 && userInput == 2){
									hero.goSouth();
									wrongInput = false;
									break;
								}
								
							case 3:
								if(hero.getLocation().yCoor != 4 && userInput == 3){
									hero.goEast();
									wrongInput = false;
									break;
								}
								
							case 4:
								if(hero.getLocation().yCoor != 0 && userInput == 4){
									hero.goWest();
									wrongInput = false;
									break;
								}
							case 5:
								if(userInput == 5){
									wrongInput = false;
									wantToKeepPlaying = false;
									System.out.print("End GAME .........");
									continue;		
								}
							default:
								System.out.println("Cannot move outside of map");
								
							}
					
					}while(wrongInput);
			
					//break from while loop if user chose quit
					if (!wantToKeepPlaying){
						break;
					}
					
					//getting character at the location in which the hero just moved to
					char charAtLocation = gameMap.getCharAtLoc(hero.getLocation());
		
					switch (charAtLocation){
						case 'm' : 
							enteredBattleRoom = true;
							break;
						case 'i' :
							itemFound = true;
							break;
						case 's':
							reenteredStartP = true;
							break;
						case 'f':
							atFinishP = true;
							break;
						case 'n':
							break;
					}
		
					if(enteredBattleRoom){
						monsterRoom(hero,gameMap,enemyGen);
						
					}
					if(itemFound){
						itemRoom(hero,gameMap,itemGen);
					}
					if(reenteredStartP){
						store(hero, itemGen);
					}
					//player entered finish point
					if(atFinishP){
						hero.increaseLevel();
						System.out.println("CONGRATS! You Beat Level\nYou leveled up to "
							+ hero.getLevel() + "\n-----------");
						//reset map object so a new one can be made
						gameMap = null;
						mapNotBeat = false;
						break;
					}
					//hero died
					if (hero.getHp() <= 0){
						System.out.println("You Died");
						wantToKeepPlaying = false;
						break;
					}
					
			
			}
			
		
		}
	

	}
	
}

