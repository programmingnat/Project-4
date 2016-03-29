package com.imaginat.tetriscombat.gameLogic;

/**
 * Created by nat on 3/27/16.
 */
import java.util.Random;

/**
 * Created by nat on 3/23/16.
 */
public class GameModel {
    private Board mBoard;
    private Pieces mPieces;
    private Random mRand;

    int mNextPosX, mNextPosY;		// Position of the next piece
    int mNextPiece, mNextRotation;	// Kind and rotation of the next piece
    public int mPosX, mPosY;				// Position of the piece that is falling down
    public int mPiece, mRotation;			// Kind and rotation the piece that is falling down

   public  GameModel(Board board, Pieces pieces){


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
        mPiece			= 0;//getRandomWithinRange (0, 6);
        mRotation		= getRandomWithinRange (0, 3);
        mPosX 			= (Board.BOARD_WIDTH / 2) + mPieces.getXInitialPosition (mPiece, mRotation);
        mPosY 			= mPieces.getYInitialPosition (mPiece, mRotation);

        //  Next piece
        mNextPiece 		= 0;//getRandomWithinRange (0, 6);
        mNextRotation 	= getRandomWithinRange (0, 3);
        mNextPosX 		= Board.BOARD_WIDTH + 5;
        mNextPosY 		= 5;
    }

    public void createNewPiece()
    {
        // The new piece
        mPiece			= 0;//mNextPiece;
        mRotation		= mNextRotation;
        mPosX 			= (Board.BOARD_WIDTH / 2) + mPieces.getXInitialPosition (mPiece, mRotation);
        mPosY 			= mPieces.getYInitialPosition (mPiece, mRotation);

        // Random next piece
        mNextPiece 		= 0;//getRandomWithinRange (0, 6);
        mNextRotation 	= getRandomWithinRange (0, 3);
        GameLog.getInstance().write("createNewPiece: with pieceID"+mPiece+" rotation "+mRotation+" posX "+mPosX+" posY "+mPosY);
    }

    ////-------for debugging----------------------------------------
    public void drawPiece (int pX, int pY, int pPiece, int pRotation)
    {
        System.out.println("drawPiece: pX "+pX+" pY "+pY+" pPiece "+pPiece+" pRotation"+pRotation);
        //mBoard.paintMovingPiece(pX,pY,pPiece,pRotation);
    }
}
