package views.ui.progressbar;

import views.ui.UIComponent;

/**
 * Created by eunderhi on 16/12/15.
 */
public abstract class ProgressBar extends UIComponent {

    private int percentDone;

    public int getPercentDone() {
        return percentDone;
    }

    public void setPercentDone(int percentDone) {
        this.percentDone = percentDone;
    }

}
