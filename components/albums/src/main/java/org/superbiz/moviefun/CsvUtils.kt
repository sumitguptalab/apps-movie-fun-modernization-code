package org.superbiz.moviefun

import com.fasterxml.jackson.databind.ObjectReader

import java.io.IOException
import java.io.InputStream

object CsvUtils {

    @Throws(IOException::class)
    fun <T> readFromCsv(objectReader: ObjectReader, inputStream: InputStream): List<T> {
        val results = mutableListOf<T>()

       objectReader.readValues<T>(inputStream) to results

        return results
    }
}
