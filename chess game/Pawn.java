import java.util.ArrayList;
/**
 * This represents the Pawn chess piece which is a subclass of Chesspiece
 * Pawns are only allowed to move 2 units forwards at the start then 1 units afterwards
 * but can capture diagonally 
 * Contains the method moveTo,updateThreateningLocation
 * @author (Jason Wang) 
 * @version (Nov 27 2016)
 */
public class Pawn extends Chesspiece
{
    // instance variables 
    //this variable is to check if the move is the first move for a pawn
    private boolean firstMove;
    
    /**
     * Constructor for objects of class Pawn accepts String value for the
     * player,ChessLocation object for the pieces location 
     * and ChessGame object for the game which this piece belongs to
     */
    public Pawn(String player,ChessLocation initial_location,ChessBoard board)
    {
       super(player,initial_location,board);
       super.getChessBoard().placePieceAt(this,super.getLocation());
       if (getOwner().equals("Player1"))
        {
            this.id='P';
        }
        else if (getOwner().equals("Player2"))
        {
            this.id='p';
        }
       firstMove=true;
    }
    public boolean isfirstMove()
    {
        return firstMove;
    }
    public void resetFirstMove()
    {
        firstMove=true;
    }
    /**
     * This method accepts an object of ChessLocation as the new location which the user wants to move to
     * It checks to see if a move is legal by the standards of the Pawn piece if so, it will move
     * returns true if move worked false if not
     */
    public boolean moveTo(ChessLocation destination)
    {
        //checks to see if pawn belongs to player1 as player one moves downwards compared to player 2 moving upwards
        if (super.getOwner()=="Player1")
        {
            //checks to see if it is the Pawns first move
            if(firstMove)
            {
                //makes sure the Pawn is moving vertical and is between 1 or 2 units
                if(destination.getCol()==super.getLocation().getCol() && ((destination.getRow()-super.getLocation().getRow())==2 ||
                (destination.getRow()-super.getLocation().getRow())==1))
                {
                    //makes sure the Pawn has line of sight and is being moved to a new location
                    if ((checkLineOfSight(super.getLocation(),destination)) && !super.getChessBoard().isPieceAt(destination.getRow(),destination.getCol()))
                    {
                        //sets firstMove to false as the pawn has used up its first move
                        firstMove=false;
                        super.moveTo(destination);
                        updateThreateningLocation(destination);
                        return true;
                        
                    }
                    
                    //tell the user that there is a piece in the way
                    else
                    {
                        System.out.println("Another piece is in the way");
                        return false;
                    }   
                
                }
                
                // checks to see if pawn is able to capture
                else if (super.getChessBoard().isPieceAt(destination.getRow(),destination.getCol()) && (destination.getRow()-super.getLocation().getRow()==1) 
                    && (Math.abs(destination.getCol()-super.getLocation().getCol()))==1 )
                {
                    moveTo(destination);
                    updateThreateningLocation(destination);
                    return true;
                } 
                
                else
                {
                    System.out.println("Invalid move");
                    return false;
                }
                
            }
            //makes sure the Pawn is moving vertical and its magnitude is 1
            else if (destination.getCol()==super.getLocation().getCol() && (destination.getRow()-super.getLocation().getRow())==1)
            {
                 //makes sure the Pawn has line of sight and is being moved to a new location
                if ((checkLineOfSight(super.getLocation(),destination)) && !super.getChessBoard().isPieceAt(destination.getRow(),destination.getCol()))
                {
                    super.moveTo(destination);
                    updateThreateningLocation(destination);
                    return true;
                }
                
                //tell the user that there is a piece in the way
                else
                {
                    System.out.println("Another piece is in the way");
                    return false;
                }
                
            
            }
            // checks to see if pawn is able to capture
            else if (super.getChessBoard().isPieceAt(destination.getRow(),destination.getCol()) && (destination.getRow()-super.getLocation().getRow()==1) 
                    && (Math.abs(destination.getCol()-super.getLocation().getCol()))==1 )
            {
                super.moveTo(destination);
                updateThreateningLocation(destination);
                return true;
            }
            else
            {
                System.out.println("Invalid move");
                return false;
            }
        }
        
        // if the outter if failed then it means this pawn belongs to player 2
        else
        {
            if(firstMove)
            {
                //makes sure the Pawn is moving vertical and is between 1 or 2 units
                if(destination.getCol()==super.getLocation().getCol() && ((destination.getRow()-super.getLocation().getRow())==-2 ||
                (destination.getRow()-super.getLocation().getRow())==-1))
                {
                    if ((checkLineOfSight(super.getLocation(),destination)) && !super.getChessBoard().isPieceAt(destination.getRow(),destination.getCol()))
                    {
                        //sets firstMove to false as the pawn has used up its first move
                        firstMove=false; 
                        super.moveTo(destination);
                        updateThreateningLocation(destination);
                        return true;
                        
                    }
                    else
                    {
                        System.out.println("Another piece is in the way");
                        return false;
                    }
                }
                // checks to see if pawn is able to capture
                else if (super.getChessBoard().isPieceAt(destination.getRow(),destination.getCol()) && (destination.getRow()-super.getLocation().getRow()==-1) 
                    && (Math.abs(destination.getCol()-super.getLocation().getCol()))==1 )
                {
                    super.moveTo(destination);
                    updateThreateningLocation(destination);
                    return true;
                } 
                
                else
                {
                    System.out.println("Invalid move");
                    return false;
                }
            }
            //makes sure the Pawn is moving vertical and its magnitude is -1 as it is moving upwards
            else if (destination.getCol()==super.getLocation().getCol() && (destination.getRow()-super.getLocation().getRow())==-1)
            {
                if ((checkLineOfSight(super.getLocation(),destination)) && !super.getChessBoard().isPieceAt(destination.getRow(),destination.getCol()))
                {
                    super.moveTo(destination);
                    updateThreateningLocation(destination);
                    return true; 
                }
                else
                {
                    System.out.println("Another piece is in the way");
                    return false;
                }
            }
            // checks to see if pawn is able to capture
            else if (super.getChessBoard().isPieceAt(destination.getRow(),destination.getCol()) && (destination.getRow()-super.getLocation().getRow()==-1) 
                    && (Math.abs(destination.getCol()-super.getLocation().getCol()))==1 )
            {
                super.moveTo(destination);
                updateThreateningLocation(destination);
                return true;
            } 
              
            else
            {
                System.out.println("Invalid move");
                return false;
            }
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
        if(getOwner()=="Player1")
        {
            if(newLocation.getRow()+1<=7)
            {
                if(newLocation.getCol()+1<=7)
                {
                    newThreat.add(new ChessLocation(newLocation.getRow()+1,newLocation.getCol()+1));
                }
                if(newLocation.getCol()-1>=0)
                {
                    newThreat.add(new ChessLocation(newLocation.getRow()+1,newLocation.getCol()-1));
                }
            }
        }
        else
        {
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
            }
        }
        setThreateningLocations(newThreat);
    }
}
