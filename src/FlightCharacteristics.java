public class FlightCharacteristics {

    // Initialise variables and constants
    private int type;
    private char phase;
    private int level;
    private char runway;

    // Constructor method for FlightCharacteristics
    public FlightCharacteristics(int type, char phase, int level, char runway) {
        this.type = type;
        this.phase = phase;
        this.level = level;
        this.runway = runway;
    }

    // Returns the roll control characteristics corresponding to the chosen flight characteristics
    // as specified in MIL-F-8785C
    public double[] getRollControlRequirements(FlightCharacteristics aircraft) {
        double[] requirements = new double[2];

        switch (aircraft.type) {
            case 1:
                switch (aircraft.phase) {
                    case 'A':
                        requirements[0] = 60;
                        switch(aircraft.level) {
                            case 1:
                                requirements[1] = 1.3;
                                break;
                        }
                        break;
                }
        }

        return requirements;
    }
}
