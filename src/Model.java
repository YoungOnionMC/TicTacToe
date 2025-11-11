public class Model {


    private char[] state;

    boolean xPlayer = true;

    public Model() {
        state = new char[9];
    }

    public void clearBoard() {
        for (int i = 0; i < state.length; i++) {
            state[i] = 0;
        }
        xPlayer = true;
    }

    public void updatePos(int index, char c) {
        if (xPlayer ^ (c == 'x')) {
            System.out.println("it is player " + (xPlayer ? "x" : "o") + " turn");
            return;
        }
        if(state[index] != 0) {
            System.out.println("position already occupied by a piece");
            return;
        }

        if (c != 'x' && c != 'o') {
            System.out.println("invalid character play, must be either \'x\' or \'o\'");
            return;
        }

        state[index] = c;
        xPlayer = !(c == 'x');



    }

    public char[] getState() {
        return state;
    }
}
