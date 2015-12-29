package com.bcx.Test.Activity;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bcx.Test.model.TrueFalse;

public class MainActivity extends Activity {
    private TextView mtextView;
    private Button mtrue_button;
    private Button mfalse_button;
    private Button mup_button;
    private Button mnext_button;
    private TrueFalse[] mQuestionbook=new TrueFalse[]{
            new TrueFalse(R.string.question_one,true),
            new TrueFalse(R.string.question_two,true),
            new TrueFalse(R.string.question_three,false),
            new TrueFalse(R.string.question_four,true),
    };
    private int mCurrentindex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtextView=(TextView)findViewById(R.id.textView);
        Log.d("MainActivity", "doenwon");
        mtextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentindex = (mCurrentindex + 1) % mQuestionbook.length;
                updateQuestion();
            }
        });
        updateQuestion();
        mtrue_button=(Button)findViewById(R.id.true_button);
        mtrue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mfalse_button=(Button)findViewById(R.id.false_button);
        mfalse_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mnext_button=(Button)findViewById(R.id.next_button);
        mnext_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentindex=(mCurrentindex+1)%mQuestionbook.length;
                updateQuestion();
            }
        });
        mup_button=(Button)findViewById(R.id.upquestion_button);
        mup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upquestion();
                updateQuestion();
            }
        });
    }




    public void updateQuestion(){
        int question=mQuestionbook[mCurrentindex].getQuestion();
        mtextView.setText(question);
    }
    public void upquestion(){
        mCurrentindex=(mCurrentindex+mQuestionbook.length-1)%mQuestionbook.length;
    }

    public void checkAnswer(boolean Answer){
        boolean manswer=mQuestionbook[mCurrentindex].isTrueQuestion();
        System.out.println("你的答案为："+Answer); 
        System.out.println("正确答案为："+manswer);
        int messageid=0;
        if(Answer&&manswer){
            messageid=R.string.true_button;
        }
        else{
            messageid=R.string.false_button;
        }
        Toast.makeText(MainActivity.this,messageid,Toast.LENGTH_SHORT).show();
    }
}
