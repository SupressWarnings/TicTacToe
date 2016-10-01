package com.example.tobias.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import player.Player;





public class MainActivity extends AppCompatActivity {

    Button[] buttons;
    TextView output;
    private int player;
    private boolean[] clicked;
    private Player x, o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        x = new Player("X", "X");
        o = new Player("O", "O");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clicked = new boolean[9];
        for(int i = 0; i<9; ++i){
            clicked[i] = false;
        }
        this.output = (TextView) findViewById(R.id.output);
        buttons = new Button[10];
        buttons[0] = (Button) findViewById(R.id.button);
        buttons[1] = (Button) findViewById(R.id.button2);
        buttons[2] = (Button) findViewById(R.id.button3);
        buttons[3] = (Button) findViewById(R.id.button4);
        buttons[4] = (Button) findViewById(R.id.button5);
        buttons[5] = (Button) findViewById(R.id.button6);
        buttons[6] = (Button) findViewById(R.id.button7);
        buttons[7] = (Button) findViewById(R.id.button8);
        buttons[8] = (Button) findViewById(R.id.button9);
        buttons[9] = (Button) findViewById(R.id.button10);

        play();
    }

    private void play(){
        for(int i = 0; i< 9; ++i){
            final int number = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(player == 0 && !clicked[number]){
                        buttons[number].setText(x.getToken());
                        player = 1;
                    }else if(player == 1 && !clicked[number]){
                        buttons[number].setText(o.getToken());
                        player = 0;
                    }
                    clicked[number] = true;
                    checkWin();
                }
            });
        }
        buttons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < 9; ++i){
                    buttons[i].setText("");
                    clicked[i] = false;
                }
                player = 0;
                output.setText("");
            }
        });
    }

    private void checkWin(){
        for(int i = 0; i<3; ++i){
            int start = 3*i;
            if(buttons[start].getText().toString().equals(buttons[start+1].getText().toString()) && buttons[start].getText().toString().equals(buttons[start+2].getText().toString())){
                win(start);
            }
        }

        for(int i = 0; i<3; ++i){
            if(buttons[i].getText().toString().equals(buttons[i + 3].getText().toString()) && buttons[i].getText().toString().equals(buttons[i+6].getText().toString())){
                win(i);
            }
        }
        if(buttons[0].getText().toString().equals(buttons[4].getText().toString()) && buttons[0].getText().toString().equals(buttons[8].getText().toString())){
            win(0);
        }
        if(buttons[2].getText().toString().equals(buttons[4].getText().toString()) && buttons[2].getText().toString().equals(buttons[6].getText().toString())){
            win(2);
        }
    }

    private void win(int i){
        if(buttons[i].getText().toString().equals(x.getToken())){
            output.setText(x.getName() + " wins!!!");
        }else if(buttons[i].getText().toString().equals(o.getToken())){
            output.setText(o.getName() + " wins!!!");
        }else{
            return;
        }
        player=2;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
