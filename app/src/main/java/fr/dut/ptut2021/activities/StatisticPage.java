package fr.dut.ptut2021.activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import fr.dut.ptut2021.R;
import fr.dut.ptut2021.database.CreateDatabase;
import fr.dut.ptut2021.models.database.app.Game;
import fr.dut.ptut2021.models.database.app.User;
import fr.dut.ptut2021.models.database.log.GameResultLog;
import fr.dut.ptut2021.utils.MyVibrator;

public class StatisticPage extends AppCompatActivity implements View.OnClickListener {

    private CreateDatabase db = null;
    private int pageUser = 0;
    private TextView userTitle, barChartTitle, leftStatTitle, rightStatTitle, leftStatText, rightStatText;
    private Spinner gameSpinner;
    private List<User> userList;
    private List<Game> gameList;
    private Button generalButton, lettresButton, chiffresButton;
    private ImageView nextPage, previousPage, leftStatIcon, rightStatIcon;
    private String categoryName = "General";
    private long startWeekTime;
    private final long DAY_MILLIS = 24 * 3600 * 1000;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_page);

        initViews();
        initOnClickViews();
        createDbAndImportUsers();
        setStartWeekTimeAndDate();
        displayNewUserPage();
    }

    private void initOnClickViews() {
        generalButton.setOnClickListener(this);
        lettresButton.setOnClickListener(this);
        chiffresButton.setOnClickListener(this);
        nextPage.setOnClickListener(this);
        previousPage.setOnClickListener(this);
        generalButton.setEnabled(false);
    }

    private void initViews() {
        userTitle = findViewById(R.id.title_StatisticPage);
        generalButton = findViewById(R.id.buttonGeneral_statistics);
        lettresButton = findViewById(R.id.buttonLettres_statistics);
        chiffresButton = findViewById(R.id.buttonChiffres_statistics);
        nextPage = findViewById(R.id.arrow_nextPage);
        previousPage = findViewById(R.id.arrow_previousPage);

        barChartTitle = findViewById(R.id.title_bar_chart);
        leftStatTitle = findViewById(R.id.title_left_stat1);
        rightStatTitle = findViewById(R.id.title_right_stat1);
        leftStatText = findViewById(R.id.text_left_stat1);
        rightStatText = findViewById(R.id.text_right_stat1);
        leftStatIcon = findViewById(R.id.image_left_stat1);
        rightStatIcon = findViewById(R.id.image_right_stat1);

        gameSpinner = findViewById(R.id.spinner_game_stats);
    }

    private void createDbAndImportUsers() {
        db = CreateDatabase.getInstance(StatisticPage.this);
        if (!db.appDao().tabUserIsEmpty()) {
            userList = db.appDao().getAllUsers();
        }
        gameList = db.appDao().getAllGames();
    }

    private void displayNewUserPage() {
        if (!userList.isEmpty()) displayUserTitle();
        setTitleText();
        displayNewCategoryPage();
    }

    private void displayNewCategoryPage() {
        createBarChart(getGameFrequencyData());
        //createLineChart(getGameAvgStarsData());
        setStatsText();
        setSpinnerGames();
    }

    private void displayUserTitle() {
        userTitle.setText(userList.get(pageUser).getUserName());
        verifyPageUserLocation();
    }

    @SuppressLint("SetTextI18n")
    private void setTitleText() {
        barChartTitle.setText("Fréquence de jeu");
        leftStatTitle.setText("Jeu le plus joué");
        rightStatTitle.setText("Jeu le moins joué");
    }

    private void createBarChart(Map<String, Integer> mapData) {
        BarChart barChart = findViewById(R.id.bar_chart);
        List<BarEntry> data = new ArrayList<>();

        String[] days = new String[7];
        int it = 0;
        for (Map.Entry<String, Integer> val : mapData.entrySet()) {
            data.add(new BarEntry(it, val.getValue()));
            days[it] = val.getKey();
            it++;
        }

        BarDataSet barDataSet = new BarDataSet(data, "Data");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setDrawValues(false);

        XAxis axis = barChart.getXAxis();
        axis.setTypeface(Typeface.MONOSPACE);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setTextSize(15f);
        axis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return days[(int) value];
            }
        });

        AxisBase axisBase = barChart.getAxis(YAxis.AxisDependency.LEFT);
        axisBase.setGranularity(1f);
        axisBase.setTextSize(15f);
        axisBase.setAxisMinimum(0f);
        axisBase.setTypeface(Typeface.MONOSPACE);
        barChart.getAxis(YAxis.AxisDependency.RIGHT).setEnabled(false);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);

        barChart.getDescription().setEnabled(false);
        barChart.animateY(500);
        barChart.setTouchEnabled(false);
        barChart.setNoDataText("Commencer a jouer !");
        barChart.getLegend().setEnabled(false);
        barChart.setExtraOffsets(0, 0, 0, 5);
    }
