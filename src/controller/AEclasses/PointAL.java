package controller.AEclasses;
import java.awt.Point;

import ViewParts.PointBtn;
import controller.callbacks.PointPressedCallBack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PointAL implements ActionListener {
    PointPressedCallBack callBack;
    public PointAL(PointPressedCallBack callBack){
        this.callBack=callBack;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        PointBtn btn= (PointBtn) e.getSource();
            callBack.clickedPoint(btn.getModelPoint());
    }

}
