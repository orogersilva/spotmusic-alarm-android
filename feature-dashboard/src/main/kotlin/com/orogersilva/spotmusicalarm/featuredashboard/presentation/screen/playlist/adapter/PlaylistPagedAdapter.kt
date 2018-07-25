package com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.adapter

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orogersilva.spotmusicalarm.dashboarddomain.enums.NetworkState
import com.orogersilva.spotmusicalarm.dashboarddomain.model.Playlist
import com.orogersilva.spotmusicalarm.featuredashboard.R
import com.orogersilva.spotmusicalarm.featuredashboard.databinding.ItemLoadingBinding
import com.orogersilva.spotmusicalarm.featuredashboard.databinding.ItemPlaylistBinding
import com.orogersilva.spotmusicalarm.featuredashboard.presentation.screen.playlist.PlaylistViewModel
import javax.inject.Inject

class PlaylistPagedAdapter @Inject constructor(private val playlistViewModel: PlaylistViewModel)
    : PagedListAdapter<Playlist, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    // region PROPERTIES

    private val ITEM_TYPE = 0
    private val LOADING_TYPE = 1

    private var networkState: NetworkState? = null

    // endregion

    // region PUBLIC METHODS

    fun setNetworkState(newNetworkState: NetworkState) {

        val previousState = this.networkState

        val previousExtraRowStatus = hasExtraRow()

        this.networkState = newNetworkState

        val newExtraRowStatus = hasExtraRow()

        if (previousExtraRowStatus != newExtraRowStatus) {

            if (previousExtraRowStatus) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }

        } else if (newExtraRowStatus && (previousState != newNetworkState)) {
            notifyItemChanged(itemCount - 1)
        }
    }

    // endregion

    // region OVERRIDED METHODS

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == ITEM_TYPE) {

            return PlaylistViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                    R.layout.item_playlist, parent, false))

        } else {

            return LoadingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                    R.layout.item_loading, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is PlaylistViewHolder) {

            holder.bind(getItem(position))

        } else if (holder is LoadingViewHolder) {

            holder.bind(networkState)
        }
    }

    override fun getItemViewType(position: Int): Int =
            if (hasExtraRow() && position == (itemCount - 1)) LOADING_TYPE else ITEM_TYPE

    // endregion

    // region UTILITY METHODS

    private fun hasExtraRow(): Boolean =
            networkState != null && networkState != NetworkState.SUCCESS

    // endregion

    // region VIEW HOLDERS

    inner class PlaylistViewHolder(private val binding: ItemPlaylistBinding) : RecyclerView.ViewHolder(binding.root) {

        // region PUBLIC METHODS

        fun bind(playlist: Playlist?) {

            playlist?.let { pl ->

                binding.playlist = pl
                binding.playlistConstraintLayout.setOnClickListener {
                    playlistViewModel.selectPlaylist(pl.id)
                }
            }
        }

        // endregion
    }

    inner class LoadingViewHolder(private val binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root) {

        // region PUBLIC METHODS

        fun bind(networkState: NetworkState?) {

            if (networkState != null && networkState == NetworkState.LOADING) {
                binding.loadPlaylistsProgressBar.visibility = View.VISIBLE
            } else {
                binding.loadPlaylistsProgressBar.visibility = View.GONE
            }

            if (networkState != null && networkState == NetworkState.FAILED) {

                // TODO: To implement.
            }
        }

        // endregion
    }

    /*public class NetworkStateItemViewHolder extends RecyclerView.ViewHolder {

        private NetworkItemBinding binding;
        public NetworkStateItemViewHolder(NetworkItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindView(NetworkState networkState) {
            if (networkState != null && networkState.getStatus() == NetworkState.Status.RUNNING) {
                binding.progressBar.setVisibility(View.VISIBLE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
            }

            if (networkState != null && networkState.getStatus() == NetworkState.Status.FAILED) {
                binding.errorMsg.setVisibility(View.VISIBLE);
                binding.errorMsg.setText(networkState.getMsg());
            } else {
                binding.errorMsg.setVisibility(View.GONE);
            }
        }
    }*/

    // endregion

    // region COMPANION OBJECTS

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Playlist>() {

            // region OVERRIDED METHODS

            override fun areItemsTheSame(oldItem: Playlist, newItem: Playlist): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Playlist, newItem: Playlist): Boolean =
                    oldItem == newItem

            // endregion
        }
    }

    // endregion
}

