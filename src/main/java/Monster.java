import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Textbasiertes Rollenspiel.
 * Main-Methode.
 *
 * @author Mai Kuginuki 4582942 Gruppe 2a
 * @author Ryo Kuginuki 4321886 Gruppe 2a
 */

//Klasse Monster erbt auch von Creature.
public class Monster extends Creature {


    /**
     * Das Monster hat folgende Variablen:
     * name = Name des Monsters
     * maxHp = maximale Lebenspunkte
     * atk = minimaler Angriffswert
     * hitChance = Trefferwahrscheinlichkeit
     * hp = aktuelle Lebenspunkte

     */
    String name;
    double attackVariationRelation;
    String type;
    int resurrection;


    public Monster(int maxHp, int atk, double hitChance, String name, int resurrection, String type){
        this.maxHp = maxHp;
        this.atk = atk;
        this.hitChance = hitChance;
        this.hp = maxHp;
        this.name = name;
        this.resurrection = resurrection;
        this.type = type;

    }


    public static Monster createAMonster(){

        List<Monster> monsterPool = new ArrayList<Monster>();
        monsterPool.add(new Monster(120,10,0.9,"Golem",0,"normal"));
        monsterPool.add(new Monster(50,20,0.7,"Goblin",0,"normal"));
        monsterPool.add(new Monster(60,40,0.5,"Harpyie",0,"air"));
        monsterPool.add(new SuperMonster(10,10,0.7,"Untoter Zauberer",0.5,"undead",2){
            public boolean isDefeated() {
                if (this.hp == 0 && this.resurrection > 0) {
                    this.resurrection = this.resurrection - 1;
                    this.hp = maxHp;
                    System.out.println(this.name+" ist wieder auferstanden!");
                    return false;
                }else if (this.hp == 0 && this.resurrection == 0){
                    return true;
                } else if (this.hp > 0 && this.resurrection > 0){
                    return false;
                } else if (this.hp>0 && this.resurrection == 0){
                    return false;
                }else{
                    return false;
                }
            };

        });
        monsterPool.add(new SuperMonster(220,80,0.7,"Eisdrache",0.2,"ice",0){
            @Override
            public int attack(Player target) {
                if (Math.random() > this.attackVariationRelation) {
                    super.attack(target);
                } else {
                    if (Math.random() <= this.hitChance) {

                        damage = (int) Math.round(this.atk + this.atk * Math.random());

                        target.takeDamage(3*damage);

                        System.out.println("Attacke zeigt Wirkung!");

                        return damage;
                    }
                    else {
                        System.out.println("Attacke ging daneben.");
                        return -1;
                    }
                }
                return damage;
            }
        });


        monsterPool.add(new SuperMonster(80,20,0.9,"Sphinx",1,"normal",0){
            @Override
            protected void enterBattleField() {
                System.out.println("SPHINX:\n" +
                        "Es ist am Morgen vierfüßig, \n" +
                        "am Mittag zweifüßig,\n" +
                        "am Abend dreifüßig.\n" +
                        "Von allen Geschöpfen wechselt es \n" +
                        "allein mit der Zahl seiner Füße.\n" +
                        "(1 Wort. Achte auf die Rechtschreibung.");

                Scanner sc = new Scanner(System.in);
                String answer = sc.nextLine();
                answer = answer.toUpperCase();

                if (answer.equals("MENSCH")) {
                    System.out.println("Die Antwort ist richtig!");
                    System.out.println("Sphinx lässt dich ohne einen Kampf ziehen.");
                    this.hp = 0;
                }else {
                    System.out.println("Die Antwort war FALSCH!");
                    System.out.println("Sphinx greift dich an!");
                }
            }
        });


        int monsterIndex = new Random().nextInt(monsterPool.size());
        Monster monster = monsterPool.get(monsterIndex);

        monster.enterBattleField();

        return monsterPool.get(monsterIndex);
    }

    protected void enterBattleField() {

    }


    //Monster greift Spieler an.
    public int attack(Player target) {
        return super.attack(target);
    }

    //Statusausgabe des Monsters.
    public String toString() {
        return (this.name + " -- LP: " + this.hp + " -- ATK: " + this.atk+ " -- HIT: " + this.hitChance);
    }


}
