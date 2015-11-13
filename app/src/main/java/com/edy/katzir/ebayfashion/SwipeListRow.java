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
    String company;
    String cat;
    String image;

    Bitmap imageBitmap = null;
    CustomList adapter;

    public SwipeListRow(Context context, CustomList adapter, String name, String company, String cat, String image) {
        super();
        this.context = context;
        this.adapter = adapter;

        this.name = name;
        this.company = company;
        this.cat = cat;
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

    /**
     * Company
     **/
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Category
     **/
    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    /**
     * Image
     **/
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
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
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                img = BitmapFactory.decodeStream(input);
            } catch (IOException e) {
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
