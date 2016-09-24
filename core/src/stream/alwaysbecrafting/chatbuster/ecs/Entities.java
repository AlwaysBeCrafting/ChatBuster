package stream.alwaysbecrafting.chatbuster.ecs;

import org.pircbotx.hooks.events.MessageEvent;

import java.util.Random;

import stream.alwaysbecrafting.chatbuster.ecs.component.ChatUserComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.PositionComponent;
import stream.alwaysbecrafting.chatbuster.ecs.component.SpriteComponent;
import stream.alwaysbecrafting.ecs.GameEngine;

//==============================================================================
public abstract class Entities {
	//--------------------------------------------------------------------------

	public static long makeChatCharacter( GameEngine engine, MessageEvent event ) {
		Random rand = new Random();

		String username = "";
		if ( event.getUser() != null ) username = event.getUser().getNick();

		return engine.createEntity(
				new PositionComponent( rand.nextFloat() * 320, rand.nextFloat() * 180 ),
				new SpriteComponent( "guy-grey.png" ),
				new ChatUserComponent( username, event.getMessage() ));
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
