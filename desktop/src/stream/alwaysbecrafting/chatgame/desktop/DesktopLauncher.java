package stream.alwaysbecrafting.chatgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import javafx.application.Application;
import javafx.stage.Stage;
import stream.alwaysbecrafting.chatgame.twitch.TwitchAuthenticator;

//==============================================================================
public class DesktopLauncher extends Application {
	//--------------------------------------------------------------------------

	public static void main( String[] arg ) {
		launch( arg );

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 1280;
		config.height = 720;

		//new LwjglApplication( new ChatGame(), config );
	}

	//--------------------------------------------------------------------------

	@Override public void start( Stage primaryStage ) throws Exception {
		new TwitchAuthenticator( "alwaysbecrafting" ).requestAuthorization();
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
