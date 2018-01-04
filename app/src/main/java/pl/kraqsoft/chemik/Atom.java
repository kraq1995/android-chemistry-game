package pl.kraqsoft.chemik;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by krakus on 11/5/2017.
 */

public class Atom extends View {
    private int radius;
    private int x;
    private int y;
    private Paint paint;
    private Paint paintLetter;
    private Paint collsionPaint;
    private int color;
    private String letter;
    private boolean colliding = false;

    public Atom(Context context, int x, int y, int radius, int color, String letter){
        super(context);
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.letter = letter;
        this.paint = new Paint();
        paint.setColor(color);
        this.paintLetter = new Paint();
        paintLetter.setColor(Color.WHITE);
        this.collsionPaint = new Paint();
        collsionPaint.setColor(Color.GREEN);
        this.color = color;
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public double getRadius(){
        return radius;
    }
    public void setRadius(int value){this.radius = value;}
    public void setX(int value){
        this.x = value;
    }
    public void setY(int value){
        this.x = value;
    }
    public void updatePositon(int x, int y){
        this.x=x;
        this.y=y;
    }
    public Rect getHitbox(){
        return new Rect(x-Constants.ATOM_HITBOX,y-Constants.ATOM_HITBOX,x+Constants.ATOM_HITBOX,y+Constants.ATOM_HITBOX);
    }
    private Rect getTouchHitbox(){ return new Rect(x-radius,y-radius,x+radius,y+radius);}

    public boolean isTouched(int x, int y){
        if(getTouchHitbox().contains(x,y))
            return true;
        return false;
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawCircle(x, y, radius, isColliding() ? collsionPaint : paint);
        canvas.drawText(letter, x, y, paintLetter);
    }

    public boolean isColliding() {
        return colliding;
    }

    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }
}
