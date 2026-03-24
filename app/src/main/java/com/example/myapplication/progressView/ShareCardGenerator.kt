package com.example.myapplication.progressView


import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import androidx.core.content.FileProvider
import android.content.Intent
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import kotlin.math.min

object ShareCardGenerator {

    private const val SIZE = 1080
    private const val HALF = SIZE / 2f

    // Colores de EstomatoLearn
    private val COLOR_BG_DARK   = Color.parseColor("#1530B8")
    private val COLOR_BG_MAIN   = Color.parseColor("#1A3ADB")
    private val COLOR_WHITE      = Color.WHITE
    private val COLOR_GREEN      = Color.parseColor("#4ADE80")
    private val COLOR_RED_SOFT   = Color.parseColor("#FCA5A5")
    private val COLOR_WHITE_60   = Color.argb(153, 255, 255, 255)  // 60% alpha
    private val COLOR_WHITE_15   = Color.argb(38,  255, 255, 255)  // 15% alpha
    private val COLOR_WHITE_10   = Color.argb(25,  255, 255, 255)  // 10% alpha
    private val COLOR_WHITE_40   = Color.argb(102, 255, 255, 255)  // 40% alpha

    fun generateAndShare(
        context: Context,
        userName: String,
        moduleName: String,
        score: Int,
        totalQuestions: Int
    ) {
        val bitmap = buildBitmap(userName, moduleName, score, totalQuestions)
        val uri    = saveBitmapToCache(context, bitmap)
        launchShareIntent(context, uri, userName, moduleName, score, totalQuestions)
    }

    // ── Construcción del bitmap ───────────────────────────────────────────────

    private fun buildBitmap(
        userName: String,
        moduleName: String,
        score: Int,
        total: Int
    ): Bitmap {
        val bmp    = Bitmap.createBitmap(SIZE, SIZE, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)
        val paint  = Paint(Paint.ANTI_ALIAS_FLAG)

        drawBackground(canvas, paint)
        drawHeaderSection(canvas, paint, userName, moduleName)
        drawScoreCircle(canvas, paint, score, total)
        drawStatCards(canvas, paint, score, total)
        drawFooter(canvas, paint)

        return bmp
    }

    private fun drawBackground(canvas: Canvas, paint: Paint) {
        // Fondo principal azul
        paint.color = COLOR_BG_MAIN
        canvas.drawRoundRect(RectF(0f, 0f, SIZE.toFloat(), SIZE.toFloat()), 60f, 60f, paint)

        // Banda superior más oscura
        paint.color = COLOR_BG_DARK
        canvas.drawRoundRect(RectF(0f, 0f, SIZE.toFloat(), 280f), 60f, 60f, paint)
        canvas.drawRect(0f, 220f, SIZE.toFloat(), 280f, paint)

        // Círculos decorativos sutiles
        paint.color = COLOR_WHITE_10
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3f
        canvas.drawCircle(200f, 180f, 120f, paint)
        canvas.drawCircle(880f, 100f, 80f, paint)
        canvas.drawCircle(700f, 260f, 50f, paint)
        paint.style = Paint.Style.FILL
    }

    private fun drawHeaderSection(
        canvas: Canvas, paint: Paint,
        userName: String, moduleName: String
    ) {
        // App name
        paint.color  = COLOR_WHITE_60
        paint.textSize    = 34f
        paint.typeface    = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        paint.letterSpacing = 0.15f
        drawCenteredText(canvas, paint, "ESTOMATOLEARN", 130f)
        paint.letterSpacing = 0f

        // Nombre del usuario
        paint.color    = COLOR_WHITE
        paint.textSize = 54f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        drawCenteredText(canvas, paint, userName, 210f)

        // Nombre del módulo
        paint.color    = COLOR_WHITE_60
        paint.textSize = 38f
        paint.typeface = Typeface.DEFAULT
        drawCenteredText(canvas, paint, moduleName, 268f)
    }

