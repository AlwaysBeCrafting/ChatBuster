package stream.alwaysbecrafting.chatbuster.ecs.component;

import stream.alwaysbecrafting.chatbuster.util.Log;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.State;
import stream.alwaysbecrafting.flare.StateMachine;

//==============================================================================
public class AllyStateComponent {
	//--------------------------------------------------------------------------

	public StateMachine characterState;
	public StateMachine gunState;

	public int cellX = 0;
	public int cellY = 0;

	//--------------------------------------------------------------------------

	public AllyStateComponent() {
		characterState = new StateMachine();
		gunState = new StateMachine();


		characterState.add( "zorpIn", new State() );
		characterState.add( "stand", new State() );
		characterState.add( "jump", new JumpState() );
		characterState.add( "fall", new FallState() );
		characterState.add( "takeDamage", new State() );
		characterState.add( "zorpOut", new State() );

		gunState.add( "idle", new State() );
		gunState.add( "charge", new ChargeState() );
		gunState.add( "shoot", new ShootState() );


		characterState.change( "fall" );
		gunState.change( "idle" );
	}

	//--------------------------------------------------------------------------

	//==========================================================================
	private class JumpState extends State {
		//----------------------------------------------------------------------

		VelocityComponent velocityComp;

		//----------------------------------------------------------------------

		@Override public void onEnter( Object... params ) {
			velocityComp = ( (Entity) params[0] ).get( VelocityComponent.class );
			velocityComp.v = 5;

			SpriteComponent spriteComp = ( (Entity) params[0] ).get( SpriteComponent.class );

			spriteComp.setCellX( 1 );
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
			cellX = 1;
		}

		//----------------------------------------------------------------------

		@Override public void onUpdate( double deltaTime ) {
			Log.d( "falling" );
		}

		//----------------------------------------------------------------------
	}
	//--------------------------------------------------------------------------


	//==========================================================================
	private class ChargeState extends State {
		//----------------------------------------------------------------------

		private int chargeLevel;

		//----------------------------------------------------------------------

		@Override public void onEnter( Object... params ) {
			cellY = 1;
		}

		//----------------------------------------------------------------------
	}
	//--------------------------------------------------------------------------

	//==========================================================================
	private class ShootState extends State {
		//----------------------------------------------------------------------

		@Override public void onEnter( Object... params ) {
			Integer strength = (Integer)params[0];
			cellY = 1;
		}

		//----------------------------------------------------------------------

		@Override public void onUpdate( double deltaTime ) {
			Log.d( "shooting" );
			gunState.change( "idle" );
		}

		//----------------------------------------------------------------------
	}
	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
