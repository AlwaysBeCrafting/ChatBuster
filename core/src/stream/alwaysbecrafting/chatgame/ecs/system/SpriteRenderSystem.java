package stream.alwaysbecrafting.chatgame.ecs.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import stream.alwaysbecrafting.chatgame.ecs.component.PositionComponent;
import stream.alwaysbecrafting.chatgame.ecs.component.SpriteComponent;

//==============================================================================
public class SpriteRenderSystem extends IteratingSystem {
	//--------------------------------------------------------------------------

	private final ComponentMapper<SpriteComponent> SPRITE_MAPPER;
	private final ComponentMapper<PositionComponent> POSITION_MAPPER;

	private final SpriteBatch BATCHER = new SpriteBatch();

	//--------------------------------------------------------------------------

	public static SpriteRenderSystem create() {
		Family family = Family
				.all( SpriteComponent.class, PositionComponent.class )
				.get();

		return new SpriteRenderSystem( family );
	}

	//--------------------------------------------------------------------------

	private SpriteRenderSystem( Family family ) {
		super( family );

		SPRITE_MAPPER = ComponentMapper.getFor( SpriteComponent.class );
		POSITION_MAPPER = ComponentMapper.getFor( PositionComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override public void update( float deltaTime ) {
		BATCHER.begin();
		super.update( deltaTime );
		BATCHER.end();
	}

	//--------------------------------------------------------------------------

	@Override protected void processEntity( Entity entity, float deltaTime ) {
		Texture sprite = SPRITE_MAPPER.get( entity ).sprite;
		Vector2 position = POSITION_MAPPER.get( entity ).position;

		BATCHER.draw(
				sprite,
				position.x,
				position.y );
	}

	//--------------------------------------------------------------------------

	@Override public void removedFromEngine( Engine e ) {
		super.removedFromEngine( e );

		BATCHER.dispose();
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
