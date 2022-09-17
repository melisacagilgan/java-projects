/**
 * Extending the player class to maintain tank entity
 */
public class Tank extends Player {
    private int defense;

    public Tank() {
    }

    public Tank(String role, int entityID, int healthPoints, int baseDamage, int defense) {
        super(role, entityID, healthPoints, baseDamage);
        this.defense = defense;
    }

    /**
     * A tank player attacks to the enemy boss every 1 second
     */
    @Override
    public void run() {
        EncounterManager myEncounterManager = EncounterManager.getInstance();
        while (myEncounterManager.enemyIsAlive() && myEncounterManager.playersAreAlive()) {
            try {
                myEncounterManager.playerAttack('t');
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updating the health points of a tank player after being attacked
     *
     * @param damageReceived is the enemy's attack value
     */
    @Override
    public void takeDamage(int damageReceived) {
        setHealthPoints(getHealthPoints() - (damageReceived - defense));
        if (getHealthPoints() <= 0)
            setHealthPoints(0);
    }

    /**
     * Getting the defense value of a tank player
     *
     * @return the defense value of that tank player
     */
    public int getDefense() {
        return this.defense;
    }

    /**
     * Setting the defense value of a tank player
     *
     * @param defense is the defense value of that tank player
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }
}