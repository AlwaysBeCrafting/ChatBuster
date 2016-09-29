package stream.alwaysbecrafting.chatbuster.ecs.system;

import com.badlogic.gdx.Gdx;

import stream.alwaysbecrafting.chatbuster.ecs.component.AllyStateComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.PlayerControllerComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.VelocityComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class PlayerInputSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	public PlayerInputSystem() {
		requireAll(
				PlayerControllerComponent.class,
				VelocityComponent.class,
				AllyStateComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		PlayerControllerComponent controlComp = entity.get( PlayerControllerComponent.class );
		VelocityComponent velocityComp = entity.get( VelocityComponent.class );
		AllyStateComponent stateComp = entity.get( AllyStateComponent.class );


		if ( Gdx.input.isKeyJustPressed( controlComp.key_a )) {
			stateComp.characterState.change( "jump", velocityComp );
		}

		if ( Gdx.input.isKeyJustPressed( controlComp.key_b )) {
			stateComp.gunState.change( "shoot", 1 );
		}

	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
