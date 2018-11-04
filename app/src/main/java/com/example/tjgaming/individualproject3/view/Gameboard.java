package com.example.tjgaming.individualproject3.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.tjgaming.individualproject3.R;

/**
 * Created by TJ on 11/1/2018.
 */
public class Gameboard extends View {

    public static String DIFFICULTY = "default";
    public static String LEVEL = "default";

    int width;
    int height;

    private Paint bgPaint = new Paint();
    private Paint pathPaint = new Paint();

    private Rect rect = new Rect();

    public Gameboard(Context context, AttributeSet set) {
        super(context,set);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.i("OnDrawCalled",LEVEL);

        switch(LEVEL){
            case "Level 1":
                createLevelOne(canvas);
                break;
            case "Level 2":
                createLevelTwo(canvas);
                break;
            case "Level 3":
                createLevelThree(canvas);
                break;
            case "default":
                break;
        }

    }

    private void createLevelThree(Canvas canvas) {
        if (DIFFICULTY.equals("Easy")) {
            //Draw the background
            bgPaint.setColor(getResources().getColor(R.color.colorAccent));
            canvas.drawRect(0,0,width,height,bgPaint);

            //Draw the path
            rect.set(0,0,width/5,height/2);
            pathPaint.setColor(getResources().getColor(R.color.colorPrimary));
            pathPaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(rect, pathPaint);
            rect.set(width/5,(height/2 - width/5),width,height/2);
            canvas.drawRect(rect,pathPaint);
            rect.set(width - width/5,0,width,height/2);
            canvas.drawRect(rect,pathPaint);

        } else {
            //Draw the background
            bgPaint.setColor(getResources().getColor(R.color.colorAccent));
            canvas.drawRect(0,0,width,height,bgPaint);

            //Draw the path
            rect.set(0,width/20,width,(width/5 + width/20));
            pathPaint.setColor(getResources().getColor(R.color.colorPrimary));
            pathPaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(rect, pathPaint);
            rect.set(width - width/5,width/5 + width/20,width,height/2 + width/20);
            canvas.drawRect(rect,pathPaint);
            rect.set(0,height/2 + width/20 - width/5,width,height/2 + width/20);
            canvas.drawRect(rect,pathPaint);
            rect.set(0,height/2 + width/20,width/5,height);
            canvas.drawRect(rect,pathPaint);


        }
    }

    private void createLevelTwo(Canvas canvas) {
        if (DIFFICULTY.equals("Easy")){
            //Draw the background
            bgPaint.setColor(getResources().getColor(R.color.colorAccent));
            canvas.drawRect(0,0,width,height,bgPaint);

            //Draw the path
            rect.set(0,width/20,width,(width/5 + width/20));
            pathPaint.setColor(getResources().getColor(R.color.colorPrimary));
            pathPaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(rect, pathPaint);
            rect.set(width - width/5,width/5 + width/20,width,height/2 + width/20);
            canvas.drawRect(rect,pathPaint);
        } else {
            //Draw the background
            bgPaint.setColor(getResources().getColor(R.color.colorAccent));
            canvas.drawRect(0,0,width,height,bgPaint);

            //Draw the path
            rect.set(0,width/20,width,(width/5 + width/20));
            pathPaint.setColor(getResources().getColor(R.color.colorPrimary));
            pathPaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(rect, pathPaint);
            rect.set(width - width/5,width/5 + width/20,width,height/2 + width/20);
            canvas.drawRect(rect,pathPaint);
            rect.set(0,height/2 + width/20 - width/5,width,height/2 + width/20);
            canvas.drawRect(rect,pathPaint);
        }
    }

    private void createLevelOne(Canvas canvas) {
        if (DIFFICULTY.equals("Easy")) {
            //Draw the background
            bgPaint.setColor(getResources().getColor(R.color.colorAccent));
            canvas.drawRect(0,0,width,height,bgPaint);

            //Draw the path
            rect.set(0,0,width/5,height/2);
            pathPaint.setColor(getResources().getColor(R.color.colorPrimary));
            pathPaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(rect, pathPaint);
            rect.set(width/5,(height/2 - width/5),width,height/2);
            canvas.drawRect(rect,pathPaint);
        } else {
            //Draw the background
            bgPaint.setColor(getResources().getColor(R.color.colorAccent));
            canvas.drawRect(0,0,width,height,bgPaint);

            //Draw the path
            rect.set(0,0,width/5,height/2);
            pathPaint.setColor(getResources().getColor(R.color.colorPrimary));
            pathPaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(rect, pathPaint);
            rect.set(width/5,height/2 - width/5,width,height/2);
            canvas.drawRect(rect,pathPaint);
            rect.set(width - width/5,height/2 - width/5,width,height);
            canvas.drawRect(rect,pathPaint);

        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;
    }
}