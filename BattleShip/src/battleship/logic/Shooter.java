package battleship.logic;

import static battleship.logic.MapBuilder.getCoordinatesFromInterval;
import static battleship.ui.UserInterface.printBattleShipMap;
import static battleship.ui.UserInterface.readInputFromUser;

/**
 * todo Document type Shooter
 */
public class Shooter {

    public static void placeShootOnTheMap(String[][] battleShip) {
        while(true) {

            int[] coordinatesOfShoot = getCoordinatesFromInterval(readInputFromUser());
            int x1, y1;
            x1 = coordinatesOfShoot[0];
            y1 = coordinatesOfShoot[1];

            if (x1 < 1 || x1 > 10 || y1 < 1 || y1 > 10) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                continue;
            }
            if (battleShip[x1][y1].equals("O ")) {
                battleShip[x1][y1] = "X ";
                printBattleShipMap(battleShip);
                System.out.println("You hit a ship!");
                break;

            } else if (battleShip[x1][y1].equals("~ ")) {
                battleShip[x1][y1] = "M ";
                printBattleShipMap(battleShip);
                System.out.println("You missed!");
                break;
            }

        }
    }
}
