package com.example.bcx.mssq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by bcx on 2015/12/27.
 */
public class bcx extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private Intent intent;
    private Bundle bundle;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bcx);
        System.out.println("bcx调用成功！");
        textView=(TextView)findViewById(R.id.textview);
        editText=(EditText)findViewById(R.id.name2);
        intent=this.getIntent();
        bundle=intent.getExtras();
        String name=bundle.getString("name");
        textView.setText(name);
    }

    public void rebacktoone(View v){
        editText=(EditText)findViewById(R.id.name2);
        String text=editText.getText().toString();

        if(!text.isEmpty()){
            reback(text);
        }
    }
    public void reback(String te){
        Bundle bundle=new Bundle();
        bundle.putString("name2",te);
        this.getIntent().putExtras(bundle);
        bcx.this.setResult(RESULT_OK, this.getIntent());
        bcx.this.finish();
    }
}
