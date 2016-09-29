package stream.alwaysbecrafting.chatbuster.ecs.component;

//==============================================================================
public class BoundingBoxComponent {
	//--------------------------------------------------------------------------

	public int left, bottom, right, top;

	//--------------------------------------------------------------------------

	public BoundingBoxComponent() {}

	//--------------------------------------------------------------------------

	public BoundingBoxComponent( int left, int bottom, int right, int top ) {
		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.top = top;
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
