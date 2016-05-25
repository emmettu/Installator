package views.ui.gui.layout;

import java.awt.*;

/**
 * Created by eunderhi on 24/05/16.
 */
public class Constraints {

    private static final int baseIndent = 20;

    public static GridBagConstraints getBasicConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(40, baseIndent, 2, 20);
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        return gbc;
    }

    public static GridBagConstraints getBasicNoInsetsConstraints(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        return gbc;
    }

    public static GridBagConstraints getContentPanelConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.weightx = 10;
        gbc.weighty = 10;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        return gbc;
    }

    public static GridBagConstraints getTitleConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        return gbc;
    }

    public static GridBagConstraints getTitleConstraints(int gridBagAnchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = gridBagAnchor;
        gbc.gridx = 0;
        gbc.gridy = 0;
        return gbc;
    }

    public static GridBagConstraints createNonFullLineElementConstraint(int row, int col, int alignment, int indent) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, baseIndent + indent, 2, 20);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = alignment;
        gbc.gridheight = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridx = col;
        gbc.gridy = row;
        return gbc;
    }

    public static GridBagConstraints createFullLineElementConstraint(int row, int col, int indent) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridheight = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.insets = new Insets(2, baseIndent + indent, 2, 40);
        return gbc;
    }

    /**
     * Full line element with only base indent
     *
     * @param row
     * @param col
     * @return
     */
    public static GridBagConstraints createFullLineElementConstraint(int row, int col) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridheight = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.insets = new Insets(2, baseIndent, 2, 40);
        return gbc;
    }


    public static GridBagConstraints createCheckAndRadioConstraint(int row, int col, int indent, double weightx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridheight = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = weightx;
        gbc.weighty = 0;
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.insets = new Insets(0, baseIndent + indent, 0, 20);
        return gbc;
    }

    public static GridBagConstraints createAlignedElementConstraint(int row, int col, int indent, int fill) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = fill;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.insets = new Insets(2, baseIndent + indent, 2, 20);
        return gbc;
    }

    public static GridBagConstraints createFixedSizeAlignedElementConstraint(int row, int col, int indent, double weightx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = weightx;
        gbc.weighty = 0;
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.insets = new Insets(2, baseIndent + indent, 2, 20);
        return gbc;
    }

    public static GridBagConstraints createDividerConstraints(int row, int indent) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridheight = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 10000;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.insets = new Insets(2, baseIndent + indent, 2, 20);
        return gbc;
    }

}
