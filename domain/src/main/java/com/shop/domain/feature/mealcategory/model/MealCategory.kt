package com.shop.domain.feature.mealcategory.model

import android.os.Parcel
import android.os.Parcelable

data class MealCategory(
    val categoryId: String, val category: String, val categoryImage: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(categoryId)
        p0.writeString(category)
        p0.writeString(categoryImage)
    }

    companion object CREATOR : Parcelable.Creator<MealCategory> {
        override fun createFromParcel(parcel: Parcel): MealCategory {
            return MealCategory(parcel)
        }

        override fun newArray(size: Int): Array<MealCategory?> {
            return arrayOfNulls(size)
        }
    }
}