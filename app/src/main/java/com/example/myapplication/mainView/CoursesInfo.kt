package com.example.myapplication.mainView

import androidx.compose.ui.graphics.Color
import com.example.myapplication.R

// ── Modelo de contenido enriquecido ──────────────────────────────────────────

sealed class CourseSection {
    data class PlainText(val body: String) : CourseSection()
    data class Heading(val title: String, val body: String) : CourseSection()
    data class ClinicalNote(val body: String) : CourseSection()
    data class AccentQuote(val body: String) : CourseSection()
}

data class CourseItem(
    val id: Int,
    val title: String,
    val description: String,
    val content: String,                          // se mantiene por compatibilidad
    val imageRes: Int? = null,
    val sections: List<CourseSection>? = null,    // nuevo: contenido estructurado
    val categoryLabel: String = "Endodoncia",
    val moduleIndex: Int? = null,                 // ej. 3
    val moduleTotal: Int? = null,                 // ej. 8
    val progress: Float? = null,
    val totalLessons: Int? = null,
    val completedLessons: Int? = null,
    val duration: String? = null,
    val modules: Int? = null,
    val students: Int? = null,
    val avatarColors: List<Color>? = null,
    val buttonLabel: String,
    val imageColor: Color
)

object Courses {

    val dummyCourses = listOf(

        CourseItem(
            id = 0,
            title = "Acceso Cameral",
            imageRes = R.drawable.acceso_cameral,
            description = "Introducción al procedimiento quirúrgico de apertura de la cámara pulpar.",
            content = "",
            categoryLabel = "Endodoncia",
            moduleIndex = 1, moduleTotal = 8,
            sections = listOf(
                CourseSection.PlainText(
                    "Fase quirúrgica con la que se abre la cámara pulpar previa a la localización y " +
                            "exploración de los conductos. Su objetivo es obtener un acceso directo, amplio y sin " +
                            "obstáculos a la zona apical del conducto radicular."
                ),
                CourseSection.ClinicalNote(
                    "Es indispensable dominar la morfología de la cámara pulpar, así como las posibles " +
                            "alteraciones que puedan presentarse."
                )
            ),
            progress = null, totalLessons = null, completedLessons = null,
            duration = null, modules = null, students = null, avatarColors = null,
            buttonLabel = "Ver detalles", imageColor = Color(0xFF1E3A5F)
        ),

        CourseItem(
            id = 1,
            title = "Aspectos a considerar",
            imageRes = R.drawable.aspectos_a_considerar,
            description = "Principios y reglas fundamentales que guían la correcta ejecución del acceso cameral.",
            content = "",
            categoryLabel = "Técnica",
            moduleIndex = 2, moduleTotal = 8,
            sections = listOf(
                CourseSection.Heading(
                    title = "Principios de forma",
                    body = "La forma de la cavidad de acceso cameral debe corresponder a la forma de la cámara. " +
                            "El abordaje en dientes anteriores es por la cara lingual o palatina; en dientes " +
                            "posteriores por la cara oclusal."
                ),
                CourseSection.Heading(
                    title = "Consideraciones en multiradiculares",
                    body = "En los dientes multiradiculares, el acceso debe incluir el techo de la cámara pulpar " +
                            "completo y debe evitarse la deformación del piso."
                ),
                CourseSection.AccentQuote(
                    "No debe localizarse el conducto a través de la cavidad de caries."
                ),
                CourseSection.Heading(
                    title = "Dientes jóvenes vs. adultos",
                    body = "La pulpa cameral es mayor en un diente joven que en uno adulto, y el acceso cameral " +
                            "debe ser igual. El límite de la apertura coronaria deberá incluir todos los cuernos pulpares."
                ),
                CourseSection.ClinicalNote(
                    "Nunca deberá deformarse la pared cervical o el piso de la cámara pulpar."
                )
            ),
            progress = null, totalLessons = null, completedLessons = null,
            duration = null, modules = null, students = null, avatarColors = null,
            buttonLabel = "Ver Detalles", imageColor = Color(0xFF2D6A4F)
        ),

        CourseItem(
            id = 2,
            title = "Pasos de la técnica",
            imageRes = R.drawable.pasos_tecnica,
            description = "Secuencia clínica paso a paso para realizar la apertura de la cámara pulpar.",
            content = "",
            categoryLabel = "Técnica",
            moduleIndex = 3, moduleTotal = 8,
            sections = listOf(
                CourseSection.Heading("1. Apertura", "Apertura inicial de la cámara pulpar con fresa de fisura."),
                CourseSection.Heading("2. Remoción del techo", "Eliminación completa del techo de la cámara pulpar."),
                CourseSection.Heading("3. Pulpa cameral", "Eliminación de la pulpa cameral en su totalidad."),
                CourseSection.Heading(
                    title = "4. Rectificación",
                    body = "Rectificación de las paredes de la cámara para tener acceso amplio y directo a la " +
                            "zona apical del conducto. Se realiza el desgaste compensatorio."
                )
            ),
            progress = null, totalLessons = null, completedLessons = null,
            duration = null, modules = null, students = null, avatarColors = null,
            buttonLabel = "Ver detalles", imageColor = Color(0xFF1E3A5F)
        ),

        CourseItem(
            id = 3,
            title = "Dientes Anteriores",
            imageRes = R.drawable.dientes_anteriores,
            description = "Técnica de acceso cameral para incisivos y caninos.",
            content = "",
            categoryLabel = "Anatomía clínica",
            moduleIndex = 4, moduleTotal = 8,
            sections = listOf(
                CourseSection.Heading(
                    title = "Punto de entrada",
                    body = "La entrada siempre se realiza por la cara palatina o lingual. La penetración " +
                            "inicial se hace en el tercio medio, en el centro exacto de la superficie lingual, " +
                            "a 2–3 mm del borde incisal y 1–2 mm del cingulum."
                ),
                CourseSection.AccentQuote(
                    "Fresa de fisura troncocónica 701 o fisura cilíndrica 556 con superalta velocidad, " +
                            "en posición perpendicular al eje longitudinal del diente."
                ),
                CourseSection.Heading(
                    title = "Técnica de penetración",
                    body = "Se penetra solo en esmalte hasta el límite amelodentinario. Después del punto de " +
                            "penetración, se gira la mano hacia incisal para que la fresa tome la dirección " +
                            "paralela al eje longitudinal del diente, hasta sentir la sensación de caída o vacío."
                ),
                CourseSection.Heading(
                    title = "Forma final de la cavidad",
                    body = "En incisivos la forma es triangular; en el canino es romboidal u ovoide. " +
                            "En el diente joven con pulpa grande el contorno refleja la anatomía interior " +
                            "triangular; en el adulto con cámara obliterada es ovoide."
                ),
                CourseSection.ClinicalNote(
                    "Usar fresas 1 o 2 redondas para eliminar escombros de los cuernos pulpares. " +
                            "Previene el descoloramiento futuro por restos de tejido pulpar."
                )
            ),
            progress = null, totalLessons = null, completedLessons = null,
            duration = null, modules = null, students = null, avatarColors = null,
            buttonLabel = "Ver detalles", imageColor = Color(0xFF1E3A5F)
        ),

        CourseItem(
            id = 4,
            title = "Premolares",
            imageRes = R.drawable.premolares,
            description = "Técnica de apertura cameral para premolares con sus particularidades anatómicas.",
            content = "",
            categoryLabel = "Anatomía clínica",
            moduleIndex = 5, moduleTotal = 8,
            sections = listOf(
                CourseSection.Heading(
                    title = "Entrada oclusal",
                    body = "La entrada siempre se realiza a través de la superficie oclusal. La penetración " +
                            "inicial se realiza en el centro exacto del surco central del premolar, siguiendo " +
                            "el eje longitudinal del diente."
                ),
                CourseSection.AccentQuote(
                    "La fresa produce la sensación de vacío cuando llega a la cámara; luego se dirige " +
                            "buco-lingualmente con movimientos más intensos y proximales leves."
                ),
                CourseSection.Heading(
                    title = "Forma y particularidades",
                    body = "La preparación tiene contorno ovoide. En premolares inferiores, cuya cara lingual " +
                            "mira hacia la lengua, es necesario incluir la cúspide vestibular en la apertura."
                )
            ),
            progress = null, totalLessons = null, completedLessons = null,
            duration = null, modules = null, students = null, avatarColors = null,
            buttonLabel = "Ver detalles", imageColor = Color(0xFF1E3A5F)
        ),

        CourseItem(
            id = 5,
            title = "Molares Maxilares",
            imageRes = R.drawable.molares_maxilares,
            description = "Apertura cameral en molares superiores: forma triangular o trapezoidal.",
            content = "",
            categoryLabel = "Anatomía clínica",
            moduleIndex = 6, moduleTotal = 8,
            sections = listOf(
                CourseSection.Heading(
                    title = "Contorno de entrada",
                    body = "La entrada se realiza por la superficie oclusal en los 2/3 mesiales. El contorno " +
                            "es triangular con base hacia vestibular y vértice hacia palatino."
                ),
                CourseSection.Heading(
                    title = "Secuencia de localización",
                    body = "La fresa se dirige hacia el cuerno mesio-bucal (MV), cercano a la cúspide " +
                            "homónima. Luego se cambia hacia disto-bucal, sin cruzar el reborde oblicuo, y " +
                            "finalmente hacia palatino para localizar el conducto palatino."
                ),
                CourseSection.ClinicalNote(
                    "En el primer molar superior el contorno se modifica a forma trapezoidal porque el " +
                            "conducto distobucal no está tan próximo a la superficie bucal como el mesial."
                )
            ),
            progress = null, totalLessons = null, completedLessons = null,
            duration = null, modules = null, students = null, avatarColors = null,
            buttonLabel = "Ver detalles", imageColor = Color(0xFF1E3A5F)
        ),

        CourseItem(
            id = 6,
            title = "Molares Mandibulares",
            imageRes = R.drawable.molares_mandibulares,
            description = "Apertura cameral para molares inferiores: triángulo de base mesial.",
            content = "",
            categoryLabel = "Anatomía clínica",
            moduleIndex = 7, moduleTotal = 8,
            sections = listOf(
                CourseSection.Heading(
                    title = "Punto de entrada",
                    body = "La penetración inicial se realiza entre el vértice de la cúspide mesio-vestibular " +
                            "y la estría central, siguiendo el eje longitudinal del diente. Ahí se localiza " +
                            "el cuerno mesio-bucal."
                ),
                CourseSection.Heading(
                    title = "Cierre del triángulo",
                    body = "Se extiende 2 mm hacia lingual para localizar el cuerno mesio-lingual, luego hacia " +
                            "distal (fosa central) para el cuerno pulpar distal. Se cierra el triángulo con " +
                            "fresa redonda #4."
                ),
                CourseSection.ClinicalNote(
                    "El triángulo presenta base hacia mesial y vértice a distal. En ocasiones, por la " +
                            "presencia de un segundo conducto distal en el primer molar, adquiere forma rectangular."
                )
            ),
            progress = null, totalLessons = null, completedLessons = null,
            duration = null, modules = null, students = null, avatarColors = null,
            buttonLabel = "Ver detalles", imageColor = Color(0xFF1E3A5F)
        ),

        CourseItem(
            id = 7,
            title = "Instrumentación de Conductos",
            imageRes = R.drawable.intrumentacion,
            description = "Objetivo y fundamento de la instrumentación radicular.",
            content = "",
            categoryLabel = "Endodoncia",
            moduleIndex = 8, moduleTotal = 8,
            sections = listOf(
                CourseSection.PlainText(
                    "La instrumentación busca limpiar los conductos de restos de tejido pulpar, bacterias " +
                            "y restos tisulares necróticos, y darles una forma que permita su relleno con un " +
                            "material biológicamente inerte."
                ),
                CourseSection.AccentQuote(
                    "La instrumentación debe llegar hasta la parte más estrecha del conducto: " +
                            "la constricción apical, que coincide con el límite cemento-dentinario."
                ),
                CourseSection.Heading(
                    title = "Tríada endodóntica",
                    body = "Piedra angular del éxito del tratamiento: Asepsia, Preparación Biomecánica " +
                            "y Sellado Hermético."
                )
            ),
            progress = null, totalLessons = null, completedLessons = null,
            duration = null, modules = null, students = null, avatarColors = null,
            buttonLabel = "Ver detalles", imageColor = Color(0xFF1E3A5F)
        )
    )
}