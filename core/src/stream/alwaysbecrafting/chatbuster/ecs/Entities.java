package stream.alwaysbecrafting.chatbuster.ecs;

import org.pircbotx.hooks.events.MessageEvent;

import java.util.Random;

import stream.alwaysbecrafting.chatbuster.ecs.component.BoundingBoxComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.ChatUserComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.CollisionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.ColorFillComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.PositionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.SpriteComponent;
import stream.alwaysbecrafting.flare.Entity;
import stream.alwaysbecrafting.flare.GameEngine;

//==============================================================================
public abstract class Entities {
	//--------------------------------------------------------------------------

	public static Entity makeChatCharacter( MessageEvent event ) {
		Random rand = new Random();

		String username = "";
		if ( event.getUser() != null ) username = event.getUser().getNick();

		return new Entity(
				new PositionComponent( rand.nextFloat() * 320, rand.nextFloat() * 180 ),
				new SpriteComponent( "guy-grey.png" ),
				new ChatUserComponent( username, event.getMessage() ),
				new BoundingBoxComponent( 7, 9, 26, 32 ),
				new CollisionComponent( 0b1 ));
	}

	//--------------------------------------------------------------------------

	public static Entity makeWall( int left, int top, int right, int bottom ) {
		return new Entity(
				new BoundingBoxComponent( left, top, right, bottom ),
				new ColorFillComponent(),
				new CollisionComponent( 0b1 ));
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
