package pl.kraqsoft.chemik;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by krakus on 12/27/2017.
 */

public class Obstacle implements IGameObject {

    private Rect rectangle;
    private int color;

    public Obstacle(Rect rectangle, int color){
        this.rectangle = rectangle;
        this.color = color;
    }

public boolean playerCollide(RectPlayer player){
    if(collisionDetected(player))
        return true;

    return false;
}

public boolean collisionDetected(RectPlayer player){
    return (rectangle.contains(player.getRectangle().left, player.getRectangle().top) ||
            rectangle.contains(player.getRectangle().right, player.getRectangle().top) ||
            rectangle.contains(player.getRectangle().left, player.getRectangle().bottom) ||
            rectangle.contains(player.getRectangle().right, player.getRectangle().bottom));
}

public Rect getRectangle(){
        return rectangle;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void Update() {

    }
}
