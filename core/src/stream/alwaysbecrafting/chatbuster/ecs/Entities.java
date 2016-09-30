package stream.alwaysbecrafting.chatbuster.ecs;

import org.pircbotx.hooks.events.MessageEvent;

import java.util.Random;

import stream.alwaysbecrafting.chatbuster.ecs.component.AllyStateComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.BoundingBoxComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.ChatControllerComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.CollisionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.ColorFillComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.PlayerControllerComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.PositionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.SpriteComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.VelocityComponent;
import stream.alwaysbecrafting.flare.Entity;


//==============================================================================
public abstract class Entities {
	//--------------------------------------------------------------------------

	public static Entity makeCharacter() {
		Random rand = new Random();

		return new Entity(
				new AllyStateComponent(),

				new PositionComponent( rand.nextFloat() * 320, rand.nextFloat() * 180 ),
				new VelocityComponent(),
				new BoundingBoxComponent( 7, 9, 26, 32 ),

				new CollisionComponent( 0b1 ),

				new SpriteComponent( "guy.png", 32, 32 ));
	}

	//--------------------------------------------------------------------------

	public static Entity makeChatCharacter( MessageEvent event ) {
		Entity entity = makeCharacter();

		String username = "";
		if ( event.getUser() != null ) username = event.getUser().getNick();
		entity.add( new ChatControllerComponent( username, event.getMessage() ));

		return entity;
	}

	//--------------------------------------------------------------------------

	public static Entity makePlayerCharacter() {
		Entity entity = makeCharacter();

		entity.add( new PlayerControllerComponent() );

		return entity;
	}

	//--------------------------------------------------------------------------

	public static Entity makeWall( int left, int bottom, int right, int top ) {
		return new Entity(
				new BoundingBoxComponent( left, bottom, right, top ),
				new ColorFillComponent( 0xff000044 ),
				new CollisionComponent( 0b1 ));
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
