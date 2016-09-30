package stream.alwaysbecrafting.chatbuster.ecs.component.physics;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

//==============================================================================
public class BoundingBoxComponent {
	//--------------------------------------------------------------------------

	public Rectangle rect;

	//--------------------------------------------------------------------------

	public BoundingBoxComponent() {
		rect = new Rectangle();
	}

	//--------------------------------------------------------------------------

	public BoundingBoxComponent( int left, int bottom, int right, int top ) {
		rect = new Rectangle( left, bottom, right - left, top - bottom );
	}

	//--------------------------------------------------------------------------

	public boolean intersects( BoundingBoxComponent other ) {
		return Intersector.intersectRectangles( rect, other.rect, null );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
