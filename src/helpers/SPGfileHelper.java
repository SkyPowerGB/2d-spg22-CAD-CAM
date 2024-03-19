package helpers;

import model.FileData;

import java.io.File;
import java.io.IOException;

public class SPGfileHelper {


    public void createFile(FileData data,String path){
        boolean newFile=false;
        File file=new File(path+"/"+data.fileName+".spgCaM");
        try {
             newFile = file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(!newFile){
            return;
        }

        String header="{ thickness:"+data.materialThickness+", materialX:+ }";

    }



}
