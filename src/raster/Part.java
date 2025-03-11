package raster;

import objects.TopologyType;
import transforms.Col;

/**
 * @author KŠ
 * 04.03.2025 8:46
 */

public class Part {
    // Prezentace 01, Slide 17
    private TopologyType topologyType;
    private int startIndex;
    private int count;
    private Col color;

    public Part(TopologyType topologyType, int startIndex, int count) {
        this.topologyType = topologyType;
        this.startIndex = startIndex;
        this.count = count;
        this.color = new Col(0xFFFFFF);
    }

    public Part(TopologyType topologyType, int startIndex, int count, Col color) {
        this.topologyType = topologyType;
        this.startIndex = startIndex;
        this.count = count;
        this.color = color;
    }

    public TopologyType getTopologyType() {
        return topologyType;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getCount() {
        return count;
    }

    public Col getColor() {
        return color;
    }
}
