package stream.alwaysbecrafting.chatbuster.ecs.system.physics;

import stream.alwaysbecrafting.chatbuster.ecs.component.physics.CollisionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.render.ColorDrawComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class CollisionDebugSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				CollisionComponent.class,
				ColorDrawComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		CollisionComponent collisionComp = entity.get( CollisionComponent.class );
		ColorDrawComponent colorComp = entity.get( ColorDrawComponent.class );

		if ( collisionComp.collisions.isEmpty() ) colorComp.color = 0xff00ff00;
		else                                      colorComp.color = 0xffff0000;
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
