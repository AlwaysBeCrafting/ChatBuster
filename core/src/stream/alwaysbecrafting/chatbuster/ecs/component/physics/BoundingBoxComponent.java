package stream.alwaysbecrafting.chatbuster.ecs.component.physics;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

//==============================================================================
public class BoundingBoxComponent {
	//--------------------------------------------------------------------------

	public Rectangle rect;
	public Vector2 origin;

	//--------------------------------------------------------------------------

	public BoundingBoxComponent() {
		rect = new Rectangle();
		origin = new Vector2();
	}

	//--------------------------------------------------------------------------

	public BoundingBoxComponent( int width, int height ) {
		rect = new Rectangle( 0, 0, width, height );
		origin = new Vector2( width / 2f, height / 2f );
	}

	//--------------------------------------------------------------------------

	public BoundingBoxComponent( int width, int height, int originX, int originY ) {
		rect = new Rectangle( 0, 0, width, height );
		origin = new Vector2( originX, originY );
	}

	//--------------------------------------------------------------------------

	public void moveTo( int x, int y ) {
		rect.setPosition( x - origin.x, y - origin.y );
	}

	//--------------------------------------------------------------------------

	public boolean intersects( BoundingBoxComponent other ) {
		return Intersector.intersectRectangles( rect, other.rect, null );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
