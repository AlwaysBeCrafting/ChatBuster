package stream.alwaysbecrafting.chatbuster.ecs.system;

import com.badlogic.gdx.Gdx;

import stream.alwaysbecrafting.chatbuster.ecs.component.PlayerControllerComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.PositionComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class PlayerInputSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	public PlayerInputSystem() {
		requireAll(
				PlayerControllerComponent.class,
				PositionComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		PlayerControllerComponent controller = entity.get( PlayerControllerComponent.class );
		PositionComponent         position   = entity.get( PositionComponent.class         );


		if ( Gdx.input.isKeyJustPressed( controller.key_up )) {
			position.y += 20;
		}
		if ( Gdx.input.isKeyJustPressed( controller.key_down )) {
			position.y -= 20;
		}
		if ( Gdx.input.isKeyJustPressed( controller.key_right )) {
			position.x += 20;
		}
		if ( Gdx.input.isKeyJustPressed( controller.key_left )) {
			position.x -= 20;
		}
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
