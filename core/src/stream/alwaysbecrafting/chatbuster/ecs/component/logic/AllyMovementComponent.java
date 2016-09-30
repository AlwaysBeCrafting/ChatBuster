package stream.alwaysbecrafting.chatbuster.ecs.component.logic;

import stream.alwaysbecrafting.chatbuster.ecs.component.physics.GravityComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.VelocityComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.render.SpriteComponent;
import stream.alwaysbecrafting.chatbuster.util.Log;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.State;
import stream.alwaysbecrafting.flare.StateMachine;

//==============================================================================
public class AllyMovementComponent {
	//--------------------------------------------------------------------------

	public StateMachine state;

	//--------------------------------------------------------------------------

	public AllyMovementComponent() {
		state = new StateMachine();

		state.add( "zorpIn", new State() );
		state.add( "stand", new State() );
		state.add( "jump", new JumpState() );
		state.add( "fall", new FallState() );
		state.add( "takeDamage", new State() );
		state.add( "zorpOut", new State() );
	}

	//--------------------------------------------------------------------------

	//==========================================================================
	private class JumpState extends State {
		//----------------------------------------------------------------------

		VelocityComponent velocityComp;

		//----------------------------------------------------------------------

		@Override public void onEnter( Object... params ) {
			( (GravityComponent) params[0] ).force = 1;
			( (VelocityComponent)params[1] ).v     = 5;
			( (SpriteComponent)  params[2] ).setCellX( 1 );
		}

		//----------------------------------------------------------------------

		@Override public void onUpdate( double deltaTime ) {
			if ( velocityComp.v < 0 ) state.change( "fall", velocityComp );
		}

		//----------------------------------------------------------------------
	}
	//--------------------------------------------------------------------------

	//==========================================================================
	private class FallState extends State {
		//----------------------------------------------------------------------

		@Override public void onEnter( Object... params ) {
			Entity entity = (Entity)params[0];

			entity.add( new GravityComponent( 1 ));
			entity.get( SpriteComponent.class ).setCellX( 1 );
		}

		//----------------------------------------------------------------------

		@Override public void onUpdate( double deltaTime ) {
			Log.d( "falling" );
		}

		//----------------------------------------------------------------------
	}
	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
