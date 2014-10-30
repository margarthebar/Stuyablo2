public class Wizard extends Adventurer{
    private int mana;

    public Wizard(){
	this("Tim",20);
    }
    public Wizard(String s){
        this(s,20);
    }
    public Wizard(String s, int startHP){
	super(s,startHP);
	setMana(25);
	Str = 6;
	Dex = 8;
	Int = 18;
    }

    public int getMana(){
	return mana;
    }
    public void setMana(int n){
	mana = n;
    }
    public void setSpecial(int n){
	setMana(n);
    }
    public boolean hitMagic(Adventurer other){
	return rand.nextDouble() < ((double)(Int - other.Int + 6)/20);
    }
    public void attack(Adventurer other){
	System.out.println(this.getName() + " attacks " + other.getName());
	if(hit(other)){
	    int damage = (rand.nextInt(Str) + 1);
	    other.setHP(other.getHP() - damage);
	    System.out.println("and hits for " + damage + " points of damage");
	}else{
	    System.out.println("and misses");
	}
    }
    public void specialAttack(Adventurer other){
	if(mana >= 5){
	    System.out.println(this.getName() + " hurls fire at " + other.getName());
	    if(hitMagic(other)){
		int damage = (rand.nextInt(Int) + 1 + rand.nextInt(10) + 1);
		other.setHP(other.getHP() - damage);
		System.out.println("and burns for " + damage + " points of damage");
	    }else{
		System.out.println("and misses");
	    }
	    mana -= 5;
	}else{
	    System.out.println("You do not have enough mana to perform this action.");
	}
    }
    public String getStats(){
	return super.getStats() +" "+ mana+"MANA";
    }

    public boolean canSpecialAttack(){
	return mana >= 5;
    }
}
