package com.example.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CustomView extends View {

    private Paint paint;
    private int x;
    private int y;

    public CustomView(Context context, int x, int y) {
        super(context);
        this.x = x;
        this.y = y;
        // create the Paint and set its color
        paint = new Paint();
        paint.setColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
       // canvas.drawColor(Color.BLUE);
        /*canvas.drawCircle(200, 200, 50, paint);
        canvas.drawCircle(250, 250, 50, paint);*/
        canvas.drawCircle(x, y, 20, paint);
        paint.setColor(Color.RED);
        canvas.drawCircle(x, y, 15, paint);
    }
}