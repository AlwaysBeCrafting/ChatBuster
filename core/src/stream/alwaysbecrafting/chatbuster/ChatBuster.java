package stream.alwaysbecrafting.chatbuster;

import com.badlogic.gdx.ApplicationAdapter;

import stream.alwaysbecrafting.chatbuster.ecs.system.AllyStateSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.BackgroundRenderSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.ChatControlSystem;
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
		engine = new GameEngine();

		engine.add( new ChatControlSystem( "alwaysbecrafting", TOKEN ));
		engine.add( new AllyStateSystem() );
		engine.add( new BackgroundRenderSystem() );
		engine.add( new SpriteRenderSystem() );
		engine.add( new PlayerInputSystem() );
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
