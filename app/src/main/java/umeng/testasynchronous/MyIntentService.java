package umeng.testasynchronous;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by wangfei on 17/1/19.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intentresult = new Intent(IntentServiceActivity.RESULT);

        sendBroadcast(intentresult);
    }
    public void onDestroy()
    {
        super.onDestroy();
    }
}
