package ru.tyanmt.task.common;

import java.util.NoSuchElementException;

public enum FacePosition {
    BOTTOM {
        @Override
        public int getPoint(int i, int j, int[][][] cube) {
            return cube[0][i][j];
        }

        @Override
        public int getPointFromNeighbor(int i, int j, int[][][] cube) {
            return cube[cube.length / 2][i][j];
        }

        @Override
        public void setPoint(int i, int j, int[][][] cube) {
            cube[0][i][j] = this.ordinal()+1;
        }
    }, FRONT {
        @Override
        public int getPoint(int i, int j, int[][][] cube) {
            return cube[i][0][j];
        }

        @Override
        public int getPointFromNeighbor(int i, int j, int[][][] cube) {
            return cube[i][cube.length / 2][j];
        }

        @Override
        public void setPoint(int i, int j, int[][][] cube) {
            cube[i][0][j] = this.ordinal()+1;
        }
    }, TOP {
        @Override
        public int getPoint(int i, int j, int[][][] cube) {
            return cube[cube.length - 1][i][j];
        }

        @Override
        public int getPointFromNeighbor(int i, int j, int[][][] cube) {
            return cube[cube.length / 2][i][j];
        }

        @Override
        public void setPoint(int i, int j, int[][][] cube) {
            cube[cube.length - 1][i][j] = this.ordinal()+1;
        }
    }, RIGHT {
        @Override
        public int getPoint(int i, int j, int[][][] cube) {
            return cube[i][j][cube.length - 1];
        }

        @Override
        public int getPointFromNeighbor(int i, int j, int[][][] cube) {
            return cube[i][j][cube.length / 2];
        }

        @Override
        public void setPoint(int i, int j, int[][][] cube) {
            cube[i][j][cube.length - 1] = this.ordinal()+1;
        }
    }, REAR {
        @Override
        public int getPoint(int i, int j, int[][][] cube) {
            return cube[i][cube.length - 1][j];
        }

        @Override
        public int getPointFromNeighbor(int i, int j, int[][][] cube) {
            return cube[i][cube.length / 2][j];
        }

        @Override
        public void setPoint(int i, int j, int[][][] cube) {
            cube[i][cube.length - 1][j] = this.ordinal()+1;
        }
    }, LEFT {
        @Override
        public int getPoint(int i, int j, int[][][] cube) {
            return cube[i][j][0];
        }

        @Override
        public int getPointFromNeighbor(int i, int j, int[][][] cube) {
            return cube[i][j][cube.length / 2];
        }

        @Override
        public void setPoint(int i, int j, int[][][] cube) {
            cube[i][j][0] = this.ordinal()+1;
        }
    };

    public abstract int getPoint(int i, int j, int[][][] cube);

    public abstract int getPointFromNeighbor(int i, int j, int[][][] cube);

    public abstract void setPoint(int i, int j, int[][][] cube);

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
