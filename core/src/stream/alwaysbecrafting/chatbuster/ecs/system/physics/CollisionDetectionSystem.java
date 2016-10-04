package stream.alwaysbecrafting.chatbuster.ecs.system.physics;

import stream.alwaysbecrafting.chatbuster.ecs.component.physics.BoundingBoxComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.CollisionComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;
import stream.alwaysbecrafting.flare.GameEngine;

//==============================================================================
public class CollisionDetectionSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll( CollisionComponent.class )
		&&     entity.hasAny( BoundingBoxComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		GameEngine engine = entity.getEngine();
		CollisionComponent collisionComp = entity.get( CollisionComponent.class );
		BoundingBoxComponent boundsComp = entity.get( BoundingBoxComponent.class );

		engine.entityStream()
				.filter( other -> other.has( CollisionComponent.class ))
				.filter( other -> other.get( CollisionComponent.class ).sharesLayer( collisionComp ))

				.filter( other -> other.has( BoundingBoxComponent.class ))
				.filter( other -> other.get( BoundingBoxComponent.class ).intersects( boundsComp ))

				.forEach( other -> collisionComp.collisions.add( other ));
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
