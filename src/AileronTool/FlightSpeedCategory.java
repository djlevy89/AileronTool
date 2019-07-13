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
        double V_min = 1.1*(double)flight.stall;
        switch (flight.type) {
            case 3:
                if (flight.speed < 1.8*V_min) {
                    return 'L';
                } else if (flight.speed >= 1.8*V_min && flight.speed < .7*flight.max) {
                    return 'M';
                } else if (flight.speed >= 0.7*flight.max) {
                    return 'H';
                }
            case 4:
                if (flight.speed < V_min+20) {
                    return 'V';
                } else if (flight.speed >= V_min+20 && flight.speed < 1.4*V_min) {
                    return 'L';
                } else if (flight.speed >= 1.4*V_min && flight.speed <= 0.7*flight.max) {
                    return 'M';
                } else if (flight.speed >= 0.7*flight.max) {
                    return 'H';
                }

        }
        return 'A';
    }

}
