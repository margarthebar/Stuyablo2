import java.util.Scanner;
import java.util.Random;
public class Game{

    public static void main(String[]args){
	Adventurer[] party = initializeParty();
	Adventurer[] startParty = party;
	Adventurer opponent = initializeOpponent();

	boolean cont = false;
	do{
	    combat(party,opponent);
	    if(again()){
		party = startParty;
		opponent = initializeOpponent();
		cont = true;
	    }else{
		cont = false;
	    }
	}while(cont);
    }

    public static boolean again(){
	Scanner in = new Scanner(System.in);
	System.out.println("Would you like to play again?\n\tS: stop\n\tP: play again");
	String input = in.nextLine();
	boolean again = false;

	boolean done = true;
	do{
	    done = true;
	    if(input.equalsIgnoreCase("S")){
	        again = false;
	    }else if(input.equalsIgnoreCase("P")){	
		again = true;
	    }else{
		done = false;
	    }
	}while(!done);
	return again;
    }

    public static Adventurer[] initializeParty(){
	Adventurer[] p;
	Scanner in = new Scanner(System.in);
	System.out.println("Type 'D' to create a default party. Type any other key to create a custom party");
	String choice = in.nextLine();

	if(choice.equalsIgnoreCase("D")){
	    p = defaultParty();
	}else{
	    p = customParty();
	}
	return p;
    }

    public static Adventurer initializeOpponent(){
        Random rnd = new Random();
	int num = rnd.nextInt(3);
	
	Adventurer opponent = new Warrior("Error");
	if(num == 0){
	    opponent = new Warrior();
	}else if(num == 1){
	    opponent = new Wizard();
	}else if(num == 2){
	    opponent = new Rogue();
	}
	return opponent;
    }
    
    public static Adventurer[] defaultParty(){
	Adventurer[] player = new Adventurer[3];
	player[0] = new Warrior("Cohen");
	player[1] = new Wizard("Merlin");
	player[2] = new Rogue("Halt");
	return player;
    }

    public static Adventurer[] customParty(){
	Adventurer[] player = setNameAndClass();

	setStats(player);

	return player;
    }

    public static Adventurer[] setNameAndClass(){
	Adventurer[] party = new Adventurer[3];
	System.out.println("You may fight with a party of 3 Adventurers.");
	System.out.println("For each, choose a name and a class:\n\tA: Warrior\n\tB: Wizard\n\tC: Rogue");

	for(int i=0;i<party.length;i++){
	    System.out.println("Adventurer "+(i+1)+":");
	    party[i] = new Warrior("Error");
	
	    boolean looper = true;
	    Scanner in = new Scanner(System.in);

	    System.out.println("Choose a name:");
	    String name = in.nextLine();

	    System.out.println("Choose a class:");
	    String input = in.nextLine();

	    while(looper){
		if(input.equalsIgnoreCase("A")){
		    party[i] = new Warrior(name);
		    looper = false;
		}else if(input.equalsIgnoreCase("B")){
		    party[i] = new Wizard(name);
		    looper = false;
		}else if(input.equalsIgnoreCase("C")){
		    party[i] = new Rogue(name);
		    looper = false;
		}else{
		    party[i] = new Warrior("Error: Invalid input");
		    System.out.println("Error: Invalid input. Try again:");
		    input = in.nextLine();
		}	
	    }
	    System.out.println();
	    party[i].setSTR(1);
	    party[i].setSTR(1);
	    party[i].setSTR(1);
	}

	return party;
    }

    public static void setStats(Adventurer[] party){
	System.out.println("Each member of your party starts out with 1 point each of Strength, Dexterity, and Intelligence.");
	System.out.println("For each member, allocate a total of 27 additional stat points amongst the three categories.");
	for(int i=0;i<party.length;i++){
	    Scanner in = new Scanner(System.in);
	    int statPoints = 27;
	    
	    System.out.println(party[i].getName()+":");
	    System.out.println("Set STR points:");
	    int STR = in.nextInt();
	    boolean looper = true;
	    while(looper){
		if(STR >= 0 && STR <= statPoints){
		    party[i].setSTR(STR);
		    statPoints -= STR;
		    looper = false;
		}else{
		    System.out.println("Error: Invalid input. Try again:");
		    STR = in.nextInt();
		}
	    }

	    if(statPoints>0){
		System.out.println(statPoints+" stat points remaining. Set DEX points:");
		int DEX = in.nextInt();
		looper = true;
		while(looper){
		    if(DEX >= 0 && DEX <= statPoints){
			party[i].setDEX(DEX);
			statPoints -= DEX;
			looper = false;
		    }else{
			System.out.println("Error: Invalid input. Try again:");
			DEX = in.nextInt();
		    }
		}
		System.out.println("Your INT will be allocated the remaining stat points");
		party[i].setINT(statPoints);
	    }else{
		System.out.println("Your DEX and INT will recieve no extra stat points");
		party[i].setDEX(0);
		party[i].setINT(0);
	    }
	    System.out.println();
	}
    }

    public static void combat(Adventurer[] player, Adventurer other){
	int roundCount=1;
	boolean cont = true;
	do{
	    System.out.println("Round "+roundCount+":");
	    for(int n=0;n<player.length;n++){
		System.out.println(player[n].getStats());
	    }
	    System.out.println(other.getStats());
	    System.out.println();
	
	    for(int i=0;i<player.length;i++){
		if(player[i].getHP()>0){
		    characterTurn(player[i],other);
		}
		if(other.getHP()<=0){
		    System.out.println("You win!");
		    cont = false;
		    break;
		}
	    }
	    if(cont){
		opponentTurn(other,player);
	    }
	    if(!groupAlive(player)){
		System.out.println("You lose!");
		cont = false;;
	    }
	    roundCount++;
	    System.out.println();
	}while(cont);

	for(int n=0;n<player.length;n++){
	    System.out.println(player[n].getStats());
	}
	System.out.println(other.getStats());
    }

    public static boolean groupAlive(Adventurer[] player){
	for(int i=0;i<player.length;i++){
	    if(player[i].getHP()>0){
		return true;
	    }
	}
	return false;
    }

    public static void characterTurn(Adventurer character, Adventurer other){
	Scanner in = new Scanner(System.in);
	System.out.println(character.getName()+"'s turn:");
	System.out.println(character.getStats());
	System.out.println(other.getStats());
	System.out.println("Choose an action:\n\tA: attack\n\tS: special attack\n\t");
	String input = in.nextLine();

	boolean done = false;
	do{
	    done = true;
	    if(input.equalsIgnoreCase("A")){
		character.attack(other);
	    }else if(input.equalsIgnoreCase("S")){
		character.specialAttack(other);
	    }else{
		System.out.println("Error: Invalid input. Try again: ");
		input = in.nextLine();
		done = false;
	    }
	}while(!done);
	System.out.println();
	try {
	    Thread.sleep(1000);
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
    }

    public static void opponentTurn(Adventurer opponent,Adventurer[] party){
	Random rnd = new Random();
	boolean repeat = false;
	int character = 0;
	do{
	    character = rnd.nextInt(3);
	    if(party[character].getHP()<=0){
		repeat = true;
	    }else{
		repeat = false;
	    }
	}while(repeat);

	if(rnd.nextInt(4) == 3 && opponent.canSpecialAttack()){
	    opponent.specialAttack(party[character]);
	}else{
	    opponent.attack(party[character]);
	}
	if(party[character].getHP()<=0){
	    System.out.println(party[character].getName()+" is dead!");
	}
	System.out.println();
	try {
	    Thread.sleep(2000);
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
    }
    
}
