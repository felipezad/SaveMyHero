package com.exercise.savemyhero.ui.core


enum class ThumbnailSize(val path: String) {
    SMALL("small."),
    MEDIUM("medium."),
    LARGE("large.");
}

enum class ThumbnailOrientation(val path: String) {
    PORTRAIT("/portrait_"),
    LANDSCAPE("/landscape_");
}
