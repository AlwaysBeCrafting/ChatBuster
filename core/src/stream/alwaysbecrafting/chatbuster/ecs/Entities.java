package stream.alwaysbecrafting.chatbuster.ecs;

import org.pircbotx.hooks.events.MessageEvent;

import java.util.Random;

import stream.alwaysbecrafting.chatbuster.ecs.component.ChatPlayerComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.PositionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.SpriteComponent;
import stream.alwaysbecrafting.flare.Entity;

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
				new ChatPlayerComponent( username, event.getMessage() ));
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
