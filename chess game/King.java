import java.util.ArrayList;
/**
 * This represents the King chess piece which is a subclass of Chesspiece
 * Kings are only allowed to move 1 square in horizontal and vertical plane 
 * as well as 1 squares on the diagonals of the board
 * Contains the method moveTo,check,locationInDanger,updateThreateningLocation
 * @author (Jason Wang) 
 * @version (june 1 2017)
 */
public class King extends Chesspiece
{
    /**
     * Constructor for objects of class King accepts String value for the
     * player,ChessLocation object for the pieces location 
     * and ChessGame object for the game which this piece belongs to
     */
    public King(String player,ChessLocation initial_location,ChessBoard board)
    {
        super(player,initial_location,board);
        //sets the location of itself on the board
        super.getChessBoard().placePieceAt(this,super.getLocation());
        if (getOwner().equals("Player1"))
        {
            this.id='K';
        }
        else if (getOwner().equals("Player2"))
        {
            this.id='k';
        }
    }

    /**
     * This method accepts an object of ChessLocation as the new location which the user wants to move to
     * It checks to see if a move is legal by the standards of the King piece if so, it will move
     * returns true if move worked false if it didnt work
     */
    public boolean moveTo(ChessLocation destination)
    {
        //checks to see if king is moving in vertical plane and is being moved to new location
        if(destination.getCol()==super.getLocation().getCol() && Math.abs(destination.getRow()-super.getLocation().getRow())==1 &&
           !(super.getLocation().equals(destination)) && locationInDanger(destination).isEmpty())
        {
             //makes sure king has line of sight to new location
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
        //checks to see if king is moving in horizontal plane and is being moved to new location
        else if(destination.getRow()==super.getLocation().getRow() && Math.abs(destination.getCol()-super.getLocation().getCol())==1
                && !(super.getLocation().equals(destination)) && locationInDanger(destination).isEmpty())
        {
            //makes sure king has line of sight to new location
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
        //checks to see if king is moving in diagonal and is being moved to new location
        else if(Math.abs(super.getLocation().getCol()-destination.getCol())==Math.abs(super.getLocation().getRow()-destination.getRow()) &&
                Math.abs(super.getLocation().getCol()-destination.getCol())==1 && Math.abs(super.getLocation().getRow()-destination.getRow())==1
                && !(super.getLocation().equals(destination)) && locationInDanger(destination).isEmpty())
        {
            //makes sure king has line of sight to new location
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
        else if(locationInDanger(destination).isEmpty())
        {
            System.out.println("Invalid move");
            return false;
        }
        else
        {
            System.out.println("Location endangers king");
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
        //Adds the threats from the lower diagonal and lower vertical 
        if(newLocation.getRow()+1<=7)
        {
            if(newLocation.getCol()+1<=7)
            {
                newThreat.add(new ChessLocation(newLocation.getRow()+1,newLocation.getCol()+1));
                //newThreat.add(new ChessLocation(newLocation.getRow(),newLocation.getCol()+1));
            }
            if(newLocation.getCol()-1>=0)
            {
                newThreat.add(new ChessLocation(newLocation.getRow()+1,newLocation.getCol()-1));
                //newThreat.add(new ChessLocation(newLocation.getRow(),newLocation.getCol()-1));
            }
            newThreat.add(new ChessLocation(newLocation.getRow()+1,newLocation.getCol()));
        }
        //adds threat from horizontal
        if(newLocation.getCol()+1<=7)
        {
            newThreat.add(new ChessLocation(newLocation.getRow(),newLocation.getCol()+1));
        }
        if(newLocation.getCol()-1>=0)
        {
            newThreat.add(new ChessLocation(newLocation.getRow(),newLocation.getCol()-1));
        }
        //Adds the threats from the upper diagonal and upper vertical
        if(newLocation.getRow()-1>=0)
        {
            if(newLocation.getCol()+1<=7)
            {
                newThreat.add(new ChessLocation(newLocation.getRow()-1,newLocation.getCol()+1));
            }
            if(newLocation.getCol()-1>=0)
            {
                newThreat.add(new ChessLocation(newLocation.getRow()-1,newLocation.getCol()-1));
            }
            newThreat.add(new ChessLocation(newLocation.getRow()-1,newLocation.getCol()));
        }
        setThreateningLocations(newThreat);
    }
    /**
     * This method will return all enermy chessPiece it finds that is threating the king and
     * return it as a arraylist, empty for no threat
     * Accepts an object of ChessLocation
     */
    public ArrayList<Chesspiece> locationInDanger(ChessLocation destination)
    {
        ArrayList<Chesspiece> danger=new ArrayList<Chesspiece>();
        for (int row=0; row<8; row++)
        {
            for (int col=0; col<8; col++)
            {
                if(getChessBoard().getPieceAt(row,col)!=null)
                {
                   
                    if(!getChessBoard().getPieceAt(row,col).getOwner().equals(getOwner()))
                    {
                        getChessBoard().getPieceAt(row,col).updateThreateningLocation(new ChessLocation(row,col));
                        
                        for (ChessLocation threat:getChessBoard().getPieceAt(row,col).getThreateningLocations())
                        {
                            if (threat.getRow()==destination.getRow() && threat.getCol()==destination.getCol())
                            {
                                danger.add(getChessBoard().getPieceAt(row,col));
                            }
                        }
                    }
                }
            }
        }
        return danger;
    }
    /**
     * This method will return a list of chesspieces that are threating the king will return empty list
     * if there are no threats
     */
    public ArrayList<Chesspiece> check()
    {
        return locationInDanger(getLocation());
    }
}
