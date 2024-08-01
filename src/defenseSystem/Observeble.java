package defenseSystem;

interface Observeble {
    public void addComponent(Observer obj);
    public void sendMessage(String msg);
    public String[] getComponentNames();
    public void notifyAreaStatus(boolean areaState);
    public void sentPrivateMessage(String msg, String name);
    public void fuelInfoStatus(String name);
    public void oxygenInfoStatus(String name);
    public void energyInfoStatus(String name);
    public void damageInfoStatus(String name);
    public void deathNotice(String name);
}

