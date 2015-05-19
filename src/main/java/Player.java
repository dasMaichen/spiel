import javax.swing.*;

/**
 * Textbasiertes Rollenspiel.
 * Main-Methode.
 *
 * @author Mai Kuginuki 4582942 Gruppe 2a
 * @author Ryo Kuginuki 4321886 Gruppe 2a
 */

//Die Spieler erbt von Creature.
public class Player extends Creature {

    /**
     * healingPower = Stärke des Tranks (Item).
     */
    private final int healingPower;

    /**
     * maxAP = maximale Fähigkeitspunke, um Spezialfähigkeit zu benutzen.
     */
    public final int maxAp;

    /**
     * apRegen = Regenerationswert der APs nach jeder Runde.
     */
    private final int apRegen;

    /**
     * remainingItemUses = noch verbleibende Tränke (Items).
     */
    public int remainingItemUses;

    /**
     * ap = Aktueller AP-Wert.
     */
    public int ap;

    /**
     * name = Name des Players.
     */
    String name;


    /**
     * Konstruktor mit Variablen. (1.Vorschalag des Aufgabenblattes).
     * mit folgenden Variablen:
     *
     * maxHp = maximale Lebenspunkte
     * atk = minimaler Standardangriffswert
     * healingPower = Heilungswert
     * remainingItemUses = noch verbleibende Tränke
     * hitChance = Trefferwahrscheinlichkeit
     * maxAp = maximale Fähigkeitspunkt
     * apRegen = Regenerationswert der APs
     * hp = aktuelle Lebenspunkte
     * ap = aktuelle Fähigkeitspunkte
     * name = Name des Players.
     */


    public Player(int maxHp, int atk, int healingPower,
                  int remainingItemUses, double hitChance, int maxAp, int apRegen){
        this.maxHp = maxHp;
        this.atk = atk;
        this.healingPower = healingPower;
        this.remainingItemUses = remainingItemUses;
        this.hitChance = hitChance;
        this.maxAp = maxAp;
        this.apRegen = apRegen;
        this.hp = maxHp;
        this.ap = maxAp;
        this.name = "Spieler";

    }

    //Konstruktor ohne Variablen, mit festem Wert.
    public Player(){
        this.maxHp = 100;
        this.atk =20;
        this.hitChance = 0.9;
        this.healingPower = 20;
        this.remainingItemUses = 3;
        this.maxAp = 50;
        this.apRegen = 5;
        this.hp = maxHp;
        this.ap = maxAp;
        this.name = "Spieler";
    }


    //Spieler greift Monster an (Methode in Creature)
    public int attack(Monster target) {
        return super.attack(target);
    }

    //Ermittelt wie viele Tränke man noch hat.
    public int getRemainingItemUses(){
        return this.remainingItemUses;
    }

    //Guckt, ob Heilung erfolgreich war (ob noch Tränke vorhanden) und führt Heilung aus.
    public boolean heal(){

        if (this.remainingItemUses <= 0){
            System.out.println("Keine Tränke mehr!");
            return false;
        }

        this.hp = Math.min(this.maxHp, this.hp + this.healingPower);

        this.remainingItemUses = this.remainingItemUses - 1;

        System.out.println("Der Trank hat dich geheilt.");

        return true;

    }

    //AP wird regeneriert.
    public int regenerateAp(){
        ap = ap + apRegen;

        return ap = Math.min(this.maxAp, this.ap);
    }


    //Spezialfähigkeit 1: Greift mit großem Wert an.
    public int ability1 (Monster target){
        if (ap >= 30) {
            ap = ap - 30;

            double hitChanceAbility1 = 0.7;

            if (Math.random() <= hitChanceAbility1) {

                if (target.type.equals("ice")){

                    target.takeDamage(200);
                } else {
                    target.takeDamage(100);
                }

                System.out.println("Flammenexplosion trifft das Monster. Ein verheerender Schaden!");
                return 100;

            } else {
                System.out.println("Die Attacke ging daneben.");
                return -1;
            }
        }else {
            System.out.println("Nicht genug AP!");
            return 0;
        }
    }


    //Spezialfähigkeit 2: Senkt die Trefferwahrscheinlichkeit hitChance des Monsters.
    public double ability2 (Monster target){

        if (ap >= 10) {

            ap = ap - 10;

            if (Math.random() > this.hitChance) {
                System.out.println("Die Attacke ging daneben.");
                return 0;
            }

            if (target.hitChance > 0){
                target.hitChance = Math.max(target.hitChance - 0.1, 0);
                System.out.println("Das Monster ist geblendet.\n"
                        + "Seine Trefferwahrscheinlichkeit sinkt.");
                return target.hitChance;
            }else return target.hitChance;


        }else System.out.println("Nicht genug AP!");
            return target.hitChance;



    }

    //Spezialfähigkeit 3: passiert nichts. :P
    public String ability3(){

        if (ap >= 40) {

            ap = ap - 40;

            System.out.println("Blümchen wachsen, das Monster erleidet keinen Schaden.");
            System.out.println("Aber die Welt wird ein bisschen schöner.");
            System.out.println("               _(_)_                          wWWWw   _\n" +
                    "   @@@@       (_)@(_)   vVVVv     _     @@@@  (___) _(_)_\n" +
                    "  @@()@@ wWWWw  (_)\\    (___)   _(_)_  @@()@@   Y  (_)@(_)\n" +
                    "   @@@@  (___)     `|/    Y    (_)@(_)  @@@@   \\|/   (_)\\\n" +
                    "    /      Y       \\|    \\|/    /(_)    \\|      |/      |\n" +
                    " \\ |     \\ |/       | / \\ | /  \\|/       |/    \\|      \\|/\n" +
                    "jgs|//   \\\\|///  \\\\\\|//\\\\\\|/// \\|///  \\\\\\|//  \\\\|//  \\\\\\|//\n" +
                    "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            return "Blumen wachsen...";
        }else {
            System.out.println("Nicht genug AP!");
            return "Keine Blumen...";
        }

    }


    //Statusausgabe Player.
    public String toString(){
        return (this.name + " -- LP: " + this.hp + " -- ATK: " + this.atk + " -- HIT: " + this.hitChance +
                " -- AP: " + this.ap);
    }




}
