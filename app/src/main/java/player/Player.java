package player;


/**
 * Created by Tobias on 31.08.2016.
 */
public class Player{
    private String token;
    private String name;

    public Player(String name, String token){
        this.token = token;
        this.name = name;
    }

    public String getToken(){
        return token;
    }

    public String getName(){
        return name;
    }
}
