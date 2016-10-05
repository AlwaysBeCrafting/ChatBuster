package stream.alwaysbecrafting.chatbuster.data;


import com.badlogic.gdx.math.Rectangle;

import stream.alwaysbecrafting.flare.Entity;

//==============================================================================
public class Collision {
	//--------------------------------------------------------------------------

	public static byte DOWN   = 0;
	public static byte LEFT   = 1;
	public static byte RIGHT  = 2;
	public static byte UP     = 3;

	public Entity other;
	public Rectangle intersection = new Rectangle();

	public int quadrant;

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
