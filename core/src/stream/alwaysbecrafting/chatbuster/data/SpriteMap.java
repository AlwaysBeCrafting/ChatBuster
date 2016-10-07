package stream.alwaysbecrafting.chatbuster.data;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

import javafx.util.Pair;

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

	public void mapCell( int spriteId, int cellX, int cellY ) {
		mapCell( spriteId, cellX, cellY, 0 );
	}

	//--------------------------------------------------------------------------

	public void mapRow( int spriteId, int startX, int startY, int cellCount ) {
		IntStream.range( 0, cellCount ).forEach( (index) -> {
			mapCell( spriteId, startX + ( CELL_WIDTH * index ), startY, index );
		});
	}

	//--------------------------------------------------------------------------

	public void mapColumn( int spriteId, int startX, int startY, int cellCount ) {
		IntStream.range( 0, cellCount ).forEach( (index) -> {
			mapCell( spriteId, startX, startY + ( CELL_HEIGHT * index ), index );
		});
	}

	//--------------------------------------------------------------------------

	private void mapCell( int spriteId, int cellX, int cellY, int index ) {
		CELLS.computeIfAbsent( spriteId, (i) -> new TreeMap<>() )
				.put( index, new Pair<>( cellX, cellY ));
	}

	//--------------------------------------------------------------------------

	public void applyRegion( Sprite sprite, int spriteId, int index ) {
		sprite.setRegion(
				CELLS.get( spriteId ).get( index ).getKey() * CELL_WIDTH,
				CELLS.get( spriteId ).get( index ).getValue() * CELL_HEIGHT,
				CELL_WIDTH,
				CELL_HEIGHT );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
