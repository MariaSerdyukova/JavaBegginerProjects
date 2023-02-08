package logic;

import static java.lang.Math.abs;

/**
 * todo Document type ExtendedNumber
 */
public class ExtendedNumber {

    static final String BUZZ_TEXT = NumberProperty.BUZZ.getDescription();
    static final String DUCK_TEXT = NumberProperty.DUCK.getDescription();
    static final String EVEN_TEXT = NumberProperty.EVEN.getDescription();
    static final String GAPFUL_TEXT = NumberProperty.GAPFUL.getDescription();
    static final String ODD_TEXT = NumberProperty.ODD.getDescription();
    static final String PALINDROMIC_TEXT = NumberProperty.PALINDROMIC.getDescription();
    static final String SPY_TEXT = NumberProperty.SPY.getDescription();
    static final String SQUARE_TEXT = NumberProperty.SQUARE.getDescription();
    static final String SUNNY_TEXT = NumberProperty.SUNNY.getDescription();
    static final String JUMPING_TEXT = NumberProperty.JUMPING.getDescription();
    static final String HAPPY_TEXT = NumberProperty.HAPPY.getDescription();
    static final String SAD_TEXT = NumberProperty.SAD.getDescription();

    public static boolean checkValueByProperty(String value, String property) {
        return switch (convertToProperties(property)) {
            case BUZZ -> checkBuzz(value);
            case DUCK -> checkDuck(value);
            case EVEN -> checkEven(value);
            case GAPFUL -> checkGapful(value);
            case ODD -> !checkEven(value);
            case PALINDROMIC -> checkPalindromic(value);
            case SPY -> checkSpy(value);
            case SQUARE -> checkSquare(value);
            case SUNNY -> checkSunny(value);
            case JUMPING -> checkJumping(value);
            case HAPPY -> checkHappy(value);
            case SAD -> !checkHappy(value);
        };
    }

    public static String completeFullText(String value) {
        return "Properties of " + value +
            "\n" + BUZZ_TEXT + ": " + checkBuzz(value) +
            "\n" + DUCK_TEXT + ": " + checkDuck(value) +
            "\n" + EVEN_TEXT + ": " + checkEven(value) +
            "\n" + GAPFUL_TEXT + ": " + checkGapful(value) +
            "\n" + ODD_TEXT + ": " + !checkEven(value) +
            "\n" + PALINDROMIC_TEXT + ": " + checkPalindromic(value) +
            "\n" + SPY_TEXT + ": " + checkSpy(value) +
            "\n" + SQUARE_TEXT + ": " + checkSquare(value) +
            "\n" + SUNNY_TEXT + ": " + checkSunny(value) +
            "\n" + JUMPING_TEXT + ": " + checkJumping(value) +
            "\n" + HAPPY_TEXT + ": " + checkHappy(value) +
            "\n" + SAD_TEXT + ": " + !checkHappy(value);
    }

    public static String completeShortText(String value) {
        String text = value + " is ";
        StringBuilder textToReplace = new StringBuilder();

        if (checkBuzz(value)) {
            textToReplace.append(BUZZ_TEXT).append(" ");
        }

        if (checkDuck(value)) {
            textToReplace.append(DUCK_TEXT).append(" ");
        }

        if (checkEven(value)) {
            textToReplace.append(EVEN_TEXT).append(" ");
        }

        if (checkGapful(value)) {
            textToReplace.append(GAPFUL_TEXT).append(" ");
        }

        if (!checkEven(value)) {
            textToReplace.append(ODD_TEXT).append(" ");
        }

        if (checkPalindromic(value)) {
            textToReplace.append(PALINDROMIC_TEXT).append(" ");
        }

        if (checkSpy(value)) {
            textToReplace.append(SPY_TEXT).append(" ");
        }

        if (checkSquare(value)) {
            textToReplace.append(SQUARE_TEXT).append(" ");
        }

        if (checkSunny(value)) {
            textToReplace.append(SUNNY_TEXT).append(" ");
        }

        if (checkJumping(value)) {
            textToReplace.append(JUMPING_TEXT).append(" ");
        }

        if (checkHappy(value)) {
            textToReplace.append(HAPPY_TEXT).append(" ");
        }

        if (!checkHappy(value)) {
            textToReplace.append(SAD_TEXT).append(" ");
        }

        return text + textToReplace.toString().trim().replace(" ", ", ");
    }

    public static String formMutuallyExclusiveString(String[] properties) {
        for (String property : properties) {
            for (String property1 : properties) {
                if(property.equals('-'+ property1) ||
                    checkHasMutuallyExclusive(property, property1)) {
                    return property.toUpperCase() + ", "+ property1.toUpperCase();
                }
            }
        }
        return "";
    }

