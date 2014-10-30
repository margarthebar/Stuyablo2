import java.util.Random;
public abstract class Adventurer{
    private String name;
    private int HP;
    public int Dex, Int, Str;
    public Random rand = new Random();
    
    public Adventurer(String name,int startHP){
	setName(name);
	setHP(startHP);
	Str = 10;
	Dex = 10;
	Int = 10;
    }
    public Adventurer(String name){
        this(name,20);
    }
    public Adventurer(){
	this("Lester",20);
    }

    public String toString(){
	return getName();
    }
    
    public String getName(){
	return name;
    }
    public void setName(String s){
	name = s;
    }
    public int getHP(){
	return HP;
    }
    public void setHP(int n){
	HP = n;
    }

    public int getSTR(){
	return Str;
    }
    public void setSTR(int n){
	Str = n;
    }
    public int getDEX(){
	return Dex;
    }
    public void setDEX(int n){
	Dex = n;
    }
    public int getINT(){
	return Int;
    }
    public void setINT(int n){
	Int = n;
    }

    public String getStats(){
	return name + "\t" + HP+"HP " +Str+"STR " +Dex+"DEX " +Int+"INT";
    }

    public abstract void attack(Adventurer other);
	public abstract void specialAttack(Adventurer other);

    public boolean hit(Adventurer other){
        return rand.nextDouble() < ((double)(Dex - other.Dex + 10)/20);
    }
    
    public boolean canSpecialAttack(){
	return true;
    }

    public abstract void setSpecial(int n);
}
