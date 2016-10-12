package stream.alwaysbecrafting.chatbuster.ecs.system.render;

import stream.alwaysbecrafting.chatbuster.ecs.component.logic.ChatControllerComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.logic.PlayerControllerComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.GravityComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.VelocityComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.render.SpriteComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.state.CharacterHitstunStateComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.state.CharacterZorpStateComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.state.GunShootStateComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.EntitySystem;

//==============================================================================
public class CharacterSpriteMapSystem extends EntitySystem {
	//--------------------------------------------------------------------------

	public static final byte STAND = 0b0000;

	public static final byte FALL  = 0b0001;
	public static final byte SHOOT = 0b0010;

	public static final byte HITSTUN = 0b0100;
	public static final byte ZORP    = 0b1000;
	public static final byte RUN     = 0b1100;

	//--------------------------------------------------------------------------

	@Override protected boolean acceptEntity( Entity entity ) {
		return entity.hasAll(
				SpriteComponent.class )

		&& entity.hasAny(
				ChatControllerComponent.class,
				PlayerControllerComponent.class );
	}

	//--------------------------------------------------------------------------

	@Override protected void onHandleEntity( Entity entity, double deltaTime ) {
		SpriteComponent spriteComp = entity.get( SpriteComponent.class );
		byte state = 0;

		state = entity.has( CharacterHitstunStateComponent.class ) ? HITSTUN : state;
		state = entity.has( CharacterZorpStateComponent.class ) ? ZORP : state;

		state |= entity.has( GravityComponent.class ) ? FALL : 0;
		state |= entity.has( GunShootStateComponent.class ) ? SHOOT : 0;

		state = (( entity.get( VelocityComponent.class ).h != 0 ) && ( state & FALL ) == 0 ) ? RUN : state;

		spriteComp.spriteMap.applyRegion( spriteComp.sprite, state, 0 );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
