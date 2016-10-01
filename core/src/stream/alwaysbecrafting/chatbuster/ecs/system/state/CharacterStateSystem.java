package stream.alwaysbecrafting.chatbuster.ecs.system.state;

import stream.alwaysbecrafting.chatbuster.ecs.component.state.CharacterHitstunStateComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.state.CharacterZorpStateComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

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

	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
