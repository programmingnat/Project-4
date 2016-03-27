package com.imaginat.tetriscombat;

/**
 * Created by nat on 3/22/16.
 */


public class Pieces {

    public static int LONG=0;
    public static int BLOCK=1;
    public static int T_TURN=2;
    public static int RIGHT_SNAKE=3;
    public static int LEFT_SNAKE=4;
    public static int LEFT_GUN=5;
    public static int RIGHT_GUN=6;
    public static int TOTAL_PIECES=7;

    public Pieces(){
        char[][]mTotalPieces = new char[TOTAL_PIECES][];
        for(int i=0;i<mTotalPieces.length;i++){
            mTotalPieces[i] = new char[4];
            for(int j=0;j<mTotalPieces[i].length;i++){
                
            }
        }
    }


}
