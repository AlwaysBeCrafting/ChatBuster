package stream.alwaysbecrafting.chatbuster.ecs.system.physics;

import com.badlogic.gdx.math.Rectangle;

import org.junit.Assert;
import org.junit.Test;

import static stream.alwaysbecrafting.chatbuster.data.Collision.BOTTOM;
import static stream.alwaysbecrafting.chatbuster.data.Collision.LEFT;
import static stream.alwaysbecrafting.chatbuster.data.Collision.RIGHT;
import static stream.alwaysbecrafting.chatbuster.data.Collision.TOP;

//==============================================================================
public class CollisionDetectionSystemTest {
	//--------------------------------------------------------------------------

	@Test public void GetQuadrant_AtLeftBoundary_ReturnsLeft() {
		Rectangle bounds = new Rectangle( 0, 0, 13, 13);

		Assert.assertEquals( CollisionDetectionSystem.getQuadrant( bounds, 0, 7 ), LEFT );
	}

	//--------------------------------------------------------------------------

	@Test public void GetQuadrant_AtRightBoundary_ReturnsRight() {
		Rectangle bounds = new Rectangle( 0, 0, 13, 13);

		Assert.assertEquals( CollisionDetectionSystem.getQuadrant( bounds, 13, 7 ), RIGHT );
	}

	//--------------------------------------------------------------------------

	@Test public void GetQuadrant_AtTopBoundary_ReturnsTop() {
		Rectangle bounds = new Rectangle( 0, 0, 13, 13);

		Assert.assertEquals( CollisionDetectionSystem.getQuadrant( bounds, 7, 13 ), TOP );
	}

	//--------------------------------------------------------------------------

	@Test public void GetQuadrant_AtBottomBoundary_ReturnsBottom() {
		Rectangle bounds = new Rectangle( 0, 0, 13, 13);

		Assert.assertEquals( CollisionDetectionSystem.getQuadrant( bounds, 7, 0 ), BOTTOM );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------