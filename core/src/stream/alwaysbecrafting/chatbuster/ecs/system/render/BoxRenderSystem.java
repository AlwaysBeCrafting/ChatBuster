package stream.alwaysbecrafting.chatbuster.ecs.system.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

import stream.alwaysbecrafting.chatbuster.ecs.component.physics.BoundingBoxComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.PositionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.render.ColorDrawComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;
import stream.alwaysbecrafting.flare.GameEngine;

//==============================================================================
public class BoxRenderSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	private final ShapeRenderer RENDERER = new ShapeRenderer();

	private final Color COLOR = new Color();

	//--------------------------------------------------------------------------

	public BoxRenderSystem( Matrix4 matrix ) {
		RENDERER.setProjectionMatrix( matrix );
	}

	//--------------------------------------------------------------------------

	@Override public void onUpdate( GameEngine engine, double deltaTime ) {
		RENDERER.begin( ShapeRenderer.ShapeType.Line );

		super.onUpdate( engine, deltaTime );

		RENDERER.end();
	}

	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				BoundingBoxComponent.class,
				ColorDrawComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		BoundingBoxComponent boundsComp = entity.get( BoundingBoxComponent.class );
		ColorDrawComponent colorComp = entity.get( ColorDrawComponent.class );

		if ( entity.has( PositionComponent.class )) {
			boundsComp.moveTo(
					entity.get( PositionComponent.class ).x,
					entity.get( PositionComponent.class ).y );
		}

		Color.argb8888ToColor( COLOR, colorComp.color );

		RENDERER.setColor( COLOR );
		RENDERER.rect(
				boundsComp.rect.x,
				boundsComp.rect.y,
				boundsComp.rect.width,
				boundsComp.rect.height );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
