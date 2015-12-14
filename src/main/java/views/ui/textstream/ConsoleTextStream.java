package views.ui.textstream;

/**
 * Created by eunderhi on 14/12/15.
 */
public class ConsoleTextStream extends TextStream {

    @Override
    public void update() {

        display();
    }

    @Override
    public void display() {
        System.out.println(text);
    }

}
