package stream.alwaysbecrafting.chatbuster.ecs.system.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import stream.alwaysbecrafting.chatbuster.ecs.component.physics.PositionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.render.SpriteComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.render.TransformComponent;
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
		SpriteComponent   spriteComp   = entity.get( SpriteComponent.class   );
		PositionComponent positionComp = entity.get( PositionComponent.class );

		int translateX = (int)-spriteComp.origin.x;
		int translateY = (int)-spriteComp.origin.y;

		if ( entity.has( TransformComponent.class )) {
			TransformComponent transComp = entity.get( TransformComponent.class );
			translateX += transComp.translateX;
			translateY += transComp.translateY;
		}

		BATCHER.draw(
				spriteComp.sprite,
				positionComp.x + translateX,
				positionComp.y + translateY,
				spriteComp.origin.x,
				spriteComp.origin.y,
				spriteComp.spriteMap.CELL_WIDTH,
				spriteComp.spriteMap.CELL_HEIGHT,
				spriteComp.flipped ? -1 : 1,
				1,
				0 );
	}

	//--------------------------------------------------------------------------

	@Override public void onStop( GameEngine engine ) {
		super.onStop( engine );

		BATCHER.dispose();
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
