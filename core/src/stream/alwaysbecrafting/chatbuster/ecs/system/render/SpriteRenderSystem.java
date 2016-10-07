package stream.alwaysbecrafting.chatbuster.ecs.system.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import stream.alwaysbecrafting.chatbuster.ecs.component.physics.PositionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.render.SpriteComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;
import stream.alwaysbecrafting.flare.GameEngine;

import static stream.alwaysbecrafting.chatbuster.ecs.system.render.CharacterSpriteMapSystem.FALL;
import static stream.alwaysbecrafting.chatbuster.ecs.system.render.CharacterSpriteMapSystem.SHOOT;

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

		spriteComp.spriteMap.applyRegion( spriteComp.sprite, FALL | SHOOT, 0 );

		BATCHER.draw(
				spriteComp.sprite,
				positionComp.x - spriteComp.origin.x,
				positionComp.y - spriteComp.origin.y );
	}

	//--------------------------------------------------------------------------

	@Override public void onStop( GameEngine engine ) {
		super.onStop( engine );

		BATCHER.dispose();
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
