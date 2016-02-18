package views.lookandfeel.patternfly;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * Created by aabulawi on 10/04/15.
 */
public class PatternflyScrollBarUI extends BasicScrollBarUI {

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new CustomArrowButton(orientation,
                new Color(0xEFEFEF),
                new Color(0xEFEFEF),
                Color.GRAY,
                new Color(0xEFEFEF));
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new CustomArrowButton(orientation,
                new Color(0xEFEFEF),
                new Color(0xEFEFEF),
                Color.GRAY,
                new Color(0xEFEFEF));
    }


    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds)
    {
        if(thumbBounds.isEmpty() || !scrollbar.isEnabled())     {
            return;
        }

        int w = thumbBounds.width;
        int h = thumbBounds.height;
        g.translate(thumbBounds.x, thumbBounds.y);
        g.setColor(new Color(0xCDCDCD));
        g.fillRect(0, 0, w-1, h-1);
        g.translate(-thumbBounds.x, -thumbBounds.y);
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds)
    {
        g.setColor(new Color(0xEFEFEF));
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);

        /*if(trackHighlight == DECREASE_HIGHLIGHT)        {
            paintDecreaseHighlight(g);
        }
        else if(trackHighlight == INCREASE_HIGHLIGHT)           {
            paintIncreaseHighlight(g);
        }*/
    }


    public static ComponentUI createUI(JComponent c)    {
        return new PatternflyScrollBarUI();
    }

    public class CustomArrowButton extends BasicArrowButton{
        Color shadow, darkShadow, highlight;

        public CustomArrowButton(int direction, Color background, Color shadow, Color darkShadow, Color highlight) {
            super(direction, background, shadow, darkShadow, highlight);
            this.shadow = shadow;
            this.darkShadow = darkShadow;
            setBorder(BorderFactory.createEmptyBorder());
        }

        public void paint(Graphics g) {
            Color origColor;
            boolean isEnabled;
            int w, h, size;

            w = getSize().width;
            h = getSize().height;
            origColor = g.getColor();
            isEnabled = isEnabled();

            g.setColor(getBackground());
            g.fillRect(1, 1, w, h);

            /// Draw the proper Border
            if (getBorder() != null && !(getBorder() instanceof UIResource)) {
                paintBorder(g);
            } /*else if (isPressed) {
                g.setColor(shadow);
                g.drawRect(0, 0, w, h);
            }*/ else {
                // Using the background color set above
                g.drawLine(0, 0, 0, h);
                g.drawLine(1, 0, w, 0);

//                g.setColor(highlight);    // inner 3D border
//                g.drawLine(1, 1, 1, h-3);
//                g.drawLine(2, 1, w-3, 1);
//
//                g.setColor(shadow);       // inner 3D border
//                g.drawLine(1, h-2, w-2, h-2);
//                g.drawLine(w-2, 1, w-2, h-3);

                g.setColor(darkShadow);     // black drop shadow  __|
                /*g.drawLine(0, h-1, w-1, h-1);
                g.drawLine(w-1, h-1, w-1, 0);*/
            }

            // If there's no room to draw arrow, bail
            if(h < 5 || w < 5)      {
                g.setColor(origColor);
                return;
            }

           /* if (isPressed) {
                g.translate(1, 1);
            }*/

            // Draw the arrow
            size = Math.min((h - 4) / 3, (w - 4) / 3);
            size = Math.max(size, 2);
            paintTriangle(g, (w - size) / 2, (h - size) / 2,
                    size, direction, isEnabled);
/*// Reset the Graphics back to it's original settings
            if (isPressed) {
                g.translate(-1, -1);
            }*/

            g.setColor(origColor);

        }
    }

}