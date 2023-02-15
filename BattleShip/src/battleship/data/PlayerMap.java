package battleship.data;

import static battleship.logic.MapBuilder.*;
import static battleship.logic.MapBuilder.fillBattleShip;

/**
 * todo Document type Map
 */
public class PlayerMap {
    private final String[][] battleField = replaceWithNumbers(replaceWithLetters(fillBattleShip()));
    private final String[][] battleFieldWithFog = replaceWithNumbers(replaceWithLetters(fillBattleShip()));
    private final String playerName;

    public PlayerMap(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }


    public String getMapString(boolean includeFog) {
        String[][] targetMas = includeFog ? battleFieldWithFog : battleField;
        StringBuilder stringBuilder = new StringBuilder();

        for (String[] strings : targetMas) {
            for (String string : strings) {
                stringBuilder.append(string);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public boolean isShipPlacementAllowed(int[] inputedCoordinates) {
        int i = 0;
        int x1, y1, x2, y2;

        while (i < inputedCoordinates.length) {
            x1 = inputedCoordinates[i];
            y1 = inputedCoordinates[i + 1];
            x2 = inputedCoordinates[i + 2];
            y2 = inputedCoordinates[i + 3];

            if (x1 == x2) {
                if (y1 > y2) {
                    int temp = y1;
                    y1 = y2;
                    y2 = temp;
                }

                for (int k = y1; k <= y2; k++) {
                    if (x1 > 1 && battleField[x1 - 1][k].equals("O ") ||
                        x1 < 10 && battleField[x1 + 1][k].equals("O ") ||
                        k > 1 && battleField[x1][k - 1].equals("O ") ||
                        k < 10 && battleField[x1][k + 1].equals("O ") ||
                        battleField[x1][k].equals("O ") ||
                        x1 > 1 && k > 1 && battleField[x1 - 1][k - 1].equals("O ") ||
                        x1 < 10 && k > 1 && battleField[x1 + 1][k - 1].equals("O ") ||
                        x1 > 1 && k < 10 && battleField[x1 - 1][k + 1].equals("O ") ||
                        x1 < 10 && k < 10 && battleField[x1 + 1][k + 1].equals("O ")) {

                        return false;
                    }
                }
            } else if (y1 == y2) {
                if (x1 > x2) {
                    int temp = x1;
                    x1 = x2;
                    x2 = temp;
                }

                for (int m = x1; m <= x2; m++) {
                    if (m > 1 && battleField[m - 1][y1].equals("O ") ||
                        m < 10 && battleField[m + 1][y1].equals("O ") ||
                        y1 > 1 && battleField[m][y1 - 1].equals("O ") ||
                        y1 < 10 && battleField[m][y1 + 1].equals("O ") ||
                        battleField[m][y1].equals("O ") ||
                        m > 1 && y1 > 1 && battleField[m - 1][y1 - 1].equals("O ") ||
                        m < 10 && y1 > 1 && battleField[m + 1][y1 - 1].equals("O ") ||
                        m > 1 && y1 < 10 && battleField[m - 1][y1 + 1].equals("O ") ||
                        m < 10 && y1 < 10 && battleField[m + 1][y1 + 1].equals("O ")) {
                        return false;
                    }
                }
            }

            i += 4;
        }
        return true;
    }

    public boolean checkAllShipsDestroyed() {
        for (String[] value : battleField) {
            for (String string : value) {
                if (string.equals("O ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void placeShipOnTheMap(int[] coordinates) {
        int i = 0;
        while (i < coordinates.length) {
            int x1, y1, x2, y2;
            x1 = coordinates[i];
            y1 = coordinates[i + 1];
            x2 = coordinates[i + 2];
            y2 = coordinates[i + 3];

            if (x1 == x2) {
                if (y1 > y2) {
                    int temp = y1;
                    y1 = y2;
                    y2 = temp;
                }

                for (int k = y1; k <= y2; k++) {
                    battleField[x1][k] = "O ";
                }
            } else if (y1 == y2) {
                if (x1 > x2) {
                    int temp = x1;
                    x1 = x2;
                    x2 = temp;
                }

                for (int m = x1; m <= x2; m++) {
                    battleField[m][y1] = "O ";
                }
            }

            i += 4;
        }
    }

    public ShootResult shoot(int x1, int y1) {
        if (battleField[x1][y1].equals("O ") || battleField[x1][y1].equals("X ")) {
            battleField[x1][y1] = "X ";
            //battleFieldWithFog[x1][y1] = "X ";

            if (isFullShipDestroyed(battleField, x1, y1)) {
                return ShootResult.SANK;

            } else {

                return ShootResult.HIT;
            }

        } else {
            battleField[x1][y1] = "M ";
            //battleFieldWithFog[x1][y1] = "M ";

            return ShootResult.MISS;
        }

    }

    private static boolean isFullShipDestroyed(String[][] battleShip, int firstCoordinate, int secondCoordinate) {
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


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String[] strings : battleField) {
            for (String string : strings) {
                stringBuilder.append(string);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}

