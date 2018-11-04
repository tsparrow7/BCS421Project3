package com.example.tjgaming.individualproject3.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tjgaming.individualproject3.R;
import com.example.tjgaming.individualproject3.controller.Database;
import com.example.tjgaming.individualproject3.model.Game;


public class GameActivity extends Activity {

    MyStrtDrggngLsntr mStrtDrg;
    MyEndDrgLsntr mEndDrg;
    Gameboard myGameboard;

    ImageView imageView;
    Button btn;

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    AnimatorSet animatorSet;
    String[] commands;
    String[] correctCommands;
    ObjectAnimator[] animators;
    boolean correct;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Game game = (Game)getIntent().getExtras().get("game");
        database = new Database(getApplicationContext());
        myGameboard = new Gameboard(getApplicationContext(), null);

        Gameboard.DIFFICULTY = game.getDifficulty();
        Gameboard.LEVEL = game.getLevel();
        setContentView(R.layout.activity_game);

        mStrtDrg = new MyStrtDrggngLsntr();
        mEndDrg = new MyEndDrgLsntr();

        findViewById(R.id.upBtn).setOnLongClickListener(mStrtDrg);
        findViewById(R.id.downBtn).setOnLongClickListener(mStrtDrg);
        findViewById(R.id.leftBtn).setOnLongClickListener(mStrtDrg);
        findViewById(R.id.rightBtn).setOnLongClickListener(mStrtDrg);

        (btn1 = findViewById(R.id.Btn1)).setOnDragListener(mEndDrg);
        (btn2 = findViewById(R.id.Btn2)).setOnDragListener(mEndDrg);
        (btn3 = findViewById(R.id.Btn3)).setOnDragListener(mEndDrg);
        (btn4 = findViewById(R.id.Btn4)).setOnDragListener(mEndDrg);

        imageView = findViewById(R.id.Jay);

        myGameboard = findViewById(R.id.game_board);
        myGameboard.invalidate();

        if (Gameboard.DIFFICULTY.equals("Easy")) {

            if (Gameboard.LEVEL.equals("Level 1")) {
                correctCommands = new String[]{"down","right","empty","empty"};

            } else if (Gameboard.LEVEL.equals("Level 2")) {
                correctCommands = new String[]{"right","down","empty","empty"};

            } else if (Gameboard.LEVEL.equals("Level 3")) {
                correctCommands = new String[]{"down","right","up","empty"};
            }

        } else if (Gameboard.DIFFICULTY.equals("Hard")) {

            if (Gameboard.LEVEL.equals("Level 1")) {
                correctCommands = new String[]{"down","right","down","empty"};

            } else if (Gameboard.LEVEL.equals("Level 2")) {
                correctCommands = new String[]{"right","down","left","empty"};

            } else if (Gameboard.LEVEL.equals("Level 3")) {
                correctCommands = new String[]{"right","down","left","down"};
            }
        }


