// Syahir Amri bin Mohd Azha, 22007728

import java.util.ArrayList;

public class Users {
    private ArrayList<User> users;

    public Users() {
        this.users = new ArrayList<>();
    }

    public Users(ArrayList<User> users) {
        this.users = users;
    }

    public void setUsers(ArrayList<User> users){
        this.users = users;
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void printUser() {
        for (int i = 0; i < users.size(); i++){
            User user = users.get(i);
            System.out.println(user.getName());
        }
    }

    public User findUser(String id) {
        for (int i = 0; i < users.size(); i++){
            User user = users.get(i);
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
