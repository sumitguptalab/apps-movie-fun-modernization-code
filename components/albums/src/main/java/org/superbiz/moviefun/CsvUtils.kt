package org.superbiz.moviefun

import com.fasterxml.jackson.databind.ObjectReader
import java.io.IOException
import java.io.InputStream

data class CsvUtils(private val objectReader: ObjectReader, private val inputStream: InputStream) {

    @Throws(IOException::class)
    fun <T> readFromCsv() : List<T>  = objectReader.readValues<T>(inputStream).readAll().toList()
}
