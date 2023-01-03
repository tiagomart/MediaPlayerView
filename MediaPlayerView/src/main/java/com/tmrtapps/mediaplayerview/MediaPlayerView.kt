package com.tmrtapps.mediaplayerview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
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
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.annotation.FontRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.tmrtapps.mediaplayerview.databinding.MediaPlayerBinding

@SuppressLint("ClickableViewAccessibility")
class MediaPlayerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    @Dimension
    var buttonSize = context.resources.getDimensionPixelSize(R.dimen.buttonSize)
        set(value) {
            field = value
            handleImageButton()
        }

    @ColorInt
    var buttonColor = ContextCompat.getColor(context, R.color.button_background_color)
        set(value) {
            field = value
            handleImageButton()
        }

    @ColorInt
    var buttonStrokeColor = 0
        set(value) {
            field = value
            handleImageButton()
        }

    @Dimension
    var buttonStrokeWidth = context.resources.getDimensionPixelSize(R.dimen.strokeWidth)
        set(value) {
            field = value
            handleImageButton()
        }

    @ColorInt
    var buttonRippleColor = ContextCompat.getColor(context, R.color.button_ripple_color)
        set(value) {
            field = value
            handleImageButton()
        }

    @Dimension
    var buttonCornerRadius = context.resources.getDimensionPixelSize(R.dimen.cornerRadius)
        set(value) {
            field = value
            handleImageButton()
        }

    @ColorInt
    var buttonImageTint = 1
        set(value) {
            field = value
            handleImageButton()
        }

    @Dimension
    var buttonMarginEnd = context.resources.getDimensionPixelSize(R.dimen.buttonMarginEnd)
        set(value) {
            field = value
            handleImageButton()
        }

    @Dimension
    var buttonMarginStart = context.resources.getDimensionPixelSize(R.dimen.buttonMarginsStart)
        set(value) {
            field = value
            handleImageButton()
        }

    @Dimension
    var buttonMarginTop = context.resources.getDimensionPixelSize(R.dimen.buttonMarginTop)
        set(value) {
            field = value
            handleImageButton()
        }

    var buttonScaleType = ImageView.ScaleType.CENTER
        set(value) {
            field = value
            handleImageButton()
        }

    @ColorInt
    var textColor = ContextCompat.getColor(context, R.color.text_color)
        set(value) {
            field = value
            handleTextView()
        }

    var textSizeUnit = TypedValue.COMPLEX_UNIT_PX
        set(value) {
            field = value
            handleTextView()
        }

    @Dimension
    var textSize = context.resources.getDimensionPixelSize(R.dimen.textSize)
        set(value) {
            field = value
            handleTextView()
        }

    @FontRes
    var fontFamilyResId = -1
        set(value) {
            field = value
            handleTextView()
        }

    @Dimension
    var textViewMarginEnd = context.resources.getDimensionPixelSize(R.dimen.textViewMarginEnd)
        set(value) {
            field = value
            handleTextView()
        }

    @Dimension
    var textViewMarginStart = context.resources.getDimensionPixelSize(R.dimen.textViewMarginsStart)
        set(value) {
            field = value
            handleTextView()
        }

    @Dimension
    var textViewMarginTop = context.resources.getDimensionPixelSize(R.dimen.textViewMarginTop)
        set(value) {
            field = value
            handleTextView()
        }

    @ColorInt
    var thumbTint = ContextCompat.getColor(context, R.color.thumb_tint)
        set(value) {
            field = value
            handleSeekBar()
        }

    @ColorInt
    var progressTint = ContextCompat.getColor(context, R.color.progress_tint)
        set(value) {
            field = value
            handleSeekBar()
        }

    @ColorInt
    var progressBackgroundTint = ContextCompat.getColor(context, R.color.progress_background_tint)
        set(value) {
            field = value
            handleSeekBar()
        }

    @Dimension
    var seekBarMarginBottom = context.resources.getDimensionPixelSize(R.dimen.seekbarMarginBottom)
        set(value) {
            field = value
            handleSeekBar()
        }

    @Dimension
    var seekBarMarginEnd = context.resources.getDimensionPixelSize(R.dimen.seekbarMarginEnd)
        set(value) {
            field = value
            handleSeekBar()
        }

    @Dimension
    var seekBarMarginStart = context.resources.getDimensionPixelSize(R.dimen.seekbarMarginsStart)
        set(value) {
            field = value
            handleSeekBar()
        }

    @Dimension
    var seekBarMarginTop = context.resources.getDimensionPixelSize(R.dimen.seekbarMarginTop)
        set(value) {
            field = value
            handleSeekBar()
        }

    private var mediaPlayer: MediaPlayer? = null
    private var mediaPlayerPrepared = false
    private var currentPosition = 0
    private var dataSource = ""

    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    private var audioFocusChangeListener: AudioManager.OnAudioFocusChangeListener? = null

    private var playbackAttributes: AudioAttributes? = null

    private var focusRequest: AudioFocusRequest? = null

    private lateinit var myHandler: Handler
    private lateinit var myRunnable: Runnable

    private var onButtonClickListener: ((view: View) -> Unit)? = null
    private var onButtonLongClickListener: ((view: View) -> Unit)? = null
    private var onDataSourceListener: ((dataSource: String) -> Unit)? = null
    private var onPreparedListener: ((mediaPlayer: MediaPlayer) -> Unit)? = null

    private val binding = MediaPlayerBinding.inflate(LayoutInflater.from(context), this, true)

    init {

        attrs?.let {

            val attributes = context.obtainStyledAttributes(it, R.styleable.MediaPlayerView)

            buttonSize = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_buttonSize, buttonSize)
            buttonColor = attributes.getColor(R.styleable.MediaPlayerView_buttonColor, buttonColor)
            buttonStrokeColor = attributes.getColor(R.styleable.MediaPlayerView_buttonStrokeColor, buttonStrokeColor)
            buttonStrokeWidth = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_buttonStrokeWidth, buttonStrokeWidth)
            buttonRippleColor = attributes.getColor(R.styleable.MediaPlayerView_buttonRippleColor, buttonRippleColor)
            buttonCornerRadius = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_buttonCornerRadius, buttonCornerRadius)
            buttonImageTint = attributes.getColor(R.styleable.MediaPlayerView_buttonImageTint, buttonImageTint)
            buttonMarginEnd = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_buttonMarginEnd, buttonMarginEnd)
            buttonMarginStart = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_buttonMarginStart, buttonMarginStart)
            buttonMarginTop = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_buttonMarginTop, buttonMarginTop)
            buttonScaleType = intToScaleType(attributes.getInt(R.styleable.MediaPlayerView_buttonScaleType, scaleTypeToInt(buttonScaleType)))

            textColor = attributes.getColor(R.styleable.MediaPlayerView_textColor, textColor)
            textSize = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_textSize, textSize)
            fontFamilyResId = attributes.getResourceId(R.styleable.MediaPlayerView_textFontFamily, -1)
            textViewMarginEnd = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_textViewMarginEnd, textViewMarginEnd)
            textViewMarginStart = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_textViewMarginStart, textViewMarginStart)
            textViewMarginTop = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_textViewMarginTop, textViewMarginTop)

            thumbTint = attributes.getColor(R.styleable.MediaPlayerView_seekBarThumbTint, thumbTint)
            progressTint = attributes.getColor(R.styleable.MediaPlayerView_seekBarProgressTint, progressTint)
            progressBackgroundTint = attributes.getColor(R.styleable.MediaPlayerView_seekBarProgressBackgroundTint, progressBackgroundTint)

            seekBarMarginBottom = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_seekBarMarginBottom, seekBarMarginBottom)
            seekBarMarginEnd = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_seekBarMarginEnd, seekBarMarginEnd)
            seekBarMarginStart = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_seekBarMarginStart, seekBarMarginStart)
            seekBarMarginTop = attributes.getDimensionPixelSize(R.styleable.MediaPlayerView_seekBarMarginTop, seekBarMarginTop)

            attributes.recycle()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            playbackAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()

            audioFocusChangeListener = AudioManager.OnAudioFocusChangeListener { focusChange ->
                when (focusChange) {

                    AudioManager.AUDIOFOCUS_GAIN -> {
                        mediaPlayer!!.start()
                    }

                    AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                        mediaPlayer!!.pause()
                        mediaPlayer!!.seekTo(0)
                    }

                    AudioManager.AUDIOFOCUS_LOSS -> {
                        mediaPlayer!!.release()
                    }
                }
            }

            focusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                .setAudioAttributes(playbackAttributes!!)
                .setOnAudioFocusChangeListener(audioFocusChangeListener!!)
                .setAcceptsDelayedFocusGain(true)
                .build()
        }

        binding.imageButton.setOnClickListener {

            onButtonClickListener?.invoke(it)

            if (mediaPlayer!!.isPlaying) {
                pause()
            } else {
                start()
            }
        }

        binding.imageButton.setOnLongClickListener {
            onButtonLongClickListener?.invoke(it)
            onButtonLongClickListener != null
        }

        binding.imageButton.setOnTouchListener(AnimationTouchListener())

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onStartTrackingTouch(p0: SeekBar?) {
                pause()
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                if (!mediaPlayerPrepared) {
                    return
                }

                if (fromUser) {
                    currentPosition = progress
                    val progressString = makeTimeString(context, progress.toLong())
                    val durationString = makeTimeString(context, mediaPlayer!!.duration.toLong())
                    binding.textView.text = context.getString(R.string.progress_duration, progressString, durationString)
                }
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                start()
            }
        })

        myRunnable = Runnable {

            val progress = makeTimeString(context, mediaPlayer!!.currentPosition.toLong())
            val duration = makeTimeString(context, mediaPlayer!!.duration.toLong())
            binding.textView.text = context.getString(R.string.progress_duration, progress, duration)

            binding.seekBar.progress = mediaPlayer!!.currentPosition

            myHandler.postDelayed(myRunnable, 100)
        }

        myHandler = Handler(Looper.getMainLooper())
    }

    private fun handleImageButton() {

        binding.imageButton.layoutParams = LayoutParams(buttonSize, buttonSize)

        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        gradientDrawable.cornerRadius = buttonCornerRadius.toFloat()
        gradientDrawable.setColor(buttonColor)
        gradientDrawable.setStroke(buttonStrokeWidth, buttonStrokeColor)

        val rippleDrawable = RippleDrawable(ColorStateList.valueOf(buttonRippleColor), gradientDrawable, null)

        binding.imageButton.background = rippleDrawable

        if (buttonImageTint != 1) binding.imageButton.imageTintList = ColorStateList.valueOf(buttonImageTint)

        binding.imageButton.scaleType = buttonScaleType

        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.root)
        constraintSet.connect(binding.imageButton.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, buttonMarginEnd)
        constraintSet.connect(binding.imageButton.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, buttonMarginStart)
        constraintSet.connect(binding.imageButton.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, buttonMarginTop)
        constraintSet.applyTo(binding.root)
    }

    private fun handleTextView() {

        binding.textView.setTextColor(textColor)

        binding.textView.setTextSize(textSizeUnit, textSize.toFloat())

        if (fontFamilyResId != -1) {
            binding.textView.typeface = ResourcesCompat.getFont(context, fontFamilyResId)
        }

        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.root)
        constraintSet.connect(binding.textView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, textViewMarginEnd)
        constraintSet.connect(binding.textView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, textViewMarginStart)
        constraintSet.connect(binding.textView.id, ConstraintSet.TOP, binding.imageButton.id, ConstraintSet.BOTTOM, textViewMarginTop)
        constraintSet.applyTo(binding.root)
    }

    private fun handleSeekBar() {

        binding.seekBar.thumbTintList = ColorStateList.valueOf(thumbTint)
        binding.seekBar.progressTintList = ColorStateList.valueOf(progressTint)
        binding.seekBar.progressBackgroundTintList = ColorStateList.valueOf(progressBackgroundTint)

        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.root)
        constraintSet.connect(binding.seekBar.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, seekBarMarginBottom)
        constraintSet.connect(binding.seekBar.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, seekBarMarginEnd)
        constraintSet.connect(binding.seekBar.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, seekBarMarginStart)
        constraintSet.connect(binding.seekBar.id, ConstraintSet.TOP, binding.textView.id, ConstraintSet.BOTTOM, seekBarMarginTop)
        constraintSet.applyTo(binding.root)
    }

    fun setOnButtonClickListener(onButtonClickListener: ((view: View) -> Unit)?) {
        this.onButtonClickListener = onButtonClickListener
    }

    fun setOnButtonLongClickListener(onButtonLongClickListener: ((view: View) -> Unit)?) {
        this.onButtonLongClickListener = onButtonLongClickListener
    }

    fun setOnDataSourceListener(onDataSourceListener: ((dataSource: String) -> Unit)?) {
        this.onDataSourceListener = onDataSourceListener
    }

    fun setOnPreparedListener(onPreparedListener: ((mediaPlayer: MediaPlayer) -> Unit)?) {
        this.onPreparedListener = onPreparedListener
    }

    fun setDataSource(dataSource: String) {
        this.dataSource = dataSource
        onDataSourceListener?.invoke(dataSource)
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setDataSource(dataSource)
    }

    fun prepare(startOnPrepared: Boolean) {

        if (mediaPlayer == null) {
            return
        }

        mediaPlayer!!.prepareAsync()

        mediaPlayer!!.setOnPreparedListener {

            onPreparedListener?.invoke(it)

            mediaPlayerPrepared = true

            val progress = makeTimeString(context, currentPosition.toLong())
            val duration = makeTimeString(context, mediaPlayer!!.duration.toLong())
            binding.textView.text = context.getString(R.string.progress_duration, progress, duration)

            binding.seekBar.max = mediaPlayer!!.duration

            if (startOnPrepared) start()
        }

        mediaPlayer!!.setOnCompletionListener {

            currentPosition = 0

            myHandler.removeCallbacks(myRunnable)

            val progress = makeTimeString(context, 0)
            val duration = makeTimeString(context, mediaPlayer!!.duration.toLong())
            binding.textView.text = context.getString(R.string.progress_duration, progress, duration)

            binding.imageButton.setImageResource(R.drawable.ic_round_play_arrow_24)

            binding.seekBar.progress = 0
        }
    }

    fun start() {

        if (mediaPlayer == null) {
            return
        }

        if (mediaPlayer!!.isPlaying) {
            return
        }

        if (!mediaPlayerPrepared) {
            return
        }

        var canStart = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val audioFocusRequest = audioManager.requestAudioFocus(focusRequest!!)

            canStart = audioFocusRequest == AudioManager.AUDIOFOCUS_REQUEST_GRANTED
        }

        if (canStart) {

            mediaPlayer!!.start()
            mediaPlayer!!.seekTo(currentPosition)

            myHandler.post(myRunnable)

            binding.imageButton.setImageResource(R.drawable.ic_round_pause_24)
        }
    }

    fun pause() {

        if (mediaPlayer == null) {
            return
        }

        if (!mediaPlayer!!.isPlaying) {
            return
        }

        if (!mediaPlayerPrepared) {
            return
        }

        currentPosition = mediaPlayer!!.currentPosition
        mediaPlayer!!.pause()

        myHandler.removeCallbacks(myRunnable)

        binding.imageButton.setImageResource(R.drawable.ic_round_play_arrow_24)
    }

    fun release() {

        if (mediaPlayer == null) {
            return
        }

        currentPosition = 0

        mediaPlayer!!.release()
        mediaPlayerPrepared = false

        myHandler.removeCallbacks(myRunnable)

        val progress = makeTimeString(context, 0)
        val duration = makeTimeString(context, 0)
        binding.textView.text = context.getString(R.string.progress_duration, progress, duration)

        binding.imageButton.setImageResource(R.drawable.ic_round_play_arrow_24)

        binding.seekBar.progress = 0
    }

    private fun intToScaleType(i: Int): ImageView.ScaleType {

        return when (i) {

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

            else -> {
                ImageView.ScaleType.FIT_CENTER
            }
        }
    }

    private fun scaleTypeToInt(scaleType: ImageView.ScaleType): Int {

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

    private fun makeTimeString(context: Context, millis: Long): String {

        val hours = (millis / (1000 * 60 * 60)).toInt()
        val minutes = (millis % (1000 * 60 * 60) / (1000 * 60)).toInt()
        val seconds = (millis % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()

        return if (hours > 0) context.getString(R.string.hours_minutes_seconds, hours, minutes, seconds) else context.getString(R.string.minutes_seconds, minutes, seconds)
    }

    class AnimationTouchListener : View.OnTouchListener {

        var scaleX = 0.90f
        var scaleY = 0.90f
        var duration: Long = 100

        private var scaleDownAnimationEnded = false
        private var canBack = false

        override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {

            if (view != null && motionEvent != null) {

                if (motionEvent.action == MotionEvent.ACTION_DOWN) {

                    scaleDownAnimationEnded = false
                    canBack = false

                    view.animate().cancel()
                    view.animate().scaleY(scaleX).scaleX(scaleY).setDuration(duration).withEndAction {

                        scaleDownAnimationEnded = true

                        if (canBack) {
                            view.animate().cancel()
                            view.animate().scaleY(1.0f).scaleX(1.0f).setDuration(duration).start()
                        }

                    }.start()

                } else if (motionEvent.action == MotionEvent.ACTION_UP) {

                    canBack = true

                    if (scaleDownAnimationEnded) {
                        view.animate().cancel()
                        view.animate().scaleY(1.0f).scaleX(1.0f).setDuration(duration).start()
                    }
                }

                return false
            }

            return true
        }
    }
}