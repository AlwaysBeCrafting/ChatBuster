package stream.alwaysbecrafting.chatbuster.ecs.system;

import com.badlogic.gdx.Gdx;

import stream.alwaysbecrafting.chatbuster.ecs.component.AllyMovementComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.PlayerControllerComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.VelocityComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class PlayerInputSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				PlayerControllerComponent.class,
				VelocityComponent.class,
				AllyMovementComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		PlayerControllerComponent controlComp = entity.get( PlayerControllerComponent.class );
		AllyMovementComponent stateComp = entity.get( AllyMovementComponent.class );


		if ( Gdx.input.isKeyJustPressed( controlComp.key_a )) {
			stateComp.characterState.change( "jump", entity );
		}

		if ( Gdx.input.isKeyJustPressed( controlComp.key_b )) {
			stateComp.gunState.change( "shoot", entity, 1 );
		}

	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
