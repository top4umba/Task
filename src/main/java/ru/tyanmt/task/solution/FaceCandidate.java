package ru.tyanmt.task.solution;

import ru.tyanmt.task.common.Face;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mityan on 03.08.2015.
 */
public class FaceCandidate {

    private int topEdge;
    private boolean isFlipped;


    public ArrayList<Integer> getEdges() {
        return new ArrayList<>(Arrays.asList(edges));
    }

    public int getEdge(int edgeFace) {
        return edges[edgeFace];
    }

    public int getTopEdge() {
        return topEdge;
    }

    public void setTopEdge(int topEdge) {
        this.topEdge = topEdge;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void setIsFlipped(boolean isInverted) {
        this.isFlipped = isInverted;
    }

    private final Integer[] edges = new Integer[4];

    public static Builder newBuilder(){
        return new FaceCandidate().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder top(int edge){
            FaceCandidate.this.edges[0] = edge;
            return this;
        }

        public Builder right(int edge){
            FaceCandidate.this.edges[1] = edge;
            return this;
        }

        public Builder bottom(int edge){
            FaceCandidate.this.edges[2] = edge;
            return this;
        }

        public Builder left(int edge){
            FaceCandidate.this.edges[3] = edge;
            return this;
        }

        public FaceCandidate build(){
            return FaceCandidate.this;
        }

    }
}
