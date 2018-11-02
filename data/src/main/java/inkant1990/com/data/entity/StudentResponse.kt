package inkant1990.com.data.entity

import com.google.gson.annotations.SerializedName

data class StudentResponse(

    @SerializedName("objectId")
    val id: String,
    val name: String,
    val age: Int, val image: String
) : DataEntity