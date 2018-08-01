package com.epam.movierating.util;

public class RatingCalculator {
    private static final int POINT_WHEN_ADDING_REVIEW = 3;
    private static int POINT_WHEN_NO_DIFFERENCE = 3;
    private static int POINT_WHEN_SMALL_DIFFERENCE = 2;
    private static int POINT_WHEN_AVERAGE_DIFFERENCE = 1;
    private static int POINT_WHEN_BIG_DIFFERENCE = 0;

    public static double culcUserRatingByAddingReview(double userRating, double filmRating, int reviewRating) {
        double newUserRating = userRating + POINT_WHEN_ADDING_REVIEW;
        double difference = Math.abs(filmRating - reviewRating);
        int roundDifference = (int) Math.round(difference);

        switch (roundDifference) {
            case 0:
                newUserRating += POINT_WHEN_NO_DIFFERENCE;
                break;
            case 1:
                newUserRating += POINT_WHEN_SMALL_DIFFERENCE;
                break;
            case 2:
                newUserRating += POINT_WHEN_AVERAGE_DIFFERENCE;
                break;
            default:
                newUserRating += POINT_WHEN_BIG_DIFFERENCE;
                break;
        }
        return newUserRating;
    }
}
