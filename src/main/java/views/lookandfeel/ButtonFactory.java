/*
 * IzPack - Copyright 2001-2008 Julien Ponge, All Rights Reserved.
 *
 * http://izpack.org/
 * http://izpack.codehaus.org/
 *
 * Copyright 2002 Jan Blok
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package views.lookandfeel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;


/**
 * This class makes it possible to use default buttons on macosx platform
 */
public class ButtonFactory {
    private static HashMap<String, String> buttonMnemonicMap = new HashMap<String, String>();
    private static HashMap<String, String> frameShortcuts = new HashMap<String, String>();
    private static boolean useHighlightButtons = false;
    private static boolean useButtonIcons = false;

    /**
     * Enable icons for buttons This setting has no effect on OSX
     */
    public static void useButtonIcons() {
        useButtonIcons(true);
    }

    /**
     * Enable or disable icons for buttons This setting has no effect on OSX
     *
     * @param useit flag which determines the behavior
     */
    public static void useButtonIcons(boolean useit) {
        if (System.getProperty("mrj.version") == null) {
            useButtonIcons = useit;
        }
    }

    /**
     * Enable highlight buttons This setting has no effect on OSX
     */
    public static void useHighlightButtons() {
        useHighlightButtons(true);
    }

    /**
     * Enable or disable highlight buttons This setting has no effect on OSX
     *
     * @param useit flag which determines the behavior
     */
    public static void useHighlightButtons(boolean useit) {
        if (System.getProperty("mrj.version") == null) {
            useHighlightButtons = useit;
        }
        useButtonIcons(useit);
    }

    public static JButton createButton(String text, Icon icon, Color color, int mnemonic) {
        JButton btn = null;
        if (useHighlightButtons) {
            if (useButtonIcons && icon != null) {
                btn = new HighlightJButton(text, icon, color);
            } else {
                btn = new HighlightJButton(text, color);
            }
        } else {
            if (useButtonIcons && icon != null) {
                btn = new AutoSizedButton(text, icon);
            } else {
                btn = new AutoSizedButton(text);
            }
        }

        if (text != null) {
            if (mnemonic == 0) {
                String key = findMnemonic(text);
                if (key != null) {
                    btn.setMnemonic(key.charAt(0));
                    buttonMnemonicMap.put(key, text);
                }
            } else {
                btn.setMnemonic(mnemonic);
                buttonMnemonicMap.put(String.valueOf(mnemonic), text);
            }
        }

        btn.setFont(FontResources.getOpenSansRegular());
        return btn;

    }

    public static JButton createButton(String text) {
        return createButton(text, null, null, 0);
    }

    public static JButton createButton(String text, int mnemonic) {
        return createButton(text, null, null, mnemonic);
    }

    public static JButton createButton(String text, Color color) {
        return createButton(text, null, color, 0);
    }

    public static JButton createButton(String text, Icon icon) {
        return createButton(text, icon, null, 0);
    }

    public static JButton createButton(String text, Icon icon, Color color) {
        return createButton(text, icon, color, 0);
    }

    public static JButton createButton(Icon icon, Color color) {
        return createButton(null, icon, color, 0);
    }

    public static JButton createButton(Action a, Color color) {
        if (useHighlightButtons) {
            return new HighlightJButton(a, color);
        } else {
            return new AutoSizedButton(a);
        }
    }

    /**
     * Finds an available key mnemonic for this button,
     * and sets it. If no mnemonic is possible, no mnemonic
     * is set.
     *
     * @param text Non-null text caption for the JButton.
     */
    public static String findMnemonic(String text) {
        String stepOne = text.toLowerCase().replaceAll("</*html>", "");
        String caption = stepOne.toLowerCase().replaceAll("<font.*>", "");

        String key = null;

        // Iterate through the characters in this button's
        // text until we either find a suitable mnemonic,
        // or else run out of characters.
        while (caption != null && caption.length() > 0) {
            // The mnemonic, or key.
            key = String.valueOf(caption.charAt(0));
            // If key is already in use:
            if (buttonMnemonicMap.containsKey(key) || frameShortcuts.containsKey(key)) {
                caption = caption.substring(1);
                continue;
            } else {
                // If key doesn't exist, this mnemonic
                // is available, so set it and finish.
                return key;
            }
        }
        return null;
    }

