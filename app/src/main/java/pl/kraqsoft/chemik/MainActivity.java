package pl.kraqsoft.chemik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import pl.kraqsoft.chemik.Vievs.HighScore;


public class MainActivity extends AppCompatActivity {

    int score = 1337;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setConstants();
        setContentView(new GamePanel(this));
        showResult();
    }

    protected void setConstants(){
        DisplayMetrics ds = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(ds);
        Constants.SCREEN_WIDTH = ds.widthPixels;
        Constants.SCREEN_HEIGHT = ds.heightPixels;
    }

    //TODO: METODA MUSI BYC WYKONANA PO ZAKONCZENIU GRY, W MIEJSCE ZMIENNEJ SCORE DAC AKTUALNY WYNIK PUNKTOWY GRY
    protected void showResult(){
        Intent intent = new Intent(getApplicationContext(), HighScore.class);
        //boolean score;
        intent.putExtra("SCORE", score);
        startActivity(intent);
    }

}

