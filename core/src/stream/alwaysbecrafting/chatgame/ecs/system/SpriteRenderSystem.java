package stream.alwaysbecrafting.chatgame.ecs.system;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import stream.alwaysbecrafting.chatgame.ecs.component.PositionComponent;
import stream.alwaysbecrafting.chatgame.ecs.component.SpriteComponent;
import stream.alwaysbecrafting.ecs.EntitySystem;
import stream.alwaysbecrafting.ecs.GameEngine;

//==============================================================================
public class SpriteRenderSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	private final SpriteBatch BATCHER = new SpriteBatch();

	//--------------------------------------------------------------------------

	public SpriteRenderSystem() {
		requireAll(
				SpriteComponent.class,
				PositionComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override public void onUpdate( GameEngine engine, float deltaTime ) {
		BATCHER.begin();
		super.onUpdate( engine, deltaTime );
		BATCHER.end();
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( GameEngine engine, long entityId, float deltaTime ) {
		Texture sprite = engine.getComponent( entityId, SpriteComponent.class ).sprite;
		Vector2 position = engine.getComponent( entityId, PositionComponent.class ).position;

		BATCHER.draw(
				sprite,
				position.x,
				position.y );
	}

	//--------------------------------------------------------------------------

	@Override public void onStop( GameEngine engine ) {
		super.onStop( engine );

		BATCHER.dispose();
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
