package Dashboard.Components;

public enum RegionCoordinates {
    Nordjylland(335, 90),
    Midtjylland(215, 320),
    Syddanmark(190, 550),
    Sjælland(550, 530),
    Hovedstaden(605, 390);

    private int xCoordinate;
    private int yCoordinate;

    // Contains coordinates for where to place the different buttons on the map
    //
    RegionCoordinates(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }
}
