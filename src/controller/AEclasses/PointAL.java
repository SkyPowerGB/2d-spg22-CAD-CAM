package controller.AEclasses;

import View.ViewUIComponents.UIPointBtn;
import controller.callbacks.PointPressedCallBack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PointAL implements ActionListener {
    PointPressedCallBack callBack;
    public PointAL(PointPressedCallBack callBack){
        this.callBack=callBack;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        UIPointBtn btn= (UIPointBtn) e.getSource();
            callBack.clickedPoint(btn.getModelPoint());
    }

}
