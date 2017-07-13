package com.sairajen.allstatuscollection.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sairajen.allstatuscollection.R;
import com.sairajen.allstatuscollection.utils.Helper;

/**
 * @author Gmonetix
 */

public class ScreenSlidePageFragment extends Fragment implements View.OnClickListener {

    private View rootView;
    private TextView tvStatus, tvTotalCount;
    private ImageView shareFB, shareWhatsapp, shareTwitter, copy, share;

    private String text = "", total = "0", current = "0";

    public ScreenSlidePageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.status_screen_slide_layout, container, false);

        init();

        tvStatus.setText(text);
        tvTotalCount.setText("Total Status : "+current+"/"+total);

        shareFB.setOnClickListener(this);
        shareWhatsapp.setOnClickListener(this);
        shareTwitter.setOnClickListener(this);
        copy.setOnClickListener(this);
        share.setOnClickListener(this);

        return rootView;
    }

    private void init() {
        tvStatus = (TextView) rootView.findViewById(R.id.statusText);
        tvTotalCount = (TextView) rootView.findViewById(R.id.page_slide_text_total_count);
        shareFB = (ImageView) rootView.findViewById(R.id.page_slide_share_email);
        shareWhatsapp = (ImageView) rootView.findViewById(R.id.page_slide_share_whatsapp);
        shareTwitter = (ImageView) rootView.findViewById(R.id.page_slide_share_twitter);
        copy = (ImageView) rootView.findViewById(R.id.page_slide_copy);
        share = (ImageView) rootView.findViewById(R.id.page_slide_share);
    }

    public void data(String text, String total, String current) {
        this.text = text;
        this.current = current;
        this.total = total;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.page_slide_share_email:
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {""});
                intent.putExtra(Intent.EXTRA_SUBJECT, "All Status Collection");
                intent.putExtra(Intent.EXTRA_TEXT,text);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;
            case R.id.page_slide_share_whatsapp:
                Helper.shareViaWhatsapp(getActivity(),text + "\n\n" + Helper.SHARE_SOURCE);
                break;
            case R.id.page_slide_share_twitter:
                Helper.shareViaTwitter(getActivity(),text + "\n\n" + Helper.SHARE_SOURCE);
                break;
            case R.id.page_slide_copy:
                Helper.copy(getActivity(),text + "\n\n" + Helper.SHARE_SOURCE);
                break;
            case R.id.page_slide_share:
                Helper.share(getActivity(),text + "\n\n" + Helper.SHARE_SOURCE);
                break;
        }
    }
}