package com.agarron.frescosamples

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.facebook.drawee.span.DraweeSpan
import com.facebook.drawee.span.DraweeSpanStringBuilder
import com.facebook.drawee.span.SimpleDraweeSpanTextView

class MainActivity : AppCompatActivity() {

  val simpleDraweeSpanTextView: SimpleDraweeSpanTextView
      by lazy { findViewById(R.id.simple_drawee_span_text_view) as SimpleDraweeSpanTextView }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    renderImage()
  }

  private fun renderImage() {
    val builder = DraweeSpanStringBuilder("Animated Image: ")

    val startIndex = builder.length
    builder.append("<image>")

    val controller = Fresco.newDraweeControllerBuilder()
        .setUri("http://cultofthepartyparrot.com/parrots/hd/parrot.gif")
        .setAutoPlayAnimations(true)
        .build()

    val draweeHierarchy = GenericDraweeHierarchyBuilder.newInstance(resources)
        .setPlaceholderImage(ColorDrawable(Color.TRANSPARENT))
        .setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER)
        .build()

    builder.setImageSpan(
        this,
        draweeHierarchy,
        controller,
        startIndex,
        builder.length - 1,
        dpToPixels(IMAGE_WIDTH_DP),
        dpToPixels(IMAGE_HEIGHT_DP),
        false,
        DraweeSpan.ALIGN_CENTER)

    simpleDraweeSpanTextView.setDraweeSpanStringBuilder(builder)
  }

  companion object {

    private const val IMAGE_WIDTH_DP = 50
    private const val IMAGE_HEIGHT_DP = 50

    private val DENSITY = Resources.getSystem().displayMetrics.density

    fun dpToPixels(dp: Int) = (dp * DENSITY).toInt()
  }
}
