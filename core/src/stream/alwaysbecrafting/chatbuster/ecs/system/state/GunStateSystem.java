package stream.alwaysbecrafting.chatbuster.ecs.system.state;

import stream.alwaysbecrafting.chatbuster.ecs.component.state.GunChargeStateComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.state.GunShootStateComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class GunStateSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAny(
				GunChargeStateComponent.class,
				GunShootStateComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {

	}
	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
