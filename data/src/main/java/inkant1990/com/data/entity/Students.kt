package inkant1990.com.data.entity

import com.google.gson.annotations.SerializedName

data class Students(
    @SerializedName("age")
    val age: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
) : DataEntity