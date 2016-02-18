package views.ui.gui;

import models.ValidatorContainers.Validation;

/**
 * Created by eunderhi on 12/02/16.
 */
public interface Validated {
    void validate();
    Validation validation();
}
