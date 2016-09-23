package stream.alwaysbecrafting.chatgame.ecs.system;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.cap.EnableCapHandler;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;

import java.io.IOException;

import stream.alwaysbecrafting.chatgame.util.Log;
import stream.alwaysbecrafting.ecs.GameEngine;
import stream.alwaysbecrafting.ecs.GameSystem;

//==============================================================================
public class ChatSystem extends GameSystem {
	//--------------------------------------------------------------------------

	private final PircBotX BOT;
	private final Listener MESSAGE_LISTENER;

	//--------------------------------------------------------------------------

	public ChatSystem( String username, String token ) {
		MESSAGE_LISTENER = new ListenerAdapter() {
			@Override public void onEvent( Event event ) throws Exception {
				Log.d( event.toString() );
				super.onEvent( event );
			}
		};

		Configuration config = new Configuration.Builder()
				.setAutoNickChange( false ) //Twitch doesn't support multiple users
				.setOnJoinWhoEnabled( false ) //Twitch doesn't support WHO command
				.setCapEnabled( true )
				.addCapHandler( new EnableCapHandler( "twitch.tv/membership" )) //Twitch by default doesn't send JOIN, PART, and NAMES unless you request it, see https://github.com/justintv/Twitch-API/blob/master/IRC.md#membership

				.setServer( "irc.chat.twitch.tv", 6667 )
				.setName( username.toLowerCase() )
				.setServerPassword( "oauth:" + token )
				.addListener( MESSAGE_LISTENER )

				.addAutoJoinChannel( "#alwaysbecrafting" )
				.buildConfiguration();

		BOT = new PircBotX( config );
	}

	//--------------------------------------------------------------------------

	@Override public void onStart( GameEngine engine ) {
		try {
			BOT.startBot();
		} catch ( IOException | IrcException e ) {
			e.printStackTrace();
		}
	}

	//--------------------------------------------------------------------------

	@Override public void onUpdate( GameEngine engine, float deltaTime ) {}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
