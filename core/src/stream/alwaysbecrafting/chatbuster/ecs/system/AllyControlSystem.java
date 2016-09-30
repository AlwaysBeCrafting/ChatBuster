package stream.alwaysbecrafting.chatbuster.ecs.system;

import stream.alwaysbecrafting.chatbuster.ecs.component.AllyGunComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.AllyMovementComponent;
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
		if ( moveComp.characterState.is( "" )) moveComp.characterState.change( "fall", entity );
		moveComp.characterState.update( deltaTime );

		AllyGunComponent gunComp = entity.get( AllyGunComponent.class );
		if ( gunComp.gunState.is( "" )) gunComp.gunState.change( "idle", entity );
		gunComp.gunState.update( deltaTime );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
