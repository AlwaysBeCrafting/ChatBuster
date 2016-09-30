package stream.alwaysbecrafting.chatbuster.ecs.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

//==============================================================================
public class SpriteComponent {
	//--------------------------------------------------------------------------

	public Sprite sprite;
	public int cellWidth;
	public int cellHeight;

	public int cellX;
	public int cellY;

	//--------------------------------------------------------------------------

	public SpriteComponent( String path ) {
		sprite = new Sprite( new Texture( path ));
		cellWidth = sprite.getTexture().getWidth();
		cellHeight = sprite.getTexture().getHeight();

		cellX = 0;
		cellY = 0;
	}

	//--------------------------------------------------------------------------

	public SpriteComponent( String path, int cellWidth, int cellHeight ) {
		this( path );

		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
