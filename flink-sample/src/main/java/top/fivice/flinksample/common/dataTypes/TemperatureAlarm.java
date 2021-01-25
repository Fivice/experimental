package top.fivice.flinksample.common.dataTypes;

/**
 * @author wubin
 */
public class TemperatureAlarm {
    /**
     * 传感器id
      */
    private String id;

    /**
     * 传感器温度
     */
    private int temperature;

    /**
     * 传感器Unix时间戳
     */
    private long unixTime;

    public TemperatureAlarm(String id, int temperature, long unixTime) {
        this.id = id;
        this.temperature = temperature;
        this.unixTime = unixTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public long getUnixTime() {
        return unixTime;
    }

    public void setUnixTime(long unixTime) {
        this.unixTime = unixTime;
    }
}
