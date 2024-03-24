package helpers;

import java.awt.*;

public class DimensionLineGen {
    Point A;
    Point B;

    double k;
    double l;
    double perPk;

    double arrowK;
    double arrowKn;



    boolean vertical=false;
    boolean horizontal=false;

    public DimensionLineGen(Point A, Point B){
        this.A=A;
        this.B=B;
   calcK();
    }

    private void calcK(){

        if((B.x-A.x)==0){
                  k=0;

               vertical=true;
                  return;
        }
        if((B.y-A.y)==0){
            horizontal=true;
            k=0;
            return;
        }
        k=((double)( (B.y - A.y) /(B.x-A.x)));

        l=A.y-(k*A.x);

        perPk=-(1/k);

    }


    public void calcLines(int pointsDist){
         if(vertical){calcVerticalCase(pointsDist);}
         else if(horizontal){calcHorizontalCase(pointsDist);}




    }
    private void calcVerticalCase(int points){}
    private void calcHorizontalCase(int points){}







}
