package stream.alwaysbecrafting.chatbuster.ecs.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

//==============================================================================
public class SpriteComponent {
	//--------------------------------------------------------------------------

	public Sprite sprite;
	public int cellWidth;
	public int cellHeight;

	//--------------------------------------------------------------------------

	public SpriteComponent( String path ) {
		sprite = new Sprite( new Texture( path ));
		cellWidth = sprite.getTexture().getWidth();
		cellHeight = sprite.getTexture().getHeight();
	}

	//--------------------------------------------------------------------------

	public SpriteComponent( String path, int cellWidth, int cellHeight ) {
		this( path );

		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;

		setCell( 0, 0 );
	}

	//--------------------------------------------------------------------------

	public void setCell( int cellX, int cellY ) {
		sprite.setRegion(
				cellX * cellWidth,
				cellY * cellHeight,
				cellWidth,
				cellHeight );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
