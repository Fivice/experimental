package top.fivice.springbootsample.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author wubin
 */
public class Entity implements Serializable {


    /**
     * 采集服务唯一标识ID
     */
    @JsonAlias({"collectorId","CollectorId","CC"})
    @JsonProperty("mm")
    String collectorId;

    public String getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(String collectorId) {
        this.collectorId = collectorId;
    }
}
