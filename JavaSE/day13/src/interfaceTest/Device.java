package interfaceTest;

public abstract class Device {
    private final int ID;
    private String name;

    private static int total=1;

    {this.ID=total++;
    }

    public abstract void startup();
    public abstract void shoutdown();

    @Override
    public String toString() {
        return "Device{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Device() {
    }

    public Device( String name) {
        this.name = name;
    }
}
