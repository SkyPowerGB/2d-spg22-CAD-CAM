package helpers;

import helpers.enums.ToolNamesE;

public  class improvedToolBox {
 static ToolNamesE activeTool;

    public static ToolNamesE getActiveTool() {
        return activeTool;
    }

    public static void setActiveTool(ToolNamesE activeTool) {
        improvedToolBox.activeTool = activeTool;
    }


}
