package stream.alwaysbecrafting.chatbuster.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import javafx.application.Application;
import javafx.stage.Stage;
import stream.alwaysbecrafting.chatbuster.ChatBuster;
import stream.alwaysbecrafting.chatbuster.twitch.TwitchAuthenticator;

//==============================================================================
public class DesktopLauncher extends Application {
	//--------------------------------------------------------------------------

	static TwitchAuthenticator authenticator = new TwitchAuthenticator( "alwaysbecrafting" );

	//--------------------------------------------------------------------------

	public static void main( String[] arg ) {
		launch( arg );

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 1280;
		config.height = 720;

		new LwjglApplication( new ChatBuster( authenticator.getAccessToken() ), config );
	}

	//--------------------------------------------------------------------------

	@Override public void start( Stage primaryStage ) throws Exception {
		authenticator.requestAuthorization( primaryStage );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
