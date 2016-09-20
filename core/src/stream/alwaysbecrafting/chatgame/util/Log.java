package stream.alwaysbecrafting.chatgame.util;


import static stream.alwaysbecrafting.chatgame.util.Log.Level.*;

//==============================================================================
public abstract class Log {
	//--------------------------------------------------------------------------

	private static final Level MIN_LOG_LEVEL = INFO;

	//--------------------------------------------------------------------------

	public static void i( String message ) { log( INFO,    message ); }
	public static void d( String message ) { log( DEBUG,   message ); }
	public static void w( String message ) { log( WARNING, message ); }
	public static void e( String message ) { log( ERROR,   message ); }

	//--------------------------------------------------------------------------

	private static void log( Level lv, String message ) {
		if ( lv.compareTo( MIN_LOG_LEVEL ) < 0 ) return;

		System.out.println( String.format(
				"%1$tT.%1$tL | (%2$s)\t%3$s",
				System.currentTimeMillis(),
				lv,
				message
		));
	}

	//--------------------------------------------------------------------------

	//==========================================================================
	enum Level implements Comparable<Level> {
		//----------------------------------------------------------------------

		INFO( "I" ),
		DEBUG( "D" ),
		WARNING( "W" ),
		ERROR( "E" );

		//----------------------------------------------------------------------

		public final String TAG;

		//----------------------------------------------------------------------

		private Level( String tag ) { TAG = tag; }

		//----------------------------------------------------------------------

		@Override public String toString() { return TAG; }

		//----------------------------------------------------------------------
	}
	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
