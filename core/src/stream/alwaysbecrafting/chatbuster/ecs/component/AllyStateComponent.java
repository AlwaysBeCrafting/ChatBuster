package stream.alwaysbecrafting.chatbuster.ecs.component;

import stream.alwaysbecrafting.flare.State;
import stream.alwaysbecrafting.flare.StateMachine;

//==============================================================================
public class AllyStateComponent {
	//--------------------------------------------------------------------------

	public StateMachine characterState;
	public StateMachine gunState;

	public AllyStateComponent() {
		characterState = new StateMachine();
		gunState = new StateMachine();


		characterState.add( "zorpIn", new State() {} );

		characterState.add( "stand", new State() {} );

		characterState.add( "jump", new State() {} );

		characterState.add( "fall", new State() {} );

		characterState.add( "takeDamage", new State() {} );

		characterState.add( "zorpOut", new State() {} );


		gunState.add( "idle", new State() {} );

		gunState.add( "charge", new State() {} );

		gunState.add( "shoot", new State() {} );


		characterState.change( "zorpIn" );
		gunState.change( "idle" );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
