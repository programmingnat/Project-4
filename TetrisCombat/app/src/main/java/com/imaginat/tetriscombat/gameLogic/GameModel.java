package com.imaginat.tetriscombat.gameLogic;

/**
 * Created by nat on 3/27/16.
 */
import android.graphics.Color;

import com.imaginat.tetriscombat.TempScreen;

import java.util.Random;

/**
 * Created by nat on 3/23/16.
 */
public class GameModel {
    private Board mBoard;
    private Pieces mPieces;
    private Random mRand;

    int mNextPosX, mNextPosY;		// Position of the next piece
    public int mNextPiece, mNextRotation,mNextColor;	// Kind and rotation of the next piece
    public int mPosX, mPosY;				// Position of the piece that is falling down
    public int mPiece, mRotation,mColor;			// Kind and rotation the piece that is falling down

    public int[] mPlayerPowersCount= new int[TempScreen.ATTACK_POWERS_MAX_INDEX+1];

    public static final int[] GAME_COLORS= new int[]{Color.RED,Color.rgb(255,165,0),Color.YELLOW,Color.BLUE,Color.GREEN};

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
        mPiece			= getRandomWithinRange (0, 6);
        mRotation		= getRandomWithinRange (0, 3);
        mPosX 			= (Board.BOARD_WIDTH / 2) + mPieces.getXInitialPosition (mPiece, mRotation);
        mPosY 			= mPieces.getYInitialPosition (mPiece, mRotation);
        mColor          = getRandomWithinRange(0,4);

        //  Next piece
        mNextPiece 		= getRandomWithinRange (0, 6);
        mNextRotation 	= getRandomWithinRange (0, 3);
        mNextPosX 		= Board.BOARD_WIDTH + 5;
        mNextPosY 		= 5;
        mNextColor      =getRandomWithinRange(0,4);
    }

    public void createNewPiece()
    {
        // The new piece
        mPiece			= mNextPiece;
        mRotation		= mNextRotation;
        mPosX 			= (Board.BOARD_WIDTH / 2) + mPieces.getXInitialPosition (mPiece, mRotation);
        mPosY 			= mPieces.getYInitialPosition (mPiece, mRotation);
        mColor          =getRandomWithinRange(0,4);
        // Random next piece
        mNextPiece 		= getRandomWithinRange (0, 6);
        mNextRotation 	= getRandomWithinRange (0, 3);
        mNextColor      =getRandomWithinRange(0,4);
        GameLog.getInstance().write("createNewPiece: with pieceID"+mPiece+" rotation "+mRotation+" posX "+mPosX+" posY "+mPosY);
    }

    ////-------for debugging----------------------------------------
    public void drawPiece (int pX, int pY, int pPiece, int pRotation)
    {
        System.out.println("drawPiece: pX "+pX+" pY "+pY+" pPiece "+pPiece+" pRotation"+pRotation);
        //mBoard.paintMovingPiece(pX,pY,pPiece,pRotation);
    }
}
