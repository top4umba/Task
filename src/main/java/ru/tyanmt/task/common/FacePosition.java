package ru.tyanmt.task.common;

import java.util.NoSuchElementException;

/**
 * Created by mityan on 30.09.2015.
 */
public enum FacePosition {
    BOTTOM, FRONT, TOP, RIGHT, REAR, LEFT;

    public FacePosition next() {
        if (isLast()) throw new NoSuchElementException("Enumeration doesn't have next element");
        return FacePosition.values()[this.ordinal() + 1];
    }

    public boolean hasNext() {
        return this.ordinal() < FacePosition.values().length-1;
    }

    private boolean isLast() {
        return this.ordinal() == FacePosition.values().length-1;
    }
}
