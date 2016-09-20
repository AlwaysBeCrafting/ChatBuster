package stream.alwaysbecrafting.chatgame;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;

import stream.alwaysbecrafting.chatgame.ecs.component.PositionComponent;
import stream.alwaysbecrafting.chatgame.ecs.component.SpriteComponent;
import stream.alwaysbecrafting.chatgame.ecs.system.BackgroundRenderSystem;
import stream.alwaysbecrafting.chatgame.ecs.system.SpriteRenderSystem;

//==============================================================================
public class ChatGame extends ApplicationAdapter {
	//--------------------------------------------------------------------------

	Engine engine;

	//--------------------------------------------------------------------------

	@Override public void create() {
		engine = new Engine();

		engine.addSystem( new BackgroundRenderSystem() );
		engine.addSystem( SpriteRenderSystem.create() );


		Entity e = new Entity();

		e.add( new PositionComponent() );
		e.add( new SpriteComponent( "badlogic.jpg" ));

		engine.addEntity( e );
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
