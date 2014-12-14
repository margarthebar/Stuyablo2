public class Warrior extends Adventurer implements Comparable<Warrior>{
    private int rage;

    public Warrior(){
        this("Ted",30);
    }
    public Warrior(String s){
	this(s,30);
    }
    public Warrior(String s, int startHP){
	super(s,startHP);
	setRage(20);
	Str = 14;
	Dex = 6;
	Int = 10;
    }
    
    public int getRage(){
	return rage;
    }

    public void setRage(int n){
	rage = n;
    }

    public void setSpecial(int n){
	setRage(n);
    }

    public void attack(Adventurer other){
	System.out.println(this.getName() + " attacks " + other.getName());
	if(hit(other)){
	    int damage = (rand.nextInt(Str) + 4);
	    other.setHP(other.getHP() - damage);
	    System.out.println("and hits for " + damage + " points of damage");
	}else{
	    System.out.println("and misses");
	}
    }
    public void specialAttack(Adventurer other){
	System.out.println(this.getName() + " hacks madly at " + other.getName());
	if(hit(other)){
	    int damage = (rand.nextInt(Str) + 1 + rand.nextInt(rage/2) + 1);
	    other.setHP(other.getHP() - damage);
	    System.out.println("and hits for " + damage + " points of damage");
	}else{
	    System.out.println("and misses");
	}
    }
    public String getStats(){
	return super.getStats() +" "+ rage+"RAGE";
    }

    public int compareTo(Warrior other){
	return this.getHP() - other.getHP();
    }
}
