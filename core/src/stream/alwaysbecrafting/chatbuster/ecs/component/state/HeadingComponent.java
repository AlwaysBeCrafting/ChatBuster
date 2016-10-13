package stream.alwaysbecrafting.chatbuster.ecs.component.state;

//==============================================================================
public class HeadingComponent {
	//--------------------------------------------------------------------------

	public static final byte HEADING_LEFT  = -1;
	public static final byte HEADING_RIGHT =  1;

	//--------------------------------------------------------------------------

	public byte heading =  HEADING_RIGHT;

	//--------------------------------------------------------------------------

	public HeadingComponent( byte heading ) {
		this.heading = heading;
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
