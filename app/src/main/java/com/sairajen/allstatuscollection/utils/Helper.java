package com.sairajen.allstatuscollection.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * @author Gmonetix
 */

public class Helper {

    public static final String APP_LINK = "https://play.google.com/store/apps/details?id=com.sairajen.allstatuscollection";

    public static final String SHORT_APP_LINK = "https://goo.gl/qR6wWm";

    public static final String SHARE_SOURCE = "source : " + SHORT_APP_LINK;

    public static final String COOL_APP_LINK = "http://www.saihere.com/more-app.html";

    public static void watchYoutubeVideo(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        appIntent.putExtra("force_fullscreen",true);
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    public static void shareViaFacebook(Context context, String text) {
        try {
            Intent intent1 = new Intent();
            intent1.setPackage("com.facebook.katana");
            intent1.setAction("android.intent.action.SEND");
            intent1.setType("text/plain");
            intent1.putExtra("android.intent.extra.TEXT", text);
            context.startActivity(intent1);
        } catch (Exception e) {
            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + text;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
            context.startActivity(intent);
        }
    }

    public static void shareViaWhatsapp(Context context, String text) {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.setAction("android.intent.action.SEND");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, text);
        try {
            context.startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            ex.printStackTrace();
            Toast.makeText(context,"Whatsapp not installed on this device !", Toast.LENGTH_SHORT).show();
        }
    }

    public static void shareViaTwitter(Context context,String text) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setClassName("com.twitter.android", "com.twitter.android.composer.ComposerActivity");
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, text);
            context.startActivity(intent);
        }
        catch (Exception e){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/intent/tweet?text="+ text));
            context.startActivity(browserIntent);
            Toast.makeText(context,"Twitter not installed on this device !",Toast.LENGTH_LONG).show();
        }
    }

    public static void share(Context context,String text) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, text);
            context.startActivity(intent);
        }
        catch (Exception e){
            Toast.makeText(context,"No sharing app installed on this device !",Toast.LENGTH_LONG).show();
        }
    }

    public static void openLink(Context context,String link) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(link)));
        }
        catch (Exception e){
            Toast.makeText(context,"No browser installed on this device !",Toast.LENGTH_LONG).show();
        }
    }

    public static void copy(Context context,String text) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copy", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context,"copied !",Toast.LENGTH_SHORT).show();
    }

}
