import java.util.*;
import java.util.Scanner;

class Computer {
    protected String deployMsg = "Computer is deploying ships:";
}
public class Battleship extends Location {
    public static Scanner input=new Scanner (System.in);
    public static Random rand = new Random();

    public static String[][] ocean= new String[10][10];//Create the main ocean map using a Single 2D array
    public static String[][] ocean2= new String[10][10];//create Ocean2 with computer ships,

    public static int playerShips=0;
    public static int computerShips=0;
    public void begin (){
        //Abstraction
        Battleship battleship =new Battleship();
        battleship.greetings();
        battleship.userLoc(ocean); //deploy user ships.
        battleship.compLoc(ocean); //deploy computer ships
        battleship.battle();
    }

    public void greetings(){
            //Encapsulation
            greetings myMsg1 = new greetings();
            myMsg1.setMsgName("\n**** Welcome to Battle Ships Game ****");
            System.out.println(myMsg1.getMsgName());
            //Encapsulation
            myMsg1.setMsgName("\nRight now, the sea is empty.\n");
            System.out.println(myMsg1.getMsgName());
            printMap(ocean);
        }

    public void printMap(String[][] ocean){
        System.out.println("\n  0123456789  ");

        for(int row =0; row<ocean.length;row++){ //Create the numbers on the grid:
            System.out.print(row+"|");
            for (int col=0; col<ocean[row].length;col++){
                if(ocean[row][col]==null){
                    System.out.print(" ");
                } else {
                    System.out.print(ocean[row][col]);
                }
            }
            System.out.println("|"+row);
        }
        System.out.println("  0123456789  ");
    }

    public static void printMap2(String[][] ocean2){

        System.out.println("OCEAN2");
        System.out.println("  0123456789  ");
        //Create the numbers on the grid:
        for(int row =0; row<ocean2.length;row++){
            System.out.print(row+"|");
            for (int col=0; col<ocean2[row].length;col++){
                if(ocean2[row][col]==null){
                    System.out.print(" ");
                } else {
                    System.out.print(ocean2[row][col]);
                }
            }
            System.out.println("|"+row);
        }
        System.out.println("  0123456789  ");
    }

    public void userLoc(String[][] ocean){
        //Polymorphism // (Inheritance)
        userAction actionMsg = new userAction();
        actionMsg.userMsg();

        while (playerShips<5){
            //setters and getters
            //Encapsulation
            userInput test = new userInput();
            System.out.print("Enter X coordinate for your " + (playerShips+1)+ ". ship: ");
            test.setCol(input.nextInt());
            System.out.print("Enter Y coordinate for your "+ (playerShips+1)+ ". ship: ");
            test.setRow(input.nextInt());

            int row = test.getRow();
            int col = test.getCol();

            if (row>9||col>9){//you can’t place ships outside the 10 by 10 grid
                System.out.println("The coordinate you entered is out of range, please try again");
            } else if(ocean [row][col]!=null) { //you can NOT place two or more ships on the same location
                System.out.println("The coordinate you entered is already used, please try again");
            } else {
                ocean [row][col]="@";
                playerShips++;
            }
        }
    }

    public void compLoc(String[][] ocean){
        Computer CompMsg = new Computer();
        System.out.println("\n\n" + CompMsg.deployMsg);

        while (computerShips<5){
            int row=rand.nextInt(10), col=rand.nextInt(10) ; //you can’t place ships outside the 10 by 10 grid

            if (ocean [row][col]==null&&ocean2 [row][col]==null){//you cannot place the ship on a location that is already taken by another ship (player’s or computer’s)
                System.out.println("Ship Deployed. ");
                ocean2[row][col] = "@";//place ships in Ocean2
                computerShips++;
            }
        }

        System.out.println("\n--------------------------------------\n");
    }

    public static void userTurn(){

        System.out.println("\nYOUR TURN");
        int turn=0;

        while (turn==0) {

            System.out.print("Enter X coordinate: ");
            int col = input.nextInt();
            System.out.print("Enter Y coordinate: ");
            int row = input.nextInt();


            if (row > 9 || col > 9) {//you can’t shot outside the 10 by 10 grid
                System.out.println("The coordinate you entered is out of range, please try again.\n");
            }else if(ocean [row][col]!=null){
                if(ocean[row][col].equals("!")||ocean[row][col].equals("x")||ocean[row][col].equals("-")){
                    System.out.println("The coordinate you entered was already used, please try again.\n");
                }
            }
            if (ocean2[row][col] == "@") {//Player correctly guessed coordinates of computer’s ship (computer loses ship).
                System.out.println("Boom! You sunk the computer's ship!");
                ocean[row][col] = "!";
                ocean2[row][col] = "!";
                computerShips--;
                turn++;
            } else if (ocean[row][col] == "@") { //Player entered coordinates of his/her own ship (player loses ship).
                System.out.println("Oh no!, you sunk your own ship!");
                ocean[row][col] = "x";
                playerShips--;
                turn++;
            }else {//Player missed. No ship on the entered coordinates
                System.out.println("Sorry, you missed!");
                ocean[row][col]="-";
                turn++;
            }
        }
    }

    public static void computerTurn(){
        System.out.println("\n\nCOMPUTER'S TURN");
        int turn=0;

        while (turn==0){

            int x=rand.nextInt(10);;
            int y=rand.nextInt(10);;

            if(ocean2[x][y]=="@") {//Computer guessed coordinates of its own ship (computer loses ship).
                System.out.println("The Computer sunk one of its own ships!");
                ocean[x][y]="!";
                ocean2[x][y]="!";
                computerShips--;
                turn++;
            } else if (ocean[x][y]=="@") { //Computer guessed coordinates of the player’s ship (player loses ship).
                System.out.println("The Computer sunk one of your ships!");
                ocean[x][y]="x";
                ocean2[x][y]="x";
                playerShips--;
                turn++;
            }else if(ocean[x][y] =="!"||ocean[x][y] == "x"||ocean2[x][y]=="-"){

            } else {//Computer missed. No ship on guessed coordinates.
                System.out.println("Computer missed!");
                ocean2[x][y]="-";
                turn++;
            }
        }

    }

    public void battle(){
        printMap(ocean); //print updated map
        System.out.println("\nYour ships: "+playerShips+" | Computer ships: "+computerShips+"\n------------------------------------");

        while (playerShips>0&&computerShips>0) {
            userTurn();
            computerTurn();
            printMap(ocean);
            System.out.println("\nYour ships: "+playerShips+" | Computer ships: "+computerShips+"\n------------------------------------");
        }

        if (playerShips==0){
            announcement message = new announcement();
            message.gameOver();
            System.out.println("\nYour ships: "+playerShips+" | Computer ships: "+computerShips+"\n------------------------------------");
        } else if (computerShips==0){
            System.out.println("Hooray! You win the Battle :)");
            System.out.println("\nYour ships: "+playerShips+" | Computer ships: "+computerShips+"\n------------------------------------");
        }
        //printMap2(ocean2);// just for test
    }
}

