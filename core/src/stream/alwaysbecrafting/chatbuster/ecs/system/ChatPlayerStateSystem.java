package stream.alwaysbecrafting.chatbuster.ecs.system;

import stream.alwaysbecrafting.chatbuster.ecs.component.ChatPlayerStateComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class ChatPlayerStateSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	public ChatPlayerStateSystem() {
		requireAll( ChatPlayerStateComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, float deltaTime ) {
		ChatPlayerStateComponent stateComp = entity.get( ChatPlayerStateComponent.class );

		stateComp.machine.update( deltaTime );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
