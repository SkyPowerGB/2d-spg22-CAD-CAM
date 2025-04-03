package helpers;

import helpers.enums.ToolNamesE;
import helpers.enums.ToolStatesE;

public  class ToolTracker {
public static ToolNamesE activeTool;
public static ToolStatesE toolState;
    public static ToolNamesE getActiveTool() {
        return activeTool;
    }

    public static void setActiveTool(ToolNamesE activeTool) {
        ToolTracker.activeTool = activeTool;
    }




}
