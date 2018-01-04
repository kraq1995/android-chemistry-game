package pl.kraqsoft.chemik;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;


/**
 * Created by krakus on 12/27/2017.
 */

public class CirclePlayer implements IGameObject {

    private Atom atom;
    private int color;
    private int x,y,radius;

    public CirclePlayer(Atom atom, int color){
        this.atom = atom;
        this.color = color;
        this.x = ((int) atom.getX());
        this.y = ((int) atom.getY());
        this.radius = ((int) atom.getRadius());
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
        atom.setX(point.x);
        atom.setY(point.y);
        atom.setRadius(Radius);
    }

}
