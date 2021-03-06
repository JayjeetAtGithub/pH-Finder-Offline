package com.phdetector.jayjeet.phdetector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
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
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawCircle(m.x,m.y,30,paint);
        canvas.drawPoint(m.x,m.y,paint);
        invalidate();
    }
}


