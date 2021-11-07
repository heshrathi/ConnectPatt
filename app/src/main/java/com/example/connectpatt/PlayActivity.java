package com.example.connectpatt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    //playerTurn => 1: red, 2: yellow, 0: empty
    int playerTurn = 1;

    int[] pattBlock = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    int[][] winningPosition = {{0, 4, 8, 9}, {4, 8, 12, 13}, {4, 5, 8, 12}, {0, 1, 4, 8},
            {1, 5, 9, 10}, {1, 5, 8, 9}, {5, 9, 13, 14}, {5, 9, 12, 13},
            {4, 5, 9, 13}, {5, 6, 9, 13}, {0, 1, 5, 9}, {1, 2, 5, 9},
            {2, 6, 10, 11}, {2, 6, 9, 10}, {6, 10, 13, 14}, {6, 10, 14, 15},
            {5, 6, 10, 14}, {6, 7, 10, 14}, {1, 2, 6, 10}, {2, 3, 6, 10},
            {3, 7, 10, 11}, {2, 3, 7, 11}, {7, 11, 14, 15}, {6, 7, 11, 15},
            {0, 1, 2, 6}, {0, 1, 2, 4}, {1, 2, 3, 5}, {1, 2, 3, 7},
            {2, 4, 5, 6}, {4, 5, 6, 10}, {3, 5, 6, 7}, {5, 6, 7, 11},
            {1, 5, 6, 7}, {5, 6, 7, 9}, {0, 4, 5, 6}, {4, 5, 6, 8},
            {6, 8, 9, 10}, {8, 9, 10, 14}, {7, 9, 10, 11}, {9, 10, 11, 15},
            {9, 10, 11, 13}, {5, 9, 10, 11}, {4, 8, 9, 10}, {8, 9, 10, 12},
            {10, 12, 13, 14}, {8, 12, 13, 14}, {9, 13, 14, 15}, {11, 13, 14, 15}};

    boolean winCheck = true;
    int count = 0;

    public void callPatt(View view) {
        ImageView pattCounter = (ImageView) view;

        int tagNumber = Integer.parseInt(pattCounter.getTag().toString());

        TextView winnerText = (TextView) findViewById(R.id.winnerTextView);
        Button playAgain = (Button) findViewById(R.id.playAgainButton);

        if (pattBlock[tagNumber] == 0 && winCheck == true) {

            Log.i("Tag number", String.valueOf(tagNumber));
            pattBlock[tagNumber] = playerTurn;

            pattCounter.setTranslationY(-1500);

            if (playerTurn == 1) {
                pattCounter.setImageResource(R.drawable.red);
                playerTurn = 2;
            } else if (playerTurn == 2) {
                pattCounter.setImageResource(R.drawable.yellow);
                playerTurn = 1;
            }
            pattCounter.animate().translationYBy(1500).rotation(1000).setDuration(400);
            count++;

            for (int[] winPosition : winningPosition) {
                Log.i("for", "working");
                if (pattBlock[winPosition[0]] != 0 && pattBlock[winPosition[0]] == pattBlock[winPosition[1]] && pattBlock[winPosition[1]] == pattBlock[winPosition[2]] && pattBlock[winPosition[2]] == pattBlock[winPosition[3]]) {
                    String user = "";
                    winCheck = false;
                    if (playerTurn == 2)
                        user = "Red";
                    else if (playerTurn == 1)
                        user = "Yellow";

                    Log.i("check", "won");

                    winnerText.setText(user + " player is winner!");
                    winnerText.setVisibility(View.VISIBLE);
                    playAgain.setVisibility(View.VISIBLE);
                }
                if (count == 16) {
                    winnerText.setText("Try Again!");
                    winnerText.setVisibility(View.VISIBLE);
                    playAgain.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void PlayAgain(View view) {
        Intent intent = new Intent(PlayActivity.this, PlayActivity.class);
        startActivity(intent);
        finish();
    }
}