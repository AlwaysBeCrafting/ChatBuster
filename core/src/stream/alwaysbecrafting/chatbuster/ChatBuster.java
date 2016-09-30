package stream.alwaysbecrafting.chatbuster;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.math.Matrix4;

import stream.alwaysbecrafting.chatbuster.ecs.Entities;
import stream.alwaysbecrafting.chatbuster.ecs.system.AllyStateSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.BackgroundRenderSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.BoxRenderSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.ChatControlSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.MovementSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.PlayerInputSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.SpriteRenderSystem;
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

		engine.add( new ChatControlSystem( "alwaysbecrafting", TOKEN ));
		engine.add( new PlayerInputSystem() );
		engine.add( new AllyStateSystem() );

		engine.add( new MovementSystem() );

		engine.add( new BackgroundRenderSystem() );
		engine.add( new BoxRenderSystem( matrix ));
		engine.add( new SpriteRenderSystem( matrix ));

		engine.add( Entities.makePlayerCharacter() );
		engine.add( Entities.makeWall( 0, 0, 320, 40 ));
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
