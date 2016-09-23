package stream.alwaysbecrafting.chatgame.ecs;

import org.pircbotx.hooks.events.MessageEvent;

import java.util.Random;

import stream.alwaysbecrafting.chatgame.ecs.component.ChatUserComponent;
import stream.alwaysbecrafting.chatgame.ecs.component.PositionComponent;
import stream.alwaysbecrafting.chatgame.ecs.component.SpriteComponent;
import stream.alwaysbecrafting.ecs.GameEngine;

//==============================================================================
public abstract class Entities {
	//--------------------------------------------------------------------------

	public static long makeChatCharacter( GameEngine engine, MessageEvent event ) {
		Random rand = new Random();

		String username = "";
		if ( event.getUser() != null ) username = event.getUser().getNick();

		return engine.createEntity(
				new PositionComponent( rand.nextFloat() * 1280, rand.nextFloat() * 720 ),
				new SpriteComponent( "fella.png" ),
				new ChatUserComponent( username, event.getMessage() ));
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
