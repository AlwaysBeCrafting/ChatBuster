package stream.alwaysbecrafting.chatbuster.ecs.system.physics;

import stream.alwaysbecrafting.chatbuster.ecs.component.logic.ChatControllerComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.logic.PlayerControllerComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.CollisionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.GravityComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.PositionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.VelocityComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

import static stream.alwaysbecrafting.chatbuster.data.Collision.BOTTOM;
import static stream.alwaysbecrafting.chatbuster.data.Collision.LEFT;
import static stream.alwaysbecrafting.chatbuster.data.Collision.RIGHT;
import static stream.alwaysbecrafting.chatbuster.data.Collision.TOP;

//==============================================================================
public class CharacterCollisionSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				CollisionComponent.class,
				VelocityComponent.class,
				PositionComponent.class )
		&&     entity.hasAny(
				PlayerControllerComponent.class,
				ChatControllerComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		CollisionComponent collisionComp = entity.get( CollisionComponent.class );
		VelocityComponent velocityComp = entity.get( VelocityComponent.class );
		PositionComponent positionComp = entity.get( PositionComponent.class );

		collisionComp.collisions.forEach( collision -> {
			if ( collision.quadrant == BOTTOM ) {
				entity.remove( GravityComponent.class );
				velocityComp.v = 0;
				positionComp.y += collision.intersection.height;
			}

			if ( collision.quadrant == TOP ) {
				velocityComp.v = 0;
				positionComp.y -= collision.intersection.height;
			}

			if ( collision.quadrant == LEFT ) {
				velocityComp.h = 0;
				positionComp.x += collision.intersection.width;
			}

			if ( collision.quadrant == RIGHT ) {
				velocityComp.h = 0;
				positionComp.x -= collision.intersection.width;
			}
		} );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
