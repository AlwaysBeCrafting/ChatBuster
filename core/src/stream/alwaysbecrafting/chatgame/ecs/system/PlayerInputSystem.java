package stream.alwaysbecrafting.chatgame.ecs.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;

import stream.alwaysbecrafting.chatgame.ecs.component.PlayerControllerComponent;
import stream.alwaysbecrafting.chatgame.ecs.component.PositionComponent;

//==============================================================================
public class PlayerInputSystem extends IteratingSystem {
	//--------------------------------------------------------------------------

	private final ComponentMapper<PlayerControllerComponent> CONTROLLER_MAPPER;
	private final ComponentMapper<PositionComponent> POSITION_MAPPER;

	//--------------------------------------------------------------------------

	public static PlayerInputSystem create() {
		Family family = Family
				.all( PlayerControllerComponent.class, PositionComponent.class )
				.get();

		return new PlayerInputSystem( family );
	}

	//--------------------------------------------------------------------------

	public PlayerInputSystem( Family family ) {
		super( family );

		CONTROLLER_MAPPER = ComponentMapper.getFor( PlayerControllerComponent.class );
		POSITION_MAPPER = ComponentMapper.getFor( PositionComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void processEntity( Entity entity, float deltaTime ) {
		PlayerControllerComponent controller = CONTROLLER_MAPPER.get( entity );
		PositionComponent position = POSITION_MAPPER.get( entity );

		if ( Gdx.input.isKeyJustPressed( controller.key_up )) {
			position.position.y += 20;
		}
		if ( Gdx.input.isKeyJustPressed( controller.key_down )) {
			position.position.y -= 20;
		}
		if ( Gdx.input.isKeyJustPressed( controller.key_right )) {
			position.position.x += 20;
		}
		if ( Gdx.input.isKeyJustPressed( controller.key_left )) {
			position.position.x -= 20;
		}
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
