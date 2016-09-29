package stream.alwaysbecrafting.chatbuster.ecs.system;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.cap.EnableCapHandler;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import stream.alwaysbecrafting.chatbuster.ecs.Entities;
import stream.alwaysbecrafting.flare.GameEngine;
import stream.alwaysbecrafting.flare.GameSystem;

//==============================================================================
public class ChatControlSystem extends GameSystem {
	//--------------------------------------------------------------------------

	private static final String JOIN_COMMAND = "!join";
	private static final String CHANNEL = "#alwaysbecrafting";


	private final PircBotX BOT;
	private final Listener MESSAGE_LISTENER;

	private final Queue<MessageEvent> JOIN_MESSAGES = new ConcurrentLinkedQueue<>();


	private boolean hasJoined = false;

	//--------------------------------------------------------------------------

	public ChatControlSystem( String username, String token ) {
		MESSAGE_LISTENER = new ListenerAdapter() {
			@Override public void onConnect( ConnectEvent event ) {
				event.getBot().sendRaw().rawLine( "CAP REQ :twitch.tv/tags" );
			}

			@Override public void onMessage( MessageEvent event ) {
				if ( event.getMessage().startsWith( JOIN_COMMAND )) {
					JOIN_MESSAGES.add( event );
				}
			}

			@Override public void onJoin( JoinEvent event ) {
				if ( event.getChannel().getName().equals( CHANNEL )) {
					if ( !hasJoined ) {
						event.getBot().send().message(
								event.getChannel().getName(),
								"Bot has joined channel, type '!join' to see your message in the log." );
					}
					hasJoined = true;
				}
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

				.addAutoJoinChannel( CHANNEL )
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

	@Override public void onUpdate( GameEngine engine, double deltaTime ) {
		JOIN_MESSAGES.stream()
				.map( Entities::makeChatCharacter )
				.forEach( engine::add );
		JOIN_MESSAGES.clear();
		super.onUpdate( engine, deltaTime );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
