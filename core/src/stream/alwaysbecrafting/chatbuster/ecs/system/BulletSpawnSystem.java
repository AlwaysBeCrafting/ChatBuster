package stream.alwaysbecrafting.chatbuster.ecs.system;

import stream.alwaysbecrafting.chatbuster.ecs.Entities;
import stream.alwaysbecrafting.chatbuster.util.Log;
import stream.alwaysbecrafting.flare.GameEngine;
import stream.alwaysbecrafting.flare.GameSystem;

import static stream.alwaysbecrafting.chatbuster.ecs.component.state.HeadingComponent.HEADING_LEFT;

//==============================================================================
public class BulletSpawnSystem extends GameSystem {
	//--------------------------------------------------------------------------

	private double timeToNextBullet = 5;

	//--------------------------------------------------------------------------

	@Override public void onUpdate( GameEngine engine, double deltaTime ) {
		super.onUpdate( engine, deltaTime );
		timeToNextBullet -= deltaTime;

		if ( timeToNextBullet <= 0 ) {
			Log.d( "Create bullet" );
			engine.add( Entities.makeEnemyBullet( 300, 48, HEADING_LEFT ));
			timeToNextBullet = 5;
		}
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
