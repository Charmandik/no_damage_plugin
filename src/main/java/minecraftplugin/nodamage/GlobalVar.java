package minecraftplugin.nodamage;

public class GlobalVar {

    private GlobalVar() {
    }

    private long timeout = 600000;

    public static final GlobalVar INSTANCE = new GlobalVar();

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = Long.parseLong(timeout);
    }
}


