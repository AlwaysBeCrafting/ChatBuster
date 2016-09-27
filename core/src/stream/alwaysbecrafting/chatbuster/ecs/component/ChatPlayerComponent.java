package stream.alwaysbecrafting.chatbuster.ecs.component;

import stream.alwaysbecrafting.chatbuster.util.Log;
import stream.alwaysbecrafting.flare.State;
import stream.alwaysbecrafting.flare.StateMachine;

//==============================================================================
public class ChatPlayerComponent {
	//--------------------------------------------------------------------------

	public String username;
	public String message;

	public StateMachine stateMachine;

	//--------------------------------------------------------------------------

	public ChatPlayerComponent( String username, String message ) {
		this.username = username;
		this.message = message;



		stateMachine = new StateMachine();


		stateMachine.add( "zorpIn", new State() {

		} );

		stateMachine.add( "stand", new State() {
			@Override public void onUpdate( double deltaTime ) {
				Log.d( "standing by..." );
			}
		} );

		stateMachine.add( "shoot", new State() {

		} );

		stateMachine.add( "jump", new State() {

		} );

		stateMachine.add( "fall", new State() {

		} );

		stateMachine.add( "takeDamage", new State() {

		} );

		stateMachine.add( "zorpOut", new State() {

		} );



		stateMachine.change( "stand" );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
