package ui;

import logic.*;

import java.util.Scanner;

/**
 * todo Document type UserInterface
 */
public class UserInterface {

    private static void greet() {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
    }

    public static void start() {
        greet();
        printInstructions();

        while (true) {
            try {
                String value = getAction();
                String[] values = value.split(" ");

                if (values.length == 1) {
                    if (value.equals("0")) {
                        System.out.println("Goodbye!");
                        break;
                    }
                }
                processInputValues(values);
            } catch (Exception e) {
                System.out.println("This number is not natural!");
            }
        }
    }

    private static String getAction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a request: ");

        return scanner.nextLine();
    }

    private static void processInputValues(String[] values) {
        if (Long.parseLong(values[0]) < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
            return;
        } else if (values.length == 1) {
            printFullText(values[0]);
            return;
        }

        int count = Integer.parseInt(values[1]);
        if (count <= 0) {
            System.out.println("The second parameter should be a natural number.");
            return;
        }

        if (values.length < 3) {
            showProperties(values[0], count);
            return;
        }

        String[] propertiesArray = new String[values.length - 2];
        int index = 0;
        int countElements = 0;
        for (int i = 0; i < propertiesArray.length; i++) {
            propertiesArray[index] = values[i + 2];

            if (values[i + 2].contains("-")) {
                countElements++;
            }

            index++;
        }

        showProperties(
            values[0],
            count,
            completePropertiesArray(countElements, propertiesArray, false),
            completePropertiesArray(countElements, propertiesArray, true),
            propertiesArray);
    }

    private static String[] completePropertiesArray(int countElements, String[] propertiesArray, boolean isExcluded) {
        String[] properties;
        if (isExcluded) {
            properties = new String[countElements];
        } else {
            properties = new String[propertiesArray.length - countElements];
        }

        int index = 0;

        for (String s : propertiesArray) {
            if (isExcluded) {
                if (s.contains("-")) {
                    properties[index] = s;
                    index++;
                }
            } else if (!s.contains("-")) {
                properties[index] = s;
                index++;
            }
        }
        return properties;
    }

    private static void printFullText(String textValue) {
        System.out.println(ExtendedNumber.completeFullText(textValue));
    }

    private static void printInstructions() {
        System.out.println("""
                Supported requests: 
                   - enter a natural number to know its properties; 
                   - enter two natural numbers to obtain the properties of the list: 
                       * the first parameter represents a starting number;
                       * the second parameter shows how many consecutive numbers are to be processed;
                   - two natural numbers and properties to search for
                   - a property preceded by minus must not be present in numbers;
                   - separate the parameters with one space;
                   - enter 0 to exit.
                """);

    }

    private static void printMutuallyExclusiveMessage(String property) {
        System.out.println("The request contains mutually exclusive properties: " +
            "[" + property + "]\n" +
            "There are no numbers with these properties.");
    }

    private static void printShortText(String textValue) {
        System.out.println(ExtendedNumber.completeShortText(textValue));
    }

    private static void printWrongPropertyMessage(String propertiesString) {
        String[] properties = propertiesString.trim().split(" ");
        if (properties.length < 2) {
            System.out.println("The property " + propertiesString + " is wrong.\n" + NumberProperty.getFullList());
        } else {
            System.out.println("The properties " + propertiesString + " are wrong.\n" + NumberProperty.getFullList());
        }
    }

    public static void showProperties(String value, int count) {
        for (long s = 0; s < count; s++) {
            printShortText(String.valueOf(Long.parseLong(value) + s));
        }
    }

    public static void showProperties(String value, int count, String[] propertiesToInclude, String[] propertiesToExclude, String[] fullList) {
        long naturalValue = Long.parseLong(value);
        int i = 0;
        int countOfCorrect = 0;
        int countOfInCorrect = 0;

        if (ExtendedNumber.formIncorrectPropertiesString(propertiesToExclude).equals("")
            && ExtendedNumber.formIncorrectPropertiesString(propertiesToInclude).equals("")) {
            if (ExtendedNumber.formMutuallyExclusiveString(fullList).equals("")) {
                while (i < count) {

                    for (String property1 : propertiesToInclude) {
                        if (ExtendedNumber.checkValueByProperty(String.valueOf(naturalValue), property1)) {
                            countOfCorrect++;
                        }
                    }
                    for (String property1 : propertiesToExclude) {
                        if (!ExtendedNumber.checkValueByProperty(String.valueOf(naturalValue), property1)) {
                            countOfInCorrect++;
                        }
                    }
                    if (countOfInCorrect == propertiesToExclude.length
                        && countOfCorrect == propertiesToInclude.length) {
                        i++;
                        UserInterface.printShortText(String.valueOf(naturalValue));
                    }
                    countOfCorrect = 0;
                    countOfInCorrect = 0;
                    naturalValue++;
                }
            } else {

                UserInterface.printMutuallyExclusiveMessage(ExtendedNumber.formMutuallyExclusiveString(fullList));
            }
        } else {
            UserInterface.printWrongPropertyMessage(ExtendedNumber.formIncorrectPropertiesString(fullList));
        }
    }
}
