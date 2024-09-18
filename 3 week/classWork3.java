public static void main(String[] args) {
    
}

class User{
    protected String name;
    protected String email;
    protected String role;

    User(String name, String email, String role){
        this.name = name;
        this.email = email;
        this.role = role;
    }

}

class UserManager{
    protected User list_users[];
    protected int size_list_users = 0;


    public void addUser(User u){
        if(size_list_users < list_users.length){
            list_users[size_list_users] = u;
            size_list_users++;

        }else{
            System.out.println("List is full");
        }
    }

    public void dropUser(int index_user){
        if(index_user < 0 || index_user >= size_list_users){
            System.out.println("Error index");
            return;
        }
        User arr[] = new User[list_users.length - 1];
        int index = 0;
        for(int i = 0; i < size_list_users; i++){
            if(i != index_user){
                arr[index++] = list_users[i];
            }
        }
        list_users = arr;
        size_list_users--;
    }

    public void updateUser(int index){
        
    }
}

