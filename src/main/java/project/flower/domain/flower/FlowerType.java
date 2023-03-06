package project.flower.domain.flower;

import lombok.Getter;

@Getter
public enum FlowerType {

    FLOWER_BOUQUET("FLOWER_BOUQUET"),
    FLOWER_SINGLE("FLOWER_SINGLE"),
    FLOWER_SELF_BOUQUET("FLOWER_SELF_BOUQUET");

    String type;

    FlowerType(String type) {
        this.type = type;
    }
}
