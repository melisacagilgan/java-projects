/**
 * Maintaining the enemy boss class
 */
public class EnemyEntity extends Thread implements PotencyCalculator {
    private int entityID;
    private int healthPoints;
    private int baseDamage;

    public EnemyEntity() {
    }

    public EnemyEntity(int entityID, int healthPoints, int baseDamage) {
        this.entityID = entityID;
        this.healthPoints = healthPoints;
        this.baseDamage = baseDamage;
    }

    /**
     * Enemy boss attacks to the tank player every 1 second and every 4th attack will be a group wide attack
     */
    @Override
    public void run() {
        EncounterManager myEncounterManager = EncounterManager.getInstance();
        int attackCount = 0;
        while (myEncounterManager.enemyIsAlive() && myEncounterManager.playersAreAlive()) {
            try {
                if (attackCount % 4 == 0) {
                    myEncounterManager.groupWideAttack();
                } else {
                    myEncounterManager.enemyAttack('t');
                }
                attackCount++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Calculating the attack value of enemy boss
     *
     * @return the attack value of enemy boss
     */
    @Override
    public int dealDamage() {
        return baseDamage;
    }

    /**
     * Updating the health points of enemy boss after being attacked
     *
     * @param damageReceived is a player's attack value
     */
    @Override
    public void takeDamage(int damageReceived) {
        int HP = getHealthPoints() - damageReceived;
        if (HP <= 0) {
            setHealthPoints(0);
        } else {
            setHealthPoints(getHealthPoints() - damageReceived);
        }
    }

    /**
     * Getting the entity id of enemy boss
     *
     * @return the entity id of enemy boss
     */
    public int getEntityID() {
        return entityID;
    }

    /**
     * Setting an entity id of enemy boss
     *
     * @param entityID is a unique identifier for enemy boss
     */
    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    /**
     * Getting the health points of enemy boss
     *
     * @return the health points of enemy boss
     */
    public int getHealthPoints() {
        return this.healthPoints;
    }

    /**
     * Setting the health points of enemy boss
     *
     * @param healthPoints is the health value of enemy boss
     */
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    /**
     * Getting the damage value of enemy boss
     *
     * @return the damage value of enemy boss
     */
    public int getBaseDamage() {
        return this.baseDamage;
    }

    /**
     * Setting the damage value of enemy boss
     *
     * @param baseDamage is the damage value of enemy boss
     */
    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

}
