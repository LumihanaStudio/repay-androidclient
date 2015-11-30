package malang.moe.repay.data;

/**
 * Created by Junseok on 2015-11-30.
 */
public class User {
    public String id;
    public String password;
    public String name;
    public String apikey;
    public boolean isParent;
    public User(String id, String password, String name, String apikey, boolean isParent){
        this.id = id;
        this.password = password;
        this.name = name;
        this.apikey = apikey;
        this.isParent = isParent;
    }
}
