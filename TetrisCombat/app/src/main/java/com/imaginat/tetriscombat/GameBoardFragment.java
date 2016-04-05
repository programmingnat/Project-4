package com.imaginat.tetriscombat;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imaginat.tetriscombat.framework.Game;
import com.imaginat.tetriscombat.framework.Graphics;
import com.imaginat.tetriscombat.framework.Input;
import com.imaginat.tetriscombat.framework.Screen;
import com.imaginat.tetriscombat.framework.implementation.AndroidFastRenderView;
import com.imaginat.tetriscombat.framework.implementation.AndroidGraphics;
import com.imaginat.tetriscombat.framework.implementation.AndroidInput;

/**
 * Created by nat on 4/4/16.
 */
public class GameBoardFragment extends Fragment implements Game {

    public interface ISendInfo{
       public void communicate(int c);
    }
    AndroidFastRenderView renderView;
    Graphics graphics;
    //Audio audio;
    Input input;
    //FileIO fileIO;
    Screen screen;
    PowerManager.WakeLock wakeLock;
    ISendInfo mISendInfo=null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof ISendInfo){
            mISendInfo = (ISendInfo)context;
            mISendInfo.communicate(0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         final View view = inflater.inflate(R.layout.gameboardfragment,container,false);
        //return super.onCreateView(inflater, container, savedInstanceState);
        super.onCreate(savedInstanceState);

        //getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        boolean isLandscape = getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
       int frameBufferWidth = isLandscape ? 480 : 320;
        int frameBufferHeight = isLandscape ? 320 : 480;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
                frameBufferHeight, Bitmap.Config.RGB_565);

        float scaleX = (float) frameBufferWidth
                / getActivity().getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight
                / getActivity().getWindowManager().getDefaultDisplay().getHeight();

        renderView = new AndroidFastRenderView(this, frameBuffer);
        graphics = new AndroidGraphics(getActivity().getAssets(), frameBuffer);
//        fileIO = new AndroidFileIO(this);
//        audio = new AndroidAudio(this);
        input = new AndroidInput(getActivity(), renderView, scaleX, scaleY);
        screen = new BlankScreen(this);//new TempScreen(this);
        getActivity().setContentView(renderView);

        PowerManager powerManager = (PowerManager) getActivity().getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");

        //TempScreen gameScreen = new TempScreen(this);
        //setScreen(gameScreen);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
       wakeLock.acquire();
        screen.resume();
        renderView.resume();
    }


    public void onPause() {
        super.onPause();
        wakeLock.release();
        renderView.pause();
        screen.pause();

        if (getActivity().isFinishing())
            screen.dispose();
    }

    @Override
    public Input getInput() {
        return input;
    }
    /*
        @Override
        public FileIO getFileIO() {
            return fileIO;
        }
    */
    @Override
    public Graphics getGraphics() {
        return graphics;
    }
    /*
        @Override
        public Audio getAudio() {
            return audio;
        }
    */
    @Override
    public void setScreen(Screen screen) {
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
        this.screen.setCommunicationInterface(mISendInfo);
    }

    public Screen getCurrentScreen() {
        return screen;
    }

    @Override
    public Screen getStartScreen() {
        return null;
    }
}
