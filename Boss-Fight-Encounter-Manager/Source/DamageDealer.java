/**
 * Extending the player class to maintain damage dealer entity
 */

public class DamageDealer extends Player
{
	private int intelligence;

	public DamageDealer() {}

	public DamageDealer(String role, int entityID, int healthPoints, int baseDamage, int intelligence)
	{
		super(role, entityID, healthPoints, baseDamage);
		this.intelligence = intelligence;
	}

	/**
	 * Calculating the attack value of a damage dealer player
	 * @return the attack value of that damage dealer player
	 */
	@Override
	public int dealDamage()
	{
		return (getBaseDamage() + intelligence);
	}

	/**
	 * Getting the intelligence value of a damage dealer player
	 * @return the intelligence value of that damage dealer player
	 */
	public int getIntelligence()
	{
		return this.intelligence;
	}

	/**
	 * Setting the intelligence value of a damage dealer player
	 * @param intelligence is the intelligence value of that damage dealer player
	 */
	public void setIntelligence(int intelligence)
	{
		this.intelligence = intelligence;
	}
}
