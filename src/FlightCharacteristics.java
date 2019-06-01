public class FlightCharacteristics {

    // Initialise variables and constants
    private int type;
    private char phase;
    private int level;
    private char runway;
    private char speed;

    // Constructor method for FlightCharacteristics
    public FlightCharacteristics(int type, char phase, int level, char runway, char speed) {
        this.type = type;
        this.phase = phase;
        this.level = level;
        this.runway = runway;
        this.speed = speed;
    }

    // Returns the roll control characteristics corresponding to the chosen flight characteristics
    // as specified in MIL-F-8785C
    public double[] getRollControlRequirements(FlightCharacteristics aircraft) {
        // Initialise variable to be returned with roll response requirements
        double[] requirements = new double[2];

        // Hardcoding of the MIL-F-8785C requirements spec
        switch (aircraft.type) {
            case 1:
                switch (aircraft.phase) {
                    case 'A':
                        requirements[0] = 60;
                        switch(aircraft.level) {
                            case 1:
                                requirements[1] = 1.3;
                                break;
                            case 2:
                                requirements[1] = 1.7;
                                break;
                            case 3:
                                requirements[1] = 2.6;
                                break;
                        }
                        break;
                    case 'B':
                        requirements[0] = 45;
                        switch(aircraft.level) {
                            case 1:
                                requirements[1] = 1.7;
                                break;
                            case 2:
                                requirements[1] = 2.5;
                                break;
                            case 3:
                                requirements[1] = 3.4;
                                break;
                        }
                        break;
                    case 'C':
                        requirements[0] = 30;
                        switch(aircraft.level) {
                            case 1:
                                requirements[1] = 1.3;
                                break;
                            case 2:
                                requirements[1] = 1.8;
                                break;
                            case 3:
                                requirements[1] = 2.6;
                                break;
                        }
                        break;
                }
                break;
            case 2:
                switch (aircraft.phase) {
                    case 'A':
                        requirements[0] = 45;
                        switch (aircraft.level) {
                            case 1:
                                requirements[1] = 1.4;
                                break;
                            case 2:
                                requirements[1] = 1.9;
                                break;
                            case 3:
                                requirements[1] = 2.8;
                                break;
                        }
                        break;
                    case 'B':
                        requirements[0] = 45;
                        switch (aircraft.level) {
                            case 1:
                                requirements[1] = 1.9;
                                break;
                            case 2:
                                requirements[1] = 2.8;
                                break;
                            case 3:
                                requirements[1] = 3.8;
                                break;
                        }
                        break;
                    case 'C':
                        switch (aircraft.level) {
                            case 1:
                                switch (aircraft.runway) {
                                    case 'L':
                                        requirements[0] = 30;
                                        requirements[1] = 1.8;
                                        break;
                                    case 'C':
                                        requirements[0] = 30;
                                        requirements[1] = 2.5;
                                        break;
                                }
                                break;
                            case 2:
                                switch (aircraft.runway) {
                                    case 'L':
                                        requirements[0] = 30;
                                        requirements[1] = 3.6;
                                        break;
                                    case 'C':
                                        requirements[0] = 25;
                                        requirements[1] = 1.0;
                                        break;
                                }
                                break;
                            case 3:
                                requirements[0] = 25;
                                switch (aircraft.runway) {
                                    case 'L':
                                        requirements[1] = 1.5;
                                        break;
                                    case 'C':
                                        requirements[1] = 2.0;
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
            case 3:
                requirements[0] = 30;
                switch (aircraft.level) {
                    case 1:
                        switch (aircraft.speed) {
                            case 'L':
                                switch (aircraft.phase) {
                                    case 'A':
                                        requirements[1] = 1.8;
                                        break;
                                    case 'B':
                                        requirements[1] = 2.3;
                                }
                        }
                }

        }

        return requirements;
    }
}
