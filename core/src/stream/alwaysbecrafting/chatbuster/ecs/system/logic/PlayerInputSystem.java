package stream.alwaysbecrafting.chatbuster.ecs.system.logic;

import com.badlogic.gdx.Gdx;

import stream.alwaysbecrafting.chatbuster.ecs.component.logic.PlayerControllerComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.GravityComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.VelocityComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.state.CharacterHitstunStateComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.state.CharacterZorpStateComponent;
import stream.alwaysbecrafting.chatbuster.util.Log;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class PlayerInputSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				PlayerControllerComponent.class )

		&& entity.hasNone(
				CharacterHitstunStateComponent.class,
				CharacterZorpStateComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		PlayerControllerComponent controlComp = entity.get( PlayerControllerComponent.class );
		VelocityComponent veloComp = entity.get( VelocityComponent.class );

		if ( Gdx.input.isKeyJustPressed( controlComp.key_a )
		&&   !entity.has( GravityComponent.class )) {
			veloComp.v = 7;
			entity.add( new GravityComponent( 0.5 ));
		}

		if ( Gdx.input.isKeyJustPressed( controlComp.key_b )) {
			Log.d( "shoot" );
		}
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
