package com.fztl.harsha.gamerain;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public interface GRCode {
    public void run(Canvas canvas);
    public void update();
    public void onTouch(boolean clicked);
}
