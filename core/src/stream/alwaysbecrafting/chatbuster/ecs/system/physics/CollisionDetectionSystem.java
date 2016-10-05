package stream.alwaysbecrafting.chatbuster.ecs.system.physics;

import com.badlogic.gdx.math.Rectangle;

import java.util.stream.Stream;

import stream.alwaysbecrafting.chatbuster.ecs.component.physics.BoundingBoxComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.CollisionComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;
import stream.alwaysbecrafting.flare.GameEngine;

//==============================================================================
public class CollisionDetectionSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	private final Rectangle INTERSECTION = new Rectangle();

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

		collisionComp.collisions.clear();

		Stream<Entity> eStream = engine.entityStream();
		eStream = eStream.filter( other -> other.has( CollisionComponent.class ));
		eStream = eStream.filter( other -> other.get( CollisionComponent.class ).sharesLayer( collisionComp ));

		eStream = eStream.filter( other -> other.has( BoundingBoxComponent.class ));
		eStream = eStream.filter( other -> other.get( BoundingBoxComponent.class ).intersects( boundsComp, INTERSECTION ));

		eStream.forEach( other -> collisionComp.collisions.add( other ));
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
