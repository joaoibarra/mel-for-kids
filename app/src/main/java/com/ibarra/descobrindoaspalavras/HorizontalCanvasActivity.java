package com.ibarra.descobrindoaspalavras;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class HorizontalCanvasActivity extends Activity
{

    // public this_canvas this_canvas;
    public DrawView draw_view;
    public ScrollView scroll_view;
    public HorizontalScrollView h_scroll_view;
    public LinearLayout lin_layout;
    public Canvas this_canvas;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        DrawView main_draw_view;

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        lin_layout = new LinearLayout (this);
        scroll_view = new ScrollView (this);
        h_scroll_view = new HorizontalScrollView (this);

        scroll_view.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

        h_scroll_view.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                ));

        lin_layout.setLayoutParams(
                new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                ));

        lin_layout.setOrientation(LinearLayout.VERTICAL);

        Bitmap mBitmap = Bitmap.createBitmap (630, 870, Bitmap.Config.ARGB_8888);
        this_canvas = new Canvas (mBitmap);

        main_draw_view = new DrawView (this);

        main_draw_view.setLayoutParams(
                new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                ));

        scroll_view.addView(main_draw_view);
        h_scroll_view.addView(scroll_view);

        setContentView(h_scroll_view);
    }
}
