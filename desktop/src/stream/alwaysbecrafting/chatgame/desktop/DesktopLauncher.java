package stream.alwaysbecrafting.chatgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import javafx.application.Application;
import javafx.stage.Stage;
import stream.alwaysbecrafting.chatgame.ChatGame;
import stream.alwaysbecrafting.chatgame.twitch.TwitchAuthenticator;

//==============================================================================
public class DesktopLauncher extends Application {
	//--------------------------------------------------------------------------

	static TwitchAuthenticator authenticator = new TwitchAuthenticator( "alwaysbecrafting" );

	//--------------------------------------------------------------------------

	public static void main( String[] arg ) {
		launch( arg );

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 320;
		config.height = 180;

		new LwjglApplication( new ChatGame( authenticator.getAccessToken() ), config );
	}

	//--------------------------------------------------------------------------

	@Override public void start( Stage primaryStage ) throws Exception {
		authenticator.requestAuthorization( primaryStage );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
