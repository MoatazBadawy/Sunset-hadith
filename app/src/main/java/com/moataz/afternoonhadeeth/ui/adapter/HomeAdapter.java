package com.moataz.afternoonhadeeth.ui.adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.moataz.afternoonhadeeth.R;
import com.moataz.afternoonhadeeth.data.model.home.DailyImage;
import com.moataz.afternoonhadeeth.data.model.home.DailyPost;
import com.moataz.afternoonhadeeth.data.model.home.FirstImage;
import com.moataz.afternoonhadeeth.data.model.home.HomeResponse;
import com.moataz.afternoonhadeeth.data.model.home.KanzHasanat;
import com.moataz.afternoonhadeeth.data.model.home.Live;
import com.moataz.afternoonhadeeth.data.model.home.SaheehBukhari;
import com.moataz.afternoonhadeeth.data.model.home.SaheehMuslim;
import com.moataz.afternoonhadeeth.data.model.home.TahzeebMuslim;
import com.moataz.afternoonhadeeth.databinding.ItemHomeDailyimageBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeDailypostBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeFirstimageBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeKanzhasanatBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeLiveBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeSaheehbukhariBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeSaheehmuslimBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeTahzeebmuslimBinding;
import com.moataz.afternoonhadeeth.ui.view.activity.DisplayImageActivity;
import com.moataz.afternoonhadeeth.ui.view.activity.YoutubePlayerActivity;
import com.moataz.afternoonhadeeth.utils.helper.TextAction;

