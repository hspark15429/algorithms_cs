public class Percolation {
    private int M;
    private WeightedQuickUnionUF gridUF;
    private boolean[][] gridOC;

    public Percolation(int N) {
        if(N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        M = N;
        // create ,N*N+2 components
        gridUF = new WeightedQuickUnionUF(N*N+2);
        // grid
        gridOC = new boolean[N][N];

        // union all top sites with one site above all, bottom sites the same
        for (int i = 1; i <= N; i++) {
            gridUF.union(0, i);
            gridUF.union(N*N+1, N*N-i+1);
        }
    }
    public void open(int i, int j) {
       // open site (row i, column j) if it is not open already
            if(i > M || i < 1 || j > M || j < 1){
                throw new java.lang.IndexOutOfBoundsException();
            }

            if (!gridOC[i - 1][j - 1]) {
                gridOC[i-1][j-1] = true;

                if (i-1 > 0 && gridOC[i-2][j-1]) {
                   gridUF.union(M*(i-1)+j, M*(i-1)+j - M);
                }

                if (i+1 <= M && gridOC[i][j-1]) {
                   gridUF.union(M*(i-1)+j, M*(i-1)+j + M);
                }

                if (j-1 > 0 && gridOC[i-1][j-2]) {
                   gridUF.union(M*(i-1)+j, M*(i-1)+j - 1);
                }

                if (j+1 <= M && gridOC[i-1][j]) {
                   gridUF.union(M*(i-1)+j, M*(i-1)+j + 1);
                }
            }

    }

    public boolean isOpen(int i, int j) {     // is site (row i, column j) open?
       return gridOC[i-1][j-1];
    }

    public boolean isFull(int i, int j) {     // is site (row i, column j) full?
       return gridUF.connected(0, M*(i-1)+j) && gridOC[i-1][j-1];
    }
    public boolean percolates() {
       // does the system percolate?
       return gridUF.connected(0, M*M+1);
    }
    public static void main(String[] args) {
       // test client (optional)

       /*Percolation some = new Percolation(5);
       some.open(1,2);

       some.open(2,2);
       some.open(3,2);
       some.open(4,2);
       some.open(5,2);
       */

    }
}