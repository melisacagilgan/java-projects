/**
 * Maintaining a player's score table
 */
public class PlayerScore {
    private final String playerName;          // Name of the player
    private final String role;                // Role of player
    private final Integer damageDealt;        // Amount of damage dealt
    private final Integer damageReceived;     // Amount of damage received
    private final Integer healingDone;        // Amount of healing dealt
    private final Integer healingReceived;    // Amount of healing received
    private final Integer attacks;            // Number of attacks performed

    PlayerScore(String name, String playerRole, int dmgDealt, int dmgReceived, int healDone, int healReceived, int numOfAttacks) {
        playerName = name;
        role = playerRole;
        damageDealt = dmgDealt;
        damageReceived = dmgReceived;
        healingDone = healDone;
        healingReceived = healReceived;
        attacks = numOfAttacks;
    }

    /**
     * Getting the player's name
     *
     * @return the name of that player
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Getting the player's role
     *
     * @return the role of that player
     */
    public String getRole() {
        return role;
    }

    /**
     * Getting the damage dealt by a player
     *
     * @return the amount of damage dealt by that player
     */
    public Integer getDamageDealt() {
        return damageDealt;
    }

    /**
     * Getting the damage received by a player
     *
     * @return the amount of damage received by that player
     */
    public Integer getDamageReceived() {
        return damageReceived;
    }

    /**
     * Getting the healing done by a player
     *
     * @return the amount of healing done by that player
     */
    public Integer getHealingDone() {
        return healingDone;
    }

    /**
     * Getting the healing received by a player
     *
     * @return the amount of healing received by that player
     */
    public Integer getHealingReceived() {
        return healingReceived;
    }

    /**
     * Getting the attack number of a player
     *
     * @return the number of attack done by that player
     */
    public Integer getAttacks() {
        return attacks;
    }
}