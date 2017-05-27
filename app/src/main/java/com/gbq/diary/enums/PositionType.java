package com.gbq.diary.enums;

/**
 * 类说明：列表Item
 * Author: Kuzan
 * Date: 2017/5/26 17:00.
 */
public enum PositionType {
    POSITION_0(0),
    POSITION_1(1),
    POSITION_2(2),
    POSITION_3(3),
    POSITION_4(4),
    POSITION_5(5),
    POSITION_6(6),
    POSITION_7(7),
    POSITION_8(8),
    POSITION_9(9);


    private int type;

    PositionType(int type) {
        this.type = type;
    }


    public static PositionType valueOf(int type) {
        switch (type) {
            case 0:
                return POSITION_0;
            case 1:
                return POSITION_1;
            case 2:
                return POSITION_2;
            case 3:
                return POSITION_3;
            case 4:
                return POSITION_4;
            case 5:
                return POSITION_5;
            case 6:
                return POSITION_6;
            case 7:
                return POSITION_7;
            case 8:
                return POSITION_8;
            case 9:
                return POSITION_9;
            default:
                return POSITION_0;
        }
    }

    public int value() {
        return this.type;
    }
}
