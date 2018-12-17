package com.montilangelo.connect3;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 2 = unoccupied, 1 = yellow, 0 = red
    int activePlayer = 0;
    boolean gameIsActive = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && gameIsActive){
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if(activePlayer == 0){
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            }else{
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(250);
            for(int[] winningPositions: winningPositions){
                if(gameState[winningPositions[0]] == gameState[winningPositions[1]] &&
                        gameState[winningPositions[1]] == gameState[winningPositions[2]] &&
                        gameState[winningPositions[0]] != 2){
                        // Show winner
                        System.out.println(gameState[winningPositions[0]]+"has won the game.");
                        ConstraintLayout dialog = (ConstraintLayout)findViewById(R.id.dialog);
                        dialog.setVisibility(View.VISIBLE);
                        dialog.bringToFront();
                        TextView message = (TextView) findViewById(R.id.message_tv);
                        String color;
                        if(gameState[winningPositions[0]] == 0){
                            color = "Red has won the game!";
                        }else{
                            color = "Yellow has won the game!";
                        }
                        message.setText(color);
                        gameIsActive = false;
                }
            }
        }else{
            Log.e("ERROR","HEREEE1");
            boolean gameIsOver = true;
            for(int counterState: gameState){
                if(counterState == 2){
                    gameIsOver = false;
                }
            }
            Log.e("ERROR","HEREEE2");
            if(gameIsOver){
                TextView message = (TextView) findViewById(R.id.message_tv);
                String drawMsg = " It's a Draw!";
                message.setText(drawMsg);
                ConstraintLayout constraintLayout = (ConstraintLayout)findViewById(R.id.dialog);
                constraintLayout.setVisibility(View.VISIBLE);
                constraintLayout.bringToFront();
                Log.e("ERROR","HEREEE3");
            }
        }
    }
    public void playAgain(View view){
        gameIsActive = true;
        activePlayer = 0;
        for(int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }
        ConstraintLayout constraintLayout = (ConstraintLayout)findViewById(R.id.dialog);
        constraintLayout.setVisibility(View.INVISIBLE);
        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout)findViewById(R.id.gridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
