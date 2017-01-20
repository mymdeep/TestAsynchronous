package umeng.testasynchronous;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by wangfei on 17/1/15.
 */
public class AsyncTaskActivity extends Activity{
    private Button btnStart;
    private MyAsyncTask myAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        btnStart = (Button) findViewById(R.id.thread_btn);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               myAsyncTask = new MyAsyncTask(btnStart);
                myAsyncTask.execute(1000);

            }
        });

    }
    public class MyAsyncTask extends AsyncTask<Integer, String, String> {
        private Button btn;
        public MyAsyncTask(Button btn) {
            super();
            this.btn = btn;
        }
        /**
         * 这里的Integer参数对应AsyncTask中的第一个参数
         * 这里的String返回值对应AsyncTask的第三个参数
         * 该方法并不运行在UI线程当中，主要用于异步操作，所有在该方法中不能对UI当中的空间进行设置和修改
         * 但是可以调用publishProgress方法触发onProgressUpdate对UI进行操作
         */
        @Override
        protected String doInBackground(Integer... integers) {
            Log.e("xxxxxx","xxxxxxexecute传入参数="+integers[0]);
            try {
                Thread.sleep(1000);
                publishProgress("过了一秒");
                Thread.sleep(1000);
                publishProgress("过了两秒");
                Thread.sleep(1000);
                publishProgress("过了三秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "doInBackground的返回";
        }
        /**
         * 这里的String参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）
         * 在doInBackground方法执行结束之后在运行，并且运行在UI线程当中 可以对UI空间进行设置
         */
        @Override
        protected void onPostExecute(String result) {
            btn.setText("线程结束" + result);
        }
        //该方法运行在UI线程当中,并且运行在UI线程当中 可以对UI空间进行设置
        @Override
        protected void onPreExecute() {
            btn.setText("开始执行异步线程");
        }
        /**
         * 这里的Intege参数对应AsyncTask中的第二个参数
         * 在doInBackground方法当中，，每次调用publishProgress方法都会触发onProgressUpdate执行
         * onProgressUpdate是在UI线程中执行，所有可以对UI空间进行操作
         */
        @Override
        protected void onProgressUpdate(String... values) {

            String vlaue = values[0]+"";
            Log.e("xxxxxx","xxxxxx vlaue="+vlaue);
            btn.setText(vlaue+"");


        }
    }
}
