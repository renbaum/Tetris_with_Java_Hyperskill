package tetris;

public class Board {
    private Frame emptyFrame;
    private Piece piece;

    int rows = 0, cols = 0;

    public Board(int rows, int cols) {
        emptyFrame = new EmptyFrame(rows, cols);
        this.rows = rows;
        this.cols = cols;
//        initBorder();
    }

    public boolean addNewPiece(String type){
        piece = FactoryPiece.createPiece(type);
        piece.row = 0;
        piece.col = (this.cols - piece.m.getColCount()) / 2;
        emptyFrame = new EmptyFrame(rows, cols).addFrame(piece, piece.row, piece.col);
        if(emptyFrame == null) return false;
        return true;
    }

    private boolean showPiece(int row, int col){
        emptyFrame = new EmptyFrame(rows, cols).addFrame(piece, row, col);
        if (emptyFrame == null) return false;
        return true;
    }

    public boolean movePieceDown() {
        if(showPiece(piece.row + 1, piece.col)){
            piece.row ++;
            return true;
        }
        return false;
    }

    public boolean movePieceRight() {
        if(showPiece(piece.row, piece.col + 1)){
            piece.col ++;
            movePieceDown();
            return true;
        }
        return false;
    }

    public boolean movePieceLeft() {
        if(showPiece(piece.row, piece.col - 1)){
            piece.col --;
            movePieceDown();
            return true;
        }
        return false;
    }

    public boolean rotatePiece() {
        Piece n = new Piece(this.piece);
        n.rotate();
        emptyFrame = new EmptyFrame(rows, cols).addFrame(n, n.row, n.col);
        if(emptyFrame == null) return false;
        this.piece = n;
        movePieceDown();
        return true;
    }


    public void print(){
        if (emptyFrame == null) return;
        emptyFrame.print();
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
