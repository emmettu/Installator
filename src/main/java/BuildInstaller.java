import controllers.textinput.PathInputController;
import controllers.unpacker.Unpacker;
import models.packaging.StandardPackage;
import views.ui.button.ConsoleButton;
import views.ui.textinput.ConsoleTextInputField;

/**
 * Created by eunderhi on 25/11/15.
 */
public class BuildInstaller {

    public static void main(String args[]) {
        StandardPackage standardPackage = new StandardPackage("wildfly-10.0.0.CR4");
        standardPackage.addExclude("docs/contrib");
        Unpacker unpacker = new Unpacker();
        unpacker.setPackage(standardPackage);
        ConsoleTextInputField field = new ConsoleTextInputField();
        field.setPrompt("Enter the unpacking directory: ");
        ConsoleButton button = new ConsoleButton();
        button.setText("Press enter to continue.");
        PathInputController controller = new PathInputController();
        controller.setTextInputField(field);
        controller.setPackage(standardPackage);
        button.addController(unpacker);


        field.update();
        System.out.println(standardPackage.getUnpackDirectory());
        button.update();
    }

}
