package stream.alwaysbecrafting.chatbuster;

import com.badlogic.gdx.ApplicationAdapter;

import stream.alwaysbecrafting.chatbuster.ecs.component.PlayerControllerComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.PositionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.SpriteComponent;
import stream.alwaysbecrafting.chatbuster.ecs.system.BackgroundRenderSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.ChatSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.PlayerInputSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.SpriteRenderSystem;
import stream.alwaysbecrafting.ecs.GameEngine;

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

		engine.add( new ChatSystem( "alwaysbecrafting", TOKEN ));
		engine.add( new BackgroundRenderSystem() );
		engine.add( new SpriteRenderSystem() );
		engine.add( new PlayerInputSystem() );


		engine.createEntity(
				new PositionComponent(),
				new SpriteComponent( "guy-grey.png" ),
				new PlayerControllerComponent() );
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
