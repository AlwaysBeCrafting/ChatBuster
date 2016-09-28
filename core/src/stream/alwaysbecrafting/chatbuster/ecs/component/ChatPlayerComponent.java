package stream.alwaysbecrafting.chatbuster.ecs.component;

import stream.alwaysbecrafting.flare.State;
import stream.alwaysbecrafting.flare.StateMachine;

//==============================================================================
public class ChatPlayerComponent {
	//--------------------------------------------------------------------------

	public String username;
	public String message;

	public StateMachine characterState;
	public StateMachine gunState;

	//--------------------------------------------------------------------------

	public ChatPlayerComponent( String username, String message ) {
		this.username = username;
		this.message = message;



		characterState = new StateMachine();
		gunState = new StateMachine();


		characterState.add( "zorpIn", new State() {} );

		characterState.add( "stand", new State() {
			@Override public void onUpdate( double deltaTime ) {}
		} );

		characterState.add( "jump", new State() {} );

		characterState.add( "fall", new State() {} );

		characterState.add( "takeDamage", new State() {} );

		characterState.add( "zorpOut", new State() {} );


		gunState.add( "idle", new State() {} );

		gunState.add( "charge", new State() {} );

		gunState.add( "shoot", new State() {} );


		characterState.change( "stand" );
		gunState.change( "idle" );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
