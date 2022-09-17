/**
 * Maintaining the player class
 */
public abstract class Player implements PotencyCalculator
{
	private String role;
	private int entityID;
	private int healthPoints;
	private int baseDamage;

	public Player() {}

	public Player(String role, int entityID, int healthPoints, int baseDamage)
	{
		this.role = role;
		this.entityID = entityID;
		this.healthPoints = healthPoints;
		this.baseDamage = baseDamage;
	}

	/**
	 * Calculating the attack value of a player
	 * @return the attack value of that player
	 */
	public int dealDamage()
	{
		return baseDamage;
	}

	/**
	 * Updating the health points of a player after being attacked
	 * @param damageReceived is the enemy's attack value
	 */
	public void takeDamage(int damageReceived)
	{
		setHealthPoints(getHealthPoints() - damageReceived);
		if(getHealthPoints() <= 0)
			setHealthPoints(0);
	}

	/**
	 * Getting the role of a player
	 * @return the role of that player
	 */
	public String getRole()
	{
		return role;
	}

	/**
	 * Setting the role of a player
	 * @param role of the player
	 */
	public void setRole(String role)
	{
		this.role = role;
	}

	/**
	 * Getting the entity id of a player
	 * @return the entity id of that player
	 */
	public int getEntityID()
	{
		return entityID;
	}

	/**
	 * Setting an entity id of a player
	 * @param entityID is a unique identifier for that player
	 */
	public void setEntityID(int entityID)
	{
		this.entityID = entityID;
	}

	/**
	 * Getting the health points of a player
	 * @return the health points of that
	 */
	public int getHealthPoints()
	{
		return this.healthPoints;
	}

	/**
	 * Setting the health points of a player
	 * @param healthPoints is the health value of that player
	 */
	public void setHealthPoints(int healthPoints)
	{
		this.healthPoints = healthPoints;
	}

	/**
	 * Getting the damage value of a player
	 * @return the damage value of that player
	 */
	public int getBaseDamage()
	{
		return this.baseDamage;
	}

	/**
	 * Setting the damage value of a player
	 * @param baseDamage is the damage value of that player
	 */
	public void setBaseDamage(int baseDamage)
	{
		this.baseDamage = baseDamage;
	}
}