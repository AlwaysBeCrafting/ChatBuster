package stream.alwaysbecrafting.chatbuster.ecs.system.physics;

import stream.alwaysbecrafting.chatbuster.ecs.component.physics.BoundingBoxComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.PositionComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class BoundingBoxPositioningSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				PositionComponent.class,
				BoundingBoxComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		PositionComponent posComp = entity.get( PositionComponent.class );
		BoundingBoxComponent bboxComp = entity.get( BoundingBoxComponent.class );

		bboxComp.moveTo( posComp.x, posComp.y );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
