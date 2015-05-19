/**
 * Created by mai on 18.01.15.
 */
    public class SuperMonster extends Monster {


    public SuperMonster(int maxHp, int atk, double hitChance, String name, double attackVariationRelation,
                        String type, int resurrection) {
        super(maxHp, atk, hitChance, name, resurrection,type);

        this.attackVariationRelation = attackVariationRelation;

    }



}
