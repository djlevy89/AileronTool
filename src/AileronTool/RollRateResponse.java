
package AileronTool;
import java.lang.Math;

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

    public static double t_ss(double P_ss, double phi_1) {
        return (P_ss*P_ss)/(2*phi_1);
    }


}

