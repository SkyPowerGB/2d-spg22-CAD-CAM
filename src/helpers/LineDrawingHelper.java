package helpers;

import helpers.helperModels.Line;

import java.awt.*;

public class LineDrawingHelper {
    Point A;
    Point B;

    double k;
    double l;
    double perPk;
    int distPerp=20;
    int distParalel=15;

    public  LineDrawingHelper(Point A,Point B){
        this.A=A;
        this.B=B;

    }

    private void calck(){
        if((B.x-A.x)==0){
                  k=0;
        }
        k=((double)( (B.y - A.y) /(B.x-A.x)));

        l=A.y-(k*A.x);

        perPk=-(1/k);


    }









}
