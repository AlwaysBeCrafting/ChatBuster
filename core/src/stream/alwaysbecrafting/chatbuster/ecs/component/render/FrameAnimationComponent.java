package stream.alwaysbecrafting.chatbuster.ecs.component.render;

//==============================================================================
public class FrameAnimationComponent {
	//--------------------------------------------------------------------------

	public int frameCount;
	public double framerate;

	public double loopElapsed = 0;

	//--------------------------------------------------------------------------

	public FrameAnimationComponent( int frameCount, double framerate ) {
		this.frameCount = frameCount;
		this.framerate = framerate;
	}

	//--------------------------------------------------------------------------

	public double loopDuration() {
		return frameCount / framerate;
	}

	//--------------------------------------------------------------------------

	public int currentFrame() {
		return (int)( loopElapsed * framerate );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
