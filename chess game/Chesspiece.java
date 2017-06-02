import java.util.ArrayList;
/**
 * This represents the Superclass chesspiece
 * Models the general behaviour of a chess piece
 * contains the method moveTo(),checkLineOfSight(),getChessGame(),getOwner(),getLocation()
 * getId(),setNewLocation(),getThreateningLocations(),setThreateningLocations 
 * @author (Jason Wang) 
 * @version (Nov 27 2016)
 */
public abstract class  Chesspiece implements ChessPieceInterface
{
    // instance variables 
    private ChessBoard board;
    private String owner;
    private ChessLocation location;
    protected char id;
    private ArrayList<ChessLocation> threateningLocations;
    /**
     * Constructor for objects of class Chesspiece accepts String value for the 
     * player,ChessLocation object for the pieces location 
     * and ChessGame object for the game which this piece belongs to
     */
    public Chesspiece(String owner,ChessLocation initialLocation,ChessBoard board)
    {
        // initialise instance variables
        this.owner=owner;
        location=initialLocation;
        this.board=board;
        threateningLocations= new ArrayList<ChessLocation>();
    }
    /**
     * This method accepts an object of ChessLocation as the new location which the user wants to move to
     * It checks to see if a move is within the board as well as if you have your own piece blocking another
     * it will move the piece if it is inside and nothing is blocking its final location
     * returns true if move suceeded false if not
     */
    public boolean moveTo(ChessLocation newLocation)
    {
         //calls the ChessGame class method to get the game board removes piece in old location
         if((newLocation.getRow()>=0 && newLocation.getRow()<=7) && (newLocation.getCol()>=0 && newLocation.getCol()<=7))
         {   
             //this checks to make sure the piece being attacked is not owned by yourself since you cant
             //capture your own pieces
             if(board.getPieceAt(newLocation.getRow(),newLocation.getCol())==null || !owner.equals(board.getPieceAt(newLocation.getRow(),newLocation.getCol()).getOwner()))
             {
                 board.removePieceAt(location);
                 //moves piece to its new location
                 board.placePieceAt(this,newLocation);
                 return true;
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
     * This method checks for line of sight for a chess piece if there are no other pieces blocking its way the method returns true
     * other wise it will return false as in there is something blocking it
     * 
     */
    protected boolean checkLineOfSight(ChessLocation start,ChessLocation end)
    {
        // checks to see if the move is in the horizontal plane
        if (start.getRow()==end.getRow())
        {
            //if starting col is larger then end col then we are going left
            if(start.getCol()> end.getCol())
            {
                //if it is then just subtract it until we reach the number one value away from end col 
                //this is done since a piece at the very end of the move order doesnt count as blocking it
                for (int i=start.getCol();i>end.getCol();i--)
                {
                    // this if makes sure we dont check ourselves in the first pass
                    if(i!=start.getCol())
                    {
                        if(board.isPieceAt(end.getRow(),i))
                        {
                            return !(board.isPieceAt(end.getRow(),i));
                        }
                    }
                }
                return true;
            }
            //if first contition is false then that means the piece is moving right
            else
            {
                // if it is just add to the start until we reach the second last value from end col
                //this is done since a piece at the very end of the move order doesnt count as blocking it
                for(int i=start.getCol();i<end.getCol();i++)
                {
                    // this makes sure we dont check ourselves in the first pass
                    if(i!=start.getCol())
                    {
                        if(board.isPieceAt(end.getRow(),i))
                        {
                            return !(board.isPieceAt(end.getRow(),i));
                        }
                    }
                }
                return true;
            }
            
        }
        // checks to see if move is in the vertical plane
        else if (start.getCol()==end.getCol())
        {
            //this checks to see if we are moving the piece upwards
            if(start.getRow()> end.getRow())
            {
                //if it is then just subtract start row until we reach the second last value from end row
                //this is done since a piece at the very end of the move order doesnt count as blocking it
                for (int i=start.getRow();i>end.getRow();i--)
                {
                     // this makes sure we dont check the piece we intend to move in the first pass
                    if(board.isPieceAt(i,end.getCol()))
                    {
                        if(i!=start.getRow())
                        {
                            return !(board.isPieceAt(i,end.getCol()));
                        }
                    }
                }
                return true;
            }
            //if the first contition is false then the piece is being moved downwards
            else
            {
                //if it is then just add to it until we reach the second last value from end row
                //this is done since a piece at the very end of the move order doesnt count as blocking it
                for(int i=start.getRow();i<end.getRow();i++)
                {
                    if(board.isPieceAt(i,end.getCol()))
                    {
                        // this makes sure we dont check ourselves in the first pass
                        if(i!=start.getRow())
                        {
                            return !(board.isPieceAt(i,end.getCol()));
                        }
                    }
                }
                return true;
            }
        }
        // if the above 2 cases failed then the only path left is diagonally
       
       else
       {    //checks to see if the diagonal is going left compared to start location
            if(start.getCol()>end.getCol())
            {
                //checks to see if the diagonal has postive slope
                if(start.getRow()>end.getRow())
                {
                    //sets c to the start col
                    int c=start.getCol();
                    //it subtracts row since that makes the diagonal slope positive as in moving up
                    //it only goes to the second last value from row since a piece at the very end of the move doesnt block it
                    for (int r=start.getRow();r>end.getRow();r--)
                    {
                        //it only goes to the second last value from col since a piece at the very end of the move doesnt block it
                        if(c>end.getCol())
                        {
                            // this makes sure we dont check ourselves in the first pass
                            if (c!=start.getCol() && r!=start.getRow())
                            {
                                //this checks if there is a piece at that location
                                if(board.isPieceAt(r,c))
                                {
                                    //returns false as the piece doesnt have line of sight if something is blocking it
                                    return !(board.isPieceAt(r,c));
                                }
                            }
                            //this increments the col by subtracting it as that makes it go left from the starting point (run) m=rise/run
                            c=c-1;
                        }
                    }
                    return true;
                }
                //if the first statment is false then that means this diagonal has negative slope
                else
                {
                   int c=start.getCol();
                   //this adds to row as that makes the slope negative as in moving downwards
                   //it only goes to the second last value from row since a piece at the very end of the move doesnt block it
                   for (int r=start.getRow();r<end.getRow();r++)
                    {
                        //it only goes to the second last value from col since a piece at the very end of the move doesnt block it
                        if(c>end.getCol())
                        {
                            // this makes sure we dont check ourselves in the first pass
                            if(c!=start.getCol() && r!=start.getRow())
                            {
                                if(board.isPieceAt(r,c))
                                {
                                    return !(board.isPieceAt(r,c));
                                }
                            }
                        }
                        //this increments the col by subtracting it as that makes it go left from the starting point (run) m=rise/run
                        c=c-1;
                    } 
                   return true;
                }
            }
            // if first statment is false then that means diagonal is going to the right compared to start location
            else
            {
                //checks to see if the diagonal has postive slope
                if(start.getRow()>end.getRow())
                {
                    int c=start.getCol();
                    //this increments the row by subtracting it to make it go up (rise) m=rise/run
                    //it only goes to the second last value from Row since a piece at the very end of the move doesnt block it
                    for (int r=start.getRow();r>end.getRow();r--)
                    {
                        //it only goes to the second last value from Col since a piece at the very end of the move doesnt block it
                        if(c<end.getCol())
                        {
                            // this makes sure we dont check ourselves in the first pass
                            if(c!=start.getCol() && r!=start.getRow())
                            {
                                if(board.isPieceAt(r,c))
                                {
                                    return !(board.isPieceAt(r,c));
                                }
                            }
                            //this increments the col by adding it as that makes it go right from the starting point (run) m=rise/run
                            c=c+1;
                        }
                    }
                    return true;
                }
                //if the first statment is false then that means this diagonal has negative slope
                else
                {
                   int c=start.getCol();
                   //this increments the row by subtracting it as that makes the slope negative (run) m=rise/run
                   //it only goes to the second last value from col since a piece at the very end of the move doesnt block it
                   for (int r=start.getRow();r<end.getRow();r++)
                    {
                        //this increments the row by adding it to make it go down (rise) m=rise/run
                        //it only goes to the second last value from row since a piece at the very end of the move doesnt block it
                        if(c<end.getCol())
                        {
                           // this makes sure we dont check ourselves in the first pass
                           if(c!=start.getCol() && r!=start.getRow())
                           {
                               if(board.isPieceAt(r,c))
                               {
                                   return !(board.isPieceAt(r,c));
                               }
                           }
                        }
                        //this increments the col by adding it as that makes it go right from the starting point (run) m=rise/run
                        c=c+1;
                    }
                   return true;
                }
            }
        }
       
    }
    public abstract void updateThreateningLocation(ChessLocation newLocation);
    /**
     * This method returns the game associated with the piece
     * returns a object of chessBoard
     */
    public ChessBoard getChessBoard()
    {
        return board;
    }
     /**
     * This method returns the owner associated with the piece
     * returns a string value 
     */
    public String getOwner()
    {
        return owner;
    }
     /**
     * This method returns the location associated with the piece
     * returns a object of ChessLocation
     */
    public ChessLocation getLocation()
    {
        return location;
    }
     /**
     * This method returns the piece id associated with the piece
     * returns a char
     */
    public char getId()
    {
        return id;
    }
     /**
     * accepts an object ot ChessLocation 
     * This method sets the piece's location to a new value
     * returns nothing
     */
    public void setNewLocation(ChessLocation location)
    {
        this.location.setRow(location.getRow());
        this.location.setCol(location.getCol());
    }
    /**
     * accepts an array of ChessLocation objects and sets the threaten array to that
     * returns nothing
     */
    public void setThreateningLocations (ArrayList<ChessLocation> newThreat)
    {
        threateningLocations= newThreat;
    }
    /**
     * This method returns an object of ArrayList<ChessLocation>
     * accepts no value
     */
    public ArrayList<ChessLocation> getThreateningLocations()
    {
        return threateningLocations;
    }
}
