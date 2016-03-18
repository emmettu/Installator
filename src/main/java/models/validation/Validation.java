package models.validation;

import controllers.Validator;
import controllers.exceptions.ControllerFailException;
import controllers.exceptions.ControllerWarnException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunderhi on 16/02/16.
 * Encapsulates validation behaviour for some object
 */
public abstract class Validation<Data> {

    List<Validator<Data>> validators = new ArrayList<>();

    private List<List<ValidateAction>> controllers = new ArrayList<>(2);

    public enum Type { WARN, FAIL, SUCCESS }

    public Validation() {
        for (int i = 0; i < 3; i++) {
            controllers.add(new ArrayList<>());
        }
    }

    public boolean validate() {
        try {
            validateComponents();
        }
        catch (ControllerFailException e) {
            handleFail(e);
            return false;
        }
        return true;
    }

    public void validateComponents() throws ControllerFailException {
        for(Validator<Data> v : validators) {
            try {
                validateComponent(v);
            }
            catch (ControllerWarnException e) {
                handleWarn(e);
            }
        }
        handleSuccess();
    }

    public void addHook(ValidateAction c, Type t) {
        controllers.get(t.ordinal()).add(c);
    }

    protected void runHooks(Type t, Exception e) {
        controllers.get(t.ordinal()).forEach((c) -> c.go(e));
    }

    public void add(Validator<Data> v) {
        validators.add(v);
    }

    protected abstract void validateComponent(Validator<Data> v) throws ControllerWarnException, ControllerFailException;
    protected abstract void handleWarn(ControllerWarnException e);
    protected abstract void handleFail(ControllerFailException e);
    protected abstract void handleSuccess();

}
