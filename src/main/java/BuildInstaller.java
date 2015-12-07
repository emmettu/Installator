import controllers.textinput.PathInputController;
import controllers.unpacker.Unpacker;
import models.packaging.StandardPackage;
import org.json.JSONObject;
import views.ui.textinput.ConsoleTextInputField;
import views.ui.textinput.TextInputField;

/**
 * Created by eunderhi on 25/11/15.
 */
public class BuildInstaller {

    public static void main(String args[]) {
        StandardPackage standardPackage = new StandardPackage("wildfly-10.0.0.CR4");
        standardPackage.addExclude("docs/contrib");
        //standardPackage.setUnpackDirectory("/home/eunderhi/tmp/");
        Unpacker unpacker = new Unpacker();
        unpacker.setPackageToUnpack(standardPackage);
        TextInputField inputField = new ConsoleTextInputField();
        PathInputController controller = new PathInputController();
        controller.setTextInputField(inputField);
        controller.setPackage(standardPackage);

        System.out.println(standardPackage.getUnpackDirectory());
    }

}
