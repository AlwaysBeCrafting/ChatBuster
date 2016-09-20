package stream.alwaysbecrafting.chatgame.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

//==============================================================================
public class SpriteComponent implements Component {
	//--------------------------------------------------------------------------

	public Texture sprite;

	//--------------------------------------------------------------------------

	public SpriteComponent() {}

	//--------------------------------------------------------------------------

	public SpriteComponent( String path ) {
		sprite = new Texture( path );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
