package stream.alwaysbecrafting.chatbuster.ecs.system.physics;

import stream.alwaysbecrafting.chatbuster.ecs.component.logic.AllyGunComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.logic.AllyMovementComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.logic.DamageComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.CollisionComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class ChatCharacterCollisionSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				CollisionComponent.class,
				AllyMovementComponent.class,
				AllyGunComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		CollisionComponent collisionComp = entity.get( CollisionComponent.class );
		AllyMovementComponent moveComp = entity.get( AllyMovementComponent.class );
		AllyGunComponent gunComp = entity.get( AllyGunComponent.class );

		if ( moveComp.state.is( "damage" )) {
			collisionComp.collisions.stream()
					.filter( other -> other.has( DamageComponent.class ))
					.findAny()
					.ifPresent( other -> {
						moveComp.state.change( "damage", other.get( DamageComponent.class ).damage );
						gunComp.state.change( "idle" );
					} );
		}

		collisionComp.collisions.stream()
				.filter( other -> !other.has( DamageComponent.class ))
				.forEach( other -> {
					moveComp.state.change( "stand" );
				} );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
