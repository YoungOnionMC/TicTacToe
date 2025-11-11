public class View {

    public static void render(char[] state, int xi, int yi) {
        for (int x = 0; x < xi; x++) {
            for (int y = 0; y < yi; y++){

                char c = state[x * 3 + y];
                if (c == 0) {
                    System.out.print("*");
                } else {
                    System.out.print(c);
                }
            }
            System.out.println();
        }
    }
}