    public static void reserveInstallerFrameShortcuts(String[] shortcuts) {
        for (String shortcut : shortcuts) {
            frameShortcuts.put(findMnemonic(shortcut), shortcut);
        }
    }

    public static void clearInstallerFrameShortcuts() {
        frameShortcuts.clear();
    }

    public static void clearButtonMnemonics() {
        buttonMnemonicMap.clear();
    }

    private static JButton createGradientButton(String text, Color light, Color dark, Color stroke, Color shadow) {
        JButton gradientButton = new GradientButton(text, light, dark, stroke, shadow);
        gradientButton.setFont(FontResources.getOpenSansRegular());
        if (text != null) {
            String key = findMnemonic(text);
            if (key != null) {
                gradientButton.setMnemonic(key.charAt(0));
                buttonMnemonicMap.put(key, text);
            }
        }
        return gradientButton;
    }

    public static JButton createBlueGradientButton(String text, Icon icon) {
        JButton button = createBlueGradientButton(text);
        button.setIcon(icon);
        return button;
    }

    public static JButton createBlueGradientButton(String text) {
        JButton button = createGradientButton(text,
                UiResources.blueButtonLight,
                UiResources.blueButtonDark,
                UiResources.blueButtonStroke,
                UiResources.blueButtonShadow);
        button.setForeground(new Color(0xFFFFFF));
        return button;
    }

    public static JButton createGrayGradientButton(String text, Icon icon) {
        JButton button = createGrayGradientButton(text);
        button.setIcon(icon);
        return button;
    }

    public static JButton createGrayGradientButton(String text) {
        return createGradientButton(text,
                UiResources.grayButtonLight,
                UiResources.grayButtonDark,
                UiResources.grayButtonStroke,
                UiResources.grayButtonShadow);
    }

    public static JButton createConstrainedGrayGradientButton(String text, int minWidth) {
        JButton gradient = new GradientButton(text,
                UiResources.grayButtonLight,
                UiResources.grayButtonDark,
                UiResources.grayButtonStroke,
                UiResources.grayButtonShadow,
                minWidth);
        return gradient;
    }

    public static class AutoSizedButton extends JButton {
        private final int leftSpace = 10;
        private final int rightSpace = 10;
        private final int fontAwesomeWidth = 8;
        private final int buttonHeight = 25;
        private final int minimumWidth = 55;

        public AutoSizedButton(Icon icon) {
            super(icon);
        }

        public AutoSizedButton(String text) {
            super(text);
        }

        public AutoSizedButton(String text, Icon icon) {
            super(text, icon);
        }

        public AutoSizedButton(Action a) {
            super(a);
        }

        public AutoSizedButton() {
            super();
        }

        @Override
        public Dimension getPreferredSize() {
            if (this.getFont() != null) {
                int totalWidth = this.getStringWidth(this.getText());
                Dimension dimension;
                if (totalWidth >= minimumWidth) {
                    dimension = new Dimension(leftSpace + totalWidth + rightSpace, buttonHeight);
                } else {
                    dimension = new Dimension(leftSpace + minimumWidth + rightSpace, buttonHeight);
                }
                return dimension;
            } else {
                return new Dimension(0, 0);
            }
        }

        @Override
        public Dimension getMinimumSize() {
            return this.getPreferredSize();
        }

        private int getStringWidth(String s) {
            int totalWidth = 0;
            if (s != null) {
                FontMetrics metrics = this.getFontMetrics(this.getFont());
                String htmlStripped = stripHtml(s);
                if (containsFontAwesome(htmlStripped)) {
                    totalWidth = metrics.stringWidth(htmlStripped) + this.fontAwesomeWidth;
                } else {
                    totalWidth = metrics.stringWidth(htmlStripped);
                }
            }
            return totalWidth;
        }

