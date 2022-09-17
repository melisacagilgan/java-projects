/**
 * Extending the player class to maintain tank entity
 */
public class Tank extends Player
{
	private int defense;

	public Tank() {}

	public Tank(String role, int entityID, int healthPoints, int baseDamage, int defense)
	{
		super(role, entityID, healthPoints, baseDamage);
		this.defense = defense;
	}

	/**
	 * Updating the health points of a tank player after being attacked
	 * @param damageReceived is the enemy's attack value
	 */
	@Override
	public void takeDamage(int damageReceived)
	{
		setHealthPoints(getHealthPoints() - (damageReceived - defense));
		if(getHealthPoints() <= 0)
			setHealthPoints(0);
	}

	/**
	 * Getting the defense value of a tank player
	 * @return the defense value of that tank player
	 */
	public int getDefense()
	{
		return this.defense;
	}

	/**
	 * Setting the defense value of a tank player
	 * @param defense is the defense value of that tank player
	 */
	public void setDefense(int defense)
	{
		this.defense = defense;
	}
}