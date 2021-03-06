import java.util.Stack;

/**
 * Created by nat on 3/23/16.
 */
class Board{
    public final static int BOARD_LINE_WIDTH=6;			// Width of each of the two lines that delimit the board
    public final static int BLOCK_SIZE=16;				// Width and Height of each block of a piece
    public final static int BOARD_POSITION=320;			// Center position of the board from the left of the screen
    public final static int BOARD_WIDTH=10;				// Board width in blocks
    public final static int BOARD_HEIGHT=20;				// Board height in blocks
    public final static int MIN_VERTICAL_MARGIN=20;		// Minimum vertical margin for the board limit
    public final static int MIN_HORIZONTAL_MARGIN=20;	// Minimum horizontal margin for the board limit
    public final static int PIECE_BLOCKS=5; // Number of horizontal and vertical blocks of a matrix piece
    public final static byte POS_FREE=0;
    public final static byte POS_FILLED=1;
    public final static byte TEMP_PAINT=5;


    class DebugPoint{
        int x, y;
        DebugPoint(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    private byte[][] mBoard;
    private Pieces mPieces = null;
    Stack<DebugPoint>debugIDrewHere = new Stack<>();
    public Board(Pieces pieces){

        mPieces = pieces;
    }

    public void init(){
        mBoard= new byte[BOARD_HEIGHT][];
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            mBoard[i]=new byte[BOARD_WIDTH];
            for (int j = 0; j < BOARD_WIDTH; j++) {
                mBoard[i][j] = POS_FREE;
            }
        }
    }

    public void clearMovingPiece(){
        while(debugIDrewHere.empty()==false){

            DebugPoint d = debugIDrewHere.pop();
            if(mBoard[d.x][d.y]==5) {
                mBoard[d.x][d.y] = POS_FREE;
            }
        }
    }
    //this is for debugging purpose only
    public void paintMovingPiece(int pX,int pY,int pieceType,int rotation){
        System.out.println("Inside paintMovingPiece with "+pX+" "+pY+" "+pieceType+" "+rotation);
        //clear previous
        clearMovingPiece();
        for(int i=pY,pieceVerticalCounter=0;i<pY+PIECE_BLOCKS;i++,pieceVerticalCounter++){

            for(int j=pX,pieceHorizontalCounter=0;j<pX+PIECE_BLOCKS;j++,pieceHorizontalCounter++){

                if(mPieces.getBlockType(pieceType,rotation,pieceVerticalCounter,pieceHorizontalCounter)!=0){
                    //int blockType=mPieces.getBlockType(pieceType,rotation,j,i);
                    //System.out.println("found block type "+blockType+" to be placed at "+i+","+j);
                    if(i<0  || j<0){
                        continue;
                    }
                    debugIDrewHere.push(new DebugPoint(i,j));
                    mBoard[i][j]=TEMP_PAINT;
                }
            }//end of for loop going across

        }//for loop going down
    }

    //for debuggint purposes
    public void printSelectedShapeBlocks(int pieceNo,int rotation){
        int[][] block = Pieces.mPieces[pieceNo][rotation];
        for(int i=0;i<block.length;i++){
            for(int j=0;j<block[i].length;j++){
                System.out.print(block[i][j]+"|");
            }
            System.out.println("");
        }

//        for(int i=0;i<block.length;i++){
//            for(int j=0;j<block[i].length;j++){
//                System.out.print("block["+i+"]["+j+"]="+block[i][j]+"|");
//            }
//
//        }
    }
    public void placePiece(int pX,int pY,int pieceType,int rotation){
        String log = "Inside placePiece with "+pX+" "+pY+" "+pieceType+" "+rotation+"\r\n";

        for(int i=pY,pieceVerticalCounter=0;i<pY+PIECE_BLOCKS;i++,pieceVerticalCounter++){

            for(int j=pX,pieceHorizontalCounter=0;j<pX+PIECE_BLOCKS;j++,pieceHorizontalCounter++){
                if(mPieces.getBlockType(pieceType,rotation,pieceVerticalCounter,pieceHorizontalCounter)!=0){
                    int blockType=mPieces.getBlockType(pieceType,rotation,pieceVerticalCounter,pieceHorizontalCounter);
                    System.out.println("found block type "+blockType+" to be placed at "+i+","+j+" piece coord "+pieceVerticalCounter+" "+pieceHorizontalCounter);
                    log+="found block type "+blockType+" to be placed at "+i+","+j+"\r\n";

                    mBoard[i][j]=POS_FILLED;
                }
            }//end of for loop going across
            GameLog.getInstance().write(log);
        }//for loop going down

    }

