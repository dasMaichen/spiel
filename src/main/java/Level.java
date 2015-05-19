import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.*;

/**
 * Textbasiertes Rollenspiel.
 * Main-Methode.
 *
 * @author Mai Kuginuki 4582942 Gruppe 2a
 * @author Ryo Kuginuki 4321886 Gruppe 2a
 */



public class Level {
    private static final String LEVEL_01 = ""
            + "##.T..#.K..\n"
            + "##....#.B..\n"
            + "......B..B.\n"
            + ".BO...#....\n"
            + "#.#.###B.BZ\n"
            + "#S#..O#....";

    public final FieldTypes[][] fieldMatrix;

    public Level(char[][] fieldMatrixData) {
        this.fieldMatrix = new FieldTypes[fieldMatrixData.length][];

        for (int i = 0; i < fieldMatrixData.length; i++){
            this.fieldMatrix[i]=new FieldTypes[fieldMatrixData[i].length];
                for (int j = 0; j < fieldMatrixData[i].length; j++) {

                    char element = fieldMatrixData[i][j];

                    for (FieldTypes fieldType: FieldTypes.values()){
                        if (element == fieldType.toString().charAt(0)){
                            this.fieldMatrix[i][j] = fieldType;
                            break;
                        }
                    }

                }

        }


    }

    public static Level loadFromFile(String fileName) {


        // TODO: load level file

        String[] levelFileLines = LEVEL_01.split("\n");

        char[][] fieldMatrix = new char[levelFileLines.length][];

        for (int i = 0; i < levelFileLines.length; i++) {
            fieldMatrix[i] = levelFileLines[i].toCharArray();
        }

        Level level = new Level(fieldMatrix);
        GsonBuilder gsonBuilder = new GsonBuilder();
        for (FieldTypes fieldType : FieldTypes.values()) {
            gsonBuilder.registerTypeAdapter(fieldType.getClass(), FieldTypes.SERIALIZER);
        }
        System.out.println(gsonBuilder.create().toJson(level));
        return level;
    }

    @Override
    public String toString() {
        String fieldOutput = "";

        for (int i = 0; i < this.fieldMatrix.length; i++) {
            for (int j = 0; j < this.fieldMatrix[i].length; j++) {
                fieldOutput += this.fieldMatrix[i][j];
            }
            fieldOutput += "\n";
        }

        return fieldOutput;
    }



    public boolean isFieldPassable (Point point){

        if (point.x < 0 || point.y < 0){
            return false;
        }

        if (point.x >= fieldMatrix[0].length || point.y >= fieldMatrix.length){
            return false;
        }

        FieldTypes field = fieldMatrix[point.y][point.x];

        if (field.isPassable()==true) {
            return true;
        }else {
            return false;

        }

    }


    public Point findStartPoint (){
        for (int i = 0; i < fieldMatrix.length; i++) {
            for (int j = 0; j < fieldMatrix[i].length; j++) {
                FieldTypes element = fieldMatrix[i][j];

                if (element == FieldTypes.STARTING_FIELD){
                    return new Point(j,i);
                }

            }
        }return null;

    }


    public void printTheLevel(Player spieler) {

        for (int i = 0; i < this.fieldMatrix.length; i++) {
            for (int j = 0; j < this.fieldMatrix[i].length; j++) {

                Point p = new Point(j,i);
                if (p.equals (spieler.position)){
                    System.out.print(FieldTypes.PLAYER_POSITION);
                }else {
                    System.out.print(this.fieldMatrix[i][j]);
                }
            }
            System.out.println();



        }

    }


    public Point findGoalPoint (){
        for (int i = 0; i < fieldMatrix.length; i++) {
            for (int j = 0; j < fieldMatrix[i].length; j++) {
                FieldTypes element = fieldMatrix[i][j];

                if (element == FieldTypes.GOAL_FIELD){
                    return new Point(j,i);
                }

            }
        }return null;

    }



}
