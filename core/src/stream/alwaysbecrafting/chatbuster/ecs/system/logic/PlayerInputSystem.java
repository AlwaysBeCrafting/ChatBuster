package stream.alwaysbecrafting.chatbuster.ecs.system.logic;

import com.badlogic.gdx.Gdx;

import stream.alwaysbecrafting.chatbuster.ecs.component.logic.AllyGunComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.logic.AllyMovementComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.logic.PlayerControllerComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.VelocityComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class PlayerInputSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				PlayerControllerComponent.class,
				VelocityComponent.class,
				AllyMovementComponent.class,
				AllyGunComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		PlayerControllerComponent controlComp = entity.get( PlayerControllerComponent.class );

		AllyMovementComponent moveComp = entity.get( AllyMovementComponent.class );
		AllyGunComponent gunComp = entity.get( AllyGunComponent.class );


		if ( Gdx.input.isKeyJustPressed( controlComp.key_a )) {
			moveComp.state.change( "jump" );
		}

		if ( Gdx.input.isKeyJustPressed( controlComp.key_b )) {
			gunComp.state.change( "shoot" );
		}
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
