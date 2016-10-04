package stream.alwaysbecrafting.chatbuster.ecs.system.logic;

import com.badlogic.gdx.Gdx;

import stream.alwaysbecrafting.chatbuster.ecs.component.logic.PlayerControllerComponent;
import stream.alwaysbecrafting.chatbuster.util.Log;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class PlayerInputSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				PlayerControllerComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		PlayerControllerComponent controlComp = entity.get( PlayerControllerComponent.class );

		if ( Gdx.input.isKeyJustPressed( controlComp.key_a )) {
			Log.d( "jump" );
		}

		if ( Gdx.input.isKeyJustPressed( controlComp.key_b )) {
			Log.d( "shoot" );
		}
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
