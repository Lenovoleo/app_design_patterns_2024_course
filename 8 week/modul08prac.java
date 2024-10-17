import java.util.Dictionary;
import java.util.List;

public class modul08prac {
    
}


interface IMediator{
    void SendMessage(String message, IUser sender);
    void AddUser(IUser user);
    void RemoveUser(IUser user);
}

interface IUser(){
    void SendMessage(String message);
    void RecieveMessage(String message, IUser sender);
    void RecieveSystemMessage(String message);
    String getName();
}

class ChatMediator implements IMediator{


    private List<IUser> users;

    void ChatMediator(){
        users = new List<Iuser>();
    }

    @Override
    public void AddUser(IUser user){
        users.add(user);
    }

    @Override
    public void RemoveUser(IUser user){
        users.remove(user);
    }

    @Override
    public void SendMessage(String message, IUser sender){
        for (var user : users) {
            if(user!=sender){
                user.RecieveMessage(message, sender);
            }
        }
    }
}

class User implements IUser{
    private String name;

    private IMediator mediator;

    public User(String name, IMediator mediator){
        this.name = name;
        this.mediator = mediator;

    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public void SendMessage(String message){
        System.out.println(message, name);
        mediator.SendMessage(message, this);
    }
    @Override
    public void RecieveMessage(String message, IUser sender){
        System.out.println(message, sender.getName(), name);

    }
    @Override
    public void RecieveSystemMessage(String message){
        System.out.println(name, message)
    }
    
}

class ChannelMediator implements IMediator{
    private String channelName;
    private Dictionary<String, List<IUser>> channels;

    void ChannelMediator(String channelName){
        channels.new Dictionary<String, List<IUser>>();
        this.channelName = channelName;
    }
    @Override
    public void AddUser(IUser user){

        if(!channels.get(channelName)){
            channels[channelName] = new List<IUser>();
        }
        channels[channelName].add(user);
    }

    @Override
    public void RemoveUser(IUser user){
        users.remove(user);
    }

    @Override
    public void SendMessage(String message, IUser sender){
        for (var user : users) {
            if(user!=sender){
                user.Reci
                
                
                
                eveMessage(message, sender);
            }
        }
    }
}