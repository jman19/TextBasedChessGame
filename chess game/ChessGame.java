
import java.util.InputMismatchException;
/**
 * initialize the chess board and the piece 
 * Contains the methods getboard
 * @author (Jason Wang) 
 * @version (June 1 2017)
 */
public class ChessGame
{
    // instance variables 
    private ChessBoard board;
    private Chesspiece piece;
    private boolean playerOneKingAlive;
    private boolean playerTwoKingAlive;
    private int numTurns;
    private Chesspiece playerOneKing;
    private Chesspiece playerTwoKing;
    /**
     * Constructor for objects of class ChessGame accpets no values
     */
    public ChessGame()
    {
        setupGame();
        numTurns=0;
    }
    public int getnumTurns()
    {
        return numTurns;
    }
    /**
     * this method restarts the game
     */
    public void restart()
    {
        setupGame();
        board.printString();
        numTurns=0;
    }
    /**
     * this method returns playerOneking
     */
    public Chesspiece getPlayerOneKing()
    {
        return playerOneKing;
    }
    /**
     * this method returns playerTwoking
     */
    public Chesspiece getPlayerTwoKing()
    {
        return playerTwoKing;
    }
    /**
     * checks to see if player has lost due to checkmate
     */
    public boolean HasPlayerLost()
    {
        if(numTurns%2==0)
        {
            if(!((King)playerOneKing).check().isEmpty())
            {
                if(isCheckMate())
                {
                    return true;
                }
            }
        }
        else
        {
            //quits game if your move checkmates the oppoent occured
            if(!((King)playerTwoKing).check().isEmpty())
            {
                if(isCheckMate())
                {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * this method plays one turn
     */
    public boolean playturn(String choice,int row,int col,int sourceRow,int sourceCol)
    {
        //plays the game 
        //quits game if checkmate has occured
        if(HasPlayerLost())
        {
            return false;
        }
        
        if (choice.equals("Move"))
        {
            //if (numTurns%2==0)
            //{
                //if(((King)playerOneKing).check()!=null)
                //{
                    //System.out.println("Check "+((King)playerOneKing).check().getOwner()+ " "+((King)playerOneKing).check().getId()
                    //+" At: "+"("+((King)playerOneKing).check().getLocation().getRow()+","+((King)playerOneKing).check().getLocation().getCol()+")");
                //}
            //}
            //else if (numTurns%2!=0)
            //{
                //if(((King)playerTwoKing).check()!=null)
                //{
                    //System.out.println("Check "+((King)playerTwoKing).check().getOwner()+ " "+((King)playerTwoKing).check().getId()
                    //+" At: "+"("+((King)playerTwoKing).check().getLocation().getRow()+","+((King)playerTwoKing).check().getLocation().getCol()+")");
                        
                //}
            //}
            //makes sure the user is choosing location inside the board
            if((sourceRow>=0 && sourceRow<=7) && (sourceCol>=0 && sourceCol<=7))
            {        
                // makes sure that there is actually a piece at that location to move 
                // also checks to make sure destination is in range
                if (board.isPieceAt(sourceRow,sourceCol) && (col>=0 && col<=7) && (row>=0 && row<=7))
                {
                    if(numTurns%2==0 && 
                    board.getPieceAt(sourceRow,sourceCol).getOwner().equals("Player1"))
                    {
                        Chesspiece target=board.getPieceAt(row,col);
                        if (board.getPieceAt(sourceRow,sourceCol).moveTo(new ChessLocation(row,col)))
                        {
                            //makes sure move will not endanger king
                            if(!((King)playerOneKing).check().isEmpty())
                            {
                                if(board.getPieceAt(row,col).getId()=='P')
                                {
                                    ((Pawn)board.getPieceAt(row,col)).resetFirstMove();
                                }
                                board.placePieceAt(board.getPieceAt(row,col),new ChessLocation(sourceRow,sourceCol));
                                board.removePieceAt(new ChessLocation(row,col));
                                //if the target was an enermy piece restore it to board
                                if(target!=null)
                                {
                                    board.placePieceAt(target,new ChessLocation(row,col));
                                }
                                System.out.println("Moving that piece would put the king in check");
                            }
                            else
                            {
                                //increments one turn
                                numTurns+=1;
                            }
                        }
                        
                        
                    }
                    else if (numTurns%2!=0 && 
                    board.getPieceAt(sourceRow,sourceCol).getOwner().equals("Player2"))
                    {
                        Chesspiece target=board.getPieceAt(row,col);
                        if (board.getPieceAt(sourceRow,sourceCol).moveTo(new ChessLocation(row,col)))
                        {
                            //makes sure move will not endanger king
                            if(!((King)playerTwoKing).check().isEmpty())
                            {
                                if(board.getPieceAt(row,col).getId()=='p')
                                {
                                    ((Pawn)board.getPieceAt(row,col)).resetFirstMove();
                                }
                                board.placePieceAt(board.getPieceAt(row,col),new ChessLocation(sourceRow,sourceCol));
                                board.removePieceAt(new ChessLocation(row,col));
                                //if the target was an enermy piece restore it to board
                                if(target!=null)
                                {
                                    board.placePieceAt(target,new ChessLocation(row,col));
                                }
                                System.out.println("Moving that piece would put the king in check");
                            }
                            else
                            {
                                //increments one turn
                                numTurns+=1;
                            }
                        }
                        
                    }
                    else
                    {
                        System.out.println("Not your Turn");
                    }
                }
                // if there is not a piece to move tell user
                else if (!board.isPieceAt(sourceRow,sourceCol))
                {
                    System.out.println("No piece is present in that location");
                }
                else
                {
                    System.out.println("Invalid input");
                }
            }
            // tell the user they are trying to move a piece not on the board
            else
            {
                System.out.println("Invalid input");
            } 
        }
        //playerOneKingAlive=isAlive(board,playerOneKing);
        //playerTwoKingAlive=isAlive(board,playerTwoKing);
        return true;
    }    
    
    /**
     * This method returns the chess board that ChessGame initialized
     * as an object of ChessBoard class
     */
    public ChessBoard getboard()
    {
        return board;
    }
    /**
     * this method creates the game board
     */
    private void setupGame()
    {
        // initialize instance variables
        board= new ChessBoard();
        // initialize the pieces for player 1
        piece=new Knight("Player1",new ChessLocation(0,1),board); 
        piece=new Knight("Player1",new ChessLocation(0,6),board);
        piece=new Queen("Player1",new ChessLocation(0,3),board);
        piece=new Rook("Player1",new ChessLocation(0,0),board);
        piece=new Rook("Player1",new ChessLocation(0,7),board);
        piece=new Bishop("Player1",new ChessLocation(0,2),board);
        piece=new Bishop("Player1",new ChessLocation(0,5),board);
        piece=new King("Player1",new ChessLocation(0,4),board);
        for(int i=0;i<8;i++)
        {
            piece=new Pawn("Player1",new ChessLocation(1,i),board);
        }
        // initialize the pieces for player 2
        piece=new Knight("Player2",new ChessLocation(7,1),board); 
        piece=new Knight("Player2",new ChessLocation(7,6),board);
        piece=new Queen("Player2",new ChessLocation(7,3),board);
        piece=new Rook("Player2",new ChessLocation(7,0),board);
        piece=new Rook("Player2",new ChessLocation(7,7),board);
        piece=new Bishop("Player2",new ChessLocation(7,2),board);
        piece=new Bishop("Player2",new ChessLocation(7,5),board);
        piece=new King("Player2",new ChessLocation(7,4),board);
        for(int i=0;i<8;i++)
        {
            piece=new Pawn("Player2",new ChessLocation(6,i),board);
        }
        playerOneKingAlive=true;
        playerTwoKingAlive=true;
        //gets a reference of the two players kings
        playerOneKing=board.getPieceAt(0,4);
        playerTwoKing=board.getPieceAt(7,4);
    }
    public boolean kingsAlive()
    {
        return playerOneKingAlive && playerTwoKingAlive;
    }
    /**
     * This method checks to see if a cheese piece is alive
     * Will accept an object of ChessBoard and Chesspiece
     * returns true if it was found and false if not
     */
    private static boolean isAlive(ChessBoard board,Chesspiece piece)
    {
        //checks to see if piece is alive
        for (int r=0; r<8; r++)
        {
            for (int c=0; c<8; c++)
            {
                if(board.getPieceAt(r,c)!=null)
                {
                    if(board.getPieceAt(r,c)==piece)
                    {   
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean isCheckMate()
    { 
        boolean checkmate=true;
        for(int r=0; r<8; r++)
        {
            for(int c=0; c<8; c++)
            {
                if(board.getPieceAt(r,c)!=null)
                {
                    //checks for the checkmate of player1
                    if(board.getPieceAt(r,c).getOwner().equals("Player1") && (numTurns%2==0))
                    {
                       
                        //checks for all possible moves to save the king by any pawn pieces left on the board
                        if(board.getPieceAt(r,c).getId()=='P')
                        {
                       
                            if (((Pawn)board.getPieceAt(r,c)).isfirstMove())
                            {
                                
                                if((r+2)<8 && board.getPieceAt(r+2,c)==null && 
                                board.getPieceAt(r,c).checkLineOfSight(new ChessLocation(r,c),new ChessLocation(r+2,c)))
                                {
                                    board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r+2,c));
                                    board.removePieceAt(new ChessLocation(r,c));
                                    if(((King)playerOneKing).check().isEmpty())
                                    {
                                        board.placePieceAt(board.getPieceAt(r+2,c),new ChessLocation(r,c));
                                        board.removePieceAt(new ChessLocation(r+2,c));
                                        return false;
                                        
                                    }
                                    board.placePieceAt(board.getPieceAt(r+2,c),new ChessLocation(r,c));
                                    board.removePieceAt(new ChessLocation(r+2,c));
                                }
                                
                            }
                            if((r+1)<8 && board.getPieceAt(r+1,c)==null)
                            {
                                board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r+1,c));
                                board.removePieceAt(new ChessLocation(r,c));
                                if(((King)playerOneKing).check().isEmpty())
                                {
                                    board.placePieceAt(board.getPieceAt(r+1,c),new ChessLocation(r,c));
                                    board.removePieceAt(new ChessLocation(r+1,c));
                                    return false;
                                    
                                }
                                board.placePieceAt(board.getPieceAt(r+1,c),new ChessLocation(r,c));
                                board.removePieceAt(new ChessLocation(r+1,c));
                            }
                            //checks if pawn can save king by killing enermy piece
                            if((c+1<8) && ((r+1)<8) && board.getPieceAt(r+1,c+1)!=null && board.getPieceAt(r+1,c+1).getOwner().equals("Player2") )
                            {
                                Chesspiece temp=board.getPieceAt(r+1,c+1);
                                board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r+1,c+1));
                                board.removePieceAt(new ChessLocation(r,c));
                                if(((King)playerOneKing).check().isEmpty())
                                {
                                    board.placePieceAt(board.getPieceAt(r+1,c+1),new ChessLocation(r,c));
                                    board.removePieceAt(new ChessLocation(r+1,c+1));
                                    board.placePieceAt(temp,new ChessLocation(r+1,c+1));
                                    return false;
                                    
                                }
                                board.placePieceAt(board.getPieceAt(r+1,c+1),new ChessLocation(r,c));
                                board.removePieceAt(new ChessLocation(r+1,c+1));
                                board.placePieceAt(temp,new ChessLocation(r+1,c+1));
                            }
                            if((c-1)>=0 && (r+1)<8 && board.getPieceAt(r+1,c-1)!=null && board.getPieceAt(r+1,c-1).getOwner().equals("Player2"))
                            {
                                Chesspiece temp=board.getPieceAt(r+1,c-1);
                                board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r+1,c-1));
                                board.removePieceAt(new ChessLocation(r,c));
                                if(((King)playerOneKing).check().isEmpty())
                                {
                                    board.placePieceAt(board.getPieceAt(r+1,c-1),new ChessLocation(r,c));
                                    board.removePieceAt(new ChessLocation(r+1,c-1));
                                    board.placePieceAt(temp,new ChessLocation(r+1,c-1));
                                    return false;
                                    
                                }
                                board.placePieceAt(board.getPieceAt(r+1,c-1),new ChessLocation(r,c));
                                board.removePieceAt(new ChessLocation(r+1,c-1));
                                board.placePieceAt(temp,new ChessLocation(r+1,c-1));
                            }
                        }
                        
                        //checks the possible moves of knights to see if it can save king
                        if(board.getPieceAt(r,c).getId()=='N')
                        {
                            if(!KnightPermutation(r,c,((King)playerOneKing),"Player2"))
                            {
                                return false;
                            }
                        }
                        
                        //this checks for all possible moves of rook to see if it can save king
                        if(board.getPieceAt(r,c).getId()=='R')
                        {
                            if(!rookPermutation(r,c,((King) playerOneKing),"Player2"))
                            {
                                return false;
                            }
                        }
                        //this checks for all possible moves of bishop to see if it can save king
                        if(board.getPieceAt(r,c).getId()=='B')
                        {
                            if(!bishopPermutation(r,c,((King) playerOneKing),"Player2"))
                            {
                                return false;
                            }
                        }
                        //this checks all possible moves by queen to see if it can save king
                        if(board.getPieceAt(r,c).getId()=='Q')
                        {
                            if(!queenPermutation(r,c,((King) playerOneKing),"Player2"))
                            {
                                return false;
                            }
                        }
                        //this checks if king can save itself
                        if(board.getPieceAt(r,c).getId()=='K')
                        {
                            if(!kingPermutation(r,c,((King) playerOneKing),"Player2"))
                            {
                                return false;
                            }
                        }
                    }
                    //checks for checkmate of player2
                    else if(board.getPieceAt(r,c).getOwner().equals("Player2") && (numTurns%2!=0))
                    {
                        if(board.getPieceAt(r,c).getId()=='p')
                        {
                       
                            if (((Pawn)board.getPieceAt(r,c)).isfirstMove())
                            {
                                
                                if((r-2)>=0 && board.getPieceAt(r-2,c)==null && 
                                board.getPieceAt(r,c).checkLineOfSight(new ChessLocation(r,c),new ChessLocation(r-2,c)))
                                {
                                    board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r-2,c));
                                    board.removePieceAt(new ChessLocation(r,c));
                                    if(((King)playerTwoKing).check().isEmpty())
                                    {
                                        board.placePieceAt(board.getPieceAt(r-2,c),new ChessLocation(r,c));
                                        board.removePieceAt(new ChessLocation(r-2,c));
                                        return false;
                                        
                                    }
                                    board.placePieceAt(board.getPieceAt(r-2,c),new ChessLocation(r,c));
                                    board.removePieceAt(new ChessLocation(r-2,c));
                                }
                                
                            }
                            if((r-1)>=0 && board.getPieceAt(r-1,c)==null)
                            {
                                board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r-1,c));
                                board.removePieceAt(new ChessLocation(r,c));
                                if(((King)playerTwoKing).check().isEmpty())
                                {
                                    board.placePieceAt(board.getPieceAt(r-1,c),new ChessLocation(r,c));
                                    board.removePieceAt(new ChessLocation(r-1,c));
                                    return false;
                                    
                                }
                                board.placePieceAt(board.getPieceAt(r-1,c),new ChessLocation(r,c));
                                board.removePieceAt(new ChessLocation(r-1,c));
                            }
                            //checks if pawn can save king by killing enermy piece
                            if((c-1)>=0 && ((r-1)>=0) && board.getPieceAt(r-1,c-1)!=null && board.getPieceAt(r-1,c-1).getOwner().equals("Player1") )
                            {
                                Chesspiece temp=board.getPieceAt(r-1,c-1);
                                board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r-1,c-1));
                                board.removePieceAt(new ChessLocation(r,c));
                                if(((King)playerTwoKing).check().isEmpty())
                                {
                                    board.placePieceAt(board.getPieceAt(r-1,c-1),new ChessLocation(r,c));
                                    board.removePieceAt(new ChessLocation(r-1,c-1));
                                    board.placePieceAt(temp,new ChessLocation(r-1,c-1));
                                    return false;
                                    
                                }
                                board.placePieceAt(board.getPieceAt(r-1,c-1),new ChessLocation(r,c));
                                board.removePieceAt(new ChessLocation(r-1,c-1));
                                board.placePieceAt(temp,new ChessLocation(r-1,c-1));
                            }
                            if((c+1)<8 && (r-1)>=0 && board.getPieceAt(r-1,c+1)!=null && board.getPieceAt(r-1,c+1).getOwner().equals("Player1"))
                            {
                                Chesspiece temp=board.getPieceAt(r-1,c+1);
                                board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r-1,c+1));
                                board.removePieceAt(new ChessLocation(r,c));
                                if(((King)playerTwoKing).check().isEmpty())
                                {
                                    board.placePieceAt(board.getPieceAt(r-1,c+1),new ChessLocation(r,c));
                                    board.removePieceAt(new ChessLocation(r-1,c+1));
                                    board.placePieceAt(temp,new ChessLocation(r-1,c+1));
                                    return false;
                                    
                                }
                                board.placePieceAt(board.getPieceAt(r-1,c+1),new ChessLocation(r,c));
                                board.removePieceAt(new ChessLocation(r-1,c+1));
                                board.placePieceAt(temp,new ChessLocation(r-1,c+1));
                            }
                        }
                        //checks too see if knight can save king
                        if(board.getPieceAt(r,c).getId()=='n')
                        {
                            if(!KnightPermutation(r,c,((King)playerTwoKing),"Player1"))
                            {
                                return false;
                            }
                        }
                        //this checks for all possible moves of rook to see if it can save king
                        if(board.getPieceAt(r,c).getId()=='r')
                        {
                            if(!rookPermutation(r,c,((King) playerTwoKing),"Player1"))
                            {
                                return false;
                            }
                        }
                        //this checks for all possible moves of bishop to see if it can save king
                        if(board.getPieceAt(r,c).getId()=='b')
                        {
                            if(!bishopPermutation(r,c,((King) playerTwoKing),"Player1"))
                            {
                                //return false;
                            }
                        }
                        //this checks all possible moves by queen to see if it can save king
                        if(board.getPieceAt(r,c).getId()=='q')
                        {
                            if(!queenPermutation(r,c,((King) playerTwoKing),"Player1"))
                            {
                                return false;
                            }
                        }
                        //this checks if king can save itself
                        if(board.getPieceAt(r,c).getId()=='k')
                        {
                            if(!kingPermutation(r,c,((King) playerTwoKing),"Player1"))
                            {
                                return false;
                            }
                        }
                    }  
                }
            }
        }
        return checkmate;
    }
    
    /**
     * this is a helper function for checkmate it will return false if there is a way for knight to save king, true if it cannot
     */
    private boolean KnightPermutation(int r,int c,King playerKing,String enermy)
    {
        //checks all moves in upper part vertical
        if((r-2)>=0 && (c-1)>=0 && (board.getPieceAt(r-2,c-1)==null || board.getPieceAt(r-2,c-1).getOwner().equals(enermy)))
        {
            boolean attack=false;
            Chesspiece temp=board.getPieceAt(r-2,c-1);
            if(board.getPieceAt(r-2,c-1)!=null )
            {
                attack=true;
            }
            board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r-2,c-1));
            board.removePieceAt(new ChessLocation(r,c));
            if(playerKing.check().isEmpty())
            {
                
                board.placePieceAt(board.getPieceAt(r-2,c-1),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(r-2,c-1));
                if(attack)
                {
                    board.placePieceAt(temp,new ChessLocation(r-2,c-1));
                }
                return false;
            }
            board.placePieceAt(board.getPieceAt(r-2,c-1),new ChessLocation(r,c));
            board.removePieceAt(new ChessLocation(r-2,c-1));
            if(attack)
            {
                board.placePieceAt(temp,new ChessLocation(r-2,c-1));
            }
        }
        
        if ((r-2)>=0 && (c+1)<8 && (board.getPieceAt(r-2,c+1)==null || board.getPieceAt(r-2,c+1).getOwner().equals(enermy)))
        {
            boolean attack=false;
            Chesspiece temp=board.getPieceAt(r-2,c+1);
            if (board.getPieceAt(r-2,c+1)!=null)
            {
               
                attack=true;
            }
            board.placePieceAt(board.getPieceAt(r,c), new ChessLocation(r-2,c+1));
            board.removePieceAt(new ChessLocation(r,c));
            if(playerKing.check().isEmpty())
            {
                board.placePieceAt(board.getPieceAt(r-2,c+1),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(r-2,c+1));
                if(attack)
                {
                    board.placePieceAt(temp,new ChessLocation(r-2,c+1));
                }
                return false;
            }
            board.placePieceAt(board.getPieceAt(r-2,c+1),new ChessLocation(r,c));
            board.removePieceAt(new ChessLocation(r-2,c+1));
            if(attack)
            {
                board.placePieceAt(temp,new ChessLocation(r-2,c+1));
            }
        }
        
        //checks the upper horizontal
        if((r-1)>=0 && (c-2)>=0 && (board.getPieceAt(r-1,c-2)==null || board.getPieceAt(r-1,c-2).getOwner().equals(enermy)))
        {
            boolean attack=false;
            Chesspiece temp=board.getPieceAt(r-1,c-2);
            if(board.getPieceAt(r-1,c-2)!=null)
            {
               
                attack=true;
            }
            board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r-1,c-2));
            board.removePieceAt(new ChessLocation(r,c));
            if(playerKing.check().isEmpty())
            {
                
                board.placePieceAt(board.getPieceAt(r-1,c-2),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(r-1,c-2));
                if(attack)
                {
                    board.placePieceAt(temp,new ChessLocation(r-1,c-2));
                }
                return false;
            }
            board.placePieceAt(board.getPieceAt(r-1,c-2),new ChessLocation(r,c));
            board.removePieceAt(new ChessLocation(r-1,c-2));
            if(attack)
            {
                board.placePieceAt(temp,new ChessLocation(r-1,c-2));
            }
        }
        
        
        if((r-1)>=0 && (c+2)<8 && (board.getPieceAt(r-1,c+2)==null || board.getPieceAt(r-1,c+2).getOwner().equals(enermy)))
        {
            boolean attack=false;
            Chesspiece temp=board.getPieceAt(r-1,c+2);
            if(board.getPieceAt(r-1,c+2)!=null)
            {
                
                attack=true;
            }
            board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r-1,c+2));
            board.removePieceAt(new ChessLocation(r,c));
            if(playerKing.check().isEmpty())
            {
                board.placePieceAt(board.getPieceAt(r-1,c+2),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(r-1,c+2));
                if(attack)
                {
                    board.placePieceAt(temp,new ChessLocation(r-1,c+2));
                }
                return false;
            }
            board.placePieceAt(board.getPieceAt(r-1,c+2),new ChessLocation(r,c));
            board.removePieceAt(new ChessLocation(r-1,c+2));
            if(attack)
            {
                board.placePieceAt(temp,new ChessLocation(r-1,c+2));
            }
        }
        
        //checks lowerparts vertical
        if((r+2)<8 && (c-1)>=0 && (board.getPieceAt(r+2,c-1)==null || board.getPieceAt(r+2,c-1).getOwner().equals(enermy)))
        {
            boolean attack=false;
            Chesspiece temp=board.getPieceAt(r+2,c-1);
            if(board.getPieceAt(r+2,c-1)!=null)
            {
                
                attack=true;
            }
            board.placePieceAt(board.getPieceAt(r,c), new ChessLocation(r+2,c-1));
            board.removePieceAt(new ChessLocation(r,c));
            if(playerKing.check().isEmpty())
            {
                board.placePieceAt(board.getPieceAt(r+2,c-1),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(r+2,c-1));
                if (attack)
                {
                    board.placePieceAt(temp,new ChessLocation(r+2,c-1));
                }
                return false;
            }
            board.placePieceAt(board.getPieceAt(r+2,c-1),new ChessLocation(r,c));
            board.removePieceAt(new ChessLocation(r+2,c-1));
            if (attack)
            {
                board.placePieceAt(temp,new ChessLocation(r+2,c-1));
            }
        }
        
        
        if((r+2)<8 && (c+1)<8 && (board.getPieceAt(r+2,c+1)==null || board.getPieceAt(r+2,c+1).getOwner().equals(enermy)))
        {
            boolean attack=false;
            Chesspiece temp=board.getPieceAt(r+2,c+1);
            if(board.getPieceAt(r+2,c+1)!=null)
            {
                
                attack=true;
            }
            board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r+2,c+1));
            board.removePieceAt(new ChessLocation(r,c));
            if(playerKing.check().isEmpty())
            {
                board.placePieceAt(board.getPieceAt(r+2,c+1),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(r+2,c+1));
                if(attack)
                {
                    board.placePieceAt(temp,new ChessLocation(r+2,c+1));
                }
                return false;
            }
            board.placePieceAt(board.getPieceAt(r+2,c+1),new ChessLocation(r,c));
            board.removePieceAt(new ChessLocation(r+2,c+1));
            if(attack)
            {
                board.placePieceAt(temp,new ChessLocation(r+2,c+1));
            }
        }
        
        //checks lower horizontal
        
        if((r+1)<8 && (c-2)>=0 && (board.getPieceAt(r+1,c-2)==null || board.getPieceAt(r+1,c-2).getOwner().equals(enermy)))
        {
            boolean attack=false;
            Chesspiece temp=board.getPieceAt(r+1,c-2);
            if(board.getPieceAt(r+1,c-2)!=null)
            {
                
                attack=true;
            }
            board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r+1,c-2));
            board.removePieceAt(new ChessLocation(r,c));
            if(playerKing.check().isEmpty())
            {
                board.placePieceAt(board.getPieceAt(r+1,c-2),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(r+1,c-2));
                if(attack)
                {
                    board.placePieceAt(temp,new ChessLocation(r+1,c-2));
                }
                return false;
            }
            board.placePieceAt(board.getPieceAt(r+1,c-2),new ChessLocation(r,c));
            board.removePieceAt(new ChessLocation(r+1,c-2));
            if(attack)
            {
                board.placePieceAt(temp,new ChessLocation(r+1,c-2));
            }
        }
        
        if((r+1)<8 && (c+2)<8 && (board.getPieceAt(r+1,c+2)==null || board.getPieceAt(r+1,c+2).getOwner().equals(enermy)))
        {
            boolean attack=false;
            Chesspiece  temp=board.getPieceAt(r+1,c+2);
            if(board.getPieceAt(r+1,c+2)!=null)
            {
               
                attack=true;
            }
            board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r+1,c+2));
            board.removePieceAt(new ChessLocation(r,c));
            if(playerKing.check().isEmpty())
            {
                board.placePieceAt(board.getPieceAt(r+1,c+2),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(r+1,c+2));
                if (attack)
                {
                    board.placePieceAt(temp,new ChessLocation(r+1,c+2));
                }
                return false;
            }
            board.placePieceAt(board.getPieceAt(r+1,c+2),new ChessLocation(r,c));
            board.removePieceAt(new ChessLocation(r+1,c+2));
            if (attack)
            {
                board.placePieceAt(temp,new ChessLocation(r+1,c+2));
            }
        }
        return true;
    }
    
    /** 
     * this is a helper function for checkmate it will return false if there is a way for rook to save king, true if it cannot
     */
    private boolean rookPermutation(int r,int c,King playerKing,String enermy)
    {
        //checks downwards moves to see if it can save king
        for (int row=r;row<8;row++)
        {
            boolean attack=false;
            Chesspiece  temp=board.getPieceAt(r,c);
            if(row!=r && (board.getPieceAt(row,c)==null || board.getPieceAt(row,c).getOwner().equals(enermy)) && 
            board.getPieceAt(r,c).checkLineOfSight(new ChessLocation(r,c),new ChessLocation(row,c)))
            {
                if(board.getPieceAt(row,c)!=null)
                {
                    temp=board.getPieceAt(row,c);
                    attack=true;
                }
                board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(row,c));
                board.removePieceAt(new ChessLocation(r,c));
                if(playerKing.check().isEmpty())
                {
                    board.placePieceAt(board.getPieceAt(row,c),new ChessLocation(r,c));
                    board.removePieceAt(new ChessLocation(row,c));
                    if(attack)
                    {
                        board.placePieceAt(temp,new ChessLocation(row,c));
                    }
                    return false;
                }
                board.placePieceAt(board.getPieceAt(row,c),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(row,c));
                if(attack)
                {
                    board.placePieceAt(temp,new ChessLocation(row,c));
                    break;
                }
            }
        }
        //checks the upwards moves to see if it can save king
        for (int row=r;row>=0;row--)
        {
            
            boolean attack=false;
            Chesspiece  temp=board.getPieceAt(r,c);
            if(row!=r && (board.getPieceAt(row,c)==null || board.getPieceAt(row,c).getOwner().equals(enermy)) &&
            board.getPieceAt(r,c).checkLineOfSight(new ChessLocation(r,c),new ChessLocation(row,c)))
            {
                if(board.getPieceAt(row,c)!=null)
                {
                    temp=board.getPieceAt(row,c);
                    attack=true;
                }
                board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(row,c));
                board.removePieceAt(new ChessLocation(r,c));
                if(playerKing.check().isEmpty())
                {
                    board.placePieceAt(board.getPieceAt(row,c),new ChessLocation(r,c));
                    board.removePieceAt(new ChessLocation(row,c));
                    if(attack)
                    {
                        board.placePieceAt(temp,new ChessLocation(row,c));
                    }
                    return false;
                }
                board.placePieceAt(board.getPieceAt(row,c),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(row,c));
                if(attack)
                {
                    board.placePieceAt(temp,new ChessLocation(row,c));
                    break;
                }
            }
        }
        //checks the left to see if it can save king
        for (int col=c;col>=0;col--)
        {
      
            boolean attack=false;
            Chesspiece  temp=board.getPieceAt(r,c);
            if(col!=c && (board.getPieceAt(r,col)==null || board.getPieceAt(r,col).getOwner().equals(enermy)) &&
            board.getPieceAt(r,c).checkLineOfSight(new ChessLocation(r,c),new ChessLocation(r,col)))
            {
                if(board.getPieceAt(r,col)!=null)
                {
                    temp=board.getPieceAt(r,col);
                    attack=true;
                }
                board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r,col));
                board.removePieceAt(new ChessLocation(r,c));
                if(playerKing.check().isEmpty())
                {
                    board.placePieceAt(board.getPieceAt(r,col),new ChessLocation(r,c));
                    board.removePieceAt(new ChessLocation(r,col));
                    if(attack)
                    {
                        board.placePieceAt(temp,new ChessLocation(r,col));
                    }
                    return false;
                }
                board.placePieceAt(board.getPieceAt(r,col),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(r,col));
                if(attack)
                {
                    board.placePieceAt(temp,new ChessLocation(r,col));
                    break;
                }
            }
        }
        //checks the right to see if it can save king
        for (int col=c;col<8;col++)
        {
      
            boolean attack=false;
            Chesspiece  temp=board.getPieceAt(r,c);
            if(col!=c && (board.getPieceAt(r,col)==null || board.getPieceAt(r,col).getOwner().equals(enermy)) &&
            board.getPieceAt(r,c).checkLineOfSight(new ChessLocation(r,c),new ChessLocation(r,col)))
            {
                if(board.getPieceAt(r,col)!=null)
                {
                    temp=board.getPieceAt(r,col);
                    attack=true;
                }
                board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r,col));
                board.removePieceAt(new ChessLocation(r,c));
                if(playerKing.check().isEmpty())
                {
                    board.placePieceAt(board.getPieceAt(r,col),new ChessLocation(r,c));
                    board.removePieceAt(new ChessLocation(r,col));
                    if(attack)
                    {
                        board.placePieceAt(temp,new ChessLocation(r,col));
                    }
                    return false;
                }
                board.placePieceAt(board.getPieceAt(r,col),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(r,col));
                if(attack)
                {
                    board.placePieceAt(temp,new ChessLocation(r,col));
                    break;
                }
            }
        }
        return true;
    }
    /**
     * helper function for checkmate generates permutations to see if bishop can save king
     */
    private boolean bishopPermutation(int r,int c,King playerKing,String enermy)
    {
        //checks upper right diagonal
        int col=c;
        for(int row=r;(row>=0 && col<8);row--)
        {
            boolean attack=false;
            Chesspiece  temp=board.getPieceAt(r,c);
            if(row!=r && col!=c && (board.getPieceAt(row,col)==null || board.getPieceAt(row,col).getOwner().equals(enermy)) &&
            board.getPieceAt(r,c).checkLineOfSight(new ChessLocation(r,c),new ChessLocation(row,col)))
            {
                if(board.getPieceAt(row,col)!=null)
                {
                    temp=board.getPieceAt(row,col);
                    attack=true;
                }
                board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(row,col));
                board.removePieceAt(new ChessLocation(r,c));
                if(playerKing.check().isEmpty())
                {
                    board.placePieceAt(board.getPieceAt(row,col),new ChessLocation(r,c));
                    board.removePieceAt(new ChessLocation(row,col));
                    if(attack)
                    {
                        board.placePieceAt(temp,new ChessLocation(row,col));
                    }
                    return false;
                }
                board.placePieceAt(board.getPieceAt(row,col),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(row,col));
                if(attack)
                {
                    board.placePieceAt(temp,new ChessLocation(row,col));
                    break;
                }
                
                
            }
            col++;
        }
        //checks the upper left diagonal
        col=c;
        for(int row=r;(row>=0 && col>=0);row--)
        {
            boolean attack=false;
            Chesspiece  temp=board.getPieceAt(r,c);
            if(row!=r && (board.getPieceAt(row,col)==null || board.getPieceAt(row,col).getOwner().equals(enermy)) &&
            board.getPieceAt(r,c).checkLineOfSight(new ChessLocation(r,c),new ChessLocation(row,col)))
            {
                if(board.getPieceAt(row,col)!=null)
                {
                    temp=board.getPieceAt(row,col);
                    attack=true;
                }
                board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(row,col));
                board.removePieceAt(new ChessLocation(r,c));
                if(playerKing.check().isEmpty())
                {
                    board.placePieceAt(board.getPieceAt(row,col),new ChessLocation(r,c));
                    board.removePieceAt(new ChessLocation(row,col));
                    if(attack)
                    {
                        board.placePieceAt(temp,new ChessLocation(row,col));
                    }
                    return false;
                }
                board.placePieceAt(board.getPieceAt(row,col),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(row,col));
                if(attack)
                {
                    board.placePieceAt(temp,new ChessLocation(row,col));
                    break;
                }
                
            }
            col--;
        }
        //checks the lower right diagonal
        col=c;
        for(int row=r;(row<8 && col<8);row++)
        {
            boolean attack=false;
            Chesspiece  temp=board.getPieceAt(r,c);
            if(row!=r && (board.getPieceAt(row,col)==null || board.getPieceAt(row,col).getOwner().equals(enermy)) &&
            board.getPieceAt(r,c).checkLineOfSight(new ChessLocation(r,c),new ChessLocation(row,col)))
            {
                if(board.getPieceAt(row,col)!=null)
                {
                    temp=board.getPieceAt(row,col);
                    attack=true;
                }
                board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(row,col));
                board.removePieceAt(new ChessLocation(r,c));
                if(playerKing.check().isEmpty())
                {
                    board.placePieceAt(board.getPieceAt(row,col),new ChessLocation(r,c));
                    board.removePieceAt(new ChessLocation(row,col));
                    if(attack)
                    {
                        board.placePieceAt(temp,new ChessLocation(row,col));
                    }
                    return false;
                }
                board.placePieceAt(board.getPieceAt(row,col),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(row,col));
                if(attack)
                {
                    board.placePieceAt(temp,new ChessLocation(row,col));
                    break;
                }
                
            }
            col++;
        }
        //checks the lower left diagonal
        col=c;
        for(int row=r;(row<8 && col>=0);row++)
        {
            boolean attack=false;
            Chesspiece  temp=board.getPieceAt(r,c);
            if(col>=0 && col!=c && row!=r && (board.getPieceAt(row,col)==null || board.getPieceAt(row,col).getOwner().equals(enermy)) &&
            board.getPieceAt(r,c).checkLineOfSight(new ChessLocation(r,c),new ChessLocation(row,col)))
            {
                if(board.getPieceAt(row,col)!=null)
                {
                    temp=board.getPieceAt(row,col);
                    attack=true;
                }
                board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(row,col));
                board.removePieceAt(new ChessLocation(r,c));
                if(playerKing.check().isEmpty())
                {
                    board.placePieceAt(board.getPieceAt(row,col),new ChessLocation(r,c));
                    board.removePieceAt(new ChessLocation(row,col));
                    if(attack)
                    {
                        board.placePieceAt(temp,new ChessLocation(row,col));
                    }
                    return false;
                }
                board.placePieceAt(board.getPieceAt(row,col),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(row,col));
                if(attack)
                {
                    board.placePieceAt(temp,new ChessLocation(row,col));
                    break;
                }
                
            }
            col--;
        }
        return true;
    }
    /**
     * helper function for checkmate checks to see if the queen can save the king
     * 
     */
    private boolean queenPermutation(int r,int c,King playerKing,String enermy)
    {
        if(!bishopPermutation(r,c,playerKing,enermy) || !rookPermutation(r,c,playerKing,enermy))
        {
            return false;
        }
        return true;
    }
    /**
     * helper function for checkmate checks to see if king can save itself
     */
    private boolean kingPermutation(int r,int c,King playerKing, String enermy)
    {
        //checks the lower diagonal and lower vertical 
        if(r+1<=7)
        {
            if(c+1<=7)
            {
                boolean attack=false;
                Chesspiece temp=board.getPieceAt(r+1,c+1);
                if((board.getPieceAt(r+1,c+1)==null || board.getPieceAt(r+1,c+1).getOwner().equals(enermy)))
                {
                    if(board.getPieceAt(r+1,c+1)!=null)
                    {
                        attack=true;
                    }
                    board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r+1,c+1));
                    board.removePieceAt(new ChessLocation(r,c));
                    if(playerKing.check().isEmpty())
                    {
                        board.placePieceAt(board.getPieceAt(r+1,c+1),new ChessLocation(r,c));
                        board.removePieceAt(new ChessLocation(r+1,c+1));
                        if(attack)
                        {
                            board.placePieceAt(temp,new ChessLocation(r+1,c+1));
                        }
                        return false;
                    }
                    board.placePieceAt(board.getPieceAt(r+1,c+1),new ChessLocation(r,c));
                    board.removePieceAt(new ChessLocation(r+1,c+1));
                    if(attack)
                    {
                        board.placePieceAt(temp,new ChessLocation(r+1,c+1));
                    }
                }
            }
            if((c-1)>=0)
            {
                boolean attack=false;
                Chesspiece temp=board.getPieceAt(r+1,c-1);
                if((board.getPieceAt(r+1,c-1)==null || board.getPieceAt(r+1,c-1).getOwner().equals(enermy)))
                {
                    if(board.getPieceAt(r+1,c-1)!=null)
                    {
                        attack=true;
                    }
                    board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r+1,c-1));
                    board.removePieceAt(new ChessLocation(r,c));
                    if(playerKing.check().isEmpty())
                    {
                        board.placePieceAt(board.getPieceAt(r+1,c-1),new ChessLocation(r,c));
                        board.removePieceAt(new ChessLocation(r+1,c-1));
                        if(attack)
                        {
                            board.placePieceAt(temp,new ChessLocation(r+1,c-1));
                        }
                        return false;
                    }
                    board.placePieceAt(board.getPieceAt(r+1,c-1),new ChessLocation(r,c));
                    board.removePieceAt(new ChessLocation(r+1,c-1));
                    if(attack)
                    {
                        board.placePieceAt(temp,new ChessLocation(r+1,c-1));
                    }
                }
              
            }
            boolean attack=false;
            Chesspiece temp=board.getPieceAt(r+1,c);
            if((board.getPieceAt(r+1,c)==null || board.getPieceAt(r+1,c).getOwner().equals(enermy)))
            {
                if(board.getPieceAt(r+1,c)!=null)
                {
                    attack=true;
                }
                board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r+1,c));
                board.removePieceAt(new ChessLocation(r,c));
                if(playerKing.check().isEmpty())
                {
                    board.placePieceAt(board.getPieceAt(r+1,c),new ChessLocation(r,c));
                    board.removePieceAt(new ChessLocation(r+1,c));
                    if(attack)
                    {
                        board.placePieceAt(temp,new ChessLocation(r+1,c));
                    }
                    return false;
                }
                board.placePieceAt(board.getPieceAt(r+1,c),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(r+1,c));
                if(attack)
                {
                    board.placePieceAt(temp,new ChessLocation(r+1,c));
                }
            }
        }
        //checks horizontal
        if((c+1)<=7)
        {
            boolean attack=false;
            Chesspiece temp=board.getPieceAt(r,c+1);
            if((board.getPieceAt(r,c+1)==null || board.getPieceAt(r,c+1).getOwner().equals(enermy)))
            {
                if(board.getPieceAt(r,c+1)!=null)
                {
                    attack=true;
                }
                board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r,c+1));
                board.removePieceAt(new ChessLocation(r,c));
                if(playerKing.check().isEmpty())
                {
                    board.placePieceAt(board.getPieceAt(r,c+1),new ChessLocation(r,c));
                    board.removePieceAt(new ChessLocation(r,c+1));
                    if(attack)
                    {
                        board.placePieceAt(temp,new ChessLocation(r,c+1));
                    }
                    return false;
                }
                board.placePieceAt(board.getPieceAt(r,c+1),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(r,c+1));
                if(attack)
                {
                    board.placePieceAt(temp,new ChessLocation(r,c+1));
                }
            }
        }
        if((c-1)>=0)
        {
            boolean attack=false;
            Chesspiece temp=board.getPieceAt(r,c-1);
            if((board.getPieceAt(r,c-1)==null || board.getPieceAt(r,c-1).getOwner().equals(enermy)))
            {
                if(board.getPieceAt(r,c-1)!=null)
                {
                    attack=true;
                }
                board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r,c-1));
                board.removePieceAt(new ChessLocation(r,c));
                if(playerKing.check().isEmpty())
                {
                    board.placePieceAt(board.getPieceAt(r,c-1),new ChessLocation(r,c));
                    board.removePieceAt(new ChessLocation(r,c-1));
                    if(attack)
                    {
                        board.placePieceAt(temp,new ChessLocation(r,c-1));
                    }
                    return false;
                }
                board.placePieceAt(board.getPieceAt(r,c-1),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(r,c-1));
                if(attack)
                {
                    board.placePieceAt(temp,new ChessLocation(r,c-1));
                }
            }
        }
        //Adds the threats from the upper diagonal and upper vertical
        if((r-1)>=0)
        {
            if((c+1)<=7)
            {
                boolean attack=false;
                Chesspiece temp=board.getPieceAt(r-1,c+1);
                if((board.getPieceAt(r-1,c+1)==null || board.getPieceAt(r-1,c+1).getOwner().equals(enermy)))
                {
                    if(board.getPieceAt(r-1,c+1)!=null)
                    {
                        attack=true;
                    }
                    board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r-1,c+1));
                    board.removePieceAt(new ChessLocation(r,c));
                    if(playerKing.check().isEmpty())
                    {
                        board.placePieceAt(board.getPieceAt(r-1,c+1),new ChessLocation(r,c));
                        board.removePieceAt(new ChessLocation(r-1,c+1));
                        if(attack)
                        {
                            board.placePieceAt(temp,new ChessLocation(r-1,c+1));
                        }
                        return false;
                    }
                    board.placePieceAt(board.getPieceAt(r-1,c+1),new ChessLocation(r,c));
                    board.removePieceAt(new ChessLocation(r-1,c+1));
                    if(attack)
                    {
                        board.placePieceAt(temp,new ChessLocation(r-1,c+1));
                    }
                }
            }
            if((c-1)>=0)
            {
                boolean attack=false;
                Chesspiece temp=board.getPieceAt(r-1,c-1);
                if((board.getPieceAt(r-1,c-1)==null || board.getPieceAt(r-1,c-1).getOwner().equals(enermy)))
                {
                    if(board.getPieceAt(r-1,c-1)!=null)
                    {
                        attack=true;
                    }
                    board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r-1,c-1));
                    board.removePieceAt(new ChessLocation(r,c));
                    if(playerKing.check().isEmpty())
                    {
                        board.placePieceAt(board.getPieceAt(r-1,c-1),new ChessLocation(r,c));
                        board.removePieceAt(new ChessLocation(r-1,c-1));
                        if(attack)
                        {
                            board.placePieceAt(temp,new ChessLocation(r-1,c-1));
                        }
                        return false;
                    }
                    board.placePieceAt(board.getPieceAt(r-1,c-1),new ChessLocation(r,c));
                    board.removePieceAt(new ChessLocation(r-1,c-1));
                    if(attack)
                    {
                        board.placePieceAt(temp,new ChessLocation(r-1,c-1));
                    }
                }
            }
            boolean attack=false;
            Chesspiece temp=board.getPieceAt(r-1,c);
            if((board.getPieceAt(r-1,c)==null || board.getPieceAt(r-1,c).getOwner().equals(enermy)))
            {
                if(board.getPieceAt(r-1,c)!=null)
                {
                    attack=true;
                }
                board.placePieceAt(board.getPieceAt(r,c),new ChessLocation(r-1,c));
                board.removePieceAt(new ChessLocation(r,c));
                if(playerKing.check().isEmpty())
                {
                    board.placePieceAt(board.getPieceAt(r-1,c),new ChessLocation(r,c));
                    board.removePieceAt(new ChessLocation(r-1,c));
                    if(attack)
                    {
                        board.placePieceAt(temp,new ChessLocation(r-1,c));
                    }
                    return false;
                }
                board.placePieceAt(board.getPieceAt(r-1,c),new ChessLocation(r,c));
                board.removePieceAt(new ChessLocation(r-1,c));
                if(attack)
                {
                    board.placePieceAt(temp,new ChessLocation(r-1,c));
                }
            }
        }
        return true;
    }
    /**
     * this is helper for playgame it will check to see if moving another piece will put the king into check the next round
     * returns false for not endageringking and true for endangering king
     */
    private boolean moveEndangerKing(int row,int col,int sourceRow,int sourceCol, String enermy,King playerking)
    {
         
         if(board.getPieceAt(row,col)==null || board.getPieceAt(row,col).getOwner().equals(enermy))
         {
             Chesspiece temp=board.getPieceAt(row,col);
             board.placePieceAt(board.getPieceAt(sourceRow,sourceCol),new ChessLocation(row,col));
             board.removePieceAt(new ChessLocation(sourceRow,sourceCol));
             if (playerking.check().isEmpty())
            {
                board.placePieceAt(board.getPieceAt(row,col),new ChessLocation(sourceRow,sourceCol));
                board.removePieceAt(new ChessLocation(row,col));
                if(temp!=null)
                {
                    board.placePieceAt(temp,new ChessLocation(row,col));
                }
                return false;
            }
             board.placePieceAt(board.getPieceAt(row,col),new ChessLocation(sourceRow,sourceCol));
             board.removePieceAt(new ChessLocation(row,col));
             if(temp!=null)
             {
                 board.placePieceAt(temp,new ChessLocation(row,col));
             }
             
         }
         return true;
    }
}
