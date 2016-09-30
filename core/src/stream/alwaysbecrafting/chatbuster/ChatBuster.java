package stream.alwaysbecrafting.chatbuster;

import com.badlogic.gdx.ApplicationAdapter;

import stream.alwaysbecrafting.chatbuster.ecs.Entities;
import stream.alwaysbecrafting.chatbuster.ecs.system.logic.AllyControlSystem;
import stream.alwaysbecrafting.chatbuster.ecs.system.logic.PlayerInputSystem;
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
		engine = new GameEngine();

//		engine.add( new ChatInputSystem( "alwaysbecrafting", TOKEN ));
		engine.add( new PlayerInputSystem() );
		engine.add( new AllyControlSystem() );

		engine.add( new MovementSystem() );
		engine.add( new GravitySystem() );

		engine.add( new BackgroundRenderSystem() );
		engine.add( new BoxRenderSystem() );
		engine.add( new SpriteRenderSystem() );

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
