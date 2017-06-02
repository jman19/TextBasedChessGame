import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Main program which runs the game initializes a ChessGame
 * Displays the chess board with pieces allows the user to pick which piece they would like to move and
 * then allows them to move it to the specified location as long as it is legal
 * Contains the method: isAlive
 * @author (Jason Wang) 
 * @version (june 1 2017)
 */

public class PlayGame
{
    public static void main(String[] args)
    {
        ChessGame game=new ChessGame();
        String choice=" ";
        int row,sourceRow;
        int col,sourceCol;
        Chesspiece playerOneKing=game.getPlayerOneKing();
        Chesspiece playerTwoKing=game.getPlayerTwoKing();
        //this sets in to scanner object
        Scanner in=new Scanner(System.in);
        //this tells the user the program discription
        System.out.println("This program allows you Play a game of Chess");
        //displays chess board
        game.getboard().printString();
        //this while loop keeps going until it recives a quit or restart
        while(!choice.equals("Quit"))
        {
            if(game.HasPlayerLost())
            {
                break;
            }
            // checks which players turn it is
            if(game.getnumTurns()%2==0)
            {
                System.out.println("Player1's Turn");
                if(!((King)playerOneKing).check().isEmpty())
                {
                    for (Chesspiece threat : ((King)playerOneKing).check())
                    {
                        System.out.println("Check "+threat.getOwner()+ " "+threat.getId()
                        +" At: "+"("+threat.getLocation().getRow()+","+threat.getLocation().getCol()+")");
                    }
                }
            }
            else
            {
                System.out.println("Player2's Turn");
                if(!((King)playerTwoKing).check().isEmpty())
                {
                    for(Chesspiece threat : ((King)playerTwoKing).check())
                    {
                        System.out.println("Check "+threat.getOwner()+ " "+threat.getId()
                        +" At: "+"("+threat.getLocation().getRow()+","+threat.getLocation().getCol()+")");
                    }
                }
            }
            //tells playerOne to move thier king
            if(!((King)playerOneKing).check().isEmpty() && game.getnumTurns()%2==0)
            {
                
                System.out.println("You Must save your King");
            }
            //tells playerTwo to move thier King
            if(!((King)playerTwoKing).check().isEmpty() && game.getnumTurns()%2!=0)
            {
                System.out.println("You Must save your King");
            }
            System.out.print("Would you like to Move,Quit or Restart: ");
            choice=in.nextLine();
            System.out.print("\n");
            if(choice.equals("Quit"))
            {
                break;
            }
            if(choice.equals("Restart"))
            {
                game.restart();
            }
            if(choice.equals("Move"))
            {
                //ask the user for source row value
                System.out.println("Enter the row and col of the piece you wish to move below");
                System.out.print("Enter source row : ");
                try
                {
                    sourceRow=in.nextInt();
                    in.nextLine();
                }
                catch(InputMismatchException e)
                {
                    sourceRow=-1;
                    in.nextLine();
                }
                //ask the user for source col value
                System.out.print("Enter source col: ");
                try
                {
                    sourceCol=in.nextInt();
                    in.nextLine();
                }
                catch(InputMismatchException e)
                {
                    sourceCol=-1;
                    in.nextLine();
                }
                System.out.println(" ");
                System.out.println("Enter the row and col for destination below");
                //ask the user for destination row value
                System.out.print("Enter destination row : ");
                try
                {
                    row=in.nextInt();
                    in.nextLine();
                }
                catch(InputMismatchException e)
                {
                    row=-1;
                    in.nextLine();
                }
                //ask the user for destination col value
                System.out.print("Enter destination col: ");
                try
                {
                    col=in.nextInt();
                    in.nextLine();
                }
                catch(InputMismatchException e)
                {
                    col=-1;
                    in.nextLine();
                }
                //quits game if a check mate has occured
                if(!game.playturn(choice,row,col,sourceRow,sourceCol))
                {
                    break;
                }
                //prints the updated game board
                game.getboard().printString();
            }
        }
        
        //prints out which player won
        if (game.getnumTurns()%2==0)
        {
            System.out.println("Player1 Lost");
        }
        else if (game.getnumTurns()%2!=0)
        {
            System.out.println("Player2 Lost");
        }
    }
}
