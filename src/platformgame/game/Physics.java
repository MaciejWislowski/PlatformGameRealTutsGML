package platformgame.game;

public class Physics {

    // Gravitational acceleration
    public static final double GRAVITATIONAL_ACCELERATION = 9.81; // [m/s^2]
    public static final double G = GRAVITATIONAL_ACCELERATION/0.000277/3600; // [pixels/tick^2]

    // Terminal velocity
    public static final double TERMINAL_VELOCITY = 54; // [m/s]
    public static final double TV = TERMINAL_VELOCITY/0.000277/60; // [pixels/tick]

    public static float getPixelSpeedFromMeters(float speed) {
        return (float) (speed/0.000277/60);
    }

}
