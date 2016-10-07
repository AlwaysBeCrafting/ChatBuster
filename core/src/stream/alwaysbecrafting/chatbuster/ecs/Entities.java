package stream.alwaysbecrafting.chatbuster.ecs;

import org.pircbotx.hooks.events.MessageEvent;

import java.util.Random;

import stream.alwaysbecrafting.chatbuster.data.SpriteMap;
import stream.alwaysbecrafting.chatbuster.ecs.component.logic.ChatControllerComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.logic.PlayerControllerComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.BoundingBoxComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.CollisionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.PositionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.physics.VelocityComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.render.ColorDrawComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.render.SpriteComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.state.CharacterZorpStateComponent;
import stream.alwaysbecrafting.flare.Entity;

import static stream.alwaysbecrafting.chatbuster.ecs.system.render.CharacterSpriteMapSystem.FALL;
import static stream.alwaysbecrafting.chatbuster.ecs.system.render.CharacterSpriteMapSystem.HITSTUN;
import static stream.alwaysbecrafting.chatbuster.ecs.system.render.CharacterSpriteMapSystem.RUN;
import static stream.alwaysbecrafting.chatbuster.ecs.system.render.CharacterSpriteMapSystem.SHOOT;
import static stream.alwaysbecrafting.chatbuster.ecs.system.render.CharacterSpriteMapSystem.STAND;
import static stream.alwaysbecrafting.chatbuster.ecs.system.render.CharacterSpriteMapSystem.ZORP;


//==============================================================================
public abstract class Entities {
	//--------------------------------------------------------------------------

	public static Entity makeCharacter( int x, int y ) {
		SpriteMap spriteMap = new SpriteMap( 32, 32 );
		spriteMap.mapCell( STAND,        0, 0 );
		spriteMap.mapCell( FALL,         1, 0 );
		spriteMap.mapCell( SHOOT,        2, 0 );
		spriteMap.mapCell( FALL | SHOOT, 3, 0 );

		spriteMap.mapCell( HITSTUN, 0, 1    );
		spriteMap.mapRow(  ZORP,    1, 1, 3 );

		spriteMap.mapRow( RUN,         0, 2, 4 );
		spriteMap.mapRow( RUN | SHOOT, 0, 3, 4 );

		return new Entity(
				new PositionComponent( x, y ),
				new VelocityComponent( 0, 0 ),
//				new GravityComponent( 0.5f ),
				new BoundingBoxComponent( 16, 23, 7, 0 ),

				new CharacterZorpStateComponent(),

				new CollisionComponent( 0b1 ),

				new ColorDrawComponent( 0xff00ff00 ),
				new SpriteComponent( "guy.png", spriteMap, 15, 3 ));
	}

	//--------------------------------------------------------------------------

	public static Entity makeChatCharacter( MessageEvent event ) {
		Random rand = new Random();
		Entity entity = makeCharacter( rand.nextInt( 320 ), rand.nextInt( 180 ));

		String username = "";
		if ( event.getUser() != null ) username = event.getUser().getNick();
		entity.add( new ChatControllerComponent( username, event.getMessage() ));

		return entity;
	}

	//--------------------------------------------------------------------------

	public static Entity makePlayerCharacter( int x, int y ) {
		Entity entity = makeCharacter( x, y );

		entity.add( new PlayerControllerComponent() );

		return entity;
	}

	//--------------------------------------------------------------------------

	public static Entity makeWall( int x, int y, int width, int height ) {
		return new Entity(
				new PositionComponent( x, y ),
				new BoundingBoxComponent( width, height, 0, 0 ),
				new ColorDrawComponent( 0xffffffff ),
				new CollisionComponent( 0b1 ));
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
