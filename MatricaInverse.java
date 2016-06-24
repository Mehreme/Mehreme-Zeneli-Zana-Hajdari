import java.lang.*;

public class MatricaInverse {

    public double[][] invert(double[][] numeric) {
        int n = numeric.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;

        gaussian(numeric, index);

        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k]
                            -= numeric[index[j]][i] * b[index[i]][k];

        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / numeric[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= numeric[index[j]][k] * x[k][i];
                }
                x[j][i] /= numeric[index[j]][j];
            }
        }
        return x;
    }

    // Metoda per te kryer metoden me pivotim pacial eleminimet e gausit
    //   indekset[] e ruajtura ne baze te urdherit te pivotit

    public static void gaussian(double numeric[][], int index[]) {
        int n = index.length;
        double c[] = new double[n];

        for (int i = 0; i < n; ++i)
            index[i] = i;

        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(numeric[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(numeric[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = numeric[index[i]][j] / numeric[index[j]][j];

                numeric[index[i]][j] = pj;

                for (int l = j + 1; l < n; ++l)
                    numeric[index[i]][l] -= pj * numeric[index[j]][l];
            }
        }
    }

/*    public static void main(String args[]) {
        double a[][] = {{x1, x2, x3},
                {y1, y2, y3},
                {z1, z2, z3}};
        int n = a.length;
        double d[][] = invert(a);
        for (int i = 0; i < n; ++i) {
            System.out.println("");

            for (int j = 0; j < n; ++j)
                System.out.print(d[i][j] + "  ");
        }
        System.out.println();

    }*/
}


