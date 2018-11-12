package inkant1990.com.data.entity

import inkant1990.com.data.db.entity.StudentDb
import inkant1990.com.domain.entity.Student


fun Student.transformToRequest(): Students {

    return Students(name = name, age = age, image = image)
}
fun StudentResponse.transformToDomain(): Student {

    return Student(id = id, name = name, age = age, image = image)
}

fun StudentDb.transformToDomain(): Student {

    return Student(id = id, name = name, age = age, image = image)
}

fun StudentResponse.transformToDb(): StudentDb {

    return StudentDb(name = name, age = age, image = image,id=id)
}
fun Student.transformToDb():StudentDb{
    return StudentDb(name = name, age = age, image = image,id = id)
}
