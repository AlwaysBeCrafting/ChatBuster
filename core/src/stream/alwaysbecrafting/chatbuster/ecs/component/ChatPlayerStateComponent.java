package stream.alwaysbecrafting.chatbuster.ecs.component;

import stream.alwaysbecrafting.chatbuster.util.Log;
import stream.alwaysbecrafting.flare.State;
import stream.alwaysbecrafting.flare.StateMachine;

//==============================================================================
public class ChatPlayerStateComponent {
	//--------------------------------------------------------------------------

	public StateMachine machine;

	//--------------------------------------------------------------------------

	public ChatPlayerStateComponent() {
		machine = new StateMachine();



		machine.add( "zorpIn", new State() {

		} );

		machine.add( "stand", new State() {
			@Override public void onUpdate( double deltaTime ) {
				Log.d( "standing by..." );
			}
		} );

		machine.add( "shoot", new State() {

		} );

		machine.add( "jump", new State() {

		} );

		machine.add( "fall", new State() {

		} );

		machine.add( "takeDamage", new State() {

		} );

		machine.add( "zorpOut", new State() {

		} );



		machine.change( "stand" );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
