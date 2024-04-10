package helpers;

import helpers.enums.ToolNamesE;
import helpers.enums.ToolStatesE;

public  class improvedToolBox {
public static ToolNamesE activeTool;
public static ToolStatesE toolState;
    public static ToolNamesE getActiveTool() {
        return activeTool;
    }

    public static void setActiveTool(ToolNamesE activeTool) {
        improvedToolBox.activeTool = activeTool;
    }

    public static ToolStatesE getToolState() {
        return toolState;
    }

    public static void setToolState(ToolStatesE toolState) {
        improvedToolBox.toolState = toolState;
    }
}
