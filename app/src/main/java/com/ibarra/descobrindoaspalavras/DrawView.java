package com.ibarra.descobrindoaspalavras;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class DrawView extends View
{

    Context app_context;
    Canvas this_canvas;

    public DrawView(Context context)
    {
        super(context);

        app_context = context;

//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
    }

// onMeasure must be included otherwise one or both scroll views will be compressed to zero pixels
// and the scrollview will then be invisible

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

        int width = 2000;
        int height = 3000 + 50; // Since 3000 is bottom of last Rect to be drawn added and 50 for padding.
        setMeasuredDimension(width, height);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        this_canvas = canvas;

// ToDo: Put drawing code in here
    }



}