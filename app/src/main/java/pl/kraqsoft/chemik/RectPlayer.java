package pl.kraqsoft.chemik;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by krakus on 12/27/2017.
 */

public class RectPlayer implements IGameObject {

    private Rect rectangle;
    private int color;

    public Rect getRectangle(){
        return rectangle;
    }
    public RectPlayer(Rect rectangle, int color){
        this.rectangle = rectangle;
        this.color = color;
    }

    @Override
    public void Update() {

    }

    public void Update(Point pnt){
        rectangle.set(pnt.x - rectangle.width()/2,
                pnt.y - rectangle.height()/2,
                pnt.x + rectangle.width()/2,
                pnt.y + rectangle.height()/2);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }



}
