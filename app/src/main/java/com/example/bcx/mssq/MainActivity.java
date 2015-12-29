package com.example.bcx.mssq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText etname;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void tjyz(View v){
        etname = (EditText) findViewById(R.id.name);
        String name = etname.getText().toString();
        Toast.makeText(MainActivity.this,R.string.app_name,Toast.LENGTH_LONG).show();
        if (!name.isEmpty()) {
            tjiao();
        }

    }

    public void tjiao() {
        textView=(TextView)findViewById(R.id.textview);
        etname=(EditText)findViewById(R.id.name);
        String name=etname.getText().toString();
        String pwd=textView.getText().toString();
        Intent intent=new Intent();
        intent.setClass(MainActivity.this,bcx.class);
        Bundle bundle=new Bundle();
        bundle.putString("name",name);
        bundle.putString("pwe",pwd);
        intent.putExtras(bundle);
        startActivityForResult(intent,0);
    }
    public void login(View v){
        Intent intent=new Intent();
        intent.setClass(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                Bundle bundle = data.getExtras();
                String myName = bundle.getString("name2");
                textView = (TextView) findViewById(R.id.textview);
                textView.setText(myName);
                break;
            default:
                break;
        }
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
}
