package stream.alwaysbecrafting.chatbuster.ecs.component.physics;

import java.util.ArrayList;
import java.util.List;

import stream.alwaysbecrafting.chatbuster.data.Collision;

//==============================================================================
public class CollisionComponent {
	//--------------------------------------------------------------------------

	public int layers;
	public List<Collision> collisions = new ArrayList<>();

	//--------------------------------------------------------------------------

	public CollisionComponent( int layers ) {
		this.layers = layers;
	}

	//--------------------------------------------------------------------------

	public boolean sharesLayer( CollisionComponent other ) {
		return ( layers & other.layers ) != 0;
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
