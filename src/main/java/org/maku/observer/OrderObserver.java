package org.maku.observer;

public interface OrderObserver {
    public boolean sendMail();
    public boolean equals(int orderId);
    public int getOrderId();
}
