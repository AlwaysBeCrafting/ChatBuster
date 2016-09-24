package stream.alwaysbecrafting.chatgame.util;

import com.badlogic.gdx.utils.IntMap;

import static stream.alwaysbecrafting.chatgame.util.Colors.Solarized.BASE0;
import static stream.alwaysbecrafting.chatgame.util.Colors.Solarized.BASE00;
import static stream.alwaysbecrafting.chatgame.util.Colors.Solarized.BASE01;
import static stream.alwaysbecrafting.chatgame.util.Colors.Solarized.BASE02;
import static stream.alwaysbecrafting.chatgame.util.Colors.Solarized.BASE03;
import static stream.alwaysbecrafting.chatgame.util.Colors.Solarized.BASE1;
import static stream.alwaysbecrafting.chatgame.util.Colors.Solarized.BASE2;
import static stream.alwaysbecrafting.chatgame.util.Colors.Solarized.BASE3;
import static stream.alwaysbecrafting.chatgame.util.Colors.Solarized.BLUE;
import static stream.alwaysbecrafting.chatgame.util.Colors.Solarized.CYAN;
import static stream.alwaysbecrafting.chatgame.util.Colors.Solarized.GREEN;
import static stream.alwaysbecrafting.chatgame.util.Colors.Solarized.MAGENTA;
import static stream.alwaysbecrafting.chatgame.util.Colors.Solarized.ORANGE;
import static stream.alwaysbecrafting.chatgame.util.Colors.Solarized.RED;
import static stream.alwaysbecrafting.chatgame.util.Colors.Solarized.VIOLET;
import static stream.alwaysbecrafting.chatgame.util.Colors.Solarized.YELLOW;

//==============================================================================
public abstract class Sprites {
	//--------------------------------------------------------------------------

	private static final IntMap<String> GUYS = new IntMap<>( 16 );
	static {
		GUYS.put( BASE03, "fella-grey.png" );
		GUYS.put( BASE02, "fella-grey.png" );
		GUYS.put( BASE01, "fella-grey.png" );
		GUYS.put( BASE00, "fella-grey.png" );
		GUYS.put( BASE0,  "fella-grey.png" );
		GUYS.put( BASE1,  "fella-grey.png" );
		GUYS.put( BASE2,  "fella-grey.png" );
		GUYS.put( BASE3,  "fella-grey.png" );

		GUYS.put( YELLOW,  "fella-yellow.png"  );
		GUYS.put( ORANGE,  "fella-orange.png"  );
		GUYS.put( RED,     "fella-red.png"     );
		GUYS.put( MAGENTA, "fella-magenta.png" );
		GUYS.put( VIOLET,  "fella-violet.png"  );
		GUYS.put( BLUE,    "fella-blue.png"    );
		GUYS.put( CYAN,    "fella-cyan.png"    );
		GUYS.put( GREEN,   "fella-green.png"   );
	}

	//--------------------------------------------------------------------------

	public String guyFor( int color ) {
		final int r1 = Colors.rInt( color );
		final int g1 = Colors.gInt( color );
		final int b1 = Colors.bInt( color );

		String outSprite = "fella-grey.png";
		int distance = Integer.MAX_VALUE;

		for ( int solarizedColor : Colors.Solarized.ALL ) {
			final int r2 = Colors.rInt( solarizedColor );
			final int g2 = Colors.gInt( solarizedColor );
			final int b2 = Colors.bInt( solarizedColor );

			int rMean = ( r1 + r2 ) >> 1;
			int r = r1 - r2;
			int g = g1 - g2;
			int b = b1 - b2;

			int magic =
					((( 512 + rMean ) * r * r ) >> 8 ) +
					    4             * g * g          +
					((( 767 - rMean ) * b * b ) >> 8 );

			if ( magic < distance ) {
				outSprite = GUYS.get( solarizedColor );
			}
		}


		return outSprite;
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
