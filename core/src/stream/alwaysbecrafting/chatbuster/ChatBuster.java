package stream.alwaysbecrafting.chatbuster;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.math.Matrix4;

import stream.alwaysbecrafting.chatbuster.ecs.Entities;
import stream.alwaysbecrafting.chatbuster.ecs.system.logic.PlayerInputSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.physics.BoundingBoxPositioningSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.physics.CharacterCollisionSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.physics.CollisionDebugSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.physics.CollisionDetectionSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.physics.GravitySystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.physics.MovementSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.render.BackgroundRenderSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.render.BoxRenderSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.render.SpriteRenderSystem;
import stream.alwaysbecrafting.flare.GameEngine;

//==============================================================================
public class ChatBuster extends ApplicationAdapter {
	//--------------------------------------------------------------------------

	private final String TOKEN;

	private GameEngine engine;

	//--------------------------------------------------------------------------

	public ChatBuster( String token ) {
		TOKEN = token;
	}

	//--------------------------------------------------------------------------

	@Override public void create() {
		Matrix4 matrix = new Matrix4();
		matrix.setToOrtho2D( 0, 0, 320, 180 );


		engine = new GameEngine();


//		engine.add( new ChatSpawnerSystem( "alwaysbecrafting", TOKEN ));
		engine.add( new PlayerInputSystem() );

		engine.add( new GravitySystem() );
		engine.add( new MovementSystem() );
		engine.add( new BoundingBoxPositioningSystem() );

		engine.add( new CollisionDetectionSystem() );
		engine.add( new CollisionDebugSystem() );
		engine.add( new CharacterCollisionSystem() );

		engine.add( new BackgroundRenderSystem() );
		engine.add( new SpriteRenderSystem( matrix ));
		engine.add( new BoxRenderSystem( matrix ));


		engine.add( Entities.makeWall( 0, 0, 320, 40 ));
		engine.add( Entities.makePlayerCharacter( 60, 39 ));
	}

	//--------------------------------------------------------------------------

	@Override public void render() {
		engine.update( 0 );
	}

	//--------------------------------------------------------------------------

	@Override public void dispose() {}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
