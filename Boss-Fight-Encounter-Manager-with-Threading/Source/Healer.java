/**
 * Extending the player class to maintain healer entity
 */

public class Healer extends Player {
    private int mind;

    public Healer() {
    }

    public Healer(String role, int entityID, int healthPoints, int baseDamage, int mind) {
        super(role, entityID, healthPoints, baseDamage);
        this.mind = mind;
    }

    /**
     * A healer player heals a player who has the smallest health point every 1 second
     */
    @Override
    public void run() {
        EncounterManager myEncounterManager = EncounterManager.getInstance();
        while (myEncounterManager.enemyIsAlive() && myEncounterManager.playersAreAlive()) {
            try {
                myEncounterManager.healPlayer();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Calculating the healing value of a healer player
     *
     * @return the healing effect value of the healer to be added a player's health points including oneself
     */
    public int heal() {
        return mind + 10;
    }

    /**
     * Getting the mind value of a healer player
     *
     * @return the mind value of that healer player
     */
    public int getMind() {
        return this.mind;
    }

    /**
     * Setting the mind value of a healer player
     *
     * @param mind is the mind value of that healer player
     */
    public void setMind(int mind) {
        this.mind = mind;
    }
}
