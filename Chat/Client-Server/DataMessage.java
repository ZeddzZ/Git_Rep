import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class DataMessage implements JSONAware {
    static int currId = 1;
    private String username;
    private int id;
    private String text;
    private boolean isDeleted = false;
    private boolean isChanged = false;

    public DataMessage() {
        username = "1";
        text = "";
        id = currId++;
    }
    
    public DataMessage(String text,String username) {
        this.username = username;
        this.text = text;
        id = currId++;
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    public void setDelete(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    public void setChange(boolean isChanged) {
        this.isChanged = isChanged;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public String getUsername() {
        return username;
    }
    
    public int getID() {
        return id;
    }
    
    public String getText() {
        return text;
    }
    
    public boolean isDelete() {
        return isDeleted;
    }
    
    public boolean isChange() {
        return isChanged;
    }

    public void deleteMessage() {
        if (!isDeleted) {
            this.text = "message was deleted.";
            this.setDelete(true);
        }
    }

    public static DataMessage parseDataMessage(JSONObject obj){
        DataMessage info = new DataMessage();
        if ((String)obj.get("user") != null)
            info.username = (String)obj.get("user");
        info.text = (String)obj.get("message");
        info.id = Integer.parseInt(obj.get("id").toString());
        return info;
    }
    
    @Override
    public String toJSONString(){
        JSONObject obj = new JSONObject();
        obj.put("user", username);
        obj.put("message", text);
        obj.put("id", id);
        return obj.toString();
    }
    
    @Override
    public String toString() {
        return username + ": " + text;
    }
    
    @Override
    public boolean equals(Object obj) {
        return (((DataMessage)obj).getID() == id);
    }
}

/*
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class DataMessage implements JSONAware {
    static int currId = 1;
    private String username;
    private int id;
    private String text;
    private boolean deleted = false;
    private boolean changed = false;

    public Message() {
        username = "1";
        text = "";
        id = currId++;
    }

    public Message(String text, String username) {
        this.username = username;
        this.text = text;
        id = currId++;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void deleteMessage() {
        if (!deleted) {
            this.text = "message has isDeleted.";
            this.setDeleted(true);
        }
    }

    public static Message parseDataMessage(JSONObject obj){
        Message info = new Message();
        if((String)obj.get("user") != null) {
            info.username = (String)obj.get("user");
        }
        info.text = (String)obj.get("message");
        info.id = Integer.parseInt(obj.get("id").toString());
        return info;
    }
    
    @Override
    public String toJSONString(){
        JSONObject obj = new JSONObject();
        obj.put("user", username);
        obj.put("message", text);
        obj.put("id", id);
        return obj.toString();
    }
    
    @Override
    public String toString(){
        return username+" : "+text;
    }
    
    @Override
    public boolean equals(Object obj){
        return (((Message)obj).getID()==id);
    }
}
*/