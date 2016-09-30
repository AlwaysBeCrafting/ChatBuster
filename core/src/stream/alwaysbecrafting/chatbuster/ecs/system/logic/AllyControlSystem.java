package stream.alwaysbecrafting.chatbuster.ecs.system.logic;

import stream.alwaysbecrafting.chatbuster.ecs.component.logic.AllyGunComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.logic.AllyMovementComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class AllyControlSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				AllyMovementComponent.class,
				AllyGunComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		AllyMovementComponent moveComp = entity.get( AllyMovementComponent.class );
		moveComp.state.update( deltaTime );

		AllyGunComponent gunComp = entity.get( AllyGunComponent.class );
		gunComp.state.update( deltaTime );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
