
package AileronTool;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;

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

    public static double HMTemp(double CCa, double y_o, double y_i, double delta_A) {
        return delta_A*CCa*CCa*(y_o-y_i);
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

        for (int a = 5; a < 31; a=a+5) {
           for (int b = 5; b < 31; b++) {
              for (int c = 20; c < 62; c++) {
                  for (int d = 20; d < c; d++) {
                      double da = (double)a*Math.PI/180;
                      double db = (double)b/100;
                      double dc = (double)c;
                      double dd = (double)d;
                      if (verify(dc, dd, db, da)) {
                          double score = (dc - dd)*db;
                          double[] populator = {dc, dd, db, da, score, HMTemp(db, dc, dd, da)};
                          Configs.add(populator);
                      }
                  }
              }
           }
        }
        /*System.out.println(Configs.size());
        double[] test = Configs.get(0);
        System.out.println(test[0]);
        System.out.println(test[1]);
        System.out.println(test[2]);
        System.out.println(test[3]);
        System.out.println(test[4]);
        System.out.println(test[5]);*/

        System.out.println(Configs.size());

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

        System.out.println(Optimum.size());
        double[] test = Optimum.get(0);
        System.out.println(test[0]);
        System.out.println(test[1]);
        System.out.println(test[2]);
        System.out.println(test[3]);

        ArrayList<double[]> OptimumHinge = new ArrayList<>();
        OptimumHinge.add(Optimum.get(0));
        /*Opt2:
        for (int i = 1; i < Configs.size(); i++) {
            double[] current = Configs.get(i);
            for (int j = 0; j < OptimumHinge.size(); j++) {
                double[] previous = OptimumHinge.get(j);
                if (current[5] > previous[5]) {
                    OptimumHinge.add(j,current);
                    continue Opt2;
                }
            }
            OptimumHinge.add(current);
        }*/

        Opt2:
        for (int i = 1; i < Optimum.size(); i++) {
            double[] current = Optimum.get(i);
            for (int j = 0; j < OptimumHinge.size(); j++) {
                double[] previous = OptimumHinge.get(j);
                if (current[5] > previous[5]) {
                    double[] next = {current[0], current[1], current[2], current[3], current[4], current[5],(double)i};
                    OptimumHinge.add(j,next);
                    continue Opt2;
                }
            }
            OptimumHinge.add(current);
        }

        System.out.println(OptimumHinge.size());
        double[] test2 = OptimumHinge.get(0);
        System.out.println(test2[0]);
        System.out.println(test2[1]);
        System.out.println(test2[2]);
        System.out.println(test2[3]);

        ArrayList<String> sort = new ArrayList<>();

        for (int i = 0; i < OptimumHinge.size(); i++) {
            double[] sb = OptimumHinge.get(i);
            String sum = Integer.toString(i+(int)sb[6]);
            sort.add(sum);
        }

        Collections.sort(sort, String.CASE_INSENSITIVE_ORDER);

        for (int i = 0; i < sort.size(); i++) {
            System.out.println(sort.get(i));
        }

        /*for (int i = 0; i < 3; i++) {
            double[] roll =  Optimum.get(i);
            for (int j = 0; j < 4; j++) {
                System.out.println(roll[j]);
            }
        }

        for (int i = 0; i < 3; i++) {
            double[] roll =  OptimumHinge.get(i);
            for (int j = 0; j < 4; j++) {
                System.out.println(roll[j]);
            }
        }*/
    }







}

