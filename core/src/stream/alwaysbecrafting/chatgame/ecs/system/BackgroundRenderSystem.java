package stream.alwaysbecrafting.chatgame.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

//==============================================================================
public class BackgroundRenderSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	@Override public void addedToEngine( Engine e ) {
		super.addedToEngine( e );

		Gdx.gl.glClearColor( 1, 0, 0, 1 );
	}

	//--------------------------------------------------------------------------

	@Override public void update( float deltaTime ) {
		super.update( deltaTime );

		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
