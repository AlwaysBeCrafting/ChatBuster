package stream.alwaysbecrafting.chatbuster.ecs.system.state;

import stream.alwaysbecrafting.chatbuster.ecs.component.state.CharacterHitstunStateComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.state.CharacterZorpStateComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

import static stream.alwaysbecrafting.chatbuster.ecs.component.state.CharacterZorpStateComponent.IN;

//==============================================================================
public class CharacterStateSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAny(
				CharacterHitstunStateComponent.class,
				CharacterZorpStateComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		if ( entity.has( CharacterHitstunStateComponent.class )) {
			CharacterHitstunStateComponent comp = entity.get( CharacterHitstunStateComponent.class );

			if ( comp.durationRemaining <= 0 ) {
				entity.remove( comp );

			} else comp.durationRemaining -= deltaTime;
		}

		if ( entity.has( CharacterZorpStateComponent.class )) {
			CharacterZorpStateComponent comp = entity.get( CharacterZorpStateComponent.class );

			if ( comp.durationRemaining <= 0 ) {

				if ( comp.direction == IN ) entity.remove( comp );
				else entity.getEngine().remove( entity );

			} else comp.durationRemaining -= deltaTime;
		}
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
