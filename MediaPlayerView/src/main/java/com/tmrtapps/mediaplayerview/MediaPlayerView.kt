package com.tmrtapps.mediaplayerview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.*
import com.bumptech.glide.Glide
import com.tmrtapps.mediaplayerview.databinding.MediaPlayerBinding

class MediaPlayerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    @ColorInt
    var boxColor = ContextCompat.getColor(context, R.color.boxColor)
        set(value) {
            field = value
            handleBackground()
        }

    @Dimension
    var boxCornerRadius = context.resources.getDimensionPixelSize(R.dimen.boxCornerRadius)
        set(value) {
            field = value
            handleBackground()
        }

    @Dimension
    var boxElevation = context.resources.getDimensionPixelSize(R.dimen.boxElevation)
        set(value) {
            field = value
            handleBackground()
        }

    @Dimension
    var boxMargin = context.resources.getDimensionPixelSize(R.dimen.boxMargin)
        set(value) {
            field = value
            handleBackground()
        }

    @ColorInt
    var errorColorPrimary = ContextCompat.getColor(context, R.color.errorColorPrimary)

    @ColorInt
    var errorColorSecondary = ContextCompat.getColor(context, R.color.errorColorSecondary)


    var albumArtIsVisible = context.resources.getBoolean(R.bool.albumArtIsVisible)
        set(value) {
            field = value
            handleAlbumArtImageView()
        }

    @Dimension
    var albumArtWidth = context.resources.getDimensionPixelSize(R.dimen.albumArtWidth)
        set(value) {
            field = value
            handleAlbumArtImageView()
        }

    @Dimension
    var albumArtHeight = context.resources.getDimensionPixelSize(R.dimen.albumArtHeight)
        set(value) {
            field = value
            handleAlbumArtImageView()
        }

    @Dimension
    var albumArtMarginEnd = context.resources.getDimensionPixelSize(R.dimen.albumArtMarginEnd)
        set(value) {
            field = value
            handleAlbumArtImageView()
        }

    @Dimension
    var albumArtMarginStart = context.resources.getDimensionPixelSize(R.dimen.albumArtMarginStart)
        set(value) {
            field = value
            handleAlbumArtImageView()
        }

    @Dimension
    var albumArtMarginTop = context.resources.getDimensionPixelSize(R.dimen.albumArtMarginTop)
        set(value) {
            field = value
            handleAlbumArtImageView()
        }

    @Dimension
    var albumArtBoxCornerRadius = context.resources.getDimensionPixelSize(R.dimen.albumArtBoxCornerRadius)
        set(value) {
            field = value
            handleAlbumArtImageView()
        }

    @Dimension
    var albumArtBoxElevation = context.resources.getDimensionPixelSize(R.dimen.albumArtBoxElevation)
        set(value) {
            field = value
            handleAlbumArtImageView()
        }


    var albumArtPlaceholder: Drawable? = null


    var artistIsVisible = context.resources.getBoolean(R.bool.artistIsVisible)
        set(value) {
            field = value
            handleArtistTextView()
        }

    @Dimension
    var artistTextSize = context.resources.getDimensionPixelSize(R.dimen.artistTextSize)
        set(value) {
            field = value
            handleArtistTextView()
        }

    @ColorInt
    var artistTextColor = ContextCompat.getColor(context, R.color.artistTextColor)
        set(value) {
            field = value
            handleArtistTextView()
        }


    var titleIsVisible = context.resources.getBoolean(R.bool.titleIsVisible)
        set(value) {
            field = value
            handleTitleTextView()
        }

    @Dimension
    var titleTextSize = context.resources.getDimensionPixelSize(R.dimen.titleTextSize)
        set(value) {
            field = value
            handleTitleTextView()
        }

    @ColorInt
    var titleTextColor = ContextCompat.getColor(context, R.color.titleTextColor)
        set(value) {
            field = value
            handleTitleTextView()
        }

    var textSizeUnit = TypedValue.COMPLEX_UNIT_PX
        set(value) {
            field = value
            handleTitleTextView()
            handleArtistTextView()
        }


    @FontRes
    var fontFamilyResId = -1
        set(value) {
            field = value
            handleTitleTextView()
            handleArtistTextView()
        }


    var seekBarIsVisible = context.resources.getBoolean(R.bool.seekBarIsVisible)
        set(value) {
            field = value
            handleSeekBar()
        }

    @Dimension
    var seekBarMarginTop = context.resources.getDimensionPixelSize(R.dimen.seekBarMarginTop)
        set(value) {
            field = value
            handleSeekBar()
        }

    @ColorInt
    var seekBarThumbTint = ContextCompat.getColor(context, R.color.seekBarThumbTint)
        set(value) {
            field = value
            handleSeekBar()
        }

    @ColorInt
    var seekBarProgressTint = ContextCompat.getColor(context, R.color.seekBarProgressTint)
        set(value) {
            field = value
            handleSeekBar()
        }

    @ColorInt
    var seekBarProgressBackgroundTint = ContextCompat.getColor(context, R.color.seekBarProgressBackgroundTint)
        set(value) {
            field = value
            handleSeekBar()
        }


    @Dimension
    var progressTextSize = context.resources.getDimensionPixelSize(R.dimen.progressTextSize)
        set(value) {
            field = value
            handleProgressTextView()
        }

    @ColorInt
    var progressTextColor = ContextCompat.getColor(context, R.color.progressTextColor)
        set(value) {
            field = value
            handleProgressTextView()
        }


    var playButtonIsVisible = context.resources.getBoolean(R.bool.playButtonIsVisible)
        set(value) {
            field = value
            handlePlayButton()
        }

    @Dimension
    var playButtonSize = context.resources.getDimensionPixelSize(R.dimen.playButtonSize)
        set(value) {
            field = value
            handlePlayButton()
        }

    @Dimension
    var playButtonMarginTop = context.resources.getDimensionPixelSize(R.dimen.playButtonMarginTop)
        set(value) {
            field = value
            handlePlayButton()
        }

    @Dimension
    var playButtonMarginBottom = context.resources.getDimensionPixelSize(R.dimen.playButtonMarginBottom)
        set(value) {
            field = value
            handlePlayButton()
        }

    @ColorInt
    var playButtonColor = ContextCompat.getColor(context, R.color.buttonBackgroundColor)
        set(value) {
            field = value
            handlePlayButton()
        }

    @ColorInt
    var playButtonStrokeColor = ContextCompat.getColor(context, R.color.buttonStrokeColor)
        set(value) {
            field = value
            handlePlayButton()
        }

    @Dimension
    var playButtonStrokeWidth = context.resources.getDimensionPixelSize(R.dimen.buttonStrokeWidth)
        set(value) {
            field = value
            handlePlayButton()
        }

    @ColorInt
    var playButtonRippleColor = ContextCompat.getColor(context, R.color.buttonRippleColor)
        set(value) {
            field = value
            handlePlayButton()
        }

    @Dimension
    var playButtonCornerRadius = context.resources.getDimensionPixelSize(R.dimen.buttonCornerRadius)
        set(value) {
            field = value
            handlePlayButton()
        }

    @ColorInt
    var playButtonIconTint = ContextCompat.getColor(context, R.color.buttonIconTint)
        set(value) {
            field = value
            handlePlayButton()
        }


    var nextButtonIsVisible = context.resources.getBoolean(R.bool.nextButtonIsVisible)
        set(value) {
            field = value
            handleNextButton()
        }

    @Dimension
    var nextButtonSize = context.resources.getDimensionPixelSize(R.dimen.nextButtonSize)
        set(value) {
            field = value
            handleNextButton()
        }

    @Dimension
    var nextButtonMarginStart = context.resources.getDimensionPixelSize(R.dimen.nextButtonMarginStart)
        set(value) {
            field = value
            handleNextButton()
        }

    @ColorInt
    var nextButtonColor = ContextCompat.getColor(context, R.color.buttonBackgroundColor)
        set(value) {
            field = value
            handleNextButton()
        }

    @ColorInt
    var nextButtonStrokeColor = ContextCompat.getColor(context, R.color.buttonStrokeColor)
        set(value) {
            field = value
            handleNextButton()
        }

    @Dimension
    var nextButtonStrokeWidth = context.resources.getDimensionPixelSize(R.dimen.buttonStrokeWidth)
        set(value) {
            field = value
            handleNextButton()
        }

    @ColorInt
    var nextButtonRippleColor = ContextCompat.getColor(context, R.color.buttonRippleColor)
        set(value) {
            field = value
            handleNextButton()
        }

    @Dimension
    var nextButtonCornerRadius = context.resources.getDimensionPixelSize(R.dimen.buttonCornerRadius)
        set(value) {
            field = value
            handleNextButton()
        }

    @ColorInt
    var nextButtonIconTint = ContextCompat.getColor(context, R.color.buttonIconTint)
        set(value) {
            field = value
            handleNextButton()
        }


    var prevButtonIsVisible = context.resources.getBoolean(R.bool.prevButtonIsVisible)
        set(value) {
            field = value
            handlePrevButton()
        }

    @Dimension
    var prevButtonSize = context.resources.getDimensionPixelSize(R.dimen.prevButtonSize)
        set(value) {
            field = value
            handlePrevButton()
        }

    @Dimension
    var prevButtonMarginEnd = context.resources.getDimensionPixelSize(R.dimen.prevButtonMarginEnd)
        set(value) {
            field = value
            handlePrevButton()
        }

    @ColorInt
    var prevButtonColor = ContextCompat.getColor(context, R.color.buttonBackgroundColor)
        set(value) {
            field = value
            handlePrevButton()
        }

    @ColorInt
    var prevButtonStrokeColor = ContextCompat.getColor(context, R.color.buttonStrokeColor)
        set(value) {
            field = value
            handlePrevButton()
        }

    @Dimension
    var prevButtonStrokeWidth = context.resources.getDimensionPixelSize(R.dimen.buttonStrokeWidth)
        set(value) {
            field = value
            handlePrevButton()
        }

    @ColorInt
    var prevButtonRippleColor = ContextCompat.getColor(context, R.color.buttonRippleColor)
        set(value) {
            field = value
            handlePrevButton()
        }

    @Dimension
    var prevButtonCornerRadius = context.resources.getDimensionPixelSize(R.dimen.buttonCornerRadius)
        set(value) {
            field = value
            handlePrevButton()
        }

    @ColorInt
    var prevButtonIconTint = ContextCompat.getColor(context, R.color.buttonIconTint)
        set(value) {
            field = value
            handlePrevButton()
        }

    private lateinit var mediaPlayer: MediaPlayer
    private var mediaPlayerPrepared = false
    private var currentPosition = 0

    private var sourceList = mutableListOf<Audio>()
    private var index = 0

    private var data = ""
    private var albumArt: Any? = null
    private var title = ""
    private var artist = ""

    private lateinit var myHandler: Handler
    private lateinit var myRunnable: Runnable

    private val binding = MediaPlayerBinding.inflate(LayoutInflater.from(context), this, true)

    init {

        attrs?.let {

            val attributes = context.obtainStyledAttributes(it, R.styleable.MediaPlayerView)

            boxColor = attributes.getColor(R.styleable.MediaPlayerView_boxColor, boxColor)
            boxCornerRadius = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_boxCornerRadius, boxCornerRadius)
            boxElevation = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_boxElevation, boxElevation)
            boxMargin = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_boxMargin, boxMargin)
            errorColorPrimary = attributes.getColor(R.styleable.MediaPlayerView_errorColorPrimary, errorColorPrimary)
            errorColorSecondary = attributes.getColor(R.styleable.MediaPlayerView_errorColorSecondary, errorColorSecondary)

            albumArtIsVisible = attributes.getBoolean(R.styleable.MediaPlayerView_albumArtIsVisible, albumArtIsVisible)
            albumArtWidth = attributes.getLayoutDimension(R.styleable.MediaPlayerView_albumArtWidth, albumArtWidth)
            albumArtHeight = attributes.getLayoutDimension(R.styleable.MediaPlayerView_albumArtHeight, albumArtHeight)
            albumArtMarginTop = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_albumArtMarginTop, albumArtMarginTop)
            albumArtMarginEnd = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_albumArtMarginEnd, albumArtMarginEnd)
            albumArtMarginStart = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_albumArtMarginStart, albumArtMarginStart)
            albumArtBoxCornerRadius = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_albumArtBoxCornerRadius, albumArtBoxCornerRadius)
            albumArtBoxElevation = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_albumArtBoxElevation, albumArtBoxElevation)
            albumArtPlaceholder = attributes.getDrawable(R.styleable.MediaPlayerView_albumArtPlaceholder)

            titleIsVisible = attributes.getBoolean(R.styleable.MediaPlayerView_titleIsVisible, titleIsVisible)
            titleTextSize = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_titleTextSize, titleTextSize)
            titleTextColor = attributes.getColor(R.styleable.MediaPlayerView_titleTextColor, titleTextColor)

            artistIsVisible = attributes.getBoolean(R.styleable.MediaPlayerView_artistIsVisible, artistIsVisible)
            artistTextSize = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_artistTextSize, artistTextSize)
            artistTextColor = attributes.getColor(R.styleable.MediaPlayerView_artistTextColor, artistTextColor)

            fontFamilyResId = attributes.getResourceId(R.styleable.MediaPlayerView_textFontFamily, -1)

            seekBarIsVisible = attributes.getBoolean(R.styleable.MediaPlayerView_seekBarIsVisible, seekBarIsVisible)
            seekBarMarginTop = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_seekBarMarginTop, seekBarMarginTop)
            seekBarThumbTint = attributes.getColor(R.styleable.MediaPlayerView_seekBarThumbTint, seekBarThumbTint)
            seekBarProgressTint = attributes.getColor(R.styleable.MediaPlayerView_seekBarProgressTint, seekBarProgressTint)
            seekBarProgressBackgroundTint = attributes.getColor(R.styleable.MediaPlayerView_seekBarProgressBackgroundTint, seekBarProgressBackgroundTint)

            progressTextSize = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_progressTextSize, progressTextSize)
            progressTextColor = attributes.getColor(R.styleable.MediaPlayerView_progressTextColor, progressTextColor)

            playButtonIsVisible = attributes.getBoolean(R.styleable.MediaPlayerView_playButtonIsVisible, playButtonIsVisible)
            playButtonSize = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_playButtonSize, playButtonSize)
            playButtonMarginTop = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_playButtonMarginTop, playButtonMarginTop)
            playButtonMarginBottom = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_playButtonMarginBottom, playButtonMarginBottom)
            playButtonColor = attributes.getColor(R.styleable.MediaPlayerView_playButtonColor, playButtonColor)
            playButtonStrokeColor = attributes.getColor(R.styleable.MediaPlayerView_playButtonStrokeColor, playButtonStrokeColor)
            playButtonStrokeWidth = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_playButtonStrokeWidth, playButtonStrokeWidth)
            playButtonRippleColor = attributes.getColor(R.styleable.MediaPlayerView_playButtonRippleColor, playButtonRippleColor)
            playButtonCornerRadius = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_playButtonCornerRadius, playButtonCornerRadius)
            playButtonIconTint = attributes.getColor(R.styleable.MediaPlayerView_playButtonIconTint, playButtonIconTint)

            nextButtonIsVisible = attributes.getBoolean(R.styleable.MediaPlayerView_nextButtonIsVisible, nextButtonIsVisible)
            nextButtonSize = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_nextButtonSize, nextButtonSize)
            nextButtonMarginStart = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_nextButtonMarginStart, nextButtonMarginStart)
            nextButtonColor = attributes.getColor(R.styleable.MediaPlayerView_nextButtonColor, nextButtonColor)
            nextButtonStrokeColor = attributes.getColor(R.styleable.MediaPlayerView_nextButtonStrokeColor, nextButtonStrokeColor)
            nextButtonStrokeWidth = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_nextButtonStrokeWidth, nextButtonStrokeWidth)
            nextButtonRippleColor = attributes.getColor(R.styleable.MediaPlayerView_nextButtonRippleColor, nextButtonRippleColor)
            nextButtonCornerRadius = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_nextButtonCornerRadius, nextButtonCornerRadius)
            nextButtonIconTint = attributes.getColor(R.styleable.MediaPlayerView_nextButtonIconTint, nextButtonIconTint)

            prevButtonIsVisible = attributes.getBoolean(R.styleable.MediaPlayerView_prevButtonIsVisible, prevButtonIsVisible)
            prevButtonSize = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_prevButtonSize, prevButtonSize)
            prevButtonMarginEnd = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_prevButtonMarginEnd, prevButtonMarginEnd)
            prevButtonColor = attributes.getColor(R.styleable.MediaPlayerView_prevButtonColor, prevButtonColor)
            prevButtonStrokeColor = attributes.getColor(R.styleable.MediaPlayerView_prevButtonStrokeColor, prevButtonStrokeColor)
            prevButtonStrokeWidth = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_prevButtonStrokeWidth, prevButtonStrokeWidth)
            prevButtonRippleColor = attributes.getColor(R.styleable.MediaPlayerView_prevButtonRippleColor, prevButtonRippleColor)
            prevButtonCornerRadius = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_prevButtonCornerRadius, prevButtonCornerRadius)
            prevButtonIconTint = attributes.getColor(R.styleable.MediaPlayerView_prevButtonIconTint, prevButtonIconTint)

            attributes.recycle()
        }

        if (!binding.constraintLayout.isInEditMode) {

            mediaPlayer = MediaPlayer()

            myRunnable = Runnable {

                binding.seekBar.progress = mediaPlayer.currentPosition

                val progress = makeTimeString(context, mediaPlayer.currentPosition.toLong())
                binding.currentProgressTextView.text = progress

                myHandler.postDelayed(myRunnable, 100)
            }

            myHandler = Handler(Looper.getMainLooper())

            binding.prevButton.isEnabled = false
            binding.prevButton.setOnClickListener {
                prev()
            }

            binding.playButton.isEnabled = false
            binding.playButton.setOnClickListener {

                if (mediaPlayer.isPlaying) {
                    pause()
                } else {
                    play()
                }
            }

            binding.nextButton.isEnabled = false
            binding.nextButton.setOnClickListener {
                next()
            }

            binding.seekBar.isEnabled = false
            binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

                override fun onStartTrackingTouch(p0: SeekBar?) {
                    pause()
                }

                override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {

                    if (!mediaPlayerPrepared) {
                        return
                    }

                    if (fromUser) {
                        currentPosition = progress
                        val progressString = makeTimeString(context, progress.toLong())
                        binding.currentProgressTextView.text = progressString
                    }
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                    play()
                }
            })
        }
    }

    fun setDataSource(list: MutableList<Audio>) {
        sourceList = list
        if (sourceList.isEmpty()) {
            binding.prevButton.isEnabled = false
            binding.nextButton.isEnabled = false
        }
    }

    fun prepare (i: Int = 0, startOnPrepared: Boolean = true) {

        index = i

        data = sourceList[index].data
        albumArt = sourceList[index].albumArt
        title = sourceList[index].title
        artist = sourceList[index].artist

        Glide.with(context)
            .load(albumArt)
            .error(albumArtPlaceholder)
            .placeholder(albumArtPlaceholder)
            .into(binding.albumArtImageView)

        binding.rootCardView.setCardBackgroundColor(boxColor)

        binding.titleTextView.text = title
        binding.titleTextView.setTextColor(titleTextColor)
        binding.artistTextView.text = artist
        binding.artistTextView.setTextColor(artistTextColor)

        binding.seekBar.isEnabled = false
        binding.prevButton.isEnabled = false
        binding.playButton.isEnabled = false
        binding.nextButton.isEnabled = false

        mediaPlayer.stop()
        mediaPlayer.reset()

        mediaPlayer.setDataSource(data)

        mediaPlayer.prepareAsync()

        mediaPlayer.setOnPreparedListener {

            binding.prevButton.isEnabled = true
            binding.playButton.isEnabled = true
            binding.nextButton.isEnabled = true

            binding.seekBar.isEnabled = true
            binding.seekBar.max = mediaPlayer.duration
            binding.seekBar.progress = 0

            mediaPlayerPrepared = true
            currentPosition = 0

            val progress = makeTimeString(context, currentPosition.toLong())
            binding.currentProgressTextView.text = progress

            val duration = makeTimeString(context, mediaPlayer.duration.toLong())
            binding.maxDurationTextView.text = duration

            if (startOnPrepared) play()
        }

        mediaPlayer.setOnCompletionListener {

            if (sourceList.isEmpty()){
                stop()
            } else {
                next()
            }
        }

        mediaPlayer.setOnErrorListener { _, _, _ ->

            //on error code
            mediaPlayer.stop()
            mediaPlayer.reset()

            mediaPlayerPrepared = false
            currentPosition = 0

            myHandler.removeCallbacks(myRunnable)

            Glide.with(context)
                .load(albumArtPlaceholder)
                .into(binding.albumArtImageView)

            binding.rootCardView.setCardBackgroundColor(errorColorPrimary)

            binding.titleTextView.text = title
            binding.titleTextView.setTextColor(errorColorSecondary)
            binding.artistTextView.text = context.getString(R.string.could_not_load_this_resource)
            binding.artistTextView.setTextColor(errorColorSecondary)

            binding.seekBar.isEnabled = false
            binding.seekBar.max = 100
            binding.seekBar.progress = 0

            binding.playButton.isEnabled = false
            binding.playButton.setImageResource(R.drawable.ic_round_play_arrow_24)

            val progress = makeTimeString(context, 0)
            binding.currentProgressTextView.text = progress

            val duration = makeTimeString(context, 0)
            binding.maxDurationTextView.text = duration

            true
        }
    }

    fun play () {

        if (!mediaPlayerPrepared || mediaPlayer.isPlaying) {
            return
        }

        mediaPlayer.seekTo(currentPosition)
        mediaPlayer.start()

        myHandler.post(myRunnable)

        binding.playButton.setImageResource(R.drawable.ic_round_pause_24)
    }

    fun pause() {

        if (!mediaPlayerPrepared || !mediaPlayer.isPlaying) {
            return
        }

        currentPosition = mediaPlayer.currentPosition
        mediaPlayer.pause()

        myHandler.removeCallbacks(myRunnable)

        binding.playButton.setImageResource(R.drawable.ic_round_play_arrow_24)
    }

    fun stop() {

        if (!mediaPlayerPrepared) {
            return
        }

        currentPosition = 0
        mediaPlayer.pause()

        myHandler.removeCallbacks(myRunnable)

        binding.seekBar.max = mediaPlayer.duration
        binding.seekBar.progress = 0

        val progress = makeTimeString(context, 0)
        binding.currentProgressTextView.text = progress

        val duration = makeTimeString(context, mediaPlayer.duration.toLong())
        binding.maxDurationTextView.text = duration

        binding.playButton.setImageResource(R.drawable.ic_round_play_arrow_24)
    }

    fun prev() {

        if (sourceList.isEmpty()){
            return
        }

        index --

        if (index < 0) {
            index = 0
        }

        prepare(index)
    }

    fun next() {

        if (sourceList.isEmpty()){
            return
        }

        index ++

        if (index + 1 > sourceList.size) {
            index = 0
        }

        prepare(index)
    }

    private fun handleBackground() {

        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.root)
        constraintSet.connect(binding.rootCardView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, boxMargin)
        constraintSet.connect(binding.rootCardView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, boxMargin)
        constraintSet.connect(binding.rootCardView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, boxMargin)
        constraintSet.connect(binding.rootCardView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, boxMargin)
        constraintSet.applyTo(binding.root)

        binding.rootCardView.radius = boxCornerRadius.toFloat()
        binding.rootCardView.setCardBackgroundColor(boxColor)
        binding.rootCardView.cardElevation = boxElevation.toFloat()
    }

    private fun handleAlbumArtImageView() {

        binding.albumArtCardView.isVisible = albumArtIsVisible

        binding.albumArtCardView.layoutParams = LayoutParams(albumArtWidth, albumArtHeight)

        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.constraintLayout)
        constraintSet.connect(binding.albumArtCardView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, albumArtMarginEnd)
        constraintSet.connect(binding.albumArtCardView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, albumArtMarginStart)
        constraintSet.connect(binding.albumArtCardView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, albumArtMarginTop)
        constraintSet.applyTo(binding.constraintLayout)

        binding.albumArtCardView.radius = albumArtBoxCornerRadius.toFloat()
        binding.albumArtCardView.cardElevation = albumArtBoxElevation.toFloat()
    }

    private fun handleTitleTextView() {

        binding.titleTextView.isVisible = titleIsVisible

        binding.titleTextView.setTextColor(artistTextColor)

        binding.titleTextView.setTextSize(textSizeUnit, titleTextSize.toFloat())

        if (fontFamilyResId != -1) {
            binding.titleTextView.typeface = ResourcesCompat.getFont(context, fontFamilyResId)
        }
    }

    private fun handleArtistTextView() {

        binding.artistTextView.isVisible = artistIsVisible

        binding.artistTextView.setTextColor(artistTextColor)

        binding.artistTextView.setTextSize(textSizeUnit, artistTextSize.toFloat())

        if (fontFamilyResId != -1) {
            binding.artistTextView.typeface = ResourcesCompat.getFont(context, fontFamilyResId)
        }
    }

    private fun handlePlayButton() {

        binding.playButton.isVisible = playButtonIsVisible

        binding.playButton.layoutParams = LayoutParams(playButtonSize, playButtonSize)

        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.constraintLayout)
        constraintSet.connect(binding.playButton.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, playButtonMarginBottom)
        constraintSet.connect(binding.playButton.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, binding.playButton.marginEnd)
        constraintSet.connect(binding.playButton.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, binding.playButton.marginStart)
        constraintSet.connect(binding.playButton.id, ConstraintSet.TOP, binding.currentProgressTextView.id, ConstraintSet.BOTTOM, playButtonMarginTop)
        constraintSet.applyTo(binding.constraintLayout)

        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.OVAL
        gradientDrawable.cornerRadius = playButtonCornerRadius.toFloat()
        gradientDrawable.setColor(playButtonColor)
        gradientDrawable.setStroke(playButtonStrokeWidth, playButtonStrokeColor)

        val rippleDrawable = RippleDrawable(ColorStateList.valueOf(playButtonRippleColor), gradientDrawable, null)

        binding.playButton.background = rippleDrawable

        if (playButtonIconTint != 1) {
            binding.playButton.imageTintList = ColorStateList.valueOf(playButtonIconTint)
        }
    }

    private fun handleNextButton() {

        binding.nextButton.isVisible = nextButtonIsVisible

        binding.nextButton.layoutParams = LayoutParams(nextButtonSize, nextButtonSize)

        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.constraintLayout)
        constraintSet.connect(binding.nextButton.id, ConstraintSet.BOTTOM, binding.playButton.id, ConstraintSet.BOTTOM, binding.nextButton.marginBottom)
        constraintSet.connect(binding.nextButton.id, ConstraintSet.START, binding.playButton.id, ConstraintSet.END, nextButtonMarginStart)
        constraintSet.connect(binding.nextButton.id, ConstraintSet.TOP, binding.playButton.id, ConstraintSet.TOP, binding.nextButton.marginTop)
        constraintSet.applyTo(binding.constraintLayout)

        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.OVAL
        gradientDrawable.cornerRadius = nextButtonCornerRadius.toFloat()
        gradientDrawable.setColor(nextButtonColor)
        gradientDrawable.setStroke(nextButtonStrokeWidth, nextButtonStrokeColor)

        val rippleDrawable = RippleDrawable(ColorStateList.valueOf(nextButtonRippleColor), gradientDrawable, null)

        binding.nextButton.background = rippleDrawable

        if (nextButtonIconTint != 1) {
            binding.nextButton.imageTintList = ColorStateList.valueOf(nextButtonIconTint)
        }
    }

    private fun handlePrevButton() {

        binding.prevButton.isVisible = prevButtonIsVisible

        binding.prevButton.layoutParams = LayoutParams(prevButtonSize, prevButtonSize)

        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.constraintLayout)
        constraintSet.connect(binding.prevButton.id, ConstraintSet.BOTTOM, binding.playButton.id, ConstraintSet.BOTTOM, binding.prevButton.marginBottom)
        constraintSet.connect(binding.prevButton.id, ConstraintSet.END, binding.playButton.id, ConstraintSet.START, prevButtonMarginEnd)
        constraintSet.connect(binding.prevButton.id, ConstraintSet.TOP, binding.playButton.id, ConstraintSet.TOP, binding.prevButton.marginTop)
        constraintSet.applyTo(binding.constraintLayout)

        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.OVAL
        gradientDrawable.cornerRadius = prevButtonCornerRadius.toFloat()
        gradientDrawable.setColor(prevButtonColor)
        gradientDrawable.setStroke(prevButtonStrokeWidth, prevButtonStrokeColor)

        val rippleDrawable = RippleDrawable(ColorStateList.valueOf(prevButtonRippleColor), gradientDrawable, null)

        binding.prevButton.background = rippleDrawable

        if (prevButtonIconTint != 1) {
            binding.prevButton.imageTintList = ColorStateList.valueOf(prevButtonIconTint)
        }
    }

    private fun handleSeekBar() {

        binding.seekBar.isVisible = seekBarIsVisible

        binding.seekBar.thumbTintList = ColorStateList.valueOf(seekBarThumbTint)
        binding.seekBar.progressTintList = ColorStateList.valueOf(seekBarProgressTint)
        binding.seekBar.progressBackgroundTintList = ColorStateList.valueOf(seekBarProgressBackgroundTint)

        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.constraintLayout)
        constraintSet.connect(binding.seekBar.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, binding.titleTextView.marginEnd)
        constraintSet.connect(binding.seekBar.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, binding.titleTextView.marginStart)
        constraintSet.connect(binding.seekBar.id, ConstraintSet.TOP, binding.artistTextView.id, ConstraintSet.BOTTOM, seekBarMarginTop)
        constraintSet.applyTo(binding.constraintLayout)
    }

    private fun handleProgressTextView() {

        binding.currentProgressTextView.setTextColor(progressTextColor)
        binding.maxDurationTextView.setTextColor(progressTextColor)

        binding.currentProgressTextView.setTextSize(textSizeUnit, progressTextSize.toFloat())
        binding.maxDurationTextView.setTextSize(textSizeUnit, progressTextSize.toFloat())

        if (fontFamilyResId != -1) {
            binding.currentProgressTextView.typeface = ResourcesCompat.getFont(context, fontFamilyResId)
            binding.maxDurationTextView.typeface = ResourcesCompat.getFont(context, fontFamilyResId)
        }
    }

    private fun makeTimeString(context: Context, millis: Long): String {

        val hours = (millis / (1000 * 60 * 60)).toInt()
        val minutes = (millis % (1000 * 60 * 60) / (1000 * 60)).toInt()
        val seconds = (millis % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()

        return if (hours > 0) context.getString(R.string.hours_minutes_seconds, hours, minutes, seconds) else context.getString(R.string.minutes_seconds, minutes, seconds)
    }

    data class Audio(var data: String, var albumArt: Any?, var title: String, var artist: String)
}