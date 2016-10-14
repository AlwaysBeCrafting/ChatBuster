package stream.alwaysbecrafting.chatbuster.ecs.system.physics;

import stream.alwaysbecrafting.chatbuster.ecs.component.physics.CollisionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.stats.PierceComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class BulletCollisionSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				PierceComponent.class,
				CollisionComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		PierceComponent pierceComp = entity.get( PierceComponent.class );
		CollisionComponent collisionComp = entity.get( CollisionComponent.class );

		pierceComp.collisionsRemaining -= collisionComp.collisions.size();
		if ( pierceComp.collisionsRemaining <= 0 ) entity.getEngine().remove( entity );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
