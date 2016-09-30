package stream.alwaysbecrafting.chatbuster.ecs.system;

import stream.alwaysbecrafting.chatbuster.ecs.component.AllyStateComponent;
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
				AllyStateComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		CollisionComponent collisionComp = entity.get( CollisionComponent.class );
		AllyStateComponent allyComp = entity.get( AllyStateComponent.class );

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
