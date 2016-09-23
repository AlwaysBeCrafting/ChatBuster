package stream.alwaysbecrafting.chatgame.ecs.system;

import com.badlogic.gdx.Gdx;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.cap.EnableCapHandler;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import stream.alwaysbecrafting.chatgame.util.Colors;
import stream.alwaysbecrafting.chatgame.util.Log;
import stream.alwaysbecrafting.ecs.GameEngine;
import stream.alwaysbecrafting.ecs.GameSystem;

//==============================================================================
public class ChatSystem extends GameSystem {
	//--------------------------------------------------------------------------

	private final PircBotX BOT;
	private final Listener MESSAGE_LISTENER;

	private final Queue<MessageEvent> EVENTS = new ConcurrentLinkedQueue<>();

	//--------------------------------------------------------------------------

	public ChatSystem( String username, String token ) {
		MESSAGE_LISTENER = new ListenerAdapter() {
			@Override public void onMessage( MessageEvent event ) throws Exception {
				Log.i( event.getMessage() );
				EVENTS.add( event );
			}
		};

		Configuration config = new Configuration.Builder()
				.setAutoNickChange( false ) //Twitch doesn't support multiple users
				.setOnJoinWhoEnabled( false ) //Twitch doesn't support WHO command
				.addCapHandler( new EnableCapHandler( "twitch.tv/membership" )) //Twitch by default doesn't send JOIN, PART, and NAMES unless you request it, see https://github.com/justintv/Twitch-API/blob/master/IRC.md#membership

				.addServer( "irc.chat.twitch.tv", 6667 )
				.setName( username.toLowerCase() )
				.setServerPassword( "oauth:" + token )
				.addListener( MESSAGE_LISTENER )

				.addAutoJoinChannel( "#alwaysbecrafting" )
				.buildConfiguration();

		BOT = new PircBotX( config );
	}

	//--------------------------------------------------------------------------

	@Override public void onStart( GameEngine engine ) {
		new Thread( () -> {
			try { BOT.startBot(); }
			catch ( IOException | IrcException e ) { e.printStackTrace(); }
		}).start();
	}

	//--------------------------------------------------------------------------

	@Override public void onUpdate( GameEngine engine, float deltaTime ) {

		while ( EVENTS.peek() != null ) {
			int index = ( EVENTS.poll().getMessage().hashCode() ) & 15;
			int color = Colors.Solarized.ALL[ index ];
			Gdx.gl.glClearColor(
					Colors.r( color ),
					Colors.g( color ),
					Colors.b( color ),
					1 );
		}
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
