package stream.alwaysbecrafting.chatgame;

import com.badlogic.gdx.ApplicationAdapter;

import stream.alwaysbecrafting.chatgame.ecs.component.PlayerControllerComponent;
import stream.alwaysbecrafting.chatgame.ecs.component.PositionComponent;
import stream.alwaysbecrafting.chatgame.ecs.component.SpriteComponent;
import stream.alwaysbecrafting.chatgame.ecs.system.BackgroundRenderSystem;
import stream.alwaysbecrafting.chatgame.ecs.system.ChatSystem;
import stream.alwaysbecrafting.chatgame.ecs.system.PlayerInputSystem;
import stream.alwaysbecrafting.chatgame.ecs.system.SpriteRenderSystem;
import stream.alwaysbecrafting.ecs.GameEngine;

//==============================================================================
public class ChatGame extends ApplicationAdapter {
	//--------------------------------------------------------------------------

	private final String TOKEN;

	private GameEngine engine;

	//--------------------------------------------------------------------------

	public ChatGame( String token ) {
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
				new SpriteComponent( "fella.png" ),
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
