import java.util.ArrayList;
import java.util.List;

public class modul3prac {
    
}

enum Role{
    ADMIN,
    USER
}

class User{
    protected String name;
    protected String email;
    protected Role role;

    User(String name, String email, Role role){
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getEmail(){
        return email;
    }
    public String getName(){
        return name;
    }
    public Role getRole(){
        return role;
    }
  
}

class UserManager{
    protected List<User> users;
    UserManager(){
        users = new ArrayList<>();
    }

    public void addUser(User user){
        users.add(user);
    }

    public void dropUser(String email){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getEmail().equals(email)){
                users.remove(i);
                return;
            }
        }
    }

    public void updateUser(String email, User updateUser){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getEmail().equals(email)){
                users.get(i).name = updateUser.getName();
                users.get(i).role = updateUser.getRole();
                return;
            }
        }
    }

    
}