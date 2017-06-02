import java.util.ArrayList;
/**
 * This represents the Rook chess piece which is a subclass of Chesspiece
 * Rooks are only allowed to move in the horizontal and vertical planes
 * contains the method moveTo,updateThreateningLocation
 * 
 * @author (Jason Wang) 
 * @version (Nov 27 2016)
 */
public class Rook extends Chesspiece
{

    /**
     * Constructor for objects of class Rook accepts String value for the 
     * player,ChessLocation object for the pieces location 
     * and ChessGame object for the game which this piece belongs to
     */
    public Rook(String player,ChessLocation initial_location,ChessBoard board)
    {
        //call to the super class constructor
        super(player,initial_location,board);
        //this puts the knight piece on the chess board 
        super.getChessBoard().placePieceAt(this,super.getLocation());
        //sets its piece id
        if (getOwner().equals("Player1"))
        {
            this.id='R';
        }
        else if (getOwner().equals("Player2"))
        {
            this.id='r';
        }
    }
    /**
     * This method accepts an object of ChessLocation as the new location which the user wants to move to
     * It checks to see if a move is legal by the standards of the Rook piece if so, it will move
     * returns boolean true for sucessue false for not
     */
    public boolean moveTo(ChessLocation destination)
    {
        //checks to see if Rook is moving in vertical plane as well as making sure it actually is being
        //moved to a new location
        if(destination.getCol()==super.getLocation().getCol() && !(super.getLocation().equals(destination)))
        {
            //checks for line of sight to the new location
            
            if ((checkLineOfSight(super.getLocation(),destination)))
            {
                boolean moveSucess;
                moveSucess=super.moveTo(destination);
                updateThreateningLocation(destination);
                return moveSucess;
            }
            // tells the user that the line of sight is blocked
            else
            {
                System.out.println("Another piece is in the way");
                return false;
            }
        }
        //checks to see if Rook is moving in horizontal plane as well as making sure it actually is being
        //moved to a new location
        else if(destination.getRow()==super.getLocation().getRow()&& !(super.getLocation().equals(destination)))
        {
            //checks for line of sight to the new location
            if(checkLineOfSight(super.getLocation(),destination))
            {
                boolean moveSucess;
                moveSucess=super.moveTo(destination);
                updateThreateningLocation(destination);
                return moveSucess;
            }
             // tells the user that the line of sight is blocked
            else
            {
                System.out.println("Another piece is in the way");
                return false;
            }
        }
         // tells the user that the move is not legal
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
        //checks the vertical region downward
        for (int i=newLocation.getRow(); i<=7; i++)
        {
            //this checks to make sure the location is empty means its possible for this piece to move
            //to that spot which makes that spot dangerous
            if(getChessBoard().getPieceAt(i,newLocation.getCol())==null)
            {
                newThreat.add(new ChessLocation(i,newLocation.getCol()));
            }
            //this adds the location of the region that is under threat if it happens to be an enermy piece
            else if (!getChessBoard().getPieceAt(i,newLocation.getCol()).getOwner().equals(getOwner()))
            {
                newThreat.add(new ChessLocation(i,newLocation.getCol()));
                break;
            }
            // this makes sure to stop adding location when it hits a friendly piece which cast
            // a shadow over the rest of the moves
            else if (getChessBoard().getPieceAt(i,newLocation.getCol()).getOwner().equals(getOwner()))
            {
                if(i!=getLocation().getRow())
                {
                    newThreat.add(new ChessLocation(i,newLocation.getCol()));
                    break;
                }
            }
        }
         //checks the vertical region upward
        for (int i=newLocation.getRow(); i>=0; i--)
        {
            //this checks to make sure the location is empty means its possible for this piece to move
            //to that spot which makes that spot dangerous
            if(getChessBoard().getPieceAt(i,newLocation.getCol())==null)
            {
                newThreat.add(new ChessLocation(i,newLocation.getCol()));
            }
            //this adds the location of the region that is under threat if it happens to be an enermy piece
            else if (!getChessBoard().getPieceAt(i,newLocation.getCol()).getOwner().equals(getOwner()))
            {
                newThreat.add(new ChessLocation(i,newLocation.getCol()));
                break;
            }
            // this makes sure to stop adding location when it hits a friendly piece which cast
            // a shadow over the rest of the moves
            else if (getChessBoard().getPieceAt(i,newLocation.getCol()).getOwner().equals(getOwner()))
            {
                if(i!=getLocation().getRow())
                {
                    newThreat.add(new ChessLocation(i,newLocation.getCol()));
                    break;
                }
            }
        }
         //checks the horizontal region right
        for (int i=newLocation.getCol(); i<=7; i++)
        {
            //this checks to make sure the location is empty means its possible for this piece to move
            //to that spot which makes that spot dangerous
            if(getChessBoard().getPieceAt(newLocation.getRow(),i)==null)
            {
                newThreat.add(new ChessLocation(newLocation.getRow(),i));
            }
            //this adds the location of the region that is under threat if it happens to be an enermy piece
            else if (!getChessBoard().getPieceAt(newLocation.getRow(),i).getOwner().equals(getOwner()))
            {
                newThreat.add(new ChessLocation(newLocation.getRow(),i));
                break;
            }
            // this makes sure to stop adding location when it hits a friendly piece which cast
            // a shadow over the rest of the moves
            else if (getChessBoard().getPieceAt(newLocation.getRow(),i).getOwner().equals(getOwner()))
            {
                if (i!=getLocation().getCol())
                {
                    newThreat.add(new ChessLocation(newLocation.getRow(),i));
                    break;
                }
            }
        }
         //checks the horizontal region left
        for (int i=newLocation.getCol(); i>=0; i--)
        {
             //this checks to make sure the location is empty means its possible for this piece to move
            //to that spot which makes that spot dangerous
            if(getChessBoard().getPieceAt(newLocation.getRow(),i)==null)
            {
                newThreat.add(new ChessLocation(newLocation.getRow(),i));
            }
            //this adds the location of the region that is under threat if it happens to be an enermy piece
            else if (!getChessBoard().getPieceAt(newLocation.getRow(),i).getOwner().equals(getOwner()))
            {
                newThreat.add(new ChessLocation(newLocation.getRow(),i));
                break;
            }
            // this makes sure to stop adding location when it hits a friendly piece which cast
            // a shadow over the rest of the moves
            else if (getChessBoard().getPieceAt(newLocation.getRow(),i).getOwner().equals(getOwner()))
            {
                if (i!=getLocation().getCol())
                {
                    newThreat.add(new ChessLocation(newLocation.getRow(),i));
                    break;
                }
            }
        }
        setThreateningLocations(newThreat);
    }
}
