package stream.alwaysbecrafting.chatbuster.ecs.component.render;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import stream.alwaysbecrafting.chatbuster.data.SpriteMap;

//==============================================================================
public class SpriteComponent {
	//--------------------------------------------------------------------------

	public Sprite sprite;

	public Vector2 origin = new Vector2();

	private final SpriteMap SPRITE_MAP;

	//--------------------------------------------------------------------------

	public SpriteComponent( String path ) {
		sprite = new Sprite( new Texture( path ));
		SPRITE_MAP = new SpriteMap( (int)sprite.getWidth(), (int)sprite.getHeight() );
	}

	//--------------------------------------------------------------------------

	public SpriteComponent( String path, SpriteMap spriteMap ) {
		sprite = new Sprite( new Texture( path ));
		SPRITE_MAP = spriteMap;
	}

	//--------------------------------------------------------------------------

	public SpriteComponent( String path, SpriteMap spriteMap, int originX, int originY ) {
		this( path, spriteMap );
		origin.set( originX, originY );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
