package clickgame.bau.com.clickgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private Context mContext;
    private String userTime;
    private TextView tvUserCount;
    private int userSecondsNumber;
    TextView show;
    CountDownT timer;
    public int clicks;
    private Button push;
    private String userName;


    //shared prefs
    public static final String MARCOS_SHARED_PREFERENCES = "MarcosSharedPreferences";
    //topscores
    private int top1;
    private int top2;
    private int top3;
    private int top4;
    private int top5;
    //Top scores
    String top1string;
    String top2string;
    String top3string;
    String top4string;
    String top5string;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mContext = this;
        Intent i = getIntent();
        userTime = i.getStringExtra("sec");
        getUserSeconds(userTime);
        userName = i.getStringExtra("name");
        show = (TextView) findViewById(R.id.tv_seconds);
        timer = new CountDownT(userSecondsNumber*1000,1000);
        show.setText(String.valueOf(userSecondsNumber));
        start();
        clicks = 0;
        push = (Button) findViewById(R.id.btn_push);
        tvUserCount = (TextView) findViewById(R.id.tv_counter);
        tvUserCount.setText("0");


        //share prefs
        SharedPreferences prefs = getSharedPreferences(MARCOS_SHARED_PREFERENCES, MODE_PRIVATE);

        top1string = prefs.getString("top1", "Robot: 0");
        top2string = prefs.getString("top2", "Robot: 0");
        top3string = prefs.getString("top3", "Robot: 0");
        top4string = prefs.getString("top4", "Robot: 0");
        top5string = prefs.getString("top5", "Robot: 0");

        String[] parts1 = top1string.split(": ");
        String[] parts2 = top2string.split(": ");
        String[] parts3 = top3string.split(": ");
        String[] parts4 = top4string.split(": ");
        String[] parts5 = top5string.split(": ");

        top1 = Integer.parseInt(parts1[1]);
        top2 = Integer.parseInt(parts2[1]);
        top3 = Integer.parseInt(parts3[1]);
        top4 = Integer.parseInt(parts4[1]);
        top5 = Integer.parseInt(parts5[1]);



    }

    /***********************************************************************************************
     * Method to star timer
     **********************************************************************************************/
    public void start (){
        timer.start();
    }

//    /***********************************************************************************************
//     * Method to stop timer
//     **********************************************************************************************/
//    public void stop() {
//        timer.cancel();
//    }

//    /***********************************************************************************************
//     * Method pause timer
//     **********************************************************************************************/
//    public void pause(){
//        startPauseTime = System.currentTimeMillis();
//    }

//    /***********************************************************************************************
//     * Method resume timer
//     **********************************************************************************************/
//    public void resumen(){
//        pauseTime +=  System.currentTimeMillis()-startPauseTime;
//    }

    /***********************************************************************************************
     * class timer
     **********************************************************************************************/
    public class CountDownT extends CountDownTimer {
        public CountDownT (long InMilliSeconds, long TimeGap){
            super(InMilliSeconds,TimeGap);
        }

        /*******************************************************************************************
         * Method to show the time
         * @param InMilliSeconds
         ******************************************************************************************/
        @Override
        public void onTick(long InMilliSeconds) {

            show.setText((InMilliSeconds/1000 + ""));
        }

        /*******************************************************************************************
         * Method to end countDown
         *******************************************************************************************/
        @Override
        public void onFinish() {
            show.setText("end");
            push.setEnabled(false);
            Intent i = new Intent(mContext, LeaderBoard.class);
            String tagScore = "score";
            i.putExtra(tagScore, tvUserCount.getText().toString());
            String tagName = "name";
            i.putExtra(tagName, userName);
            startActivity(i);
            refreshTop();
            //finishSplashScreenActivity()

        }
    }
    /***********************************************************************************************
     * Method to get the seconds user
     **********************************************************************************************/
    private void getUserSeconds (String userSeconds){
        String seconds = userSeconds.substring(0,2);
        userSecondsNumber = Integer.parseInt(seconds);
    }


    /***********************************************************************************************
     * Method to count the push button
     **********************************************************************************************/
    public void countPushButton(View view){

        clicks ++;
        tvUserCount.setText(""+clicks);



    }




//    private void finishSplashScreenActivity() {
//        // Move on to the next activity in SPLASH_TIME_OUT seconds
//        new Handler().postDelayed(new Runnable() {
//            /**
//             *  This code runs after the timer has finished.
//             *  It reads the auth token from shared preferences and performs the autologin request
//             *  on the server. If both auth tokens match, we go to mainactivity. Otherwise we go to
//             *  Login Activity.
//             */
//            @Override
//            public void run() {
//                ServerCommunication.startAutoLogin(mContext, authToken);
//            }
//        }, SPLASH_TIME_OUT);
//    }







    public void refreshTop(){

        SharedPreferences.Editor editor =
                getSharedPreferences(MARCOS_SHARED_PREFERENCES, MODE_PRIVATE).edit();

        if(clicks > top1){
            top5 = top4;
            top4 = top3;
            top3 = top2;
            top2 = top1;
            top1 = clicks;
            editor.putString("top1",userName + ": " + String.valueOf(top1));
            editor.putString("top2",top1string);
            editor.putString("top3",top2string);
            editor.putString("top4",top3string);
            editor.putString("top5",top4string);
            top5string = top4string;
            top4string = top5string;
            top3string = top2string;
            top2string = top1string;
            top1string = userName + ": " + String.valueOf(top1);

        }else if(clicks > top2){
            top5 = top4;
            top4 = top3;
            top3 = top2;
            top2 = clicks;
            editor.putString("top2",userName + ": " + String.valueOf(top2));
            editor.putString("top3",top2string);
            editor.putString("top4",top3string);
            editor.putString("top5",top4string);
            top5string = top4string;
            top4string = top5string;
            top3string = top2string;
            top2string = userName + ": " + String.valueOf(top2);

        }else if (clicks > top3){
            top5 = top4;
            top4 = top3;
            top3 = clicks;
            editor.putString("top3",userName + ": " + String.valueOf(top3));
            editor.putString("top4",top3string);
            editor.putString("top5",top4string);
            top5string = top4string;
            top4string = top5string;
            top3string = userName + ": " + String.valueOf(top3);

        }else if (clicks > top4){
            top5 = top4;
            top4 = clicks;
            editor.putString("top4",userName + ": " + String.valueOf(top4));
            editor.putString("top5",top4string);
            top5string = top4string;
            top4string = userName + ": " + String.valueOf(top4);

        }else if (clicks > top5) {
            top5 = clicks;
            editor.putString("top5",userName + ": " + String.valueOf(top5));
            top5string = userName + ": " + String.valueOf(top5);
        }
        editor.apply();
    }










}