    public static String formIncorrectPropertiesString(String[] properties) {
        StringBuilder result = new StringBuilder();
        for (String property : properties) {
            if (!isPropertyCorrect(property)) {
                result.append(property).append(" ");
            }
        }
        return result.toString();
    }

    private static boolean checkBuzz(String value) {

        return Long.parseLong(value) % 7 == 0 || Long.parseLong(value) % 10 == 7;
    }

    private static boolean checkDuck(String value) {

        return value.contains("0") && value.charAt(0) != 0;
    }

    private static boolean checkEven(String value) {

        return Long.parseLong(value) % 2 == 0;
    }

    private static boolean checkGapful(String value) {
        if (value.length() < 3) {
            return false;
        } else {
            int concatenation = Integer.parseInt(String.valueOf(value.charAt(0))
                .concat(String.valueOf(value.charAt(value.length() - 1))));
            return Long.parseLong(value) % concatenation == 0;
        }
    }

    private static boolean checkPalindromic(String value) {
        if (value.length() == 1) {
            return true;
        } else if (value.length() == 2) {
            return value.charAt(0) == value.charAt(1);
        } else {
            int middleIndex = value.length() / 2;
            int startIndex = 0;
            int endIndex = value.length() - 1;

            while (startIndex < middleIndex) {
                if (value.charAt(startIndex) == value.charAt(endIndex)) {
                    startIndex++;
                    endIndex--;
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    private static boolean checkSpy(String value) {
        long product = 1;
        long sum = 0;
        long n = Long.parseLong(value);

        while (n != 0) {
            product *= n % 10;
            sum += n % 10;
            n /= 10;
        }

        return product == sum;
    }

    private static boolean checkSquare(String value) {
        long sr = (long) Math.sqrt(Long.parseLong(value));
        return ((sr * sr) == Long.parseLong(value));
    }

    private static boolean checkSunny(String value) {
        return checkSquare(String.valueOf(Long.parseLong(value) + 1));
    }

    private static boolean checkJumping(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (i > 0) {
                if (abs(Long.parseLong(String.valueOf(value.charAt(i))) - Long.parseLong(String.valueOf(value.charAt(i - 1)))) != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkHappy(String value) {
        long naturalValue = Long.parseLong(value);
        int sum = 0;
        String stringToSearchIn = "";

        while (true) {
            while (naturalValue > 0) {
                sum += (naturalValue % 10) * (naturalValue % 10);
                naturalValue /= 10;
            }

            if (stringToSearchIn.contains(" " + sum + " ")) {
                return false;
            }

            if(sum == 1) {
                return true;
            }

            naturalValue = sum;
            stringToSearchIn += " " + sum + " ";
            sum = 0;
        }
    }

    private static NumberProperty convertToProperties(String val) {
        for (NumberProperty prop : NumberProperty.values()) {
            if (val.equalsIgnoreCase(prop.getDescription())) {
                return prop;
            }
            if(val.substring(1).equalsIgnoreCase(prop.getDescription()))
            {
                return prop;
            }
        }
        return NumberProperty.ODD;
    }

    private static boolean checkHasMutuallyExclusive(String properties1, String prop2) {
        return checkHasMutuallyExclusive(new String[]{properties1, prop2});
    }

    private static boolean checkHasMutuallyExclusive(String[] properties) {
        String stringToSearchIn = "";
        for (String property : properties) {
            stringToSearchIn += "," + property;
        }
        return stringToSearchIn.contains("," + EVEN_TEXT) && stringToSearchIn.contains("," + ODD_TEXT) ||
            stringToSearchIn.contains(",-" + EVEN_TEXT) && stringToSearchIn.contains(",-" + ODD_TEXT) ||
            stringToSearchIn.contains("," + DUCK_TEXT) && stringToSearchIn.contains("," + SPY_TEXT) ||
            stringToSearchIn.contains("," + SUNNY_TEXT) && stringToSearchIn.contains("," + SQUARE_TEXT) ||
            stringToSearchIn.contains("," + HAPPY_TEXT) && stringToSearchIn.contains("," + SAD_TEXT);
    }

    private static boolean isPropertyCorrect(String property) {
        for (NumberProperty propertyOfNumber : NumberProperty.values()) {
            if (propertyOfNumber.getDescription().equalsIgnoreCase(property) ||
                propertyOfNumber.getDescription().equalsIgnoreCase(property.substring(1))) {
                return true;
            }
        }
        return false;
    }
}
