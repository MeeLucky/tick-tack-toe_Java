package com.example.tudim_sudim;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    static int steps = 0;
    static boolean side = true;
    static boolean gameStatus = true;
    static int [] field = new int[] {0,
                21,22,23,
                24,25,26,
                27,28,29};

    public void restart (View view) {
        gameStatus = true;
        steps = 0;
        for(int i = 0; i < field.length; i++) {
            field[i] = i + 20;
        }
        side = true;
        Intent i = new Intent( this , this.getClass() );
        finish();
        this.startActivity(i);
    }

    public void btnClick (View view) {
        if (gameStatus) {
            String text = ((Button) view).getText().toString();
            if (text.equals("")) {
                steps++;
                Button btn = (Button) view;
                TextView mainText = (TextView) findViewById(R.id.mainText);
                LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
                String player = "";

                int tag = Integer.parseInt((String) btn.getTag());

                if (side) {
                    player = "X";
                    field[tag] = 1;
                } else {
                    player = "O";
                    field[tag] = 0;
                }
                btn.setText(player);

                side = !side;

                if (side) {
                    mainText.setText("Ход игрока Х");
                    btn.setTextColor(Color.parseColor("#ff0000"));
                }
                else {
                    mainText.setText("Ход игрока О");
                    btn.setTextColor(Color.parseColor("#0000ff"));
                }

                //victory check
                //123 456 789  147 258 369  159 357

                if (victoryChek(1, 2, 3) || victoryChek(4, 5, 6)
                        || victoryChek(7, 8, 9) || victoryChek(1, 4, 7)
                        || victoryChek(2, 5, 8) || victoryChek(3, 6, 9)
                        || victoryChek(1, 5, 9) || victoryChek(3, 5, 7)) {
                    String foo = "Победил игрок " + player;
                    mainText.setText(foo);

                    mainLayout.setBackgroundColor(Color.parseColor("#0fff83"));
                    mainText.setBackgroundColor(Color.parseColor("#0fff83"));

                    gameStatus = !gameStatus;
                }

                if (gameStatus) {
                    if (steps == 9) {
                        gameStatus = !gameStatus;
                        mainText.setBackgroundColor(Color.parseColor("#ff0000"));
                        mainText.setText("Ничья .-.");
                        mainLayout.setBackgroundColor(Color.parseColor("#ff0000"));
                    }
                }
            }
        }
    }

    private boolean victoryChek(int a, int b, int c) {
        if(field[a] == field[b] && field[b] == field[c])
            return true;
        else
            return false;
    }
}
