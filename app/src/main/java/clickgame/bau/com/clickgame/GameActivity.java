package clickgame.bau.com.clickgame;

import android.content.Context;
import android.content.Intent;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent i = getIntent();
        userTime = i.getStringExtra("sec");
        getUserSeconds(userTime);
        show = (TextView) findViewById(R.id.tv_seconds);
        timer = new CountDownT(userSecondsNumber*1000,1000);
        show.setText(String.valueOf(userSecondsNumber));
        start();
        clicks = 0;
        push = (Button) findViewById(R.id.btn_push);
        tvUserCount = (TextView) findViewById(R.id.tv_counter);

        tvUserCount.setText("0");



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



}





