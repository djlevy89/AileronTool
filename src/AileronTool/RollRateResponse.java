package AileronTool;
import java.lang.Math;
import java.util.ArrayList;

public class RollRateResponse {

    public static double t_req (double phi_1, double P_ss, double t_ss) {
        return ((0.5236 - phi_1)/P_ss)+t_ss;
    }

    public static double phi_1 (double P_ss) {
        return 25.76*Math.log(P_ss*P_ss);
    }

    public static double P_ss (double L_A) {
        return Math.sqrt(0.000017*L_A);
    }

    public static double L_A (double C_l) {
        return 9485621*C_l;
    }

    public static double C_l(double C_ldA, double delta_A) {
        return C_ldA*delta_A;
    }

    public static double C_ldA(double y_o, double y_i, double tau) {
        return 0.000977*tau*((0.5*y_o*y_o-0.00323*y_o*y_o*y_o)-(0.5*y_i*y_i-0.00323*y_i*y_i*y_i));
    }

    public static double tau(double CCa) {
        return 59.5238*Math.pow(CCa,7)-133.333*Math.pow(CCa,5)+112.5*Math.pow(CCa,4)-27.625*Math.pow(CCa,3)-2.625*Math.pow(CCa,2)+2.93952*CCa;
    }

    public static double P_dot(double P_ss, double phi_1) {
        return (P_ss*P_ss)/(2*phi_1);
    }

    public static double t_ss(double phi_1, double P_dot) {
        return Math.sqrt((2*phi_1)/P_dot);
    }


    public static boolean verify (double y_o, double y_i, double CCa, double delta_A) {
        double tau = tau(CCa);
        double C_ldA = C_ldA(y_o, y_i, tau);
        double C_l = C_l(C_ldA, delta_A);
        double L_A = L_A(C_l);
        double P_ss = P_ss(L_A);
        double phi_1 = phi_1(P_ss);
        double P_dot = P_dot(P_ss, phi_1);

        if (phi_1 > 30*Math.PI/180) {
            return (t_ss(30*Math.PI/180, P_dot) > 2.45 && t_ss(30*Math.PI/180, P_dot) < 2.55);
        } else {
            return (t_req(phi_1, P_ss, t_ss(phi_1, P_dot)) > 2.45 && t_req(phi_1, P_ss, t_ss(phi_1, P_dot)) < 2.55);
        }
    }

    public static void main(String[] args) {
        ArrayList<double[]> Configs = new ArrayList<>();
        for (int a = 10; a < 31; a++) {
            for (int b = 10; b < 31; b++) {
                for (int c = 30; c < 62; c++) {
                    for (int d = 30; d < c; d++) {
                        double da = (double)a*Math.PI/180;
                        double db = (double)b/100;
                        double dc = (double)c;
                        double dd = (double)d;
                        if (verify(dc, dd, db, da) && dc - dd > 5) {
                            double score = (dc - dd)*db;
                            double[] populator = {dc, dd, db, da, score};
                            Configs.add(populator);
                        }
                    }
                }
            }
        }

        ArrayList<double[]> Optimum = new ArrayList<>();
        Optimum.add(Configs.get(0));
        Opt:
        for (int i = 1; i < Configs.size(); i++) {
            double[] current = Configs.get(i);
            for (int j = 0; j < Optimum.size(); j++) {
                double[] previous = Optimum.get(j);
                if (current[4] < previous[4]) {
                    Optimum.add(j,current);
                    continue Opt;
                }
            }
            Optimum.add(current);
        }

        for (int i = 0; i < 10; i++) {
            double[] prt = Optimum.get(i);
            System.out.println(prt[0]);
            System.out.println(prt[1]);
            System.out.println(prt[2]);
            System.out.println(prt[3]*180/Math.PI);
        }
    }
}
