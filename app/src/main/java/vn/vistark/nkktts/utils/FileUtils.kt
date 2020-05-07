package vn.vistark.nkktts.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.SystemClock
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream

class FileUtils {
    companion object {
        fun getCapturedImage(context: Context, selectedPhotoUri: Uri): Bitmap {
            val source =
                ImageDecoder.createSource(context.contentResolver, selectedPhotoUri)
            return ImageDecoder.decodeBitmap(source)
        }

        fun SaveImages(context: AppCompatActivity, imgName: String, bm: Bitmap): String {
            val root = context.externalCacheDir!!.path + "/spices_image"
            val fz = File(root)
            if (!fz.exists()) {
                fz.mkdirs()
            }
            val fname = "${imgName}_${System.currentTimeMillis()}.jpg"
            val f = File(root, fname)
            if (f.exists()) {
                f.delete()
            }
            try {
                val out = FileOutputStream(f)
                bm.compress(Bitmap.CompressFormat.JPEG, 100, out)
                out.flush()
                out.close()
                return f.path
            } catch (e: Exception) {
                e.printStackTrace()
                return ""
            }
        }

        fun getBitmapFile(filePath: String): Bitmap? {
            val f = File(filePath)
            if (f.exists()) {
                return BitmapFactory.decodeFile(f.absolutePath)
            }
            return null
        }
    }
}