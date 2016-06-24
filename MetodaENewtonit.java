
public class MetodaENewtonit {

    // Metoda e cila permban Jakobianin 
    public static double[][] ktheJ(double[] x,int l,int m) {
        double[][] J = {{-l*Math.sin(x[0]),-m*Math.sin(x[1])},
        		{l*Math.cos(x[0]),m*Math.cos(x[1])}};
        return J;
    }

    // Metoda e cila permban F
    public static double[] ktheF(double[] x,int l,int m) {
        double[] F = {(l*Math.cos(x[0])+m*Math.cos(x[1])-1),
                (l*Math.sin(x[0])+m*Math.sin(x[1])-1)};
        return F;
    }

    public static double[] ktheY(double[] x,int l,int m) {
        MatricaInverse in = new MatricaInverse();
        double[][] J = ktheJ(x,l,m);
        double[][] JInverz = in.invert(J);// Gjejme inverzin e Jakobianit
        double[] F = ktheF(x,l,m);
        double[] Y = new double[F.length]; // Prodhimi i J dhe F
        for (int i = 0; i < F.length; i++) {
            for (int j = 0; j < F.length; j++) {
                Y[i] = JInverz[i][j] * F[j];
            }
             
        }
        return Y;
    }

    public static String ktheNewton(int n, double[] x0, double TOL, int NO,int l,int m) {
        NormaInfinit norma = new NormaInfinit();
        int k = 1;
        double[] x = new double[n];

        while (k <= NO) {
            double[] y = ktheY(x0,l,m);
            for (int i = 0; i < n; i++) {
                x[i] = x0[i] - y[i];
                // System.out.println(" " + x[i]);
            }

            double normaInfinit = norma.ktheNorme(n, y);
            if (normaInfinit < TOL) {

                System.out.println(" Iteracioni i " + k + ":");
                for (int j=0 ;j<n;j++){
                	
              
        
                System.out.println(x[j] + ", ");

            }
            
                    
          
        }
        for (int t= 0; t<n ;t++){
        
        	x0[t]=x[t];
        x[t]= 0;
     
        } k++;
        
    }
        return "Metoda deshtoi pas " + k + " iteracionesh";
        
    }

    public static void main(String[] args) {

        double x[] = {0.0,Math.PI/2};

        int n = x.length;
        double TOL = 0.000001;
        int NO = 6;
        int l = 2;
        int m = 1;
        System.out.println(ktheNewton(n, x, TOL, NO,l,m));
    }

}
