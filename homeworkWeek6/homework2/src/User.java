// Muhammad Aqil bin Anuar, 24000198

public class User{
    private String name, id, password;
    private Accounts accounts;

    public User() {
        this.accounts = new Accounts();
    }

    public User(String name, String id, String password, Accounts accounts) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.accounts = accounts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword(){
        return password;
    }

    public Accounts getAccounts() {
        return accounts;
    }
}
