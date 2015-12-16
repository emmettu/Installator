package views.ui.progressbar;

/**
 * Created by eunderhi on 16/12/15.
 */
public class ConsoleProgressBar extends ProgressBar {

    private int length = 20;
    private String character = "=";
    private String antiCharacter = "-";
    private String prompt = "";
    private String endPrompt = "";

    @Override
    public void update() {
       display();
    }

    @Override
    public void display() {
        System.out.print(prompt + makeBar() + "\r");
        if(getPercentDone() == 100) {
            System.out.println(prompt + makeBar() + endPrompt);
        }
    }

    private String makeBar() {
        int percentDone = getPercentDone();
        int barLength = percentDone * length / 100;

        String bar = "[";
        for(int i = 0; i < barLength; i++) {
            bar += character;
        }
        for(int i = barLength; i < length; i++) {
            bar += antiCharacter;
        }
        bar += "]";

        return bar;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setEndPrompt(String endPrompt) {
        this.endPrompt = endPrompt;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
