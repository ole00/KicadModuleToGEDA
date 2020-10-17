public class FootprintElementArchetype {

    static String excludedLayers;

    long xOffsetNm = 0;
    long yOffsetNm = 0;

    boolean excluded; //when true - exclude from export

    public long Xposition() {
        return xOffsetNm;
    }

    public long Yposition() {
        return yOffsetNm;
    }

    public void FootprintElementArchetype(long x, long y) {
        xOffsetNm = x;
        yOffsetNm = y;
    }

    public String toString() {
        return ("x: " + xOffsetNm + ", y: " + yOffsetNm);
    }

    public String generateGEDAglyph(long xOffset, long yOffset, float magnificationRatio) {
        return "";
    }

    public String generateGEDAelement(long xOffset, long yOffset, float magnificationRatio) {
        return "";
    }

    public void populateElement(String moduleDefinition, boolean metric) {
        System.out.println("You're not supposed to see this.");
    }

    public void populateElement(String moduleDefinition, boolean metric, long minimumViaDrillSize) {
        System.out.println("You're not supposed to see this.");
    }


    protected long convertToNanometres(float rawValue, boolean metricSystem) {
        if (metricSystem) { // metricSystem = units mm
            return (long)(1000000 * rawValue);
            // multiply mm by 1000000 to turn into nm
        } else {
            return (long)(2540 * rawValue);
            // multiply 0.1 mil units by 2540 to turn into nm
        }
    }

    protected boolean isLayerExcluded(String layer) {
        if (excludedLayers == null) {
            return false;
        }
        // layer is excluded if it is found in the list of excluded layers
        return excludedLayers.indexOf(layer) >= 0;
    }

}
