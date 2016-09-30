package stream.alwaysbecrafting.chatbuster.ecs.system;

import stream.alwaysbecrafting.chatbuster.ecs.component.AllyMovementComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.CollisionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.DamageComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class ChatCharacterCollisionSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				CollisionComponent.class,
				AllyMovementComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		CollisionComponent collisionComp = entity.get( CollisionComponent.class );
		AllyMovementComponent allyComp = entity.get( AllyMovementComponent.class );

		if ( allyComp.characterState.is( "damage" )) {
			collisionComp.collisions.stream()
					.filter( other -> other.has( DamageComponent.class ))
					.findAny()
					.ifPresent( other -> {
						allyComp.characterState.change( "damage" );
						allyComp.gunState.change( "idle" );
					} );
		}

		collisionComp.collisions.stream()
				.filter( other -> !other.has( DamageComponent.class ))
				.forEach( other -> {
					allyComp.characterState.change( "stand" );
				} );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
