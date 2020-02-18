package com.app.basemodule.utils

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.os.Environment
import android.provider.ContactsContract
import com.app.basemodule.extensions.logd
import com.app.basemodule.utils.files.FileUtils
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

const val FOLDER_NAME = "kpithumbstmp"
fun getSavedImageAbsolutePath(contactId: Int, contentResolver: ContentResolver, fileName: String): String? {
    val uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId.toLong())
    val stream = ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri)
    return saveContactImage(stream, fileName)
}

// fun getSavedImageAbsolutePath(uri: Uri,  contentResolver: ContentResolver, fileName: String): String? {
//  val stream = ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri)
//  return saveContactImage(stream, fileName)
// }

fun saveContactImage(inputStream: InputStream, fileName: String): String? = try {
    val file = File(Environment.getExternalStorageDirectory().path, fileName)
    val output = FileOutputStream(file)
    try {
        try {
            val buffer = ByteArray(4 * 1024) // or other buffer size
            var read: Int = 0

//        while ((read = inputStream.read(buffer)) != -1) {
            while (read != -1) {
                read = inputStream.read(buffer)
                output.write(buffer, 0, read)
            }
            output.flush()
        } finally {
            output.close()
        }
    } catch (e: Exception) {
        e.printStackTrace() // handle exception, define IOException and others
    }
    val absolutePath = file.absolutePath
    logd(" Contact Image Path ===>" + absolutePath)
    absolutePath
} catch (e: Exception) {
    e.printStackTrace()
    null
}

fun clearTmpDir() {
    val storagePath = Environment.getExternalStorageDirectory().path
    val file = File(storagePath, FOLDER_NAME)
    if (file.exists() && file.isDirectory()) {
        FileUtils.deleteDir(file)
    }
}

fun openPhoto(contactId: Long, contentResolver: ContentResolver): InputStream? {
    val contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId)
    val photoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY)
    val cursor = contentResolver.query(photoUri,
            arrayOf<String>(ContactsContract.Contacts.Photo.PHOTO), null, null, null) ?: return null
    try {
        if (cursor.moveToFirst()) {
            val data = cursor.getBlob(0)
            if (data != null) {
                return ByteArrayInputStream(data)
            }
        }
    } finally {
        cursor.close()
    }
    return null
}
