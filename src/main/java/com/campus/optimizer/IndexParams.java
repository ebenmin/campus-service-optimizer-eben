package com.campus.optimizer;

import java.util.Arrays;

/**
 * Team index-number-derived parameters (project brief Section 2.iii requires
 * at least three). Formulas are documented in docs/data_dictionary.md under
 * "Index-number-derived parameters" — keep both in sync. Values are computed
 * from the raw index numbers below rather than hardcoded, so a roster change
 * only means editing TEAM_INDEX_NUMBERS.
 */
public final class IndexParams {

    private IndexParams() {}

    // DCIT 204/308 Group 2 "Scythe" roster (15 members).
    private static final long[] TEAM_INDEX_NUMBERS = {
        22018250L, // Robert Owoo (leader)
        22128192L, // Chris Nana Opoku
        22108048L, // Blessing Owusu Frema
        22150775L, // Kwabena Yeboah
        22018785L, // Gabriel Ansah
        22332968L, // Felix Kwame Amuzu
        22262952L, // Ebenezer Acheampong Mintah
        22391866L, // Brian Aborampah-Akoto
        22413990L, // David Kwame Fiagbe
        22330946L, // Iman Iman Yelba Anafo
        22299343L, // Abdul-Hamid Fuseini
        22330254L, // Lincoln Chinecherem Ofose
        22386157L, // Edwin Kwesi Frimpong
        22369431L, // Marcel Yeboah
        22394237L, // Nana Yaw Adu-Gyamfi Kyeremah
    };

    // Confirmed team member index number, also used to seed
    // scripts/generate_requests_resources.py.
    public static final long INDEX_NUMBER = 22128192L; // Chris Nana Opoku

    // random_seed = INDEX_NUMBER mod 10000
    public static final int SEED = (int) (INDEX_NUMBER % 10000);

    // priority_weight = (sum of digits of INDEX_NUMBER) mod 5 + 1
    public static final int PRIORITY_WEIGHT = (digitSum(INDEX_NUMBER) % 5) + 1;

    // hash_table_size = nearest prime to the average team index number.
    // The raw average (~22.26 million) isn't a usable bucket-array size, so
    // it's scaled down (mod 1000) before finding the nearest prime — the
    // scaling is documented here and in the data dictionary so the formula
    // stays traceable back to the real index numbers.
    public static final long TEAM_INDEX_AVERAGE = average(TEAM_INDEX_NUMBERS);
    public static final int HASH_TABLE_SIZE = nearestPrime((int) (TEAM_INDEX_AVERAGE % 1000));

    private static int digitSum(long n) {
        int sum = 0;
        n = Math.abs(n);
        while (n > 0) {
            sum += (int) (n % 10);
            n /= 10;
        }
        return sum;
    }

    private static long average(long[] values) {
        long sum = Arrays.stream(values).sum();
        return Math.round((double) sum / values.length);
    }

    private static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; (long) i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private static int nearestPrime(int n) {
        if (n < 2) n = 2;
        int lower = n;
        int upper = n;
        while (true) {
            if (isPrime(lower)) return lower;
            if (isPrime(upper)) return upper;
            lower--;
            upper++;
        }
    }
}
