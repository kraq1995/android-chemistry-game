package pl.kraqsoft.chemik;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by krakus on 11/5/2017.
 */

public class Atom extends View implements Comparator<Atom> {
    private int radius;
    private int x;
    private int y;
    private Paint paint;
    private Paint paintLetter;
    private Paint collisionPaint;
    private int color;
    private String letter;
   // private boolean colliding = false;
    private ArrayList<Atom> collidingAtomList = new ArrayList<>();


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
        paintLetter.setTextSize(Constants.TEXT_SIZE);
        this.collisionPaint = new Paint();
        collisionPaint.setColor(Color.GREEN);
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
    public void updatePosition(int x, int y){
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
        canvas.drawCircle(x, y, radius, isColliding() ? collisionPaint : paint);
        canvas.drawText(String.valueOf(collidingAtomList.size()) + letter, x, y, paintLetter);
    }

    public boolean isColliding() {
        return (collidingAtomList.size() > 0);
    }

    public void addAtomColliding(Atom atom) {
        if(!this.collidingAtomList.contains(atom)) {
            this.collidingAtomList.add(atom);
        }

        for (Atom collidingAtom : atom.getCollidingAtomList()) {
            if (!this.collidingAtomList.contains(collidingAtom) && collidingAtom != this) {
                this.collidingAtomList.add(collidingAtom);
            }
        }

        if(!atom.getCollidingAtomList().contains(this)) {
            atom.getCollidingAtomList().add(this);
        }

        for (Atom collidingAtom : atom.getCollidingAtomList()) {
            if (!collidingAtom.getCollidingAtomList().contains(this) && collidingAtom != this) {
                collidingAtom.getCollidingAtomList().add(this);
            }
        }
    }

    public ArrayList<Atom> getCollidingAtomList(){
        return collidingAtomList;
    }

    public void removeAtomColliding(Atom atom){
      //  collidingAtomList.removeAll(atom.getCollidingAtomList());
        collidingAtomList.remove(atom);
        atom.getCollidingAtomList().remove(this);
    }

    public String getLetter(){
        return letter;
    }

    public ArrayList<Atom> getSortedListOfCollidingAtoms(){
        collidingAtomList.sort(this);
        return collidingAtomList;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    public void printArray(ArrayList<Atom> tempAtomList){
        String s = "";
        for(Atom atom : tempAtomList)
            s += atom.getLetter() + " ";
        System.out.println(s);
    }
    public boolean compareWithPattern(){
        ArrayList<Atom> tempAtomList = new ArrayList<>();
        tempAtomList.addAll(collidingAtomList);
        tempAtomList.add(this);
        tempAtomList.sort(this);

        System.out.print("a=");
        printArray(tempAtomList);
        System.out.print("b=");
        printArray(collidingAtomList);

        boolean patternFound = false;
        for(String[] pattern : Constants.PATTERN_ARRAY){
            boolean isTheSame = true;
            for (int i = 0; i < pattern.length; i++) {

                if(i >= tempAtomList.size()) {
                    isTheSame = false;
                    break;
                }
                String element = tempAtomList.get(i).getLetter();

                if(!pattern[i].equals(element)) {
                    isTheSame = false;
                    break;
                }
            }
            if(isTheSame) {
                patternFound = true;
                System.out.println(pattern);
                break;
            }
        }
        return patternFound;

    }

    @Override
    public int compare(Atom o1, Atom o2) {
        return o1.getLetter().charAt(0) >= o2.getLetter().charAt(0) ? 1 : -1;
    }
}