        (btn = findViewById(R.id.begin_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.setEnabled(false);
                correct = true;
                commands = getCommands();
                animatorSet = new AnimatorSet();
                animators = new ObjectAnimator[commands.length];
                boolean down = false;

                for (int i = 0; i < commands.length; i++) {

                    switch (commands[i]){

                        case "empty":
                            break;
                        case "up":
                            animators[i] = playUpAnimation();
                            break;
                        case "down":
                            if (down) {
                                animators[i] = playDoubleDownAnimation();
                            } else {
                                animators[i] = playDownAnimation();
                                down = true;
                            }
                            break;
                        case "right":
                            animators[i] = playRightAnimation();
                            break;
                        case "left":
                            animators[i] = playLeftAnimation();
                            break;
                    }
                }

                if (animators[0] != null )
                    if (animators[1] != null)
                        if (animators[2] != null)
                            if (animators[3] != null)
                                animatorSet.playSequentially(animators[0], animators[1], animators[2], animators[3]);
                            else
                                animatorSet.playSequentially(animators[0], animators[1], animators[2]);
                        else
                            animatorSet.playSequentially(animators[0], animators[1]);
                    else
                        animatorSet.playSequentially(animators[0]);
                else
                    animatorSet.playSequentially();


                for (int i = 0; i < commands.length; i++) {
                    if (!(commands[i].equals(correctCommands[i]))) {
                        Log.i("Command vs correct", commands[i] + "->" + correctCommands[i]);
                        correct = false;
                    }
                }

                if (correct) {
                    animatorSet.start();
                    animatorSet.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            btn1.setEnabled(false);
                            btn2.setEnabled(false);
                            btn3.setEnabled(false);
                            btn4.setEnabled(false);

                            showLevelBeatenDialog();
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                } else {
                    Toast.makeText(GameActivity.this, "Sorry those commands are incorrect. Please try again.", Toast.LENGTH_SHORT).show();
                    btn.setEnabled(true);
                }

                btn1.setBackground(getDrawable(android.R.drawable.btn_default));
                btn2.setBackground(getDrawable(android.R.drawable.btn_default));
                btn3.setBackground(getDrawable(android.R.drawable.btn_default));
                btn4.setBackground(getDrawable(android.R.drawable.btn_default));
            }
        });

    }

    private void showLevelBeatenDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(GameActivity.this);
        alertBuilder.setMessage("Congratulations! You cleared this level")
                .setCancelable(false)
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ChildActivity.beatLevel = true;
                        finish();
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.setTitle("Level Beat");
        alert.show();
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setMessage("Are you sure you want to quit this level?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ChildActivity.beatLevel = false;
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.setTitle("Quit");
        alert.show();
    }

    private ObjectAnimator playRightAnimation() {
        return ObjectAnimator.ofFloat(imageView,"x",(myGameboard.width/1.25f)).setDuration(1000);
    }

    private ObjectAnimator playLeftAnimation() {
        return ObjectAnimator.ofFloat(imageView,"x", 0).setDuration(1000);
    }

    private ObjectAnimator playUpAnimation() {
        return ObjectAnimator.ofFloat(imageView,"y", 0).setDuration(1000);
    }

    private ObjectAnimator playDownAnimation() {
        return ObjectAnimator.ofFloat(imageView,"y",(myGameboard.height/3f)).setDuration(1000);
    }

    private ObjectAnimator playDoubleDownAnimation() {
        return ObjectAnimator.ofFloat(imageView,"y",myGameboard.height/1.2f).setDuration(1000);
    }

    private String[] getCommands() {
        String[] commands = new String[4];
        Drawable[] backgrounds = {
                btn1.getBackground(),
                btn2.getBackground(),
                btn3.getBackground(),
                btn4.getBackground()};

        for (int i = 0; i < commands.length; i++) {
            if (backgrounds[i].getConstantState()
                    .equals(getDrawable(R.drawable.ic_arrow_back_black_24dp).getConstantState())){
                commands[i] = "left";
            } else if (backgrounds[i].getConstantState()
                    .equals(getDrawable(R.drawable.ic_arrow_downward_black_24dp).getConstantState())){
                commands[i] = "down";
            } else if (backgrounds[i].getConstantState()
                    .equals(getDrawable(R.drawable.ic_arrow_upward_black_24dp).getConstantState())){
                commands[i] = "up";
            } else if (backgrounds[i].getConstantState()
                    .equals(getDrawable(R.drawable.ic_arrow_forward_black_24dp).getConstantState())){
                commands[i] = "right";
            } else {
                commands[i] = "empty";
            }
        }
        return commands;
    }

    private class MyStrtDrggngLsntr implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View view) {
            WithDragShadow shadow = new WithDragShadow(view);
            ClipData data = ClipData.newPlainText("","");
            view.startDrag(data,shadow,view,0);

            return false;
        }
    }

    private class MyEndDrgLsntr implements View.OnDragListener {

        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            if(dragEvent.getAction()==DragEvent.ACTION_DROP) {
                ((Button)view).setBackground(
                        ((Button)dragEvent.getLocalState()).getBackground());
            }
            return true;
        }
    }

    private class WithDragShadow extends View.DragShadowBuilder {
        public WithDragShadow(View v) {
            super(v);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            super.onDrawShadow(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
            super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
        }
    }
}
