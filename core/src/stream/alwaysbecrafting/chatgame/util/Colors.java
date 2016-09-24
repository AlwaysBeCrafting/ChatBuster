package stream.alwaysbecrafting.chatgame.util;

//==============================================================================
public abstract class Colors {
	//--------------------------------------------------------------------------

	// Colors are as 0xAA_RR_GG_BB
	// AA = Alpha
	// RR = Red
	// GG = Green
	// BB = Blue

	//--------------------------------------------------------------------------

	public static float r( int color ) {
		return rInt( color ) / 255f;
	}

	//--------------------------------------------------------------------------

	public static int rInt( int color ) {
		return ( color & 0x00_ff_00_00 ) >> 16;
	}

	//--------------------------------------------------------------------------

	public static float g( int color ) {
		return gInt( color ) / 255f;
	}

	//--------------------------------------------------------------------------

	public static int gInt( int color ) {
		return ( color & 0x00_00_ff_00 ) >> 8;
	}

	//--------------------------------------------------------------------------

	public static float b( int color ) {
		return bInt( color ) / 255f;
	}

	//--------------------------------------------------------------------------

	public static int bInt( int color ) {
		return color & 0x00_00_00_ff;
	}

	//--------------------------------------------------------------------------

	public static float a( int color ) {
		return aInt( color ) / 255f;
	}

	//--------------------------------------------------------------------------

	private static int aInt( int color ) {
		return ( color & 0xff_00_00_00 ) >> 24;
	}

	//--------------------------------------------------------------------------

	//==========================================================================
	public static abstract class Solarized {
		//----------------------------------------------------------------------

		public static final int BASE03  = 0xff002b36;
		public static final int BASE02  = 0xff073642;
		public static final int BASE01  = 0xff586e75;
		public static final int BASE00  = 0xff657b83;
		public static final int BASE0   = 0xff839496;
		public static final int BASE1   = 0xff93a1a1;
		public static final int BASE2   = 0xffeee8d5;
		public static final int BASE3   = 0xfffdf6e3;
		public static final int YELLOW  = 0xffb58900;
		public static final int ORANGE  = 0xffcb4b16;
		public static final int RED     = 0xffdc322f;
		public static final int MAGENTA = 0xffd33682;
		public static final int VIOLET  = 0xff6c71c4;
		public static final int BLUE    = 0xff268bd2;
		public static final int CYAN    = 0xff2aa198;
		public static final int GREEN   = 0xff859900;
		//----------------------------------------------------------------------

		public static final int[] ALL = new int[] {
				BASE03, BASE02, BASE01, BASE00,
				BASE0,  BASE1,  BASE2,  BASE3,
				YELLOW, ORANGE, RED,    MAGENTA,
				VIOLET, BLUE,   CYAN,   GREEN
		};

		//----------------------------------------------------------------------
	}
	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
