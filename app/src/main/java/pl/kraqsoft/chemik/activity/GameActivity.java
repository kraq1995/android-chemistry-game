package pl.kraqsoft.chemik.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import pl.kraqsoft.chemik.Constants;
import pl.kraqsoft.chemik.GamePanel;
import pl.kraqsoft.chemik.activity.HighScoreActivity;
import pl.kraqsoft.chemik.activity.MainMenuActivity;


public class GameActivity extends AppCompatActivity {


    int score = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        SetConstants();
        //ShowMenu();
        setContentView(new GamePanel(this));
        // showResult();
    }

    protected void SetConstants() {
        DisplayMetrics ds = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(ds);
        Constants.SCREEN_WIDTH = ds.widthPixels;
        Constants.SCREEN_HEIGHT = ds.heightPixels;
    }

    //TODO: METODA MUSI BYC WYKONANA PO ZAKONCZENIU GRY, W MIEJSCE ZMIENNEJ SCORE DAC AKTUALNY WYNIK PUNKTOWY GRY
    protected void showResult() {
        Intent intent = new Intent(getApplicationContext(), HighScoreActivity.class);
        //boolean score;
        intent.putExtra("SCORE", score);
        startActivity(intent);
    }

    protected void ShowMenu() {
        Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
        startActivity(intent);
    }

    protected void GameLogic() {

    }

}

