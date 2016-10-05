package stream.alwaysbecrafting.chatbuster.ecs.system.physics;

import com.badlogic.gdx.math.Rectangle;

import stream.alwaysbecrafting.chatbuster.data.Collision;
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

		engine.entityStream()
				.filter( other -> other != entity )
				.filter( other -> other.has( CollisionComponent.class ))
				.filter( other -> other.get( CollisionComponent.class ).sharesLayer( collisionComp ))

				.filter( other -> other.has( BoundingBoxComponent.class ))
				.filter( other -> other.get( BoundingBoxComponent.class ).intersects( boundsComp, INTERSECTION ))

				.forEach( other -> {
					Collision c = new Collision();
					int centerX = (int)( INTERSECTION.x + ( INTERSECTION.width / 2 ));
					int centerY = (int)( INTERSECTION.y + ( INTERSECTION.height / 2 ));
					c.other = other;
					c.intersection.set( INTERSECTION );
					c.quadrant = getQuadrant( boundsComp.rect, centerX, centerY );
					collisionComp.collisions.add( c );
				});
	}

	//--------------------------------------------------------------------------

	private static byte getQuadrant( Rectangle bounds, int x, int y ) {
		float slope = 1f / bounds.getAspectRatio();
		float centerX = bounds.x + ( bounds.width / 2 );
		float centerY = bounds.y + ( bounds.height / 2 );
		float relativeX = x - centerX;
		float relativeY = y - centerY;

		return (byte)(
				(( relativeY > relativeX *  slope ) ? 0b01 : 0 )
		|       (( relativeY > relativeX * -slope ) ? 0b10 : 0 ));
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
