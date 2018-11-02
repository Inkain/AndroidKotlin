package inkant1990.com.data.entity

import inkant1990.com.domain.entity.Student

fun StudentResponse.transformToDomain(): Student {

    return Student(id = id, name = name, age = age, image = image)
}

fun Student.transformToDomain(): Students {

    return Students(name = name, age = age, image = image)
}



