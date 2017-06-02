import java.util.ArrayList;
/**
 * This represents the Queen chess piece which is a subclass of Chesspiece
 * Queens are only allowed to move any number of square in horizontal and vertical plane 
 * as well as move any number of squares on the diagonals of the board
 * Contains the method moveTo,updateThreateningLocation
 * @author (Jason Wang) 
 * @version (Nov 27 2016)
 */
public class Queen extends Chesspiece
{
    
    /**
     * Constructor for objects of class Queen accepts String value for the
     * player,ChessLocation object for the pieces location 
     * and ChessGame object for the game which this piece belongs to
     */
    public Queen(String player,ChessLocation initial_location,ChessBoard board)
    {
        super(player,initial_location,board);
        super.getChessBoard().placePieceAt(this,super.getLocation());
        if (getOwner().equals("Player1"))
        {
            this.id='Q';
        }
        else if (getOwner().equals("Player2"))
        {
            this.id='q';
        }
    }
    
    /**
     * This method accepts an object of ChessLocation as the new location which the user wants to move to
     * It checks to see if a move is legal by the standards of the Queen piece if so, it will move
     * returns true if move worked false if not
     */
    public boolean moveTo(ChessLocation destination)
    {
        //checks to see if queen is moving in vertical plane and is actually being moved to new location
        if(destination.getCol()==super.getLocation().getCol() && !(super.getLocation().equals(destination)))
        {
            //makes sure queen has line of sight to new location
            if ((checkLineOfSight(super.getLocation(),destination)))
            {
                boolean moveSucess;
                moveSucess=super.moveTo(destination);
                updateThreateningLocation(destination);
                return moveSucess;
            }
            else
            {
                System.out.println("Another piece is in the way");
                return false;
            }
        }
        //checks to see if queen is moving in horizontal plane and is being moved to new location
        else if(destination.getRow()==super.getLocation().getRow() && !(super.getLocation().equals(destination)))
        {
             //makes sure queen has line of sight to new location
            if(checkLineOfSight(super.getLocation(),destination))
            {
                boolean moveSucess;
                moveSucess=super.moveTo(destination);
                updateThreateningLocation(destination);
                return moveSucess;
            }
            else
            {
                System.out.println("Another piece is in the way");
                return false;
            }
        }
        //checks to see if queen is moving in diagonals and is being moved to new location
        else if(Math.abs(super.getLocation().getCol()-destination.getCol())==Math.abs(super.getLocation().getRow()-destination.getRow()) && 
                !(super.getLocation().equals(destination)))
        {
             //makes sure queen has line of sight to new location
            if(checkLineOfSight(super.getLocation(),destination))
            {
                boolean moveSucess;
                moveSucess=super.moveTo(destination);
                updateThreateningLocation(destination);
                return moveSucess;
            }
            else
            {
                System.out.println("Another piece is in the way");
                return false;
            }
        }
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

