abstract class Drone {
    public abstract String fly();
}

interface Trackable {
    String getLocation();
}

class DeliveryDrone extends Drone implements Trackable {
    private String id;

    public DeliveryDrone(String id) {
        this.id = id;
    }

    @Override
    public String fly() {
        return id + " flying delivery route";
    }

    @Override
    public String getLocation() {
        return id + " at Sector 4";
    }
}

class ScoutDrone extends Drone {
    private String id;

    public ScoutDrone(String id) {
        this.id = id;
    }

    @Override
    public String fly() {
        return id + " scouting area";
    }
}

class GroundRobot implements Trackable {
    private String id;

    public GroundRobot(String id) {
        this.id = id;
    }

    @Override
    public String getLocation() {
        return id + " at Sector 4";
    }
}

class A5 {
    public static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            Trackable t = (Trackable) o;
            return t.getLocation();
        }
        return "Tracking not available";
    }
}