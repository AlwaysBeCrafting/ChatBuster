package stream.alwaysbecrafting.chatbuster.ecs.system;

import stream.alwaysbecrafting.chatbuster.ecs.component.AllyStateComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class AllyStateSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.has( AllyStateComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		AllyStateComponent stateComp = entity.get( AllyStateComponent.class );

		if ( stateComp.characterState.is( "" )) stateComp.characterState.change( "fall", entity );
		if ( stateComp.gunState.is( "" )) stateComp.gunState.change( "idle", entity );

		stateComp.characterState.update( deltaTime );
		stateComp.gunState.update( deltaTime );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
