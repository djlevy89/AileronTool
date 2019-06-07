package AileronTool;

public class FlightSpeedCategory {

    // Initialise variables and constants
    private Integer stall;
    private Integer max;
    private Integer speed;
    private int type;
    private int level;

    // Constructor method for FlightCharacteristics
    public FlightSpeedCategory(Integer speed, Integer stall, Integer max, int type, int level) {
        this.stall = stall;
        this.max = max;
        this.type = type;
        this.level = level;
        this.speed = speed;
    }

    public static char getFlightSpeed(FlightSpeedCategory flight) {
        switch (flight.type) {
            case 3:
                double V_min = 1.1*(double)flight.stall;
                switch (flight.level) {
                    case 1:
                        if (flight.speed < 1.8*V_min) {
                            return 'L';
                        } else if (flight.speed >= 1.8*V_min && flight.speed < .7*flight.max) {
                            return 'M';
                        } else if (flight.speed >= 0.7*flight.max) {
                            return 'H';
                        }
                    case 2:
                        if (flight.speed < 1.8*V_min) {
                            return 'L';
                        } else if (flight.speed >= 1.8*V_min && flight.speed < .7*flight.max) {
                            return 'M';
                        } else if (flight.speed >= 0.7*flight.max) {
                            return 'H';
                        }
                        break;
                }


        }
        return 'A';
    }

}
