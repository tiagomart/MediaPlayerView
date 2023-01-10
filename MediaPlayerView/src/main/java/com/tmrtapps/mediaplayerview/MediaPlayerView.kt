package com.tmrtapps.mediaplayerview

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.annotation.FontRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.*
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.tmrtapps.mediaplayerview.databinding.MediaPlayerBinding

class MediaPlayerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    @ColorInt
    var boxColor = ContextCompat.getColor(context, R.color.boxColor)
        set(value) {
            field = value
            cardView()
        }

    @Dimension
    var boxCornerRadius = context.resources.getDimensionPixelSize(R.dimen.boxCornerRadius)
        set(value) {
            field = value
            cardView()
        }

    @Dimension
    var boxElevation = context.resources.getDimensionPixelSize(R.dimen.boxElevation)
        set(value) {
            field = value
            cardView()
        }

    @Dimension
    var boxPadding = context.resources.getDimensionPixelSize(R.dimen.boxPadding)
        set(value) {
            field = value
            cardView()
        }

    @ColorInt
    var boxErrorColor = ContextCompat.getColor(context, R.color.boxErrorColor)


    var albumArtIsVisible = context.resources.getBoolean(R.bool.albumArtIsVisible)
        set(value) {
            field = value
            albumArt()
        }

    @Dimension
    var albumArtWidth = context.resources.getDimensionPixelSize(R.dimen.albumArtWidth)
        set(value) {
            field = value
            albumArt()
        }

    @Dimension
    var albumArtHeight = context.resources.getDimensionPixelSize(R.dimen.albumArtHeight)
        set(value) {
            field = value
            albumArt()
        }

    @Dimension
    var albumArtMarginEnd = context.resources.getDimensionPixelSize(R.dimen.albumArtMarginEnd)
        set(value) {
            field = value
            albumArt()
        }

    @Dimension
    var albumArtMarginStart = context.resources.getDimensionPixelSize(R.dimen.albumArtMarginStart)
        set(value) {
            field = value
            albumArt()
        }

    @Dimension
    var albumArtMarginTop = context.resources.getDimensionPixelSize(R.dimen.albumArtMarginTop)
        set(value) {
            field = value
            albumArt()
        }

    @Dimension
    var albumArtBoxCornerRadius = context.resources.getDimensionPixelSize(R.dimen.albumArtBoxCornerRadius)
        set(value) {
            field = value
            albumArt()
        }

    @Dimension
    var albumArtBoxElevation = context.resources.getDimensionPixelSize(R.dimen.albumArtBoxElevation)
        set(value) {
            field = value
            albumArt()
        }

    var albumArtPlaceholder: Drawable? = ContextCompat.getDrawable(context, R.drawable.default_album_art_image)

    var albumArtScaleType = ImageView.ScaleType.FIT_CENTER
        set(value) {
            field = value
            albumArt()
        }


    var titleIsVisible = context.resources.getBoolean(R.bool.titleIsVisible)
        set(value) {
            field = value
            titleTextView()
        }

    @Dimension
    var titleTextSize = context.resources.getDimensionPixelSize(R.dimen.titleTextSize)
        set(value) {
            field = value
            titleTextView()
        }

    @ColorInt
    var titleTextColor = ContextCompat.getColor(context, R.color.titleTextColor)
        set(value) {
            field = value
            titleTextView()
        }

    var textSizeUnit = TypedValue.COMPLEX_UNIT_PX
        set(value) {
            field = value
            titleTextView()
            artistTextView()
        }

    @FontRes
    var titleFontFamilyResId = -1
        set(value) {
            field = value
            titleTextView()
        }

    var titleTextStyle = Typeface.BOLD
        set(value) {
            field = value
            titleTextView()
        }


    var artistIsVisible = context.resources.getBoolean(R.bool.artistIsVisible)
        set(value) {
            field = value
            artistTextView()
        }

    @Dimension
    var artistTextSize = context.resources.getDimensionPixelSize(R.dimen.artistTextSize)
        set(value) {
            field = value
            artistTextView()
        }

    @ColorInt
    var artistTextColor = ContextCompat.getColor(context, R.color.artistTextColor)
        set(value) {
            field = value
            artistTextView()
        }

    @FontRes
    var artistFontFamilyResId = -1
        set(value) {
            field = value
            artistTextView()
        }

    var artistTextStyle = Typeface.NORMAL
        set(value) {
            field = value
            artistTextView()
        }


    var seekBarIsVisible = context.resources.getBoolean(R.bool.seekBarIsVisible)
        set(value) {
            field = value
            seekBar()
        }

    @Dimension
    var seekBarMarginTop = context.resources.getDimensionPixelSize(R.dimen.seekBarMarginTop)
        set(value) {
            field = value
            seekBar()
        }

    @ColorInt
    var seekBarThumbTint = ContextCompat.getColor(context, R.color.seekBarThumbTint)
        set(value) {
            field = value
            seekBar()
        }

    @ColorInt
    var seekBarProgressTint = ContextCompat.getColor(context, R.color.seekBarProgressTint)
        set(value) {
            field = value
            seekBar()
        }

    @ColorInt
    var seekBarProgressBackgroundTint = ContextCompat.getColor(context, R.color.seekBarProgressBackgroundTint)
        set(value) {
            field = value
            seekBar()
        }


    @Dimension
    var progressTextSize = context.resources.getDimensionPixelSize(R.dimen.progressTextSize)
        set(value) {
            field = value
            progressTextView()
        }

    @ColorInt
    var progressTextColor = ContextCompat.getColor(context, R.color.progressTextColor)
        set(value) {
            field = value
            progressTextView()
        }

    @FontRes
    var progressFontFamilyResId = -1
        set(value) {
            field = value
            progressTextView()
        }

    var progressTextStyle = Typeface.NORMAL
        set(value) {
            field = value
            progressTextView()
        }


    var playButtonIsVisible = context.resources.getBoolean(R.bool.playButtonIsVisible)
        set(value) {
            field = value
            playButton()
        }

    @Dimension
    var playButtonSize = context.resources.getDimensionPixelSize(R.dimen.playButtonSize)
        set(value) {
            field = value
            playButton()
        }

    @Dimension
    var playButtonMarginTop = context.resources.getDimensionPixelSize(R.dimen.playButtonMarginTop)
        set(value) {
            field = value
            playButton()
        }

    @Dimension
    var playButtonMarginBottom = context.resources.getDimensionPixelSize(R.dimen.playButtonMarginBottom)
        set(value) {
            field = value
            playButton()
        }

    @ColorInt
    var playButtonColor = ContextCompat.getColor(context, R.color.buttonBackgroundColor)
        set(value) {
            field = value
            playButton()
        }

    @ColorInt
    var playButtonStrokeColor = ContextCompat.getColor(context, R.color.buttonStrokeColor)
        set(value) {
            field = value
            playButton()
        }

    @Dimension
    var playButtonStrokeWidth = context.resources.getDimensionPixelSize(R.dimen.buttonStrokeWidth)
        set(value) {
            field = value
            playButton()
        }

    @ColorInt
    var playButtonRippleColor = ContextCompat.getColor(context, R.color.buttonRippleColor)
        set(value) {
            field = value
            playButton()
        }

    @Dimension
    var playButtonCornerRadius = context.resources.getDimensionPixelSize(R.dimen.buttonCornerRadius)
        set(value) {
            field = value
            playButton()
        }

    @ColorInt
    var playButtonIconTint = ContextCompat.getColor(context, R.color.buttonIconTint)
        set(value) {
            field = value
            playButton()
        }


    var nextButtonIsVisible = context.resources.getBoolean(R.bool.nextButtonIsVisible)
        set(value) {
            field = value
            nextButton()
        }

    @Dimension
    var nextButtonSize = context.resources.getDimensionPixelSize(R.dimen.nextButtonSize)
        set(value) {
            field = value
            nextButton()
        }

    @Dimension
    var nextButtonMarginStart = context.resources.getDimensionPixelSize(R.dimen.nextButtonMarginStart)
        set(value) {
            field = value
            nextButton()
        }

    @ColorInt
    var nextButtonColor = ContextCompat.getColor(context, R.color.buttonBackgroundColor)
        set(value) {
            field = value
            nextButton()
        }

    @ColorInt
    var nextButtonStrokeColor = ContextCompat.getColor(context, R.color.buttonStrokeColor)
        set(value) {
            field = value
            nextButton()
        }

    @Dimension
    var nextButtonStrokeWidth = context.resources.getDimensionPixelSize(R.dimen.buttonStrokeWidth)
        set(value) {
            field = value
            nextButton()
        }

    @ColorInt
    var nextButtonRippleColor = ContextCompat.getColor(context, R.color.buttonRippleColor)
        set(value) {
            field = value
            nextButton()
        }

    @Dimension
    var nextButtonCornerRadius = context.resources.getDimensionPixelSize(R.dimen.buttonCornerRadius)
        set(value) {
            field = value
            nextButton()
        }

    @ColorInt
    var nextButtonIconTint = ContextCompat.getColor(context, R.color.buttonIconTint)
        set(value) {
            field = value
            nextButton()
        }


    var prevButtonIsVisible = context.resources.getBoolean(R.bool.prevButtonIsVisible)
        set(value) {
            field = value
            prevButton()
        }

    @Dimension
    var prevButtonSize = context.resources.getDimensionPixelSize(R.dimen.prevButtonSize)
        set(value) {
            field = value
            prevButton()
        }

    @Dimension
    var prevButtonMarginEnd = context.resources.getDimensionPixelSize(R.dimen.prevButtonMarginEnd)
        set(value) {
            field = value
            prevButton()
        }

    @ColorInt
    var prevButtonColor = ContextCompat.getColor(context, R.color.buttonBackgroundColor)
        set(value) {
            field = value
            prevButton()
        }

    @ColorInt
    var prevButtonStrokeColor = ContextCompat.getColor(context, R.color.buttonStrokeColor)
        set(value) {
            field = value
            prevButton()
        }

    @Dimension
    var prevButtonStrokeWidth = context.resources.getDimensionPixelSize(R.dimen.buttonStrokeWidth)
        set(value) {
            field = value
            prevButton()
        }

    @ColorInt
    var prevButtonRippleColor = ContextCompat.getColor(context, R.color.buttonRippleColor)
        set(value) {
            field = value
            prevButton()
        }

    @Dimension
    var prevButtonCornerRadius = context.resources.getDimensionPixelSize(R.dimen.buttonCornerRadius)
        set(value) {
            field = value
            prevButton()
        }

    @ColorInt
    var prevButtonIconTint = ContextCompat.getColor(context, R.color.buttonIconTint)
        set(value) {
            field = value
            prevButton()
        }

    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    private var audioFocusChangeListener: AudioManager.OnAudioFocusChangeListener? = null
    private var playbackAttributes: AudioAttributes? = null
    private var focusRequest: AudioFocusRequest? = null

    private var mediaPlayer: MediaPlayer? = null
    private var mediaPlayerPrepared = false
    private var mediaPlayerCurrentPosition = 0

    private var sourceList = mutableListOf<Audio>()
    private var index = 0

    private lateinit var myHandler: Handler
    private lateinit var myRunnable: Runnable

    private val binding = MediaPlayerBinding.inflate(LayoutInflater.from(context), this, true)

    init {

        attrs?.let {

            val attributes = context.obtainStyledAttributes(it, R.styleable.MediaPlayerView)

            boxColor = attributes.getColor(R.styleable.MediaPlayerView_boxColor, boxColor)
            boxCornerRadius = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_boxCornerRadius, boxCornerRadius)
            boxElevation = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_boxElevation, boxElevation)
            boxPadding = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_boxPadding, boxPadding)
            boxErrorColor = attributes.getColor(R.styleable.MediaPlayerView_boxErrorColor, boxErrorColor)

            albumArtIsVisible = attributes.getBoolean(R.styleable.MediaPlayerView_albumArtIsVisible, albumArtIsVisible)
            albumArtWidth = attributes.getLayoutDimension(R.styleable.MediaPlayerView_albumArtWidth, albumArtWidth)
            albumArtHeight = attributes.getLayoutDimension(R.styleable.MediaPlayerView_albumArtHeight, albumArtHeight)
            albumArtMarginTop = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_albumArtMarginTop, albumArtMarginTop)
            albumArtMarginEnd = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_albumArtMarginEnd, albumArtMarginEnd)
            albumArtMarginStart = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_albumArtMarginStart, albumArtMarginStart)
            albumArtBoxCornerRadius = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_albumArtBoxCornerRadius, albumArtBoxCornerRadius)
            albumArtBoxElevation = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_albumArtBoxElevation, albumArtBoxElevation)
            albumArtPlaceholder = attributes.getDrawable(R.styleable.MediaPlayerView_albumArtPlaceholder) ?: albumArtPlaceholder
            albumArtScaleType = intToImageViewScaleType(attributes.getInt(R.styleable.MediaPlayerView_albumArtScaleType, imageViewScaleTypeToInt(albumArtScaleType)))

            titleIsVisible = attributes.getBoolean(R.styleable.MediaPlayerView_titleIsVisible, titleIsVisible)
            titleTextSize = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_titleTextSize, titleTextSize)
            titleTextColor = attributes.getColor(R.styleable.MediaPlayerView_titleTextColor, titleTextColor)
            titleFontFamilyResId = attributes.getResourceId(R.styleable.MediaPlayerView_titleFontFamily, -1)
            titleTextStyle = attributes.getInt(R.styleable.MediaPlayerView_titleTextStyleTypeFace, titleTextStyle)

            artistIsVisible = attributes.getBoolean(R.styleable.MediaPlayerView_artistIsVisible, artistIsVisible)
            artistTextSize = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_artistTextSize, artistTextSize)
            artistTextColor = attributes.getColor(R.styleable.MediaPlayerView_artistTextColor, artistTextColor)
            artistFontFamilyResId = attributes.getResourceId(R.styleable.MediaPlayerView_artistFontFamily, -1)
            artistTextStyle = attributes.getInt(R.styleable.MediaPlayerView_artistTextStyleTypeFace, artistTextStyle)

            seekBarIsVisible = attributes.getBoolean(R.styleable.MediaPlayerView_seekBarIsVisible, seekBarIsVisible)
            seekBarMarginTop = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_seekBarMarginTop, seekBarMarginTop)
            seekBarThumbTint = attributes.getColor(R.styleable.MediaPlayerView_seekBarThumbTint, seekBarThumbTint)
            seekBarProgressTint = attributes.getColor(R.styleable.MediaPlayerView_seekBarProgressTint, seekBarProgressTint)
            seekBarProgressBackgroundTint = attributes.getColor(R.styleable.MediaPlayerView_seekBarProgressBackgroundTint, seekBarProgressBackgroundTint)

            progressTextSize = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_progressTextSize, progressTextSize)
            progressTextColor = attributes.getColor(R.styleable.MediaPlayerView_progressTextColor, progressTextColor)
            progressFontFamilyResId = attributes.getResourceId(R.styleable.MediaPlayerView_progressFontFamily, -1)
            progressTextStyle = attributes.getInt(R.styleable.MediaPlayerView_progressTextStyleTypeFace, progressTextStyle)

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

            Typeface.BOLD
        }

        if (!binding.constraintLayout.isInEditMode) {

            mediaPlayer = MediaPlayer()

            myRunnable = Runnable {

                if (mediaPlayer == null) {
                    return@Runnable
                }

                binding.seekBar.progress = mediaPlayer!!.currentPosition

                val progress = mediaPlayer!!.currentPosition.toLong().toTimeString(context)
                binding.currentProgressTextView.text = progress

                myHandler.postDelayed(myRunnable, 100)
            }

            myHandler = Handler(Looper.getMainLooper())

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                playbackAttributes = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()

                audioFocusChangeListener = AudioManager.OnAudioFocusChangeListener { focusChange ->
                    when (focusChange) {

                        AudioManager.AUDIOFOCUS_GAIN -> {
                            play()
                        }

                        AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                            pause()
                        }

                        AudioManager.AUDIOFOCUS_LOSS -> {
                            pause(true)
                        }
                    }
                }

                focusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                    .setAudioAttributes(playbackAttributes!!)
                    .setOnAudioFocusChangeListener(audioFocusChangeListener!!)
                    .setAcceptsDelayedFocusGain(true)
                    .build()
            }

            binding.prevButton.isEnabled = false
            binding.prevButton.setOnClickListener {
                prev()
            }

            binding.playButton.isEnabled = false

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
                        mediaPlayerCurrentPosition = progress
                        val progressString = progress.toLong().toTimeString(context)
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

        sourceList.clear()
        sourceList.addAll(list)
    }

    fun prepare (i: Int = 0, startOnPrepared: Boolean = true) {

        index = i

        binding.cardView.setAnimatedBackgroundColor(boxColor, 300)

        if (isValidContextForGlide(context)) {
            Glide.with(context)
                .load(sourceList[index].albumArt)
                .error(albumArtPlaceholder)
                .placeholder(albumArtPlaceholder)
                .into(binding.albumArtImageView)
        }

        binding.titleTextView.text = sourceList[index].title
        binding.titleTextView.setAnimatedTextColor(titleTextColor, 300)

        binding.artistTextView.text = sourceList[index].artist
        binding.artistTextView.setAnimatedTextColor(artistTextColor, 300)

        binding.seekBar.isEnabled = false
        binding.seekBar.max = 100
        binding.seekBar.progress = 0

        binding.currentProgressTextView.setText(R.string.zero_zero)
        binding.maxProgressTextView.setText(R.string.zero_zero)

        binding.prevButton.isEnabled = false
        binding.playButton.isEnabled = false
        binding.nextButton.isEnabled = false

        try {

            if (mediaPlayer == null) mediaPlayer = MediaPlayer()

            mediaPlayer!!.reset()

            mediaPlayer!!.setDataSource(sourceList[index].data)

            mediaPlayer!!.prepareAsync()

            mediaPlayer!!.setOnPreparedListener {

                binding.seekBar.isEnabled = true
                binding.seekBar.max = mediaPlayer!!.duration
                binding.seekBar.progress = 0

                val duration = mediaPlayer!!.duration.toLong().toTimeString(context)
                binding.maxProgressTextView.text = duration

                binding.prevButton.isEnabled = true

                binding.playButton.isEnabled = true
                binding.playButton.setOnClickListener {
                    if (mediaPlayer!!.isPlaying) {
                        pause()
                    } else {
                        play()
                    }
                }

                binding.nextButton.isEnabled = true

                mediaPlayerPrepared = true
                mediaPlayerCurrentPosition = 0

                if (startOnPrepared) play()
            }

            mediaPlayer!!.setOnErrorListener { _, _, _ ->

                binding.cardView.setAnimatedBackgroundColor(boxErrorColor, 300)

                binding.seekBar.isEnabled = false
                binding.seekBar.max = 100
                binding.seekBar.progress = 0

                binding.currentProgressTextView.setText(R.string.zero_zero)
                binding.maxProgressTextView.setText(R.string.zero_zero)

                binding.prevButton.isEnabled = true

                binding.playButton.isEnabled = true
                binding.playButton.setImageResource(R.drawable.ic_round_play_arrow_24)
                binding.playButton.setOnClickListener {
                    Toast.makeText(context, R.string.cannot_play_this_audio, Toast.LENGTH_SHORT).show()
                }

                binding.nextButton.isEnabled = true

                mediaPlayerPrepared = false
                mediaPlayerCurrentPosition = 0

                myHandler.removeCallbacks(myRunnable)

                true
            }

        } catch (e: Exception) {

            binding.cardView.setAnimatedBackgroundColor(boxErrorColor, 300)

            if (isValidContextForGlide(context)) {
                Glide.with(context)
                    .load(albumArtPlaceholder)
                    .into(binding.albumArtImageView)
            }

            binding.seekBar.isEnabled = false

            binding.prevButton.isEnabled = true

            binding.playButton.isEnabled = true
            binding.playButton.setImageResource(R.drawable.ic_round_play_arrow_24)
            binding.playButton.setOnClickListener {
                Toast.makeText(context, R.string.cannot_play_this_audio, Toast.LENGTH_SHORT).show()
            }

            binding.nextButton.isEnabled = true

            mediaPlayerPrepared = false
            mediaPlayerCurrentPosition = 0

            myHandler.removeCallbacks(myRunnable)
        }
    }

    fun play () {

        if (mediaPlayer == null) {
            return
        }

        if (!mediaPlayerPrepared || mediaPlayer!!.isPlaying) {
            return
        }

        var canStart = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val audioFocusRequest = audioManager.requestAudioFocus(focusRequest!!)

            canStart = audioFocusRequest == AudioManager.AUDIOFOCUS_REQUEST_GRANTED
        }

        if (canStart) {

            mediaPlayer!!.seekTo(mediaPlayerCurrentPosition)
            mediaPlayer!!.start()

            myHandler.post(myRunnable)

            binding.playButton.setImageResource(R.drawable.ic_round_pause_24)
        }
    }

    fun pause(resetPosition: Boolean = false) {

        if (mediaPlayer == null) {
            return
        }

        if (!mediaPlayerPrepared || !mediaPlayer!!.isPlaying) {
            return
        }

        if (resetPosition) {
            binding.seekBar.progress = 0
            binding.currentProgressTextView.setText(R.string.zero_zero)
        }

        mediaPlayerCurrentPosition = if (resetPosition) 0 else mediaPlayer!!.currentPosition
        mediaPlayer!!.pause()

        myHandler.removeCallbacks(myRunnable)

        binding.playButton.setImageResource(R.drawable.ic_round_play_arrow_24)
    }

    fun prev() {

        if (sourceList.isEmpty()) {
            return
        }

        index --

        if (index < 0) {
            index = sourceList.size - 1
        }

        prepare(index)
    }

    fun next() {

        if (sourceList.isEmpty()) {
            return
        }

        index ++

        if (index + 1 > sourceList.size) {
            index = 0
        }

        prepare(index)
    }

    fun release() {

        sourceList.clear()

        mediaPlayer?.release()
        mediaPlayer = null
        
        mediaPlayerPrepared = false
        mediaPlayerCurrentPosition = 0

        myHandler.removeCallbacks(myRunnable)
        
        binding.cardView.setAnimatedBackgroundColor(boxColor, 300)

        if (isValidContextForGlide(context)) {
            Glide.with(context)
                .load(albumArtPlaceholder)
                .into(binding.albumArtImageView)
        }

        binding.titleTextView.setText(R.string.title)
        binding.titleTextView.setAnimatedTextColor(titleTextColor, 300)

        binding.artistTextView.setText(R.string.artist)
        binding.artistTextView.setAnimatedTextColor(artistTextColor, 300)

        binding.seekBar.isEnabled = false
        binding.seekBar.max = 100
        binding.seekBar.progress = 0
        
        binding.currentProgressTextView.setText(R.string.zero_zero)
        binding.maxProgressTextView.setText(R.string.zero_zero)

        binding.prevButton.isEnabled = false

        binding.playButton.isEnabled = false
        binding.playButton.setImageResource(R.drawable.ic_round_play_arrow_24)

        binding.nextButton.isEnabled = false
    }

    private fun cardView() {

        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.root)
        constraintSet.connect(binding.cardView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, boxPadding)
        constraintSet.connect(binding.cardView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, boxPadding)
        constraintSet.connect(binding.cardView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, boxPadding)
        constraintSet.connect(binding.cardView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, boxPadding)
        constraintSet.applyTo(binding.root)

        binding.cardView.radius = boxCornerRadius.toFloat()
        binding.cardView.setCardBackgroundColor(boxColor)
        binding.cardView.cardElevation = boxElevation.toFloat()
    }

    private fun albumArt() {

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

        binding.albumArtImageView.scaleType = albumArtScaleType
    }

    private fun titleTextView() {

        binding.titleTextView.isVisible = titleIsVisible

        binding.titleTextView.setTextColor(artistTextColor)

        binding.titleTextView.setTextSize(textSizeUnit, titleTextSize.toFloat())

        if (titleFontFamilyResId != -1) {
            binding.titleTextView.typeface = ResourcesCompat.getFont(context, titleFontFamilyResId)
        }

        binding.titleTextView.setTypeface(binding.titleTextView.typeface, titleTextStyle)
    }

    private fun artistTextView() {

        binding.artistTextView.isVisible = artistIsVisible

        binding.artistTextView.setTextColor(artistTextColor)

        binding.artistTextView.setTextSize(textSizeUnit, artistTextSize.toFloat())

        if (artistFontFamilyResId != -1) {
            binding.artistTextView.typeface = ResourcesCompat.getFont(context, artistFontFamilyResId)
        }

        binding.artistTextView.setTypeface(binding.artistTextView.typeface, artistTextStyle)
    }

    private fun playButton() {

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

    private fun nextButton() {

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

    private fun prevButton() {

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

    private fun seekBar() {

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

    private fun progressTextView() {

        binding.currentProgressTextView.setTextColor(progressTextColor)
        binding.currentProgressTextView.setTextSize(textSizeUnit, progressTextSize.toFloat())

        if (progressFontFamilyResId != -1)  binding.currentProgressTextView.typeface = ResourcesCompat.getFont(context, progressFontFamilyResId)

        binding.currentProgressTextView.setTypeface(binding.currentProgressTextView.typeface, progressTextStyle)

        binding.maxProgressTextView.setTextSize(textSizeUnit, progressTextSize.toFloat())
        binding.maxProgressTextView.setTextColor(progressTextColor)

        if (progressFontFamilyResId != -1) binding.maxProgressTextView.typeface = ResourcesCompat.getFont(context, progressFontFamilyResId)

        binding.maxProgressTextView.setTypeface(binding.maxProgressTextView.typeface, progressTextStyle)
    }

    private fun isValidContextForGlide(context: Context?): Boolean {

        if (context == null) {
            return false
        }

        if (context is Activity) {
            if (context.isDestroyed || context.isFinishing) {
                return false
            }
        }

        if (context is FragmentActivity) {
            if (context.isDestroyed || context.isFinishing) {
                return false
            }
        }

        return true
    }

    private fun intToImageViewScaleType(i: Int): ImageView.ScaleType {

        when (i) {

            0 -> {
                return ImageView.ScaleType.MATRIX
            }

            1 -> {
                return ImageView.ScaleType.FIT_XY
            }

            2 -> {
                return ImageView.ScaleType.FIT_START
            }

            3 -> {
                return ImageView.ScaleType.FIT_CENTER
            }

            4 -> {
                return ImageView.ScaleType.FIT_END
            }

            5 -> {
                return ImageView.ScaleType.CENTER
            }

            6 -> {
                return ImageView.ScaleType.CENTER_CROP
            }

            7 -> {
                return ImageView.ScaleType.CENTER_INSIDE
            }
        }

        return ImageView.ScaleType.FIT_CENTER
    }

    private fun imageViewScaleTypeToInt(scaleType: ImageView.ScaleType): Int {

        when (scaleType) {

            ImageView.ScaleType.MATRIX -> {
                return 0
            }

            ImageView.ScaleType.FIT_XY -> {
                return 1
            }

            ImageView.ScaleType.FIT_START -> {
                return 2
            }

            ImageView.ScaleType.FIT_CENTER -> {
                return 3
            }

            ImageView.ScaleType.FIT_END -> {
                return 4
            }

            ImageView.ScaleType.CENTER -> {
                return 5
            }

            ImageView.ScaleType.CENTER_CROP -> {
                return 6
            }

            ImageView.ScaleType.CENTER_INSIDE -> {
                return 7
            }

            else -> {
                return 3
            }
        }
    }

}