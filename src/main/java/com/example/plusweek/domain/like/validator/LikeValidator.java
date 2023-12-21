package com.example.plusweek.domain.like.validator;

import com.example.plusweek.domain.like.entity.Like;

public class LikeValidator {
    private static boolean checkIsNull(Like like) {
        return like == null;
    }
}
