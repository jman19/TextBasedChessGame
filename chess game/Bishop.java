import java.util.ArrayList;
/**
 * This represents the Bishop chess piece which is a subclass of Chesspiece
 * Bishops are only allowed to move in an diagonal patten on the board
 * Contains the method moveTo,updateThreateningLocation
 * @author (Jason Wang) 
 * @version (Nov 27 2016)
 */
public class Bishop extends Chesspiece
{
    /**
     * Constructor for objects of class Bishop accepts String value for the
     * player,ChessLocation object for the pieces location 
     * and ChessGame object for the game which this piece belongs to
     */
    public Bishop(String player,ChessLocation initial_location,ChessBoard board)
    {
        super(player,initial_location,board);
        super.getChessBoard().placePieceAt(this,super.getLocation());
        if (getOwner().equals("Player1"))
        {
            this.id='B';
        }
        else if (getOwner().equals("Player2"))
        {
            this.id='b';
        }
    }

    /**
     * This method accepts an object of ChessLocation as the new location which the user wants to move to
     * It checks to see if a move is legal by the standards of the Bishop piece if so, it will move
     * returns true if move worked false if not
     */
    public boolean moveTo(ChessLocation destination)
    {
        //checks to see if Bishop is moving in diagonal and that it is actually being moved to a new location
        if(Math.abs(super.getLocation().getCol()-destination.getCol())==Math.abs(super.getLocation().getRow()-destination.getRow())
            && !(super.getLocation().equals(destination)))
        {
            // makes sure the piece has line of sight to the new location
            if((checkLineOfSight(super.getLocation(),destination)))
            {
                boolean moveSucess;
                moveSucess=super.moveTo(destination);
                updateThreateningLocation(destination);
                return moveSucess;
            }
            // tells the user the piece is blocked
            else
            {
                System.out.println("Another piece is in the way");
                return false;
            }
        }
        //tells user the move is illegal
        else
        {
            System.out.println("Invalid move");
            return false;
        }
        
    }
    /**
     * This method updates the places where the chesspiece is threating 
     * It accepts an object of Class ChessLocation 
     * Returns nothing
     */
    public void updateThreateningLocation(ChessLocation newLocation)
    {
        ArrayList<ChessLocation> newThreat=new ArrayList<ChessLocation> ();
        int r=newLocation.getRow();
        //checks downward diagonals right
        for (int c=0;( c<=(7-newLocation.getCol()) && r<=7); c++)
        {
            if (getChessBoard().getPieceAt(r,c+newLocation.getCol())==null)
            {
                newThreat.add(new ChessLocation(r,c+newLocation.getCol()));
                
            }
            else if (!getChessBoard().getPieceAt(r,c+newLocation.getCol()).getOwner().equals(getOwner()))
            {
                newThreat.add(new ChessLocation(r,c+newLocation.getCol()));
                break;
            }
            // this makes sure to stop adding location when it hits a friendly piece which cast
            // a shadow over the rest of the moves
            else if (getChessBoard().getPieceAt(r,c+newLocation.getCol()).getOwner().equals(getOwner()))
            {
                if(r!=getLocation().getRow() && c+newLocation.getCol()!=getLocation().getCol())
                {
                    newThreat.add(new ChessLocation(r,c+newLocation.getCol()));
                    break;
                }
            }
            r++; 
            
        }
        r=newLocation.getRow();
        //checks downward diagonals left
        for (int c=0; ( c<=(newLocation.getCol()) && r<=7); c++)
        {
            if (getChessBoard().getPieceAt(r,newLocation.getCol()-c)==null)
            {
                newThreat.add(new ChessLocation(r,newLocation.getCol()-c));
                
            }
            else if (!getChessBoard().getPieceAt(r,newLocation.getCol()-c).getOwner().equals(getOwner()))
            {
                newThreat.add(new ChessLocation(r,newLocation.getCol()-c));
                break;
            }
            // this makes sure to stop adding location when it hits a friendly piece which cast
            // a shadow over the rest of the moves
            else if (getChessBoard().getPieceAt(r,newLocation.getCol()-c).getOwner().equals(getOwner()))
            {
                if(r!=getLocation().getRow() && newLocation.getCol()-c!=getLocation().getCol())
                {
                    newThreat.add(new ChessLocation(r,newLocation.getCol()-c));
                    break;
                }
            }
            r++; 
            
        }
        
        r=newLocation.getRow();
        //checks upward diagonals left
        for (int c=0; ( c<=(newLocation.getCol()) && r>=0); c++)
        {
            if (getChessBoard().getPieceAt(r,newLocation.getCol()-c)==null)
            {
                newThreat.add(new ChessLocation(r,newLocation.getCol()-c));
                
            }
            else if (!getChessBoard().getPieceAt(r,newLocation.getCol()-c).getOwner().equals(getOwner()))
            {
                newThreat.add(new ChessLocation(r,newLocation.getCol()-c));
                break;
            }
            // this makes sure to stop adding location when it hits a friendly piece which cast
            // a shadow over the rest of the moves
            else if (getChessBoard().getPieceAt(r,newLocation.getCol()-c).getOwner().equals(getOwner()))
            {
                if(r!=getLocation().getRow() && newLocation.getCol()-c!=getLocation().getCol())
                {
                    newThreat.add(new ChessLocation(r,newLocation.getCol()-c));
                    break;
                }
            }
            r--; 
            
        }
         //checks upward diagonals right
        r=newLocation.getRow();
        for (int c=0; ( c<=(7-newLocation.getCol()) && r>=0); c++)
        {
            
            if (getChessBoard().getPieceAt(r,c+newLocation.getCol())==null)
            {
                newThreat.add(new ChessLocation(r,c+newLocation.getCol()));
                
            }
            else if (!getChessBoard().getPieceAt(r,c+newLocation.getCol()).getOwner().equals(getOwner()))
            {
                newThreat.add(new ChessLocation(r,c+newLocation.getCol()));
                break;
            }
            // this makes sure to stop adding location when it hits a friendly piece which cast
            // a shadow over the rest of the moves
            else if (getChessBoard().getPieceAt(r,c+newLocation.getCol()).getOwner().equals(getOwner()))
            {
                if(r!=getLocation().getRow() && c+newLocation.getCol()!=getLocation().getCol())
                {
                    newThreat.add(new ChessLocation(r,c+newLocation.getCol()));
                    break;
                }
            }
            r--; 
            
        }
        setThreateningLocations(newThreat);
    }
    
}
