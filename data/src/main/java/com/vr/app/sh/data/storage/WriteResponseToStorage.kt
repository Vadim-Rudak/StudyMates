package com.vr.app.sh.data.storage

import com.vr.app.sh.domain.model.WriteToFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class WriteResponseToStorage {

    suspend fun write(body: ResponseBody?, pathFile: String): Flow<WriteToFile> = flow {
        val writeToFile = WriteToFile()
        try {
            val futureStudioIconFile = File(pathFile)
            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null

            try {
                val fileReader = ByteArray(4096)

                val fileSize = body?.contentLength()
                var fileSizeDownloaded: Long = 0

                inputStream = body?.byteStream()
                outputStream = FileOutputStream(futureStudioIconFile)

                while (true) {
                    val read = inputStream!!.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()
                    writeToFile.progress = fileSizeDownloaded*100/ fileSize!!
                    emit(writeToFile)
                }
                outputStream.flush()
                emit(WriteToFile(true))
            } catch (e: IOException) {
                emit(WriteToFile(false))
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: IOException) {
            emit(WriteToFile(false))
        }
    }
}