package pack2;

public class Room {
    int roomNumber;
    String type;
    double costPerDay;
    boolean isOccupied;

    public Room(int roomNumber, String type, double costPerDay) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.costPerDay = costPerDay;
        this.isOccupied = false;
    }
}