package stream.alwaysbecrafting.chatbuster.ecs.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import stream.alwaysbecrafting.chatbuster.util.Colors;
import stream.alwaysbecrafting.flare.GameEngine;
import stream.alwaysbecrafting.flare.GameSystem;

import static stream.alwaysbecrafting.chatbuster.util.Colors.Solarized.BASE3;

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

	@Override public void onUpdate( GameEngine engine, double deltaTime ) {
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
