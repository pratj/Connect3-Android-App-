package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//import android.widget.GridLayout;
//import android.support.constraint.solver.widgets.*;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.HashMap;

import static com.example.connect3.R.drawable.circle;

public class MainActivity extends AppCompatActivity {
    int aPlayer=0;
    int aPlayer2=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winPos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean activeGame =true;
    boolean activeGame2 =true;
    int draw=0;
    int[] scores={-1,1,0};


    public void dropIn(View view){
        //aI(view);
        user(view);
    }

    public void user(View view){
        ImageView counter= (ImageView) view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && activeGame && aPlayer==0){

            gameState[tappedCounter]= aPlayer;
            counter.setTranslationY(-1500);
            counter.setImageResource(R.drawable.cross);
            draw++;
            counter.animate().translationYBy(1500).setDuration(500);
            resultCheck();
            aPlayer=1;
            aI(view);

        }

    }




    public void aI(View view){
        GridLayout gridLayout1= (GridLayout) findViewById(R.id.gridL);

        if(activeGame && aPlayer==1){
            int bestScore=Integer.MIN_VALUE;
            int move=-1;
            for(int i=0;i<9;i++){
                if(gameState[i]==2){
                    gameState[i]=1;
                    aPlayer2=1;
                    int score=miniMax(gameState,0,false);
                    gameState[i]=2;
                    //activeGame2= true;
                    if(score>bestScore){
                        bestScore=score;
                        move=i;
                    }
                }
            }
            gameState[move]= 1;
            ImageView counter1= (ImageView) gridLayout1.getChildAt(move);
            counter1.setTranslationY(-1500);
            counter1.setImageResource(circle);
            draw++;
            counter1.animate().translationYBy(1500).setDuration(500);
            resultCheck();
            aPlayer=0;
            //user(view);
    }}





   /* public int minimax(int[] gamestate, int depth, boolean isMaximizing){
        resultCheck2(gamestate);
        if(activeGame2==false){
            Log.d("Value1232131", String.valueOf(aPlayer2));
            return scores[aPlayer2];
            //return 10;
        }
        if(isMaximizing){
            int bestScore=Integer.MIN_VALUE;
            for(int i=0;i<9;i++){
                if(gamestate[i]==2){
                    gamestate[i]=1;
                    aPlayer2=1;
                    //o=o+1;
                    int score=minimax(gamestate,depth+1,false);
                    gamestate[i]=2;
                    //bestScore=Integer.max(score,bestScore);
                    bestScore=Math.max(score,bestScore);
                }
        }
        return bestScore;
    }
        else {
            int bestScore=Integer.MAX_VALUE;
            for(int i=0;i<9;i++){
                if(gamestate[i]==2){
                    gamestate[i]=0;
                    aPlayer2=0;
                    int score=minimax(gamestate,depth+1,true);
                    //Toast.makeText(this,"333333333333##############################", Toast.LENGTH_SHORT).show();
                    gamestate[i]=2;
                    bestScore=Math.min(score,bestScore);
                }
            }
            Toast.makeText(this,"333333333333##############################", Toast.LENGTH_SHORT).show();
            return bestScore;
        }
    }*/

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int miniMax(int[] gameState2, int depth, boolean isMaximizing) {
       int result = resultCheck2(gameState2);
        if (result != 3) {
            return scores[result];
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if(gameState2[i]==2) {
                    gameState2[i]=1;
                        int score = miniMax(gameState2, depth + 1, false);
                    gameState2[i]=2;
                        bestScore = Math.max(score, bestScore);
                    }
                }
            return bestScore;
            }

        else {
            for (int i = 0; i < 9; i++) {

                if(gameState2[i]==2) {
                    gameState2[i]=0;
            int bestScore = Integer.MAX_VALUE;
                    int score = miniMax(gameState2, depth + 1, true);
                    gameState2[i]=2;
                        bestScore = Math.min(score, bestScore);

                }
            }
            return bestScore;
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





    public void resultCheck(){
        for(int[] winPosition: winPos){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2){

                String winner="";
                if(aPlayer == 1){
                    winner= " Player 1";


                }
                else{
                    winner= " Player 2";

                }
                Toast.makeText(this,winner+" Wins!", Toast.LENGTH_SHORT).show();
                activeGame= false;

                Button playButton= (Button) findViewById(R.id.button1);
                playButton.setVisibility(View.VISIBLE);


            }
        }
        if(draw == 9 && activeGame){
            Toast.makeText(this," Draw", Toast.LENGTH_SHORT).show();
            activeGame= false;
            aPlayer=3;
            //playButton.setVisibility(View.VISIBLE);
        }
    }

    /*public int resultCheck2(){
        if(draw == 9 && activeGame){
            activeGame2= false;
            aPlayer2=2;
        }
        for(int[] winPosition: winPos){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2){
                activeGame2= false;

                //Toast.makeText(this,"Loop!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
            }
            return gameState[winPosition[0]];
        }


        return 3;
    }*/
    public int resultCheck2(int[] gamestate){
        if(draw == 9 && activeGame){
            activeGame2= false;
            aPlayer2=2;
        }
        for(int[] winPosition: winPos){
            if(gamestate[winPosition[0]] == gamestate[winPosition[1]] && gamestate[winPosition[1]] == gamestate[winPosition[2]] && gamestate[winPosition[0]] != 2){
                //activeGame2= false;

                //Toast.makeText(this,"Loop!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
            }
            return gameState[winPosition[0]];
        }


        return 3;
    }






    public void playAgain(View view){
        Button playButton= (Button) findViewById(R.id.button1);
        playButton.setVisibility(View.INVISIBLE);
        //int aPlayer=0;

        GridLayout gridLayout1= (GridLayout) findViewById(R.id.gridL);
        for(int i=0; i< gridLayout1.getChildCount();i++){
            ImageView counter1= (ImageView) gridLayout1.getChildAt(i);
            counter1.setImageDrawable(null);
        }

        //gameState={2,2,2,2,2,2,2,2,2};
        //int[][] winPos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        aPlayer=0;
        activeGame=true;
        draw=0;
        //Toast.makeText(this," Pressed!!!!!!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}