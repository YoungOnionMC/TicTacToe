import java.util.Scanner;

public class Controller {

    private Model model;
    private Scanner scanner;
    private boolean running = true;

    public Controller() {
        model = new Model();
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            while (running) {
                Input playerChar = null;
                while (playerChar == null) {
                    System.out.println("Enter move in the form <xPos yPos character>");
                    playerChar = getInput();
                }
                var index = validateInput(playerChar);
                if (index != -1) {
                    model.updatePos(index, playerChar.c);
                }
                View.render(model.getState(), 3, 3);
                validateGameState(model.getState());
            }
            System.out.println("Want to play again? [y or n]");

            if (scanner.hasNext() && scanner.nextLine().equals("y")) {
                model.clearBoard();
                setRunning(true);
            } else {
                break;
            }
        }
    }

    private void setRunning(boolean running) {
        this.running = running;
    }

    private Input getInput() {
        if (scanner.hasNext()) {
            String s = scanner.nextLine();
            if (s.length() != 5) {
                System.out.println("Enter move in the form <xPos yPos character> where xPos and yPos are 0, 1, or 2 and character is either \'o\' or \'x\'");
                return null;
            }
            return new Input(Character.getNumericValue(s.charAt(0)), Character.getNumericValue(s.charAt(2)), s.charAt(4));
        }
        return null;
    }

    private int validateInput(Input input) {
        if(input.x < 0 || input.x > 2 || input.y < 0 || input.y > 2) {
            System.out.println("move must be in bounds of the board, was " + input.x + ", " + input.y);
            return -1;
        }
        return input.x * 3 + input.y;
    }

    private void validateGameState(char[] state) {
        boolean winnerFound = false;
        char winnerChar = 0;

        for(int i = 0; i < 3; i++) {
            // check horizontals
            char a = state[i * 3 + 0];
            char b = state[i * 3 + 1];
            char c = state[i * 3 + 2];
            if (a == b && b == c && (a == 'x' || a == 'o')) {
                winnerFound = true;
                winnerChar = a;
                break;
            }

            // check verticals
            a = state[0 + i];
            b = state[3 + i];
            c = state[6 + i];
            if (a == b && b == c && (a == 'x' || a == 'o')) {
                winnerFound = true;
                winnerChar = a;
                break;
            }
        }

        if(!winnerFound) {
            // diagonals
            char a = state[0 * 3 + 0];
            char b = state[1 * 3 + 1];
            char c = state[2 * 3 + 2];
            if(a == b && b == c && (a == 'x' || a == 'o')) {
                winnerFound = true;
                winnerChar = a;
            }

            a = state[0 * 3 + 2];
            b = state[1 * 3 + 1];
            c = state[2 * 3 + 0];
            if (a == b && b == c && (a == 'x' || a == 'o')) {
                winnerFound = true;
                winnerChar = a;
            }
        }

        if(winnerFound) {
            System.out.println(winnerChar + " is the winner!");
            setRunning(false);
        }

    }

    record Input(int x, int y, char c) {}
}
