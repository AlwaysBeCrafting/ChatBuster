package stream.alwaysbecrafting.chatbuster.ecs.system.physics;

import stream.alwaysbecrafting.chatbuster.ecs.component.physics.CollisionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.stats.DamageComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.stats.HealthComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class DamagingCollisionSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				DamageComponent.class,
				CollisionComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		DamageComponent damageComp = entity.get( DamageComponent.class );
		CollisionComponent collisionComp = entity.get( CollisionComponent.class );

		collisionComp.collisions.stream()
				.filter( collision -> collision.other.has( HealthComponent.class ))
				.map( collision -> collision.other.get( HealthComponent.class ))
				.forEach( health -> health.damageReceived += damageComp.damage );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
