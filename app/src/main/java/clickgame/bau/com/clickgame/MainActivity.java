package clickgame.bau.com.clickgame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private TextView appName;
    Spinner spTime;
    private TextView userTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initApp();
    }

    /**
     * Method init app
     */
    private void initApp() {
        mContext = this;
        this.spTime = (Spinner) findViewById(R.id.sp_time);
        loadUserSeconds();

    }

    /***********************************************************************************************
     * Method spinner
     **********************************************************************************************/
    private void loadUserSeconds(){
        final ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource
                        (this, R.array.seconds, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spTime.setAdapter(adapter);
        this.spTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getId()){
                    case R.id.sp_time:
                        String[]  arraySeconds = getResources().getStringArray(R.array.seconds);
                        ArrayAdapter<CharSequence> adapter =
                                new ArrayAdapter<CharSequence>
                                        (mContext, android.R.layout.simple_spinner_item,
                                                android.R.id.text1, arraySeconds);
                        adapter.setDropDownViewResource
                                (android.R.layout.simple_spinner_dropdown_item);

                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    /**
     * method oneclick
     */
    public void pushClick(View view){
        Intent i = new Intent(mContext, GameActivity.class);
        String tagSeconds = "sec";
        i.putExtra(tagSeconds, (String) spTime.getSelectedItem());
        startActivity(i);

    }

}
