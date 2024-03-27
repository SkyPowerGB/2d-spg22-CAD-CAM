package controller;

import View.DrawText;
import controller.callbacks.AddTextCallback;

public class DrawTextController {
    DrawText view;
    AddTextCallback callback;
    public DrawTextController(AddTextCallback callback){
        view=new DrawText();
         view.getADDButton().addActionListener(e->{addText();});
         view.getCLOSEButton().addActionListener(e->{closeWindow();});
         this.callback=callback;
    }


    public void closeWindow(){view.closeWindow();}
    public void addText(){

        view.closeWindow();

        callback.AddText(view.storeAndGetData());
    }





}
