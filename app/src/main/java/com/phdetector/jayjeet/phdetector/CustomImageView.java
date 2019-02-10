package com.phdetector.jayjeet.phdetector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;


class Marker {
    public float x;
    public float y;


    public Marker(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

public class CustomImageView extends ImageView {

    Marker m = new Marker(0,0);
    Paint paint = new Paint();

    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        //canvas.drawCircle(m.x,m.y,10,paint);
        canvas.drawRect(m.x-15,m.y+15,m.x+15,m.y-15,paint);
        invalidate();
    }
}


