package com.bcx.Test.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class CheatActivity extends Activity {
    private Button infoAnswer;
    private TextView AnswerText;
    private Button rebackfirst;
    public static final String ANSWER_IS_TRUE="com.bcx.Text.answer_is_true";
    public static final String ANSWER_SHOW="com.bcx.Text.answer_show";
    private boolean answeristrue;

    private void setAnswerShowResult(boolean Isanswer){
        Intent date=new Intent();
        date.putExtra(ANSWER_SHOW,Isanswer);
        setResult(RESULT_OK,date);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        AnswerText=(TextView)findViewById(R.id.Answertext);
        infoAnswer=(Button)findViewById(R.id.infoAnswe);
        setAnswerShowResult(false);
        infoAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start info answer
                answeristrue=getIntent().getBooleanExtra(ANSWER_IS_TRUE,false);

                if (answeristrue){
                    AnswerText.setText(R.string.true_button);
                }else {
                    AnswerText.setText(R.string.false_button);
                }
                setAnswerShowResult(true);
            }
        });
    }
}
