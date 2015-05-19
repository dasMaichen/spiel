import java.awt.*;
import java.util.Scanner;

/**
 * Created by mai on 18.01.15.
 */
public class Crawler {

    //Initialisiert Spieler.
    static Player spieler = new Player();
    static Level level = Level.loadFromFile("");

    public static  void main(String[] args){

        spieler.position = level.findStartPoint();

        //test...
        //spieler.position.move(spieler.position.x,spieler.position.y-1);

        // level.printTheLevel(spieler);


        do {
            System.out.println("\n");
            level.printTheLevel(spieler);



            boolean obenGeht = level.isFieldPassable(new Point(spieler.position.x,spieler.position.y-1) );
            if (obenGeht){
                System.out.println("[O] oben");
            }


            boolean untenGeht = level.isFieldPassable(new Point(spieler.position.x,spieler.position.y+1) );
            if (untenGeht){
                System.out.println("[U] unten");
            }

            boolean linksGeht = level.isFieldPassable(new Point(spieler.position.x-1,spieler.position.y) );
            if (linksGeht){
                System.out.println("[L] links");
            }

            boolean rechtsGeht = level.isFieldPassable(new Point(spieler.position.x+1,spieler.position.y) );
            if (rechtsGeht){
                System.out.println("[R] rechts");
            }


            Scanner sc = new Scanner(System.in);
            String richtung = sc.nextLine();
            richtung = richtung.toUpperCase();

            //Richtung?
            if (richtung.equals("O") && obenGeht) {
                spieler.position.move(spieler.position.x,spieler.position.y-1);
            }   else if (richtung.equals("U") && untenGeht) {
                spieler.position.move(spieler.position.x,spieler.position.y+1);
            }else  if (richtung.equals("L") && linksGeht){
                spieler.position.move(spieler.position.x-1,spieler.position.y);
            }else if (richtung.equals("R") && rechtsGeht){
                spieler.position.move(spieler.position.x+1,spieler.position.y);
            }else {
                System.out.println("Ungültige Eingabe.");
                continue;
            }


            level.fieldMatrix[spieler.position.y][spieler.position.x].action(spieler);




        }

        while (!spieler.position.equals(level.findGoalPoint()));




    }






}
