public class Rogue extends Adventurer{
    private int stamina;

    public Rogue(){
	this("Aragorn",20);	
    }
    public Rogue(String s){
	this(s,20);
    }
    public Rogue(String s, int startHP){
	super(s,startHP);
	setStamina(25);
	Str = 8;
	Dex = 14;
	Int = 10;
    }

    public int getStamina(){
	return stamina;
    }
    public void setStamina(int n){
	stamina = n;
    }
    public void setSpecial(int n){
	setStamina(n);
    }

     public void attack(Adventurer other){
	 System.out.println(this.getName() + " attacks " + other.getName());
	if(hit(other) || hit(other)){
	    int damage = (rand.nextInt(Str) + 1);
	    other.setHP(other.getHP() - damage);
	    System.out.println("and hits for " + damage + " points of damage");
	}else{
	    System.out.println("and misses");
	}
    }
    public void specialAttack(Adventurer other){
	if(stamina >= 5){
	    System.out.println(this.getName() + " backstabs " + other.getName());
	    if(hit(other) || hit(other)){
		int damage = (rand.nextInt(Str) + 1 + rand.nextInt(10) + 1);
		other.setHP(other.getHP() - damage);
		System.out.println("and hits for " + damage + " points of damage");
	    }else{
		System.out.println("and misses");
	    }
	    stamina -= 5;
	}else{
	    System.out.println("You do not have enough stamina to complete this action");
	}
    }
    public String getStats(){
	return super.getStats() +" "+ stamina+"STAMINA";
    }
    public boolean canSpecialAttack(){
	return stamina >= 5;
    }
}
