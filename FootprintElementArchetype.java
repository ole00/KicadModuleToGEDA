public class FootprintElementArchetype {

    public static final int KC_LAYER_BOTTOM_COPPER = 0;
    public static final int KC_LAYER_TOP_COPPER = 15;
    public static final int KC_LAYER_BOTTOM_PASTE = 18;
    public static final int KC_LAYER_TOP_PASTE = 19;
    public static final int KC_LAYER_BOTTOM_SILK = 20;
    public static final int KC_LAYER_TOP_SILK = 21;
    public static final int KC_LAYER_BOTTOM_MASK = 22;
    public static final int KC_LAYER_TOP_MASK = 23;
    
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

    protected int getKicadLayer(String layer) {
        if (layer.startsWith("F.Cu")) {
            return KC_LAYER_TOP_COPPER; // front most copper layer
        } else if (layer.startsWith("B.Cu")) {
            return KC_LAYER_BOTTOM_COPPER;
        } else if (layer.startsWith("B.Paste")) {
            return KC_LAYER_BOTTOM_PASTE;
        } else if (layer.startsWith("F.Paste")) {
            return KC_LAYER_TOP_PASTE;
        } else if (layer.startsWith("B.Silk")) {
            return KC_LAYER_BOTTOM_SILK;
        } else if (layer.startsWith("F.Silk")) {
            return KC_LAYER_TOP_SILK;
        } else if (layer.startsWith("B.Mask")) {
            return KC_LAYER_BOTTOM_MASK;
        } else if (layer.startsWith("F.Mask")) {
            return KC_LAYER_TOP_MASK;
        }
        return -1;
    }
    protected String addGedaFlag(String flags, String newFlag) {
        if (flags == null || flags.length() == 0) {
            return newFlag;
        }
        if (flags.endsWith("\"")) {
            return flags.concat(newFlag);
        }
        return flags.concat(",").concat(newFlag);
    }
}
