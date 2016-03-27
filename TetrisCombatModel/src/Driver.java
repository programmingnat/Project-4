import java.util.Scanner;

/**
 * Created by nat on 3/22/16.
 */
public class Driver {

    public static void main(String args[]) {
        System.out.println("Starting Tetris:Combat test model");

       /* System.out.println("Testing out pieces");
        int[][] block = Pieces.mPieces[Pieces.LONG][0];
        for(int i=0;i<block.length;i++){
            for(int j=0;j<block[i].length;j++){
                    System.out.print("|"+(block[i][j]));
            }
            System.out.println("");
        }*/


        Pieces pieces = new Pieces();
        Board board = new Board(pieces);
        board.init();
        Game game = new Game(board, pieces);
        game.initGame();

        Scanner s = new Scanner(System.in);
        String instructions = "START_GAME";

        //board.isPossibleMovement(5,16,1,1);

        int counter = 0;

        while (instructions.equalsIgnoreCase("END") == false) {
            instructions = s.nextLine();
            //System.out.println("drawPiece about to be called with, posX: "+game.mPosX+" posY: "+game.mPosY+"rotation: "+game.mRotation);
            //game.drawPiece(game.mPosX,game.mPosY,game.mPiece,game.mRotation);
            //System.out.println("drawPiece just called");

            if (instructions.equalsIgnoreCase("RIGHT")) {
                if(board.isPossibleMovement(game.mPosX+1,game.mPosY,game.mPiece,game.mRotation))
                    game.mPosX++;
            }

            if (instructions.equalsIgnoreCase("LEFT")) {
                if(board.isPossibleMovement(game.mPosX-1,game.mPosY,game.mPiece,game.mRotation))
                    game.mPosX--;

            }
            if(instructions.equalsIgnoreCase("ROTATE")){

                int tempRotation  =(game.mRotation+1)%4;
                if(board.isPossibleMovement(game.mPosX,game.mPosY,game.mPiece,tempRotation))
                    game.mRotation=tempRotation;


            }
            //progress the game piece down
            String log="\r\nPROGRESS PIECE CALCULATION";
            log+="\r\n+++Progress piece calculation+++";
            if (board.isPossibleMovement(game.mPosX, game.mPosY + 1, game.mPiece, game.mRotation)) {
                System.out.println("Progress: movement is possible tp " + (game.mPosY + 1));
                //board.printSelectedShapeBlocks(game.mPiece,game.mRotation);
                game.drawPiece(game.mPosX, game.mPosY, game.mPiece, game.mRotation);
                game.mPosY++;

            } else {
                System.out.println("movement of game piece not possible");
                board.placePiece(game.mPosX, game.mPosY, game.mPiece, game.mRotation);

                board.deletePossibleLines();

                if (board.isGameOver()) {

                    System.out.println("GAME OVER");
                    System.exit(0);
                }
                log+="\r\nCreating new piece";
                GameLog.getInstance().write(log);
                game.createNewPiece();


            }


            System.out.println("NEXT TICK: CURRENT SCREEN " + counter);
            board.printBoard();
            counter++;
        }//end of while loop

        /*
        System.out.println("Testing out pieces");
        int[][] block = Pieces.mPieces[Pieces.L_SHAPE][0];
        for(int i=0;i<block.length;i++){
            for(int j=0;j<block[i].length;j++){
                System.out.print(block[i][j]+"|");
            }
            System.out.println("");
        }

        Pieces pieces = new Pieces();
        int blockType= pieces.getBlockType(Pieces.L_SHAPE,0,2,2);
        System.out.println("found blockType "+blockType);



        System.out.println("Testing out board");
        Board b = new Board();
        b.init();
        b.printBoard();

        System.out.println("Placing piece in board");
        b.placePiece(0,0,Pieces.SQUARE,0);
        b.printBoard();*/
    }
}

