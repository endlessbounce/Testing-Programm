package Interfaces;

public interface UserDataDao {
    public String login(String login, String pswd);
    public boolean registration(String name, String login, String pswd);
    public String getStatistics(String user);
}
