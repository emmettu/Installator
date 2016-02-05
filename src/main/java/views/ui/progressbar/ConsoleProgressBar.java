package views.ui.progressbar;

import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;

/**
 * Created by eunderhi on 16/12/15.
 * A progress bar view for console mode.
 */
public class ConsoleProgressBar extends ProgressBar {

    private int length = 20;
    private String character = "█";
    private String antiCharacter = " ";
    private String prompt = "";
    private String endPrompt = "";

    @Override
    public void update() {
       display();
    }

    @Override
    public void display() {
        if(getPercentDone() == 100) {
            System.out.println(prompt + makeBar() + endPrompt);
        }
        else {
            System.out.print(prompt + makeBar() + "\r");
        }
    }

    private String makeBar() {
        int percentDone = getPercentDone();
        int barLength = percentDone * length / 100;

        String bar = "|";
        for(int i = 0; i < barLength; i++) {
            bar += character;
        }
        for(int i = barLength; i < length; i++) {
            bar += antiCharacter;
        }
        bar += "|";

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

    @Override
    protected void onControllerFail(ControllerFailException e) {

    }

    @Override
    protected void onControllerWarn(ControllerWarnException e) {

    }
}
