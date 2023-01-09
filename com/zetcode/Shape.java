package com.zetcode;

import java.util.Random;

public class Shape {

    protected enum Tetrominoe { NoShape, ZShape, SShape, LineShape,
        TShape, SquareShape, LShape, MirroredLShape }
    //공책에 그림

    private Tetrominoe pieceShape; // 도형들 이름
    private int coords[][]; //coord, coordinates 좌표. 도형들의 좌표
    private int[][][] coordsTable; //도형들의 집합


    public Shape() {

        initShape(); //초기화
    }

    private void initShape() {

        coords = new int[4][2];

        coordsTable = new int[][][] {
                { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },  //NoShape
                { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } }, //ZShape 
                { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },  //SShape
                { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },  //LineShape
                { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },  //TShape
                { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },  //SquareShape
                { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },  //LShape
                { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }   //MirroredLShape
        };

        setShape(Tetrominoe.NoShape);
    }

    protected void setShape(Tetrominoe shape) {

        for (int i = 0; i < 4 ; i++) {

            for (int j = 0; j < 2; ++j) {

                coords[i][j] = coordsTable[shape.ordinal()][i][j];
            }
        }

        pieceShape = shape;
    }

    private void setX(int index, int x) { coords[index][0] = x; }// x좌표값 변경
    private void setY(int index, int y) { coords[index][1] = y; }// y좌표값 변경
    public int x(int index) { return coords[index][0]; }         // x좌표값 리턴
    public int y(int index) { return coords[index][1]; }         // y좌표값 리턴
    public Tetrominoe getShape()  { return pieceShape; }         // pieceShape 리턴

    public void setRandomShape() {

        var r = new Random();                      //랜덤객체 생성
        int x = Math.abs(r.nextInt()) % 7 + 1;     //1~8 랜덤정수 생성

        Tetrominoe[] values = Tetrominoe.values(); //values()함수는 enum의 요소들을 순서대로 enum타입의 배열로 리턴
        setShape(values[x]);                       //따라서 values[x]가 사용 가능
    }

    public int minX() { //x최소값 리턴

        int m = coords[0][0];

        for (int i=0; i < 4; i++) {

            m = Math.min(m, coords[i][0]);
        }

        return m;
    }


    public int minY() { //y최소값  리턴

        int m = coords[0][1];

        for (int i=0; i < 4; i++) {

            m = Math.min(m, coords[i][1]);
        }

        return m;
    }

    public Shape rotateLeft() {

        if (pieceShape == Tetrominoe.SquareShape) { //정사각형은 돌려도 똑같기 때문에 바로 리턴

            return this;
        }

        var result = new Shape();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; ++i) {

            result.setX(i, y(i));
            result.setY(i, -x(i));
        }

        return result;
    }

    public Shape rotateRight() {

        if (pieceShape == Tetrominoe.SquareShape) {//정사각형 바로 리턴

            return this;
        }

        var result = new Shape();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; ++i) {

            result.setX(i, -y(i));
            result.setY(i, x(i));
        }

        return result;
    }
    //rotateRight, rotateLeft 두 개의 함수 TShape의 경우 반대로 됨
}

