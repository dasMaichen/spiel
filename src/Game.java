import java.util.Scanner;


/**
 * Textbasiertes Rollenspiel.
 * Main-Methode.
 *
 * @author Mai Kuginuki 4582942 Gruppe 2a
 * @author Ryo Kuginuki 4321886 Gruppe 2a
 */


public class Game {

//hier werden neue Instanzen (Spieler und Monster) erstellt.



//Main-Methode.
    public static void start(Player spieler) {


        Monster monster = Monster.createAMonster();

        if (monster.isDefeated()){
            return;
        }

        do {
            //Status der Spieler und Montser werden angezeigt. Und Mögliche Aktionen aufgelistet.
            System.out.println(spieler);
            System.out.println(monster);

            System.out.println("----------------------------------------");
            System.out.println("Mögliche Aktionen:");
            System.out.println("[A] Angriff");
            System.out.println("[Z] Zauber");
            System.out.println("[I] Item (noch " + spieler.getRemainingItemUses() + " verbleibend)");
            System.out.println("Welche Aktion?");
            System.out.println("----------------------------------------");


        //mit dem Scanner kann die Auswahleingabe ausgelesen werden. Und wird geprüft, ob die Eingabe zulässig ist.
            Scanner sc = new Scanner(System.in);
            String aktion = sc.nextLine();
            aktion = aktion.toUpperCase();

            //Standardangriff
            if (aktion.equals("A")) {
                spieler.attack(monster);

            //Heiltränke einsetzen.
            }   else if (aktion.equals("I")) {
                spieler.heal();

            //Zauber. Spezialfähigkeit.
            } else if (aktion.equals("Z")) {
                System.out.println("Welche Zauber?");
                System.out.println("[F] Flammenexplosion (AP:30)");
                System.out.println("[L] Lichtblitz (AP:10)");
                System.out.println("[N] Gewalten der Natur (AP: 40)");
                System.out.println("----------------------------------------");

            //Auswahl der Spezialfähigkeit. Wieder über den Scanner.
                Scanner s = new Scanner(System.in);
                String zaubern = s.nextLine();
                zaubern = zaubern.toUpperCase();
                if (zaubern.equals("F")) {
                    spieler.ability1(monster);
                } else if (zaubern.equals("L")) {
                    spieler.ability2(monster);
                } else if (zaubern.equals("N")) {
                    spieler.ability3();
                } else {
                    System.out.println("Fehlerhafte Eingabe");
                }


            } else {
                System.out.println("Fehlerhafte Eingabe");
            }


            //Statusausgabe.
            System.out.println(spieler);
            System.out.println(monster);
            System.out.println("----------------------------------------");




            //Solange Monster lebt, greift es an.
            if (monster.isDefeated() == false){
                monster.attack(spieler);
            }

            //Guckt, ob Spieler oder Monster schon besiegt wurde.
            if (spieler.isDefeated() == true){
                System.out.println(spieler);
                System.out.println(monster);
                System.out.println("Der Spieler wurde besiegt. Game Over");
                System.exit(0);
            }

            if (monster.isDefeated() == true){
                System.out.println(monster.name + " wurde besiegt.");

            }


            spieler.regenerateAp();



        }


        while (spieler.isDefeated() == false && monster.isDefeated() == false);
        //while (spieler.hp > 0 && monster.hp > 0);

    }



}




