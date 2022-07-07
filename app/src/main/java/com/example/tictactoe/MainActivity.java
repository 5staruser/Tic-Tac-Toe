package com.example.tictactoe;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    boolean gameactive=true;
    int activeplayer=1;
    int [] gamestate={2,2,2,2,2,2,2,2,2}; //it is an array representing all the spaces in grid
//    state meanings
//    0-X
//    1-o
//    2-null
    int [][] winpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void playertap(View view){
        ImageView img=(ImageView) view;
        int tappedimage=Integer.parseInt(img.getTag().toString());
        if(!gameactive){
            gamereset(view);
        }
        if(gamestate[tappedimage]==2){
            gamestate[tappedimage]=activeplayer;
            img.setTranslationY(-1000f);
            if(activeplayer==0){
                img.setImageResource(R.drawable.x);
                activeplayer=1;
                TextView status=findViewById(R.id.status);
                status.setText("Player 2's Turn-Tap To Play");
            }
            else{
                img.setImageResource(R.drawable.o);
                activeplayer=0;
                TextView status=findViewById(R.id.status);
                status.setText("Player 1's Turn-Tap To Play");
            }
        img.animate().translationYBy(1000f).setDuration(300);
        }
        //    check if any player has won
        for(int[] winposition:winpos){
            if(gamestate[winposition[0]]==gamestate[winposition[1]] && gamestate[winposition[1]]==gamestate[winposition[2]] && gamestate[winposition[0]]!=2){
//                somebody won find out who
                String winstr;
                gameactive=false;
                if(gamestate[winposition[0]]==0){
                    winstr="Player 2 has won";
                }
                else{
                    winstr="Player 1 has won";
                }
//                update the status bar for winner announcement
                TextView status=findViewById(R.id.status);
                status.setText(winstr);
            }
            boolean emptySquare = false;
            for (int squareState : gamestate) {
                if (squareState == 2) {
                    emptySquare = true;
                    break;
                }
            }
            if (!emptySquare && gameactive) {
                // Game is a draw
                gameactive = false;
                String winnerStr;
                winnerStr = "No one won";
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
    }
    public void gamereset(View view){
        gameactive=true;
        activeplayer=0;
        for(int i=0; i<gamestate.length; i++){
            gamestate[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        TextView status=findViewById(R.id.status);
        status.setText("Player 1's Turn-Tap To Play");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}