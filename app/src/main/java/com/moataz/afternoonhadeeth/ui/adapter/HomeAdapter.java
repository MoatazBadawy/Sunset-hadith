package com.moataz.afternoonhadeeth.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.moataz.afternoonhadeeth.R;
import com.moataz.afternoonhadeeth.data.model.DailyImage;
import com.moataz.afternoonhadeeth.data.model.DailyPost;
import com.moataz.afternoonhadeeth.data.model.FirstImage;
import com.moataz.afternoonhadeeth.data.model.HomeResponse;
import com.moataz.afternoonhadeeth.data.model.KanzHasanat;
import com.moataz.afternoonhadeeth.data.model.Live;
import com.moataz.afternoonhadeeth.data.model.SaheehBukhari;
import com.moataz.afternoonhadeeth.data.model.SaheehMuslim;
import com.moataz.afternoonhadeeth.data.model.TahzeebMuslim;
import com.moataz.afternoonhadeeth.databinding.ItemHomeDailyimageBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeDailypostBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeFirstimageBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeKanzhasanatBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeLiveBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeSaheehbukhariBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeSaheehmuslimBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeTahzeebmuslimBinding;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private HomeResponse items = null;

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

        } else if (getItemViewType(position) == R.id.text_kanz_hasanat) {
            KanzHasanat kanzHasanat = items.getKanzHasanat().get(position - (items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size()));
            ((KanzHasanatViewHolder) holder).itemHomeKanzhasanatBinding.setKanzHasanatModel(kanzHasanat);

        } else if (getItemViewType(position) == R.id.text_tahzeeb_muslim) {
            TahzeebMuslim tahzeebMuslim = items.getTahzeebMuslim().get(position - (items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size()));
            ((TahzeebMuslimViewHolder) holder).itemHomeTahzeebmuslimBinding.setTahzeebMuslimModel(tahzeebMuslim);

        } else if (getItemViewType(position) == R.id.daily_image) {
            DailyImage dailyImage = items.getDailyImage().get(position - (items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size()));
            ((DailyImageViewHolder) holder).itemHomeDailyimageBinding.setDailyImageModel(dailyImage);

        } else if (getItemViewType(position) == R.id.text_saheeh_bukhari) {
            SaheehBukhari saheehBukhari = items.getSaheehBukhari().get(position - (items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size()));
            ((SaheehBukhariViewHolder) holder).itemHomeSaheehbukhariBinding.setSaheehBukhariModel(saheehBukhari);

        } else if (getItemViewType(position) == R.id.text_saheeh_muslim) {
            SaheehMuslim saheehMuslim = items.getSaheehMuslim().get(position - (items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size()));
            ((SaheehMuslimViewHolder) holder).itemHomeSaheehmuslimBinding.setSaheehMuslimModel(saheehMuslim);

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
                // Add your code here to open Youtube activity .
                // NOTE: You should add youtube library into your project lib file.
                // check out youtube documentation for more info.
            });
        }
    }

    static class DailyPostViewHolder extends RecyclerView.ViewHolder {
        ItemHomeDailypostBinding homeDailypostBinding;

        DailyPostViewHolder(@NonNull ItemHomeDailypostBinding itemView) {
            super(itemView.getRoot());
            homeDailypostBinding = itemView;
        }
    }

    static class KanzHasanatViewHolder extends RecyclerView.ViewHolder {
        ItemHomeKanzhasanatBinding itemHomeKanzhasanatBinding;

        KanzHasanatViewHolder(@NonNull ItemHomeKanzhasanatBinding itemView) {
            super(itemView.getRoot());
            itemHomeKanzhasanatBinding = itemView;
        }
    }

    static class TahzeebMuslimViewHolder extends RecyclerView.ViewHolder {
        ItemHomeTahzeebmuslimBinding itemHomeTahzeebmuslimBinding;

        TahzeebMuslimViewHolder(@NonNull ItemHomeTahzeebmuslimBinding itemView) {
            super(itemView.getRoot());
            itemHomeTahzeebmuslimBinding = itemView;
        }
    }

    static class DailyImageViewHolder extends RecyclerView.ViewHolder {
        ItemHomeDailyimageBinding itemHomeDailyimageBinding;

        DailyImageViewHolder(@NonNull ItemHomeDailyimageBinding itemView) {
            super(itemView.getRoot());
            itemHomeDailyimageBinding = itemView;
        }
    }

    static class SaheehBukhariViewHolder extends RecyclerView.ViewHolder {
        ItemHomeSaheehbukhariBinding itemHomeSaheehbukhariBinding;

        SaheehBukhariViewHolder(@NonNull ItemHomeSaheehbukhariBinding itemView) {
            super(itemView.getRoot());
            itemHomeSaheehbukhariBinding = itemView;
        }
    }

    static class SaheehMuslimViewHolder extends RecyclerView.ViewHolder {
        ItemHomeSaheehmuslimBinding itemHomeSaheehmuslimBinding;

        SaheehMuslimViewHolder(@NonNull ItemHomeSaheehmuslimBinding itemView) {
            super(itemView.getRoot());
            itemHomeSaheehmuslimBinding = itemView;
        }
    }
}
