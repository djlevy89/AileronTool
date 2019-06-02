package AileronTool.Tests;

import AileronTool.FlightCharacteristics;

// Generates a printed table of roll control requirements as stipulated in MIL-F-8785C

public class FlightCharTest {

    public static void main(String[] args) {
        System.out.println("Aircraft Class I");
        for (int i = 1; i < 4; i++) {
            System.out.print("Level "+i+" | ");
            for (char j = 'A'; j < 'D'; j++) {
                FlightCharacteristics a = new FlightCharacteristics(1,j,i,'0','0');
                double[] requirements = AileronTool.FlightCharacteristics.getRollControlRequirements(a);
                System.out.print(requirements[0]+" degrees in "+requirements[1]+" seconds | ");
            }
            System.out.print("\n");
        }

        System.out.println("\nAircraft Class II");
        char[] d = {'L','C'};
        for (int i = 1; i < 4; i++) {
            for (char e : d) {
                for (char j = 'A'; j < 'D'; j++) {
                    System.out.print("Level "+i+" | ");
                    System.out.print("Runway "+e+" | ");
                    FlightCharacteristics a = new FlightCharacteristics(2, j, i, e, '0');
                    double[] requirements = AileronTool.FlightCharacteristics.getRollControlRequirements(a);
                    System.out.print(requirements[0] + " degrees in " + requirements[1] + " seconds ");
                }
                System.out.print("\n");
            }
        }

        System.out.println("\nAircraft Class III");
        char[] f = {'L','M','H'};
        for (int i = 1; i < 4; i++) {
            for (char e : f) {
                for (char j = 'A'; j < 'D'; j++) {
                    System.out.print("Level "+i+" | ");
                    System.out.print("Speed "+e+" | ");
                    FlightCharacteristics a = new FlightCharacteristics(3, j, i, '0', e);
                    double[] requirements = AileronTool.FlightCharacteristics.getRollControlRequirements(a);
                    System.out.print(requirements[0] + " degrees in " + requirements[1] + " seconds ");
                }
                System.out.print("\n");
            }
        }

        System.out.println("\nAircraft Class IV");
        char[] g = {'V','L','M','H'};
        for (int i = 1; i < 4; i++) {
            for (char e : g) {
                for (char j = 'A'; j < 'D'; j++) {
                    System.out.print("Level "+i+" | ");
                    System.out.print("Speed "+e+" | ");
                    FlightCharacteristics a = new FlightCharacteristics(4, j, i, '0', e);
                    double[] requirements = AileronTool.FlightCharacteristics.getRollControlRequirements(a);
                    System.out.print(requirements[0] + " degrees in " + requirements[1] + " seconds ");
                }
                System.out.print("\n");
            }
        }
    }
}
