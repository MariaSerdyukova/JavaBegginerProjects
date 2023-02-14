package battleship.logic;

import static battleship.logic.MapBuilder.getCoordinatesFromInterval;
import static battleship.ui.UserInterface.printBattleShipMap;
import static battleship.ui.UserInterface.readInputFromUser;

/**
 * todo Document type Shooter
 */
public class Shooter {

    public static void placeShootOnTheMap(String[][] battleShip, String[][] fogMap) {
        while (true) {

            int[] coordinatesOfShoot = getCoordinatesFromInterval(readInputFromUser());
            int x1, y1;
            x1 = coordinatesOfShoot[0];
            y1 = coordinatesOfShoot[1];

            if (x1 < 1 || x1 > 10 || y1 < 1 || y1 > 10) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                continue;
            }
            if (battleShip[x1][y1].equals("O ") || battleShip[x1][y1].equals("X ")) {
                battleShip[x1][y1] = "X ";
                fogMap[x1][y1] = "X ";

                printBattleShipMap(fogMap);
                if (isFullShipDestroyed(battleShip, x1, y1)) {
                    System.out.println("You sank a ship! Specify a new target:");
                } else {
                    System.out.println("You hit a ship! Try again:");
                }
                break;
            } else if (battleShip[x1][y1].equals("~ ") || battleShip[x1][y1].equals("M ")) {
                battleShip[x1][y1] = "M ";
                fogMap[x1][y1] = "M ";

                printBattleShipMap(fogMap);
                System.out.println("You missed. Try again:");
                break;
            }
        }
    }

    public static void shootUntilTheEnd(String[][] battleShip, String[][] fogMap) {
        do {
            placeShootOnTheMap(battleShip, fogMap);
        } while (!checkAllShipsDestroyed(battleShip));
    }

    public static boolean isFullShipDestroyed(String[][] battleShip, int firstCoordinate, int secondCoordinate) {
        for (int i = secondCoordinate; i < 11; i++) {
            if (battleShip[firstCoordinate][i].equals("O ")) {
                return false;
            }

            if (battleShip[firstCoordinate][i].equals("M ") || battleShip[firstCoordinate][i].equals("~ ")) {
                break;
            }
        }

        for (int i = firstCoordinate; i < 11; i++) {
            if (battleShip[i][secondCoordinate].equals("O ")) {
                return false;
            }

            if (battleShip[i][secondCoordinate].equals("M ") || battleShip[i][secondCoordinate].equals("~ ")) {
                break;
            }
        }

        for (int i = secondCoordinate; i > 0; i--) {
            if (battleShip[firstCoordinate][i].equals("O ")) {
                return false;
            }

            if (battleShip[firstCoordinate][i].equals("M ") || battleShip[firstCoordinate][i].equals("~ ")) {
                break;
            }
        }

        for (int i = firstCoordinate; i > 0; i--) {
            if (battleShip[i][secondCoordinate].equals("O ")) {
                return false;
            }

            if (battleShip[i][secondCoordinate].equals("M ") || battleShip[i][secondCoordinate].equals("~ ")) {
                break;
            }
        }
        return true;
    }

    public static boolean checkAllShipsDestroyed(String[][] battleShip) {
        for (String[] value : battleShip) {
            for (String string : value) {
                if (string.equals("O ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
