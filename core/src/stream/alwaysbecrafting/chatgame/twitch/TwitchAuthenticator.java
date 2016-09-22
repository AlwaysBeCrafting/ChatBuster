package stream.alwaysbecrafting.chatgame.twitch;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import stream.alwaysbecrafting.chatgame.util.Log;

//==============================================================================
public class TwitchAuthenticator {
	//--------------------------------------------------------------------------

	private static final String AUTH_URL = "https://api.twitch.tv/kraken/oauth2/authorize"+
			"?response_type=token"+
			"&client_id=%1$s"+
			"&redirect_uri=%2$s"+
			"&scope=%3$s";

	private static final String REDIRECT_URI = "https://www.twitch.tv/alwaysbecrafting";
	private static final String[] SCOPES = new String[] { "chat_login" };

	private static final String KEY_PATH = "../../twitch-api.keys";


	private final String USERNAME;
	private final String CLIENT_ID;
	private final String CLIENT_SECRET;


	private String auth_token = "";
	private List<String> auth_scopes = Collections.emptyList();

	//--------------------------------------------------------------------------

	public TwitchAuthenticator( String username ) {
		USERNAME = username;


		List<String> lines = new ArrayList<>( 2 );

		try ( Stream<String> stream = Files.lines( Paths.get( KEY_PATH ))) {
			stream.forEach( lines::add );

		} catch ( IOException e ) { e.printStackTrace(); }


		if ( lines.size() == 2 ) {
			CLIENT_ID = lines.get( 0 );
			CLIENT_SECRET = lines.get( 1 );

		} else {
			CLIENT_ID = "";
			CLIENT_SECRET = "";
		}


		Log.d( "" + CLIENT_ID.length() );
		Log.d( "" + CLIENT_SECRET.length() );
		Log.d( "" + ( !Objects.equals( CLIENT_ID, CLIENT_SECRET )));
	}

	//--------------------------------------------------------------------------

	public void requestAuthorization() {
		Stage stage = new Stage();

		WebView browser = new WebView();

		String url = String.format(
				AUTH_URL,
				CLIENT_ID,
				REDIRECT_URI,
				String.join( "+", SCOPES ));

		browser.getEngine().getLoadWorker().stateProperty().addListener(( observable, oldValue, newValue ) -> {
			String location = browser.getEngine().getLocation();
			setAuth( URI.create( location ).getFragment() );

		} );


		browser.getEngine().load( url );

		stage.setScene( new Scene( browser ));

		stage.show();
	}

	//--------------------------------------------------------------------------

	public void setAuth( String uriFragment ) {
		if ( uriFragment ==  null ) return;
		String[] pairs = uriFragment.split( "&" );
		Map<String,String> map = new HashMap<>( pairs.length );

		for ( String pair : pairs ) {
			String[] kv = pair.split( "=" );
			map.put( kv[0], kv[1] );
		}

		Log.i( uriFragment );

		auth_token = map.get( "access_token" );
		auth_scopes = Arrays.asList( map.get( "scope" ).split( "," ));
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
