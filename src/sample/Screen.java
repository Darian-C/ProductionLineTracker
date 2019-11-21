package sample;

/**
 * @author Darian Colon
 */
public class Screen implements ScreenSpec {
    protected String resolution;
    protected int refreshRate;
    protected int responseTime;

    /**
     * @param resolution
     * @param refreshRate
     * @param responseTime
     */
    public Screen(String resolution, int refreshRate, int responseTime) {
        this.resolution = resolution;
        this.refreshRate = refreshRate;
        this.responseTime = responseTime;
    }

    @Override
    public String getResolution() {
        return resolution;
    }

    @Override
    public int getRefreshRate() {
        return refreshRate;
    }

    @Override
    public int getResponseTime() {
        return responseTime;
    }

    /**
     * @return
     */
    public String toString() {
        return "Resolution: " + resolution + "\n" +
                "Refresh Rate: " + refreshRate + "\n" +
                "Response Time: " + responseTime;
    }
}

