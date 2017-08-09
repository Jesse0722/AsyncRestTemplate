package jesse.AsynPackage;

/**
 * Created by jesse on 2017/8/9.
 */
public class UserRequest implements BaseRequest {
    private static final String REQ_URL = "http://www.126.com";
    @Override
    public String build() {
        return REQ_URL;
    }
}
