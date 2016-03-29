package com.imaginat.tetriscombat.gameLogic;

import android.util.Log;

import java.util.logging.Logger;

/**
 * Created by nat on 3/27/16.
 */

public class GameLog {
    public static GameLog mInstance=null;
    private Logger logger;
    private GameLog(){
       /* try {
            logger = Logger.getLogger("MyLog");
            logger.setUseParentHandlers(false);
            FileHandler fh = new FileHandler("~/Gamelog.txt");
            // SimpleFormatter formatter = new SimpleFormatter();
            CustomLogFormat format = new CustomLogFormat();
            fh.setFormatter(format);
            logger.addHandler(fh);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }*/

    }

    public static GameLog getInstance(){
        if(mInstance==null){
            mInstance = new GameLog();
        }
        return mInstance;
    }

    public void write(String s){
        Log.d("GameLog",s);
        //logger.info(s);

    }

}