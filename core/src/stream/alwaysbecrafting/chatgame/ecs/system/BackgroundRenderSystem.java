package stream.alwaysbecrafting.chatgame.ecs.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import stream.alwaysbecrafting.ecs.GameEngine;
import stream.alwaysbecrafting.ecs.GameSystem;

//==============================================================================
public class BackgroundRenderSystem extends GameSystem {
	//--------------------------------------------------------------------------

	@Override public void onStart( GameEngine engine ) {
		super.onStart( engine );

		Gdx.gl.glClearColor( 1, 0, 0, 1 );
	}

	//--------------------------------------------------------------------------

	@Override public void onUpdate( GameEngine engine, float deltaTime ) {
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
