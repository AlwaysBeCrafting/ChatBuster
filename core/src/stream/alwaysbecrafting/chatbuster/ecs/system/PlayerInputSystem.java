package stream.alwaysbecrafting.chatbuster.ecs.system;

import com.badlogic.gdx.Gdx;

import stream.alwaysbecrafting.chatbuster.ecs.component.PlayerControllerComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.PositionComponent;
import stream.alwaysbecrafting.ecs.GameEngine;
import stream.alwaysbecrafting.ecs.EntitySystem;

//==============================================================================
public class PlayerInputSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	public PlayerInputSystem() {
		requireAll(
				PlayerControllerComponent.class,
				PositionComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( GameEngine engine, long entityId, float deltaTime ) {
		PlayerControllerComponent controller = engine.getComponent( entityId, PlayerControllerComponent.class );
		PositionComponent position = engine.getComponent( entityId, PositionComponent.class );


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
