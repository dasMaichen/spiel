import java.awt.*;

/**
 * Textbasiertes Rollenspiel.
 * Main-Methode.
 *
 * @author Mai Kuginuki 4582942 Gruppe 2a
 * @author Ryo Kuginuki 4321886 Gruppe 2a
 */


//auf diese Klasse greifen sowohl Player als auch Monster zu.
public class Creature {

    /**
     * maxHp = maximale Lebenspunkte
     * hp = aktuelle Lebenspunkte
     * atk = minimaler Standardangriffswert
     * hitChance = Trefferwahrscheinlichkeit.
     */
    int maxHp;
    int hp;
    int atk;
    double hitChance;
    int damage;
    int resurrection;
    Point position;


    //Testet "isDefeated" - Tot oder nicht.
    public static void main (String [] args){

        Creature c1 = new Creature();

        c1.hp = 0;

        System.out.println(c1.isDefeated());

        c1.hp = 7;

        System.out.println(c1.isDefeated());

    }


    public boolean isDefeated() {
        if (this.hp > 0){
            return false;
        }else {
            return true;
        }
    }



    /*Guckt ob Spieler oder Monster tot ist.
    public boolean isDefeated() {
        if (this.hp == 0) {
            return true;
        } else {
            return false;
        }

    }*/

    //kürzere Möglickeit zu schreiben...
    //public boolean isDefeated2() {
      //  return (this.hp == 0);
    //}


    //Erleidet Schaden.
    public void takeDamage (int damage){
        if (damage >= 0){
            this.hp = this.hp - damage;
        }else {
            System.out.println("negativer Angriffswert");
        }

        if (this.hp < 0){
            this.hp = 0;
        }
    }

    //Guckt, ob es das Ziel trifft und rechnet Angriffswert aus.
    protected int attack (Creature target){

        if (Math.random() <= this.hitChance) {

            damage = (int) Math.round(this.atk + this.atk * Math.random());

            target.takeDamage(damage);

            System.out.println("Attacke zeigt Wirkung!");

            return damage;
        }
        else {
            System.out.println("Attacke ging daneben.");
            return -1;
        }

    }



}


