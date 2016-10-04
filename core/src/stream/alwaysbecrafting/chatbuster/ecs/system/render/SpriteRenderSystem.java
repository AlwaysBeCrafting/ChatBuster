package stream.alwaysbecrafting.chatbuster.ecs.system.render;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import stream.alwaysbecrafting.chatbuster.ecs.component.physics.PositionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.render.SpriteComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;
import stream.alwaysbecrafting.flare.GameEngine;

//==============================================================================
public class SpriteRenderSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	private final SpriteBatch BATCHER = new SpriteBatch();

	//--------------------------------------------------------------------------

	public SpriteRenderSystem( Matrix4 matrix ) {
		BATCHER.setProjectionMatrix( matrix );
	}

	//--------------------------------------------------------------------------

	@Override public void onUpdate( GameEngine engine, double deltaTime ) {
		BATCHER.begin();
		super.onUpdate( engine, deltaTime );
		BATCHER.end();
	}

	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				SpriteComponent.class,
				PositionComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		Sprite            sprite   = entity.get( SpriteComponent.class   ).sprite;
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
