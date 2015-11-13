package com.edy.katzir.ebayfashion;

        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.os.AsyncTask;
        import android.util.Log;

        import org.apache.http.HttpEntity;
        import org.apache.http.HttpResponse;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.entity.BufferedHttpEntity;
        import org.apache.http.impl.client.DefaultHttpClient;

        import java.io.*;

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

            try {
                fis = new FileInputStream(file);
                img = BitmapFactory.decodeStream(fis);
            }
            catch (FileNotFoundException ex) {
                Log.v("SwipeListRow", "Image not found: " + ex);

                String urlStr = param[0];

                HttpClient client = new DefaultHttpClient();
                try {
                    HttpGet request = new HttpGet(urlStr);
                    HttpResponse response;
                    try {
                        response = client.execute(request);
                        HttpEntity entity = response.getEntity();
                        BufferedHttpEntity bufferedEntity = new BufferedHttpEntity(entity);
                        InputStream inputStream = bufferedEntity.getContent();
                        img = BitmapFactory.decodeStream(inputStream);

                        // Caching the Image
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
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                catch(Exception e) {
                    Log.v("SwipeListRow", "Image URL is not vaild: " + e);
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
