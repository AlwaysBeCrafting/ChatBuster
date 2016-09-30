package stream.alwaysbecrafting.chatbuster.ecs.component.physics;

import java.util.ArrayList;
import java.util.List;

import stream.alwaysbecrafting.flare.Entity;

//==============================================================================
public class CollisionComponent {
	//--------------------------------------------------------------------------

	public int layers;
	public List<Entity> collisions = new ArrayList<>();

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
