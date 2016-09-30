package stream.alwaysbecrafting.chatbuster.ecs.component;

import stream.alwaysbecrafting.chatbuster.util.Log;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.State;
import stream.alwaysbecrafting.flare.StateMachine;

//==============================================================================
public class AllyMovementComponent {
	//--------------------------------------------------------------------------

	public StateMachine characterState;

	//--------------------------------------------------------------------------

	public AllyMovementComponent() {
		characterState = new StateMachine();

		characterState.add( "zorpIn", new State() );
		characterState.add( "stand", new State() );
		characterState.add( "jump", new JumpState() );
		characterState.add( "fall", new FallState() );
		characterState.add( "takeDamage", new State() );
		characterState.add( "zorpOut", new State() );
	}

	//--------------------------------------------------------------------------

	//==========================================================================
	private class JumpState extends State {
		//----------------------------------------------------------------------

		VelocityComponent velocityComp;

		//----------------------------------------------------------------------

		@Override public void onEnter( Object... params ) {
			Entity entity = (Entity)params[0];

			entity.add( new GravityComponent( 1 ));
			entity.get( VelocityComponent.class ).v = 5;
			entity.get( SpriteComponent.class ).setCellX( 1 );
		}

		//----------------------------------------------------------------------

		@Override public void onUpdate( double deltaTime ) {
			Log.d( "jumping" );
			if ( velocityComp.v < 0 ) characterState.change( "fall", velocityComp );
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
