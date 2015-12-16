package views.ui.progressbar;

/**
 * Created by eunderhi on 16/12/15.
 */
public class ConsoleProgressBar extends ProgressBar {

    private int length = 20;
    private String character = "#";

    @Override
    public void update() {
       display();
    }

    @Override
    public void display() {
        System.out.println(makeBar() + "\r");
        if(getPercentDone() == 100) {
            System.out.println("done.");
        }
    }

    private String makeBar() {
        int barLength = getPercentDone() % length;
        String bar = "";

        for(int i = 0; i < barLength; i++) {
            bar += character;
        }

        return bar;

    }
}
