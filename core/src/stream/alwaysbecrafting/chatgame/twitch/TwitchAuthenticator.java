package stream.alwaysbecrafting.chatgame.twitch;

import com.google.common.base.Strings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private static final String TOKEN_PATH = "../../token.key";


	private final String USERNAME;
	private final String CLIENT_ID;
	private final String CLIENT_SECRET;


	private String access_token = "";
	private List<String> scopes = Collections.emptyList();

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
	}

	//--------------------------------------------------------------------------

	public void requestAuthorization( Stage stage ) {
		List<String> lines = new ArrayList<>( 2 );

		try ( Stream<String> stream = Files.lines( Paths.get( TOKEN_PATH ))) {
			stream.forEach( lines::add );

		} catch ( IOException e ) { Log.i( "No token file" ); }


		if ( lines.size() == 2 ) {
			access_token = lines.get( 0 );
			scopes = Arrays.asList( lines.get( 1 ).split( "," ));

			stage.show();
			stage.close();
			return;
		}


		WebView browser = new WebView();

		String url = String.format(
				AUTH_URL,
				CLIENT_ID,
				REDIRECT_URI,
				String.join( "+", SCOPES ));

		browser.getEngine().getLoadWorker().stateProperty().addListener(( observable, oldValue, newValue ) -> {
			URI uri = URI.create( browser.getEngine().getLocation() );

			if ( !Strings.isNullOrEmpty( uri.getFragment() )) {
				setAuth( uri.getFragment() );

				if ( hasToken() ) {
					try ( PrintWriter out = new PrintWriter( TOKEN_PATH )) {
						out.println( getAccessToken() );
						out.println( String.join( ",", getScopes() ));

					} catch ( FileNotFoundException e ) { e.printStackTrace(); }

					stage.close();
				}
			}
		} );


		browser.getEngine().load( url );

		stage.setScene( new Scene( browser ));

		stage.show();
	}

	//--------------------------------------------------------------------------

	private void setAuth( String uriFragment ) {
		if ( uriFragment ==  null ) return;
		String[] pairs = uriFragment.split( "&" );
		Map<String,String> map = new HashMap<>( pairs.length );

		for ( String pair : pairs ) {
			String[] kv = pair.split( "=" );
			map.put( kv[0], kv[1] );
		}

		Log.i( uriFragment );

		access_token = map.get( "access_token" );
		scopes = Arrays.asList( map.get( "scope" ).split( "," ));
	}

	//--------------------------------------------------------------------------

	public boolean hasToken() { return !Strings.isNullOrEmpty( access_token ); }

	//--------------------------------------------------------------------------

	public String getAccessToken() { return access_token; }

	//--------------------------------------------------------------------------

	public List<String> getScopes() { return scopes; }

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
