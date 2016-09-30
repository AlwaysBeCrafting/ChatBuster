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
		entity.get( AllyStateComponent.class ).characterState.update( deltaTime );
		entity.get( AllyStateComponent.class ).gunState.update( deltaTime );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
