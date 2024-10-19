package Entities;

public class Utilities {
    public enum Genders{
        Male,
        Female,
    }
    public enum Status{
        Pending,
        Reviewing,
        Approved,
        OutForDelivery,
        Canceled,
        OnHold,
        Returned,
        Refunded,
    }
    public enum DeliveryStatus{
        Pending,
        onWay,
        Delivered,
    }
}
