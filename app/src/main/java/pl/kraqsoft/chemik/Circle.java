package pl.kraqsoft.chemik;

import android.content.Context;
import android.view.View;

/**
 * Created by krakus on 11/5/2017.
 */

public class Circle extends View {
    private double Radius;
    private double X;
    private double Y;
    private double PI = Math.PI;

    public Circle(double _X, double _Y, double _Radius, Context context){
        super(context);
        this.X = _X;
        this.Y = _Y;
        this.Radius = _Radius;
    }

    public double getMiddleX(){
        return X;
    }
    public double getMiddleY(){
        return Y;
    }
    public double getRadius(){
        return Radius;
    }
    public void setX(double value){
        this.X = value;
    }
    public void setY(double value){
        this.Y = value;
    }
    public double getArea(){
        return PI*Radius*Radius;
    }
}
