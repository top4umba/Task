package ru.tyanmt.task.common;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mityan on 03.08.2015.
 */
public class Face {

    private int top;
    private int right;
    private int bottom;
    private int left;
    private int number;

    private Face(){}

    public int getTop() {
        return top;
    }

    public int getRight() {
        return right;
    }

    public int getBottom() {
        return bottom;
    }

    public int getLeft() {
        return left;
    }

    public int getNumber() {
        return number;
    }

    public static Builder newBuilder(){
        return new Face().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder withNumber(int number){
            Face.this.number = number;
            return this;
        }

        public Builder topConnectedTo(int faceNumber){
            Face.this.top = faceNumber;
            return this;
        }

        public Builder rightConnectedTo(int faceNumber){
            Face.this.right = faceNumber;
            return this;
        }

        public Builder bottomConnectedTo(int faceNumber){
            Face.this.bottom = faceNumber;
            return this;
        }

        public Builder leftConnectedTo(int faceNumber){
            Face.this.left = faceNumber;
            return this;
        }

        public Face build(){
            return Face.this;
        }

    }
}
