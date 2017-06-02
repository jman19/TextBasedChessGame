import java.util.ArrayList;
/**
 * This represents the knight chess piece which is a subclass of Chesspiece
 * Knights are only allowed to move in an L patten on the board
 * Contains the method moveTo,updateThreateningLocation
 * @author (Jason Wang) 
 * @version (Nov 27 2016)
 */

public class Knight extends Chesspiece
{
    /**
     * Constructor for objects of class Knight accepts String value for the
     * player,ChessLocation object for the pieces location 
     * and ChessGame object for the game which this piece belongs to
     */
    public Knight(String owner,ChessLocation initialLocation,ChessBoard board)
    {
        //call to the super class constructor
        super(owner,initialLocation,board);
        //this puts the knight piece on the chess board 
        super.getChessBoard().placePieceAt(this,super.getLocation());
        //sets its piece id
        if (getOwner().equals("Player1"))
        {
            this.id='N';
        }
        else if (getOwner().equals("Player2"))
        {
            this.id='n';
        }
    }
   
    /**
     * This method accepts an object of ChessLocation as the new location which the user wants to move to
     * It checks to see if a move is legal by the standards of the knight piece if so, it will move
     * returns true if move worked false if not
     */
    public boolean moveTo(ChessLocation newLocation)
    {
        // this checks if the abs difference between new row and old row is 2 or the same thing for col
        // it does this since a knight piece moves in a L patten
        if (Math.abs(super.getLocation().getRow()-newLocation.getRow())==2 || Math.abs(super.getLocation().getCol()-newLocation.getCol())==2)
            {
                //this checks to see if after it moves away 2 that its displaced by 1 either in row or col thus completeing the 
                //L move pattern
                if (Math.abs(super.getLocation().getRow()-newLocation.getRow())==1 || Math.abs(super.getLocation().getCol()-newLocation.getCol())==1)
                {
                    //call the super class moveTo
                    //also updates the threateningLocation
                    updateThreateningLocation(newLocation);
                    //for(ChessLocation a:getThreateningLocations ())
                    //{
                        //System.out.println("("+a.getRow()+","+a.getCol()+")");
                   // }
                    return super.moveTo(newLocation);
                }
                //prints invalid move if the move is illegal since it failed the meet final condition
                else
                {
                    System.out.println("Invalid move");
                    return false;
                }
            }
            //prints invalid move if the move is illegal but it relates to the outer if statment if the move didnt meet 
            //the first requirment
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
        // this checks the upper part for knight too see if it threatens any locations
        // within chessboard
        ArrayList<ChessLocation> newThreat=new ArrayList<ChessLocation> ();
        if (newLocation.getRow()-2>=0)
        {
            if (newLocation.getCol()+1<=7)
            {
                newThreat.add(new ChessLocation(newLocation.getRow()-2,newLocation.getCol()+1));
            }
            if (newLocation.getCol()-1>=0)
            {
                newThreat.add(new ChessLocation(newLocation.getRow()-2,newLocation.getCol()-1));
            }
        }
        //checks the bottom move for knight
        if (newLocation.getRow()+2<=7)
        {
            if (newLocation.getCol()+1<=7)
            {
                newThreat.add(new ChessLocation(newLocation.getRow()+2,newLocation.getCol()+1));
            }
            if (newLocation.getCol()-1>=0)
            {
                newThreat.add(new ChessLocation(newLocation.getRow()+2,newLocation.getCol()-1));
            }
        }
        
        //this checks the left move part for knight
        if (newLocation.getCol()-2>=0)
        {
            if (newLocation.getRow()+1<=7)
            {
                newThreat.add(new ChessLocation(newLocation.getRow()+1,newLocation.getCol()-2));
            }
            if (newLocation.getRow()-1>=0)
            {
                newThreat.add(new ChessLocation(newLocation.getRow()-1,newLocation.getCol()-2));
            }
        }
        //this checks the right move part for knight
        if (newLocation.getCol()+2<=7)
        {
            if (newLocation.getRow()+1<=7)
            {
                newThreat.add(new ChessLocation(newLocation.getRow()+1,newLocation.getCol()+2));
            }
            if (newLocation.getRow()-1>=0)
            {
                newThreat.add(new ChessLocation(newLocation.getRow()-1,newLocation.getCol()+2));
            }
        }
        setThreateningLocations(newThreat);
    }
    
}
