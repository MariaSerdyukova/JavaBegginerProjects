package battleship.logic;

import battleship.data.PlayerMap;
import battleship.data.ShootResult;

import java.util.Scanner;

import static battleship.logic.MapBuilder.getCoordinatesFromInterval;
import static battleship.ui.UserInterface.readInputFromUser;

/**
 * todo Document type Shooter
 */
public class Shooter {

    public static void placeShootOnTheMap(PlayerMap playerMap) {
        while (true) {

            int[] coordinatesOfShoot = getCoordinatesFromInterval(readInputFromUser());
            int x1, y1;
            x1 = coordinatesOfShoot[0];
            y1 = coordinatesOfShoot[1];

            if (x1 < 1 || x1 > 10 || y1 < 1 || y1 > 10) {
                System.out.println("Error! You entered the wrong coordinates! Try again: ");
                continue;
            }


            var result = playerMap.shoot(x1, y1);
            //System.out.println(playerMap.getMapString(true));
            switch (result) {
                case SANK -> System.out.println("You sank a ship! Specify a new target:");
                case HIT -> System.out.println("You hit a ship!");
                case MISS -> System.out.println("You missed.");
            }
            break;
        }
    }

    public static void shootUntilTheEnd(PlayerMap playerMap) {
        do {
            placeShootOnTheMap(playerMap);
        } while (!playerMap.checkAllShipsDestroyed());
    }

    public static PlayerMap shootUntilTheEnd(PlayerMap first, PlayerMap second) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Press Enter and pass the move to another player");
            if (scanner.nextLine().equals("")) {
                System.out.println("...");

                System.out.println(second.getMapString(true) + "---------------------\n" + first.getMapString(false));

                System.out.println(first.getPlayerName() + ", it's your turn:");
                placeShootOnTheMap(first);

                System.out.print("Press Enter and pass the move to another player");
                if (scanner.nextLine().equals("")) {
                    System.out.println("...");

                    System.out.println(first.getMapString(true) + "---------------------\n" + second.getMapString(false));

                    System.out.println(second.getPlayerName() + ", it's your turn:");
                    placeShootOnTheMap(second);
                }
            }

        } while (!first.checkAllShipsDestroyed() && !second.checkAllShipsDestroyed());
        if(first.checkAllShipsDestroyed())
        {
            return first;
        }
        else{
            return second;
        }
    }
}
