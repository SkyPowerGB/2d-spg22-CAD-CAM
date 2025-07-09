package model;

import java.awt.*;

public class FileDataModel {

    public FileDataModel(){
        fileName="New_File";
        materialDim= new DimensionDoubleModel(50,50);
        material="default";
        ppm=10;
        materialViewDimension= new Dimension((int)(materialDim.getWidth()*10), (int) (materialDim.getHeight()*10));
    }


    // PROPS

    private double materialThickness=1;

    private String fileName;

    private String material;

    private DimensionDoubleModel materialDim;

    private int ppm;

    private Dimension materialViewDimension;





   //GETTERS
    public Dimension getMaterialViewDimension(){
        return materialViewDimension;
    }
    public String getFileName(){return  fileName;}
    public double getMaterialThickness() {
        return materialThickness;
    }
    public String getMaterial() {
        return material;
    }
    public DimensionDoubleModel getMaterialDim() {
        return materialDim;
    }
    public int getPpm() {
        return ppm;
    }


    // SETTERS
    public void setMaterialThickness(double materialThickness) {
        this.materialThickness = materialThickness;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public void setMaterialDim(DimensionDoubleModel materialDim) {
        this.materialDim = materialDim;
        this.materialViewDimension=new Dimension((int)(materialDim.getWidth()*10), (int) (materialDim.getHeight()*10));
    }
    public void setPpm(int ppm) {
        this.ppm = ppm;
    }
    public void setMaterialViewDimension(Dimension materialViewDimension) {
        this.materialViewDimension = materialViewDimension;
    }



}
