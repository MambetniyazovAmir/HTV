package com.app.basemodule.utils.files;


import android.content.*;
import android.database.*;
import android.net.*;
import android.os.*;
import android.provider.*;
import android.util.*;

import androidx.annotation.*;

import java.io.*;

import static android.provider.DocumentsContract.*;


public class FileUtils {
    private static final String OTHER_STORAGE_TEMPLATE = "/storage/%1$s/%2$s";
/*
     public static String getPathCompressed(final Context context, final Uri uri) {
        try {

            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;

            InputStream input = context.getContentResolver().openInputStream(uri);

            Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);

            String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
            File myDir = new File(root + "/rooky");
            myDir.mkdirs();
            Random generator = new Random();
            int n = 10000;
            n = generator.nextInt(n);
            String fname = "Image-" + n + ".jpg";
            File file = new File(myDir, fname);
            if (file.exists())
                file.delete();

            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 10, out);
            out.flush();
            out.close();
            if (file.canRead()) {
                return file.getAbsolutePath();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }*/

    public static void clearLocalStorageFolder(Context context) {
        String storagePath = context.getExternalFilesDir(null).toString();
        File file = new File(storagePath);
        if (file.exists() && file.isDirectory()) {
            deleteDir(file);
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir != null && dir.delete();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static File getLocalCopyOfFile(Uri uri, Context context) throws IOException {
        String sourcePath = context.getExternalFilesDir(null).toString();
        String pathName = sourcePath + "/" + getFileName(uri, context);
        File file = new File(pathName);
        copyFileStream(file, uri, context);
        return file;
    }

    public static void copyFileStream(File dest, Uri uri, Context context) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = context.getContentResolver().openInputStream(uri);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            is.close();
            os.close();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getFileName(Uri uri, Context context) {
        String fileName;
        try (Cursor cursor = context.getContentResolver().query(uri, null, null, null, null, null)) {
            if (cursor != null && !cursor.isAfterLast()) {
                cursor.moveToFirst();
                fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
            } else {
                fileName = String.valueOf(System.currentTimeMillis());
            }
        } catch (NullPointerException | CursorIndexOutOfBoundsException e) {
            e.printStackTrace();
            fileName = String.valueOf(System.currentTimeMillis());
        }
        return fileName;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static Pair<String, Integer> getFileNameAndSizePair(Uri uri, Context context) {
        String fileName = "";
        int fileSize = 0;
        try (Cursor cursor = context.getContentResolver().query(uri, null, null, null, null, null)) {
            cursor.moveToFirst();
            fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
            int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
            if (!cursor.isNull(sizeIndex)) {
                fileSize = cursor.getInt(sizeIndex);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return new Pair<>(fileName, fileSize);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, @NonNull final Uri uri) {
        boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                } else {
                    return String.format(OTHER_STORAGE_TEMPLATE, split[0], split[1]);//"/storage/" + split[0] + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long
                        .valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = "_data";
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } catch (IllegalArgumentException iae) {
            return null;
        } finally {
            if (cursor != null) cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}