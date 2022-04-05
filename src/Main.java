import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = Menu.getInstance();

        Scanner sc = new Scanner(System.in);

        menu.doPopulateData(sc);

        while (true) {
            menu.askMainMenu(sc);
            menu.playMainOption(sc);

            if (menu.getMainOption() == 0) break;
            menu.askSecondaryMenu(sc);
            if (menu.getSecondaryoption() == 0) break;
        }

        sc.close();
    }
}
