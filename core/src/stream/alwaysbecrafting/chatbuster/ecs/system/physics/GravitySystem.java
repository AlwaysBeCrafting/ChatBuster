package stream.alwaysbecrafting.chatbuster.ecs.system.physics;

import stream.alwaysbecrafting.chatbuster.ecs.component.physics.GravityComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.VelocityComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class GravitySystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				GravityComponent.class,
				VelocityComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		GravityComponent gravComp = entity.get( GravityComponent.class );
		VelocityComponent veloComp = entity.get( VelocityComponent.class );

		veloComp.v -= gravComp.force;
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
