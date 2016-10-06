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

	int cellWidth;
	int cellHeight;

	private final SortedMap<Integer,Map<Integer,Pair<Integer,Integer>>> CELLS = new TreeMap<>();

	//--------------------------------------------------------------------------

	public void mapCell( int spriteId, int index, int cellX, int cellY ) {
		CELLS.computeIfAbsent( spriteId, (i) -> new TreeMap<>() )
				.put( index, new Pair<>( cellX, cellY ));
	}

	//--------------------------------------------------------------------------

	public void mapRow( int spriteId, int startX, int startY, int cellCount ) {
		IntStream.range( 0, cellCount ).forEach( (index) -> {
			mapCell( spriteId, index , startX + ( cellWidth * index ), startY );
		});
	}

	//--------------------------------------------------------------------------

	public void mapColumn( int spriteId, int startX, int startY, int cellCount ) {
		IntStream.range( 0, cellCount ).forEach( (index) -> {
			mapCell( spriteId, index, startX, startY + ( cellHeight * index ));
		});
	}

	//--------------------------------------------------------------------------

	public void applyRegion( Sprite sprite, int spriteId, int index ) {
		sprite.setRegion(
				CELLS.get( spriteId ).get( index ).getKey() * cellWidth,
				CELLS.get( spriteId ).get( index ).getValue() * cellHeight,
				cellWidth,
				cellHeight );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
