import java.io.*;

/**
 * Created by nat on 3/27/16.
 */
public class GameLog {
    public static GameLog mInstance=null;
    private Writer writer;
    private GameLog(){
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("gameLog.txt"), "utf-8"));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }

    public static GameLog getInstance(){
        if(mInstance==null){
            mInstance = new GameLog();
        }
        return mInstance;
    }

    public void write(String s){
        try {
            writer.write(s);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    public void close(){
        try{
            writer.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
