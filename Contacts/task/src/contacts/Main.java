package contacts;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.phoneBook();
    }

    public void phoneBook() {
        Command cmd = new Command();
        cmd.menu();
    }
}



