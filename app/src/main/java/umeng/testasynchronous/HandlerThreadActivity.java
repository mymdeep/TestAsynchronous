package umeng.testasynchronous;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;

/**
 * Created by wangfei on 17/1/15.
 */
public class HandlerThreadActivity extends AppCompatActivity{
    private Button btnStart;
    private Handler backHandler;
    private Handler mainHandler;
    private HandlerThread handlerThread;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        btnStart = (Button) findViewById(R.id.thread_btn);
        handlerThread = new HandlerThread("mythread");
        handlerThread.start();
        backHandler = new Handler(handlerThread.getLooper());
        mainHandler = new Handler(getMainLooper());
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnStart.setText("等待中");
                backHandler.post(new myBackRunnable());
            }
        });

    }
    private class myBackRunnable implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mainHandler.post(new myUIRunnable());
        }
    }
    private class myUIRunnable implements Runnable{

        @Override
        public void run() {
            btnStart.setText("线程结束");
        }
    }
}
