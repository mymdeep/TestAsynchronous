package umeng.testasynchronous;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by wangfei on 17/1/15.
 */
public class IntentServiceActivity extends Activity{
    private Button btnStart;
    public static final String RESULT = "deep.result";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        btnStart = (Button) findViewById(R.id.thread_btn);
        IntentFilter filter = new IntentFilter();
        filter.addAction(RESULT);
        registerReceiver(myReceiver, filter);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStart.setText("等待中");
                Intent intent=new Intent(IntentServiceActivity.this,MyIntentService.class);
                startService(intent);
            }
        });
    }
    private BroadcastReceiver myReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if (intent.getAction() == RESULT){

                btnStart.setText("线程结束");
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
