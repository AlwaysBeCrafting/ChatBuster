package stream.alwaysbecrafting.chatbuster.ecs.component.render;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

//==============================================================================
public class SpriteComponent {
	//--------------------------------------------------------------------------

	public Sprite sprite;
	public int cellWidth;
	public int cellHeight;

	private int cellX, cellY;

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
		this.cellX = cellX;
		this.cellY = cellY;

		sprite.setRegion(
				cellX * cellWidth,
				cellY * cellHeight,
				cellWidth,
				cellHeight );
	}

	//--------------------------------------------------------------------------

	public void setCellX( int cellX ) {
		this.cellX = cellX;
		setCell( cellX, cellY );
	}

	//--------------------------------------------------------------------------

	public void setCellY( int cellY ) {
		this.cellY = cellY;
		setCell( cellX, cellY );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
