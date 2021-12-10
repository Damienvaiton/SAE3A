package fr.dut.ptut2021.game;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import fr.dut.ptut2021.R;
import fr.dut.ptut2021.activities.ResultGamePage;
import fr.dut.ptut2021.database.CreateDatabase;
import fr.dut.ptut2021.models.stats.GameLog;
import fr.dut.ptut2021.models.stats.game.PlayWithSoundData;
import fr.dut.ptut2021.models.stats.game.WordWithHoleData;

public class PlayWithSound extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    private CreateDatabase db;
    private ImageView btnSound;
    private TextView goodAnswer;
    private boolean delay = false;
    private List<String> listAnswer;
    private List<Integer> listChooseResult;
    private TextToSpeech textToSpeech;
    private List<PlayWithSoundData> listData;
    private Button answer1, answer2, answer3;
    private String goodAnswerString, themeName;
    private MediaPlayer mpGoodAnswer, mpWrongAnswer;
    private final Button[] listButton = new Button[3];
    private final int MAX_GAME_PLAYED = 5;
    private int userId, gamePlayed = 1, nbTry = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_with_sound);

        getSharedPref();
        initDatabase();
        initializeLayout();
        initGame(themeName, 1);
        initSoundEffect();
        addOnClickListener();

        textToSpeech = new TextToSpeech(this, this);
    }

    private void getSharedPref() {
        SharedPreferences settings = getSharedPreferences("MyPref", 0);
        userId = settings.getInt("userId", 0);
        themeName = settings.getString("themeName", "");
    }

    private void initDatabase() {
        db = CreateDatabase.getInstance(PlayWithSound.this);

        String[] alphabetList = getResources().getStringArray(R.array.alphabet);
        for (String letter : alphabetList) {
            db.gameDao().insertPWSData(new PlayWithSoundData(db.gameDao().getPWSMaxId(), userId, letter, "Lettres", 1, 0));
        }

        String[] syllableList = getResources().getStringArray(R.array.syllable);
        for (String syllable : syllableList) {
            db.gameDao().insertPWSData(new PlayWithSoundData(db.gameDao().getPWSMaxId(), userId, syllable, "Lettres", 2, 0));
        }

        for (int i = 1; i < 10; i++) {
            db.gameDao().insertPWSData(new PlayWithSoundData(db.gameDao().getPWSMaxId(), userId, Integer.toString(i), "Chiffres", 1, 0));
        }
    }

    private void initializeLayout() {
        goodAnswer = findViewById(R.id.goodAnswer_playWithSound);
        btnSound = findViewById(R.id.btnSound_playWithSound);
        answer1 = findViewById(R.id.buttonAnswer1_playWithSound);
        answer2 = findViewById(R.id.buttonAnswer2_playWithSound);
        answer3 = findViewById(R.id.buttonAnswer3_playWithSound);
        listButton[0] = answer1;
        listButton[1] = answer2;
        listButton[2] = answer3;
    }

    private void initGame(String theme, int difficulty) {
        /*
        listData = db.gameDao().getAllPWSData(userId, theme, difficulty);
        indWordChoose = (int) (Math.random() * listData.size());
        goodAnswerString = listData.get(indWordChoose).getResult();
         */
        fillListChooseResult(theme);
        initListAnswer();
        setLayoutContent();
        new Handler().postDelayed(this::readTheAnswer, 1200);
    }

    //TODO A adapter en fonction de la difficulté
    private void fillListChooseResult(String theme) {
        listData = new ArrayList<>();
        listData.addAll(db.gameDao().getAllPWSDataByTheme(userId, theme));
        listChooseResult = new ArrayList<>();
        List<Integer> listDataNotUsed = db.gameDao().getAllPWSDataLastUsed(listData, false);

        //Remplit les listChooseResult avec les indices (de listData) des Result avec un lastUsed = false
        if (listDataNotUsed.size() > MAX_GAME_PLAYED) {
            while (listChooseResult.size() < MAX_GAME_PLAYED) {
                int rand = (int) (Math.random() * listDataNotUsed.size());
                if (!listChooseResult.contains(listDataNotUsed.get(rand))) {
                    listChooseResult.add(listDataNotUsed.get(rand));
                }
            }
        } else {
            List<Integer> listDataUsed = db.gameDao().getAllPWSDataLastUsed(listData, true);
            listChooseResult.addAll(listDataNotUsed);
            for (int i = listChooseResult.size(); i < MAX_GAME_PLAYED; i++) {
                int rand = (int) (Math.random() * listDataUsed.size());
                if (!listChooseResult.contains(listDataUsed.get(rand))) {
                    listChooseResult.add(listDataUsed.get(rand));
                }
            }
        }
        //Erreur si il n'y a pas assez de données dans la database

        db.gameDao().updateAllPWSDataLastUsed(userId);
    }

    private void readTheAnswer() {
        if (themeName.equals("Lettres"))
            speak("Trouve la lettre : " + goodAnswerString);
        else if (themeName.equals("Chiffres"))
            speak("Trouve le chiffre : " + goodAnswerString);
    }

    private void initListAnswer() {
        listAnswer = new ArrayList<>();

        listAnswer.add(goodAnswerString);
        while (listAnswer.size() < 3) {
            int rand = (int) (Math.random() * listData.size());
            if (!listAnswer.contains(listData.get(rand).getResult()))
                listAnswer.add(listData.get(rand).getResult());
        }

        for (int i = 0; i < 4; i++) {
            Collections.shuffle(listAnswer);
        }
    }

    private void setLayoutContent() {
        goodAnswer.setVisibility(View.INVISIBLE);
        goodAnswer.setText(listData.get(listChooseResult.get(gamePlayed - 1)).getResult());
        for (int i = 0; i < 3; i++)
            listButton[i].setText(listAnswer.get(i));
    }

    private void initSoundEffect() {
        mpGoodAnswer = MediaPlayer.create(this, R.raw.correct_answer);
        mpWrongAnswer = MediaPlayer.create(this, R.raw.wrong_answer);
    }

    private void addOnClickListener() {
        btnSound.setOnClickListener(this);
        for (int i = 0; i < 3; i++)
            listButton[i].setOnClickListener(this);
    }

    private void playSound(boolean isGoodAnswer) {
        if (isGoodAnswer)
            mpGoodAnswer.start();
        else
            mpWrongAnswer.start();
    }

    private void setWordAndAddDelay(int indWrongAnswer) {
        delay = true;
        playSound(false);

        new Handler().postDelayed(() -> {
            delay = false;
            readTheAnswer();
        }, 2000);

        nbTry++;
        int MAX_TRY = 2;
        if (nbTry >= MAX_TRY) {
            delay = true;
            new Handler().postDelayed(() -> {
                for (int i = 0; i < 3; i++) {
                    if (listButton[i].getText().equals(listData.get(listChooseResult.get(gamePlayed - 1)).getResult()))
                        listButton[i].setBackgroundColor(Color.GREEN);
                }
                replay();
            }, 2000);
        }
    }

    private void displayAnswer(boolean showAnswer){
        if(showAnswer){
            btnSound.setVisibility(View.INVISIBLE);
            goodAnswer.setVisibility(View.VISIBLE);
        }else{
            btnSound.setVisibility(View.VISIBLE);
            goodAnswer.setVisibility(View.INVISIBLE);
        }
    }

    private void replay() {
        playSound(true);
        gamePlayed++;
        delay = true;
        displayAnswer(true);
        new Handler().postDelayed(() -> {
            delay = false;
            if (gamePlayed <= MAX_GAME_PLAYED) {
                nbTry = 0;
                displayAnswer(false);
                initGame(themeName, 1);
                for (int i = 0; i < 3; i++)
                    listButton[i].setBackgroundColor(Color.parseColor("#00BCD4"));
            } else {
                Intent intent = new Intent(getApplicationContext(), ResultGamePage.class);
                intent.putExtra("starsNumber", 3);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    private void speak(String texte) {
        HashMap<String, String> onlineSpeech = new HashMap<>();
        onlineSpeech.put(TextToSpeech.Engine.KEY_FEATURE_NETWORK_SYNTHESIS, "true");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            textToSpeech.speak(texte, TextToSpeech.QUEUE_FLUSH, null, null);
        else
            textToSpeech.speak(texte, TextToSpeech.QUEUE_FLUSH, onlineSpeech);
    }

    //TODO remplir les tables PlayWithSound et GameLog
    private void updateDataInDb() {
/*
        boolean win = false;
        if (nbTry == 0) win = true;
        GameLog gameLog = new GameLog(
                "WordWithHole",
                data.getDataId(),
                win,
                nbTry);
        db.gameLogDao().insertGameLog(gameLog);
*/
    }

    private void verifyAnswer(Button answer, int nbAnswer) {
        if (answer.getText() == goodAnswerString) {
            answer.setBackgroundColor(Color.GREEN);
            replay();
        } else {
            answer.setBackgroundColor(Color.RED);
            setWordAndAddDelay(nbAnswer);
        }
    }

    @Override
    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS)
            textToSpeech.setLanguage(Locale.FRANCE);
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View v) {
        if (!delay) {
            switch (v.getId()) {
                case R.id.buttonAnswer1_playWithSound:
                    verifyAnswer(answer1, 1);
                    break;

                case R.id.buttonAnswer2_playWithSound:
                    verifyAnswer(answer2, 2);
                    break;

                case R.id.buttonAnswer3_playWithSound:
                    verifyAnswer(answer3, 3);
                    break;

                case R.id.btnSound_playWithSound:
                    readTheAnswer();
                    break;
            }
        }
    }
}