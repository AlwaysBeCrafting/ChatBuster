package stream.alwaysbecrafting.chatbuster.ecs.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import stream.alwaysbecrafting.chatbuster.ecs.Entities;
import stream.alwaysbecrafting.flare.GameEngine;
import stream.alwaysbecrafting.flare.GameSystem;

import static stream.alwaysbecrafting.chatbuster.ecs.component.state.HeadingComponent.HEADING_LEFT;

//==============================================================================
public class BulletSpawnSystem extends GameSystem {
	//--------------------------------------------------------------------------

	@Override public void onUpdate( GameEngine engine, double deltaTime ) {
		super.onUpdate( engine, deltaTime );

		if ( Gdx.input.isKeyJustPressed( Input.Keys.NUMPAD_0 )) {
			engine.add( Entities.makeEnemyBullet( 300, 48, HEADING_LEFT ));
		}
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
