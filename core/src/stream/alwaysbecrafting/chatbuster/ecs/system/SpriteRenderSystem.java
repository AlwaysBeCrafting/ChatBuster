package stream.alwaysbecrafting.chatbuster.ecs.system;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import stream.alwaysbecrafting.chatbuster.ecs.component.PositionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.SpriteComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;
import stream.alwaysbecrafting.flare.GameEngine;

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

	@Override public void onUpdate( GameEngine engine, double deltaTime ) {
		BATCHER.begin();
		super.onUpdate( engine, deltaTime );
		BATCHER.end();
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		Texture           sprite   = entity.get( SpriteComponent.class   ).sprite;
		PositionComponent position = entity.get( PositionComponent.class );

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
