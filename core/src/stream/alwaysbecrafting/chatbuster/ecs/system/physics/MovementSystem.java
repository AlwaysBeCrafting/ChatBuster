package stream.alwaysbecrafting.chatbuster.ecs.system.physics;

import stream.alwaysbecrafting.chatbuster.ecs.component.physics.PositionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.VelocityComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class MovementSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				PositionComponent.class,
				VelocityComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		PositionComponent positionComp = entity.get( PositionComponent.class );
		VelocityComponent velocityComp = entity.get( VelocityComponent.class );

		positionComp.x += velocityComp.h;
		positionComp.y += velocityComp.v;
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
