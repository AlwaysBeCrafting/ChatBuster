package stream.alwaysbecrafting.chatbuster.ecs.component;

import com.badlogic.gdx.graphics.Texture;

//==============================================================================
public class SpriteComponent {
	//--------------------------------------------------------------------------

	public Texture sprite;

	//--------------------------------------------------------------------------

	public SpriteComponent( String path ) {
		sprite = new Texture( path );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
