package stream.alwaysbecrafting.chatbuster.ecs.system.stats;

import stream.alwaysbecrafting.chatbuster.ecs.component.stats.HealthComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class DamageSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.has( HealthComponent.class )
		&&     entity.get( HealthComponent.class ).damageReceived > 0;
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		HealthComponent healthComp = entity.get( HealthComponent.class );

		healthComp.health -= healthComp.damageReceived;
		healthComp.damageReceived = 0;
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