    private fun drawScoreCircle(canvas: Canvas, paint: Paint, score: Int, total: Int) {
        val cx       = HALF
        val cy       = 560f
        val radius   = 220f
        val stroke   = 40f
        val progress = if (total > 0) score.toFloat() / total else 0f

        // Track (fondo)
        paint.style       = Paint.Style.STROKE
        paint.strokeWidth = stroke
        paint.color       = COLOR_WHITE_15
        paint.strokeCap   = android.graphics.Paint.Cap.ROUND
        canvas.drawCircle(cx, cy, radius, paint)

        // Arco de progreso
        paint.color = COLOR_WHITE
        val oval    = RectF(cx - radius, cy - radius, cx + radius, cy + radius)
        val sweep   = 360f * progress
        canvas.drawArc(oval, -90f, sweep, false, paint)

        // Texto del score
        paint.style    = Paint.Style.FILL
        paint.color    = COLOR_WHITE
        paint.textSize = 200f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        drawCenteredText(canvas, paint, score.toString(), cy + 70f)

        // "/ total"
        paint.color    = COLOR_WHITE_60
        paint.textSize = 60f
        paint.typeface = Typeface.DEFAULT
        drawCenteredText(canvas, paint, "/ $total", cy + 150f)
    }

    private fun drawStatCards(canvas: Canvas, paint: Paint, score: Int, total: Int) {
        val incorrect = total - score
        val cardTop    = 840f
        val cardHeight = 170f
        val cardRadius = 36f
        val gap        = 30f
        val cardWidth  = (SIZE - 80f - gap) / 2f
        val leftX      = 40f
        val rightX     = leftX + cardWidth + gap

        // Tarjeta correctas
        paint.color = COLOR_WHITE_15
        canvas.drawRoundRect(RectF(leftX, cardTop, leftX + cardWidth, cardTop + cardHeight), cardRadius, cardRadius, paint)

        paint.color    = COLOR_WHITE_60
        paint.textSize = 32f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        paint.letterSpacing = 0.1f
        drawCenteredText(canvas, paint, "CORRECTAS", cardTop + 68f, leftX + cardWidth / 2f)
        paint.letterSpacing = 0f

        paint.color    = COLOR_GREEN
        paint.textSize = 110f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        drawCenteredText(canvas, paint, score.toString(), cardTop + 155f, leftX + cardWidth / 2f)

        // Tarjeta incorrectas
        paint.color = COLOR_WHITE_15
        canvas.drawRoundRect(RectF(rightX, cardTop, rightX + cardWidth, cardTop + cardHeight), cardRadius, cardRadius, paint)

        paint.color    = COLOR_WHITE_60
        paint.textSize = 32f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        paint.letterSpacing = 0.1f
        drawCenteredText(canvas, paint, "INCORRECTAS", cardTop + 68f, rightX + cardWidth / 2f)
        paint.letterSpacing = 0f

        paint.color    = COLOR_RED_SOFT
        paint.textSize = 110f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        drawCenteredText(canvas, paint, incorrect.toString(), cardTop + 155f, rightX + cardWidth / 2f)
    }

    private fun drawFooter(canvas: Canvas, paint: Paint) {
        paint.color    = COLOR_WHITE_40
        paint.textSize = 30f
        paint.typeface = Typeface.DEFAULT
        drawCenteredText(canvas, paint, "estomatolearn.app", SIZE - 40f)
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private fun drawCenteredText(canvas: Canvas, paint: Paint, text: String, y: Float, cx: Float = HALF) {
        canvas.drawText(text, cx - paint.measureText(text) / 2f, y, paint)
    }

    private fun saveBitmapToCache(context: Context, bitmap: Bitmap): Uri {
        val cachedir = File(context.cacheDir, "share_images").apply { mkdirs() }
        val file     = File(cachedir, "estomato_result.png")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
    }

    private fun launchShareIntent(
        context: Context,
        imageUri: Uri,
        userName: String,
        moduleName: String,
        score: Int,
        total: Int
    ) {
        val percentage = if (total > 0) (score * 100) / total else 0
        val text = """
            🦷 *Resultados EstomatoLearn*
            
            ¡Hola! Soy *$userName*.
            Completé el módulo: *$moduleName*
            
            📊 Calificación: $score/$total ($percentage%)
            
            Enviado desde la app EstomatoLearn 📱
        """.trimIndent()

        val whatsappIntent = Intent(Intent.ACTION_SEND).apply {
            type    = "image/*"
            `package` = "com.whatsapp"
            putExtra(Intent.EXTRA_STREAM, imageUri)
            putExtra(Intent.EXTRA_TEXT, text)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        val pm          = context.packageManager
        val resolveInfo = pm.queryIntentActivities(whatsappIntent, 0)

        if (resolveInfo.isNotEmpty()) {
            context.startActivity(whatsappIntent)
        } else {
            val genericIntent = Intent(Intent.ACTION_SEND).apply {
                type = "image/*"
                putExtra(Intent.EXTRA_STREAM, imageUri)
                putExtra(Intent.EXTRA_TEXT, text)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(Intent.createChooser(genericIntent, "Compartir resultados"))
        }
    }
}