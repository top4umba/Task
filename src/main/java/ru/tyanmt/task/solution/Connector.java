package ru.tyanmt.task.solution;

/**
 * Created by mityan on 05.08.2015.
 */
public class Connector {

    public static boolean isEdgesConnected(int edge, int connectingEdge) {
        int edgeMask = 0b01110;
        boolean edgeFilled = (edge ^ connectingEdge & edgeMask) == 0b01110;
        int vertexMask = 0b10001;
        boolean vertexOverlapped = (edge & connectingEdge & vertexMask) == 0b10001;
        return edgeFilled && !vertexOverlapped;
    }

}
