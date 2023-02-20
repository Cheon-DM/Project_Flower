package project.flower.domain.order;

import lombok.Getter;

@Getter
public enum OrderStatus {

    PROCESSING("PROCESSING"),
    IN_DELIVERY("IN_DELIVERY"),
    DELIVERY_COMPLETE("DELIVERY_COMPLETE");

    String status;

    OrderStatus(String status){
        this.status = status;
    }
}