/*
public class FeedListAdapter extends PagedListAdapter<Article, RecyclerView.ViewHolder> {

    /*
     * There are two layout types we define
     * in this adapter:
     * 1. progrss view
     * 2. data view
     */
    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;

    private Context context;
    private NetworkState networkState;

    /*
     * The DiffUtil is defined in the constructor
     */
    public FeedListAdapter(Context context) {
        super(Article.DIFF_CALLBACK);
        this.context = context;
    }


    /*
     * Default method of RecyclerView.Adapter
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if(viewType == TYPE_PROGRESS) {
            NetworkItemBinding headerBinding = NetworkItemBinding.inflate(layoutInflater, parent, false);
            NetworkStateItemViewHolder viewHolder = new NetworkStateItemViewHolder(headerBinding);
            return viewHolder;

        } else {
            FeedItemBinding itemBinding = FeedItemBinding.inflate(layoutInflater, parent, false);
            ArticleItemViewHolder viewHolder = new ArticleItemViewHolder(itemBinding);
            return viewHolder;
        }
    }


    /*
     * Default method of RecyclerView.Adapter
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ArticleItemViewHolder) {
            ((ArticleItemViewHolder)holder).bindTo(getItem(position));
        } else {
            ((NetworkStateItemViewHolder) holder).bindView(networkState);
        }
    }


    /*
     * Default method of RecyclerView.Adapter
     */
    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_ITEM;
        }
    }


     private boolean hasExtraRow() {
        if (networkState != null && networkState != NetworkState.LOADED) {
            return true;
        } else {
            return false;
        }
    }

    public void setNetworkState(NetworkState newNetworkState) {
        NetworkState previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }


    /*
     * We define A custom ViewHolder for the list item
     */
    public class ArticleItemViewHolder extends RecyclerView.ViewHolder {

        private FeedItemBinding binding;
        public ArticleItemViewHolder(FeedItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindTo(Article article) {
            binding.itemImage.setVisibility(View.VISIBLE);
            binding.itemDesc.setVisibility(View.VISIBLE);

            String author = article.getAuthor() == null || article.getAuthor().isEmpty() ? context.getString(R.string.author_name) : article.getAuthor();
            String titleString = String.format(context.getString(R.string.item_title), author, article.getTitle());
            SpannableString spannableString = new SpannableString(titleString);
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context.getApplicationContext(), R.color.secondary_text)),
                        titleString.lastIndexOf(author) + author.length() + 1, titleString.lastIndexOf(article.getTitle()) - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            binding.itemTitle.setText(spannableString);
            binding.itemTime.setText(String.format(context.getString(R.string.item_date), AppUtils.getDate(article.getPublishedAt()), AppUtils.getTime(article.getPublishedAt())));             binding.itemDesc.setText(article.getDescription());
            Picasso.get().load(article.getUrlToImage()).resize(250, 200).into(binding.itemImage);
        }
    }


    /*
     * We define A custom ViewHolder for the progressView
     */
    public class NetworkStateItemViewHolder extends RecyclerView.ViewHolder {

        private NetworkItemBinding binding;
        public NetworkStateItemViewHolder(NetworkItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindView(NetworkState networkState) {
            if (networkState != null && networkState.getStatus() == NetworkState.Status.RUNNING) {
                binding.progressBar.setVisibility(View.VISIBLE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
            }

            if (networkState != null && networkState.getStatus() == NetworkState.Status.FAILED) {
                binding.errorMsg.setVisibility(View.VISIBLE);
                binding.errorMsg.setText(networkState.getMsg());
            } else {
                binding.errorMsg.setVisibility(View.GONE);
            }
        }
    }
}
view rawFeedListAdapter.java hosted with ❤ by GitHub*/