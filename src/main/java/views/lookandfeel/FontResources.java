package views.lookandfeel;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Loads and manages custom fonts. Also keeps constants for special characters for
 * use in other classes
 * <p/>
 * Created by thauser on 3/26/15.
 */
public class FontResources {

    /**
     * Languages that are not supported by openSans
     */
    private static final List<String> UNSUPPORTED_LOCALS = Arrays.asList(new String[]{"chn", "jpn"});
    /**
     * Special characters in fontAwesome
     */
    public static final String checkmarkCharacter = "\uf00c";
    public static final String rightArrowCircle = "\uf0a9";
    public static final String chevronLeft = "\uf053";
    public static final String chevronRight = "\uf054";
    public static final String question = "\uf128";
    public static final String homeIcon = "\uf015";
    public static final String upIcon = "\uf148";
    public static final String folderIcon = "\uf07c";
    public static final String gridIcon = "\uf009";
    public static final String listIcon = "\uf00b";
    public static List<String> awesomeIcons;
    static {
        awesomeIcons = new ArrayList<String>();
        awesomeIcons.add(checkmarkCharacter);
        awesomeIcons.add(rightArrowCircle);
        awesomeIcons.add(chevronLeft);
        awesomeIcons.add(chevronRight);
        awesomeIcons.add(question);
    }
    public static final String buttonLeftIcon = "<font size='2' face='FontAwesome'>" + chevronLeft + "</font>";
    public static final String buttonRightIcon = "<font size='2' face='FontAwesome'>" + chevronRight + "</font>";

    private static Font openSansLight;
    private static Font openSansRegular;
    private static Font openSansExtraBold;
    private static Font fontAwesome;
    private static Font regularBaseFont;
    private static Font titleBaseFont;

    private static void loadOpenSansExtraBold() throws IOException, FontFormatException {
        InputStream is = FontResources.class.getResourceAsStream("/fonts/opensans-extrabold.ttf");
        openSansExtraBold = Font.createFont(0, is).deriveFont(13.0f);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(openSansExtraBold);
    }

    private static void loadOpenSansRegular() throws IOException, FontFormatException {
        InputStream is = FontResources.class.getResourceAsStream("/fonts/opensans-regular.ttf");
        openSansRegular = Font.createFont(0, is).deriveFont(13.0f);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(openSansRegular);
    }

    private static void loadOpenSansLight() throws IOException, FontFormatException {
        InputStream is = FontResources.class.getResourceAsStream("/fonts/opensans-light.ttf");
        openSansLight = Font.createFont(0, is).deriveFont(24.0f);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(openSansLight);
    }

    private static void loadFontAwesome() throws IOException, FontFormatException {
        InputStream is = FontResources.class.getResourceAsStream("/fonts/fontawesome-webfont.ttf");
        fontAwesome = Font.createFont(0, is).deriveFont(14.0f);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(fontAwesome);
    }

    private static void loadRegularBaseFont() throws IOException, FontFormatException {
        regularBaseFont = new Font(Font.DIALOG, Font.PLAIN, 12);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(regularBaseFont);
    }

    private static void loadTitleBaseFont() throws IOException, FontFormatException {
        titleBaseFont = new Font(Font.DIALOG, Font.PLAIN, 24);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(titleBaseFont);
    }

    public static Font getRegularBaseFont() {
        if(regularBaseFont == null) {
            try {
                loadRegularBaseFont();
            }
            catch (FontFormatException e) {
            }
            catch (IOException e) {
            }
        }
        return regularBaseFont;
    }

    public static Font getTitleBaseFont() {
        if(titleBaseFont == null) {
            try {
                loadTitleBaseFont();
            }
            catch (FontFormatException | IOException ignored) {}
        }
        return titleBaseFont;
    }

    public static Font getFontAwesome() {
        if (fontAwesome == null) {
            try {
                loadFontAwesome();
            } catch (FontFormatException | IOException ignored) {}
        }
        return fontAwesome;
    }

    public static Font getOpenSansLight() {
        if (openSansLight == null) {
            try {
                loadOpenSansLight();
            } catch (IOException | FontFormatException ignored) {}
        }
        return openSansLight;
    }

    public static Font getOpenSansRegular() {
        if (openSansRegular == null) {
            try {
                loadOpenSansRegular();
            } catch (IOException | FontFormatException ignored) {}
        }
        return openSansRegular;
    }

    public static Font getOpenSansExtraBold() {
        if (openSansExtraBold == null) {
            try {
                loadOpenSansExtraBold();
            } catch (IOException | FontFormatException ignored) {}
        }
        return openSansExtraBold;
    }

}