    public boolean isGameOver()
    {
        //If the first line has blocks, then, game over
        for (int i = 0; i < BOARD_WIDTH; i++)
        {
            if (mBoard[0][i] == POS_FILLED) return true;
        }

        return false;
    }

    void deleteLine (int pY)
    {
        // Moves all the upper lines one row down
        for (int j = pY; j > 0; j--)
        {
            for (int i = 0; i < BOARD_WIDTH; i++)
            {
                mBoard[j][i] = mBoard[j-1][i];
            }
        }
    }

    public void deletePossibleLines ()
    {
        for (int j = 0; j < BOARD_HEIGHT; j++)
        {
            int i = 0;
            while (i < BOARD_WIDTH)
            {
                if (mBoard[j][i] != POS_FILLED) break;
                i++;
            }

            if (i == BOARD_WIDTH){
                System.out.println("DETERMINED TO DELEtE LINE "+j);
                deleteLine (j);
            }
        }
    }

    public boolean isFreeBlock (int pX, int pY)
    {
        System.out.print("Inside isFreeBlock with args "+pX+" "+pY);
        System.out.print("value is "+mBoard [pX][pY]);
        if (mBoard [pX][pY] == POS_FREE || mBoard[pX][pY]==TEMP_PAINT) {
            System.out.println(" returning true");
            return true;
        }else {
            System.out.println(" returning false");
            return false;
        }
    }

    public boolean isPossibleMovement (int pX, int pY, int pPiece, int pRotation)
    {
       String log = "inside isPossibleMovement with pX:"+pX+" pY:"+pY+" pPiece:"+pPiece+" pRotation:"+pRotation+"\r\n";
        // Checks collision with pieces already stored in the board or the board limits
        // This is just to check the 5x5 blocks of a piece with the appropiate area in the board
        for (int i1 = pY, i2 = 0; i1 < pY + PIECE_BLOCKS; i1++, i2++) {
            for (int j1 = pX, j2 = 0; j1 < pX + PIECE_BLOCKS; j1++, j2++) {
                log+="Inner loop isPossibleMovement, value of i1:"+i1+" j1:"+j1+" i2 (block)"+i2+" j2(block)"+j2+"\r\n";
                // Check if the piece is outside the limits of the board
                if (	i1 < 0 ||i1 > (BOARD_HEIGHT  - 1)	|| j1<0 || j1 > (BOARD_WIDTH - 1)){
                    log+="piece may be outside of boundaries "+i1+" "+j1+" "+i2+" "+j2+"\r\n";
                    //System.out.println("Checking if piece is outside of board");
                    int k = mPieces.getBlockType (pPiece, pRotation, i2, j2);
                    int[][][][] theData=Pieces.getPieces();
                    int k2 = theData[pPiece][0][i2][j2];
                    log+="block type at "+i2+" "+j2+" is "+k+ " checking at board coord "+i1+" "+j1+"k2 is "+k2+"\r\n";
                    if (mPieces.getBlockType (pPiece, pRotation, i2, j2) != 0) {
                        printSelectedShapeBlocks(pPiece,pRotation);

                        GameLog.getInstance().write(log);
                        System.out.println("=================================piece is outside of board=== "+j2+" "+i2);
                        return false;
                    }
                }

                // Check if the piece have collisioned with a block already stored in the map
                //debug...gotta clear
                //j1 is horizontal coord
                if (j1 >= 0) {
                    log+="checking of collision with other block, going through columns\r\n";

                    int blockType=mPieces.getBlockType (pPiece, pRotation, i2, j2);
                    log+="Found blockType "+blockType+" at row "+j2+" column "+i2+" piece "+pPiece+"\r\n";
                    if (blockType!=0 ){
                            if((!isFreeBlock (i1, j1))){
                                printSelectedShapeBlocks(pPiece,pRotation);
                                System.out.println("==============================collision with block");
                                GameLog.getInstance().write(log);
                                return false;
                            }//end ofif
                    }//end of if
                }//end of if
            }
        }

        // No collision
        System.out.println("=========================================no collision detected");
        GameLog.getInstance().write(log);
        return true;
    }
    public void printBoard(){
        for (int i = 0; i < BOARD_HEIGHT; i++){
            for (int j = 0; j < BOARD_WIDTH; j++){
                if(i==0){
                    if (mBoard[i][j] == 0)
                        System.out.print(" |" + "");
                    else
                        System.out.print("|"+mBoard[i][j]);
                }else {
                    if (mBoard[i][j] == 0)
                        System.out.print(" " + "|");
                    else
                        System.out.print(mBoard[i][j] + "|");
                }
            }
            System.out.println(" ");
        }

    }
}