/*
    public void createLineChart(Map<Integer, Float> mapData) {
        LineChart lineChart = findViewById(R.id.line_chart);
        List<Entry> data = new ArrayList<>();

        for (Map.Entry<Integer, Float> val : mapData.entrySet())
            data.add(new Entry(val.getKey(), val.getValue()));

        LineDataSet lineDataSet = new LineDataSet(data, "Data");
        lineDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setLineWidth(3);
        lineDataSet.setCircleRadius(5);
        lineDataSet.setDrawValues(false);

        String[] list = {"10", "20", "30", "40", "50", "60"};

        XAxis axis = lineChart.getXAxis();
        axis.setTypeface(Typeface.MONOSPACE);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setTextSize(15f);
        axis.setAxisMinimum(1);
        axis.setGranularity(1);
        axis.setAxisMaximum(6);
        axis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return list[Math.round(value) - 1];
            }
        });

        AxisBase axisBase = lineChart.getAxis(YAxis.AxisDependency.LEFT);
        axisBase.setGranularity(1f);
        axisBase.setTextSize(15f);
        axisBase.setAxisMinimum(0f);
        axisBase.setAxisMaximum(3f);
        axisBase.setTypeface(Typeface.MONOSPACE);
        lineChart.getAxis(YAxis.AxisDependency.RIGHT).setEnabled(false);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        lineChart.getDescription().setEnabled(false);
        lineChart.animateY(500);
        lineChart.setTouchEnabled(false);
        lineChart.setNoDataText("Tu n'as encore jamais joué !");
        lineChart.getLegend().setEnabled(false);
        lineChart.setExtraOffsets(0, 0, 0, 5);
    }
*/
    @SuppressLint("SetTextI18n")
    private void setStatsText() {
        Map<Game, Integer> gameCountMap = new LinkedHashMap<>();

        if (db.gameLogDao().getAllGameResultLogByUser(userList.get(pageUser).getUserId()).size() > 0) {
            for (int i = 0; i < gameList.size(); i++) {
                if (categoryName.equals("Chiffres") || categoryName.equals("Lettres")) {
                    if (gameList.get(i).getThemeName().equals(categoryName))
                        gameCountMap.put(gameList.get(i), db.gameLogDao().getGameResultLogNbGame(userList.get(pageUser).getUserId(), gameList.get(i).getGameId()));
                } else {
                    gameCountMap.put(gameList.get(i), db.gameLogDao().getGameResultLogNbGame(userList.get(pageUser).getUserId(), gameList.get(i).getGameId()));
                }
            }

            Game minGame = null, maxGame = null;
            int minIt = -1, maxIt = -1;

            for (Map.Entry<Game, Integer> val : gameCountMap.entrySet()) {
                if (val.getValue() < minIt || minIt == -1) {
                    minGame = val.getKey();
                    minIt = val.getValue();
                }
                if (val.getValue() > maxIt || maxIt == -1) {
                    maxGame = val.getKey();
                    maxIt = val.getValue();
                }
            }
            if (minGame != null) {
                leftStatText.setText(maxGame.getGameName());
                leftStatIcon.setImageResource(maxGame.getGameImage());
            }
            if (maxGame != null) {
                rightStatText.setText(minGame.getGameName());
                rightStatIcon.setImageResource(minGame.getGameImage());
            }
        }
        else {
            leftStatText.setText("Aucun");
            leftStatIcon.setImageResource(R.drawable.ic_square);
            rightStatText.setText("Aucun");
            rightStatIcon.setImageResource(R.drawable.ic_square);
        }
    }

    private void setSpinnerGames() {
        if (categoryName.equals("General")) {
            gameSpinner.setVisibility(View.INVISIBLE);
        } else {
            gameSpinner.setVisibility(View.VISIBLE);
            List<String> tmpList = new ArrayList<>();

            for (Game game : gameList) {
                if (game.getThemeName().equals(categoryName))
                    tmpList.add(game.getGameName());
            }

            String[] gameNameTab = new String[tmpList.size()];
            int it = 0;
            for (String s : tmpList) {
                gameNameTab[it] = s;
                it++;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_spinner_item,
                    gameNameTab);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            gameSpinner.setAdapter(adapter);
        }
    }

    private void setSpinnerDifficulty() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setStartWeekTimeAndDate() {
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date currentDate = new Date();

        String str = df.format(currentDate) + " 00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(str, formatter);

        startWeekTime = ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - (6 * DAY_MILLIS);
    }

    private Map<String, Integer> getGameFrequencyData() {
        Map<String, Integer> mapData = new LinkedHashMap<>();
        //La map se mélange si ce n'est pas une LinkedHashMap
        List<GameResultLog> listLog;

        if (categoryName.equals("Chiffres") || categoryName.equals("Lettres"))
            listLog = db.gameLogDao().getAllGameResultLogAfterTimeByTheme(userList.get(pageUser).getUserId(), categoryName, startWeekTime);
        else
            listLog = db.gameLogDao().getAllGameResultLogAfterTime(userList.get(pageUser).getUserId(), startWeekTime);

        DateFormat df = new SimpleDateFormat("E", Locale.FRENCH);
        for (int i = 0; i < 7; i++) {
            mapData.put(df.format(startWeekTime + (DAY_MILLIS * i)).toUpperCase(), 0);
        }

        int nbWeekDay = 0;
        for (int i = 0; i < listLog.size(); i++) {
            long gameTime = listLog.get(i).getEndGameDate();
            for (Map.Entry<String, Integer> val : mapData.entrySet()) {
                if (startWeekTime + (DAY_MILLIS * nbWeekDay) <= gameTime && gameTime < startWeekTime + (DAY_MILLIS * (nbWeekDay + 1))) {
                    mapData.put(val.getKey(), mapData.get(val.getKey()) + 1);
                }
                nbWeekDay++;
            }
            nbWeekDay = 0;
        }
        return mapData;
    }

    private Map<Integer, Float> getGameAvgStarsData() {
        Map<Integer, Float> mapData = new TreeMap<>();
        List<GameResultLog> listLog;

        if (categoryName.equals("Chiffres") || categoryName.equals("Lettres"))
            listLog = db.gameLogDao().getAllGameResultLogByUserLimitByTheme(userList.get(pageUser).getUserId(), categoryName);
        else
            listLog = db.gameLogDao().getAllGameResultLogByUserLimit(userList.get(pageUser).getUserId());

        final int COLUMN = 6;
        int it = 0;
        for (int i = 0; i < COLUMN; i++) {
            float n = 0;
            float sum = 0;
            while ((it + 1) % 10 != 0 && listLog.size() > it) {
                n++;
                sum += listLog.get(it).getStars();
                it++;
            }
            float avg;
            if (n == 0)
                avg = 0;
            else
                avg = sum/n;

            mapData.put(COLUMN - i, avg);
            it++;
        }

        return mapData;
    }
    
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void verifyPageUserLocation() {
        previousPage.setAlpha(1f);
        nextPage.setAlpha(1f);
        previousPage.setEnabled(true);
        nextPage.setEnabled(true);

        if (pageUser == 0) {
            previousPage.setAlpha(0.5f);
            previousPage.setEnabled(false);
        }
        if (pageUser == userList.size() - 1) {
            nextPage.setAlpha(0.5f);
            nextPage.setEnabled(false);
        }
    }

    public void lockButton(Button button) {
        generalButton.setEnabled(true);
        lettresButton.setEnabled(true);
        chiffresButton.setEnabled(true);
        button.setEnabled(false);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonGeneral_statistics:
                lockButton(generalButton);
                categoryName = "General";
                displayNewCategoryPage();
                break;
            case R.id.buttonChiffres_statistics:
                lockButton(chiffresButton);
                categoryName = "Chiffres";
                displayNewCategoryPage();
                break;
            case R.id.buttonLettres_statistics:
                lockButton(lettresButton);
                categoryName = "Lettres";
                displayNewCategoryPage();
                break;

            case R.id.arrow_nextPage:
                if (pageUser < userList.size() - 1) {
                    MyVibrator.vibrate(StatisticPage.this, 35);
                    pageUser++;
                }
                displayNewUserPage();
                break;
            case R.id.arrow_previousPage:
                if (0 < pageUser) {
                    MyVibrator.vibrate(StatisticPage.this, 35);
                    pageUser--;
                }
                displayNewUserPage();
                break;
            default:
                break;
        }
    }
}