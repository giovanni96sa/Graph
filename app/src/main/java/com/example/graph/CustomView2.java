package com.example.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

public class CustomView2 extends View {

    private Paint paint;
    private Nodo n1;
    private ArrayList<Nodo> nodi;

    public CustomView2(Context context, Nodo n1, ArrayList<Nodo> nodi) {
        super(context);
        // create the Paint and set its color
        this.n1 = n1;
        this.nodi = nodi;
        paint = new Paint();
        paint.setColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for(Nodo n2: nodi){
            canvas.drawLine(n1.getX(), n1.getY(), n2.getX(), n2.getY(), paint);
        }
    }
}
