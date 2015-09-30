package ru.tyanmt.task.common;

/**
 * Created by mityan on 30.09.2015.
 */
public enum FacePosition {
    BOTTOM, FRONT, TOP, RIGHT, REAR, LEFT;

    public FacePosition next() {
        //TODO Change exception
        if (this.ordinal() == FacePosition.values().length) throw new RuntimeException("Enumeration doesn't have next element");
        return FacePosition.values()[this.ordinal() + 1];
    }
}
