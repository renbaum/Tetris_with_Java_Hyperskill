package tetris;

public class Board {
    private Frame movingFrame;
    private Frame staticFrame;
    private Piece piece;

    int rows = 0, cols = 0;


    public Board(int rows, int cols) {
        movingFrame = new EmptyFrame(rows, cols);
        staticFrame = new EmptyFrame(rows, cols);
        this.rows = rows;
        this.cols = cols;
//        initBorder();
    }

    public boolean addNewPiece(String type){
        piece = FactoryPiece.createPiece(type);
        piece.row = 0;
        piece.col = (this.cols - piece.m.getColCount()) / 2;
        movingFrame = new EmptyFrame(rows, cols).addFrame(piece, piece.row, piece.col);
        if(movingFrame == null) return false;
        return true;
    }

    private boolean showPiece(int row, int col){
        movingFrame = new EmptyFrame(rows, cols).addFrame(piece, row, col);
        if (movingFrame == null) return false;
        return true;
    }

    private void setStaticFrame(){
        if(movingFrame.m.isValueInRow(movingFrame.m.getRowCount()-1, 1)) {
            // reached the bottom
            staticFrame = staticFrame.addFrame(movingFrame, 0, 0);
            movingFrame = new EmptyFrame(rows, cols);
            this.piece = null;
        }
    }

    public boolean movePieceDown() {
        setStaticFrame();
        if(piece == null) return false;
        if(showPiece(piece.row + 1, piece.col)){
            piece.row ++;
            setStaticFrame();
            return true;
        }
        return false;
    }

    public boolean movePieceRight() {
        if(piece == null) return false;
        if(!movingFrame.m.isValueInColumn(movingFrame.m.getColCount()-1, 1)) {
            if(showPiece(piece.row, piece.col + 1)){
                piece.col ++;
            }
        }
        return movePieceDown();
    }

    public boolean movePieceLeft() {
        if(piece == null) return false;
        if(!movingFrame.m.isValueInColumn(0, 1)) {
            if (showPiece(piece.row, piece.col - 1)) {
                piece.col--;
            }
        }
        return movePieceDown();
    }

    public boolean rotatePiece() {
        if(piece == null) return false;
        Piece n = new Piece(this.piece);
        n.rotate();
        Frame move = new EmptyFrame(rows, cols).addFrame(n, n.row, n.col);
        if(move != null) {
            if(!((move.m.isValueInColumn(move.m.getColCount()-1, 1) &&
                    move.m.isValueInColumn(0, 1)) ||
                    (move.m.isValueInRow(move.m.getRowCount()-1, 1) &&
                    move.m.isValueInRow(0, 1)))) {
                this.piece = n;
                movingFrame = move;
            }
        }
        return movePieceDown();
    }


    public void print(){
        if (movingFrame == null) return;
        Frame frame = staticFrame.addFrame(movingFrame,0, 0);
        frame.print();
    }

/*    private void initBorder(){
        for(int i = 0; i < borderFrame.m.getRowCount(); i++){
            borderFrame.m.setDataAt(i, 0, 1);
            borderFrame.m.setDataAt(i, borderFrame.m.getColCount() - 1, 1);
        }
        for(int i = 0; i < borderFrame.m.getColCount(); i++ ){
            borderFrame.m.setDataAt(borderFrame.m.getRowCount() - 1, i, 1);
        }
    }
 */

}
