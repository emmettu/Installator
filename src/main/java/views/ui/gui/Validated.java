package views.ui.gui;

import models.ValidatorContainers.ValidatorContainer;

/**
 * Created by eunderhi on 12/02/16.
 */
public interface Validated {
    void validate();
    ValidatorContainer getValidationContainer();
}
