package pl.kraqsoft.chemik;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;


/**
 * Created by krakus on 12/27/2017.
 */

public class circlePlayer implements IGameObject {
    private Circle circle;
    private int color;
    private int x,y,radius;

    public circlePlayer(Circle circle, int color){
        this.circle =  circle;
        this.color = color;
        this.x = ((int) circle.getMiddleX());
        this.y = ((int) circle.getMiddleY());
        this.radius = ((int) circle.getRadius());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle(x,y,radius,paint);
    }

    @Override
    public void Update() {

    }

    public void update(Point point, int Radius){
        circle.setX(point.x);
        circle.setY(point.y);
        circle.setRadius(Radius);
    }

}
