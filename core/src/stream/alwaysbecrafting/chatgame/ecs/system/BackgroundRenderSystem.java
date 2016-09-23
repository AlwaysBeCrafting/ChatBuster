package stream.alwaysbecrafting.chatgame.ecs.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import stream.alwaysbecrafting.chatgame.util.Colors;
import stream.alwaysbecrafting.ecs.GameEngine;
import stream.alwaysbecrafting.ecs.GameSystem;

import static stream.alwaysbecrafting.chatgame.util.Colors.Solarized.BASE3;

//==============================================================================
public class BackgroundRenderSystem extends GameSystem {
	//--------------------------------------------------------------------------

	@Override public void onStart( GameEngine engine ) {
		super.onStart( engine );

		Gdx.gl.glClearColor(
				Colors.r( BASE3 ),
				Colors.g( BASE3 ),
				Colors.b( BASE3 ),
				Colors.a( BASE3 ));
	}

	//--------------------------------------------------------------------------

	@Override public void onUpdate( GameEngine engine, float deltaTime ) {
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
