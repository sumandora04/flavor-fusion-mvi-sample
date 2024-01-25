package com.shop.presentation.view.mealcategory.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class PresentationMealCategory(
    val categoryId: String, val category: String, val categoryImage: String
) : Parcelable