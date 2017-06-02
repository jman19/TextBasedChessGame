
/**
 * This class keeps track of the current location for the pieces.
 * Contains the methods getRow, getCol, setRow, setCol
 * @author (Jason Wang) 
 * @version (Nov 6 2016)
 */
public class ChessLocation
{
    // instance variables 
    private int row;
    private int col;

    /**
     * Constructor for objects of class ChessLocation accepts int values for row and
     * int values for col
     */
    public ChessLocation(int row,int col)
    {
        // initialise instance variables
        this.row=row;
        this.col=col;
    }

    /**
     * This method returns row as an int value
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     * This method returns col as an int value
     */
    public int getCol()
    {
        return col;
    }
    
    /**
     * This method sets row to a new value it will check to make sure value is in range
     * returns nothing
     */
    public void setRow(int row)
    {
        if(row>=0 && row<=7)
        {
            this.row=row;
        }
        else
        {
            System.out.println("Invalid Location");
        }
    }
    
    /**
     * This method sets col will check to make sure it is in range
     * returns nothing
     */
    public void setCol(int col)
    {
        if(col>=0 && col<=7)
        {
            this.col=col;
        }
        else
        {
            System.out.println("Out of range");
        }
    }
    /**
     * This method checks to see if 2 objects of ChessLocation have equal values for col and row
     * returns true if they have the same values and false if not
     */
    public boolean equals(ChessLocation cp)
    {
        if(cp.getRow()==row && cp.getCol()==col)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
