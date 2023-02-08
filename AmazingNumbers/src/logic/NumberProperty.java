package logic;

/**
 * todo Document type Properties
 */
public enum NumberProperty {

    BUZZ("buzz"),
    DUCK("duck"),
    PALINDROMIC("palindromic"),
    GAPFUL("gapful"),
    SPY("spy"),
    SQUARE("square"),
    SUNNY("sunny"),
    JUMPING("jumping"),
    EVEN("even"),
    ODD("odd"),
    HAPPY("happy"),
    SAD("sad");

    String description;

    NumberProperty(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static String getFullList() {
        String text = "Available properties: ";
        StringBuilder textToReplace = new StringBuilder();
        for (NumberProperty numberProperty : NumberProperty.values()) {
            textToReplace.append(numberProperty.name()).append(" ");
        }
        return text + "[" + textToReplace.toString().trim().replace(" ", ", ") + "]";
    }
}
