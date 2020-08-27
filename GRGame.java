package com.fztl.harsha.gamerain;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.*;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.Nullable;

public class GRGame extends SurfaceView implements SurfaceHolder.Callback {
    private GRThread thread;
    private GRCode code;

    public GRGame(Context context,GRCode code){
        super(context);
        thread=new GRThread(getHolder(), this);
        this.code = code;
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}


    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        this.code.update();
    }

    public boolean onCapturedPointerEvent(MotionEvent event) {return super.onCapturedPointerEvent(event);}

    public boolean onFilterTouchEventForSecurity(MotionEvent event) {
        //while(true){
            if(super.onFilterTouchEventForSecurity(event)){
                this.code.onTouch(super.onFilterTouchEventForSecurity(event));
            }
            return super.onFilterTouchEventForSecurity(event);
        //}
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            this.code.run(canvas);
        }
    }

    public GRThread getThread() {
        return thread;
    }
}
