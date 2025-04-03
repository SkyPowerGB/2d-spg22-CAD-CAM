package helpers;

import helpers.enums.ToolNamesE;
import helpers.enums.ToolStatesE;

public  class ToolStateTracker {
public static ToolNamesE activeTool;
public static ToolStatesE toolState;
    public static ToolNamesE getActiveTool() {
        return activeTool;
    }

    public static void setActiveTool(ToolNamesE activeTool) {
        ToolStateTracker.activeTool = activeTool;
    }




}
