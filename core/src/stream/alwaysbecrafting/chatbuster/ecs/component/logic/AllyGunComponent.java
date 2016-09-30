package stream.alwaysbecrafting.chatbuster.ecs.component.logic;

import stream.alwaysbecrafting.chatbuster.ecs.component.render.SpriteComponent;
import stream.alwaysbecrafting.chatbuster.util.Log;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.State;
import stream.alwaysbecrafting.flare.StateMachine;

//==============================================================================
public class AllyGunComponent {
	//--------------------------------------------------------------------------

	public StateMachine state;

	//--------------------------------------------------------------------------

	public AllyGunComponent() {
		state = new StateMachine();

		state.add( "idle", new State() );
		state.add( "charge", new ChargeState() );
		state.add( "shoot", new ShootState() );
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
			state.change( "idle" );
		}

		//----------------------------------------------------------------------
	}
	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
