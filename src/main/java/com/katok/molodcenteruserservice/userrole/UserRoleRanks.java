package com.katok.molodcenteruserservice.userrole;

import lombok.Getter;

@Getter
public enum UserRoleRanks {
    HELPER((short) 1),
    EVENT_CREATOR((short) 5),
    OWNER((short) 10);

    private final short rank;

    UserRoleRanks(short rank) {
        this.rank = rank;
    }
}
