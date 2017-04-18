package clickgame.bau.com.clickgame;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import static clickgame.bau.com.clickgame.GameActivity.MARCOS_SHARED_PREFERENCES;

public class LeaderBoard extends AppCompatActivity {
    private Context mContext;
//last score
    private TextView tvLastScore;
    String lastScore;
//user name
    String userName;
    String userTime;

//shared preferences
    private TextView tvTop1;
    private TextView tvTop2;
    private TextView tvTop3;
    private TextView tvTop4;
    private TextView tvTop5;
    String top1string;
    String top2string;
    String top3string;
    String top4string;
    String top5string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        Intent i = getIntent();
        lastScore = i.getStringExtra("score");
        tvLastScore = (TextView) findViewById(R.id.tv_user_last_score);
        userName = i.getStringExtra("name");
        userTime = i.getStringExtra("time");
        tvLastScore.setText
                (userName + " " + getString(R.string.str_your_last_score)+ " "+lastScore);

        //shared preferences
        tvTop1 = (TextView) findViewById(R.id.tv_leaderboard_top1);
        tvTop2 = (TextView) findViewById(R.id.tv_leaderboard_top2);
        tvTop3 = (TextView) findViewById(R.id.tv_leaderboard_top3);
        tvTop4 = (TextView) findViewById(R.id.tv_leaderboard_top4);
        tvTop5 = (TextView) findViewById(R.id.tv_leaderboard_top5);

        SharedPreferences prefs = getSharedPreferences
                (GameActivity.MARCOS_SHARED_PREFERENCES, MODE_PRIVATE);

        top1string = prefs.getString(userTime + "_top1", "Robot: 0");
        top2string = prefs.getString(userTime + "_top2", "Robot: 0");
        top3string = prefs.getString(userTime + "_top3", "Robot: 0");
        top4string = prefs.getString(userTime + "_top4", "Robot: 0");
        top5string = prefs.getString(userTime + "_top5", "Robot: 0");

        tvTop1.setText(top1string);
        tvTop2.setText(top2string);
        tvTop3.setText(top3string);
        tvTop4.setText(top4string);
        tvTop5.setText(top5string);
    }

    /***********************************************************************************************
     * Method to reset tops
     * @param view
     **********************************************************************************************/
    public void resetTop(View view){
        SharedPreferences.Editor editor =
                getSharedPreferences(MARCOS_SHARED_PREFERENCES, MODE_PRIVATE).edit();
        editor.remove(userTime + "_top1");
        editor.remove(userTime + "_top2");
        editor.remove(userTime + "_top3");
        editor.remove(userTime + "_top4");
        editor.remove(userTime + "_top5");
        editor.apply();

        tvTop1.setText(getString(R.string.str_robot_message));
        tvTop2.setText(getString(R.string.str_robot_message));
        tvTop3.setText(getString(R.string.str_robot_message));
        tvTop4.setText(getString(R.string.str_robot_message));
        tvTop5.setText(getString(R.string.str_robot_message));
    }

    }








