
package AileronTool;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

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
        return 6674340.8*C_l;
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
        // System.out.println(tau+" tau");
        double C_ldA = C_ldA(y_o, y_i, tau);
        // System.out.println(C_ldA+" clda");
        double C_l = C_l(C_ldA, delta_A);
        // System.out.println(C_l+" C_l");
        double L_A = L_A(C_l);
        // System.out.println(L_A+" L_A");
        double P_ss = P_ss(L_A);
        // System.out.println(P_ss+" P_ss");
        double phi_1 = phi_1(P_ss);
        // System.out.println(phi_1+" phi_1");
        double P_dot = P_dot(P_ss, phi_1);
        // System.out.println(P_dot+" P_dot");

        if (phi_1 > 30*Math.PI/180) {
            // System.out.println("a");
            // System.out.println(t_ss(30*Math.PI/180, P_dot));
            return (t_ss(30*Math.PI/180, P_dot) > 2.25 && t_ss(30*Math.PI/180, P_dot) < 2.75);
        } else {
            // System.out.println("b");
            // System.out.println(t_ss(phi_1, P_dot));
            // System.out.println(t_req(phi_1, P_ss, t_ss(phi_1, P_dot)));
            return (t_req(phi_1, P_ss, t_ss(phi_1, P_dot)) > 2.25 && t_req(phi_1, P_ss, t_ss(phi_1, P_dot)) < 2.75);
        }
    }

    public static void main(String[] args) {
        // System.out.println(verify(61.5,50.1, 0.22, 25*Math.PI/180));
        ArrayList<double[]> Configs = new ArrayList<>();
        for (int a=5; a < 31; a=a+5) {
           for (int b = 5; b < 31; b++) {
              for (int c = 20; c < 62; c++) {
                  for (int d = 20; d < c; d++) {
                      double da = (double)a;
                      double db = (double)b/100;
                      double dc = (double)c;
                      double dd = (double)d;
                      if (verify(dc, dd, db, da)) {
                          double[] populator = {dc, dd, db, da};
                          Configs.add(populator);
                      }
                  }
              }
           }
        }
        System.out.println(Configs.size());
    }



}

