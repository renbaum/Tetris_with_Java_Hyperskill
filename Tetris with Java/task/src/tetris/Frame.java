package tetris;

import java.util.ArrayDeque;
import java.util.Deque;

public class Frame{
    Matrix m;

    public Frame(){
        this.m = new Matrix(4, 4);
    }

    public Frame(Matrix m){
        this.m = m;
    }

    void print(){
        for(int i = 0; i < m.getRowCount(); i++){
            String row = "";
            for(int j = 0; j < m.getColCount(); j++){
                row += m.getDataAt(i, j) == 1 ? "0 " : "- ";
            }
            System.out.println(row.trim());
        }
    }



    public Frame addFrame(Frame other, int rows, int cols){
        return new Frame(this.m.add(other.m, rows, cols));
    }

    public void removeRow(int i) {
        m.removeRow(i);
    }

    public void InsertRow(int i) {
        m.InsertRow(i);
    }
}

class EmptyFrame extends Frame{
    public EmptyFrame(){
        this.m = new Matrix(4, 4);
    }

    public EmptyFrame(int rows, int cols){
        this.m = new Matrix(rows, cols);
    }
}


class Piece extends Frame{
    Deque<Matrix> queue;
    int row = 0, col = 0;

    private void setMatrix(){
        super.m = queue.peekFirst();
    }

    public Piece() {
        this.queue = new ArrayDeque<Matrix>();
    }

    public Piece(Piece toClone){
        this.m = new Matrix(toClone.m);
        this.row = toClone.row;
        this.col = toClone.col;
        this.queue = new ArrayDeque<Matrix>(toClone.queue);
    }

    public int getNumberOfRotates(){
        return queue.size();
    }

    public Piece(TypeOfPiece type) {
        this();
        for(int i = 0; i < type.getCoordinates().length; i++){
            Matrix m = new Matrix(GlobalConstants.SIZE_OF_PIECE_ROW, GlobalConstants.SIZE_OF_PIECE_COL);
            for(int j = 0; j < type.getCoordinates()[i].length; j++){
                int cell = type.getCoordinates()[i][j];
                m.setDataAt(cell / GlobalConstants.SIZE_OF_PIECE_COL, cell % GlobalConstants.SIZE_OF_PIECE_COL, 1);
            }
            queue.offerLast(m);
        }
        setMatrix();
    }

    public void rotate(){
        Matrix m = queue.pollFirst();
        queue.offerLast(m);
        setMatrix();
    }

}

class IPiece extends Piece{
    public IPiece(){
        super(TypeOfPiece.I_SHAPE);
    }

}

class SPiece extends Piece{
    public SPiece(){
        super(TypeOfPiece.S_SHAPE);
    }

}

class ZPiece extends Piece{
    public ZPiece(){
        super(TypeOfPiece.Z_SHAPE);
    }

}

class LPiece extends Piece{
    public LPiece(){
        super(TypeOfPiece.L_SHAPE);
    }

}

class JPiece extends Piece{
    public JPiece(){
        super(TypeOfPiece.J_SHAPE);
    }

}

class OPiece extends Piece{
    public OPiece(){
        super(TypeOfPiece.O_SHAPE);
    }

}

class TPiece extends Piece{
    public TPiece(){
        super(TypeOfPiece.T_SHAPE);
    }

}
