
/**
 * Interface for Chesspiece.
 * 
 * @author (Jason wang) 
 * @version (Nov 26 2016)
 */
public interface ChessPieceInterface
{
    /**
     * this method moves a piece 
     * 
     * @param  takes object of ChessLocation
     * @return  returns boolean if moveTo worked else return false
     */
    public boolean moveTo(ChessLocation location);
}
