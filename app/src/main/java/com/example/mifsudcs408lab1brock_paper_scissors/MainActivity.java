package com.example.mifsudcs408lab1brock_paper_scissors;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int playerScore = 0;
    private int computerScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rockButton = findViewById(R.id.rockButton);
        Button paperButton = findViewById(R.id.paperButton);
        Button scissorsButton = findViewById(R.id.scissorsButton);

        TextView gameInfo = findViewById(R.id.gameInfo);
        TextView gameScore = findViewById(R.id.gameScore);


        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rockPaperScissors("Rock", gameInfo, gameScore);
            }
        });

        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rockPaperScissors("Paper", gameInfo, gameScore);
            }
        });

        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rockPaperScissors("Scissors", gameInfo, gameScore);
            }
        });
    }

    private void rockPaperScissors(String userChoice, TextView gameInfo, TextView gameScore) {
        String[] choices = getResources().getStringArray(R.array.weapons);
        Random random = new Random();
        int computerIndex = random.nextInt(choices.length);
        String computerChoice = choices[computerIndex];

        String result = determineWinner(userChoice, computerChoice);

        gameInfo.setText(getString(R.string.playerChoice) + userChoice + "\n" + getString(R.string.compChoice) + computerChoice + "\n" + getString(R.string.result) + "\t" + result);

        // Update the scores after determining the winner
        updateScoreDisplay(gameScore);
    }

    private void updateScoreDisplay(TextView gameScore) {
        // Update the TextView to display the current scores for player and computer
        gameScore.setText(getString(R.string.playerScore) + playerScore + "\t\t\t" + getString(R.string.computerScore) + computerScore);
    }

    private String determineWinner(String userChoice, String computerChoice) {
        String result = getString(R.string.tie);

        switch (userChoice) {
            case "Rock":
                if (computerChoice.equals("Scissors")) {
                    playerScore++;
                    result = getString(R.string.playerWins) + "\t" + getString(R.string.rockSmashesScissors);
                } else if (computerChoice.equals("Paper")) {
                    computerScore++;
                    result = getString(R.string.compWins) + "\t" + getString(R.string.paperCoversRock);
                }
                break;

            case "Paper":
                if (computerChoice.equals("Rock")) {
                    playerScore++;
                    result = getString(R.string.playerWins) + "\t" + getString(R.string.paperCoversRock);
                } else if (computerChoice.equals("Scissors")) {
                    computerScore++;
                    result = getString(R.string.compWins) + "\t" + getString(R.string.scissorsCutsPaper);
                }
                break;

            case "Scissors":
                if (computerChoice.equals("Paper")) {
                    playerScore++;
                    result = getString(R.string.playerWins) + "\t" + getString(R.string.scissorsCutsPaper);
                } else if (computerChoice.equals("Rock")) {
                    computerScore++;
                    result = getString(R.string.compWins) + "\t" + getString(R.string.rockSmashesScissors);
                }
                break;

            default:
                //result = getString(R.string.tie);
                break;
        }

        return result;
    }
}