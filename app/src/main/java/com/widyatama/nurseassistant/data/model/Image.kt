package com.widyatama.nurseassistant.data.model


/**
 * Created by iman on 15/05/2019.
 */

class Image {
    private var imagePath: Int = 0
    fun getImagePath(): Int {
        return imagePath
    }

    fun setImagePath(image_drawable: Int) {
        this.imagePath = image_drawable
    }
}