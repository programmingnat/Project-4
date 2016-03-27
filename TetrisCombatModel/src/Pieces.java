/**
 * Created by nat on 3/22/16.
 */
public class Pieces {

    public static final int SQUARE=0;
    public static final int LONG=1;
    public static final int L_SHAPE=2;
    public static final int REVERSE_L_SHAPE=3;
    public static final int N_SHAPED=4;
    public static final int REVERSE_N_SHAPED=5;
    public static final int T_SHAPED=6;

    public static int[][][][] getPieces(){
        return mPieces;
    }
    public static int[][][][] mPieces=
            {//the entire pieces array
                    //SQUARE
                    {

                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 2, 1, 0},
                                    {0, 0, 1, 1, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 2, 1, 0},
                                    {0, 0, 1, 1, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 2, 1, 0},
                                    {0, 0, 1, 1, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 2, 1, 0},
                                    {0, 0, 1, 1, 0},
                                    {0, 0, 0, 0, 0}
                            }
                    },
                    // I
                    {
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0},
                                    {0, 1, 2, 1, 1},
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 1, 0, 0},
                                    {0, 0, 2, 0, 0},
                                    {0, 0, 1, 0, 0},
                                    {0, 0, 1, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0},
                                    {1, 1, 2, 1, 0},
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 1, 0, 0},
                                    {0, 0, 1, 0, 0},
                                    {0, 0, 2, 0, 0},
                                    {0, 0, 1, 0, 0},
                                    {0, 0, 0, 0, 0}
                            }
                    },
                    // the L-SHPE
                    {
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 1, 0, 0},
                                    {0, 0, 2, 0, 0},
                                    {0, 0, 1, 1, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0},
                                    {0, 1, 2, 1, 0},
                                    {0, 1, 0, 0, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 1, 1, 0, 0},
                                    {0, 0, 2, 0, 0},
                                    {0, 0, 1, 0, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 1, 0},
                                    {0, 1, 2, 1, 0},
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0}
                            }
                    },
                    //reverse L
                    {
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 1, 0, 0},
                                    {0, 0, 2, 0, 0},
                                    {0, 1, 1, 0, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 1, 0, 0, 0},
                                    {0, 1, 2, 1, 0},
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 1, 1, 0},
                                    {0, 0, 2, 0, 0},
                                    {0, 0, 1, 0, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0},
                                    {0, 1, 2, 1, 0},
                                    {0, 0, 0, 1, 0},
                                    {0, 0, 0, 0, 0}
                            }
                    },
                    {
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 1, 0},
                                    {0, 0, 2, 1, 0},
                                    {0, 0, 1, 0, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0},
                                    {0, 1, 2, 0, 0},
                                    {0, 0, 1, 1, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 1, 0, 0},
                                    {0, 1, 2, 0, 0},
                                    {0, 1, 0, 0, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 1, 1, 0, 0},
                                    {0, 0, 2, 1, 0},
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0}
                            }
                    },
// N mirrored
                    {
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 1, 0, 0},
                                    {0, 0, 2, 1, 0},
                                    {0, 0, 0, 1, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 2, 1, 0},
                                    {0, 1, 1, 0, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 1, 0, 0, 0},
                                    {0, 1, 2, 0, 0},
                                    {0, 0, 1, 0, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 1, 1, 0},
                                    {0, 1, 2, 0, 0},
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0}
                            }
                    },
// T
                    {
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 1, 0, 0},
                                    {0, 0, 2, 1, 0},
                                    {0, 0, 1, 0, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0},
                                    {0, 1, 2, 1, 0},
                                    {0, 0, 1, 0, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 1, 0, 0},
                                    {0, 1, 2, 0, 0},
                                    {0, 0, 1, 0, 0},
                                    {0, 0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 1, 0, 0},
                                    {0, 1, 2, 1, 0},
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0}
                            }
                    }

            };
    // Displacement of the piece to the position where it is first drawn in the board when it is created
    int mPiecesInitialPosition  [][][] =
    {
/* Square */
        {
            {-2, -3},
            {-2, -3},
            {-2, -3},
            {-2, -3}
        },
/* I */
        {
            {-2, -2},
            {-2, -3},
            {-2, -2},
            {-2, -3}
        },
/* L */
        {
            {-2, -3},
            {-2, -3},
            {-2, -3},
            {-2, -2}
        },
/* L mirrored */
        {
            {-2, -3},
            {-2, -2},
            {-2, -3},
            {-2, -3}
        },
/* N */
        {
            {-2, -3},
            {-2, -3},
            {-2, -3},
            {-2, -2}
        },
/* N mirrored */
        {
            {-2, -3},
            {-2, -3},
            {-2, -3},
            {-2, -2}
        },
/* T */
        {
            {-2, -3},
            {-2, -3},
            {-2, -3},
            {-2, -2}
        },
    };

    /**
     *
     * @param pPiece: index 0-6, representing all seven tetris shapes
     * @param pRotation: index 0-3, representing all four rotations
     * @param pX:x index
     * @param pY: yindex
     * @return: the type of block, either 0 for empty, 1 for normal, 2 for pivot
     */
    int getBlockType (int pPiece, int pRotation, int pX, int pY)
    {
        //System.out.println("getBlockType:"+pPiece+" "+pRotation+" "+pX+" "+pY);
        return mPieces [pPiece][pRotation][pX][pY];
    }

    /**
     *
     * @param pPiece: index 0-6, representing all seven tetris shapes
     * @param pRotation:0-3, representing all four rotations
     * @return
     */
    int getXInitialPosition (int pPiece, int pRotation)
    {
        return mPiecesInitialPosition [pPiece][pRotation][0];
    }

    /**
     *
     * @param pPiece: index 0-6, representing all seven tetris shapes
     * @param pRotation:0-3, representing all four rotations
     * @return
     */
    int getYInitialPosition (int pPiece, int pRotation)
    {
        return mPiecesInitialPosition [pPiece][pRotation][1];
    }
}
