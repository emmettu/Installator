package views.lookandfeel.patternfly;

import views.lookandfeel.UiResources;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

/**
 * Created by thauser on 6/5/15.
 */
public class PatternflyProgressBarUI extends BasicProgressBarUI {

    protected Rectangle componentInnards = new Rectangle();
    protected Rectangle oldComponentInnards = new Rectangle();
    protected double animationDelta;
    protected int maxPosition;

    public PatternflyProgressBarUI() {
        super();
    }

    public static ComponentUI createUI(JComponent c) {
        return new PatternflyProgressBarUI();
    }

    @Override
    protected void installDefaults() {
        UIManager.put("ProgressBar.cycleTime", 1000);
        UIManager.put("ProgressBar.repaintInterval", 10);
        super.installDefaults();
        this.progressBar.setForeground(UiResources.progressBarGradientDark);
        this.progressBar.setBackground(Color.WHITE);
        this.progressBar.setBorder(BorderFactory.createEmptyBorder(-3, 0, -3, 0));
    }

    @Override
    protected Rectangle getBox(Rectangle rectangle) {
        int currentFrame = getAnimationIndex();

        if (sizeChanged() || animationDelta == 0.0 || maxPosition == 0.0) {
            updateSizes();
        }

        rectangle = getGenericBox(rectangle);
        int filledAmount = getAmountFull(progressBar.getInsets(), getComponentInnards().width, getComponentInnards().height);
        adjustDelta(filledAmount);
        if (rectangle == null) {
            return null;
        }
        if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
            rectangle.x = filledAmount + (int) Math.round(animationDelta * currentFrame);
            if (rectangle.x >= maxPosition) {
                //reset
                rectangle.x = filledAmount;
            }
        } else {
            if (rectangle.y >= maxPosition) {
                rectangle.y = getAmountFull(progressBar.getInsets(),
                        getComponentInnards().width,
                        getComponentInnards().height)
                        + (int) Math.round(animationDelta * currentFrame);
            } else {
                rectangle.y = maxPosition - (int) Math.round(animationDelta * currentFrame);
            }
        }
        return rectangle;
    }

    private void adjustDelta(int filledAmount) {
        animationDelta = (getComponentInnards().width - filledAmount) / getFrameCount();
    }

    protected Rectangle getGenericBox(Rectangle rectangle) {
        if (rectangle == null) {
            rectangle = new Rectangle();
        }

        if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
            rectangle.width = getBoxLength(getComponentInnards().width, getComponentInnards().height);
            if (rectangle.width < 0) {
                rectangle = null;
            } else {
                rectangle.height = getComponentInnards().height;
                rectangle.y = getComponentInnards().y;
            }
        }
        return rectangle;
    }

    private void updateSizes() {
        int length;

        if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
            length = getBoxLength(getComponentInnards().width,
                    getComponentInnards().height);
            maxPosition = getComponentInnards().x + getComponentInnards().width
                    - length;

        } else { //VERTICAL progress bar
            length = getBoxLength(getComponentInnards().height,
                    getComponentInnards().width);
            maxPosition = getComponentInnards().y + getComponentInnards().height
                    - length;
        }
    }

    protected boolean sizeChanged() {
        if ((oldComponentInnards == null) || (componentInnards == null)) {
            return true;
        }

        oldComponentInnards.setRect(componentInnards);
        componentInnards = SwingUtilities.calculateInnerArea(progressBar,
                componentInnards);
        return !oldComponentInnards.equals(componentInnards);
    }

    protected Rectangle getComponentInnards() {
        return componentInnards;
    }

    @Override
    protected int getBoxLength(int availableLength, int otherDimension) {
        return Math.round(availableLength / 75);
    }

    @Override
    protected void paintIndeterminate(Graphics originalGraphic, JComponent c) {
        paintDeterminate(originalGraphic, c);
        super.paintIndeterminate(originalGraphic, c);
    }

    @Override
    protected void paintDeterminate(Graphics originalGraphic, JComponent c) {
        if (originalGraphic instanceof Graphics2D) {
            Insets barInsets = this.progressBar.getInsets();
            int graphicWidth = this.progressBar.getWidth() - (barInsets.right + barInsets.left);
            int graphicHeight = this.progressBar.getHeight() - (barInsets.top + barInsets.bottom);
            if (graphicWidth > 0 && graphicHeight > 0) {
                int amountToFill = this.getAmountFull(barInsets, graphicWidth, graphicHeight);
                Graphics2D g2d = (Graphics2D) originalGraphic;
                g2d.setPaint(
                        new GradientPaint(barInsets.left, graphicHeight / 2 + barInsets.top, UiResources.progressBarGradientLight,
                                amountToFill + barInsets.left, graphicHeight / 2 + barInsets.top, UiResources.progressBarGradientDark));
                g2d.fillRect(barInsets.left, barInsets.bottom, amountToFill + barInsets.left, graphicHeight);
                if (this.progressBar.isStringPainted()) {
                    this.paintString(originalGraphic, barInsets.left, barInsets.top, graphicWidth, graphicHeight, amountToFill, barInsets);
                }
            }
        }
    }
}
