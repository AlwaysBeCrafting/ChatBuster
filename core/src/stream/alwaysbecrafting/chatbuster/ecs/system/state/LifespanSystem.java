package stream.alwaysbecrafting.chatbuster.ecs.system.state;

import stream.alwaysbecrafting.chatbuster.ecs.component.state.LifespanComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class LifespanSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.has( LifespanComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		LifespanComponent lifeComp = entity.get( LifespanComponent.class );

		lifeComp.timeRemaining -= deltaTime;

		if ( lifeComp.timeRemaining <= 0 ) entity.getEngine().remove( entity );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
