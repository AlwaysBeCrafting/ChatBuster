package stream.alwaysbecrafting.chatbuster.ecs.system;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import stream.alwaysbecrafting.chatbuster.ecs.component.BoundingBoxComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.ColorFillComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;
import stream.alwaysbecrafting.flare.GameEngine;

//==============================================================================
public class BoxRenderSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	private ShapeRenderer renderer = new ShapeRenderer();
	private Color color = new Color();

	//--------------------------------------------------------------------------

	@Override public void onUpdate( GameEngine engine, double deltaTime ) {
		renderer.begin( ShapeRenderer.ShapeType.Filled );

		super.onUpdate( engine, deltaTime );

		renderer.end();
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

		renderer.setColor( color );
		renderer.rect(
				boundsComp.rect.x,
				boundsComp.rect.y,
				boundsComp.rect.width,
				boundsComp.rect.height );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
