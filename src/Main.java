import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = Menu.getInstance();

        Scanner sc = new Scanner(System.in);

        menu.doPopulateData(sc);

        while (true) {
            menu.askMainMenu(sc);
            menu.playMainOption(sc);

            if (menu.mainOption == 0) break;
            menu.askSecondaryMenu(sc);
            if (menu.secondaryOption == 0) break;
        }

        sc.close();
    }
}
