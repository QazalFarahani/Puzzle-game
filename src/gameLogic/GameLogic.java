package gameLogic;

import interfaces.Request;
import models.Location;
import models.PuzzlePiece;
import utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GameLogic {
    private boolean gameFinished;
    private static String gameState = "#";
    private static ArrayList<PuzzlePiece> puzzlePieces;
    private static int missingPiece = 0;
    private ArrayList<Integer> piecesRandomOrder;
    private ArrayList<Request> requests;

    public GameLogic() {
        init();
    }

    private void init() {
        missingPiece = 7;
        piecesRandomOrder = new ArrayList<>(Arrays.asList(0, 5, 6, 7, 4, 3, 2, 8, 1));
        puzzlePieces = new ArrayList<>();
        requests = new ArrayList<>();
        addPieces();
    }

    public static void swapPieces(int i, int j) {
        PuzzlePiece copy = puzzlePieces.get(i).getClone();
        puzzlePieces.get(i).setImage(puzzlePieces.get(j).getImage());
        puzzlePieces.get(i).setPieceNumber(puzzlePieces.get(j).getPieceNumber());
        puzzlePieces.get(j).setImage(copy.getImage());
        puzzlePieces.get(j).setPieceNumber(copy.getPieceNumber());


        if (gameFinished()) {
            gameState = "finished";
        }
    }

    private void addPieces() {
        for (int i = 0; i < 9; i++) {
            if (this.getMissingPiece() != i) {
                puzzlePieces.add(new PuzzlePiece(piecesRandomOrder.get(i) + 1 + ".png", new Location(Constants.height / 3 * (i % 3), Constants.width / 3 * (i / 3))));
            } else {
                puzzlePieces.add(new PuzzlePiece("missing.jpg", new Location(Constants.height / 3 * (i % 3), Constants.width / 3 * (i / 3))));
            }
        }
    }

    public boolean isSolvable() {
        if (solvable(missingPiece, piecesRandomOrder))
            return true;
        else
            return false;
    }

    public void addRequest(RequestType requestType) {
        requests.add(requestType);
    }

    private static boolean solvable(int missingPiece, ArrayList<Integer> piecesOrder) {
        int inversionCount = 0;
        for (int i = 0; i < piecesOrder.size(); i++) {
            for (int j = i + 1; j < piecesOrder.size(); j++) {
                if (piecesOrder.get(i) > piecesOrder.get(j)) {
                    inversionCount += 1;
                }
            }
        }

        int parity = inversionCount % 2;

        int distanceOfMissingPiece = (2 - (missingPiece % 3)) + (2 - (missingPiece / 3));

        parity ^= (distanceOfMissingPiece % 2);
        if (parity == 0) {
            return true;
        }
        return false;
    }

    public void executeRequests() {
        for (Iterator<Request> requestIterator = requests.iterator(); requestIterator.hasNext(); ) {
            Request request = requestIterator.next();
            request.execute();
            requestIterator.remove();
        }
    }

    public enum RequestType implements Request {

        MOVE_UP {
            public void execute() {
                moveUp();
            }
        },
        MOVE_RIGHT {
            public void execute() {
                moveRight();
            }
        }, MOVE_LEFT {
            public void execute() {
                moveLeft();
            }
        }, MOVE_DOWN {
            public void execute() {
                moveDown();
            }
        }

    }

    private static void moveRight() {
        if (missingPiece % 3 == 2) {
            return;
        }
        swapPieces(missingPiece, missingPiece + 1);
        missingPiece += 1;
    }

    private static void moveUp() {
        if (missingPiece <= 2) {
            return;
        }
        swapPieces(missingPiece, missingPiece - 3);
        missingPiece -= 3;
    }

    private static void moveDown() {
        if (missingPiece >= 6) {
            return;
        }
        swapPieces(missingPiece, missingPiece + 3);
        missingPiece += 3;
    }

    private static void moveLeft() {
        if (missingPiece % 3 == 0) {
            return;
        }
        swapPieces(missingPiece, missingPiece - 1);
        missingPiece -= 1;
    }


    private static boolean gameFinished() {
        for (int i = 0; i < 9; i++) {
            int pieceIdentifier = puzzlePieces.get(i).getPieceNumber();
            if (pieceIdentifier == 8) {
                continue;
            }

            if (pieceIdentifier != i) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<PuzzlePiece> getPuzzlePieces() {
        return puzzlePieces;
    }

    public int getMissingPiece() {
        return missingPiece;
    }

    public ArrayList<Integer> getPiecesRandomOrder() {
        return piecesRandomOrder;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }
}
