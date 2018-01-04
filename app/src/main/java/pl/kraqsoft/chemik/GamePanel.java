package pl.kraqsoft.chemik;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by krakus on 11/5/2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private ArrayList<Atom> atomArrayList = new ArrayList<>();

    public GamePanel(Context context){
        super(context);
        getHolder().addCallback(this);
        populateAtomArrayList();
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    private void populateAtomArrayList(){
        Random rand = new Random();

        for(int i = 0; i<5; i++){
            atomArrayList.add(new Atom(getContext(),
                    rand.nextInt(Constants.SCREEN_WIDTH - 2*Constants.ATOM_RADIUS) + Constants.ATOM_RADIUS,
                    rand.nextInt(Constants.SCREEN_HEIGHT - 2*Constants.ATOM_RADIUS - 100) + 100,
                    Constants.ATOM_RADIUS,
                    Color.RED,
                    "H"));
        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread  = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
                retry = false;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private Atom draggingAtom;
    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                for(Atom atom: atomArrayList){
                    if(atom.isTouched((int)event.getX(), (int)event.getY())) {
                        draggingAtom = atom;
                        break;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(draggingAtom != null) {
                    draggingAtom.updatePositon((int) event.getX(), (int) event.getY());
                    draggingAtom.setColliding(false);
                    for (Atom atom : atomArrayList) {
                        if(atom == draggingAtom)
                            continue;
                        atom.setColliding(false);
                        if (atom.getHitbox().intersect(draggingAtom.getHitbox())) {
                            atom.setColliding(true);
                            draggingAtom.setColliding(true);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                draggingAtom = null;
                break;
        }

        return true;
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        for(Atom atom : atomArrayList){
            atom.draw(canvas);
        }
    }

}

