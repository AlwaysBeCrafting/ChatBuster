package stream.alwaysbecrafting.chatbuster.ecs.system.physics;

import stream.alwaysbecrafting.chatbuster.ecs.component.physics.CollisionComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class CharacterCollisionSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				CollisionComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		CollisionComponent collisionComp = entity.get( CollisionComponent.class );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