        private boolean containsFontAwesome(String htmlStripped) {
            for (String icon : FontResources.awesomeIcons) {
                if (htmlStripped.contains(icon)) {
                    return true;
                }
            }
            return false;
        }

        /*
        Workaround for fontAwesome html formatting in some buttons
         */
        private String stripHtml(String text) {
            if (text != null) {
                String stripped = text.replaceAll("<[^>]+>", "");
                return stripped;
            } else {
                return "";
            }
        }
    }


    public static class GradientButton extends JButton {
        private Color light;
        private Color dark;
        private Color stroke;
        private Color shadow;
        private Color currentStroke;
        private final int minimumWidth = 55;
        private final int fontAwesomeWidth = 8;
        private final int leftSpace = 10;
        private final int rightSpace = 10;
        private final int buttonHeight = 25;

        public GradientButton() {

        }

        public GradientButton(String s, Color light, Color dark, Color stroke, Color mouseOver) {
            this(s, light, dark, stroke, mouseOver, 0);
        }

        public GradientButton(String s, Color light, Color dark, Color stroke, Color mouseOver, int minimumWidth) {
            super(s);
            this.setText(s);
            this.light = light;
            this.dark = dark;
            this.stroke = stroke;
            this.currentStroke = stroke;
            this.shadow = mouseOver;
            setContentAreaFilled(false);
            this.setRolloverEnabled(true);
        }

        @Override
        public void setText(String text) {
            super.setText(text);
            if (this.getFont() != null) {
                FontMetrics metrics = getFontMetrics(this.getFont());
                String htmlStripped = stripHtml(getText());
                int textWidth = metrics.stringWidth(htmlStripped);
                int totalWidth = textWidth + this.fontAwesomeWidth;
                Dimension d;
                if (totalWidth >= minimumWidth) {
                    d = new Dimension(leftSpace + totalWidth + rightSpace, buttonHeight);
                } else {
                    d = new Dimension(leftSpace + minimumWidth + rightSpace, buttonHeight);
                }
                this.setPreferredSize(d);
            }
        }

        /*
        Workaround for fontAwesome html formatting in some buttons
         */
        private String stripHtml(String text) {
            String step1 = text.replaceAll("</*html>", "");
            String step2 = step1.replaceAll("<font.*>", "");
            return step2;
        }

        private Paint getPressPaint() {
            return UiResources.getGradientPaint(this, shadow, dark);
        }

        private Paint getHoverPaint() {
            return UiResources.getGradientPaint(this, dark, dark);
        }

        private Paint getNormalPaint() {
            return UiResources.getGradientPaint(this, light, dark);
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Rectangle2D rectangle = new Rectangle2D.Float(0, 0, this.getWidth(), this.getHeight());
            Paint currentPaint;
            if (isEnabled()) {
                currentStroke = stroke;
                paintBorder(g);
                currentPaint = getNormalPaint();
                if (this.getModel().isRollover()) {
                    currentPaint = getHoverPaint();
                }
                if (this.getModel().isPressed()) {
                    currentPaint = getPressPaint();
                }
            } else {
                // disabled
                currentPaint = UiResources.sideNavigationBackGround;
                currentStroke = UiResources.grayDisabledStroke;
                paintBorder(g);
            }
            g2d.setPaint(currentPaint);
            g2d.fill(rectangle);
            super.paintComponent(g);
        }

        @Override
        public void paintBorder(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(1));
            g2d.setColor(currentStroke);
            Rectangle2D border = new Rectangle2D.Float(0, 0, this.getWidth(), this.getHeight()).getBounds2D();
            // Explanation: the standard drawRect call puts the border on the wrong pixel; so we adjust for it
            g2d.drawRect(0, 0, (int) border.getWidth() - 1, (int) border.getHeight() - 1);
        }

        public Color getLight() {
            return light;
        }

        public Color getDark() {
            return dark;
        }

        public Color getShadow() {
            return shadow;
        }

        public Color getStroke() {
            return stroke;
        }
    }
}
