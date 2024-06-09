package itacademy.misbackend.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum PassportSeries {
    AN("AN"), ID("ID"), AC("ID"), AD("AD"), AS("AS"), PE("PE"), PD("PD"), PS("PS");

    String series;
}
