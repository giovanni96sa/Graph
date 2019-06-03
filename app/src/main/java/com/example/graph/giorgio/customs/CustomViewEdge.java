package com.example.graph.giorgio.customs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.graph.giorgio.graph.stuffs.Nodo;

public class CustomViewEdge extends View {

    private Paint paint;
    private Nodo n1;
    private Nodo n2;

    public CustomViewEdge(Context context, Nodo n1, Nodo n2) {
        super(context);
        this.n1 = n1;
        this.n2 = n2;
        paint = new Paint();
        paint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine(n1.getX(),n1.getY(),n2.getX(),n2.getY(),paint);
    }

}
