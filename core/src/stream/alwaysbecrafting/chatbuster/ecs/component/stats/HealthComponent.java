package stream.alwaysbecrafting.chatbuster.ecs.component.stats;

//==============================================================================
public class HealthComponent {
	//--------------------------------------------------------------------------

	public int health;
	public int maxHealth;
	public int damageReceived;

	//--------------------------------------------------------------------------

	public HealthComponent( int maxHealth ) {
		this( maxHealth, maxHealth );
	}

	//--------------------------------------------------------------------------

	public HealthComponent( int maxHealth, int health ) {
		this.maxHealth = maxHealth;
		this.health = health;
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
