package defenseSystem;

interface Observer {
    public void receiveMessage(String msg, boolean isPrivate);
    public String getComponentName();
    public void notifyAreaStatus(boolean obj);
    public void setPosition(int value);
    public String[] getInfo();
}
