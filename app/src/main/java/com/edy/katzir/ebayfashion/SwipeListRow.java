package com.edy.katzir.ebayfashion;

        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.os.AsyncTask;


        import java.io.*;
        import java.net.HttpURLConnection;
        import java.net.URL;

public class SwipeListRow {
    Context context;

    String name;
    String image;

    Bitmap imageBitmap = null;
    CustomList adapter;

    public SwipeListRow(Context context, CustomList adapter, String name,String image) {
        super();
        this.context = context;
        this.adapter = adapter;

        this.name = name;
        this.image = image;

        this.imageBitmap = null;
        this.loadImage();
    }

    /**
     * Name
     **/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void loadImage() {
        ImageLoadTask imageLoadTask = new ImageLoadTask();

        if (this.image != null && !this.image.equals("")) {
            imageLoadTask.execute(this.image);
        }
    }

    private class ImageLoadTask extends AsyncTask<String, String, Bitmap> {

        protected Bitmap doInBackground(String... param) {
            File cacheDir = context.getApplicationContext().getCacheDir();
            File file = new File(cacheDir, name);
            FileInputStream fis;

            Bitmap img = null;

            String urlStr = param[0];
            try {
                fis = new FileInputStream(file);
                img = BitmapFactory.decodeStream(fis);
            } catch (FileNotFoundException ex){
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    img = BitmapFactory.decodeStream(input);
                    try {
                        FileOutputStream out = new FileOutputStream(file);
                        img.compress(Bitmap.CompressFormat.JPEG, 80, out);
                        out.flush();
                        out.close();

                    }
                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                }

        }
            return img;
    }

        protected void onPostExecute(Bitmap ret) {
            if (ret != null) {
                imageBitmap = ret;
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }


}
