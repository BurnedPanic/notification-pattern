package com.burnedpanic.notificationpattern.domain.enumerate;

import java.util.Arrays;

public enum Category {

    ELITE(20, 35),
    JUNIOR(15, 20),
    MASTER(35, 100);

    Category(int minAge, int maxAge) {
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    private final int minAge;
    private final int maxAge;

    public int minAge() {
        return minAge;
    }

    public int maxAge() {
        return maxAge;
    }

    public static Category findCategoryFromAge(int age) {
        return Arrays.stream(Category.values())
                .filter(c -> age >= c.minAge() && age <= c.maxAge())
                .findFirst()
                .orElse(null);
    }

}
