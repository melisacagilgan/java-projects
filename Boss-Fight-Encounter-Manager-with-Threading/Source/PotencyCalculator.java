/**
 * Declaring dealDamage and takeDamage methods to be implemented by Player and EnemyEntity classes
 */
public interface PotencyCalculator {
    /**
     * Calculating the attack value of a player or enemy boss
     *
     * @return the attack value of that player or enemy boss
     */
    int dealDamage();

    /**
     * Updating the health points of a player or enemy boss after being attacked
     *
     * @param damageReceived is the attacker's attack value
     */
    void takeDamage(int damageReceived);
}