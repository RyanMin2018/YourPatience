package kr.co.sology.yourpatience;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import kr.co.sology.yourpatience.service.WakenReceiver;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = this;

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        registerBroadcastReceiver();

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

    private void registerBroadcastReceiver() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        getApplicationContext().registerReceiver(new WakenReceiver(), intentFilter);
    }


    public static MainActivity getInstance() {
        return instance;
    }

    public void update(long start, long end, long diff) {
        String msg = TimeUtil.getDateTime(start) + " ~ " + TimeUtil.getDateTime(end);
        String[] duration  = TimeUtil.getDiff(diff).split(":");
        String strDuration = (duration[0].equals("0")) ? "" : duration[0] + "일 ";
        strDuration += (duration[1].equals("0")) ? "" : duration[1] + "시간 ";
        strDuration += (duration[2].equals("0")) ? "" : duration[2] + "분 ";
        strDuration += (duration[3].equals("0")) ? "" : duration[3] + "초";


        TextView tv = findViewById(R.id.textview);
        tv.setText(msg + " " + strDuration);
    }
}
