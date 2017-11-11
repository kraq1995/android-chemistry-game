package pl.kraqsoft.chemik;

import android.graphics.Canvas;

/**
 * Created by krakus on 11/5/2017.
 */

public class DrawAtom implements IGameObject {
    private Circle circle;
    private int color;

    public DrawAtom(Circle _circle, int _color){
        this.circle = _circle;
        this.color = _color;
    }
    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void Update() {

    }
}
