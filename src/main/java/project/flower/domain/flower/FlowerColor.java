package project.flower.domain.flower;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FlowerColor {
    RED("빨강"), ORANGE("주황"), YELLOW("노랑"), GREEN("초록"), BLUE("파랑")
    , PURPLE("보라"), PINK("분홍"), WHITE("흰색"), ETC("기타");

    private final String description;
}
