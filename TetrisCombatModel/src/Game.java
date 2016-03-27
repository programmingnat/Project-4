import java.util.Random;
import java.util.function.BooleanSupplier;

/**
 * Created by nat on 3/23/16.
 */
public class Game {
    private Board mBoard;
    private Pieces mPieces;
    private Random mRand;

    int mNextPosX, mNextPosY;		// Position of the next piece
    int mNextPiece, mNextRotation;	// Kind and rotation of the next piece
    int mPosX, mPosY;				// Position of the piece that is falling down
    int mPiece, mRotation;			// Kind and rotation the piece that is falling down

    Game(Board board, Pieces pieces){


        mRand = new Random();
        // Get the pointer to the Board and Pieces classes
        mBoard = board;
        mPieces = pieces;

        // Game initialization
        initGame ();
    }

    public int getRandomWithinRange(int min,int max){
        return mRand.nextInt((max - min) + 1) + min;
    }
    public void initGame()
    {


        // First piece
        mPiece			= getRandomWithinRange (0, 6);
        mRotation		= getRandomWithinRange (0, 3);
        mPosX 			= (Board.BOARD_WIDTH / 2) + mPieces.getXInitialPosition (mPiece, mRotation);
        mPosY 			= mPieces.getYInitialPosition (mPiece, mRotation);

        //  Next piece
        mNextPiece 		= getRandomWithinRange (0, 6);
        mNextRotation 	= getRandomWithinRange (0, 3);
        mNextPosX 		= Board.BOARD_WIDTH + 5;
        mNextPosY 		= 5;
    }

    void createNewPiece()
    {
        // The new piece
        mPiece			= mNextPiece;
        mRotation		= mNextRotation;
        mPosX 			= (Board.BOARD_WIDTH / 2) + mPieces.getXInitialPosition (mPiece, mRotation);
        mPosY 			= mPieces.getYInitialPosition (mPiece, mRotation);

        // Random next piece
        mNextPiece 		= getRandomWithinRange (0, 6);
        mNextRotation 	= getRandomWithinRange (0, 3);
    }

    ////-------for debugging----------------------------------------
    void drawPiece (int pX, int pY, int pPiece, int pRotation)
    {
        System.out.println("drawPiece: pX "+pX+" pY "+pY+" pPiece "+pPiece+" pRotation"+pRotation);
        mBoard.paintMovingPiece(pX,pY,pPiece,pRotation);
    }
}
