package pl.kraqsoft.chemik;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import pl.kraqsoft.chemik.activity.HighScoreActivity;

/**
 * Created by krakus on 11/5/2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private ArrayList<Atom> atomArrayList = new ArrayList<>();

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        populateAtomArrayList();
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    private void populateAtomArrayList() {
        Random rand = new Random();
        atomArrayList.clear();
        ArrayList<String> pattern = new ArrayList<String>(Arrays.asList(Constants.getRandomPattern()));

        for (int i = 0; i < 3; i++) {
            pattern.add(Constants.getRandomAtom());
        }

        for (int i = 0; i < pattern.size(); i++) {
            atomArrayList.add(new Atom(getContext(),
                    rand.nextInt(Constants.SCREEN_WIDTH - 2 * Constants.ATOM_RADIUS) + Constants.ATOM_RADIUS,
                    rand.nextInt(Constants.SCREEN_HEIGHT - 2 * Constants.ATOM_RADIUS - 100) + 100,
                    Constants.ATOM_RADIUS,
                    Color.MAGENTA,
                    pattern.get(i)));
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Atom draggingAtom;
    private int numTries = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (Atom atom : atomArrayList) {
                    if (atom.isTouched((int) event.getX(), (int) event.getY())) {
                        draggingAtom = atom;
                        break;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (draggingAtom != null) {
                    System.out.println(draggingAtom.getCollidingAtomList().size());
                    draggingAtom.updatePosition((int) event.getX(), (int) event.getY());
                    draggingAtom.getCollidingAtomList().clear();
                    for (Atom atom : atomArrayList) {
                        atom.getCollidingAtomList().remove(draggingAtom);
                    }
                    for (Atom atom : atomArrayList) {
                        if (atom == draggingAtom) {
                            continue;
                        }
                        if (draggingAtom.getHitbox().intersect(atom.getHitbox())) {
                            draggingAtom.addAtomColliding(atom);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (draggingAtom != null && draggingAtom.compareWithPattern()) {
                    Toast.makeText(getContext(), "Udalo sie", Toast.LENGTH_LONG).show();
                    numTries++;
                    if (numTries == 5) {
                        Intent intent = new Intent(getContext(), HighScoreActivity.class);
                        getContext().startActivity(intent);
                    } else {
                        populateAtomArrayList();
                    }
                }
                draggingAtom = null;
                break;
        }


        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);

        for (Atom atom : atomArrayList) {
            atom.draw(canvas);
        }

    }

}