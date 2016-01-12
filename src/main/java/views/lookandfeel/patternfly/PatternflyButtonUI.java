package views.lookandfeel.patternfly;


import views.lookandfeel.UiResources;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

/**
 * Created by aabulawi on 13/04/15.
 */
public class PatternflyButtonUI extends BasicButtonUI {
    private Color topButtonColor;
    private Color bottomButtonColor;
    private Color buttonPressedShadow;
    private Color buttonStroke;
    private Color enabledForeground;
    private Border disabledBorder;
    private Border enabledBorder;

    public PatternflyButtonUI() {
        super();
    }

    public PatternflyButtonUI(Color enabledForeground, Color top, Color bottom, Color shadow, Color stroke) {
        this.enabledForeground = enabledForeground;
        topButtonColor = top;
        bottomButtonColor = bottom;
        buttonPressedShadow = shadow;
        buttonStroke = stroke;
    }

    @Override
    public void update(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();

        if (model.isEnabled()) {
            c.setForeground(enabledForeground);
            c.setBorder(enabledBorder);
            if (model.isRollover() && !model.isPressed()) {
                drawHoverButton(g, c);
            } else {
                drawButton(g, c, topButtonColor, bottomButtonColor);
            }
        } else {
            c.setForeground(UiResources.grayDisabledText);
            c.setBorder(disabledBorder);
            drawDisabledButton(g, c, UiResources.grayDisabledButton, UiResources.grayDisabledStroke);
        }
        paint(g2d, c);
    }

    private void drawDisabledButton(Graphics g, JComponent c, Color grayDisabledButton, Color grayDisabledStroke) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle rect = new Rectangle(0,0,c.getWidth(), c.getHeight());
        g2d.setPaint(grayDisabledButton);
        g2d.fill(rect);
        g2d.setStroke(new BasicStroke(1));
        g2d.draw(rect);
    }

    private void drawHoverButton(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(bottomButtonColor);
        Rectangle rect = new Rectangle(0, 0, c.getWidth(), c.getHeight());
        g2d.fill(rect);
    }

    @Override
    protected void paintButtonPressed(Graphics g, AbstractButton b) {
        drawButton(g, b, buttonPressedShadow, bottomButtonColor);
    }

    @Override
    protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(buttonStroke);
        g2d.setStroke(new BasicStroke());
        Rectangle rect = new Rectangle(-3,-3,b.getWidth()+3,b.getWidth()+3);
        g2d.draw(rect);
    }

    public static ComponentUI createUI(JComponent c) {
        return new PatternflyButtonUI();
    }

    private void drawButton(Graphics g, JComponent c, Color top, Color bottom) {
        Graphics2D g2d = (Graphics2D) g;
        Paint gradient = new GradientPaint(c.getWidth() / 2, 0, top, c.getWidth() / 2, c.getHeight(), bottom);
        g2d.setPaint(gradient);
        Rectangle rect = new Rectangle(0, 0, c.getWidth(), c.getHeight());
        g2d.fill(rect);
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        installColors(c);
    }

    private void installColors(JComponent c) {
        topButtonColor = topButtonColor == null ? UiResources.grayButtonLight : topButtonColor;
        bottomButtonColor = bottomButtonColor == null ? UiResources.grayButtonDark : bottomButtonColor;
        buttonPressedShadow = buttonPressedShadow == null ? UiResources.grayButtonShadow : buttonPressedShadow;
        buttonStroke = buttonStroke == null ? UiResources.grayButtonStroke : buttonStroke;
        enabledForeground = enabledForeground == null ? Color.BLACK : enabledForeground;
        installBorders();
    }

    private void installBorders() {
        enabledBorder = BorderFactory.createLineBorder(buttonStroke);
        disabledBorder = BorderFactory.createLineBorder(UiResources.grayDisabledStroke);
    }
}
