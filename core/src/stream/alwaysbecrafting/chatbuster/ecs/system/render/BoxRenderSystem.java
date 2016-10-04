package stream.alwaysbecrafting.chatbuster.ecs.system.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

import stream.alwaysbecrafting.chatbuster.ecs.component.physics.BoundingBoxComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.render.ColorFillComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;
import stream.alwaysbecrafting.flare.GameEngine;

//==============================================================================
public class BoxRenderSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	private final ShapeRenderer RENDERER = new ShapeRenderer();

	private Color color = new Color();

	//--------------------------------------------------------------------------

	public BoxRenderSystem( Matrix4 matrix ) {
		RENDERER.setProjectionMatrix( matrix );
	}

	//--------------------------------------------------------------------------

	@Override public void onUpdate( GameEngine engine, double deltaTime ) {
		RENDERER.begin( ShapeRenderer.ShapeType.Filled );

		super.onUpdate( engine, deltaTime );

		RENDERER.end();
	}

	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				BoundingBoxComponent.class,
				ColorFillComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		BoundingBoxComponent boundsComp = entity.get( BoundingBoxComponent.class );
		ColorFillComponent colorComp = entity.get( ColorFillComponent.class );

		Color.argb8888ToColor( color, colorComp.color );

		RENDERER.setColor( color );
		RENDERER.rect(
				boundsComp.rect.x,
				boundsComp.rect.y,
				boundsComp.rect.width,
				boundsComp.rect.height );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
