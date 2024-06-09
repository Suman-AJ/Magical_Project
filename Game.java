public class Game {
    private Player playerA;
    private Player playerB;

    public Game(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public void start() {
        while (playerA.isAlive() && playerB.isAlive()) {
            if (playerA.getHealth() <= playerB.getHealth()) {
                attack(playerA, playerB);
                if (playerB.isAlive()) {
                    attack(playerB, playerA);
                }
            } else {
                attack(playerB, playerA);
                if (playerA.isAlive()) {
                    attack(playerA, playerB);
                }
            }
        }

        if (!playerA.isAlive()) {
            System.out.println("Player B wins!");
        } else {
            System.out.println("Player A wins!");
        }
    }

    private void attack(Player attacker, Player defender) {
        int attackRoll = Dice.roll();
        int defendRoll = Dice.roll();
        int attackDamage = attacker.getAttack() * attackRoll;
        int defense = defender.getStrength() * defendRoll;
        int damage = Math.max(0, attackDamage - defense);
        defender.reduceHealth(damage);

        System.out.printf("%s attacks %s: Attack Roll = %d, Defend Roll = %d, Damage = %d\n",
                attacker, defender, attackRoll, defendRoll, damage);
    }
}
