package Sci.khodier.andriod.cs426;

import android.graphics.Bitmap;

public class Notes {
  private  String title;
  private  String body;
  private Bitmap bitmap;
  private  String URL;
  private int id;
    public Notes (int id,String title , String body  ,String URL ,Bitmap bitmap) {
        this.title = title;
        this.body=body;
        this.URL=URL;
        this.bitmap=bitmap;
        System.out.println("tilte"+title);
        System.out.println("body"+body);
        System.out.println("URL"+URL);
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getURL() {
        return URL;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}