import java.util.Objects;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private HomeResponse items = null;
    private static final TextAction textAction = new TextAction();

    @SuppressLint("NotifyDataSetChanged")
    public void setHomeList(HomeResponse items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == R.id.first_image) {
            return new FirstImageViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_firstimage,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.live_image) {
            return new LiveViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_live,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.daily_post) {
            return new DailyPostViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_dailypost,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.text_kanz_hasanat) {
            return new KanzHasanatViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_kanzhasanat,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.text_tahzeeb_muslim) {
            return new TahzeebMuslimViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_tahzeebmuslim,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.daily_image) {
            return new DailyImageViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_dailyimage,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.text_saheeh_bukhari) {
            return new SaheehBukhariViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_saheehbukhari,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.text_saheeh_muslim) {
            return new SaheehMuslimViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_saheehmuslim,
                            parent,
                            false
                    )
            );
        } else throw new IllegalArgumentException("unknown view type");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == R.id.first_image) {
            FirstImage firstImage = items.getFirstImage().get(position);
            ((FirstImageViewHolder) holder).itemHomeFirstimageBinding.setFirstImageModel(firstImage);

        } else if (getItemViewType(position) == R.id.live_image) {
            Live live = items.getLive().get(position - items.getFirstImage().size());
            ((LiveViewHolder) holder).itemHomeLiveBinding.setLiveModel(live);
            ((LiveViewHolder) holder).setOnClick(live);

        } else if (getItemViewType(position) == R.id.daily_post) {
            DailyPost dailyPost = items.getDailyPost().get(position - (items.getFirstImage().size() + items.getLive().size()));
            ((DailyPostViewHolder) holder).homeDailypostBinding.setDailyPostModel(dailyPost);
            ((DailyPostViewHolder) holder).setOnClick();

        } else if (getItemViewType(position) == R.id.text_kanz_hasanat) {
            KanzHasanat kanzHasanat = items.getKanzHasanat().get(position - (items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size()));
            ((KanzHasanatViewHolder) holder).itemHomeKanzhasanatBinding.setKanzHasanatModel(kanzHasanat);
            ((KanzHasanatViewHolder) holder).setOnClick(kanzHasanat);

        } else if (getItemViewType(position) == R.id.text_tahzeeb_muslim) {
            TahzeebMuslim tahzeebMuslim = items.getTahzeebMuslim().get(position - (items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size()));
            ((TahzeebMuslimViewHolder) holder).itemHomeTahzeebmuslimBinding.setTahzeebMuslimModel(tahzeebMuslim);
            ((TahzeebMuslimViewHolder) holder).setOnClick(tahzeebMuslim);


        } else if (getItemViewType(position) == R.id.daily_image) {
            DailyImage dailyImage = items.getDailyImage().get(position - (items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size()));
            ((DailyImageViewHolder) holder).itemHomeDailyimageBinding.setDailyImageModel(dailyImage);
            ((DailyImageViewHolder) holder).setOnClick(dailyImage);

        } else if (getItemViewType(position) == R.id.text_saheeh_bukhari) {
            SaheehBukhari saheehBukhari = items.getSaheehBukhari().get(position - (items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size()));
            ((SaheehBukhariViewHolder) holder).itemHomeSaheehbukhariBinding.setSaheehBukhariModel(saheehBukhari);
            ((SaheehBukhariViewHolder) holder).setOnClick(saheehBukhari);

        } else if (getItemViewType(position) == R.id.text_saheeh_muslim) {
            SaheehMuslim saheehMuslim = items.getSaheehMuslim().get(position - (items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size()));
            ((SaheehMuslimViewHolder) holder).itemHomeSaheehmuslimBinding.setSaheehMuslimModel(saheehMuslim);
            ((SaheehMuslimViewHolder) holder).setOnClick(saheehMuslim);

        }
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size() + items.getSaheehMuslim().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && position < items.getFirstImage().size()) {
            return R.id.first_image;

        } else if (position == items.getFirstImage().size() && position < items.getFirstImage().size()
                + items.getLive().size()) {
            return R.id.live_image;

        } else if (position == items.getFirstImage().size() + items.getLive().size() && position < items.getFirstImage().size()
                + items.getLive().size() + items.getDailyPost().size()) {
            return R.id.daily_post;

        } else if (position == items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() && position < items.getFirstImage().size()
                + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size()) {
            return R.id.text_kanz_hasanat;

        } else if (position == items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() & position < items.getFirstImage().size()
                + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size()) {
            return R.id.text_tahzeeb_muslim;

        } else if (position == items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() & position < items.getFirstImage().size()
                + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size()) {
            return R.id.daily_image;

        } else if (position == items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() & position < items.getFirstImage().size()
                + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size()) {
            return R.id.text_saheeh_bukhari;

        } else if (position == items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size() & position < items.getFirstImage().size()
                + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size() + items.getSaheehMuslim().size()) {
            return R.id.text_saheeh_muslim;

        }
        return R.id.first_image;
    }

    static class FirstImageViewHolder extends RecyclerView.ViewHolder {
        ItemHomeFirstimageBinding itemHomeFirstimageBinding;

        FirstImageViewHolder(@NonNull ItemHomeFirstimageBinding itemView) {
            super(itemView.getRoot());
            itemHomeFirstimageBinding = itemView;
        }
    }

    static class LiveViewHolder extends RecyclerView.ViewHolder {
        ItemHomeLiveBinding itemHomeLiveBinding;

        LiveViewHolder(@NonNull ItemHomeLiveBinding itemView) {
            super(itemView.getRoot());
            itemHomeLiveBinding = itemView;
        }

        void setOnClick(Live live) {
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), YoutubePlayerActivity.class);
                intent.putExtra("url", live.getUrl());
                itemView.getContext().startActivity(intent);
            });
        }
    }

    static class DailyPostViewHolder extends RecyclerView.ViewHolder {
        ItemHomeDailypostBinding homeDailypostBinding;

        DailyPostViewHolder(@NonNull ItemHomeDailypostBinding itemView) {
            super(itemView.getRoot());
            homeDailypostBinding = itemView;
        }

        void setOnClick() {
            homeDailypostBinding.buttonShareOtherImages.setOnClickListener(v -> {
                homeDailypostBinding.dailyPost.setDrawingCacheEnabled(true);
                homeDailypostBinding.dailyPost.buildDrawingCache();
                if (checkPermission()) {
                    Bitmap bitmap = homeDailypostBinding.dailyPost.getDrawingCache();
                    String bitmapPath = MediaStore.Images.Media.insertImage(itemView.getContext().getContentResolver(), bitmap, "حمل تطبيق حديث الغروب للمزيد من الأحاديث الشريفه", null);
                    Uri bitmapUri = Uri.parse(bitmapPath);
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("image/jpg/png");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "حمل تطبيق حديث الغروب للمزيد من الأحاديث الشريفه");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
                    itemView.getContext().startActivity(Intent.createChooser(shareIntent, ""));
                }
            });
        }

        boolean checkPermission() {
            int PERMISSION_WRITE = 0;
            Activity context = (Activity) itemView.getContext();
            if (ActivityCompat.checkSelfPermission(itemView.getContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(
                        context,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSION_WRITE
                );
                return false;
            }
        }
    }

    static class KanzHasanatViewHolder extends RecyclerView.ViewHolder {
        ItemHomeKanzhasanatBinding itemHomeKanzhasanatBinding;

        KanzHasanatViewHolder(@NonNull ItemHomeKanzhasanatBinding itemView) {
            super(itemView.getRoot());
            itemHomeKanzhasanatBinding = itemView;
        }

        void setOnClick(KanzHasanat kanzHasanat) {
            itemHomeKanzhasanatBinding.buttonShareOtherImages.setOnClickListener(v -> {
                textAction.copyText(Objects.requireNonNull(kanzHasanat.getText()), itemView.getContext());
                textAction.shareTextSnackbar(itemView.getRootView(), "تم نسخ الحديث", Objects.requireNonNull(kanzHasanat.getText()), itemView.getContext());
            });
        }
    }

    static class TahzeebMuslimViewHolder extends RecyclerView.ViewHolder {
        ItemHomeTahzeebmuslimBinding itemHomeTahzeebmuslimBinding;

        TahzeebMuslimViewHolder(@NonNull ItemHomeTahzeebmuslimBinding itemView) {
            super(itemView.getRoot());
            itemHomeTahzeebmuslimBinding = itemView;
        }

        void setOnClick(TahzeebMuslim tahzeebMuslim) {
            itemHomeTahzeebmuslimBinding.buttonShareOtherImages.setOnClickListener(v -> {
                textAction.copyText(Objects.requireNonNull(tahzeebMuslim.getText()), itemView.getContext());
                textAction.shareTextSnackbar(itemView.getRootView(), "تم نسخ الحديث", Objects.requireNonNull(tahzeebMuslim.getText()), itemView.getContext());
            });
        }
    }

    static class DailyImageViewHolder extends RecyclerView.ViewHolder {
        ItemHomeDailyimageBinding itemHomeDailyimageBinding;

        DailyImageViewHolder(@NonNull ItemHomeDailyimageBinding itemView) {
            super(itemView.getRoot());
            itemHomeDailyimageBinding = itemView;
        }

        void setOnClick(DailyImage images) {
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DisplayImageActivity.class);
                intent.putExtra("imageUrl", images.getImageUrl());
                itemView.getContext().startActivity(intent);
            });
        }
    }

    static class SaheehBukhariViewHolder extends RecyclerView.ViewHolder {
        ItemHomeSaheehbukhariBinding itemHomeSaheehbukhariBinding;

        SaheehBukhariViewHolder(@NonNull ItemHomeSaheehbukhariBinding itemView) {
            super(itemView.getRoot());
            itemHomeSaheehbukhariBinding = itemView;
        }

        void setOnClick(SaheehBukhari saheehBukhari) {
            itemHomeSaheehbukhariBinding.buttonShareOtherImages.setOnClickListener(v -> {
                textAction.copyText(Objects.requireNonNull(saheehBukhari.getText()), itemView.getContext());
                textAction.shareTextSnackbar(itemView.getRootView(), "تم نسخ الحديث", Objects.requireNonNull(saheehBukhari.getText()), itemView.getContext());
            });
        }
    }

    static class SaheehMuslimViewHolder extends RecyclerView.ViewHolder {
        ItemHomeSaheehmuslimBinding itemHomeSaheehmuslimBinding;

        SaheehMuslimViewHolder(@NonNull ItemHomeSaheehmuslimBinding itemView) {
            super(itemView.getRoot());
            itemHomeSaheehmuslimBinding = itemView;
        }

        void setOnClick(SaheehMuslim saheehMuslim) {
            itemHomeSaheehmuslimBinding.buttonShareOtherImages.setOnClickListener(v -> {
                textAction.copyText(Objects.requireNonNull(saheehMuslim.getText()), itemView.getContext());
                textAction.shareTextSnackbar(itemView.getRootView(), "تم نسخ الحديث", Objects.requireNonNull(saheehMuslim.getText()), itemView.getContext());
            });
        }
    }
}
