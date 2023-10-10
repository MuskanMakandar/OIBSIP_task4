package com.example.myquizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    TextView totalQuestionTextView;
    TextView questionTextView;
    Button ansA,ansB,ansC,ansD;
    Button submitBtn;

    int score=0;
    int totalQuestion=QuestionAnswer.question.length;
    int currentQuestionIndex=0;
    String selectedAnswer ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionTextView = findViewById(R.id.total_question);
        questionTextView=findViewById(R.id.Question);
        ansA = findViewById(R.id.ans_A);
        ansB=findViewById(R.id.ans_B);
        ansC=findViewById(R.id.ans_C);
        ansD=findViewById(R.id.ans_D);
        submitBtn=findViewById(R.id.submit_btn);
        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);


        totalQuestionTextView.setText("Total Questions: "+totalQuestion);
        loadNewQuestion();
    }
    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor((Color.WHITE));
        ansB.setBackgroundColor((Color.WHITE));
        ansC.setBackgroundColor((Color.WHITE));
        ansD.setBackgroundColor((Color.WHITE));

        Button clickedButton=(Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();
        }
        else {
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }

    private void loadNewQuestion() {

        if(currentQuestionIndex==totalQuestion){
            finishQuize();
            return;
        }
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choice[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choice[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choice[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choice[currentQuestionIndex][3]);

    }

    private void finishQuize() {

        String passStatus="";
        if(score>totalQuestion+0.60){
            passStatus="Passed";
        }
        else{
            passStatus="Passed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+"out of "+ totalQuestion)
                .setPositiveButton("Restart",((dialogInterface, i) -> restartQuiz()))
                .show();
    }

    private Object restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();

        return null;
    }

}




