import java.util.*;
import java.io.*;

public class Map {
	private char[][] map;
	private boolean [][] revealed;
	private static Map instance = null;
	
	
	private Map(){
		map  = new char[5][5];
		revealed = new boolean[5][5];
		
		for (int i = 0; i < 5; i++){
			for (int k = 0; k < 5; k++){
				revealed[i][k] = false;
			}
		}
		
	}
	
	public static Map getInstance(){
		if (instance == null){
			instance = new Map();
		}
		return instance;
	}
	
	public void loadMap(int mapNum){
		Scanner inFile = null;
		File file;
		int counter = 0;
		
		if (mapNum == 1){
			file = new File("Map1.txt");
			try{
			inFile = new Scanner(file);	
			}catch(Exception e){
				System.out.println("Error loading Map1.txt");
			}

		}
		if(mapNum == 2){
			file = new File("Map2.txt");
			try{
				inFile = new Scanner(file);	
				}catch(Exception e){
					System.out.println("Error loading Map2.txt");
				}
		}
		if(mapNum == 3){
			file = new File("Map3.txt");
			try{
				inFile = new Scanner(file);	
				}catch(Exception e){
					System.out.println("Error loading Map3.txt");
				}
		}
		
		while(inFile.hasNext()){
			String curr = inFile.nextLine();
			String currArr[] = curr.split(" ");
			
			for (int i = 0; i < 5; i++){
				//converting string to char and storing it
				map[counter][i] = currArr[i].charAt(0);
			}
			counter++;
		}
		
		inFile.close();
	}

	public char getCharAtLoc (Point p){
		int x = p.xCoor;
		int y = p.yCoor;
		
		return map[x][y];
	}
	
	public void displayMap(Point p){
		char mapDisplay[][] = new char[5][5];
		reveal(p);
		
		for(int row = 0; row < 5; row++){
			for(int column = 0; column < 5; column++){
				//if location was visited and enemy was killed reveal 'n'
				if(revealed[row][column] == true){
					mapDisplay[row][column] = map[row][column];
				}else{
				//neither than reveal 'x'
				mapDisplay[row][column] = 'x';
				}
			}
		}
		
		
		mapDisplay[p.xCoor][p.yCoor] = '*';
		
		
		for(int row = 0; row < 5; row++){
			for(int column = 0; column < 5; column++){
				System.out.print(mapDisplay[row][column] + " ");
			}
			System.out.println();;
		}
		
		System.out.println("1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit");
		
		
	}
	
	public Point findStart(){
		Point startPos = null;
		
		for(int row = 0; row < 5; row++){
			for (int column = 0; column < 5; column++){
				if(map[row][column] == 's'){
					startPos = new Point(row, column);
				}
			}
		}
		
		return startPos;
	}
	
	public void reveal (Point p){
		int x = p.xCoor;
		int y = p.yCoor;
		
		
		revealed[x][y] = true;
		
	}

	
	public void removeCharAtLoc(Point p){
		int xCoor = p.xCoor;
		int yCoor = p.yCoor;
		
		this.map[xCoor][yCoor] = 'n';
	}

}
