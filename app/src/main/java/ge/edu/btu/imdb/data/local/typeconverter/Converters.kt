package ge.edu.btu.imdb.data.local.typeconverter

import androidx.room.TypeConverter

/**
 * A class containing TypeConverter methods for converting between List<String> and String.
 */
class Converters {
    /**
     * Converts a List of Strings to a single comma-separated String.
     *
     * @param list The List of Strings to be converted.
     * @return A comma-separated String representation of the input List.
     */
    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return list.joinToString(",")
    }

    /**
     * Converts a comma-separated String to a List of Strings.
     *
     * @param data The comma-separated String to be converted.
     * @return A List of Strings extracted from the input comma-separated String.
     */
    @TypeConverter
    fun toStringList(data: String): List<String> {
        return data.split(",")
    }
}