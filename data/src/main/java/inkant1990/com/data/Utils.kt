package inkant1990.com.data

class Utils {
    companion object {
        fun transformToRequest(data: String?): String {
            return "name LIKE'${data}%'"
        }
    }
}
//where=name%20LIKE%20'v%25'