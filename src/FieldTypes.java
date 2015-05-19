

public enum FieldTypes implements Field {


    EMPTY_FIELD {
        @Override
        public boolean isPassable() {
            return true;
        }

        @Override
        public void action(Player player) {

        }

        public String toString(){
            return ".";
        }
    },


//    EINGANG {
//
//        public boolean isPassable() {return true;}
//
//        public void action (Level level){
//
//        }
//
//        public String toString() {return "E";}
//
//    },



    BATTLE_FIELD {
        @Override
        public boolean isPassable() {
            return true;
        }

        @Override
        public void action(Player player) {
            Game.start(player);
        }

        @Override
        public String toString() {
            return "B";
        }
    },


    FORGE {
        @Override
        public boolean isPassable() {
            return true;
        }

        @Override
        public void action(Player player) {
            player.atk += 20;
        }

        @Override
        public String toString() {
            return "T";
        }
    },


    FOUNTAIN {
        @Override
        public boolean isPassable() {
            return true;
        }

        @Override
        public void action(Player player) {
            player.ap = player.maxAp;
            player.hp = player.maxHp;

            System.out.println("Der Brunnen hat dich geheilt.");
        }

        @Override
        public String toString() {
            return "O";
        }
    },


    GOAL_FIELD {
        @Override
        public boolean isPassable() {
            return true;
        }

        @Override
        public void action(Player player) {
            System.out.println("Level geschafft! Glückwunsch!");
            System.exit(0);

        }

        @Override
        public String toString() {
            return "Z";
        }
    },


    OBSTACLE {
        @Override
        public boolean isPassable() {
            return false;
        }

        @Override
        public void action(Player player) {

        }

        @Override
        public String toString() {
            return "#";
        }
    },


    HOUSE {
        @Override
        public boolean isPassable() {
            return false;
        }

        @Override
        public void action(Player player) {

        }

        @Override
        public String toString() {
            return "H";
        }
    },



    PLAYER_POSITION {
        @Override
        public boolean isPassable() {
            return false;
        }

        @Override
        public void action(Player player) {

        }

        @Override
        public String toString() {
            return "P";
        }
    },



    STARTING_FIELD {
        @Override
        public boolean isPassable() {
            return true;
        }

        @Override
        public void action(Player player) {

        }

        @Override
        public String toString() {
            return "S";
        }
    },


    CHEST_FIELD {
        @Override
        public boolean isPassable() {
            return true;
        }

        @Override
        public void action(Player player) {
            System.out.println("Du öffnest eine Truhe.\n Du hast 2 Tränke erhalten");
            player.remainingItemUses = player.remainingItemUses +2;

        }

        @Override
        public String toString() {
            return "K";
        }
    };
}
