package umeng.testasynchronous;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view){
        switch (view.getId()){
           case  R.id.main_thread:
               Intent intent_thread = new Intent(MainActivity.this,ThreadActivity.class);
               startActivity(intent_thread);
            break;
            case  R.id.main_handlerthread:
                Intent intent_handlerthread = new Intent(MainActivity.this,HandlerThreadActivity.class);
                startActivity(intent_handlerthread);
                break;


            case  R.id.main_asynctask:
                Intent intent_asynctask = new Intent(MainActivity.this,AsyncTaskActivity.class);
                startActivity(intent_asynctask);
                break;

            case  R.id.main_intentservice:
                Intent intent_intentservice = new Intent(MainActivity.this,IntentServiceActivity.class);
                startActivity(intent_intentservice);
                break;

        }
    }
}
