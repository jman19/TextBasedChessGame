import java.util.ArrayList;
/**
 * This class creates a 8X8 chess board by using a Array which holds arraylist values
 * to create a 2d array
 * contains the methods placePieceAt, RemovePieceAt, isPieceAt,getPieceAt, printString, return board method
 * @author (Jason Wang) 
 * @version (Nov 6 2016)
 */

public class ChessBoard
{
    // define an array with elemnts of type ArrayList
    private ArrayList[] Board;
    /**
     * Constructor for objects of class ChessBoard
     * Accepts no values it will initialize the 2d array with all null values
     */
    
    public ChessBoard()
    {
        
        // create the 8x8 chess board
        Board=new ArrayList[]{new ArrayList(8),new ArrayList(8),new ArrayList(8),new ArrayList(8),new ArrayList(8),new ArrayList(8),new ArrayList(8),new ArrayList(8)};
        // initiate all values in chess board to null as there is no piece there
        for (int row=0; row<8; row++)
        {
            for (int col=0; col<8; col++)
            {
                Board[col].add(null);
            }
        }
    }
    
    /**
     * this method takes int values for row and col and checks to see if a piece is there, will return false if its not and true
     * if it is.
     */
    public boolean isPieceAt(int row, int col)
    {
        if (Board[row].get(col)==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    /**
     * This method takes a int row and int col and returns the piece at that location if there is nothing there it returns null
     * if there is a piece there it will return the piece
     */
    public Chesspiece getPieceAt(int row, int col)
    {
        if (Board[row].get(col)!=null)
        {
            return (Chesspiece)Board[row].get(col);
        }
        else
        {
            return null;
        }
    }
    /**
     * this method places accepts a object of Chesspiece and an object of Chesslocation 
     * it will then place the Chesspiece object in the 2d array at the location specfied in the Chesslocation
     * object. it will replace whaterver was in that space with the Chesspiece followed by updating the pieces internal location
     * returns nothing
     */
    public void placePieceAt(Chesspiece piece,ChessLocation location)
    {
        // outer element represents row then inner element represents col
        Board[location.getRow()].set(location.getCol(),piece);
        //sets the piece location to new value
        piece.setNewLocation(location);
    }
    
    /**
     * Accepts an object of ChessLocation 
     * this method removes a piece at a given location
     * returns nothing
     */
    public void removePieceAt (ChessLocation location)
    {
        // outer element represents row then inner element represents col 
        Board[location.getRow()].set(location.getCol(),null);
    }
    
    /**
     * this method prints out the board
     * returns nothing
     */
    public void printString()
    {
        // outer loop goes though each row, inner loop moves though each colume in that row
        System.out.println("  0 1 2 3 4 5 6 7 ");
        for (int row=0; row<8; row++)
        {
            System.out.print(row+ " ");
            for (int col=0; col<8; col++)
            {
                // if the value in that location is null print "-" which represents empty spot
                if (Board[row].get(col)==null)
                {
                   
                    System.out.print("-"+" ");
                   
                }
                // if the value is not null there is a piece there so it prints "k" to represent it
                else
                {
                    Chesspiece piece=(Chesspiece)Board[row].get(col);
                    System.out.print(piece.getId()+" ");
                }
            }
            System.out.print("\n");
        }
        System.out.println(" ");
 
    }
    
    /**
     * This method returns Board as a Array containing arrayList types
     */
    public ArrayList[] getBoard()
    {
        // define a new array 
        return Board;
    }
    
    
}
