package stream.alwaysbecrafting.chatbuster.data;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

import javafx.util.Pair;
import stream.alwaysbecrafting.chatbuster.util.Log;

//==============================================================================
public class SpriteMap {
	//--------------------------------------------------------------------------

	private final int CELL_WIDTH;
	private final int CELL_HEIGHT;

	private final SortedMap<Integer,Map<Integer,Pair<Integer,Integer>>> CELLS = new TreeMap<>();

	//--------------------------------------------------------------------------

	public SpriteMap( int cellWidth, int cellHeight ) {
		CELL_WIDTH = cellWidth;
		CELL_HEIGHT = cellHeight;
	}

	//--------------------------------------------------------------------------

	public void mapCell( int index, int cellX, int cellY ) {
		mapCell( index, cellX, cellY, 0 );
	}

	//--------------------------------------------------------------------------

	public void mapRow( int index, int startX, int startY, int cellCount ) {
		IntStream.range( 0, cellCount ).forEach( (frame) -> {
			mapCell( index, startX + ( CELL_WIDTH * frame ), startY, frame );
		});
	}

	//--------------------------------------------------------------------------

	public void mapColumn( int index, int startX, int startY, int cellCount ) {
		IntStream.range( 0, cellCount ).forEach( (frame) -> {
			mapCell( index, startX, startY + ( CELL_HEIGHT * frame ), frame );
		});
	}

	//--------------------------------------------------------------------------

	private void mapCell( int index, int cellX, int cellY, int frame ) {
		CELLS.computeIfAbsent( index, (i) -> new TreeMap<>() )
				.put( frame, new Pair<>( cellX, cellY ));

		if ( index == 2 ) {
			Log.d( index + " - " + frame + " - " + cellX + "," + cellY );
		}
	}

	//--------------------------------------------------------------------------

	public void applyRegion( Sprite sprite, int index, int frame ) {
		final int cellX = CELLS.get( index ).get( frame ).getKey();
		final int cellY = CELLS.get( index ).get( frame ).getValue();

		final int left = cellX * CELL_WIDTH;
		final int top  = cellY * CELL_HEIGHT;

		sprite.setRegion( left, top, CELL_WIDTH, CELL_HEIGHT );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
