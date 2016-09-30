package stream.alwaysbecrafting.chatbuster.ecs.component;

import stream.alwaysbecrafting.chatbuster.util.Log;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.State;
import stream.alwaysbecrafting.flare.StateMachine;

//==============================================================================
public class AllyGunComponent {
	//--------------------------------------------------------------------------

	public StateMachine gunState;

	//--------------------------------------------------------------------------

	public AllyGunComponent() {
		gunState = new StateMachine();

		gunState.add( "idle", new State() );
		gunState.add( "charge", new ChargeState() );
		gunState.add( "shoot", new ShootState() );
	}

	//--------------------------------------------------------------------------

	//==========================================================================
	private class ChargeState extends State {
		//----------------------------------------------------------------------

		private int chargeLevel;

		//----------------------------------------------------------------------

		@Override public void onEnter( Object... params ) {
			Entity entity = (Entity)params[0];

			entity.get( SpriteComponent.class ).setCellY( 1 );
		}

		//----------------------------------------------------------------------
	}
	//--------------------------------------------------------------------------

	//==========================================================================
	private class ShootState extends State {
		//----------------------------------------------------------------------

		@Override public void onEnter( Object... params ) {
			Entity entity = (Entity)params[0];

			entity.get( SpriteComponent.class ).setCellY( 1 );
		}

		//----------------------------------------------------------------------

		@Override public void onUpdate( double deltaTime ) {
			Log.d( "shooting" );
			gunState.change( "idle" );
		}

		//----------------------------------------------------------------------
	}
	//--------------------------------------------------------------------------

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
