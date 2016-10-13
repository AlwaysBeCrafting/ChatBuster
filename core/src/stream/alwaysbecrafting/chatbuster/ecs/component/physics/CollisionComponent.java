package stream.alwaysbecrafting.chatbuster.ecs.component.physics;

import java.util.ArrayList;
import java.util.List;

import stream.alwaysbecrafting.chatbuster.data.Collision;

//==============================================================================
public class CollisionComponent {
	//--------------------------------------------------------------------------

	public int activeLayers, passiveLayers;
	public List<Collision> collisions = new ArrayList<>();

	//--------------------------------------------------------------------------

	public CollisionComponent( int activeLayers, int passiveLayers ) {
		this.activeLayers = activeLayers;
		this.passiveLayers = passiveLayers;
	}

	//--------------------------------------------------------------------------

	public boolean canCollide( CollisionComponent other ) {
		return ( activeLayers & other.passiveLayers ) != 0;
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
