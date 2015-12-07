import controllers.unpacker.Unpacker;
import models.packaging.StandardPackage;
import org.json.JSONObject;

/**
 * Created by eunderhi on 25/11/15.
 */
public class BuildInstaller {

    public static void main(String args[]) {
        System.out.println("It's working");
        System.out.println(new JSONObject().toString());
        StandardPackage standardPackage = new StandardPackage("wildfly-10.0.0.CR4");
        standardPackage.setUnpackDirectory("/home/eunderhi/tmp/");
        standardPackage.addExclude("docs/contrib");
        Unpacker unpacker = new Unpacker();
        unpacker.setPackageToUnpack(standardPackage);
        unpacker.performAction();
    }
}
